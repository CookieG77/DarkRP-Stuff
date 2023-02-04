package cookieg.darkrp_stuff.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.command.CommandSource;

import java.util.Map;

import cookieg.darkrp_stuff.item.DoorKeyItem;
import cookieg.darkrp_stuff.DarkrpStuffMod;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

public class AddPlayerToKeyProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("arguments") == null) {
			if (!dependencies.containsKey("arguments"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency arguments for procedure AddPlayerToKey!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency entity for procedure AddPlayerToKey!");
			return;
		}
		CommandContext<CommandSource> arguments = (CommandContext<CommandSource>) dependencies.get("arguments");
		Entity entity = (Entity) dependencies.get("entity");
		if (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getItem() == DoorKeyItem.block
				&& !((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
						.getString("Prori\u00E9taire")).contains((new Object() {
							public Entity getEntity() {
								try {
									return EntityArgument.getEntity(arguments, "Joueur");
								} catch (CommandSyntaxException e) {
									e.printStackTrace();
									return null;
								}
							}
						}.getEntity()).getDisplayName().getString()))
				&& (entity instanceof PlayerEntity || entity instanceof ServerPlayerEntity)
				&& (((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
						.getString("Prori\u00E9taire")).startsWith(entity.getDisplayName().getString())) {
			((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag().putString(
					"Prori\u00E9taire", ((((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY)
							.getOrCreateTag().getString("Prori\u00E9taire")) + " : " + (new Object() {
								public Entity getEntity() {
									try {
										return EntityArgument.getEntity(arguments, "Joueur");
									} catch (CommandSyntaxException e) {
										e.printStackTrace();
										return null;
									}
								}
							}.getEntity()).getDisplayName().getString()));
		}
	}
}
