package com.syric.emissive_dragons;

import net.minecraftforge.common.ForgeConfigSpec;

public class EDClientConfig {

    public static final ForgeConfigSpec.Builder BUILDER = new ForgeConfigSpec.Builder();
    public static final ForgeConfigSpec SPEC;

    // BRUSH
    public static final ForgeConfigSpec.ConfigValue<Boolean> DRAGON_GLOW_BREATHING;
    public static final ForgeConfigSpec.ConfigValue<Double> MEAN_VALUE_AWAKE;
    public static final ForgeConfigSpec.ConfigValue<Integer> PERIOD_AWAKE;
    public static final ForgeConfigSpec.ConfigValue<Double> AMPLITUDE_AWAKE;
    public static final ForgeConfigSpec.ConfigValue<Double> MEAN_VALUE_ASLEEP;
    public static final ForgeConfigSpec.ConfigValue<Integer> PERIOD_ASLEEP;
    public static final ForgeConfigSpec.ConfigValue<Double> AMPLITUDE_ASLEEP;


    static {
        BUILDER.push("Trailier Tales Misc & Technical Configuration");

        DRAGON_GLOW_BREATHING = BUILDER.comment("Whether dragons' glow intensity should cycle over time").worldRestart().define("Dragon glow breathing effect", false);
        MEAN_VALUE_AWAKE = BUILDER.comment("Center of breathing cycle while the dragon is awake. Higher numbers = brighter glow. Must be between 0 and 1. Default: 0.9").defineInRange("Mean while awake", 0.9D, 0.0D, 1.0D, Double.class);
        PERIOD_AWAKE = BUILDER.comment("Period of breathing cycle while the dragon is awake, in ticks. Higher numbers = slower breathing. Must be positive. Default: 180").defineInRange("Period while awake", 180, 0, 1000000, Integer.class);
        AMPLITUDE_AWAKE = BUILDER.comment("Amplitude of breathing cycle while the dragon is awake. Higher numbers = glow varies more. Must be between 0 and 0.5. Default: 0.1").defineInRange("Amplitude while awake", 0.1D, 0.0D, 0.5D, Double.class);
        MEAN_VALUE_ASLEEP = BUILDER.comment("Center of breathing cycle while the dragon is asleep. Must be between 0 and 1. Default: 0.5").defineInRange("Mean while asleep", 0.5D, 0.0D, 1.0D, Double.class);
        PERIOD_ASLEEP = BUILDER.comment("Period of breathing cycle while the dragon is asleep, in ticks. Higher numbers = slower breathing. Must be positive. Default: 240").defineInRange("Period while asleep", 240, 0, 1000000, Integer.class);
        AMPLITUDE_ASLEEP = BUILDER.comment("Amplitude of breathing cycle while the dragon is asleep. Higher numbers = glow varies more. Must be between 0 and 0.5. Default: 0.3").defineInRange("Amplitude while asleep", 0.3D, 0.0D, 0.5D, Double.class);

        BUILDER.pop();
        SPEC = BUILDER.build();
    }

}
