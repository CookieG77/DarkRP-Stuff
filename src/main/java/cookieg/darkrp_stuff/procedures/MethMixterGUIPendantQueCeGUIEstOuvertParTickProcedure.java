package cookieg.darkrp_stuff.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.entity.Entity;

import java.util.Map;

import cookieg.darkrp_stuff.DarkrpStuffModVariables;
import cookieg.darkrp_stuff.DarkrpStuffMod;

public class MethMixterGUIPendantQueCeGUIEstOuvertParTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency world for procedure MethMixterGUIPendantQueCeGUIEstOuvertParTick!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency entity for procedure MethMixterGUIPendantQueCeGUIEstOuvertParTick!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		if (Math.floor(DarkrpStuffModVariables.MapVariables.get(world).modtick) / 5 == DarkrpStuffModVariables.MapVariables.get(world).modtick / 5) {
			{
				double _setval = ((entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
						.orElse(new DarkrpStuffModVariables.PlayerVariables())).AnimationGUI + 1);
				entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.AnimationGUI = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			if ((entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
					.orElse(new DarkrpStuffModVariables.PlayerVariables())).AnimationGUI > 8) {
				{
					double _setval = 1;
					entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
						capability.AnimationGUI = _setval;
						capability.syncPlayerVariables(entity);
					});
				}
			}
		}
	}
}
