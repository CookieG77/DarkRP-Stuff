package cookieg.darkrp_stuff.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.state.Property;
import net.minecraft.state.IntegerProperty;
import net.minecraft.item.ItemStack;
import net.minecraft.block.BlockState;

import java.util.concurrent.atomic.AtomicReference;
import java.util.Map;

import cookieg.darkrp_stuff.item.MethPlateItem;
import cookieg.darkrp_stuff.item.MethPlateCoolingItem;
import cookieg.darkrp_stuff.DarkrpStuffModVariables;
import cookieg.darkrp_stuff.DarkrpStuffMod;

public class FreezerMiseAJourDuTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency world for procedure FreezerMiseAJourDuTick!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency x for procedure FreezerMiseAJourDuTick!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency y for procedure FreezerMiseAJourDuTick!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency z for procedure FreezerMiseAJourDuTick!");
			return;
		}
		if (dependencies.get("blockstate") == null) {
			if (!dependencies.containsKey("blockstate"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency blockstate for procedure FreezerMiseAJourDuTick!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		BlockState blockstate = (BlockState) dependencies.get("blockstate");
		ItemStack plate = ItemStack.EMPTY;
		double nbplates = 0;
		double boucleint = 0;
		nbplates = 0;
		boucleint = 0;
		for (int index0 = 0; index0 < (int) (4); index0++) {
			plate = (new Object() {
				public ItemStack getItemStack(BlockPos pos, int sltid) {
					AtomicReference<ItemStack> _retval = new AtomicReference<>(ItemStack.EMPTY);
					TileEntity _ent = world.getTileEntity(pos);
					if (_ent != null) {
						_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
							_retval.set(capability.getStackInSlot(sltid).copy());
						});
					}
					return _retval.get();
				}
			}.getItemStack(new BlockPos(x, y, z), (int) (boucleint)));
			if (DarkrpStuffModVariables.MapVariables.get(world).modtick >= 18 && (plate).getItem() == MethPlateCoolingItem.block
					&& (plate).getOrCreateTag().getDouble("Cooling") <= 599) {
				(plate).getOrCreateTag().putDouble("Cooling", ((plate).getOrCreateTag().getDouble("Cooling") + 1));
				{
					TileEntity _ent = world.getTileEntity(new BlockPos(x, y, z));
					if (_ent != null) {
						final int _sltid = (int) (boucleint);
						final ItemStack _setstack = (plate);
						_setstack.setCount((int) 1);
						_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
							if (capability instanceof IItemHandlerModifiable) {
								((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
							}
						});
					}
				}
			} else if ((plate).getItem() == MethPlateCoolingItem.block && (plate).getOrCreateTag().getDouble("Cooling") == 600) {
				{
					TileEntity _ent = world.getTileEntity(new BlockPos(x, y, z));
					if (_ent != null) {
						final int _sltid = (int) (boucleint);
						final ItemStack _setstack = new ItemStack(MethPlateItem.block);
						_setstack.setCount((int) 1);
						_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
							if (capability instanceof IItemHandlerModifiable) {
								((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
							}
						});
					}
				}
			}
			if ((plate).getItem() == MethPlateItem.block || (plate).getItem() == MethPlateCoolingItem.block) {
				nbplates = (nbplates + 1);
			}
			boucleint = (boucleint + 1);
		}
		if (nbplates != (new Object() {
			public int get(BlockState _bs, String _property) {
				Property<?> _prop = _bs.getBlock().getStateContainer().getProperty(_property);
				return _prop instanceof IntegerProperty ? _bs.get((IntegerProperty) _prop) : -1;
			}
		}.get(blockstate, "nbplates"))) {
			{
				int _value = (int) nbplates;
				BlockPos _pos = new BlockPos(x, y, z);
				BlockState _bs = world.getBlockState(_pos);
				Property<?> _property = _bs.getBlock().getStateContainer().getProperty("nbplates");
				if (_property instanceof IntegerProperty && _property.getAllowedValues().contains(_value))
					world.setBlockState(_pos, _bs.with((IntegerProperty) _property, _value), 3);
			}
		}
	}
}
