package com.lothus.sync.stats.games.addons.ability;

import com.lothus.core.games.room.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.bukkit.entity.Player;
import org.bukkit.event.Listener;
import org.bukkit.inventory.ItemStack;

@Getter @Setter
@AllArgsConstructor
public abstract class Ability implements Listener {
    
    private String identify;

    private String soloPermission;
    private String teamPermission;

    private int coins;
    private int cash;


    public abstract ItemStack icon(Player player, RoomType type);

}
