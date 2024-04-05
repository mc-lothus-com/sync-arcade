package com.lothus.sync.stats.games.addons.deathcries;

import com.lothus.core.games.type.GameType;
import com.lothus.core.utils.bukkit.ItemCreator;
import com.lothus.sync.stats.Sync;
import com.lothus.sync.stats.platform.Platform;
import com.lothus.sync.stats.player.games.bedwars.BedPlayer;
import com.lothus.sync.stats.player.games.skywars.SkyPlayer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter @Setter
@AllArgsConstructor
public abstract class DeathCry {

    private String identify;
    private String permission;
    private Sound deathcry;

    private String texture;

    public void playSound(Player killer) {
        killer.playSound(killer.getLocation(), deathcry, 5.0f, 5.0f);
    }
    public ItemStack icon(Player player) {
        if (Sync.getType() == GameType.SKY_WARS) {
            SkyPlayer skyPlayer = Platform.getSkyPlatform().getSkyPlayerController().getAccount(player.getUniqueId());
            if (skyPlayer.getDeathCry() == identify) {
                return new ItemCreator(Material.SKULL_ITEM, "§a" + identify).setLore(
                        (
                                !skyPlayer.hasDeathCry(this) ?
                                        "§eCusto: §a500 coins §eou §6250 cash§e." :
                                        (skyPlayer.getDeathCry().equals(identify) ? "§aSelecionado." : "§eClique para selecionar")
                        )
                ).withSkullURL(texture).addEnchant(Enchantment.LURE, 1).setAmount(1).build();
            } else {
                return new ItemCreator(Material.SKULL_ITEM, "§a" + identify).setLore(
                        (
                                !skyPlayer.hasDeathCry(this) ?
                                        "§eCusto: §a500 coins §eou §6250 cash§e." :
                                        (skyPlayer.getDeathCry().equals(identify) ? "§aSelecionado." : "§eClique para selecionar")
                        )
                ).withSkullURL(texture).setAmount(1).build();
            }
        } else if (Sync.getType() == GameType.BED_WARS) {
            BedPlayer skyPlayer = Platform.getBedPlatform().getBedPlayerController().getAccount(player.getUniqueId());
            if (skyPlayer.getDeathCry() == identify) {
                return new ItemCreator(Material.SKULL_ITEM, "§a" + identify).setLore(
                        (
                                !skyPlayer.hasDeathCry(this) ?
                                        "§eCusto: §a500 coins §eou §6250 cash§e." :
                                        (skyPlayer.getDeathCry().equals(identify) ? "§aSelecionado." : "§eClique para selecionar")
                        )
                ).withSkullURL(texture).addEnchant(Enchantment.LURE, 1).setAmount(1).build();
            } else {
                return new ItemCreator(Material.SKULL_ITEM, "§a" + identify).setLore(
                        (
                                !skyPlayer.hasDeathCry(this) ?
                                        "§eCusto: §a500 coins §eou §6250 cash§e." :
                                        (skyPlayer.getDeathCry().equals(identify) ? "§aSelecionado." : "§eClique para selecionar")
                        )
                ).withSkullURL(texture).setAmount(1).build();
            }
        }
        return null;
    }
}
