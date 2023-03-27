package it.pr.itis.quartacinf.quartacinfmod;

import it.pr.itis.quartacinf.quartacinfmod.register.Blocks;
import it.pr.itis.quartacinf.quartacinfmod.register.Entity;
import it.pr.itis.quartacinf.quartacinfmod.register.Items;
import it.pr.itis.quartacinf.quartacinfmod.register.ModSounds;
import com.mojang.logging.LogUtils;
import it.pr.itis.quartacinf.quartacinfmod.util.ModItemProperties;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(QuartaCInfMod.MOD_ID)
public class QuartaCInfMod {

    public static final String MOD_ID = "quartacinfmod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public QuartaCInfMod() {
        IEventBus eventBus = FMLJavaModLoadingContext.get().getModEventBus();

        Blocks.register(eventBus);
        Items.register(eventBus);
        ModSounds.register(eventBus);
        Entity.register(eventBus);

        eventBus.addListener(this::clientSetup);

        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void clientSetup(final FMLClientSetupEvent event) {
        ModItemProperties.addCustomItemProperties();
    }
}
