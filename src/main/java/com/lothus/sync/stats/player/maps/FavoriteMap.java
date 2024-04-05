package com.lothus.sync.stats.player.maps;

import com.lothus.core.games.room.RoomType;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class FavoriteMap {

    private String display;
    private RoomType type;

    public FavoriteMap(String display, RoomType type) {
        this.display = display;
        this.type = type;
    }
}
