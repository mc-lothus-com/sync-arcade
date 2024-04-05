package com.lothus.sync.stats.games.addons.kit.register;

import com.lothus.core.Core;
import com.lothus.core.games.room.RoomType;
import com.lothus.core.player.LothPlayer;
import com.lothus.core.player.group.rank.Rank;
import com.lothus.core.utils.bukkit.ItemCreator;
import com.lothus.sync.stats.data.type.DataType;
import com.lothus.sync.stats.games.addons.kit.Kit;
import com.lothus.sync.stats.platform.Platform;
import com.lothus.sync.stats.player.games.skywars.stats.SkyStats;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.player.PlayerFishEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Fisherman extends Kit {

    private HashMap<UUID, Long> cooldown = new HashMap<>();

    public Fisherman() {
        super(
                "Fisherman",
                "kit.skywars.solo.fisherman",
                "kit.skywars.team.fisherman",
                9000,
                (9000/2)
        );
    }

    @Override
    public void apply(Player player) {
        net.minecraft.server.v1_8_R3.ItemStack nms = CraftItemStack.asNMSCopy(new org.bukkit.inventory.ItemStack(Material.FISHING_ROD));
        NBTTagCompound tag = new NBTTagCompound();

        tag.setString("owner", player.getUniqueId().toString());

        nms.setTag(tag);

        org.bukkit.inventory.ItemStack item = CraftItemStack.asBukkitCopy(nms);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aFisherman");
        item.setItemMeta(meta);

        player.getInventory().addItem(item);
    }

    @Override
    public ItemStack getIcon(Player player, RoomType type) {
        List<String> lore = new ArrayList<>();
        LothPlayer lothPlayer = Core.getPlayerController().get(player.getUniqueId());

        boolean enchant = false;

        lore.add("§7Torne-se um pescador e");
        lore.add("§7pesque seus inimigos.");
        lore.add("");
        if (type == RoomType.SOLO) {
            SkyStats stats = Platform.getSkyPlatform().getSkyPlayerController().get(DataType.SKY_WARS_SOLO, player.getUniqueId());
            if (stats.hasKit(RoomType.SOLO, this)) {
                if (stats.getKit().equals(getIdentify())) {
                    enchant = true;
                }
                lore.add((stats.getKit().equalsIgnoreCase(getIdentify()) ? "§aSelecionado." : "§eClique para selecionar."));
            } else {
                double percent = (lothPlayer.getGroup().getRank() == Rank.VIP ? 10 : lothPlayer.getGroup().getRank() == Rank.PRO ? 20 : lothPlayer.getGroup().getRank() == Rank.MASTER ? 30 : lothPlayer.getGroup().getRank() == Rank.LOTHUS ? 40 : lothPlayer.getGroup().getRank().ordinal() <= Rank.BETA.ordinal() ? 50 : 0);
                int price = percent(percent);
                lore.add("§eCusto: §a" + price + (price != getCoins() ? " §c(-" + (int)percent + "%)" : "") + " §acoins §eou §6" + getCash() + " cash§e.");
            }
        } else if (type == RoomType.DUPLAS) {
            SkyStats stats = Platform.getSkyPlatform().getSkyPlayerController().get(DataType.SKY_WARS_TEAM, player.getUniqueId());
            if (stats.hasKit(RoomType.DUPLAS, this)) {
                if (stats.getKit().equals(getIdentify())) {
                    enchant = true;
                }
                lore.add((stats.getKit().equalsIgnoreCase(getIdentify()) ? "§aSelecionado." : "§eClique para selecionar."));
            } else {
                double percent = (lothPlayer.getGroup().getRank() == Rank.VIP ? 10 : lothPlayer.getGroup().getRank() == Rank.PRO ? 20 : lothPlayer.getGroup().getRank() == Rank.MASTER ? 30 : lothPlayer.getGroup().getRank() == Rank.LOTHUS ? 40 : lothPlayer.getGroup().getRank().ordinal() <= Rank.BETA.ordinal() ? 50 : 0);
                int price = percent(percent);
                lore.add("§eCusto: §a" + price + (price != getCoins() ? " §c(-" + (int)percent + "%)" : "") + " §acoins §eou §6" + getCash() + " cash§e.");
            }
        }

        net.minecraft.server.v1_8_R3.ItemStack itemStack = CraftItemStack.asNMSCopy(new org.bukkit.inventory.ItemStack(Material.FISHING_ROD));

        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("kit", getIdentify());
        tag.setString("type", type.name());

        itemStack.setTag(tag);

        org.bukkit.inventory.ItemStack s = CraftItemStack.asBukkitCopy(itemStack);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName("§aFisherman");
        if (enchant) {
            meta.addEnchant(Enchantment.LURE, 1, true);
        }
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
        meta.setLore(lore);
        s.setItemMeta(meta);
        return s;
    }

    @Override
    public ItemStack getIconLoja(Player player, RoomType type) {
        List<String> lore = new ArrayList<>();

        boolean enchant = false;

        LothPlayer lothPlayer = Core.getPlayerController().get(player.getUniqueId());

        lore.add("§7Torne-se um pescador e");
        lore.add("§7pesque seus inimigos.");
        lore.add("");
        if (type == RoomType.SOLO) {
            SkyStats stats = Platform.getSkyPlatform().getSkyPlayerController().get(DataType.SKY_WARS_SOLO, player.getUniqueId());
            if (stats.hasKit(RoomType.SOLO, this)) {
                if (stats.getKit().equals(getIdentify())) {
                    enchant = true;
                }
                lore.add((stats.getKit().equalsIgnoreCase(getIdentify()) ? "§aSelecionado." : "§eClique para selecionar."));
            } else {
                double percent = (lothPlayer.getGroup().getRank() == Rank.VIP ? 10 : lothPlayer.getGroup().getRank() == Rank.PRO ? 20 : lothPlayer.getGroup().getRank() == Rank.MASTER ? 30 : lothPlayer.getGroup().getRank() == Rank.LOTHUS ? 40 : lothPlayer.getGroup().getRank().ordinal() <= Rank.BETA.ordinal() ? 50 : 0);
                int price = percent(percent);
                lore.add("§eCusto: §a" + price + (price != getCoins() ? " §c(-" + (int)percent + "%)" : "") + " §acoins §eou §6" + getCash() + " cash§e.");
            }
        } else if (type == RoomType.DUPLAS) {
            SkyStats stats = Platform.getSkyPlatform().getSkyPlayerController().get(DataType.SKY_WARS_TEAM, player.getUniqueId());
            if (stats.hasKit(RoomType.DUPLAS, this)) {
                if (stats.getKit().equals(getIdentify())) {
                    enchant = true;
                }
                lore.add((stats.getKit().equalsIgnoreCase(getIdentify()) ? "§aSelecionado." : "§eClique para selecionar."));
            } else {
                double percent = (lothPlayer.getGroup().getRank() == Rank.VIP ? 10 : lothPlayer.getGroup().getRank() == Rank.PRO ? 20 : lothPlayer.getGroup().getRank() == Rank.MASTER ? 30 : lothPlayer.getGroup().getRank() == Rank.LOTHUS ? 40 : lothPlayer.getGroup().getRank().ordinal() <= Rank.BETA.ordinal() ? 50 : 0);
                int price = percent(percent);
                lore.add("§eCusto: §a" + price + (price != getCoins() ? " §c(-" + (int)percent + "%)" : "") + " §acoins §eou §6" + getCash() + " cash§e.");
            }
        }


        net.minecraft.server.v1_8_R3.ItemStack itemStack = CraftItemStack.asNMSCopy(new org.bukkit.inventory.ItemStack(Material.FISHING_ROD));

        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("kit", getIdentify());
        tag.setString("type", type.name());

        itemStack.setTag(tag);

        org.bukkit.inventory.ItemStack s = CraftItemStack.asBukkitCopy(itemStack);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName("§aFisherman");
        if (enchant) {
            meta.addEnchant(Enchantment.LURE, 1, true);
        }
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
        meta.setLore(lore);
        s.setItemMeta(meta);
        return s;
    }

    @EventHandler
    public void fisherMan(PlayerFishEvent e) {
        if ((e.getPlayer() != null) && (e.getCaught() instanceof Player)) {
            Player p = e.getPlayer();

            if (p.getItemInHand().getType() != Material.FISHING_ROD)return;

            net.minecraft.server.v1_8_R3.ItemStack i = CraftItemStack.asNMSCopy(p.getItemInHand());

            if (!i.hasTag())return;
            if (!i.getTag().getString("owner").equals(p.getUniqueId().toString()))return;

            if (cooldown.get(p.getUniqueId()) != null && cooldown.get(p.getUniqueId()) > System.currentTimeMillis()) {
                p.sendMessage("§cVocê deve aguardar para utilizar novamente.");
                return;
            }

            cooldown.put(p.getUniqueId(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(60));

            Player d = (Player) e.getCaught();
            d.teleport(p.getLocation());
        }
    }
}
