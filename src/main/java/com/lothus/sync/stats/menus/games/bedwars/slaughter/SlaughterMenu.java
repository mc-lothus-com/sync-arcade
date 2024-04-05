package com.lothus.sync.stats.menus.games.bedwars.slaughter;

import com.lothus.core.api.menu.AbstractMenu;
import com.lothus.core.utils.bukkit.ItemCreator;
import com.lothus.sync.stats.data.type.DataType;
import com.lothus.sync.stats.games.addons.slaughter.Slaughter;
import com.lothus.sync.stats.menus.games.bedwars.slaughter.confirm.ConfirmSlaughterMenu;
import com.lothus.sync.stats.platform.Platform;
import com.lothus.sync.stats.player.games.bedwars.BedPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.ClickType;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.bukkit.Material.AIR;

public class SlaughterMenu extends AbstractMenu {

    private HashMap<UUID, Long> cooldown = new HashMap<>();

    public SlaughterMenu() {
        super(
                "Loja - Mensagens de Abate",
                (9*6)
        );
    }

    @Override
    public void items() {
        Inventory inventory = getInventory();
        inventory.setItem(49, new ItemCreator(Material.ARROW, "§cVoltar").build());
    }

    @Override
    public void items(Player player) {
        int slot = 9;
        Inventory inventory = getInventory();
        for (Slaughter slaughter : Platform.getSlaughterController().getDeaths()) {
            slot++;

            if (slot == 17) slot+=2;
            if (slot == 26) slot+=2;
            if (slot == 35) slot+=2;

            inventory.setItem(slot, slaughter.icon(player));
        }
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        ItemStack i = event.getCurrentItem();

        if (i == null || i.getType().equals(AIR))return;
        if (inventory == null)return;

        if (!inventory.getTitle().equalsIgnoreCase(getTitle()))return;

        event.setCancelled(true);

        if (event.getRawSlot() == 49) {
            Platform.getMenuController().getSkyWarsMenus().getShopMenu().open(player);
            return;
        }

        BedPlayer bedPlayer = Platform.getBedPlatform().getBedPlayerController().getAccount(player.getUniqueId());
        Slaughter slaughter = Platform.getSlaughterController().getKit(i.getItemMeta().getDisplayName().split("§a")[1]);

        if (slaughter == null)return;

        if (event.getClick() == ClickType.RIGHT) {
            player.sendMessage(slaughter.message("§cOponente", "§d" + player.getName()));
            return;
        }

        if (bedPlayer.hasSlaughter(slaughter)) {
            if (bedPlayer.getSlaughter().equalsIgnoreCase(slaughter.getIdentify())) {
                player.sendMessage("§cVocê já selecionou esta mensagem de abate.");
                return;
            }

            if (cooldown.get(player.getUniqueId()) != null && cooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                player.sendMessage("§cVocê deve aguardar para selecionar outra mensagem de abate.");
                return;
            }

            cooldown.put(player.getUniqueId(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(15));

            player.closeInventory();
            player.sendMessage("§eVocê mudou de §b" + (bedPlayer.getSlaughter() == "None" ? "Nenhum" : bedPlayer.getSlaughter()) + " §epara §b" + slaughter.getIdentify() + "§e.");
            bedPlayer.setSlaughter(slaughter.getIdentify());
            Platform.getDataPlayer().update(DataType.BED_WARS_ACCOUNT, bedPlayer);
            open(player);
            return;
        }

        ConfirmSlaughterMenu.open(player, slaughter);
        return;
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        InventoryView viewer = event.getView();
        Player player = (Player) viewer.getPlayer();

        if (!viewer.getTitle().equals(getTitle()))return;

        getViewers().remove(player.getUniqueId());
    }

}
