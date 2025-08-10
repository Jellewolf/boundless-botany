package boundless.botany;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.block.PlantBlock;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ModPlants {
  public static final Set<PlantBlock> ALLOWED_PLANTS = new HashSet<>();
  public static final Set<String> PLANT_NAMES = new HashSet<>();

  static {
    // Overworld vegetation
    addPlant("minecraft:short_grass");
    addPlant("minecraft:tall_grass");
    addPlant("minecraft:fern");
    addPlant("minecraft:large_fern");
    addPlant("minecraft:dandelion");
    addPlant("minecraft:poppy");
    addPlant("minecraft:blue_orchid");
    addPlant("minecraft:allium");
    addPlant("minecraft:azure_bluet");
    addPlant("minecraft:red_tulip");
    addPlant("minecraft:orange_tulip");
    addPlant("minecraft:white_tulip");
    addPlant("minecraft:pink_tulip");
    addPlant("minecraft:oxeye_daisy");
    addPlant("minecraft:cornflower");
    addPlant("minecraft:lily_of_the_valley");
    addPlant("minecraft:wheat");
    addPlant("minecraft:carrots");
    addPlant("minecraft:potatoes");
    addPlant("minecraft:beetroots");
    addPlant("minecraft:pink_petals");
    addPlant("minecraft:torchflower");
    addPlant("minecraft:leaf_litter");
    addPlant("minecraft:wildflowers");
    addPlant("minecraft:bush");
    addPlant("minecraft:tall_dry_grass");
    addPlant("minecraft:short_dry_grass");
    addPlant("minecraft:cactus_flower");
    addPlant("minecraft:firefly_bush");

    // Nether vegetation
    addPlant("minecraft:crimson_fungus");
    addPlant("minecraft:warped_fungus");
    addPlant("minecraft:crimson_roots");
    addPlant("minecraft:warped_roots");
    addPlant("minecraft:nether_sprouts");
    addPlant("minecraft:twisting_vines");
    addPlant("minecraft:weeping_vines");

    // Saplings
    addPlant("minecraft:oak_sapling");
    addPlant("minecraft:spruce_sapling");
    addPlant("minecraft:birch_sapling");
    addPlant("minecraft:jungle_sapling");
    addPlant("minecraft:acacia_sapling");
    addPlant("minecraft:dark_oak_sapling");
    addPlant("minecraft:mangrove_propagule");
    addPlant("minecraft:cherry_sapling");
  }

  private static void addPlant(String id) {
    Identifier identifier = Identifier.tryParse(id);
    if (Registries.BLOCK.containsId(identifier)) {
      if (Registries.BLOCK.get(identifier) instanceof PlantBlock plantBlock) {
        ALLOWED_PLANTS.add(plantBlock);
        PLANT_NAMES.add(id);
      }
    }
  }
}

