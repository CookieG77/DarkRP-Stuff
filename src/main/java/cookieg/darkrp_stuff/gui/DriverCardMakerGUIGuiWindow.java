
package cookieg.darkrp_stuff.gui;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.util.ResourceLocation;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.client.gui.widget.button.Button;
import net.minecraft.client.gui.widget.TextFieldWidget;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.client.Minecraft;

import java.util.HashMap;

import cookieg.darkrp_stuff.DarkrpStuffMod;

import com.mojang.blaze3d.systems.RenderSystem;
import com.mojang.blaze3d.matrix.MatrixStack;

@OnlyIn(Dist.CLIENT)
public class DriverCardMakerGUIGuiWindow extends ContainerScreen<DriverCardMakerGUIGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = DriverCardMakerGUIGui.guistate;
	TextFieldWidget Name;
	TextFieldWidget SurName;
	TextFieldWidget ResidentSince;

	public DriverCardMakerGUIGuiWindow(DriverCardMakerGUIGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 234;
		this.ySize = 120;
	}

	private static final ResourceLocation texture = new ResourceLocation("darkrp_stuff:textures/screens/driver_card_maker_gui.png");

	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
		Name.render(ms, mouseX, mouseY, partialTicks);
		SurName.render(ms, mouseX, mouseY, partialTicks);
		ResidentSince.render(ms, mouseX, mouseY, partialTicks);
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
		RenderSystem.disableBlend();
	}

	@Override
	public boolean keyPressed(int key, int b, int c) {
		if (key == 256) {
			this.minecraft.player.closeScreen();
			return true;
		}
		if (Name.isFocused())
			return Name.keyPressed(key, b, c);
		if (SurName.isFocused())
			return SurName.keyPressed(key, b, c);
		if (ResidentSince.isFocused())
			return ResidentSince.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
		Name.tick();
		SurName.tick();
		ResidentSince.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "Pr\u00E9nom :", 62, 11, -10066330);
		this.font.drawString(ms, "Nom :", 80, 38, -10066330);
		this.font.drawString(ms, "Date de naissance :", 8, 65, -10066330);
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
		Name = new TextFieldWidget(this.font, this.guiLeft + 107, this.guiTop + 11, 120, 20, new StringTextComponent(""));
		guistate.put("text:Name", Name);
		Name.setMaxStringLength(32767);
		this.children.add(this.Name);
		SurName = new TextFieldWidget(this.font, this.guiLeft + 107, this.guiTop + 38, 120, 20, new StringTextComponent(""));
		guistate.put("text:SurName", SurName);
		SurName.setMaxStringLength(32767);
		this.children.add(this.SurName);
		ResidentSince = new TextFieldWidget(this.font, this.guiLeft + 107, this.guiTop + 65, 120, 20, new StringTextComponent(""));
		guistate.put("text:ResidentSince", ResidentSince);
		ResidentSince.setMaxStringLength(32767);
		this.children.add(this.ResidentSince);
		this.addButton(new Button(this.guiLeft + 80, this.guiTop + 92, 56, 20, new StringTextComponent("Écrire"), e -> {
			if (true) {
				DarkrpStuffMod.PACKET_HANDLER.sendToServer(new DriverCardMakerGUIGui.ButtonPressedMessage(0, x, y, z));
				DriverCardMakerGUIGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
	}
}
