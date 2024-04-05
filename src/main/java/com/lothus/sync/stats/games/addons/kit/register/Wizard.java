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
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;

public class Wizard extends Kit {

    public Wizard() {
        super(
                "Mago",
                "kit.skywars.solo.wizard",
                "kit.skywars.team.wizard",
                5000,
                2500
        );
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemStack(Material.ENCHANTMENT_TABLE, 1));
        player.getInventory().addItem(new ItemStack(Material.BOOKSHELF, 16));
        player.getInventory().addItem(new ItemStack(Material.EXP_BOTTLE, 32));
    }

    @Override
    public ItemStack getIcon(Player player, RoomType type) {
        List<String> lore = new ArrayList<>();
        LothPlayer lothPlayer = Core.getPlayerController().get(player.getUniqueId());

        boolean enchant = false;

        lore.add("§7Encante o que estiver ao");
        lore.add("§7seu dispor com sua magia");
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


        net.minecraft.server.v1_8_R3.ItemStack itemStack = CraftItemStack.asNMSCopy(new org.bukkit.inventory.ItemStack(Material.ENCHANTMENT_TABLE));

        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("kit", getIdentify());
        tag.setString("type", type.name());

        itemStack.setTag(tag);

        org.bukkit.inventory.ItemStack s = CraftItemStack.asBukkitCopy(itemStack);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName("§aMago");
        if (enchant) {
            meta.addEnchant(Enchantment.LURE, 1 , false);
        }
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
        meta.setLore(lore);
        s.setItemMeta(meta);
        return s;
    }

    @Override
    public ItemStack getIconLoja(Player player, RoomType type) {
        return getIcon(player, type);
    }
}