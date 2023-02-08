
package cookieg.darkrp_stuff.itemgroup;

import net.minecraftforge.api.distmarker.OnlyIn;
import net.minecraftforge.api.distmarker.Dist;

import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemGroup;

import cookieg.darkrp_stuff.item.LatexGloveItem;
import cookieg.darkrp_stuff.DarkrpStuffModElements;

@DarkrpStuffModElements.ModElement.Tag
public class DarkRPStuffItemGroup extends DarkrpStuffModElements.ModElement {
	public DarkRPStuffItemGroup(DarkrpStuffModElements instance) {
		super(instance, 2);
	}

	@Override
	public void initElements() {
		tab = new ItemGroup("tabdark_rp_stuff") {
			@OnlyIn(Dist.CLIENT)
			@Override
			public ItemStack createIcon() {
				return new ItemStack(LatexGloveItem.block);
			}

			@OnlyIn(Dist.CLIENT)
			public boolean hasSearchBar() {
				return false;
			}
		};
	}

	public static ItemGroup tab;
}
