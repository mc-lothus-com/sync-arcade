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

public class Builder extends Kit {

    public Builder() {
        super(
                "Construtor",
                "kit.skywars.solo.builder",
                "kit.skywars.team.builder",
                5000,
                2500
        );
    }

    @Override
    public void apply(Player player) {
        player.getInventory().addItem(new ItemCreator(Material.IRON_AXE).addEnchant(Enchantment.DIG_SPEED, 3).build());
        player.getInventory().addItem(new ItemStack(Material.LOG, 64));
    }

    @Override
    public ItemStack getIcon(Player player, RoomType type) {
        List<String> lore = new ArrayList<>();
        LothPlayer lothPlayer = Core.getPlayerController().get(player.getUniqueId());

        boolean enchant = false;

        lore.add("§7Tenha um machado encantado com eficiência");
        lore.add("§7e carregue 64 blocos de madeira por aí.");
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


        net.minecraft.server.v1_8_R3.ItemStack itemStack = CraftItemStack.asNMSCopy(new org.bukkit.inventory.ItemStack(Material.LOG));

        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("kit", getIdentify());
        tag.setString("type", type.name());

        itemStack.setTag(tag);

        org.bukkit.inventory.ItemStack s = CraftItemStack.asBukkitCopy(itemStack);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName("§aConstrutor");
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
        return getIcon(player, type);
    }
}
