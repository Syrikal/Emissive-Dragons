package com.syric.emissive_dragons;

import net.minecraftforge.common.ForgeConfigSpec;

public class EDClientConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    // Glow breathing (removed)
//    public static final ForgeConfigSpec.ConfigValue<Boolean> DRAGON_GLOW_BREATHING;
//    public static final ForgeConfigSpec.ConfigValue<Double> MEAN_VALUE_AWAKE;
//    public static final ForgeConfigSpec.ConfigValue<Integer> PERIOD_AWAKE;
//    public static final ForgeConfigSpec.ConfigValue<Double> AMPLITUDE_AWAKE;
//    public static final ForgeConfigSpec.ConfigValue<Double> MEAN_VALUE_ASLEEP;
//    public static final ForgeConfigSpec.ConfigValue<Integer> PERIOD_ASLEEP;
//    public static final ForgeConfigSpec.ConfigValue<Double> AMPLITUDE_ASLEEP;

    public static final ForgeConfigSpec.ConfigValue<Boolean> DRAGON_GLOW_GLOBAL_TOGGLE;
    public static final ForgeConfigSpec.ConfigValue<Boolean> DIMORPHIC_GLOW;
    public static final ForgeConfigSpec.ConfigValue<Boolean> DIFFERENT_SLEEP_GLOW;
    public static final ForgeConfigSpec.ConfigValue<Boolean> DIMORPHIC_EYE_GLOW;
    public static final ForgeConfigSpec.ConfigValue<Boolean> DEAD_DRAGONS_GLOW;
    public static final ForgeConfigSpec.ConfigValue<Boolean> SKELETAL_DRAGONS_GLOW;

    static {
//        BUILDER.push("Dragon Glow Configuration");

        //Disabled glow-breathing configuration
//        DRAGON_GLOW_BREATHING = BUILDER.comment("Whether dragons' glow intensity should cycle over time").worldRestart().define("Dragon glow breathing effect", false);
//        MEAN_VALUE_AWAKE = BUILDER.comment("Center of breathing cycle while the dragon is awake. Higher numbers = brighter glow. Must be between 0 and 1. Default: 0.9").defineInRange("Mean while awake", 0.9D, 0.0D, 1.0D, Double.class);
//        PERIOD_AWAKE = BUILDER.comment("Period of breathing cycle while the dragon is awake, in ticks. Higher numbers = slower breathing. Must be positive. Default: 180").defineInRange("Period while awake", 180, 0, 1000000, Integer.class);
//        AMPLITUDE_AWAKE = BUILDER.comment("Amplitude of breathing cycle while the dragon is awake. Higher numbers = glow varies more. Must be between 0 and 0.5. Default: 0.1").defineInRange("Amplitude while awake", 0.1D, 0.0D, 0.5D, Double.class);
//        MEAN_VALUE_ASLEEP = BUILDER.comment("Center of breathing cycle while the dragon is asleep. Must be between 0 and 1. Default: 0.5").defineInRange("Mean while asleep", 0.5D, 0.0D, 1.0D, Double.class);
//        PERIOD_ASLEEP = BUILDER.comment("Period of breathing cycle while the dragon is asleep, in ticks. Higher numbers = slower breathing. Must be positive. Default: 240").defineInRange("Period while asleep", 240, 0, 1000000, Integer.class);
//        AMPLITUDE_ASLEEP = BUILDER.comment("Amplitude of breathing cycle while the dragon is asleep. Higher numbers = glow varies more. Must be between 0 and 0.5. Default: 0.3").defineInRange("Amplitude while asleep", 0.3D, 0.0D, 0.5D, Double.class);

        DRAGON_GLOW_GLOBAL_TOGGLE = BUILDER.comment("Toggles whether the mod does anything.\nRequires game restart to take effect.\nDefault: True (active)").worldRestart().define("Emissive Dragons enabled", true);
        DIMORPHIC_GLOW = BUILDER.comment("Toggles whether the glow is different for male and female dragons.\nIf false, all dragons will use the 'male' textures. (They also do this if no separate female texture is loaded.)\nDon't change this unless you're a resource pack author or the resource pack you're using told you to.\nDefault: True").define("Different female textures", true);
        DIFFERENT_SLEEP_GLOW = BUILDER.comment("Toggles whether the glow is different for sleeping dragons.\nIf false, sleeping dragons will use the 'glow' texture rather than the 'glow_sleep' one. (They also do this if no separate sleep texture is loaded.)\nDon't change this unless you're a resource pack author or the resource pack you're using told you to.\nDefault: True").define("Different sleeping textures", true);
        DIMORPHIC_EYE_GLOW = BUILDER.comment("Toggles whether female dragons use a separate eye-glow texture.\nIf false, female dragons will use the standard eye textures. (They also do this if no separate female texture is loaded.) \nDon't change this unless you're a resource pack author or the resource pack you're using told you to.\nDefault: True").define("Different female eye glow textures", true);
        DEAD_DRAGONS_GLOW = BUILDER.comment("Toggles whether dead dragons continue to use their glow texture.\nIf true, they use their sleeping glow texture.\nDefault: True").define("Dead dragons glow", true);
        SKELETAL_DRAGONS_GLOW = BUILDER.comment("Toggles whether dragon skeletons continue to use their glow texture.\nDefault: False").define("Dragon skeletons glow", false);

//        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}
