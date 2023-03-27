package it.pr.itis.quartacinf.quartacinfmod.register;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

// This class contains the information about the properties of the food
public class ModFoods {

    // Initialize FoodProperties - INIT
    public static final FoodProperties PASTIERA = (new FoodProperties.Builder()).nutrition(6).saturationMod(0.3F).build(); //declaration of a new type of food

    public static final FoodProperties BABA = (new FoodProperties.Builder()).nutrition(10).saturationMod(1f)
            .effect(() -> new MobEffectInstance(MobEffects.GLOWING, 100, 4), 1f)
            .effect(() -> new MobEffectInstance(MobEffects.LEVITATION, 100, 4 ), 0.5f).build();
    // Initialize FoodProperties - END
}
