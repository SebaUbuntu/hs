package it.pr.itis.quartacinf.quartacinfmod.world.feature;

import net.minecraft.world.level.levelgen.placement.*;

import java.util.List;

// took the methods from here: net/minecraft/data/worldgen/placement/OrePlacements.java
// These are the methods that are used to place ore in a specific place
public class ModOrePlacement {

    private static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    private static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }
}
