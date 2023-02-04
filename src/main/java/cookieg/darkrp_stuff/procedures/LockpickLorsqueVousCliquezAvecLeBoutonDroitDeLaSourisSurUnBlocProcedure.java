package cookieg.darkrp_stuff.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;

import java.util.Map;

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

public class LockpickLorsqueVousCliquezAvecLeBoutonDroitDeLaSourisSurUnBlocProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				DarkrpStuffMod.LOGGER
						.warn("Failed to load dependency world for procedure LockpickLorsqueVousCliquezAvecLeBoutonDroitDeLaSourisSurUnBloc!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				DarkrpStuffMod.LOGGER
						.warn("Failed to load dependency x for procedure LockpickLorsqueVousCliquezAvecLeBoutonDroitDeLaSourisSurUnBloc!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				DarkrpStuffMod.LOGGER
						.warn("Failed to load dependency y for procedure LockpickLorsqueVousCliquezAvecLeBoutonDroitDeLaSourisSurUnBloc!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				DarkrpStuffMod.LOGGER
						.warn("Failed to load dependency z for procedure LockpickLorsqueVousCliquezAvecLeBoutonDroitDeLaSourisSurUnBloc!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				DarkrpStuffMod.LOGGER
						.warn("Failed to load dependency entity for procedure LockpickLorsqueVousCliquezAvecLeBoutonDroitDeLaSourisSurUnBloc!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				DarkrpStuffMod.LOGGER
						.warn("Failed to load dependency itemstack for procedure LockpickLorsqueVousCliquezAvecLeBoutonDroitDeLaSourisSurUnBloc!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorBottomClosedBlock.block
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorTopClosedBlock.block
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorBottomOpenBlock.block
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorTopOpenBlock.block
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorBottomClosedBlock.block
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorTopClosedBlock.block
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorBottomOpenBlock.block
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorTopOpenBlock.block) {
			if ((entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new DarkrpStuffModVariables.PlayerVariables())).Lockpicking) {
				{
					boolean _setval = (false);
					entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.Lockpicking = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			} else {
				{
					boolean _setval = (true);
					entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.Lockpicking = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					boolean _setval = (false);
					entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.TurningLock = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					double _setval = 0;
					entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.Lockpick_position = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				{
					double _setval = 0;
					entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.AnimationGUI = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				itemstack.getOrCreateTag().putDouble("X", x);
				itemstack.getOrCreateTag().putDouble("Y", y);
				itemstack.getOrCreateTag().putDouble("Z", z);
			}
		}
	}
}
