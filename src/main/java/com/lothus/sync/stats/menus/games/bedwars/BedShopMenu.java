package com.lothus.sync.stats.menus.games.bedwars;

import com.lothus.core.api.menu.AbstractMenu;
import com.lothus.core.utils.bukkit.ItemCreator;
import com.lothus.sync.stats.platform.Platform;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import static org.bukkit.inventory.ItemFlag.*;
import static org.bukkit.inventory.ItemFlag.HIDE_POTION_EFFECTS;

public class BedShopMenu extends AbstractMenu {

    private Inventory inventory = getInventory();

    public BedShopMenu() {
        super(
                "Loja - Bed Wars",
                (9*3)
        );
    }

    @Override
    public void items() {
        inventory.setItem(12, new ItemCreator(Material.SKULL_ITEM, "§aGritos de morte")
                .setLore(
                        "§7Deixe os outros saberem o ",
                        "§7quão salgadas são suas lágrimas ",
                        "§7toda vez que você morrer com ",
                        "§7esses gritos de morte.",
                        "§eClique para expandir."
                )
                .setId(3).withSkullURL(
                        "https://textures.minecraft.net/texture/8069be687d1c1a3d6c2f77376febbf57143cb9401ac1a37c8a0ade5439c728d6"
                ).addItemFlag(HIDE_ATTRIBUTES, HIDE_DESTROYS, HIDE_ENCHANTS, HIDE_UNBREAKABLE, HIDE_POTION_EFFECTS, HIDE_POTION_EFFECTS).setAmount(1).build());
        inventory.setItem(14, new ItemCreator(Material.BOOK_AND_QUILL, "§aMensagem de abate")
                .setLore(
                        "§7Deixe os seus inimigos preocupados",
                        "§7com essas mensagens de abate.",
                        "§eClique para expandir."
                ).addItemFlag(HIDE_ATTRIBUTES, HIDE_DESTROYS, HIDE_ENCHANTS, HIDE_UNBREAKABLE, HIDE_POTION_EFFECTS, HIDE_POTION_EFFECTS).build());
    }

    @Override
    public void items(Player player) {}


    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        ItemStack itemStack = event.getCurrentItem();

        if (inventory == null)return;
        if (itemStack == null || itemStack.getType() == Material.AIR)return;
        if (!inventory.getTitle().equals(getTitle()))return;

        event.setCancelled(true);

        if (event.getRawSlot() == 12) {
            Platform.getMenuController().getBedWarsMenus().getDeathMenu().open(player);
            return;
        }

        if (event.getRawSlot() == 14) {
            Platform.getMenuController().getBedWarsMenus().getSlaughterMenu().open(player);
            return;
        }
    }
}
