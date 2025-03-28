package boundless.botany;

import java.util.Map;

class ConfigData {
  Map<String, Boolean> plants;
  Map<String, Boolean> blocks;

  ConfigData(Map<String, Boolean> plants, Map<String, Boolean> blocks) {
    this.plants = plants;
    this.blocks = blocks;
  }
}
