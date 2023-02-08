package cookieg.darkrp_stuff.procedures;

import net.minecraftforge.items.IItemHandler;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.AxisAlignedBB;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.container.Slot;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.renderer.model.multipart.Selector;

import java.util.stream.Collectors;
import java.util.function.Supplier;
import java.util.function.Function;
import java.util.concurrent.atomic.AtomicReference;
import java.util.Map;
import java.util.List;
import java.util.Comparator;

import cookieg.darkrp_stuff.DarkrpStuffModVariables;
import cookieg.darkrp_stuff.DarkrpStuffMod;

public class InspectPlayerProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency world for procedure InspectPlayer!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency x for procedure InspectPlayer!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency y for procedure InspectPlayer!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency z for procedure InspectPlayer!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency entity for procedure InspectPlayer!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		Entity Selector = null;
		double distance = 0;
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
			distance = 0;
			{
				String _setval = (Selector.getDisplayName().getString());
				entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
					capability.IdentityCardSurname = _setval;
					capability.syncPlayerVariables(entity);
				});
			}
			{
				AtomicReference<IItemHandler> _iitemhandlerref = new AtomicReference<>();
				Selector.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> _iitemhandlerref.set(capability));
				if (_iitemhandlerref.get() != null) {
					for (int _idx = 0; _idx < _iitemhandlerref.get().getSlots(); _idx++) {
						ItemStack itemstackiterator = _iitemhandlerref.get().getStackInSlot(_idx).copy();
						if (entity instanceof PlayerEntity) {
							Container _current = ((PlayerEntity) entity).openContainer;
							if (_current instanceof Supplier) {
								Object invobj = ((Supplier) _current).get();
								if (invobj instanceof Map) {
									ItemStack _setstack = itemstackiterator;
									_setstack.setCount((int) ((itemstackiterator).getCount()));
									((Slot) ((Map) invobj).get((int) (distance))).putStack(_setstack);
									_current.detectAndSendChanges();
								}
							}
						}
						distance = (distance + 1);
					}
				}
			}
		} else {
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).closeScreen();
		}
	}
}
