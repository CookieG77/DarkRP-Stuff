package cookieg.darkrp_stuff.procedures;

import net.minecraftforge.items.IItemHandlerModifiable;
import net.minecraftforge.items.CapabilityItemHandler;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.inventory.container.Slot;
import net.minecraft.inventory.container.Container;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.gui.widget.TextFieldWidget;

import java.util.function.Supplier;
import java.util.Map;
import java.util.HashMap;
import java.util.Calendar;

import cookieg.darkrp_stuff.item.WeaponCardItem;
import cookieg.darkrp_stuff.item.WeaponCardEmptyItem;
import cookieg.darkrp_stuff.DarkrpStuffMod;

public class WriteOnWeaponCardProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency world for procedure WriteOnWeaponCard!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency x for procedure WriteOnWeaponCard!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency y for procedure WriteOnWeaponCard!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency z for procedure WriteOnWeaponCard!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency entity for procedure WriteOnWeaponCard!");
			return;
		}
		if (dependencies.get("guistate") == null) {
			if (!dependencies.containsKey("guistate"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency guistate for procedure WriteOnWeaponCard!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		Entity entity = (Entity) dependencies.get("entity");
		HashMap guistate = (HashMap) dependencies.get("guistate");
		ItemStack Card = ItemStack.EMPTY;
		if (!(new Object() {
			public String getText() {
				TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:Name");
				if (_tf != null) {
					return _tf.getText();
				}
				return "";
			}
		}.getText()).equals("") && !(new Object() {
			public String getText() {
				TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:SurName");
				if (_tf != null) {
					return _tf.getText();
				}
				return "";
			}
		}.getText()).equals("") && !(new Object() {
			public String getText() {
				TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:ResidentSince");
				if (_tf != null) {
					return _tf.getText();
				}
				return "";
			}
		}.getText()).equals("") && new Object() {
			public int getAmount(int sltid) {
				if (entity instanceof ServerPlayerEntity) {
					Container _current = ((ServerPlayerEntity) entity).openContainer;
					if (_current instanceof Supplier) {
						Object invobj = ((Supplier) _current).get();
						if (invobj instanceof Map) {
							ItemStack stack = ((Slot) ((Map) invobj).get(sltid)).getStack();;
							if (stack != null)
								return stack.getCount();
						}
					}
				}
				return 0;
			}
		}.getAmount((int) (0)) == 1 && (new Object() {
			public ItemStack getItemStack(int sltid) {
				Entity _ent = entity;
				if (_ent instanceof ServerPlayerEntity) {
					Container _current = ((ServerPlayerEntity) _ent).openContainer;
					if (_current instanceof Supplier) {
						Object invobj = ((Supplier) _current).get();
						if (invobj instanceof Map) {
							return ((Slot) ((Map) invobj).get(sltid)).getStack();
						}
					}
				}
				return ItemStack.EMPTY;
			}
		}.getItemStack((int) (0))).getItem() == WeaponCardEmptyItem.block && new Object() {
			public int getAmount(int sltid) {
				if (entity instanceof ServerPlayerEntity) {
					Container _current = ((ServerPlayerEntity) entity).openContainer;
					if (_current instanceof Supplier) {
						Object invobj = ((Supplier) _current).get();
						if (invobj instanceof Map) {
							ItemStack stack = ((Slot) ((Map) invobj).get(sltid)).getStack();;
							if (stack != null)
								return stack.getCount();
						}
					}
				}
				return 0;
			}
		}.getAmount((int) (1)) == 0) {
			Card = new ItemStack(WeaponCardItem.block);
			(Card).getOrCreateTag().putString("Owner", (entity.getDisplayName().getString()));
			(Card).getOrCreateTag().putString("Name", (new Object() {
				public String getText() {
					TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:Name");
					if (_tf != null) {
						return _tf.getText();
					}
					return "";
				}
			}.getText()));
			(Card).getOrCreateTag().putString("GivenName", (new Object() {
				public String getText() {
					TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:SurName");
					if (_tf != null) {
						return _tf.getText();
					}
					return "";
				}
			}.getText()));
			(Card).getOrCreateTag().putString("ResidentSince", (new Object() {
				public String getText() {
					TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:ResidentSince");
					if (_tf != null) {
						return _tf.getText();
					}
					return "";
				}
			}.getText()));
			(Card).getOrCreateTag().putString("ExpirationDate",
					((("" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).length() < 2
							? "0" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
							: Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
							+ "/"
							+ (("" + (Calendar.getInstance().get(Calendar.MONTH) + 1)).length() < 2
									? "0" + (Calendar.getInstance().get(Calendar.MONTH) + 1)
									: Calendar.getInstance().get(Calendar.MONTH) + 1)
							+ "/" + ("" + Calendar.getInstance().get(Calendar.YEAR)).substring((int) 2, (int) 4)));
			(Card).getOrCreateTag().putString("OriginCountry",
					((("" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH)).length() < 2
							? "0" + Calendar.getInstance().get(Calendar.DAY_OF_MONTH)
							: Calendar.getInstance().get(Calendar.DAY_OF_MONTH))
							+ "/"
							+ (("" + (Calendar.getInstance().get(Calendar.MONTH) + 1)).length() < 2
									? "0" + (Calendar.getInstance().get(Calendar.MONTH) + 1)
									: Calendar.getInstance().get(Calendar.MONTH) + 1)
							+ "/" + ("" + (Calendar.getInstance().get(Calendar.YEAR) + 10)).substring((int) 2, (int) 4)));
			{
				TileEntity _ent = world.getTileEntity(new BlockPos(x, y, z));
				if (_ent != null) {
					final int _sltid = (int) (1);
					final ItemStack _setstack = (Card);
					_setstack.setCount((int) 1);
					_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable) {
							((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _setstack);
						}
					});
				}
			}
			{
				TileEntity _ent = world.getTileEntity(new BlockPos(x, y, z));
				if (_ent != null) {
					final int _sltid = (int) (0);
					final int _amount = (int) 1;
					_ent.getCapability(CapabilityItemHandler.ITEM_HANDLER_CAPABILITY, null).ifPresent(capability -> {
						if (capability instanceof IItemHandlerModifiable) {
							ItemStack _stk = capability.getStackInSlot(_sltid).copy();
							_stk.shrink(_amount);
							((IItemHandlerModifiable) capability).setStackInSlot(_sltid, _stk);
						}
					});
				}
			}
		}
	}
}
