package com.lothus.sync.stats.controller.addons.slaughter;

import com.lothus.sync.stats.games.addons.slaughter.Slaughter;

import java.util.ArrayList;
import java.util.List;

public class SlaughterController {

    private List<Slaughter> kits = new ArrayList<>();

    public void load(Slaughter k) {
        kits.add(k);
    }

    public Slaughter getKit(String identify) {
        for (Slaughter k : kits) {
            if (k.getIdentify().equalsIgnoreCase(identify)) {
                return k;
            }
        }
        return null;
    }

    public List<Slaughter> getDeaths() {
        return kits;
    }



}
