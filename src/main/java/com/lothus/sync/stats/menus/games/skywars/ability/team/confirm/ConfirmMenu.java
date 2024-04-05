package com.lothus.sync.stats.menus.games.skywars.ability.team.confirm;

import com.lothus.core.Core;
import com.lothus.core.player.LothPlayer;
import com.lothus.core.utils.bukkit.ItemCreator;
import com.lothus.sync.stats.data.type.DataType;
import com.lothus.sync.stats.games.addons.ability.Ability;
import com.lothus.sync.stats.platform.Platform;
import com.lothus.sync.stats.player.games.skywars.SkyPlayer;
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.meta.ItemMeta;

public class ConfirmMenu implements Listener {

    public static void open(Player player, Ability kit) {
        Inventory inventory = Bukkit.createInventory(null, 9*3, "Confirmar Hab. - Sky Wars Duplas");

        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("ability", kit.getIdentify());

        ItemStack i = CraftItemStack.asNMSCopy(new ItemCreator(Material.EMERALD).build());

        i.setTag(tag);

        org.bukkit.inventory.ItemStack coins = CraftItemStack.asBukkitCopy(i);
        ItemMeta cMeta = coins.getItemMeta();
        cMeta.setDisplayName("§aComprar com coins");
        coins.setItemMeta(cMeta);

        inventory.setItem(11, coins);

        inventory.setItem(13, new ItemCreator(Material.WOOL, "§cCancelar").setId(14).build());

        ItemStack ia = CraftItemStack.asNMSCopy(new ItemCreator(Material.GOLD_INGOT).build());

        ia.setTag(tag);

        org.bukkit.inventory.ItemStack cash = CraftItemStack.asBukkitCopy(ia);
        ItemMeta cashMeta = cash.getItemMeta();
        cashMeta.setDisplayName("§aComprar com cash");
        cash.setItemMeta(cashMeta);

        inventory.setItem(15, cash);

        player.openInventory(inventory);
    }

    @EventHandler
    public void onInventoryClick(InventoryClickEvent event) {
        Player player = (Player) event.getWhoClicked();
        Inventory inventory = event.getClickedInventory();
        org.bukkit.inventory.ItemStack currentItem = event.getCurrentItem();

        if (inventory == null) return;
        if (currentItem == null) return;

        if (currentItem.getType().equals(Material.AIR)) return;
        if (!inventory.getName().equals("Confirmar Hab. - Sky Wars Duplas")) return;

        event.setCancelled(true);

        if (event.getRawSlot() == 14) {
            player.closeInventory();
            return;
        }

        LothPlayer l = Core.getPlayerController().get(player.getUniqueId());
        SkyPlayer skyPlayer = Platform.getSkyPlatform().getSkyPlayerController().getAccount(player.getUniqueId());
        ItemStack nms = CraftItemStack.asNMSCopy(currentItem);
        NBTTagCompound tag = nms.getTag();
        String kit = tag.getString("ability");
        Ability k = Platform.getSkyPlatform().getAbilityController().getKit(kit);

        player.closeInventory();

        if (event.getRawSlot() == 11) {
            if (skyPlayer.getCoins() < k.getCoins()) {
                player.sendMessage("§cVocê não possui coins o suficiente para realizar essa compra.");
                player.closeInventory();
                return;
            }

            skyPlayer.setCoins(skyPlayer.getCoins() - k.getCoins());
            l.getGroup().addPermission(k.getTeamPermission(), -1L);
            player.sendMessage("§eVocê comprou §b" + k.getIdentify() + "§e.");
            Platform.getDataPlayer().update(DataType.SKY_WARS_ACCOUNT, skyPlayer);
            Core.getDataPlayer().update(l);
        } else if (event.getRawSlot() == 15) {
            if (l.getCash() < k.getCash()) {
                player.sendMessage("§cVocê não possui cash o suficiente para realizar essa compra.");
                player.closeInventory();
                return;
            }

            l.setCash(l.getCash() - k.getCash());
            l.getGroup().addPermission(k.getTeamPermission(), -1L);
            player.sendMessage("§eVocê comprou §b" + k.getIdentify() + "§e.");
            Platform.getDataPlayer().update(DataType.SKY_WARS_ACCOUNT, skyPlayer);
            Core.getDataPlayer().update(l);
            return;
        }
    }
}
