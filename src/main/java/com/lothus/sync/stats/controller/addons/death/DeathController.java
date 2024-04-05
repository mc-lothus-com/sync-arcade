package com.lothus.sync.stats.controller.addons.death;


import com.lothus.sync.stats.games.addons.deathcries.DeathCry;

import java.util.ArrayList;
import java.util.List;

public class DeathController {

    private List<DeathCry> kits = new ArrayList<>();

    public void load(DeathCry k) {
        kits.add(k);
    }

    public DeathCry getKit(String identify) {
        for (DeathCry k : kits) {
            if (k.getIdentify().equalsIgnoreCase(identify)) {
                return k;
            }
        }
        return null;
    }

    public List<DeathCry> getDeaths() {
        return kits;
    }

}
