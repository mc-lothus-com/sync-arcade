package com.lothus.sync.stats.games.addons.kit;

import com.lothus.core.games.room.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

@Getter @Setter
@AllArgsConstructor
public abstract class Kit implements Listener {

    private String identify;

    private String soloPermission;
    private String teamPermission;

    private int coins;
    private int cash;

    public abstract void apply(Player player);

    public abstract ItemStack getIcon(Player player, RoomType type);
    public abstract ItemStack getIconLoja(Player player, RoomType type);

    public Integer percent(double percent) {
        if (percent == 0) {
            return getCoins();
        }

        double percentValue = (getCoins() * percent) / 100;
        double value = getCoins() - percentValue;
        return (int) value;
    }
}
