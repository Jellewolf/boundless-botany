package boundless.botany.mixin.client;

import boundless.botany.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PlantBlock;
import net.minecraft.registry.Registries;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PlantBlock.class)
public class PlantBlockMixin {

  @Inject(method = "canPlaceAt", at = @At("HEAD"), cancellable = true)
  private void onCanPlaceAt(
      BlockState state, WorldView world, BlockPos pos, CallbackInfoReturnable<Boolean> info) {

    Block blockBelow = world.getBlockState(pos.down()).getBlock();
    PlantBlock plantBlock = (PlantBlock) (Object) this;

    String plantName = Registries.BLOCK.getId(plantBlock).toString();
    String blockName = Registries.BLOCK.getId(blockBelow).toString();

    // Block must be solid and not opaque
    if (!blockBelow.getDefaultState().isSolidBlock(world, pos)
        && blockBelow.getDefaultState().isOpaque()) {
      info.setReturnValue(false);
      return;
    }

    if (!ModConfig.isPlantEnabled(plantName)) {
      if (isValidVanillaPlacementBlock(blockBelow)) {
        info.setReturnValue(true);
      } else {
        info.setReturnValue(false);
      }
      return;
    }

    if (!ModConfig.isBlockEnabled(blockName)) {
      if (isValidVanillaPlacementBlock(blockBelow)) {
        info.setReturnValue(true);
      } else {
        info.setReturnValue(false);
      }
      return;
    }

    // Plants cannot be placed on other plants
    if (blockBelow instanceof PlantBlock) {
      info.setReturnValue(false);
      return;
    }

    // Indien het een geldig blok is, plaats de plant
    if (isBlock(blockBelow)) {
      info.setReturnValue(true);
    }
  }

  @Unique
  private boolean isValidVanillaPlacementBlock(Block blockBelow) {
    return blockBelow == Blocks.DIRT
        || blockBelow == Blocks.GRASS_BLOCK
        || blockBelow == Blocks.FARMLAND
        || blockBelow == Blocks.SAND
        || blockBelow == Blocks.PODZOL
        || blockBelow == Blocks.COARSE_DIRT
        || blockBelow == Blocks.ROOTED_DIRT
        || blockBelow == Blocks.PALE_MOSS_BLOCK
        || blockBelow == Blocks.MOSS_BLOCK;
  }

  @Unique
  private boolean isBlock(Block blockBelow) {
    return !(blockBelow == Blocks.AIR || blockBelow == Blocks.WATER || blockBelow == Blocks.LAVA);
  }
}
