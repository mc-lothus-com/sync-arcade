package com.lothus.sync.stats.menus.controller;

import com.lothus.sync.stats.controller.bedwars.menus.BedWarsMenus;
import com.lothus.sync.stats.controller.skywars.menus.SkyWarsMenus;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class MenuController {

    private SkyWarsMenus skyWarsMenus;
    private BedWarsMenus bedWarsMenus;

}
