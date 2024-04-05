package com.lothus.sync.stats.menus.games.bedwars.deathcries.confirm;

import com.lothus.core.Core;
import com.lothus.core.player.LothPlayer;
import com.lothus.core.utils.bukkit.ItemCreator;
import com.lothus.sync.stats.data.type.DataType;
import com.lothus.sync.stats.games.addons.deathcries.DeathCry;
import com.lothus.sync.stats.platform.Platform;
import com.lothus.sync.stats.player.games.bedwars.BedPlayer;
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

public class ConfirmDeathMenu implements Listener {

    public static void open(Player player, DeathCry deathCry) {
        Inventory inventory = Bukkit.createInventory(null, 9*3, "Confirmar - Grito de Morte");

        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("deathCry", deathCry.getIdentify());
        
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
        if (!inventory.getName().equals("Confirmar - Grito de Morte")) return;

        event.setCancelled(true);

        if (event.getRawSlot() == 13) {
            player.closeInventory();
            return;
        }

        LothPlayer l = Core.getPlayerController().get(player.getUniqueId());
        BedPlayer bedPlayer = Platform.getBedPlatform().getBedPlayerController().getAccount(player.getUniqueId());

        ItemStack i = CraftItemStack.asNMSCopy(currentItem);

        if (!i.hasTag()) return;

        DeathCry deathCry = Platform.getDeathController().getKit(i.getTag().getString("deathCry"));

        if (deathCry == null) return;

        player.closeInventory();

        if (bedPlayer.hasDeathCry(deathCry)) {
            player.sendMessage("§cVocê já comprou este grito de morte.");
            return;
        }

        boolean cash = (event.getRawSlot() == 15);

        if (cash) {
            if (l.getCash() < 250) {
                player.sendMessage("§cVocê não possui cash o suficiente para realizar essa compra.");
                return;
            }
            l.setCash(l.getCash() - 250);
            l.getGroup().addPermission(deathCry.getPermission(), -1L);
            player.sendMessage("§eVocê comprou §b" + deathCry.getIdentify() + "§e.");
            Core.getDataPlayer().update(l);
        } else {
            if (bedPlayer.getCoins() < 500) {
                player.sendMessage("§cVocê não possui coins o suficiente para realizar essa compra.");
                return;
            }

            bedPlayer.setCoins(bedPlayer.getCoins() - 500);
            l.getGroup().addPermission(deathCry.getPermission(), -1L);
            player.sendMessage("§eVocê comprou §b" + deathCry.getIdentify() + "§e.");
            Platform.getDataPlayer().update(DataType.BED_WARS_ACCOUNT, bedPlayer);
            Core.getDataPlayer().update(l);
        }
    }
}
