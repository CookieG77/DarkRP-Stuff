
package cookieg.darkrp_stuff.command;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.RegisterCommandsEvent;
import net.minecraftforge.common.util.FakePlayerFactory;

import net.minecraft.world.server.ServerWorld;
import net.minecraft.util.Direction;
import net.minecraft.entity.Entity;
import net.minecraft.command.arguments.EntityArgument;
import net.minecraft.command.Commands;
import net.minecraft.command.CommandSource;

import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

import cookieg.darkrp_stuff.procedures.HandcuffCommandProcedureProcedure;

import com.mojang.brigadier.builder.LiteralArgumentBuilder;

@Mod.EventBusSubscriber
public class HandcuffCommand {
	@SubscribeEvent
	public static void registerCommands(RegisterCommandsEvent event) {
		event.getDispatcher().register(LiteralArgumentBuilder.<CommandSource>literal("handcuff").requires(s -> s.hasPermissionLevel(3))
				.then(Commands.argument("Player", EntityArgument.player()).executes(arguments -> {
					ServerWorld world = arguments.getSource().getWorld();
					double x = arguments.getSource().getPos().getX();
					double y = arguments.getSource().getPos().getY();
					double z = arguments.getSource().getPos().getZ();
					Entity entity = arguments.getSource().getEntity();
					if (entity == null)
						entity = FakePlayerFactory.getMinecraft(world);
					Direction direction = entity.getHorizontalFacing();

					HandcuffCommandProcedureProcedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("arguments", arguments))
							.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll));
					return 0;
				})));
	}
}
