package it.pr.itis.quartacinf.quartacinfmod.register;

import it.pr.itis.quartacinf.quartacinfmod.QuartaCInfMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

// This class is used to initialize new discs in Minecraft
public class ModSounds {

    // This method is used to contain every registration of object SoundEvent to maintain a simpler registration at the end
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, QuartaCInfMod.MOD_ID);


    // Initialize SoundEvent Objects - INIT

    public static final RegistryObject<SoundEvent> TARANTELLA_NAPOLETANA = registerSoundEvent("tarantella_music");
    public static final RegistryObject<SoundEvent> FUNICULI_FUNICULA = registerSoundEvent("funiculi_funicula_music");
    public static final RegistryObject<SoundEvent> INNO_NAPOLI = registerSoundEvent("inno_napoli_music");

    // Initialize SoundEvent Objects - END


    //used to register SoundEvent objects above here
    private static RegistryObject<SoundEvent> registerSoundEvent(String name) {
        return SOUNDS.register(name, () -> new SoundEvent(new ResourceLocation(QuartaCInfMod.MOD_ID, name)));
    }


    /**
     * DON'T USE IT!
     * It registers the event bus.
     * This method must be used only by the constructor of the mod.
     */
    public static void register(IEventBus eventBus) {
        SOUNDS.register(eventBus);
    }
}
