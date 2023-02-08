package cookieg.darkrp_stuff.procedures;

import net.minecraft.potion.Effects;
import net.minecraft.potion.EffectInstance;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import cookieg.darkrp_stuff.DarkrpStuffMod;

public class IfInWaterProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency entity for procedure IfInWater!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		if (entity.isInWaterOrBubbleColumn()) {
			entity.setMotion((entity.getMotion().getY()), (entity.getMotion().getY() - 7), (entity.getMotion().getZ()));
			if (entity instanceof LivingEntity)
				((LivingEntity) entity).addPotionEffect(new EffectInstance(Effects.SLOWNESS, (int) 20, (int) 1, (false), (false)));
		}
	}
}
