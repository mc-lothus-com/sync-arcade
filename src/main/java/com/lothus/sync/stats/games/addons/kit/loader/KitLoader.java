package com.lothus.sync.stats.games.addons.kit.loader;

import com.lothus.core.utils.bukkit.classes.ClassGetter;
import com.lothus.sync.stats.games.addons.kit.Kit;
import com.lothus.sync.stats.platform.Platform;
import org.bukkit.plugin.java.JavaPlugin;

public class KitLoader {

    public static void loadKit(JavaPlugin plugin, String path) {
        for (Class<?> kit : ClassGetter.getClassesForPackage(plugin, path)) {
            if (Kit.class.isAssignableFrom(kit)) {
                try {
                    Kit k = (Kit) kit.newInstance();
                    Platform.getSkyPlatform().getKitController().load(k);
                    plugin.getServer().getPluginManager().registerEvents(k, plugin);
                } catch (Exception e) {

                }
            }
        }
    }
}
