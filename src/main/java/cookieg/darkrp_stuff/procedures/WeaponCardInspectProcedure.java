package cookieg.darkrp_stuff.procedures;

import net.minecraft.item.ItemStack;
import net.minecraft.entity.Entity;

import java.util.Map;

import cookieg.darkrp_stuff.DarkrpStuffModVariables;
import cookieg.darkrp_stuff.DarkrpStuffMod;

public class WeaponCardInspectProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("entity") == null) {
			if (!dependencies.containsKey("entity"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency entity for procedure WeaponCardInspect!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency itemstack for procedure WeaponCardInspect!");
			return;
		}
		Entity entity = (Entity) dependencies.get("entity");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		{
			String _setval = (itemstack.getOrCreateTag().getString("Name"));
			entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.IdentityCardSurname = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			String _setval = (itemstack.getOrCreateTag().getString("GivenName"));
			entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.IdentityCardGivenName = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			String _setval = (itemstack.getOrCreateTag().getString("OriginCountry"));
			entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.IdentityCardOriginCountry = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			String _setval = (itemstack.getOrCreateTag().getString("ExpirationDate"));
			entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.IdentityCardExpiration = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
		{
			String _setval = (itemstack.getOrCreateTag().getString("ResidentSince"));
			entity.getCapability(DarkrpStuffModVariables.PLAYER_VARIABLES_CAPABILITY, null).ifPresent(capability -> {
				capability.IdentityCardResidentSince = _setval;
				capability.syncPlayerVariables(entity);
			});
		}
	}
}
