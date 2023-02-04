package cookieg.darkrp_stuff.procedures;

import net.minecraft.entity.Entity;

import java.util.Map;

import cookieg.darkrp_stuff.DarkrpStuffModVariables;
import cookieg.darkrp_stuff.DarkrpStuffMod;

public class LockPickTurnLockToucheEnfonceeProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency entity for procedure LockPickTurnLockToucheEnfoncee!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		double nb = 0;
		double X = 0;
		double Y = 0;
		double Z = 0;
		if ((entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new DarkrpStuffModVariables.PlayerVariables())).Lockpicking) {
			if (!(entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new DarkrpStuffModVariables.PlayerVariables())).TurningLock) {
				{
					boolean _setval = (true);
					entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.TurningLock = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
	}
}
