package com.lothus.sync.stats.games.addons.slaughter.loader;

import com.lothus.core.utils.bukkit.classes.ClassGetter;
import com.lothus.sync.stats.games.addons.slaughter.Slaughter;
import com.lothus.sync.stats.platform.Platform;
import org.bukkit.plugin.java.JavaPlugin;

public class SlaughterLoader {

    public static void loadSlaughter(JavaPlugin plugin, String path) {
        for (Class<?> death : ClassGetter.getClassesForPackage(plugin, path)) {
            if (Slaughter.class.isAssignableFrom(death)) {
                try {
                    Slaughter k = (Slaughter) death.newInstance();
                    Platform.getSlaughterController().load(k);
                } catch (Exception e) {

                }
            }
        }
    }
}
