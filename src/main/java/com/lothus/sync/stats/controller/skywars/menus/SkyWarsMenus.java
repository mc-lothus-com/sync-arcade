package com.lothus.sync.stats.controller.skywars.menus;

import com.lothus.core.api.menu.AbstractMenu;
import com.lothus.sync.stats.menus.games.skywars.SkyShopMenu;
import com.lothus.sync.stats.menus.games.skywars.ability.solo.AbilitySoloMenu;
import com.lothus.sync.stats.menus.games.skywars.ability.team.AbilityTeamMenu;
import com.lothus.sync.stats.menus.games.skywars.deathcries.DeathMenu;
import com.lothus.sync.stats.menus.games.skywars.kits.solo.KitsSoloMenu;
import com.lothus.sync.stats.menus.games.skywars.kits.team.KitsTeamMenu;
import com.lothus.sync.stats.menus.games.skywars.slaughter.SlaughterMenu;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class SkyWarsMenus {

    private AbstractMenu shopMenu;

    private AbstractMenu kitsSoloMenu;
    private AbstractMenu kitsTeamMenu;

    private AbstractMenu abilitySoloMenu;
    private AbstractMenu abilityTeamMenu;

    private AbstractMenu slaughterMenu;

    private AbstractMenu deathMenu;

    public SkyWarsMenus() {
        shopMenu = new SkyShopMenu();

        kitsSoloMenu = new KitsSoloMenu();
        kitsTeamMenu = new KitsTeamMenu();

        abilitySoloMenu = new AbilitySoloMenu();
        abilityTeamMenu = new AbilityTeamMenu();

        slaughterMenu = new SlaughterMenu();
        deathMenu = new DeathMenu();
    }
}
