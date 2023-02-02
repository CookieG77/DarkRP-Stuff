package cookieg.darkrp_stuff.procedures;

import net.minecraftforge.fml.server.ServerLifecycleHooks;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ChatType;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.Util;
import net.minecraft.server.MinecraftServer;
import net.minecraft.entity.Entity;
import net.minecraft.block.Blocks;

import java.util.Map;

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
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == Blocks.IRON_DOOR) {
			if (!world.isRemote()) {
				MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
				if (mcserv != null)
					mcserv.getPlayerList().func_232641_a_(new StringTextComponent("Test"), ChatType.SYSTEM, Util.DUMMY_UUID);
			}
			if ((entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new DarkrpStuffModVariables.PlayerVariables())).Lockpicking) {
				{
					boolean _setval = (false);
					entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.Lockpicking = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
				if (!world.isRemote()) {
					MinecraftServer mcserv = ServerLifecycleHooks.getCurrentServer();
					if (mcserv != null)
						mcserv.getPlayerList().func_232641_a_(
								new StringTextComponent(("R\u00E9sultat : "
										+ Math.round((entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
												.orElse(new DarkrpStuffModVariables.PlayerVariables())).Lockpick_position))),
								ChatType.SYSTEM, Util.DUMMY_UUID);
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
					double _setval = 0;
					entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.Lockpick_position = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
	}
}
