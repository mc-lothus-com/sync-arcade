package com.lothus.sync.stats.controller.bedwars.menus;

import com.lothus.core.api.menu.AbstractMenu;
import com.lothus.sync.stats.menus.games.bedwars.BedShopMenu;
import com.lothus.sync.stats.menus.games.bedwars.deathcries.DeathMenu;
import com.lothus.sync.stats.menus.games.bedwars.slaughter.SlaughterMenu;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class BedWarsMenus {

    private AbstractMenu shopMenu;

    private AbstractMenu slaughterMenu;
    private AbstractMenu deathMenu;

    public BedWarsMenus() {
        shopMenu = new BedShopMenu();

        slaughterMenu = new SlaughterMenu();
        deathMenu = new DeathMenu();
    }
}
