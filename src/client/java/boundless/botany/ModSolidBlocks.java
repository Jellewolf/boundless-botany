package boundless.botany;

import java.util.HashSet;
import java.util.Set;
import net.minecraft.block.*;
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

      if (block.getDefaultState().isOpaque()
          && !(block instanceof SlabBlock)
          && !(block instanceof StairsBlock)
          && !(block instanceof WallBlock)
          && !(block instanceof FenceBlock)
          && !(block instanceof FenceGateBlock)
          && !(block instanceof TrapdoorBlock)
          && !(block instanceof DoorBlock)
          && !(block instanceof BannerBlock)
          && !(block instanceof PaneBlock)
          && !(block instanceof LadderBlock)
          && !(block instanceof ChainBlock)
          && !(block instanceof ScaffoldingBlock)
          && !(block instanceof LightningRodBlock)
          && !(block instanceof EndRodBlock)
          && !(block instanceof LecternBlock)
          && !(block instanceof EnchantingTableBlock)
          && !(block instanceof BellBlock)
          && !(block instanceof ChestBlock)
          && !(block instanceof BarrelBlock)
          && !(block instanceof BeaconBlock)
          && !(block instanceof CraftingTableBlock)
          && !(block instanceof SmithingTableBlock)
          && !(block instanceof ShulkerBoxBlock)
          && !(block instanceof SkullBlock)
          && !(block instanceof WallSkullBlock)
          && !(block instanceof SignBlock)
          && !(block instanceof WallSignBlock)
          && !(block instanceof HangingSignBlock)
          && !(block instanceof WallHangingSignBlock)
          && !(block instanceof CakeBlock)
          && !(block instanceof CandleBlock)
          && !(block instanceof CandleCakeBlock)
          && !(block instanceof BigDripleafBlock)
          && !(block instanceof SmallDripleafBlock)
          && !(block instanceof SporeBlossomBlock)
          && !(block instanceof MushroomBlock)
          && !(block instanceof LilyPadBlock)
          && !(block instanceof CarpetBlock)
          && !(block instanceof EndPortalFrameBlock)
          && !(block instanceof EndPortalBlock)
          && !(block instanceof NetherPortalBlock)
          && !(block instanceof BedBlock)
          && !(block instanceof RespawnAnchorBlock)
          && !(block instanceof CauldronBlock)
          && !(block instanceof RedstoneTorchBlock)
          && !(block instanceof RedstoneWireBlock)
          && !(block instanceof LeverBlock)
          && !(block instanceof PressurePlateBlock)
          && !(block instanceof DaylightDetectorBlock)
          && !(block instanceof PistonBlock)
          && !(block instanceof ObserverBlock)
          && !(block instanceof DispenserBlock)
          && !(block instanceof DropperBlock)
          && !(block instanceof HopperBlock)
          && !VANILLA_PLANTABLE_BLOCKS.contains(block)) {

        CONFIGURABLE_PLANTABLE_BLOCKS.add(block);
        BLOCK_NAMES.add(id.toString());
      }
    }
  }
}
