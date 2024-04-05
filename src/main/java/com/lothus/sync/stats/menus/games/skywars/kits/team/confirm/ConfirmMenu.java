package com.lothus.sync.stats.menus.games.skywars.kits.team.confirm;

import com.lothus.core.Core;
import com.lothus.core.player.LothPlayer;
import com.lothus.core.player.group.rank.Rank;
import com.lothus.core.utils.bukkit.ItemCreator;
import com.lothus.sync.stats.data.type.DataType;
import com.lothus.sync.stats.games.addons.kit.Kit;
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

    public static void open(Player player, Kit kit) {
        Inventory inventory = Bukkit.createInventory(null, 9*3, "Confirmar Kit - Sky Wars Duplas");

        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("kit", kit.getIdentify());

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
        if (!inventory.getName().equals("Confirmar Kit - Sky Wars Duplas")) return;

        event.setCancelled(true);

        if (event.getRawSlot() == 14) {
            player.closeInventory();
            return;
        }

        LothPlayer l = Core.getPlayerController().get(player.getUniqueId());
        SkyPlayer skyPlayer = Platform.getSkyPlatform().getSkyPlayerController().getAccount(player.getUniqueId());
        ItemStack nms = CraftItemStack.asNMSCopy(currentItem);
        NBTTagCompound tag = nms.getTag();
        String kit = tag.getString("kit");
        Kit k = Platform.getSkyPlatform().getKitController().getKit(kit);

        player.closeInventory();
        
        double percent = (l.getGroup().getRank() == Rank.VIP ? 10 : l.getGroup().getRank() == Rank.PRO ? 20 : l.getGroup().getRank() == Rank.MASTER ? 30 : l.getGroup().getRank() == Rank.LOTHUS ? 40 : l.getGroup().getRank().ordinal() <= Rank.BETA.ordinal() ? 50 : 0);
        if (event.getRawSlot() == 11) {
            if (skyPlayer.getCoins() < k.percent(percent)) {
                player.sendMessage("§cVocê não possui coins o suficiente para realizar essa compra.");
                return;
            }

            skyPlayer.setCoins(skyPlayer.getCoins() - k.percent(percent));
            l.getGroup().addPermission(k.getTeamPermission(), -1L);
            player.sendMessage("§eVocê comprou §b" + k.getIdentify() + "§e.");
            Platform.getDataPlayer().update(DataType.SKY_WARS_ACCOUNT, skyPlayer);
            Core.getDataPlayer().update(l);
            return;
        }

        if (event.getRawSlot() == 15) {
            if (l.getCash() < k.percent(percent)) {
                player.sendMessage("§cVocê não possui cash o suficiente para realizar essa compra.");
                return;
            }

            l.setCash(l.getCash() - k.percent(percent));
            l.getGroup().addPermission(k.getTeamPermission(), -1L);
            player.sendMessage("§eVocê comprou §b" + k.getIdentify() + "§e.");
            Core.getDataPlayer().update(l);
            return;
        }
    }
}
