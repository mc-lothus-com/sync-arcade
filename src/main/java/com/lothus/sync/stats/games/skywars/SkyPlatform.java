package com.lothus.sync.stats.games.skywars;

import com.lothus.sync.stats.controller.addons.ability.AbilityController;
import com.lothus.sync.stats.controller.addons.kit.KitController;
import com.lothus.sync.stats.controller.skywars.player.SkyPlayerController;
import lombok.Getter;


@Getter
public class SkyPlatform {

    private KitController kitController = new KitController();
    private AbilityController abilityController = new AbilityController();
    private SkyPlayerController skyPlayerController = new SkyPlayerController();
}
