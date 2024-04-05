package com.lothus.sync.stats.games.addons.ability.register;

import com.lothus.core.games.room.RoomType;
import com.lothus.sync.stats.data.type.DataType;
import com.lothus.sync.stats.games.addons.ability.Ability;
import com.lothus.sync.stats.platform.Platform;
import com.lothus.sync.stats.player.games.skywars.stats.SkyStats;
import lombok.AllArgsConstructor;
import lombok.Getter;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.event.EventHandler;
import org.bukkit.event.HandlerList;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LuckyClover extends Ability {

    public LuckyClover() {
        super(
                "LuckyClover",
                "ability.solo.luckyclover",
                "ability.team.luckyclover",
                5000,
                2500
        );
    }

    @EventHandler
    public void onBlockBreak(BlockBreakEvent event) {
        Player player = event.getPlayer();

        Random random = new Random();

        if (random.nextInt(101) > 90) {
            Bukkit.getPluginManager().callEvent(new LuckyCloverReceiveEvent(player));
        }
    }

    @Override
    public ItemStack icon(Player player, RoomType type) {
        List<String> lore = new ArrayList<>();

        boolean enchant = false;

        lore.add("§7Tenha 10% de chance de receber");
        lore.add("§7uma maça ao quebrar blocos!");
        lore.add("");

        if (type == RoomType.SOLO) {
            SkyStats stats = Platform.getSkyPlatform().getSkyPlayerController().get(DataType.SKY_WARS_SOLO, player.getUniqueId());
            if (stats.hasAbility(RoomType.SOLO, this)) {
                if (stats.getAbility().equals(getIdentify())) {
                    enchant = true;
                }
                lore.add((stats.getAbility().equalsIgnoreCase(getIdentify()) ? "§aSelecionado." : "§eClique para selecionar."));
            } else {
                lore.add("§eCusto: §a" + getCoins() + " coins §eou §6" + getCash() + " cash§e.");
            }
        } else if (type == RoomType.DUPLAS) {
            SkyStats stats = Platform.getSkyPlatform().getSkyPlayerController().get(DataType.SKY_WARS_TEAM, player.getUniqueId());
            if (stats.hasAbility(RoomType.DUPLAS, this)) {
                if (stats.getAbility().equals(getIdentify())) {
                    enchant = true;
                }
                lore.add((stats.getAbility().equalsIgnoreCase(getIdentify()) ? "§aSelecionado." : "§eClique para selecionar."));
            } else {
                lore.add("§eCusto: §a" + getCoins() + " coins §eou §6" + getCash() + " cash§e.");
            }
        }

        net.minecraft.server.v1_8_R3.ItemStack itemStack = CraftItemStack.asNMSCopy(new org.bukkit.inventory.ItemStack(Material.WATER_LILY));

        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("ability", getIdentify());
        tag.setString("type", type.name());

        itemStack.setTag(tag);

        org.bukkit.inventory.ItemStack s = CraftItemStack.asBukkitCopy(itemStack);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName("§aTrevo da Sorte");
        if (enchant) {
            meta.addEnchant(Enchantment.LURE, 1, true);
        }
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
        meta.setLore(lore);
        s.setItemMeta(meta);
        return s;
    }

    @Getter
    @AllArgsConstructor
    public static class LuckyCloverReceiveEvent extends Event {

        private static final HandlerList HANDLERS = new HandlerList();

        Player player;

        public static HandlerList getHandlerList() {
            return HANDLERS;
        }

        @Override
        public HandlerList getHandlers() {
            return HANDLERS;
        }
    }
}
