package com.lothus.sync.stats.menus.games.skywars;

import com.lothus.core.api.menu.AbstractMenu;
import com.lothus.core.utils.bukkit.ItemCreator;
import com.lothus.sync.stats.platform.Platform;
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

import java.util.UUID;

import static org.bukkit.inventory.ItemFlag.*;
import static org.bukkit.inventory.ItemFlag.HIDE_POTION_EFFECTS;

public class SkyShopMenu extends AbstractMenu {

    public SkyShopMenu() {
        super(
                "Loja - Sky Wars",
                (9*6)
        );
    }

    @Override
    public void items() {
        Inventory inventory = getInventory();
        inventory.setItem(12, new ItemCreator(Material.IRON_SWORD, "§aKits (Solo)")
                .setLore(
                        "§7Selecione um Kit para te",
                        "§7ajudar na sua jornada.",
                        "§eClique para expandir."
                ).addItemFlag(HIDE_ATTRIBUTES, HIDE_DESTROYS, HIDE_ENCHANTS, HIDE_UNBREAKABLE, HIDE_POTION_EFFECTS, HIDE_POTION_EFFECTS).build());

        inventory.setItem(21, new ItemCreator(Material.EXP_BOTTLE, "§aHabilidades (Solo)")
                .setLore(
                        "§7Selecione uma habilidade para",
                        "§7te ajudar na sua jornada.",
                        "§eClique para expandir."
                ).addItemFlag(HIDE_ATTRIBUTES, HIDE_DESTROYS, HIDE_ENCHANTS, HIDE_UNBREAKABLE, HIDE_POTION_EFFECTS, HIDE_POTION_EFFECTS).build());

        inventory.setItem(14, new ItemCreator(Material.DIAMOND_SWORD, "§aKits (Duplas)")
                .setLore(
                        "§7Selecione um Kit para te",
                        "§7ajudar na sua jornada.",
                        "§eClique para expandir."
                ).addItemFlag(HIDE_ATTRIBUTES, HIDE_DESTROYS, HIDE_ENCHANTS, HIDE_UNBREAKABLE, HIDE_POTION_EFFECTS, HIDE_POTION_EFFECTS).build());
        inventory.setItem(23, new ItemCreator(Material.EXP_BOTTLE, "§aHabilidades (Duplas)")
                .setLore(
                        "§7Selecione uma habilidade para",
                        "§7te ajudar na sua jornada.",
                        "§eClique para expandir."
                ).addItemFlag(HIDE_ATTRIBUTES, HIDE_DESTROYS, HIDE_ENCHANTS, HIDE_UNBREAKABLE, HIDE_POTION_EFFECTS, HIDE_POTION_EFFECTS).build());

        inventory.setItem(39, new ItemCreator(Material.SKULL_ITEM, "§aGritos de morte")
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
        inventory.setItem(41, new ItemCreator(Material.BOOK_AND_QUILL, "§aMensagem de abate")
                .setLore(
                        "§7Deixe os seus inimigos preocupados",
                        "§7com essas mensagens de abate.",
                        "§eClique para expandir."
                ).addItemFlag(HIDE_ATTRIBUTES, HIDE_DESTROYS, HIDE_ENCHANTS, HIDE_UNBREAKABLE, HIDE_POTION_EFFECTS, HIDE_POTION_EFFECTS).build());
    }

    @Override
    public void items(Player player) {

    }

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
            Platform.getMenuController().getSkyWarsMenus().getKitsSoloMenu().open(player);
        }

        if (event.getRawSlot() == 21) {
            Platform.getMenuController().getSkyWarsMenus().getAbilitySoloMenu().open(player);
        }

        if (event.getRawSlot() == 14) {
            Platform.getMenuController().getSkyWarsMenus().getKitsTeamMenu().open(player);
        }

        if (event.getRawSlot() == 23) {
            Platform.getMenuController().getSkyWarsMenus().getAbilityTeamMenu().open(player);
        }

        if (event.getRawSlot() == 39) {
            Platform.getMenuController().getSkyWarsMenus().getDeathMenu().open(player);
        }

        if (event.getRawSlot() == 41) {
            Platform.getMenuController().getSkyWarsMenus().getSlaughterMenu().open(player);
        }

    }

    @EventHandler
    public void onInventoryClose(InventoryCloseEvent event) {
        InventoryView viewer = event.getView();
        Player player = (Player) viewer.getPlayer();

        if (!viewer.getTitle().equals(getTitle()))return;

        getViewers().remove(player.getUniqueId());
    }

}
