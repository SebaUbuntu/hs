package it.pr.itis.quartacinf.quartacinfmod.client.event;

import it.pr.itis.quartacinf.quartacinfmod.QuartaCInfMod;
import it.pr.itis.quartacinf.quartacinfmod.client.renderer.MaradonaRenderer;
import it.pr.itis.quartacinf.quartacinfmod.client.renderer.model.MaradonaModel;
import it.pr.itis.quartacinf.quartacinfmod.register.Entity;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventBusSubscriber.Bus;

@Mod.EventBusSubscriber(modid = QuartaCInfMod.MOD_ID, bus = Bus.MOD, value = Dist.CLIENT)
public class ClientModEvents {

    public ClientModEvents(){}

    @SubscribeEvent
    public static void registerlayers(EntityRenderersEvent.RegisterLayerDefinitions event) {
        event.registerLayerDefinition(MaradonaModel.LAYER_LOCATION, MaradonaModel::createBodyLayer);
    }

    @SubscribeEvent
    public static void registerRenderers(EntityRenderersEvent.RegisterRenderers event) {
        event.registerEntityRenderer(Entity.MARADONA.get(), MaradonaRenderer:: new);
    }


}
