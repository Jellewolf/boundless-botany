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
    // Add all minecraft vegetation to this set
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
