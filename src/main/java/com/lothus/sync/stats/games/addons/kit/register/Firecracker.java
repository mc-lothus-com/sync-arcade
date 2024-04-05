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
import net.minecraft.server.v1_8_R3.ItemStack;
import net.minecraft.server.v1_8_R3.NBTTagCompound;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.craftbukkit.v1_8_R3.inventory.CraftItemStack;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityExplodeEvent;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.meta.FireworkMeta;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

public class Firecracker extends Kit {

    private HashMap<UUID, Long> cooldown = new HashMap<>();
    private HashMap<UUID, Boolean> damage = new HashMap<>();

    public Firecracker() {
        super(
                "Firecracker",
                "kit.skywars.solo.firecracker",
                "kit.skywars.team.firecracker",
                10000,
                5000
        );
    }

    @Override
    public void apply(Player player) {
        ItemStack nms = CraftItemStack.asNMSCopy(new org.bukkit.inventory.ItemStack(Material.FIREWORK));
        NBTTagCompound tag = new NBTTagCompound();

        tag.setString("owner", player.getUniqueId().toString());

        nms.setTag(tag);

        org.bukkit.inventory.ItemStack item = CraftItemStack.asBukkitCopy(nms);
        ItemMeta meta = item.getItemMeta();
        meta.setDisplayName("§aFirecracker");
        item.setItemMeta(meta);

        player.getInventory().addItem(item);
    }

    @Override
    public org.bukkit.inventory.ItemStack getIcon(Player player, RoomType type) {
        List<String> lore = new ArrayList<>();
        LothPlayer lothPlayer = Core.getPlayerController().get(player.getUniqueId());

        boolean enchant = false;

        lore.add("§7Ao cair no vazio, salve-se com um foguete!");
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


        ItemStack itemStack = CraftItemStack.asNMSCopy(new org.bukkit.inventory.ItemStack(Material.FIREWORK));

        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("kit", getIdentify());
        tag.setString("type", type.name());

        itemStack.setTag(tag);

        org.bukkit.inventory.ItemStack s = CraftItemStack.asBukkitCopy(itemStack);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName("§aFirecracker");
        if (enchant) {
            meta.addEnchant(Enchantment.LURE, 1, true);
        }
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
        meta.setLore(lore);
        s.setItemMeta(meta);
        return s;
    }

    @Override
    public org.bukkit.inventory.ItemStack getIconLoja(Player player, RoomType type) {
        List<String> lore = new ArrayList<>();
        LothPlayer lothPlayer = Core.getPlayerController().get(player.getUniqueId());

        boolean enchant = false;

        lore.add("§7Ao cair no vazio, salve-se com um foguete!");
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


        ItemStack itemStack = CraftItemStack.asNMSCopy(new org.bukkit.inventory.ItemStack(Material.FIREWORK));

        NBTTagCompound tag = new NBTTagCompound();
        tag.setString("kit", getIdentify());
        tag.setString("type", type.name());

        itemStack.setTag(tag);

        org.bukkit.inventory.ItemStack s = CraftItemStack.asBukkitCopy(itemStack);
        ItemMeta meta = s.getItemMeta();
        meta.setDisplayName("§aFirecracker");
        if (enchant) {
            meta.addEnchant(Enchantment.LURE, 1, true);
        }
        meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_DESTROYS, ItemFlag.HIDE_POTION_EFFECTS, ItemFlag.HIDE_ENCHANTS, ItemFlag.HIDE_UNBREAKABLE);
        meta.setLore(lore);
        s.setItemMeta(meta);
        return s;
    }


    @EventHandler
    public void onInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        org.bukkit.inventory.ItemStack item = event.getItem();

        if (item == null)return;
        if (item.getType() == Material.AIR)return;

        ItemStack nms = CraftItemStack.asNMSCopy(item);

        if (!nms.hasTag())return;

        if (item.getType() != Material.FIREWORK)return;

        event.setCancelled(true);

        if (!nms.getTag().getString("owner").equals(player.getUniqueId().toString())) {
            return;
        }


        if (event.getAction().equals(Action.RIGHT_CLICK_AIR) || event.getAction() == Action.RIGHT_CLICK_BLOCK) {
            if (cooldown.get(player.getUniqueId()) != null && cooldown.get(player.getUniqueId()) > System.currentTimeMillis()) {
                player.sendMessage("§cVocê deve aguardar para usar novamente.");
                return;
            }

            Location location = player.getLocation().clone();

            if (location.getBlock().getType() == Material.WATER || location.getBlock().getType() == Material.STATIONARY_WATER || location.getBlock().getType() == Material.LAVA || location.getBlock().getType() == Material.STATIONARY_LAVA) {
                return;
            }

            cooldown.put(player.getUniqueId(), System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(15));

            Firework f = (Firework) player.getLocation().getWorld().spawnEntity(player.getLocation(), EntityType.FIREWORK);
            FireworkMeta meta = f.getFireworkMeta();

            meta.setPower(1);
            f.setPassenger(player);
            f.setFireworkMeta(meta);
            damage.put(player.getUniqueId(), false);
        }
    }

    @EventHandler
    public void onDamage(EntityDamageEvent event) {
        if (damage.containsKey(event.getEntity().getUniqueId())) {
            if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                event.getEntity().setFallDistance(0);
                event.setDamage(0D);
                event.setCancelled(true);
                damage.remove(event.getEntity().getUniqueId());
            }
        }
    }

    @EventHandler
    public void onDamage(EntityDamageByEntityEvent event) {
        if (damage.containsKey(event.getEntity().getUniqueId())) {
            if (event.getCause().equals(EntityDamageEvent.DamageCause.FALL)) {
                event.getEntity().setFallDistance(0);
                event.setDamage(0D);
                event.setCancelled(true);
                damage.remove(event.getEntity().getUniqueId());
            }
        }
    }


    @EventHandler
    public void move(PlayerMoveEvent move) {
        if (damage.containsKey(move.getPlayer().getUniqueId())) {
            move.getPlayer().setFallDistance(0);
        }
    }

    @EventHandler
    public void onEntityExplode(EntityExplodeEvent event) {
        Entity e = event.getEntity();
        if (e instanceof Firework) {
            if (e.getPassenger() != null) {
                event.setCancelled(true);
                return;
            }
        }
    }
}
