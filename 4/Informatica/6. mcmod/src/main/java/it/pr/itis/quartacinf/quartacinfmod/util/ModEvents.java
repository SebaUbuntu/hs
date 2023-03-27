package it.pr.itis.quartacinf.quartacinfmod.util;

import it.pr.itis.quartacinf.quartacinfmod.QuartaCInfMod;
import it.pr.itis.quartacinf.quartacinfmod.common.entity.Maradona;
import it.pr.itis.quartacinf.quartacinfmod.register.Entity;
import net.minecraftforge.event.entity.EntityAttributeCreationEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = QuartaCInfMod.MOD_ID, bus = Bus.MOD)
public class ModEvents {

    @SubscribeEvent
    public static void registerAttribute(EntityAttributeCreationEvent event){
        event.put(Entity.MARADONA.get(), Maradona.createAttributes().build());
    }
}
