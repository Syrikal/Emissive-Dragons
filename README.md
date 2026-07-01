This mod allows dragons to render emissive "glow" textures. These textures can vary with the dragon's variant, stage, and sex, as well as whether or not it is asleep. If a certain combination has no file provided, it will not render a glow.

This mod _does not add any textures of its own!_ It will do nothing alone. It _must_ be paired with a resource pack designed to use Emissive Dragons. It was created for [Tabla's Dragon Retextures]([url](https://www.curseforge.com/minecraft/texture-packs/excalibur-ice-and-fire-tablas-dragon-retextures)), but I encourage other resource pack artists to make their own!

The mod may be toggled entirely in config, and it will ignore any dragon with the "EmissiveDragonsIgnore:true" NBT tag.

File structure:
- resources
  - assets
    - emissive_dragons
      - textures
        - models
          - firedragon
            - red_1_eyes.png
            - red_1_male_glow.png
            - red_1_male_glow_sleep.png
            - red_1_female_glow.png
            - red_1_female_glow_sleep.png
            - ... repeat for red_2 through red_5
            - ... repeat for other fire dragon variants
          - ...repeat for icedragon and lightning dragon

"Eyes" textures disappear whenever the dragon closes its eyes, including blinking. If present, they override and replace the dragon's usual Eyes texture! In Community Edition, they also only affect the dragon's head and neck.
"Glow" textures apply across the whole body and change to their alternate version when the dragon is asleep, unless "different sleeping glow" is turned off in config.

If using Ice & Fire Community Edition, make sure to call the purple lightning dragon variant 'amethyst', while regular Ice & Fire calls them 'amythest'!

**Also, whether you're using Ice & Fire Community Edition or the original, make sure you are using the matching version of this mod!!**
