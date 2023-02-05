package cookieg.darkrp_stuff.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.gui.widget.TextFieldWidget;

import java.util.Map;
import java.util.List;
import java.util.HashMap;
import java.util.ArrayList;

import cookieg.darkrp_stuff.DarkrpStuffMod;

public class SendAdminTicketProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency world for procedure SendAdminTicket!");
			return;
		}
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency entity for procedure SendAdminTicket!");
			return;
		}
		if (dependencies.get("guistate") == null) {
			if (!dependencies.containsKey("guistate"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency guistate for procedure SendAdminTicket!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		Entity entity = (Entity) dependencies.get("entity");
		HashMap guistate = (HashMap) dependencies.get("guistate");
		if (!(new Object() {
			public String getText() {
				TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:Raison");
				if (_tf != null) {
					return _tf.getText();
				}
				return "";
			}
		}.getText()).equals("") && !(new Object() {
			public String getText() {
				TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:Description");
				if (_tf != null) {
					return _tf.getText();
				}
				return "";
			}
		}.getText()).equals("")) {
			{
				List<? extends PlayerEntity> _players = new ArrayList<>(world.getPlayers());
				for (Entity entityiterator : _players) {
					if (entityiterator.hasPermissionLevel((int) 4)) {
						if (entityiterator instanceof PlayerEntity && !entityiterator.world.isRemote()) {
							((PlayerEntity) entityiterator).sendStatusMessage(
									new StringTextComponent("\u00A74\u00A7l============================================="), (false));
						}
						if (entityiterator instanceof PlayerEntity && !entityiterator.world.isRemote()) {
							((PlayerEntity) entityiterator).sendStatusMessage(new StringTextComponent(
									("\u00A74\u00A7l\u00A7nTicket de :\u00A7r\u00A77  " + entity.getDisplayName().getString())), (false));
						}
						if (entityiterator instanceof PlayerEntity && !entityiterator.world.isRemote()) {
							((PlayerEntity) entityiterator).sendStatusMessage(new StringTextComponent(""), (false));
						}
						if (entityiterator instanceof PlayerEntity && !entityiterator.world.isRemote()) {
							((PlayerEntity) entityiterator)
									.sendStatusMessage(new StringTextComponent(("\u00A7cRaison :  \u00A7r\u00A77[" + (new Object() {
										public String getText() {
											TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:Raison");
											if (_tf != null) {
												return _tf.getText();
											}
											return "";
										}
									}.getText()) + "]")), (false));
						}
						if (entityiterator instanceof PlayerEntity && !entityiterator.world.isRemote()) {
							((PlayerEntity) entityiterator)
									.sendStatusMessage(new StringTextComponent(("\u00A7cDescription :  \u00A7r\u00A77\"" + "" + (new Object() {
										public String getText() {
											TextFieldWidget _tf = (TextFieldWidget) guistate.get("text:Description");
											if (_tf != null) {
												return _tf.getText();
											}
											return "";
										}
									}.getText()) + "\"")), (false));
						}
						if (entityiterator instanceof PlayerEntity && !entityiterator.world.isRemote()) {
							((PlayerEntity) entityiterator).sendStatusMessage(
									new StringTextComponent("\u00A74\u00A7l============================================="), (false));
						}
					}
				}
			}
			if (entity instanceof PlayerEntity && !entity.world.isRemote()) {
				((PlayerEntity) entity).sendStatusMessage(new StringTextComponent("Votre ticket a \u00E9t\u00E9 envoy\u00E9 !"), (true));
			}
			if (entity instanceof PlayerEntity)
				((PlayerEntity) entity).closeScreen();
		}
	}
}
