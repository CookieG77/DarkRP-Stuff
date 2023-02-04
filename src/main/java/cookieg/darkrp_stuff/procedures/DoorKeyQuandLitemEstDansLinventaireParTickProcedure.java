package cookieg.darkrp_stuff.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.GameType;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.network.play.NetworkPlayerInfo;
import net.minecraft.client.entity.player.AbstractClientPlayerEntity;
import net.minecraft.client.Minecraft;
import net.minecraft.block.Blocks;

import java.util.Map;

import cookieg.darkrp_stuff.DarkrpStuffMod;

public class DoorKeyQuandLitemEstDansLinventaireParTickProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency entity for procedure DoorKeyQuandLitemEstDansLinventaireParTick!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency itemstack for procedure DoorKeyQuandLitemEstDansLinventaireParTick!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		if (!(new Object() {
			public boolean checkGamemode(Entity _ent) {
				if (_ent instanceof ServerPlayerEntity) {
					return ((ServerPlayerEntity) _ent).interactionManager.getGameType() == GameType.CREATIVE;
				} else if (_ent instanceof PlayerEntity && _ent.world.isRemote()) {
					NetworkPlayerInfo _npi = Minecraft.getInstance().getConnection()
							.getPlayerInfo(((AbstractClientPlayerEntity) _ent).getGameProfile().getId());
					return _npi != null && _npi.getGameType() == GameType.CREATIVE;
				}
				return false;
			}
		}.checkGamemode(entity)) && !(itemstack.getOrCreateTag().getString("Prori\u00E9taire")).contains(entity.getDisplayName().getString())
				&& !(itemstack.getOrCreateTag().getString("Prori\u00E9taire")).equals("")) {
			if (itemstack.getOrCreateTag().getDouble("DepopAge") >= 16000) {
				{
					final ItemStack _setstack = new ItemStack(Blocks.AIR);
					final int _sltid = (int) (0);
					_setstack.setCount((int) 1);
					entity.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable) {
							((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
						}
					});
				}
			} else {
				itemstack.getOrCreateTag().putDouble("DepopAge", (itemstack.getOrCreateTag().getDouble("DepopAge") + 1));
			}
		} else if (itemstack.getOrCreateTag().getDouble("DepopAge") >= 1) {
			itemstack.getOrCreateTag().putDouble("DepopAge", 0);
		}
	}
}
