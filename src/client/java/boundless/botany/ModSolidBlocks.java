package boundless.botany;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class ModSolidBlocks {
  public static final Set<Block> CONFIGURABLE_PLANTABLE_BLOCKS = new HashSet<>();
  public static final Set<String> SOLID_BLOCKS = new HashSet<>();
  public static final Set<String> BLOCK_NAMES = new HashSet<>();
  private static final Set<Block> VANILLA_PLANTABLE_BLOCKS =
      Set.of(
          Blocks.DIRT,
          Blocks.GRASS_BLOCK,
          Blocks.FARMLAND,
          Blocks.SAND,
          Blocks.PODZOL,
          Blocks.RED_SAND,
          Blocks.SOUL_SAND,
          Blocks.SOUL_SOIL,
          Blocks.MYCELIUM);

  static {
    for (Identifier id : Registries.BLOCK.getIds()) {
      Block block = Registries.BLOCK.get(id);

      if (block.getDefaultState().isOpaque()) {
        if (!VANILLA_PLANTABLE_BLOCKS.contains(block)) {
          CONFIGURABLE_PLANTABLE_BLOCKS.add(block);
          BLOCK_NAMES.add(id.toString());
        }
      }
    }
  }
}
