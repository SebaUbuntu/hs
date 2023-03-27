package it.pr.itis.quartacinf.quartacinfmod.world.feature;

import net.minecraft.core.Holder;
import net.minecraft.data.worldgen.placement.PlacementUtils;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;

// This class is used to put veins in a specific level of the world (Y - axes) -> // VerticalAnchor.aboveBottom(level);
public class ModPlacedFeatures
{
    public static final Holder<PlacedFeature> NAPOLETANITE_ORE_PLACED = PlacementUtils.register("napoletanite_ore_placed",
            ModConfiguredFeatures.NAPOLETANITE_ORE, ModOrePlacement.commonOrePlacement(37, //How many veins are in a chunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(80))));

    public static final Holder<PlacedFeature> COAL_COKE_ORE_PLACED = PlacementUtils.register("coal_coke_ore_placed",
            ModConfiguredFeatures.NAPOLETANITE_ORE, ModOrePlacement.commonOrePlacement(60, //How many veins are in a chunk
                    HeightRangePlacement.triangle(VerticalAnchor.aboveBottom(-80), VerticalAnchor.aboveBottom(30))));
}
