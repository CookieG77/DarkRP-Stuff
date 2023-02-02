
package cookieg.darkrp_stuff.block;

import net.minecraftforge.registries.ObjectHolder;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.IBlockReader;
import net.minecraft.util.math.vector.Vector3d;
import net.minecraft.util.math.shapes.VoxelShapes;
import net.minecraft.util.math.shapes.VoxelShape;
import net.minecraft.util.math.shapes.ISelectionContext;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Rotation;
import net.minecraft.util.Mirror;
import net.minecraft.util.Direction;
import net.minecraft.state.StateContainer;
import net.minecraft.state.DirectionProperty;
import net.minecraft.loot.LootContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.item.BlockItemUseContext;
import net.minecraft.item.BlockItem;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.block.material.Material;
import net.minecraft.block.SoundType;
import net.minecraft.block.HorizontalBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Block;

import java.util.List;
import java.util.Collections;

import cookieg.darkrp_stuff.itemgroup.DarkRPStuffItemGroup;
import cookieg.darkrp_stuff.DarkrpStuffModElements;

@DarkrpStuffModElements.ModElement.Tag
public class AluminiumCrateOpenedBlock extends DarkrpStuffModElements.ModElement {
	@ObjectHolder("darkrp_stuff:aluminium_crate_opened")
	public static final Block block = null;

	public AluminiumCrateOpenedBlock(DarkrpStuffModElements instance) {
		super(instance, 59);
	}

	@Override
	public void initElements() {
		elements.blocks.add(() -> new CustomBlock());
		elements.items
				.add(() -> new BlockItem(block, new Item.Properties().group(DarkRPStuffItemGroup.tab)).setRegistryName(block.getRegistryName()));
	}

	@Override
	@OnlyIn(Dist.CLIENT)
	public void clientLoad(FMLClientSetupEvent event) {
		RenderTypeLookup.setRenderLayer(block, RenderType.getCutout());
	}

	public static class CustomBlock extends Block {
		public static final DirectionProperty FACING = HorizontalBlock.HORIZONTAL_FACING;

		public CustomBlock() {
			super(Block.Properties.create(Material.WOOD).sound(SoundType.SCAFFOLDING).hardnessAndResistance(1f, 10f).setLightLevel(s -> 0).notSolid()
					.setOpaque((bs, br, bp) -> false));
			this.setDefaultState(this.stateContainer.getBaseState().with(FACING, Direction.NORTH));
			setRegistryName("aluminium_crate_opened");
		}

		@Override
		public boolean propagatesSkylightDown(BlockState state, IBlockReader reader, BlockPos pos) {
			return true;
		}

		@Override
		public int getOpacity(BlockState state, IBlockReader worldIn, BlockPos pos) {
			return 0;
		}

		@Override
		public VoxelShape getShape(BlockState state, IBlockReader world, BlockPos pos, ISelectionContext context) {
			Vector3d offset = state.getOffset(world, pos);
			switch ((Direction) state.get(FACING)) {
				case SOUTH :
				default :
					return VoxelShapes.or(makeCuboidShape(20, 0, 16, 19.5, 13, 0), makeCuboidShape(20, 0, 16, -4, 13, 15.5),
							makeCuboidShape(20, 0, 0.5, -4, 13, 0), makeCuboidShape(-3.5, 0, 16, -4, 13, 0), makeCuboidShape(20, 0, 16, -4, 8.5, 0))

							.withOffset(offset.x, offset.y, offset.z);
				case NORTH :
					return VoxelShapes.or(makeCuboidShape(-4, 0, 0, -3.5, 13, 16), makeCuboidShape(-4, 0, 0, 20, 13, 0.5),
							makeCuboidShape(-4, 0, 15.5, 20, 13, 16), makeCuboidShape(19.5, 0, 0, 20, 13, 16), makeCuboidShape(-4, 0, 0, 20, 8.5, 16))

							.withOffset(offset.x, offset.y, offset.z);
				case EAST :
					return VoxelShapes.or(makeCuboidShape(16, 0, -4, 0, 13, -3.5), makeCuboidShape(16, 0, -4, 15.5, 13, 20),
							makeCuboidShape(0.5, 0, -4, 0, 13, 20), makeCuboidShape(16, 0, 19.5, 0, 13, 20), makeCuboidShape(16, 0, -4, 0, 8.5, 20))

							.withOffset(offset.x, offset.y, offset.z);
				case WEST :
					return VoxelShapes.or(makeCuboidShape(0, 0, 20, 16, 13, 19.5), makeCuboidShape(0, 0, 20, 0.5, 13, -4),
							makeCuboidShape(15.5, 0, 20, 16, 13, -4), makeCuboidShape(0, 0, -3.5, 16, 13, -4), makeCuboidShape(0, 0, 20, 16, 8.5, -4))

							.withOffset(offset.x, offset.y, offset.z);
			}
		}

		@Override
		protected void fillStateContainer(StateContainer.Builder<Block, BlockState> builder) {
			builder.add(FACING);
		}

		@Override
		public BlockState getStateForPlacement(BlockItemUseContext context) {
			return this.getDefaultState().with(FACING, context.getPlacementHorizontalFacing().getOpposite());
		}

		public BlockState rotate(BlockState state, Rotation rot) {
			return state.with(FACING, rot.rotate(state.get(FACING)));
		}

		public BlockState mirror(BlockState state, Mirror mirrorIn) {
			return state.rotate(mirrorIn.toRotation(state.get(FACING)));
		}

		@Override
		public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
			List<ItemStack> dropsOriginal = super.getDrops(state, builder);
			if (!dropsOriginal.isEmpty())
				return dropsOriginal;
			return Collections.singletonList(new ItemStack(AluminiumCrateOpenedBlock.block));
		}
	}
}
