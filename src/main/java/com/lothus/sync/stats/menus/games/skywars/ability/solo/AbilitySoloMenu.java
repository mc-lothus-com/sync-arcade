package com.lothus.sync.stats.menus.games.skywars.ability.solo;

import com.lothus.core.api.menu.AbstractMenu;
import com.lothus.core.games.room.RoomType;
import com.lothus.core.utils.bukkit.ItemCreator;
import com.lothus.sync.stats.data.type.DataType;
import com.lothus.sync.stats.games.addons.ability.Ability;
import com.lothus.sync.stats.menus.games.skywars.ability.solo.confirm.ConfirmMenu;
import com.lothus.sync.stats.platform.Platform;
import com.lothus.sync.stats.player.games.skywars.stats.SkyStats;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.EventPriority;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.InventoryView;
import org.bukkit.inventory.ItemStack;

import java.util.UUID;

public class AbilitySoloMenu extends AbstractMenu {

    public AbilitySoloMenu() {
        super(
                "Habilidades - Sky Wars Solo",
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
        for (Ability kit : Platform.getSkyPlatform().getAbilityController().getAbility()) {
            slot++;

            if (slot == 17) slot += 2;
            if (slot == 26) slot += 2;
            if (slot == 35) slot += 2;

            inventory.setItem(slot, kit.icon(player, RoomType.SOLO));
        }
    }


    @EventHandler
    public void onInventoyCick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        ItemStack currentItem = event.getCurrentItem();

        if (inventory == null) return;
        if (currentItem == null) return;

        if (currentItem.getType().equals(Material.AIR)) return;
        if (!inventory.getTitle().equals(getTitle())) return;

        event.setCancelled(true);

        if (event.getRawSlot() == 49) {
            player.playSound(player.getLocation(), Sound.CLICK, 1, 1);
            Platform.getMenuController().getSkyWarsMenus().getShopMenu().open(player);
            return;
        }

        SkyStats solo = Platform.getSkyPlatform().getSkyPlayerController().get(DataType.SKY_WARS_SOLO, player.getUniqueId());
        net.minecraft.server.v1_8_R3.ItemStack nms = CraftItemStack.asNMSCopy(currentItem);

        if (!nms.hasTag()) return;

        Ability k = Platform.getSkyPlatform().getAbilityController().getKit(nms.getTag().getString("ability"));
        if (k == null) return;
        if (solo.hasAbility(RoomType.SOLO, k)) {
            if (solo.getAbility().equalsIgnoreCase(k.getIdentify())) {
                player.sendMessage("§cEssa habilidade já está selecionada.");
                return;
            }
            solo.setAbility(k.getIdentify());
            player.sendMessage("§eVocê selecionou §b" + k.getIdentify() + "§e.");
            player.closeInventory();
            open(player);
            Platform.getDataStats().update(DataType.SKY_WARS_SOLO, solo);
            return;
        }
        ConfirmMenu.open(player, k);
    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        InventoryView viewer = event.getView();
        Player player = (Player) viewer.getPlayer();

        if (!viewer.getTitle().equals(getTitle()))return;

        getViewers().remove(player.getUniqueId());
    }

}
