package it.pr.itis.quartacinf.quartacinfmod.world.gen;

import it.pr.itis.quartacinf.quartacinfmod.world.feature.ModPlacedFeatures;
import net.minecraft.core.Holder;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.event.world.BiomeLoadingEvent;

import java.util.List;

public class ModOreGeneration {
    //made napoletanite_ores spawn in every biome, 2 veins per chunk
    public static void generateOres(final BiomeLoadingEvent event){
        List<Holder<PlacedFeature>> base =
                event.getGeneration().getFeatures(GenerationStep.Decoration.UNDERGROUND_ORES);

        base.add(ModPlacedFeatures.NAPOLETANITE_ORE_PLACED);
        base.add(ModPlacedFeatures.COAL_COKE_ORE_PLACED);
    }
}
