package com.lothus.sync.stats.controller.addons.kit;


import com.lothus.sync.stats.games.addons.kit.Kit;

import java.util.ArrayList;
import java.util.List;

public class KitController {

    private List<Kit> kits = new ArrayList<>();

    public void load(Kit k) {
        kits.add(k);
    }

    public Kit getKit(String identify) {
        for (Kit k : kits) {
            if (k.getIdentify().equalsIgnoreCase(identify)) {
                return k;
            }
        }
        return null;
    }

    public List<Kit> getKits() {
        return kits;
    }



}
