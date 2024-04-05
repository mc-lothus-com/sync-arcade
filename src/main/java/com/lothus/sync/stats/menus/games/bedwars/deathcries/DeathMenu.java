package com.lothus.sync.stats.menus.games.bedwars.deathcries;

import com.lothus.core.Core;
import com.lothus.core.api.menu.AbstractMenu;
import com.lothus.core.utils.bukkit.ItemCreator;
import com.lothus.sync.stats.data.type.DataType;
import com.lothus.sync.stats.games.addons.deathcries.DeathCry;
import com.lothus.sync.stats.menus.games.bedwars.deathcries.confirm.ConfirmDeathMenu;
import com.lothus.sync.stats.platform.Platform;
import com.lothus.sync.stats.player.games.bedwars.BedPlayer;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.HashMap;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import static org.bukkit.Material.AIR;

public class DeathMenu extends AbstractMenu {

    private HashMap<UUID, Long> cooldown = new HashMap<>();

    public DeathMenu() {
        super(
                "Loja - Gritos de Morte",
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
        for (DeathCry deathCry : Platform.getDeathController().getDeaths()) {
            slot++;

            if (slot == 17) slot+=2;
            if (slot == 26) slot+=2;
            if (slot == 35) slot+=2;

            inventory.setItem(slot, deathCry.icon(player));
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

        if (!Core.getServerInfo().getType().name().contains("BED"))return;

        event.setCancelled(true);

        if (event.getRawSlot() == 49) {
            Platform.getMenuController().getBedWarsMenus().getShopMenu().open(player);
            return;
        }

        BedPlayer bedPlayer = Platform.getBedPlatform().getBedPlayerController().getAccount(player.getUniqueId());

        DeathCry deathCry = Platform.getDeathController().getKit(i.getItemMeta().getDisplayName().split("§a")[1]);

        if (bedPlayer.hasDeathCry(deathCry)) {
            if (bedPlayer.getDeathCry().equalsIgnoreCase(deathCry.getIdentify())) {
                player.sendMessage("§cVocê já selecionou este grito de morte.");
                return;
            }
            if (cooldown.get(player.getUniqueId()) != null && cooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                player.sendMessage("§cVocê deve aguardar para selecionar outro grito de morte.");
                return;
            }

            cooldown.put(player.getUniqueId(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(15));

            player.closeInventory();
            player.sendMessage("§eVocê mudou de §b" + (bedPlayer.getDeathCry() == "None" ? "Nenhum" : bedPlayer.getDeathCry()) + " §epara §b" + deathCry.getIdentify() + "§e.");
            bedPlayer.setDeathCry(deathCry.getIdentify());
            Platform.getDataPlayer().update(DataType.BED_WARS_ACCOUNT, bedPlayer);
            open(player);
            return;
        }

        ConfirmDeathMenu.open(player, deathCry);
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
