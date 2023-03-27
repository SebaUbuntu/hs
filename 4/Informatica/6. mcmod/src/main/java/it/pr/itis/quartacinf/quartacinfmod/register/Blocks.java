package it.pr.itis.quartacinf.quartacinfmod.register;

import it.pr.itis.quartacinf.quartacinfmod.QuartaCInfMod;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Function;
import java.util.function.Supplier;

public class Blocks {

    // This method is used to contain every registration of object Block to maintain a simpler registration at the end
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(
            ForgeRegistries.BLOCKS, QuartaCInfMod.MOD_ID);

    // REGISTRATION: Init
    public static final RegistryObject<Block> NAPOLETANITE_ORE = registerItemBlock("napoletanite_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_CYAN).strength(5.0f)
                    .sound(SoundType.ANVIL).requiresCorrectToolForDrops()),
            object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Items.CREATIVE_TAB)));

    public static final RegistryObject<Block> COAL_COKE_ORE = registerItemBlock("coal_coke_ore",
            () -> new Block(BlockBehaviour.Properties.of(Material.METAL, MaterialColor.COLOR_GRAY).strength(3.5f)
                    .sound(SoundType.BAMBOO).requiresCorrectToolForDrops()),
            object -> () -> new BlockItem(object.get(), new Item.Properties().tab(Items.CREATIVE_TAB)));

    // REGISTRATION: End

    // This method is used to register blocks
    private static <T extends Block> RegistryObject<T> registerBlock(final String name, final Supplier<? extends T> block) {
        return BLOCKS.register(name, block);
    }

    // This method is used to register ItemBLocks
    private static <T extends Block> RegistryObject<T> registerItemBlock(final String name, final Supplier<? extends T> block,
                                                                         Function<RegistryObject<T>, Supplier<? extends Item>> item) {
        RegistryObject<T> obj = registerBlock(name, block);
        Items.registerItem(name, item.apply(obj));
        return obj;
    }

    /**
     * DON'T USE IT!
     * It registers the event bus.
     * This method must be used only by the constructor of the mod.
     */
    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }
}
