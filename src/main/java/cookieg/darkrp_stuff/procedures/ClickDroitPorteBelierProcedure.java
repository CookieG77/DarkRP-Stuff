package cookieg.darkrp_stuff.procedures;

import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.common.MinecraftForge;

import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.SoundCategory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.state.Property;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.block.BlockState;

import java.util.Random;
import java.util.Map;

import cookieg.darkrp_stuff.block.LockableIronDoorTopOpenBlock;
import cookieg.darkrp_stuff.block.LockableIronDoorTopClosedBlock;
import cookieg.darkrp_stuff.block.LockableIronDoorBottomOpenBlock;
import cookieg.darkrp_stuff.block.LockableIronDoorBottomClosedBlock;
import cookieg.darkrp_stuff.block.AltLockableIronDoorTopOpenBlock;
import cookieg.darkrp_stuff.block.AltLockableIronDoorTopClosedBlock;
import cookieg.darkrp_stuff.block.AltLockableIronDoorBottomOpenBlock;
import cookieg.darkrp_stuff.block.AltLockableIronDoorBottomClosedBlock;
import cookieg.darkrp_stuff.DarkrpStuffMod;

public class ClickDroitPorteBelierProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency world for procedure ClickDroitPorteBelier!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency x for procedure ClickDroitPorteBelier!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency y for procedure ClickDroitPorteBelier!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency z for procedure ClickDroitPorteBelier!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency entity for procedure ClickDroitPorteBelier!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency itemstack for procedure ClickDroitPorteBelier!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		boolean opening = false;
		if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorTopClosedBlock.block
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorBottomClosedBlock.block
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorTopClosedBlock.block
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorBottomClosedBlock.block
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorTopOpenBlock.block
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorBottomOpenBlock.block
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorTopOpenBlock.block
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorBottomOpenBlock.block) {
			itemstack.getOrCreateTag().putBoolean("Opening", (true));
			itemstack.getOrCreateTag().putDouble("X", x);
			itemstack.getOrCreateTag().putDouble("Y", y);
			itemstack.getOrCreateTag().putDouble("Z", z);
			if (new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos(x, y, z), "Level") == 0) {
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private IWorld world;

					public void start(IWorld world, int waitTicks) {
						this.waitTicks = waitTicks;
						MinecraftForge.EVENT_BUS.register(this);
						this.world = world;
					}

					@SubscribeEvent
					public void tick(TickEvent.ServerTickEvent event) {
						if (event.phase == TickEvent.Phase.END) {
							this.ticks += 1;
							if (this.ticks >= this.waitTicks)
								run();
						}
					}

					private void run() {
						if (itemstack.getOrCreateTag().getBoolean("Opening")) {
							if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
								((PlayerEntity) entity).sendStatusMessage(
										new StringTextComponent("\u00A77\u00A7l[\u00A74\u2588\u00A78\u2592\u2592\u00A77]"), (true));
							}
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 20);
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private IWorld world;

					public void start(IWorld world, int waitTicks) {
						this.waitTicks = waitTicks;
						MinecraftForge.EVENT_BUS.register(this);
						this.world = world;
					}

					@SubscribeEvent
					public void tick(TickEvent.ServerTickEvent event) {
						if (event.phase == TickEvent.Phase.END) {
							this.ticks += 1;
							if (this.ticks >= this.waitTicks)
								run();
						}
					}

					private void run() {
						if (itemstack.getOrCreateTag().getBoolean("Opening")) {
							if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
								((PlayerEntity) entity).sendStatusMessage(
										new StringTextComponent("\u00A77\u00A7l[\u00A7e\u2588\u2588\u00A78\u2592\u00A77]"), (true));
							}
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 40);
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private IWorld world;

					public void start(IWorld world, int waitTicks) {
						this.waitTicks = waitTicks;
						MinecraftForge.EVENT_BUS.register(this);
						this.world = world;
					}

					@SubscribeEvent
					public void tick(TickEvent.ServerTickEvent event) {
						if (event.phase == TickEvent.Phase.END) {
							this.ticks += 1;
							if (this.ticks >= this.waitTicks)
								run();
						}
					}

					private void run() {
						if (itemstack.getOrCreateTag().getBoolean("Opening")) {
							if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
								((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A77\u00A7l[\u00A72\u2588\u2588\u2588\u00A77]"),
										(true));
							}
							if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorTopClosedBlock.block) {
								{
									BlockPos _bp = new BlockPos(x, y - 1, z);
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
									BlockPos _bp = new BlockPos(x, y, z);
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
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.close")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1);
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.close")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
								}
							} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorBottomClosedBlock.block) {
								{
									BlockPos _bp = new BlockPos(x, y + 1, z);
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
									BlockPos _bp = new BlockPos(x, y, z);
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
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.close")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1);
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.close")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
								}
							} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorTopClosedBlock.block) {
								{
									BlockPos _bp = new BlockPos(x, y - 1, z);
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
									BlockPos _bp = new BlockPos(x, y, z);
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
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.open")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1);
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.open")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
								}
							} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorBottomClosedBlock.block) {
								{
									BlockPos _bp = new BlockPos(x, y + 1, z);
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
									BlockPos _bp = new BlockPos(x, y, z);
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
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.open")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1);
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.open")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
								}
							} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorTopOpenBlock.block) {
								{
									BlockPos _bp = new BlockPos(x, y - 1, z);
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
									BlockPos _bp = new BlockPos(x, y, z);
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
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.close")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1);
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.close")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
								}
							} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorBottomOpenBlock.block) {
								{
									BlockPos _bp = new BlockPos(x, y + 1, z);
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
									BlockPos _bp = new BlockPos(x, y, z);
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
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.close")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1);
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.close")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
								}
							} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorTopOpenBlock.block) {
								{
									BlockPos _bp = new BlockPos(x, y - 1, z);
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
									BlockPos _bp = new BlockPos(x, y, z);
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
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.open")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1);
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.open")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
								}
							} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorBottomOpenBlock.block) {
								{
									BlockPos _bp = new BlockPos(x, y + 1, z);
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
									BlockPos _bp = new BlockPos(x, y, z);
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
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.open")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1);
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.open")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
								}
							}
							itemstack.getOrCreateTag().putBoolean("Opening", (false));
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 60);
			} else if (new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos(x, y, z), "Level") == 1) {
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private IWorld world;

					public void start(IWorld world, int waitTicks) {
						this.waitTicks = waitTicks;
						MinecraftForge.EVENT_BUS.register(this);
						this.world = world;
					}

					@SubscribeEvent
					public void tick(TickEvent.ServerTickEvent event) {
						if (event.phase == TickEvent.Phase.END) {
							this.ticks += 1;
							if (this.ticks >= this.waitTicks)
								run();
						}
					}

					private void run() {
						if (itemstack.getOrCreateTag().getBoolean("Opening")) {
							if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
								((PlayerEntity) entity).sendStatusMessage(
										new StringTextComponent("\u00A77\u00A7l[\u00A74\u2588\u00A78\u2592\u2592\u2592\u00A77]"), (true));
							}
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 20);
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private IWorld world;

					public void start(IWorld world, int waitTicks) {
						this.waitTicks = waitTicks;
						MinecraftForge.EVENT_BUS.register(this);
						this.world = world;
					}

					@SubscribeEvent
					public void tick(TickEvent.ServerTickEvent event) {
						if (event.phase == TickEvent.Phase.END) {
							this.ticks += 1;
							if (this.ticks >= this.waitTicks)
								run();
						}
					}

					private void run() {
						if (itemstack.getOrCreateTag().getBoolean("Opening")) {
							if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
								((PlayerEntity) entity).sendStatusMessage(
										new StringTextComponent("\u00A77\u00A7l[\u00A76\u2588\u2588\u00A78\u2592\u2592\u00A77]"), (true));
							}
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 40);
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private IWorld world;

					public void start(IWorld world, int waitTicks) {
						this.waitTicks = waitTicks;
						MinecraftForge.EVENT_BUS.register(this);
						this.world = world;
					}

					@SubscribeEvent
					public void tick(TickEvent.ServerTickEvent event) {
						if (event.phase == TickEvent.Phase.END) {
							this.ticks += 1;
							if (this.ticks >= this.waitTicks)
								run();
						}
					}

					private void run() {
						if (itemstack.getOrCreateTag().getBoolean("Opening")) {
							if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
								((PlayerEntity) entity).sendStatusMessage(
										new StringTextComponent("\u00A77\u00A7l[\u00A7e\u2588\u2588\u2588\u00A78\u2592\u00A77]"), (true));
							}
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 60);
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private IWorld world;

					public void start(IWorld world, int waitTicks) {
						this.waitTicks = waitTicks;
						MinecraftForge.EVENT_BUS.register(this);
						this.world = world;
					}

					@SubscribeEvent
					public void tick(TickEvent.ServerTickEvent event) {
						if (event.phase == TickEvent.Phase.END) {
							this.ticks += 1;
							if (this.ticks >= this.waitTicks)
								run();
						}
					}

					private void run() {
						if (itemstack.getOrCreateTag().getBoolean("Opening")) {
							if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
								((PlayerEntity) entity)
										.sendStatusMessage(new StringTextComponent("\u00A77\u00A7l[\u00A72\u2588\u2588\u2588\u2588\u00A77]"), (true));
							}
							if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorTopClosedBlock.block) {
								{
									BlockPos _bp = new BlockPos(x, y - 1, z);
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
									BlockPos _bp = new BlockPos(x, y, z);
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
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.close")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1);
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.close")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
								}
							} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorBottomClosedBlock.block) {
								{
									BlockPos _bp = new BlockPos(x, y + 1, z);
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
									BlockPos _bp = new BlockPos(x, y, z);
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
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.close")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1);
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.close")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
								}
							} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorTopClosedBlock.block) {
								{
									BlockPos _bp = new BlockPos(x, y - 1, z);
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
									BlockPos _bp = new BlockPos(x, y, z);
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
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.open")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1);
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.open")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
								}
							} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorBottomClosedBlock.block) {
								{
									BlockPos _bp = new BlockPos(x, y + 1, z);
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
									BlockPos _bp = new BlockPos(x, y, z);
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
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.open")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1);
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.open")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
								}
							} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorTopOpenBlock.block) {
								{
									BlockPos _bp = new BlockPos(x, y - 1, z);
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
									BlockPos _bp = new BlockPos(x, y, z);
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
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.close")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1);
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.close")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
								}
							} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorBottomOpenBlock.block) {
								{
									BlockPos _bp = new BlockPos(x, y + 1, z);
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
									BlockPos _bp = new BlockPos(x, y, z);
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
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.close")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1);
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.close")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
								}
							} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorTopOpenBlock.block) {
								{
									BlockPos _bp = new BlockPos(x, y - 1, z);
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
									BlockPos _bp = new BlockPos(x, y, z);
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
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.open")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1);
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.open")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
								}
							} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorBottomOpenBlock.block) {
								{
									BlockPos _bp = new BlockPos(x, y + 1, z);
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
									BlockPos _bp = new BlockPos(x, y, z);
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
									((World) world).playSound(null, new BlockPos(x, y, z),
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.open")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1);
								} else {
									((World) world).playSound(x, y, z,
											(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
													.getValue(new ResourceLocation("block.iron_door.open")),
											SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
								}
							}
							itemstack.getOrCreateTag().putBoolean("Opening", (false));
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 80);
			} else if (new Object() {
				public double getValue(IWorld world, BlockPos pos, String tag) {
					TileEntity tileEntity = world.getTileEntity(pos);
					if (tileEntity != null)
						return tileEntity.getTileData().getDouble(tag);
					return -1;
				}
			}.getValue(world, new BlockPos(x, y, z), "Level") == 2) {
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private IWorld world;

					public void start(IWorld world, int waitTicks) {
						this.waitTicks = waitTicks;
						MinecraftForge.EVENT_BUS.register(this);
						this.world = world;
					}

					@SubscribeEvent
					public void tick(TickEvent.ServerTickEvent event) {
						if (event.phase == TickEvent.Phase.END) {
							this.ticks += 1;
							if (this.ticks >= this.waitTicks)
								run();
						}
					}

					private void run() {
						if (itemstack.getOrCreateTag().getBoolean("Opening")) {
							if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
								((PlayerEntity) entity).sendStatusMessage(
										new StringTextComponent("\u00A77\u00A7l[\u00A74\u2588\u00A78\u2592\u2592\u2592\u00A77]"), (true));
							}
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 20);
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private IWorld world;

					public void start(IWorld world, int waitTicks) {
						this.waitTicks = waitTicks;
						MinecraftForge.EVENT_BUS.register(this);
						this.world = world;
					}

					@SubscribeEvent
					public void tick(TickEvent.ServerTickEvent event) {
						if (event.phase == TickEvent.Phase.END) {
							this.ticks += 1;
							if (this.ticks >= this.waitTicks)
								run();
						}
					}

					private void run() {
						if (itemstack.getOrCreateTag().getBoolean("Opening")) {
							if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
								((PlayerEntity) entity).sendStatusMessage(
										new StringTextComponent("\u00A77\u00A7l[\u00A76\u2588\u2588\u00A78\u2592\u2592\u00A77]"), (true));
							}
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 40);
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private IWorld world;

					public void start(IWorld world, int waitTicks) {
						this.waitTicks = waitTicks;
						MinecraftForge.EVENT_BUS.register(this);
						this.world = world;
					}

					@SubscribeEvent
					public void tick(TickEvent.ServerTickEvent event) {
						if (event.phase == TickEvent.Phase.END) {
							this.ticks += 1;
							if (this.ticks >= this.waitTicks)
								run();
						}
					}

					private void run() {
						if (itemstack.getOrCreateTag().getBoolean("Opening")) {
							if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
								((PlayerEntity) entity).sendStatusMessage(
										new StringTextComponent("\u00A77\u00A7l[\u00A7e\u2588\u2588\u2588\u00A78\u2592\u00A77]"), (true));
							}
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 60);
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private IWorld world;

					public void start(IWorld world, int waitTicks) {
						this.waitTicks = waitTicks;
						MinecraftForge.EVENT_BUS.register(this);
						this.world = world;
					}

					@SubscribeEvent
					public void tick(TickEvent.ServerTickEvent event) {
						if (event.phase == TickEvent.Phase.END) {
							this.ticks += 1;
							if (this.ticks >= this.waitTicks)
								run();
						}
					}

					private void run() {
						if (itemstack.getOrCreateTag().getBoolean("Opening")) {
							if (MathHelper.nextInt(new Random(), 0, 1) == 0) {
								if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
									((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A77Echec"), (true));
								}
							} else {
								if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
									((PlayerEntity) entity).sendStatusMessage(
											new StringTextComponent("\u00A77\u00A7l[\u00A72\u2588\u2588\u2588\u2588\u00A77]"), (true));
								}
								if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorTopClosedBlock.block) {
									{
										BlockPos _bp = new BlockPos(x, y - 1, z);
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
										BlockPos _bp = new BlockPos(x, y, z);
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
										((World) world).playSound(null, new BlockPos(x, y, z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.close")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1);
									} else {
										((World) world).playSound(x, y, z,
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.close")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
									}
								} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorBottomClosedBlock.block) {
									{
										BlockPos _bp = new BlockPos(x, y + 1, z);
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
										BlockPos _bp = new BlockPos(x, y, z);
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
										((World) world).playSound(null, new BlockPos(x, y, z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.close")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1);
									} else {
										((World) world).playSound(x, y, z,
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.close")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
									}
								} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorTopClosedBlock.block) {
									{
										BlockPos _bp = new BlockPos(x, y - 1, z);
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
										BlockPos _bp = new BlockPos(x, y, z);
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
										((World) world).playSound(null, new BlockPos(x, y, z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.open")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1);
									} else {
										((World) world).playSound(x, y, z,
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.open")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
									}
								} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorBottomClosedBlock.block) {
									{
										BlockPos _bp = new BlockPos(x, y + 1, z);
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
										BlockPos _bp = new BlockPos(x, y, z);
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
										((World) world).playSound(null, new BlockPos(x, y, z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.open")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1);
									} else {
										((World) world).playSound(x, y, z,
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.open")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
									}
								} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorTopOpenBlock.block) {
									{
										BlockPos _bp = new BlockPos(x, y - 1, z);
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
										BlockPos _bp = new BlockPos(x, y, z);
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
										((World) world).playSound(null, new BlockPos(x, y, z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.close")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1);
									} else {
										((World) world).playSound(x, y, z,
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.close")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
									}
								} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorBottomOpenBlock.block) {
									{
										BlockPos _bp = new BlockPos(x, y + 1, z);
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
										BlockPos _bp = new BlockPos(x, y, z);
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
										((World) world).playSound(null, new BlockPos(x, y, z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.close")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1);
									} else {
										((World) world).playSound(x, y, z,
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.close")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
									}
								} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorTopOpenBlock.block) {
									{
										BlockPos _bp = new BlockPos(x, y - 1, z);
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
										BlockPos _bp = new BlockPos(x, y, z);
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
										((World) world).playSound(null, new BlockPos(x, y, z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.open")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1);
									} else {
										((World) world).playSound(x, y, z,
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.open")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
									}
								} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorBottomOpenBlock.block) {
									{
										BlockPos _bp = new BlockPos(x, y + 1, z);
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
										BlockPos _bp = new BlockPos(x, y, z);
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
										((World) world).playSound(null, new BlockPos(x, y, z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.open")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1);
									} else {
										((World) world).playSound(x, y, z,
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.open")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
									}
									itemstack.getOrCreateTag().putBoolean("Opening", (false));
								}
							}
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 80);
			} else {
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private IWorld world;

					public void start(IWorld world, int waitTicks) {
						this.waitTicks = waitTicks;
						MinecraftForge.EVENT_BUS.register(this);
						this.world = world;
					}

					@SubscribeEvent
					public void tick(TickEvent.ServerTickEvent event) {
						if (event.phase == TickEvent.Phase.END) {
							this.ticks += 1;
							if (this.ticks >= this.waitTicks)
								run();
						}
					}

					private void run() {
						if (itemstack.getOrCreateTag().getBoolean("Opening")) {
							if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
								((PlayerEntity) entity).sendStatusMessage(
										new StringTextComponent("\u00A77\u00A7l[\u00A74\u2588\u00A78\u2592\u2592\u2592\u2592\u00A77]"), (true));
							}
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 20);
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private IWorld world;

					public void start(IWorld world, int waitTicks) {
						this.waitTicks = waitTicks;
						MinecraftForge.EVENT_BUS.register(this);
						this.world = world;
					}

					@SubscribeEvent
					public void tick(TickEvent.ServerTickEvent event) {
						if (event.phase == TickEvent.Phase.END) {
							this.ticks += 1;
							if (this.ticks >= this.waitTicks)
								run();
						}
					}

					private void run() {
						if (itemstack.getOrCreateTag().getBoolean("Opening")) {
							if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
								((PlayerEntity) entity).sendStatusMessage(
										new StringTextComponent("\u00A77\u00A7l[\u00A76\u2588\u2588\u00A78\u2592\u2592\u2592\u00A77]"), (true));
							}
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 40);
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private IWorld world;

					public void start(IWorld world, int waitTicks) {
						this.waitTicks = waitTicks;
						MinecraftForge.EVENT_BUS.register(this);
						this.world = world;
					}

					@SubscribeEvent
					public void tick(TickEvent.ServerTickEvent event) {
						if (event.phase == TickEvent.Phase.END) {
							this.ticks += 1;
							if (this.ticks >= this.waitTicks)
								run();
						}
					}

					private void run() {
						if (itemstack.getOrCreateTag().getBoolean("Opening")) {
							if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
								((PlayerEntity) entity).sendStatusMessage(
										new StringTextComponent("\u00A77\u00A7l[\u00A7e\u2588\u2588\u2588\u00A78\u2592\u2592\u00A77]"), (true));
							}
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 60);
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private IWorld world;

					public void start(IWorld world, int waitTicks) {
						this.waitTicks = waitTicks;
						MinecraftForge.EVENT_BUS.register(this);
						this.world = world;
					}

					@SubscribeEvent
					public void tick(TickEvent.ServerTickEvent event) {
						if (event.phase == TickEvent.Phase.END) {
							this.ticks += 1;
							if (this.ticks >= this.waitTicks)
								run();
						}
					}

					private void run() {
						if (itemstack.getOrCreateTag().getBoolean("Opening")) {
							if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
								((PlayerEntity) entity).sendStatusMessage(
										new StringTextComponent("\u00A77\u00A7l[\u00A7a\u2588\u2588\u2588\u2588\u00A78\u2592\u00A77]"), (true));
							}
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 80);
				new Object() {
					private int ticks = 0;
					private float waitTicks;
					private IWorld world;

					public void start(IWorld world, int waitTicks) {
						this.waitTicks = waitTicks;
						MinecraftForge.EVENT_BUS.register(this);
						this.world = world;
					}

					@SubscribeEvent
					public void tick(TickEvent.ServerTickEvent event) {
						if (event.phase == TickEvent.Phase.END) {
							this.ticks += 1;
							if (this.ticks >= this.waitTicks)
								run();
						}
					}

					private void run() {
						if (itemstack.getOrCreateTag().getBoolean("Opening")) {
							if (!(MathHelper.nextInt(new Random(), 0, 2) == 1)) {
								if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
									((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("\u00A77Echec"), (true));
								}
							} else {
								if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
									((PlayerEntity) entity).sendStatusMessage(
											new StringTextComponent("\u00A77\u00A7l[\u00A72\u2588\u2588\u2588\u2588\u2588\u00A77]"), (true));
								}
								if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorTopClosedBlock.block) {
									{
										BlockPos _bp = new BlockPos(x, y - 1, z);
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
										BlockPos _bp = new BlockPos(x, y, z);
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
										((World) world).playSound(null, new BlockPos(x, y, z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.close")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1);
									} else {
										((World) world).playSound(x, y, z,
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.close")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
									}
								} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorBottomClosedBlock.block) {
									{
										BlockPos _bp = new BlockPos(x, y + 1, z);
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
										BlockPos _bp = new BlockPos(x, y, z);
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
										((World) world).playSound(null, new BlockPos(x, y, z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.close")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1);
									} else {
										((World) world).playSound(x, y, z,
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.close")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
									}
								} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorTopClosedBlock.block) {
									{
										BlockPos _bp = new BlockPos(x, y - 1, z);
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
										BlockPos _bp = new BlockPos(x, y, z);
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
										((World) world).playSound(null, new BlockPos(x, y, z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.open")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1);
									} else {
										((World) world).playSound(x, y, z,
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.open")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
									}
								} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorBottomClosedBlock.block) {
									{
										BlockPos _bp = new BlockPos(x, y + 1, z);
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
										BlockPos _bp = new BlockPos(x, y, z);
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
										((World) world).playSound(null, new BlockPos(x, y, z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.open")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1);
									} else {
										((World) world).playSound(x, y, z,
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.open")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
									}
								} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorTopOpenBlock.block) {
									{
										BlockPos _bp = new BlockPos(x, y - 1, z);
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
										BlockPos _bp = new BlockPos(x, y, z);
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
										((World) world).playSound(null, new BlockPos(x, y, z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.close")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1);
									} else {
										((World) world).playSound(x, y, z,
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.close")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
									}
								} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorBottomOpenBlock.block) {
									{
										BlockPos _bp = new BlockPos(x, y + 1, z);
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
										BlockPos _bp = new BlockPos(x, y, z);
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
										((World) world).playSound(null, new BlockPos(x, y, z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.close")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1);
									} else {
										((World) world).playSound(x, y, z,
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.close")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
									}
								} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorTopOpenBlock.block) {
									{
										BlockPos _bp = new BlockPos(x, y - 1, z);
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
										BlockPos _bp = new BlockPos(x, y, z);
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
										((World) world).playSound(null, new BlockPos(x, y, z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.open")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1);
									} else {
										((World) world).playSound(x, y, z,
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.open")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
									}
								} else if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorBottomOpenBlock.block) {
									{
										BlockPos _bp = new BlockPos(x, y + 1, z);
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
										BlockPos _bp = new BlockPos(x, y, z);
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
										((World) world).playSound(null, new BlockPos(x, y, z),
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.open")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1);
									} else {
										((World) world).playSound(x, y, z,
												(net.minecraft.util.SoundEvent) ForgeRegistries.SOUND_EVENTS
														.getValue(new ResourceLocation("block.iron_door.open")),
												SoundCategory.NEUTRAL, (float) 1, (float) 1, false);
									}
									itemstack.getOrCreateTag().putBoolean("Opening", (false));
								}
							}
						}
						MinecraftForge.EVENT_BUS.unregister(this);
					}
				}.start(world, (int) 100);
			}
		}
	}
}
