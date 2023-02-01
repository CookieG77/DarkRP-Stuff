package cookieg.darkrp_stuff.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.TickEvent;

import net.minecraft.world.IWorld;

import java.util.Map;
import java.util.HashMap;

import cookieg.darkrp_stuff.DarkrpStuffModVariables;
import cookieg.darkrp_stuff.DarkrpStuffMod;

public class PrinterCount2Procedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@SubscribeEvent
		public static void onWorldTick(TickEvent.WorldTickEvent event) {
			if (event.phase == TickEvent.Phase.END) {
				IWorld world = event.world;
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("world", world);
				dependencies.put("event", event);
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency world for procedure PrinterCount2!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		if (DarkrpStuffModVariables.MapVariables.get(world).modtick >= 20) {
			DarkrpStuffModVariables.MapVariables.get(world).printer2 = (DarkrpStuffModVariables.MapVariables.get(world).printer2 + 1);
			DarkrpStuffModVariables.MapVariables.get(world).syncData(world);
		} else if (DarkrpStuffModVariables.MapVariables.get(world).printer2 >= 300) {
			DarkrpStuffModVariables.MapVariables.get(world).printer2 = 0;
			DarkrpStuffModVariables.MapVariables.get(world).syncData(world);
		}
	}
}
