
package cookieg.darkrp_stuff.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.ResourceLocation;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import java.util.stream.Stream;
import java.util.Map;
import java.util.HashMap;
import java.util.AbstractMap;

import cookieg.darkrp_stuff.procedures.VerifAnimation8Procedure;
import cookieg.darkrp_stuff.procedures.VerifAnimation7Procedure;
import cookieg.darkrp_stuff.procedures.VerifAnimation6Procedure;
import cookieg.darkrp_stuff.procedures.VerifAnimation5Procedure;
import cookieg.darkrp_stuff.procedures.VerifAnimation4Procedure;
import cookieg.darkrp_stuff.procedures.VerifAnimation3Procedure;
import cookieg.darkrp_stuff.procedures.VerifAnimation2Procedure;
import cookieg.darkrp_stuff.procedures.VerifAnimation1Procedure;
import cookieg.darkrp_stuff.procedures.NeedFilterProcedure;
import cookieg.darkrp_stuff.DarkrpStuffMod;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class MethMixterGUIGuiWindow extends ContainerScreen<MethMixterGUIGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = MethMixterGUIGui.guistate;

	public MethMixterGUIGuiWindow(MethMixterGUIGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 180;
		this.ySize = 192;
	}

	private static final ResourceLocation texture = new ResourceLocation("darkrp_stuff:textures/screens/meth_mixter_gui.png");

	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
	}

	@Override
	protected void drawGuiContainerBackgroundLayer(MatrixStack ms, float partialTicks, int gx, int gy) {
		RenderSystem.color4f(1, 1, 1, 1);
		RenderSystem.enableBlend();
		RenderSystem.defaultBlendFunc();
		Minecraft.getInstance().getTextureManager().bindTexture(texture);
		int k = (this.width - this.xSize) / 2;
		int l = (this.height - this.ySize) / 2;
		this.blit(ms, k, l, 0, 0, this.xSize, this.ySize, this.xSize, this.ySize);

		Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/methmixer_gui_off.png"));
		this.blit(ms, this.guiLeft + 112, this.guiTop + 25, 0, 0, 64, 64, 64, 64);

		if (VerifAnimation1Procedure.executeProcedure(Stream
				.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x), new AbstractMap.SimpleEntry<>("y", y),
						new AbstractMap.SimpleEntry<>("z", z), new AbstractMap.SimpleEntry<>("entity", entity))
				.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/methmixer_gui_on_1.png"));
			this.blit(ms, this.guiLeft + 112, this.guiTop + 25, 0, 0, 64, 64, 64, 64);
		}
		if (VerifAnimation2Procedure.executeProcedure(Stream
				.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x), new AbstractMap.SimpleEntry<>("y", y),
						new AbstractMap.SimpleEntry<>("z", z), new AbstractMap.SimpleEntry<>("entity", entity))
				.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/methmixer_gui_on_2.png"));
			this.blit(ms, this.guiLeft + 112, this.guiTop + 25, 0, 0, 64, 64, 64, 64);
		}
		if (VerifAnimation3Procedure.executeProcedure(Stream
				.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x), new AbstractMap.SimpleEntry<>("y", y),
						new AbstractMap.SimpleEntry<>("z", z), new AbstractMap.SimpleEntry<>("entity", entity))
				.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/methmixer_gui_on_3.png"));
			this.blit(ms, this.guiLeft + 112, this.guiTop + 25, 0, 0, 64, 64, 64, 64);
		}
		if (VerifAnimation4Procedure.executeProcedure(Stream
				.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x), new AbstractMap.SimpleEntry<>("y", y),
						new AbstractMap.SimpleEntry<>("z", z), new AbstractMap.SimpleEntry<>("entity", entity))
				.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/methmixer_gui_on_4.png"));
			this.blit(ms, this.guiLeft + 112, this.guiTop + 25, 0, 0, 64, 64, 64, 64);
		}
		if (VerifAnimation5Procedure.executeProcedure(Stream
				.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x), new AbstractMap.SimpleEntry<>("y", y),
						new AbstractMap.SimpleEntry<>("z", z), new AbstractMap.SimpleEntry<>("entity", entity))
				.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/methmixer_gui_on_5.png"));
			this.blit(ms, this.guiLeft + 112, this.guiTop + 25, 0, 0, 64, 64, 64, 64);
		}
		if (VerifAnimation6Procedure.executeProcedure(Stream
				.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x), new AbstractMap.SimpleEntry<>("y", y),
						new AbstractMap.SimpleEntry<>("z", z), new AbstractMap.SimpleEntry<>("entity", entity))
				.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/methmixer_gui_on_6.png"));
			this.blit(ms, this.guiLeft + 112, this.guiTop + 25, 0, 0, 64, 64, 64, 64);
		}
		if (VerifAnimation7Procedure.executeProcedure(Stream
				.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x), new AbstractMap.SimpleEntry<>("y", y),
						new AbstractMap.SimpleEntry<>("z", z), new AbstractMap.SimpleEntry<>("entity", entity))
				.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/methmixer_gui_on_7.png"));
			this.blit(ms, this.guiLeft + 112, this.guiTop + 25, 0, 0, 64, 64, 64, 64);
		}
		if (VerifAnimation8Procedure.executeProcedure(Stream
				.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x), new AbstractMap.SimpleEntry<>("y", y),
						new AbstractMap.SimpleEntry<>("z", z), new AbstractMap.SimpleEntry<>("entity", entity))
				.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
			Minecraft.getInstance().getTextureManager().bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/methmixer_gui_on_8.png"));
			this.blit(ms, this.guiLeft + 112, this.guiTop + 25, 0, 0, 64, 64, 64, 64);
		}
		if (NeedFilterProcedure
				.executeProcedure(Stream
						.of(new AbstractMap.SimpleEntry<>("world", world), new AbstractMap.SimpleEntry<>("x", x),
								new AbstractMap.SimpleEntry<>("y", y), new AbstractMap.SimpleEntry<>("z", z))
						.collect(HashMap::new, (_m, _e) -> _m.put(_e.getKey(), _e.getValue()), Map::putAll))) {
			Minecraft.getInstance().getTextureManager()
					.bindTexture(new ResourceLocation("darkrp_stuff:textures/screens/meth_mixter_gui_needfilter.png"));
			this.blit(ms, this.guiLeft + 14, this.guiTop + 49, 0, 0, 64, 32, 64, 32);
		}
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeScreen();
			return true;
		}
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "Mixeur Industriel", 8, 7, -12829636);
		this.font.drawString(ms, "Puret\u00E9 : \u00A7l" + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "QualityInspect")) + "", 8, 88, -12829636);
		this.font.drawString(ms, "Avancement: " + (new Object() {
			public String getValue(BlockPos pos, String tag) {
				TileEntity tileEntity = world.getTileEntity(pos);
				if (tileEntity != null)
					return tileEntity.getTileData().getString(tag);
				return "";
			}
		}.getValue(new BlockPos((int) x, (int) y, (int) z), "FakeQuality")) + "", 8, 97, -12829636);
	}

	@Override
	public void onClose() {
		super.onClose();
		Minecraft.getInstance().keyboardListener.enableRepeatEvents(false);
	}

	@Override
	public void init(Minecraft minecraft, int width, int height) {
		super.init(minecraft, width, height);
		minecraft.keyboardListener.enableRepeatEvents(true);
		this.addButton(new Button(this.guiLeft + 58, this.guiTop + 25, 51, 20, new StringTextComponent("Start"), e -> {
			if (true) {
				DarkrpStuffMod.PACKET_HANDLER.sendToServer(new MethMixterGUIGui.ButtonPressedMessage(0, x, y, z));
				MethMixterGUIGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
	}
}
