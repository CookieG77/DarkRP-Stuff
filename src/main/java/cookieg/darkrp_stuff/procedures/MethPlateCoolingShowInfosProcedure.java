package cookieg.darkrp_stuff.procedures;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.ItemTooltipEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;
import net.minecraft.client.gui.screen.Screen;

import java.util.Map;
import java.util.List;
import java.util.HashMap;

import cookieg.darkrp_stuff.item.SmokeFilterItem;
import cookieg.darkrp_stuff.item.MethPlateCoolingItem;
import cookieg.darkrp_stuff.DarkrpStuffMod;

public class MethPlateCoolingShowInfosProcedure {
	@Mod.EventBusSubscriber
	private static class GlobalTrigger {
		@OnlyIn(Dist.CLIENT)
		@SubscribeEvent
		public static void onItemTooltip(ItemTooltipEvent event) {
			if (event != null && event.getPlayer() != null) {
				Entity entity = event.getPlayer();
				ItemStack itemStack = event.getItemStack();
				List<ITextComponent> tooltip = event.getToolTip();
				Map<String, Object> dependencies = new HashMap<>();
				dependencies.put("tooltip", tooltip);
				dependencies.put("entity", entity);
				dependencies.put("event", event);
				dependencies.put("itemstack", itemStack);
				executeProcedure(dependencies);
			}
		}
	}

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency itemstack for procedure MethPlateCoolingShowInfos!");
			return;
		}
		if (dependencies.get("tooltip") == null) {
			if (!dependencies.containsKey("tooltip"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency tooltip for procedure MethPlateCoolingShowInfos!");
			return;
		}
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		List<ITextComponent> tooltip = (List<ITextComponent>) dependencies.get("tooltip");
		double advancement = 0;
		String advancementstring = "";
		if (MethPlateCoolingItem.block == itemstack.getItem() && Screen.hasShiftDown()) {
			advancement = Math.ceil(itemstack.getOrCreateTag().getDouble("Cooling") / 60);
			advancementstring = "\u00A77\u00A7l[\u00A7r";
			if (advancement > 6) {
				advancementstring = (advancementstring + "\u00A72");
			} else if (advancement > 3) {
				advancementstring = (advancementstring + "\u00A76");
			} else {
				advancementstring = (advancementstring + "\u00A74");
			}
			for (int index0 = 0; index0 < (int) (advancement); index0++) {
				advancementstring = (advancementstring + "\u2588");
			}
			advancementstring = (advancementstring + "\u00A78");
			for (int index1 = 0; index1 < (int) (10 - advancement); index1++) {
				advancementstring = (advancementstring + "\u2592");
			}
			advancementstring = (advancementstring + "\u00A77\u00A7l]\u00A7r");
			tooltip.add(new StringTextComponent(advancementstring));
		}
		if (SmokeFilterItem.block == itemstack.getItem() && Screen.hasShiftDown()) {
			advancement = Math.floor((1250 - itemstack.getOrCreateTag().getDouble("FakeDurability")) / 1250);
			advancementstring = "\u00A77\u00A7l[\u00A7r";
			if (advancement > 6) {
				advancementstring = (advancementstring + "\u00A72");
			} else if (advancement > 3) {
				advancementstring = (advancementstring + "\u00A76");
			} else {
				advancementstring = (advancementstring + "\u00A74");
			}
			for (int index2 = 0; index2 < (int) (advancement); index2++) {
				advancementstring = (advancementstring + "\u2588");
			}
			advancementstring = (advancementstring + "\u00A78");
			for (int index3 = 0; index3 < (int) (10 - advancement); index3++) {
				advancementstring = (advancementstring + "\u2592");
			}
			advancementstring = (advancementstring + "\u00A77\u00A7l]\u00A7r");
			tooltip.add(new StringTextComponent(advancementstring));
		}
	}
}
