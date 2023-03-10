
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
public class IdentityCardMakerGUIGuiWindow extends ContainerScreen<IdentityCardMakerGUIGui.GuiContainerMod> {
	private World world;
	private int x, y, z;
	private PlayerEntity entity;
	private final static HashMap guistate = IdentityCardMakerGUIGui.guistate;
	TextFieldWidget Name;
	TextFieldWidget SurName;
	TextFieldWidget Country;
	TextFieldWidget ResidentSince;

	public IdentityCardMakerGUIGuiWindow(IdentityCardMakerGUIGui.GuiContainerMod container, PlayerInventory inventory, ITextComponent text) {
		super(container, inventory, text);
		this.world = container.world;
		this.x = container.x;
		this.y = container.y;
		this.z = container.z;
		this.entity = container.entity;
		this.xSize = 230;
		this.ySize = 160;
	}

	private static final ResourceLocation texture = new ResourceLocation("darkrp_stuff:textures/screens/identity_card_maker_gui.png");

	@Override
	public void render(MatrixStack ms, int mouseX, int mouseY, float partialTicks) {
		this.renderBackground(ms);
		super.render(ms, mouseX, mouseY, partialTicks);
		this.renderHoveredTooltip(ms, mouseX, mouseY);
		Name.render(ms, mouseX, mouseY, partialTicks);
		SurName.render(ms, mouseX, mouseY, partialTicks);
		Country.render(ms, mouseX, mouseY, partialTicks);
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
		if (Country.isFocused())
			return Country.keyPressed(key, b, c);
		if (ResidentSince.isFocused())
			return ResidentSince.keyPressed(key, b, c);
		return super.keyPressed(key, b, c);
	}

	@Override
	public void tick() {
		super.tick();
		Name.tick();
		SurName.tick();
		Country.tick();
		ResidentSince.tick();
	}

	@Override
	protected void drawGuiContainerForegroundLayer(MatrixStack ms, int mouseX, int mouseY) {
		this.font.drawString(ms, "Pr\u00E9nom :", 60, 22, -10066330);
		this.font.drawString(ms, "Nom :", 78, 49, -10066330);
		this.font.drawString(ms, "Pays d'Origine  :", 15, 76, -10066330);
		this.font.drawString(ms, "Date de naissance :", 6, 103, -10066330);
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
		Name = new TextFieldWidget(this.font, this.guiLeft + 105, this.guiTop + 22, 120, 20, new StringTextComponent(""));
		guistate.put("text:Name", Name);
		Name.setMaxStringLength(32767);
		this.children.add(this.Name);
		SurName = new TextFieldWidget(this.font, this.guiLeft + 105, this.guiTop + 49, 120, 20, new StringTextComponent(""));
		guistate.put("text:SurName", SurName);
		SurName.setMaxStringLength(32767);
		this.children.add(this.SurName);
		Country = new TextFieldWidget(this.font, this.guiLeft + 105, this.guiTop + 76, 120, 20, new StringTextComponent(""));
		guistate.put("text:Country", Country);
		Country.setMaxStringLength(32767);
		this.children.add(this.Country);
		ResidentSince = new TextFieldWidget(this.font, this.guiLeft + 105, this.guiTop + 103, 120, 20, new StringTextComponent(""));
		guistate.put("text:ResidentSince", ResidentSince);
		ResidentSince.setMaxStringLength(32767);
		this.children.add(this.ResidentSince);
		this.addButton(new Button(this.guiLeft + 78, this.guiTop + 130, 56, 20, new StringTextComponent("??crire"), e -> {
			if (true) {
				DarkrpStuffMod.PACKET_HANDLER.sendToServer(new IdentityCardMakerGUIGui.ButtonPressedMessage(0, x, y, z));
				IdentityCardMakerGUIGui.handleButtonAction(entity, 0, x, y, z);
			}
		}));
	}
}
