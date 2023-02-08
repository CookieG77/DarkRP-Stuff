package cookieg.darkrp_stuff.procedures;

import net.minecraft.entity.Entity;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.command.CommandSource;

import java.util.Map;
import java.util.HashMap;

import cookieg.darkrp_stuff.DarkrpStuffModVariables;
import cookieg.darkrp_stuff.DarkrpStuffMod;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

public class FreezeGuyProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("arguments") == null) {
			if (!dependencies.containsKey("arguments"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency arguments for procedure FreezeGuy!");
			return;
		}
		CommandContext<CommandSource> arguments = (CommandContext<CommandSource>) dependencies.get("arguments");
		Map<String, Double> Coordonates = new HashMap<>();
		if (((new Object() {
			public Entity getEntity() {
				try {
					return EntityArgument.getEntity(arguments, "Player");
				} catch (CommandSyntaxException e) {
					e.printStackTrace();
					return null;
				}
			}
		}.getEntity()).getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new DarkrpStuffModVariables.PlayerVariables())).Freezed) {
			{
				boolean _setval = (false);
				(new Object() {
					public Entity getEntity() {
						try {
							return EntityArgument.getEntity(arguments, "Player");
						} catch (CommandSyntaxException e) {
							e.printStackTrace();
							return null;
						}
					}
				}.getEntity()).getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Freezed = _setval;
					capability.syncPlayerVariables((new Object() {
						public Entity getEntity() {
							try {
								return EntityArgument.getEntity(arguments, "Player");
							} catch (CommandSyntaxException e) {
								e.printStackTrace();
								return null;
							}
						}
					}.getEntity()));
				});
			}
		} else {
			{
				boolean _setval = (true);
				(new Object() {
					public Entity getEntity() {
						try {
							return EntityArgument.getEntity(arguments, "Player");
						} catch (CommandSyntaxException e) {
							e.printStackTrace();
							return null;
						}
					}
				}.getEntity()).getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.Freezed = _setval;
					capability.syncPlayerVariables((new Object() {
						public Entity getEntity() {
							try {
								return EntityArgument.getEntity(arguments, "Player");
							} catch (CommandSyntaxException e) {
								e.printStackTrace();
								return null;
							}
						}
					}.getEntity()));
				});
			}
		}
	}
}
