package com.lothus.sync.stats.platform;

import com.lothus.sync.stats.controller.addons.death.DeathController;
import com.lothus.sync.stats.controller.addons.slaughter.SlaughterController;
import com.lothus.sync.stats.data.player.DataPlayer;
import com.lothus.sync.stats.data.stats.DataStats;
import com.lothus.sync.stats.data.type.DataType;
import com.lothus.sync.stats.games.bedwars.BedPlatform;
import com.lothus.sync.stats.games.skywars.SkyPlatform;
import com.lothus.sync.stats.menus.controller.MenuController;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class Platform {

    @Getter @Setter
    private static DataStats dataStats;

    @Getter @Setter
    private static DataPlayer dataPlayer;

    /**
     * PLATAFORMA DOS MINGAMES (CONTROLLERS, KITS, HABILIDADES ETC)
     * */

    @Getter
    private static SkyPlatform skyPlatform = new SkyPlatform();

    @Getter
    private static BedPlatform bedPlatform = new BedPlatform();


    @Getter
    private static DeathController deathController = new DeathController();

    @Getter
    private static MenuController menuController = new MenuController();

    @Getter
    private static SlaughterController slaughterController = new SlaughterController();


    public static void deleteStats(UUID uniqueId) {
        Platform.getDataPlayer().delete(DataType.BED_WARS_ACCOUNT, uniqueId);
        Platform.getDataStats().delete(DataType.BED_WARS_SOLO, uniqueId);
        Platform.getDataStats().delete(DataType.BED_WARS_TEAM, uniqueId);
        Platform.getDataStats().delete(DataType.BED_WARS_TRIO, uniqueId);
        Platform.getDataStats().delete(DataType.BED_WARS_QUARTETO, uniqueId);
        Platform.getDataPlayer().delete(DataType.SKY_WARS_ACCOUNT, uniqueId);
        Platform.getDataStats().delete(DataType.SKY_WARS_SOLO, uniqueId);
        Platform.getDataStats().delete(DataType.SKY_WARS_TEAM, uniqueId);
        Platform.getDataStats().delete(DataType.SKY_WARS_RANKED, uniqueId);
    }
}
