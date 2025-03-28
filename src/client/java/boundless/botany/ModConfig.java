package boundless.botany;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import java.io.*;
import java.util.HashMap;
import java.util.Map;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.text.Text;

public class ModConfig {

  private static final File CONFIG_FILE =
      new File(FabricLoader.getInstance().getConfigDir().toFile(), "boundless-botany.json");
  private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
  private static Map<String, Boolean> plantConfig = new HashMap<>();
  private static Map<String, Boolean> blockConfig = new HashMap<>();

  static {
    loadConfig();
  }

  public static void loadConfig() {
    if (!CONFIG_FILE.exists()) {
      initializeDefaultConfig();
      saveConfig();
      return;
    }

    try (FileReader reader = new FileReader(CONFIG_FILE)) {
      ConfigData data = GSON.fromJson(reader, ConfigData.class);
      if (data != null) {
        plantConfig = data.plants != null ? data.plants : new HashMap<>();
        blockConfig = data.blocks != null ? data.blocks : new HashMap<>();
      }
    } catch (IOException e) {
      System.err.println("Error while loading the config!: " + e.getMessage());
    }
  }

  private static void initializeDefaultConfig() {
    // for default set all plants enabled
    plantConfig.clear();
    for (String plantName : ModPlants.PLANT_NAMES) {
      plantConfig.putIfAbsent(plantName, true);
    }

    // for default set all blocks enabled
    blockConfig.clear();
    for (String blockName : ModSolidBlocks.SOLID_BLOCKS) {
      blockConfig.putIfAbsent(blockName, true);
    }
  }

  public static void saveConfig() {
    try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
      GSON.toJson(new ConfigData(plantConfig, blockConfig), writer);
    } catch (IOException e) {
      System.err.println("Error when saving config!: " + e.getMessage());
    }
  }

  // helper methods to check if plant is enabled
  public static boolean isPlantEnabled(String plantName) {
    if (!ModPlants.PLANT_NAMES.contains(plantName)) {
      return false;
    }
    return plantConfig.getOrDefault(plantName, true);
  }

  public static void setPlantEnabled(String plantName, boolean isEnabled) {
    plantConfig.put(plantName, isEnabled);
    saveConfig();
  }

  // helper methods to check if block is enabled
  public static boolean isBlockEnabled(String blockName) {
    return blockConfig.getOrDefault(blockName, true);
  }

  public static void setBlockEnabled(String blockName, boolean isEnabled) {
    blockConfig.put(blockName, isEnabled);
    saveConfig();
  }

  public static ConfigScreenFactory<?> createConfigScreen() {
    return parent -> {
      ConfigBuilder builder =
          ConfigBuilder.create()
              .setTitle(Text.of("Boundless Botany Config"))
              .setSavingRunnable(ModConfig::saveConfig);

      ConfigEntryBuilder entryBuilder = builder.entryBuilder();

      //  Plant category
      ConfigCategory plantsCategory = builder.getOrCreateCategory(Text.of("Plants"));
      plantsCategory.addEntry(
          entryBuilder
              .startTextDescription(
                  Text.of("Select which plants can be placed on all solid blocks."))
              .build());

      for (String plantName : ModPlants.PLANT_NAMES) {
        plantsCategory.addEntry(
            entryBuilder
                .startBooleanToggle(Text.of(plantName), isPlantEnabled(plantName))
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> setPlantEnabled(plantName, newValue))
                .build());
      }

      // Block category
      ConfigCategory blocksCategory = builder.getOrCreateCategory(Text.of("Blocks"));
      blocksCategory.addEntry(
          entryBuilder
              .startTextDescription(Text.of("Select which solid blocks can support plants."))
              .build());

      for (String blockName : ModSolidBlocks.BLOCK_NAMES) {
        blocksCategory.addEntry(
            entryBuilder
                .startBooleanToggle(Text.of(blockName), isBlockEnabled(blockName))
                .setDefaultValue(true)
                .setSaveConsumer(newValue -> setBlockEnabled(blockName, newValue))
                .build());
      }

      return builder.build();
    };
  }
}
