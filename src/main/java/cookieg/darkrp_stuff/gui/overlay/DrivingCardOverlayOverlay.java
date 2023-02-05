
package cookieg.darkrp_stuff.gui.overlay;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.eventbus.api.EventPriority;
import net.minecraftforge.client.event.RenderGameOverlayEvent;
import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.Minecraft;

import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

import cookieg.darkrp_stuff.procedures.CheckDrivingCardGUIProcedure;
import cookieg.darkrp_stuff.DarkrpStuffModVariables;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;

@Mod.EventBusSubscriber
public class DrivingCardOverlayOverlay {
	@OnlyIn(Dist.CLIENT)
	@SubscribeEvent(priority = EventPriority.NORMAL)
	public static void eventHandler(RenderGameOverlayEvent.Post event) {
		if (event.getType() == RenderGameOverlayEvent.ElementType.HELMET) {
			int w = event.getWindow().getScaledWidth();
			int h = event.getWindow().getScaledHeight();
			int posX = w / 2;
			int posY = h / 2;
			World _world = null;
			double _x = 0;
			double _y = 0;
			double _z = 0;
			PlayerEntity entity = Minecraft.getInstance().player;
			if (entity != null) {
				_world = entity.world;
				_x = entity.getPosX();
				_y = entity.getPosY();
				_z = entity.getPosZ();
			}
			World world = _world;
			double x = _x;
			double y = _y;
			double z = _z;
			RenderSystem.disableDepthTest();
			RenderSystem.depthMask(false);
			RenderSystem.blendFuncSeparate(GlStateManager.SourceFactor.SRC_ALPHA, GlStateManager.DestFactor.ONE_MINUS_SRC_ALPHA,
					GlStateManager.SourceFactor.ONE, GlStateManager.DestFactor.ZERO);
			RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
			RenderSystem.disableAlphaTest();
			if (CheckDrivingCardGUIProcedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
					(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
				Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/drivinglicensegui.png"));
				Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -127, posY + -128, 0, 0, 256, 256, 256, 256);

				Minecraft.getInstance().fontRenderer
						.drawString(event.getMatrixStack(),
								"\u00A7l" + ((entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
										.orElse(new DarkrpStuffModVariables.PlayerVariables())).IdentityCardSurname) + "",
								posX + -18, posY + -2, -10066330);
				Minecraft.getInstance().fontRenderer.drawString(event.getMatrixStack(),
						"\u00A7l" + ((entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new DarkrpStuffModVariables.PlayerVariables())).IdentityCardGivenName) + "",
						posX + -18, posY + 10, -10066330);
				Minecraft.getInstance().fontRenderer.drawString(event.getMatrixStack(),
						"" + ((entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new DarkrpStuffModVariables.PlayerVariables())).IdentityCardResidentSince) + "",
						posX + -12, posY + 34, -52429);
				Minecraft.getInstance().fontRenderer.drawString(event.getMatrixStack(),
						"" + ((entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null)
								.orElse(new DarkrpStuffModVariables.PlayerVariables())).IdentityCardExpiration) + "",
						posX + -13, posY + -17, -10066330);
				Minecraft.getInstance().fontRenderer.drawString(event.getMatrixStack(), "\u00A7l|1251534", posX + -19, posY + -38, -52429);
				Minecraft.getInstance().fontRenderer.drawString(event.getMatrixStack(), "NONE", posX + -6, posY + 46, -10066330);
				Minecraft.getInstance().fontRenderer.drawString(event.getMatrixStack(), "C", posX + 77, posY + -38, -10066330);
				Minecraft.getInstance().fontRenderer.drawString(event.getMatrixStack(), "NONE", posX + 64, posY + -26, -10066330);
			}
			RenderSystem.depthMask(true);
			RenderSystem.enableDepthTest();
			RenderSystem.enableAlphaTest();
			RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}
