package it.pr.itis.quartacinf.quartacinfmod.world;

import it.pr.itis.quartacinf.quartacinfmod.QuartaCInfMod;
import it.pr.itis.quartacinf.quartacinfmod.world.gen.ModOreGeneration;
import net.minecraftforge.event.world.BiomeLoadingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = QuartaCInfMod.MOD_ID)
public class ModWorldEvents {
    @SubscribeEvent
    // This is used to load the events of biomes (so for example, in this case, the ore generation)
    public static void biomeLoadingEvent(final BiomeLoadingEvent event){
        ModOreGeneration.generateOres(event);
    }
}
