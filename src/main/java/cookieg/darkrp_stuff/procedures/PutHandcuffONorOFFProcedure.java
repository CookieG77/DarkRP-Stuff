package cookieg.darkrp_stuff.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.util.Hand;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.model.multipart.Selector;

import java.util.stream.Collectors;
import java.util.function.Function;
import java.util.Map;
import java.util.List;
import java.util.Comparator;

import cookieg.darkrp_stuff.item.HandcuffsKeyItem;
import cookieg.darkrp_stuff.item.HandcuffsItem;
import cookieg.darkrp_stuff.DarkrpStuffModVariables;
import cookieg.darkrp_stuff.DarkrpStuffMod;

public class PutHandcuffONorOFFProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency world for procedure PutHandcuffONorOFF!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency x for procedure PutHandcuffONorOFF!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency y for procedure PutHandcuffONorOFF!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency z for procedure PutHandcuffONorOFF!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency entity for procedure PutHandcuffONorOFF!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack ItemInHand = ItemStack.EMPTY;
		double distance = 0;
		Entity Selector = null;
		String PlayerLookedAT = "";
		distance = 4;
		{
			List<Entity> _entfound = world
					.getEntitiesWithinAABB(Entity.class,
							new AxisAlignedBB(x - (3 / 2d), y - (3 / 2d), z - (3 / 2d), x + (3 / 2d), y + (3 / 2d), z + (3 / 2d)), null)
					.stream().sorted(new Object() {
						Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
							return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
						}
					}.compareDistOf(x, y, z)).collect(Collectors.toList());
			for (Entity entityiterator : _entfound) {
				if (distance > Math.sqrt(Math.pow(entityiterator.getPosX() - entity.getPosX(), 2)
						+ Math.pow(entityiterator.getPosY() - entity.getPosY(), 2) + Math.pow(entityiterator.getPosZ() - entity.getPosZ(), 2))
						&& !(entityiterator == entity)) {
					distance = Math.sqrt(Math.pow(entityiterator.getPosX() - entity.getPosX(), 2)
							+ Math.pow(entityiterator.getPosY() - entity.getPosY(), 2) + Math.pow(entityiterator.getPosZ() - entity.getPosZ(), 2));
					Selector = entityiterator;
				}
			}
		}
		if (Selector instanceof PlayerEntity) {
			{
				List<Entity> _entfound = world
						.getEntitiesWithinAABB(Entity.class,
								new AxisAlignedBB(x - (3 / 2d), y - (3 / 2d), z - (3 / 2d), x + (3 / 2d), y + (3 / 2d), z + (3 / 2d)), null)
						.stream().sorted(new Object() {
							Comparator<Entity> compareDistOf(double _x, double _y, double _z) {
								return Comparator.comparing((Function<Entity, Double>) (_entcnd -> _entcnd.getDistanceSq(_x, _y, _z)));
							}
						}.compareDistOf(x, y, z)).collect(Collectors.toList());
				for (Entity entityiterator : _entfound) {
					if (Selector == entityiterator) {
						ItemInHand = ((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY);
						if ((ItemInHand).getItem() == HandcuffsItem.block
								&& !(entityiterator.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new DarkrpStuffModVariables.PlayerVariables())).Handcuffed) {
							{
								boolean _setval = (true);
								entityiterator.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.Handcuffed = _setval;
									capability.syncPlayerVariables(entityiterator);
								});
							}
							if (entity instanceof LivingEntity) {
								ItemStack _setstack = new ItemStack(HandcuffsKeyItem.block);
								_setstack.setCount((int) 1);
								((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
								if (entity instanceof ServerPlayerEntity)
									((ServerPlayerEntity) entity).inventory.markDirty();
							}
							((entity instanceof LivingEntity) ? ((LivingEntity) entity).getHeldItemMainhand() : ItemStack.EMPTY).getOrCreateTag()
									.putString("Handcuffed", (entityiterator.getDisplayName().getString()));
							if (entityiterator instanceof PlayerEntity && !entityiterator.world.isRemote()) {
								((PlayerEntity) entityiterator).sendStatusMessage(
										new StringTextComponent(
												("\u00A77Vous avez \u00E9t\u00E9 menotter par : \u00A76" + entity.getDisplayName().getString())),
										(false));
							}
						} else if ((ItemInHand).getItem() == HandcuffsKeyItem.block
								&& (entityiterator.getDisplayName().getString()).equals((ItemInHand).getOrCreateTag().getString("Handcuffed"))
								&& (Selector.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new DarkrpStuffModVariables.PlayerVariables())).Handcuffed) {
							{
								boolean _setval = (false);
								entityiterator.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
									capability.Handcuffed = _setval;
									capability.syncPlayerVariables(entityiterator);
								});
							}
							if (entity instanceof LivingEntity) {
								ItemStack _setstack = new ItemStack(HandcuffsItem.block);
								_setstack.setCount((int) 1);
								((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
								if (entity instanceof ServerPlayerEntity)
									((ServerPlayerEntity) entity).inventory.markDirty();
							}
						}
					}
				}
			}
		}
	}
}
