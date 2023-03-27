package it.pr.itis.quartacinf.quartacinfmod.register;

import it.pr.itis.quartacinf.quartacinfmod.QuartaCInfMod;
import it.pr.itis.quartacinf.quartacinfmod.common.entity.Maradona;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class Entity {

    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(
            ForgeRegistries.ENTITIES, QuartaCInfMod.MOD_ID);

    // REGISTRATION: Init


    public static final RegistryObject<EntityType<Maradona>> MARADONA = ENTITIES.register("maradona",
            () -> EntityType.Builder.of(Maradona::new, MobCategory.CREATURE).sized(1f, 2f)
                    .build(new ResourceLocation(QuartaCInfMod.MOD_ID, "maradona").toString()));

    // REGISTRATION: END


    /**
     * DON'T USE IT!
     * It registers the event bus.
     * This method must be used only by the constructor of the mod.
     */
    public static void register(IEventBus eventBus) {ENTITIES.register(eventBus);}
}
