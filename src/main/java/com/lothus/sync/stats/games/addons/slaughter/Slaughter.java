package com.lothus.sync.stats.games.addons.slaughter;

import com.lothus.core.games.type.GameType;
import com.lothus.core.utils.bukkit.ItemCreator;
import com.lothus.sync.stats.Sync;
import com.lothus.sync.stats.platform.Platform;
import com.lothus.sync.stats.player.games.bedwars.BedPlayer;
import com.lothus.sync.stats.player.games.skywars.SkyPlayer;
import lombok.AllArgsConstructor;
import lombok.Getter;
import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

@Getter
@AllArgsConstructor
public abstract class Slaughter {

    private String identify;
    private String message;

    private String permission;

    public String message(String dead, String killer) {
        return message.replace("{dead}", dead).replace("{killer}", killer);
    }

    public ItemStack icon(Player player) {
        if (Sync.getType() == GameType.SKY_WARS) {
            SkyPlayer skyPlayer = Platform.getSkyPlatform().getSkyPlayerController().getAccount(player.getUniqueId());
            if (skyPlayer.getSlaughter() == identify) {
                return new ItemCreator(Material.INK_SACK, "§a" + identify)
                        .setLore(
                                !skyPlayer.hasSlaughter(this) ?
                                        "§eCusto: §a500 coins §eou §6250 cash§e." :
                                        (skyPlayer.getSlaughter().equals(identify) ? "§aSelecionado." : "§eClique para selecionar")
                        ).setId((!skyPlayer.hasSlaughter(this) ? 1 :
                                skyPlayer.getSlaughter().equals(identify) ? 5 : 10)).addEnchant(Enchantment.LURE, 1).build();
            } else {
                return new ItemCreator(Material.INK_SACK, "§a" + identify)
                        .setLore(
                                !skyPlayer.hasSlaughter(this) ?
                                        "§eCusto: §a500 coins §eou §6250 cash§e." :
                                        (skyPlayer.getSlaughter().equals(identify) ? "§aSelecionado." : "§eClique para selecionar")
                        ).setId((!skyPlayer.hasSlaughter(this) ? 1 :
                                skyPlayer.getSlaughter().equals(identify) ? 5 : 10)).build();
            }
        } else if (Sync.getType() == GameType.BED_WARS) {
            BedPlayer skyPlayer = Platform.getBedPlatform().getBedPlayerController().getAccount(player.getUniqueId());
            if (skyPlayer.getSlaughter() == identify) {
                return new ItemCreator(Material.INK_SACK, "§a" + identify)
                        .setLore(
                                !skyPlayer.hasSlaughter(this) ?
                                        "§eCusto: §a500 coins §eou §6250 cash§e." :
                                        (skyPlayer.getSlaughter().equals(identify) ? "§aSelecionado." : "§eClique para selecionar")
                        ).setId((!skyPlayer.hasSlaughter(this) ? 1 :
                                skyPlayer.getSlaughter().equals(identify) ? 5 : 10)).addEnchant(Enchantment.LURE, 1).build();
            } else {
                return new ItemCreator(Material.INK_SACK, "§a" + identify)
                        .setLore(
                                !skyPlayer.hasSlaughter(this) ?
                                        "§eCusto: §a500 coins §eou §6250 cash§e." :
                                        (skyPlayer.getSlaughter().equals(identify) ? "§aSelecionado." : "§eClique para selecionar")
                        ).setId((!skyPlayer.hasSlaughter(this) ? 1 :
                                skyPlayer.getSlaughter().equals(identify) ? 5 : 10)).build();
            }
        }
        return null;
    }
}
