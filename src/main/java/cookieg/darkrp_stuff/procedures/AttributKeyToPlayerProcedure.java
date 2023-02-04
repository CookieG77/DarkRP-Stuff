package cookieg.darkrp_stuff.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;

import java.util.Map;

import cookieg.darkrp_stuff.DarkrpStuffMod;

public class AttributKeyToPlayerProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency entity for procedure AttributKeyToPlayer!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency itemstack for procedure AttributKeyToPlayer!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if ((itemstack.getOrCreateTag().getString("Prori\u00E9taire")).equals("")) {
			itemstack.getOrCreateTag().putString("Prori\u00E9taire", (entity.getDisplayName().getString()));
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Cl\u00E9 attribuer !"), (true));
			}
		}
	}
}
