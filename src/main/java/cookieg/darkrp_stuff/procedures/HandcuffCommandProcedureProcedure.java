package cookieg.darkrp_stuff.procedures;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.command.CommandSource;

import java.util.Map;

import cookieg.darkrp_stuff.DarkrpStuffModVariables;
import cookieg.darkrp_stuff.DarkrpStuffMod;

import com.mojang.brigadier.exceptions.CommandSyntaxException;
import com.mojang.brigadier.context.CommandContext;

public class HandcuffCommandProcedureProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("arguments") == null) {
			if (!dependencies.containsKey("arguments"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency arguments for procedure HandcuffCommandProcedure!");
			return;
		}
		CommandContext<CommandSource> arguments = (CommandContext<CommandSource>) dependencies.get("arguments");
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
				.orElse(new DarkrpStuffModVariables.PlayerVariables())).Handcuffed) {
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
					capability.Handcuffed = _setval;
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
					capability.Handcuffed = _setval;
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
			if ((new Object() {
				public Entity getEntity() {
					try {
						return EntityArgument.getEntity(arguments, "Player");
					} catch (CommandSyntaxException e) {
						e.printStackTrace();
						return null;
					}
				}
			}.getEntity()) instanceof PlayerEntity && !(new Object() {
				public Entity getEntity() {
					try {
						return EntityArgument.getEntity(arguments, "Player");
					} catch (CommandSyntaxException e) {
						e.printStackTrace();
						return null;
					}
				}
			}.getEntity()).world.isRemote()) {
				((PlayerEntity) (new Object() {
					public Entity getEntity() {
						try {
							return EntityArgument.getEntity(arguments, "Player");
						} catch (CommandSyntaxException e) {
							e.printStackTrace();
							return null;
						}
					}
				}.getEntity())).sendStatusMessage(new StringTextComponent("\u00A77Vous avez \u00E9t\u00E9 menotter par un \u00A76Admin"), (false));
			}
		}
	}
}
