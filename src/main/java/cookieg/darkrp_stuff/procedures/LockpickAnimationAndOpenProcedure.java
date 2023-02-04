package cookieg.darkrp_stuff.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Hand;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.state.Property;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;
import net.minecraft.block.BlockState;

import java.util.Map;
import java.util.HashMap;

import cookieg.darkrp_stuff.block.LockableIronDoorTopOpenBlock;
import cookieg.darkrp_stuff.block.LockableIronDoorTopClosedBlock;
import cookieg.darkrp_stuff.block.LockableIronDoorBottomOpenBlock;
import cookieg.darkrp_stuff.block.LockableIronDoorBottomClosedBlock;
import cookieg.darkrp_stuff.block.AltLockableIronDoorTopOpenBlock;
import cookieg.darkrp_stuff.block.AltLockableIronDoorTopClosedBlock;
import cookieg.darkrp_stuff.block.AltLockableIronDoorBottomOpenBlock;
import cookieg.darkrp_stuff.block.AltLockableIronDoorBottomClosedBlock;
import cookieg.darkrp_stuff.DarkrpStuffModVariables;
import cookieg.darkrp_stuff.DarkrpStuffMod;

public class LockpickAnimationAndOpenProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onPlayerTick(TickEvent.PlayerTickEvent event) {
			if (event.phase == TickEvent.Phase.END) {
				Entity entity = event.player;
				World world = entity.world;
				double i = entity.getPosX();
				double j = entity.getPosY();
				double k = entity.getPosZ();
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("x", i);
				dependencies.put("y", j);
				dependencies.put("z", k);
				dependencies.put("world", world);
				dependencies.put("entity", entity);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency world for procedure LockpickAnimationAndOpen!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency x for procedure LockpickAnimationAndOpen!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency y for procedure LockpickAnimationAndOpen!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency z for procedure LockpickAnimationAndOpen!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency entity for procedure LockpickAnimationAndOpen!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		double nb = 0;
		double X = 0;
		double Y = 0;
		double Z = 0;
		if ((DarkrpStuffModVariables.MapVariables.get(world).modtick == 10 || DarkrpStuffModVariables.MapVariables.get(world).modtick == 0)
				&& (entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new DarkrpStuffModVariables.PlayerVariables())).Lockpicking) {
			X = (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
					.getDouble("X"));
			Y = (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
					.getDouble("Y"));
			Z = (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
					.getDouble("Z"));
			nb = (new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos(X, Y, Z), "OpenPlacement"));
			nb = (6 - Math.abs((nb + 6) - ((entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new DarkrpStuffModVariables.PlayerVariables())).Lockpick_position + 6)) / 2);
			if ((entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new DarkrpStuffModVariables.PlayerVariables())).TurningLock) {
				if (nb >= 4.5 && new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos(X, Y, Z), "Level") == 0 || nb >= 5 && new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos(X, Y, Z), "Level") == 1 || nb >= 5.5 && new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos(X, Y, Z), "Level") == 2 || nb >= 6 && new Object() {
					public double getValue(IWorld world, BlockPos pos, String tag) {
						TileEntity tileEntity = world.getTileEntity(pos);
						if (tileEntity != null)
							return tileEntity.getTileData().getDouble(tag);
						return -1;
					}
				}.getValue(world, new BlockPos(X, Y, Z), "Level") == 3) {
					if ((entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new DarkrpStuffModVariables.PlayerVariables())).LockAnimationGui < 6) {
						{
							double _setval = ((entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new DarkrpStuffModVariables.PlayerVariables())).LockAnimationGui + 1);
							entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.LockAnimationGui = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
					} else {
						{
							boolean _setval = (false);
							entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.TurningLock = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							boolean _setval = (false);
							entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.Lockpicking = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						{
							double _setval = 0;
							entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.LockAnimationGui = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						if ((world.getBlockState(new BlockPos(X, Y, Z))).getBlock() == LockableIronDoorTopClosedBlock.block) {
							{
								BlockPos _bp = new BlockPos(X, Y - 1, Z);
								BlockState _bs = LockableIronDoorBottomOpenBlock.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
									Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
									if (_property != null && _bs.get(_property) != null)
										try {
											_bs = _bs.with(_property, (Comparable) entry.getValue());
										} catch (Exception e) {
										}
								}
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
							{
								BlockPos _bp = new BlockPos(X, Y, Z);
								BlockState _bs = LockableIronDoorTopOpenBlock.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
									Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
									if (_property != null && _bs.get(_property) != null)
										try {
											_bs = _bs.with(_property, (Comparable) entry.getValue());
										} catch (Exception e) {
										}
								}
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
							if (world instanceof World && !world.isRemote()) {
								((World) world).playSound(null, new BlockPos(X, Y, Z),
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("block.iron_door.close")),
										SoundCategory.NEUTRAL, (float) 1, (float) 1);
							} else {
								((World) world).playSound(X, Y, Z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("block.iron_door.close")),
										SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
							}
						} else if ((world.getBlockState(new BlockPos(X, Y, Z))).getBlock() == LockableIronDoorBottomClosedBlock.block) {
							{
								BlockPos _bp = new BlockPos(X, Y + 1, Z);
								BlockState _bs = LockableIronDoorTopOpenBlock.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
									Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
									if (_property != null && _bs.get(_property) != null)
										try {
											_bs = _bs.with(_property, (Comparable) entry.getValue());
										} catch (Exception e) {
										}
								}
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
							{
								BlockPos _bp = new BlockPos(X, Y, Z);
								BlockState _bs = LockableIronDoorBottomOpenBlock.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
									Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
									if (_property != null && _bs.get(_property) != null)
										try {
											_bs = _bs.with(_property, (Comparable) entry.getValue());
										} catch (Exception e) {
										}
								}
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
							if (world instanceof World && !world.isRemote()) {
								((World) world).playSound(null, new BlockPos(X, Y, Z),
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("block.iron_door.close")),
										SoundCategory.NEUTRAL, (float) 1, (float) 1);
							} else {
								((World) world).playSound(X, Y, Z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("block.iron_door.close")),
										SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
							}
						} else if ((world.getBlockState(new BlockPos(X, Y, Z))).getBlock() == AltLockableIronDoorTopClosedBlock.block) {
							{
								BlockPos _bp = new BlockPos(X, Y - 1, Z);
								BlockState _bs = AltLockableIronDoorBottomOpenBlock.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
									Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
									if (_property != null && _bs.get(_property) != null)
										try {
											_bs = _bs.with(_property, (Comparable) entry.getValue());
										} catch (Exception e) {
										}
								}
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
							{
								BlockPos _bp = new BlockPos(X, Y, Z);
								BlockState _bs = AltLockableIronDoorTopOpenBlock.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
									Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
									if (_property != null && _bs.get(_property) != null)
										try {
											_bs = _bs.with(_property, (Comparable) entry.getValue());
										} catch (Exception e) {
										}
								}
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
							if (world instanceof World && !world.isRemote()) {
								((World) world)
										.playSound(null, new BlockPos(X, Y, Z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.open")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1);
							} else {
								((World) world).playSound(X, Y, Z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("block.iron_door.open")),
										SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
							}
						} else if ((world.getBlockState(new BlockPos(X, Y, Z))).getBlock() == AltLockableIronDoorBottomClosedBlock.block) {
							{
								BlockPos _bp = new BlockPos(X, Y + 1, Z);
								BlockState _bs = AltLockableIronDoorTopOpenBlock.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
									Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
									if (_property != null && _bs.get(_property) != null)
										try {
											_bs = _bs.with(_property, (Comparable) entry.getValue());
										} catch (Exception e) {
										}
								}
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
							{
								BlockPos _bp = new BlockPos(X, Y, Z);
								BlockState _bs = AltLockableIronDoorBottomOpenBlock.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
									Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
									if (_property != null && _bs.get(_property) != null)
										try {
											_bs = _bs.with(_property, (Comparable) entry.getValue());
										} catch (Exception e) {
										}
								}
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
							if (world instanceof World && !world.isRemote()) {
								((World) world)
										.playSound(null, new BlockPos(X, Y, Z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.open")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1);
							} else {
								((World) world).playSound(X, Y, Z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("block.iron_door.open")),
										SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
							}
						} else if ((world.getBlockState(new BlockPos(X, Y, Z))).getBlock() == LockableIronDoorTopOpenBlock.block) {
							{
								BlockPos _bp = new BlockPos(X, Y - 1, Z);
								BlockState _bs = LockableIronDoorBottomClosedBlock.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
									Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
									if (_property != null && _bs.get(_property) != null)
										try {
											_bs = _bs.with(_property, (Comparable) entry.getValue());
										} catch (Exception e) {
										}
								}
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
							{
								BlockPos _bp = new BlockPos(X, Y, Z);
								BlockState _bs = LockableIronDoorTopClosedBlock.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
									Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
									if (_property != null && _bs.get(_property) != null)
										try {
											_bs = _bs.with(_property, (Comparable) entry.getValue());
										} catch (Exception e) {
										}
								}
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
							if (world instanceof World && !world.isRemote()) {
								((World) world).playSound(null, new BlockPos(X, Y, Z),
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("block.iron_door.close")),
										SoundCategory.NEUTRAL, (float) 1, (float) 1);
							} else {
								((World) world).playSound(X, Y, Z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("block.iron_door.close")),
										SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
							}
						} else if ((world.getBlockState(new BlockPos(X, Y, Z))).getBlock() == LockableIronDoorBottomOpenBlock.block) {
							{
								BlockPos _bp = new BlockPos(X, Y + 1, Z);
								BlockState _bs = LockableIronDoorTopClosedBlock.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
									Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
									if (_property != null && _bs.get(_property) != null)
										try {
											_bs = _bs.with(_property, (Comparable) entry.getValue());
										} catch (Exception e) {
										}
								}
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
							{
								BlockPos _bp = new BlockPos(X, Y, Z);
								BlockState _bs = LockableIronDoorBottomClosedBlock.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
									Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
									if (_property != null && _bs.get(_property) != null)
										try {
											_bs = _bs.with(_property, (Comparable) entry.getValue());
										} catch (Exception e) {
										}
								}
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
							if (world instanceof World && !world.isRemote()) {
								((World) world).playSound(null, new BlockPos(X, Y, Z),
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("block.iron_door.close")),
										SoundCategory.NEUTRAL, (float) 1, (float) 1);
							} else {
								((World) world).playSound(X, Y, Z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("block.iron_door.close")),
										SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
							}
						} else if ((world.getBlockState(new BlockPos(X, Y, Z))).getBlock() == AltLockableIronDoorTopOpenBlock.block) {
							{
								BlockPos _bp = new BlockPos(X, Y - 1, Z);
								BlockState _bs = AltLockableIronDoorBottomClosedBlock.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
									Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
									if (_property != null && _bs.get(_property) != null)
										try {
											_bs = _bs.with(_property, (Comparable) entry.getValue());
										} catch (Exception e) {
										}
								}
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
							{
								BlockPos _bp = new BlockPos(X, Y, Z);
								BlockState _bs = AltLockableIronDoorTopClosedBlock.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
									Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
									if (_property != null && _bs.get(_property) != null)
										try {
											_bs = _bs.with(_property, (Comparable) entry.getValue());
										} catch (Exception e) {
										}
								}
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
							if (world instanceof World && !world.isRemote()) {
								((World) world)
										.playSound(null, new BlockPos(X, Y, Z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.open")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1);
							} else {
								((World) world).playSound(X, Y, Z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("block.iron_door.open")),
										SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
							}
						} else if ((world.getBlockState(new BlockPos(X, Y, Z))).getBlock() == AltLockableIronDoorBottomOpenBlock.block) {
							{
								BlockPos _bp = new BlockPos(X, Y + 1, Z);
								BlockState _bs = AltLockableIronDoorTopClosedBlock.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
									Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
									if (_property != null && _bs.get(_property) != null)
										try {
											_bs = _bs.with(_property, (Comparable) entry.getValue());
										} catch (Exception e) {
										}
								}
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
							{
								BlockPos _bp = new BlockPos(X, Y, Z);
								BlockState _bs = AltLockableIronDoorBottomClosedBlock.block.getDefaultState();
								BlockState _bso = world.getBlockState(_bp);
								for (Map.Entry<Property<?>, Comparable<?>> entry : _bso.getValues().entrySet()) {
									Property _property = _bs.getBlock().getStateContainer().getProperty(entry.getKey().getName());
									if (_property != null && _bs.get(_property) != null)
										try {
											_bs = _bs.with(_property, (Comparable) entry.getValue());
										} catch (Exception e) {
										}
								}
								TileEntity _te = world.getTileEntity(_bp);
								CompoundNBT _bnbt = null;
								if (_te != null) {
									_bnbt = _te.write(new CompoundNBT());
									_te.remove();
								}
								world.setBlockState(_bp, _bs, 3);
								if (_bnbt != null) {
									_te = world.getTileEntity(_bp);
									if (_te != null) {
										try {
											_te.read(_bso, _bnbt);
										} catch (Exception ignored) {
										}
									}
								}
							}
							if (world instanceof World && !world.isRemote()) {
								((World) world)
										.playSound(null, new BlockPos(X, Y, Z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.open")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1);
							} else {
								((World) world).playSound(X, Y, Z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("block.iron_door.open")),
										SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
							}
							if (world instanceof World && !world.isRemote()) {
								((World) world).playSound(null, new BlockPos(x, y, z),
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("darkrp_stuff:lockpick_open")),
										SoundCategory.PLAYERS, (float) 1, (float) 1);
							} else {
								((World) world).playSound(x, y, z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("darkrp_stuff:lockpick_open")),
										SoundCategory.PLAYERS, (float) 1, (float) 1, false);
							}
						}
					}
				} else {
					if ((entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new DarkrpStuffModVariables.PlayerVariables())).LockAnimationGui < nb) {
						{
							double _setval = ((entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
									.orElse(new DarkrpStuffModVariables.PlayerVariables())).LockAnimationGui + 1);
							entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.LockAnimationGui = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
					} else {
						{
							boolean _setval = (false);
							entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
								capability.TurningLock = _setval;
								capability.syncPlayerVariables(entity);
							});
						}
						if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
								.getDouble("FakeDurability") >= 5) {
							if (entity instanceof LivingEntity) {
								ItemStack _setstack = new ItemStack(Blocks.AIR);
								_setstack.setCount((int) 1);
								((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
								if (entity instanceof ServerPlayerEntity)
									((ServerPlayerEntity) entity).inventory.markDirty();
							}
							{
								boolean _setval = (false);
								entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.TurningLock = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
							{
								boolean _setval = (false);
								entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.Lockpicking = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
							{
								double _setval = 0;
								entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.LockAnimationGui = _setval;
									capability.syncPlayerVariables(entity);
								});
							}
							if (world instanceof World && !world.isRemote()) {
								((World) world).playSound(null, new BlockPos(x, y, z),
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("darkrp_stuff:lockpick_break")),
										SoundCategory.PLAYERS, (float) 1, (float) 1);
							} else {
								((World) world).playSound(x, y, z,
										(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
												.getValue(new ResourceLocation("darkrp_stuff:lockpick_break")),
										SoundCategory.PLAYERS, (float) 1, (float) 1, false);
							}
						} else {
							if (new Object() {
								public double getValue(IWorld world, BlockPos pos, String tag) {
									TileEntity tileEntity = world.getTileEntity(pos);
									if (tileEntity != null)
										return tileEntity.getTileData().getDouble(tag);
									return -1;
								}
							}.getValue(world, new BlockPos(X, Y, Z), "Level") >= 2) {
								((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
										.putDouble("FakeDurability",
												(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
														.getOrCreateTag().getDouble("FakeDurability") + 2));
								if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
										.getOrCreateTag().getDouble("FakeDurability") >= 5) {
									if (entity instanceof LivingEntity) {
										ItemStack _setstack = new ItemStack(Blocks.AIR);
										_setstack.setCount((int) 1);
										((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
										if (entity instanceof ServerPlayerEntity)
											((ServerPlayerEntity) entity).inventory.markDirty();
									}
									{
										boolean _setval = (false);
										entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.TurningLock = _setval;
											capability.syncPlayerVariables(entity);
										});
									}
									{
										boolean _setval = (false);
										entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.Lockpicking = _setval;
											capability.syncPlayerVariables(entity);
										});
									}
									{
										double _setval = 0;
										entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
											capability.LockAnimationGui = _setval;
											capability.syncPlayerVariables(entity);
										});
									}
									if (world instanceof World && !world.isRemote()) {
										((World) world).playSound(null, new BlockPos(x, y, z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("darkrp_stuff:lockpick_break")),
												SoundCategory.PLAYERS, (float) 1, (float) 1);
									} else {
										((World) world).playSound(x, y, z,
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("darkrp_stuff:lockpick_break")),
												SoundCategory.PLAYERS, (float) 1, (float) 1, false);
									}
								}
							} else {
								((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
										.putDouble("FakeDurability",
												(((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
														.getOrCreateTag().getDouble("FakeDurability") + 1));
							}
						}
					}
				}
			} else if (!(entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new DarkrpStuffModVariables.PlayerVariables())).TurningLock) {
				if ((entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new DarkrpStuffModVariables.PlayerVariables())).LockAnimationGui > 0) {
					{
						double _setval = ((entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new DarkrpStuffModVariables.PlayerVariables())).LockAnimationGui - 1);
						entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
							capability.LockAnimationGui = _setval;
							capability.syncPlayerVariables(entity);
						});
					}
				}
			}
		}
	}
}
