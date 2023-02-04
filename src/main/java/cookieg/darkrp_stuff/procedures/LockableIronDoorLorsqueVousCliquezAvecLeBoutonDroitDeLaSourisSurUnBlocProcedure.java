package cookieg.darkrp_stuff.procedures;

import net.minecraftforge.registries.ForgeRegistries;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.GameType;
import net.minecraft.util.math.RayTraceContext;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Direction;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.state.EnumProperty;
import net.minecraft.state.DirectionProperty;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import java.util.Random;
import java.util.Map;

import cookieg.darkrp_stuff.block.LockableIronDoorTopClosedBlock;
import cookieg.darkrp_stuff.block.LockableIronDoorBottomClosedBlock;
import cookieg.darkrp_stuff.block.AltLockableIronDoorTopClosedBlock;
import cookieg.darkrp_stuff.block.AltLockableIronDoorBottomClosedBlock;
import cookieg.darkrp_stuff.DarkrpStuffMod;

public class LockableIronDoorLorsqueVousCliquezAvecLeBoutonDroitDeLaSourisSurUnBlocProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				DarkrpStuffMod.LOGGER.warn(
						"Failed to load dependency world for procedure LockableIronDoorLorsqueVousCliquezAvecLeBoutonDroitDeLaSourisSurUnBloc!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				DarkrpStuffMod.LOGGER.warn(
						"Failed to load dependency entity for procedure LockableIronDoorLorsqueVousCliquezAvecLeBoutonDroitDeLaSourisSurUnBloc!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				DarkrpStuffMod.LOGGER.warn(
						"Failed to load dependency itemstack for procedure LockableIronDoorLorsqueVousCliquezAvecLeBoutonDroitDeLaSourisSurUnBloc!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		double X = 0;
		double Y = 0;
		double Z = 0;
		X = (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
				entity.getEyePosition(1f).add(entity.getLook(1f).x * 5, entity.getLook(1f).y * 5, entity.getLook(1f).z * 5),
				RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getX());
		Y = (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
				entity.getEyePosition(1f).add(entity.getLook(1f).x * 5, entity.getLook(1f).y * 5, entity.getLook(1f).z * 5),
				RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getY());
		Z = (entity.world.rayTraceBlocks(new RayTraceContext(entity.getEyePosition(1f),
				entity.getEyePosition(1f).add(entity.getLook(1f).x * 5, entity.getLook(1f).y * 5, entity.getLook(1f).z * 5),
				RayTraceContext.BlockMode.OUTLINE, RayTraceContext.FluidMode.NONE, entity)).getPos().getZ());
		if (Blocks.IRON_DOOR.getDefaultState().isValidPosition(world, new BlockPos(X, Y + 1, Z))
				&& (world.getBlockState(new BlockPos(X, Y + 1, Z))) == Blocks.AIR.getDefaultState()
				&& (world.getBlockState(new BlockPos(X, Y + 2, Z))) == Blocks.AIR.getDefaultState()) {
			if (entity.isSneaking()) {
				world.setBlockState(new BlockPos(X, Y + 1, Z), LockableIronDoorBottomClosedBlock.block.getDefaultState(), 3);
				world.setBlockState(new BlockPos(X, Y + 2, Z), LockableIronDoorTopClosedBlock.block.getDefaultState(), 3);
				try {
					BlockState _bs = world.getBlockState(new BlockPos(X, Y + 1, Z));
					DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
					if (_property != null) {
						world.setBlockState(new BlockPos(X, Y + 1, Z), _bs.with(_property, ((entity.getHorizontalFacing()).getOpposite())), 3);
					} else {
						world.setBlockState(new BlockPos(X, Y + 1, Z),
								_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
										((entity.getHorizontalFacing()).getOpposite()).getAxis()),
								3);
					}
				} catch (Exception e) {
				}
				try {
					BlockState _bs = world.getBlockState(new BlockPos(X, Y + 2, Z));
					DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
					if (_property != null) {
						world.setBlockState(new BlockPos(X, Y + 2, Z), _bs.with(_property, ((entity.getHorizontalFacing()).getOpposite())), 3);
					} else {
						world.setBlockState(new BlockPos(X, Y + 2, Z),
								_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
										((entity.getHorizontalFacing()).getOpposite()).getAxis()),
								3);
					}
				} catch (Exception e) {
				}
			} else {
				world.setBlockState(new BlockPos(X, Y + 1, Z), AltLockableIronDoorBottomClosedBlock.block.getDefaultState(), 3);
				world.setBlockState(new BlockPos(X, Y + 2, Z), AltLockableIronDoorTopClosedBlock.block.getDefaultState(), 3);
				try {
					BlockState _bs = world.getBlockState(new BlockPos(X, Y + 1, Z));
					DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
					if (_property != null) {
						world.setBlockState(new BlockPos(X, Y + 1, Z), _bs.with(_property, ((entity.getHorizontalFacing()).getOpposite())), 3);
					} else {
						world.setBlockState(new BlockPos(X, Y + 1, Z),
								_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
										((entity.getHorizontalFacing()).getOpposite()).getAxis()),
								3);
					}
				} catch (Exception e) {
				}
				try {
					BlockState _bs = world.getBlockState(new BlockPos(X, Y + 2, Z));
					DirectionProperty _property = (DirectionProperty) _bs.getBlock().getStateContainer().getProperty("facing");
					if (_property != null) {
						world.setBlockState(new BlockPos(X, Y + 2, Z), _bs.with(_property, ((entity.getHorizontalFacing()).getOpposite())), 3);
					} else {
						world.setBlockState(new BlockPos(X, Y + 2, Z),
								_bs.with((EnumProperty<Direction.Axis>) _bs.getBlock().getStateContainer().getProperty("axis"),
										((entity.getHorizontalFacing()).getOpposite()).getAxis()),
								3);
					}
				} catch (Exception e) {
				}
			}
			if (world instanceof World && !world.isRemote()) {
				((World) world).playSound(null, new BlockPos(X, Y + 1, Z),
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.metal.place")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1);
			} else {
				((World) world).playSound(X, (Y + 1), Z,
						(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS.getValue(new ResourceLocation("block.metal.place")),
						SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
			}
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos(X, Y + 1, Z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putString("Prori\u00E9taire", (entity.getDisplayName().getString()));
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos(X, Y + 2, Z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putString("Prori\u00E9taire", (entity.getDisplayName().getString()));
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos(X, Y + 1, Z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putDouble("OpenPlacement", (MathHelper.nextInt(new Random(), -5, 6)));
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos(X, Y + 2, Z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putDouble("OpenPlacement", (new Object() {
						public double getValue(IWorld world, BlockPos pos, String tag) {
							TileEntity tileEntity = world.getTileEntity(pos);
							if (tileEntity != null)
								return tileEntity.getTileData().getDouble(tag);
							return -1;
						}
					}.getValue(world, new BlockPos(X, Y + 1, Z), "OpenPlacement")));
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos(X, Y + 1, Z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putDouble("Level", 0);
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			if (!world.isRemote()) {
				BlockPos _bp = new BlockPos(X, Y + 2, Z);
				TileEntity _tileEntity = world.getTileEntity(_bp);
				BlockState _bs = world.getBlockState(_bp);
				if (_tileEntity != null)
					_tileEntity.getTileData().putDouble("Level", 0);
				if (world instanceof World)
					((World) world).notifyBlockUpdate(_bp, _bs, _bs, 3);
			}
			if (!(new Object() {
				public boolean checkGamemode(Entity _ent) {
					if (_ent instanceof ServerPlayerEntity) {
						return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
					} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
						NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
								.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
						return _npi != null && _npi.getGameType() == GameType.CREATIVE;
					}
					return false;
				}
			}.checkGamemode(entity))) {
				(itemstack).setCount((int) ((itemstack).getCount() - 1));
			}
		}
	}
}
