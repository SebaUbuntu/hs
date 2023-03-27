package it.pr.itis.quartacinf.quartacinfmod.util;

import it.pr.itis.quartacinf.quartacinfmod.register.Blocks;
import it.pr.itis.quartacinf.quartacinfmod.register.Items;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraftforge.common.ForgeTier;

// This class give stats to NAPOLETANITE_INGOT's object
// When you craft an item with this stats, it takes those stats
public class ModTier {

    // general stats for other than below objects (in napoletanite); take a look at Items.java to see the other objects
    public static final ForgeTier NAPOLETANITE_TIER = new ForgeTier(5, 3200, 10.0f,
            0.0f, 20, BlockTags.NEEDS_DIAMOND_TOOL,() -> Ingredient.of(Items.NAPOLETANITE_INGOT.get()));

    //stats for napoletanite_sword
    public static final ForgeTier NAPOLETANITE_SWORD_TIER = new ForgeTier(6, 51, 30.f,
            10.0f, 25, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(Items.NAPOLETANITE_INGOT.get()));

    //stats for napoletanite_pickaxe
    public static final ForgeTier NAPOLETANITE_PICKAXE_TIER = new ForgeTier(9, 5000, 50.0f,
            5.0f, 10, BlockTags.NEEDS_DIAMOND_TOOL, () -> Ingredient.of(Items.NAPOLETANITE_INGOT.get()));

}
