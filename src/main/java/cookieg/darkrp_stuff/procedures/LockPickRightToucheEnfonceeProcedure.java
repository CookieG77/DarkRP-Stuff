package cookieg.darkrp_stuff.procedures;

import net.minecraft.entity.Entity;

import java.util.Map;

import cookieg.darkrp_stuff.DarkrpStuffModVariables;
import cookieg.darkrp_stuff.DarkrpStuffMod;

public class LockPickRightToucheEnfonceeProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency entity for procedure LockPickRightToucheEnfoncee!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if ((entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new DarkrpStuffModVariables.PlayerVariables())).Lockpicking
				&& (entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new DarkrpStuffModVariables.PlayerVariables())).Lockpick_position < 6) {
			{
				double _setval = ((entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new DarkrpStuffModVariables.PlayerVariables())).Lockpick_position + 1);
				entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Lockpick_position = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
		}
	}
}
