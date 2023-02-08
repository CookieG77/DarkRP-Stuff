package cookieg.darkrp_stuff.procedures;

import net.minecraft.util.Hand;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.gui.widget.TextFieldWidget;

import java.util.Map;
import java.util.HashMap;
import java.util.Calendar;

import cookieg.darkrp_stuff.item.IdentityCardItem;
import cookieg.darkrp_stuff.DarkrpStuffMod;

public class WriteOnIdentityCardProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency entity for procedure WriteOnIdentityCard!");
			return;
		}
		if (dependencies.get("guistate") == null) {
			if (!dependencies.containsKey("guistate"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency guistate for procedure WriteOnIdentityCard!");
			return;
		}
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
		}.getText()).isEmpty() && !(new Object() {
			public String getText() {
				TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:SurName");
				if (_tf != null) {
					return _tf.getText();
				}
				return "";
			}
		}.getText()).isEmpty() && !(new Object() {
			public String getText() {
				TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:Country");
				if (_tf != null) {
					return _tf.getText();
				}
				return "";
			}
		}.getText()).isEmpty() && !(new Object() {
			public String getText() {
				TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:ResidentSince");
				if (_tf != null) {
					return _tf.getText();
				}
				return "";
			}
		}.getText()).isEmpty()) {
			Card = new ItemStack(IdentityCardItem.block);
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
			(Card).getOrCreateTag().putString("OriginCountry", (new Object() {
				public String getText() {
					TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:Country");
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
							+ "/" + ("" + (Calendar.getInstance().get(Calendar.YEAR) + 10)).substring((int) 2, (int) 4)));
			if (entity instanceof LivingEntity) {
				ItemStack _setstack = (Card);
				_setstack.setCount((int) 1);
				((LivingEntity) entity).setHeldItem(Hand.MAIN_HAND, _setstack);
				if (entity instanceof ServerPlayerEntity)
					((ServerPlayerEntity) entity).inventory.markDirty();
			}
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).closeScreen();
		}
	}
}
