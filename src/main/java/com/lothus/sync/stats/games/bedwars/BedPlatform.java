package com.lothus.sync.stats.games.bedwars;

import com.lothus.sync.stats.controller.bedwars.player.BedPlayerController;
import lombok.Getter;

@Getter
public class BedPlatform {

    private final BedPlayerController bedPlayerController = new BedPlayerController();

}
