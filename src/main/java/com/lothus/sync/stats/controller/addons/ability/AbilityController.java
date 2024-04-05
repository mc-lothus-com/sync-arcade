package com.lothus.sync.stats.controller.addons.ability;

import com.lothus.sync.stats.games.addons.ability.Ability;

import java.util.ArrayList;
import java.util.List;

public class AbilityController {

    private List<Ability> kits = new ArrayList<>();

    public void load(Ability k) {
        kits.add(k);
    }

    public Ability getKit(String identify) {
        for (Ability k : kits) {
            if (k.getIdentify().equalsIgnoreCase(identify)) {
                return k;
            }
        }
        return null;
    }

    public List<Ability> getAbility() {
        return kits;
    }

}
