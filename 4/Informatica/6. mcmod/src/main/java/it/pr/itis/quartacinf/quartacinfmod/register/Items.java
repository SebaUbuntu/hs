package it.pr.itis.quartacinf.quartacinfmod.register;

import java.util.function.Supplier;
import it.pr.itis.quartacinf.quartacinfmod.QuartaCInfMod;
import it.pr.itis.quartacinf.quartacinfmod.util.ModTier;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.item.*;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.annotation.Nullable;

public class Items {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(
            ForgeRegistries.ITEMS, QuartaCInfMod.MOD_ID);

    // L'icona che apppare nel menu di creativa
    public static final RegistryObject<Item> ICON_ITEM = registerItem("icon_item",
            () -> new Item(new Item.Properties()));

    // La tab nel menu di creativa dedicata ai nostri item custom
    public static final CreativeModeTab CREATIVE_TAB = new CreativeModeTab(QuartaCInfMod.MOD_ID) {
        @Override
        @OnlyIn(Dist.CLIENT)
        public ItemStack makeIcon() {
            return new ItemStack(ICON_ITEM.get());
        }
    };

    // REGISTRATION: Init

    // Miscellaneous
    public static final RegistryObject<Item> DIAMOND_NUGGET = registerItem("diamond_nugget",
            () -> new Item(new Item.Properties().tab(CREATIVE_TAB)));

    public static final RegistryObject<Item> GOD_EYE = registerItem("god_eye",
            () -> new BowItem(new Item.Properties().tab(CREATIVE_TAB)));

    public static final RegistryObject<Item> COAL_COKE = registerItem("coal_coke",
            () -> new Item(new Item.Properties().tab(CREATIVE_TAB)) {
                @Override
                public int getBurnTime(ItemStack itemStack, @Nullable RecipeType<?> recipeType) {
                    return 3200;
                }
            });

    // Napoli
    // FACCIAMO RINASCERE LA SCARRAFONE MODE #PISTOLENELLAFENDI
    // BASIC ITEMS
    public static final RegistryObject<Item> NAPOLETANITE = registerItem("napoletanite",
            () -> new Item(new Item.Properties().tab(CREATIVE_TAB)));

    public static final RegistryObject<Item> NAPOLETANITE_INGOT = registerItem("napoletanite_ingot",
            () -> new Item(new Item.Properties().tab(CREATIVE_TAB)));

    // BASICS TOOLS
    public static final RegistryObject<Item> NAPOLETANITE_PICKAXE = registerItem("napoletanite_pickaxe",
            () -> new PickaxeItem(ModTier.NAPOLETANITE_PICKAXE_TIER, 1, 2.2f,
                    new Item.Properties().tab(CREATIVE_TAB)));

    public static final RegistryObject<Item> NAPOLETANITE_SWORD = registerItem("napoletanite_sword",
            () -> new SwordItem(ModTier.NAPOLETANITE_SWORD_TIER, 10, 2.0f,
                    new Item.Properties().tab(CREATIVE_TAB)));

    public static final RegistryObject<Item> NAPOLETANITE_AXE = registerItem("napoletanite_axe",
            () -> new AxeItem(ModTier.NAPOLETANITE_TIER, 5, 1f,
                    new Item.Properties().tab(CREATIVE_TAB)));

    public static final RegistryObject<Item> NAPOLETANITE_HOE = registerItem("napoletanite_hoe",
            () -> new HoeItem(ModTier.NAPOLETANITE_TIER, 0, 4f,
                    new Item.Properties().tab(CREATIVE_TAB)));

    public static final RegistryObject<Item> NAPOLETANITE_SHOVEL = registerItem("napoletanite_shovel",
            () -> new ShovelItem(ModTier.NAPOLETANITE_TIER, 0, 2.8f,
                    new Item.Properties().tab(CREATIVE_TAB)));

    // ARMOR
    public static final RegistryObject<Item> NAPOLETANITE_HELMET = registerItem("napoletanite_helmet",
            () -> new ArmorItem(ModArmorMaterials.NAPOLETANITE, EquipmentSlot.HEAD,
                    new Item.Properties().tab(CREATIVE_TAB)));

    public static final RegistryObject<Item> NAPOLETANITE_CHESTPLATE = registerItem("napoletanite_chestplate",
            () -> new ArmorItem(ModArmorMaterials.NAPOLETANITE, EquipmentSlot.CHEST,
                    new Item.Properties().tab(CREATIVE_TAB)));

    public static final RegistryObject<Item> NAPOLETANITE_LEGGINGS = registerItem("napoletanite_leggings",
            () -> new ArmorItem(ModArmorMaterials.NAPOLETANITE, EquipmentSlot.LEGS,
                    new Item.Properties().tab(CREATIVE_TAB)));

    public static final RegistryObject<Item> NAPOLETANITE_BOOTS = registerItem("napoletanite_boots",
            () -> new ArmorItem(ModArmorMaterials.NAPOLETANITE, EquipmentSlot.FEET,
                    new Item.Properties().tab(CREATIVE_TAB)));

    // CUSTOM ARMOR
    public static final RegistryObject<Item> NAPOLETANITE_WATCH = registerItem("napoletanite_watch",
            () -> new Item(new Item.Properties().tab(CREATIVE_TAB)));

    // MUSIC DISCS
    public static final RegistryObject<Item> TARANTELLA_MUSIC_DISC = registerItem("tarantella_music_disc",
            () -> new RecordItem(4, ModSounds.TARANTELLA_NAPOLETANA,
                    new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)));

    public static final RegistryObject<Item> FUNICULI_FUNICULA_MUSIC_DISC = registerItem("funiculi_funicula_music_disc",
            () -> new RecordItem(4, ModSounds.FUNICULI_FUNICULA,
                    new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)));

    public static final RegistryObject<Item> INNO_NAPOLI_MUSIC_DISC = registerItem("inno_napoli_music_disc",
            () -> new RecordItem(4, ModSounds.INNO_NAPOLI,
                    new Item.Properties().tab(CREATIVE_TAB).stacksTo(1)));

    // FOOD
    public static final RegistryObject<Item> PASTIERA = registerItem("pastiera",
            () -> new Item(new Item.Properties().tab(CREATIVE_TAB).food(ModFoods.PASTIERA)));

    public static final RegistryObject<Item> BABA = registerItem("baba",
            () -> new Item(new Item.Properties().tab(CREATIVE_TAB).food(ModFoods.BABA)));

    // SPAWN EGG
    public static final RegistryObject<ForgeSpawnEggItem> MARADONA_SPAWN_EGG = registerItem("maradona_spawn_egg",
            () -> new ForgeSpawnEggItem(Entity.MARADONA, 0x99CBFF, 0xFFFFFF, new Item.Properties().tab(CREATIVE_TAB)));

    // REGISTRATION: END

    /**
     * Registra un nuovo elemento
     * @param name nome dell'elemento
     * @param item oggetto Item
     * @return
     * @param <T>
     */
    public static <T extends Item> RegistryObject<T> registerItem(final String name, final Supplier<T> item) {
        return ITEMS.register(name, item);
    }

    /**
     * DON'T USE IT!
     * It registers the event bus.
     * This method must be used only by the constructor of the mod.
     */
    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }
}
