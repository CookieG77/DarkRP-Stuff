
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

import cookieg.darkrp_stuff.procedures.Veriflockn6Procedure;
import cookieg.darkrp_stuff.procedures.Veriflockn5Procedure;
import cookieg.darkrp_stuff.procedures.Veriflockn4Procedure;
import cookieg.darkrp_stuff.procedures.Veriflockn3Procedure;
import cookieg.darkrp_stuff.procedures.Veriflockn2Procedure;
import cookieg.darkrp_stuff.procedures.Veriflockn1Procedure;
import cookieg.darkrp_stuff.procedures.Veriflock6Procedure;
import cookieg.darkrp_stuff.procedures.Veriflock5Procedure;
import cookieg.darkrp_stuff.procedures.Veriflock4Procedure;
import cookieg.darkrp_stuff.procedures.Veriflock3Procedure;
import cookieg.darkrp_stuff.procedures.Veriflock2Procedure;
import cookieg.darkrp_stuff.procedures.Veriflock1Procedure;
import cookieg.darkrp_stuff.procedures.Veriflock0Procedure;
import cookieg.darkrp_stuff.procedures.VerifVerrou6Procedure;
import cookieg.darkrp_stuff.procedures.VerifVerrou5Procedure;
import cookieg.darkrp_stuff.procedures.VerifVerrou4Procedure;
import cookieg.darkrp_stuff.procedures.VerifVerrou3Procedure;
import cookieg.darkrp_stuff.procedures.VerifVerrou2Procedure;
import cookieg.darkrp_stuff.procedures.VerifVerrou1Procedure;
import cookieg.darkrp_stuff.procedures.VerifVerrou0Procedure;
import cookieg.darkrp_stuff.procedures.LockpickOverlayAfficherLoverlayEnJeuProcedure;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.platform.GlStateManager;

@Mod.EventBusSubscriber
public class LockpickOverlayOverlay {
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
			if (LockpickOverlayAfficherLoverlayEnJeuProcedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity))
					.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
				if (VerifVerrou0Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/lock0.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -63, posY + -64, 0, 0, 128, 128, 128, 128);
				}
				if (VerifVerrou1Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/lock1.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -63, posY + -64, 0, 0, 128, 128, 128, 128);
				}
				if (VerifVerrou2Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/lock2.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -63, posY + -64, 0, 0, 128, 128, 128, 128);
				}
				if (VerifVerrou3Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/lock3.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -63, posY + -64, 0, 0, 128, 128, 128, 128);
				}
				if (VerifVerrou4Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/lock4.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -63, posY + -64, 0, 0, 128, 128, 128, 128);
				}
				if (VerifVerrou5Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/lock5.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -63, posY + -64, 0, 0, 128, 128, 128, 128);
				}
				if (VerifVerrou6Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/lock6.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -63, posY + -64, 0, 0, 128, 128, 128, 128);
				}
				if (Veriflockn6Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/lockpick-6.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -63, posY + -64, 0, 0, 128, 128, 128, 128);
				}
				if (Veriflockn5Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/lockpick-5.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -63, posY + -64, 0, 0, 128, 128, 128, 128);
				}
				if (Veriflockn4Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/lockpick-4.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -63, posY + -64, 0, 0, 128, 128, 128, 128);
				}
				if (Veriflockn3Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/lockpick-3.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -63, posY + -64, 0, 0, 128, 128, 128, 128);
				}
				if (Veriflockn2Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/lockpick-2.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -63, posY + -64, 0, 0, 128, 128, 128, 128);
				}
				if (Veriflockn1Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/lockpick-1.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -63, posY + -64, 0, 0, 128, 128, 128, 128);
				}
				if (Veriflock0Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/lockpick0.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -63, posY + -64, 0, 0, 128, 128, 128, 128);
				}
				if (Veriflock1Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/lockpick1.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -63, posY + -64, 0, 0, 128, 128, 128, 128);
				}
				if (Veriflock2Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/lockpick2.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -63, posY + -64, 0, 0, 128, 128, 128, 128);
				}
				if (Veriflock3Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/lockpick3.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -63, posY + -64, 0, 0, 128, 128, 128, 128);
				}
				if (Veriflock4Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/lockpick4.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -63, posY + -64, 0, 0, 128, 128, 128, 128);
				}
				if (Veriflock5Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/lockpick5.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -63, posY + -64, 0, 0, 128, 128, 128, 128);
				}
				if (Veriflock6Procedure.executeProcedure(Stream.of(new AbstractMap.SimpleEntry<>("entity", entity)).collect(HashMap::new,
						(_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
					Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/lockpick6.png"));
					Minecraft.getInstance().ingameGUI.blit(event.getMatrixStack(), posX + -63, posY + -64, 0, 0, 128, 128, 128, 128);
				}
			}
			RenderSystem.depthMask(true);
			RenderSystem.enableDepthTest();
			RenderSystem.enableAlphaTest();
			RenderSystem.color4f(1.0F, 1.0F, 1.0F, 1.0F);
		}
	}
}
