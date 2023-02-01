
package cookieg.darkrp_stuff.item;

import net.minecraftforge.registries.ObjectHolder;

import net.minecraft.world.World;
import net.minecraft.util.text.StringTextComponent;
import net.minecraft.util.text.ITextComponent;
import net.minecraft.item.Rarity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Item;
import net.minecraft.client.util.ITooltipFlag;
import net.minecraft.block.BlockState;

import java.util.List;

import cookieg.darkrp_stuff.itemgroup.DarkRPStuffItemGroup;
import cookieg.darkrp_stuff.DarkrpStuffModElements;

@DarkrpStuffModElements.ModElement.Tag
public class DirtyMoney1Item extends DarkrpStuffModElements.ModElement {
	@ObjectHolder("darkrp_stuff:dirty_money_1")
	public static final Item block = null;

	public DirtyMoney1Item(DarkrpStuffModElements instance) {
		super(instance, 22);
	}

	@Override
	public void initElements() {
		elements.items.add(() -> new ItemCustom());
	}

	public static class ItemCustom extends Item {
		public ItemCustom() {
			super(new Item.Properties().group(DarkRPStuffItemGroup.tab).maxStackSize(64).rarity(Rarity.UNCOMMON));
			setRegistryName("dirty_money_1");
		}

		@Override
		public int getItemEnchantability() {
			return 0;
		}

		@Override
		public float getDestroySpeed(ItemStack par1ItemStack, BlockState par2Block) {
			return 1F;
		}

		@Override
		public void addInformation(ItemStack itemstack, World world, List<ITextComponent> list, ITooltipFlag flag) {
			super.addInformation(itemstack, world, list, flag);
			list.add(new StringTextComponent("Pour \u00EAtre utiliser il doit \u00EAtre blanchi"));
		}
	}
}
