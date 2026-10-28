## Licensing & Copyrights
You do not have permission to modify, port, take assets from, or otherwise redistribute this mod.

**Code** licensing goes under **GNU**; which means you may use it under their standards (as seen in CODE_LICENSE.txt).
Please credit the original (this) source code!

**Asset** licensing goes under **All Rights Reserved**; 
you may ***not*** use models, textures, animations, or any other assets in your standalone mod(s) or other projects.
Altering and re-distribution/ sale of my assets are not permitted, under any circumstance.

You also may ***not*** sell my assets or anything pertaining to, or within my mod(s), in your own shop for real
currency. This includes the selling and buying of creatures, items, blocks, and anything in-between in 'server
shops' or websites for real money.

Texture Packs and Livestock Overhaul-Dependent Addons are okay, and encouraged, provided they're free and for the public to use!

## Livestock Overhaul: Dynamic Resources
Livestock Overhaul comes with a built-in custom texture option for its animals.
This means you can create your own textures, whilst still keeping the OG ones from the mod intact.
No coding from you required. It's as simple as a texture pack!

I would suggest putting the pack server-side and having your players always have it on. If they remove it
and re-enable it, and they still can't see the texture, they should restart their game.

### Make sure to set your resource pack up correctly.
Import the GeckoLib geo models into Blockbench and create your texture. 
For example, import 'o_horse.geo.json' into Blockbench, and create your new texture(s).

You must have an 'assets' folder within your texture pack, and a working MCMETA file. Example:

```
readme_custom_textures_template > assets & mcmeta > examplepack > base_colors > horse > my_custom_texture_name.png
                                                    ^ start with this folder (the namespace) when using the summon commands, NOT the pack name!
```

### Template For Beginners
There's a 'readme_custom_textures_template.zip' texture pack in this repository you can download to use as you like.
You can use it to make your own resource pack with ease. It comes with the pre-made "example_color.png" and "example_pattern.png" that you can spawn in to make sure the pack works.
You can delete these if desired.

**Custom Color Only**
```
/summon dragnlivestock:o_horse ~ ~ ~ {Variant_Texture:"examplepack:base_colors/horse/example_color.png"}
```
**Custom Pattern Only**
```
/summon dragnlivestock:o_horse ~ ~ ~ {Overlay_Texture:"examplepack:pattern_overlays/horse/example_pattern.png"} 
```
**Both**
```
/summon dragnlivestock:o_horse ~ ~ ~ {Variant_Texture:"examplepack:base_colors/horse/example_color.png", Overlay_Texture:"examplepack:pattern_overlays/horse/example_pattern.png"}
```

### Animals That Support Dynamic Resources
Don't see the animal you want to add a texture to? Suggest it in this repo's Issues!

```
O-Horse
/summon dragnlivestock:o_horse ~ ~ ~ {...}

O-Mule
/summon dragnlivestock:o_mule ~ ~ ~ {...}

O-Donkey
/summon dragnlivestock:o_donkey ~ ~ ~ {...}

O-Rabbit
/summon dragnlivestock:o_rabbit ~ ~ ~ {...}

Caribou
/summon dragnlivestock:caribou ~ ~ ~ {...}

Unicorn
/summon dragnlivestock:unicorn ~ ~ ~ {...}
```
