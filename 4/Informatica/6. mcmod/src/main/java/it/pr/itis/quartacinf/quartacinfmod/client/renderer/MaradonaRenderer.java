package it.pr.itis.quartacinf.quartacinfmod.client.renderer;

import it.pr.itis.quartacinf.quartacinfmod.QuartaCInfMod;
import it.pr.itis.quartacinf.quartacinfmod.client.renderer.model.MaradonaModel;
import it.pr.itis.quartacinf.quartacinfmod.common.entity.Maradona;
import net.minecraft.client.renderer.entity.EntityRendererProvider.Context;
import net.minecraft.client.renderer.entity.MobRenderer;
import net.minecraft.resources.ResourceLocation;

public class MaradonaRenderer<Type extends Maradona> extends MobRenderer<Type, MaradonaModel<Type>> {

    private static final ResourceLocation TEXTURE = new ResourceLocation(QuartaCInfMod.MOD_ID,
            "textures/entities/maradona.png");

    public MaradonaRenderer(Context context) {
        super(context, new MaradonaModel<>(context.bakeLayer(MaradonaModel.LAYER_LOCATION)), 0.5f);
    }

    @Override
    public ResourceLocation getTextureLocation(Type entity){
        return TEXTURE;
    }
}
