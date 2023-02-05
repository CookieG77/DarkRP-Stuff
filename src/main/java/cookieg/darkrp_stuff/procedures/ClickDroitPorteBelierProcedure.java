package cookieg.darkrp_stuff.procedures;

import net.minecraft.world.IWorld;
import net.minecraft.util.math.BlockPos;
import net.minecraft.item.ItemStack;

import java.util.Map;

import cookieg.darkrp_stuff.block.LockableIronDoorTopOpenBlock;
import cookieg.darkrp_stuff.block.LockableIronDoorTopClosedBlock;
import cookieg.darkrp_stuff.block.LockableIronDoorBottomOpenBlock;
import cookieg.darkrp_stuff.block.LockableIronDoorBottomClosedBlock;
import cookieg.darkrp_stuff.block.AltLockableIronDoorTopOpenBlock;
import cookieg.darkrp_stuff.block.AltLockableIronDoorTopClosedBlock;
import cookieg.darkrp_stuff.block.AltLockableIronDoorBottomOpenBlock;
import cookieg.darkrp_stuff.block.AltLockableIronDoorBottomClosedBlock;
import cookieg.darkrp_stuff.DarkrpStuffMod;

public class ClickDroitPorteBelierProcedure {

	public static void executeProcedure(Map<String, Object> dependencies) {
		if (dependencies.get("world") == null) {
			if (!dependencies.containsKey("world"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency world for procedure ClickDroitPorteBelier!");
			return;
		}
		if (dependencies.get("x") == null) {
			if (!dependencies.containsKey("x"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency x for procedure ClickDroitPorteBelier!");
			return;
		}
		if (dependencies.get("y") == null) {
			if (!dependencies.containsKey("y"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency y for procedure ClickDroitPorteBelier!");
			return;
		}
		if (dependencies.get("z") == null) {
			if (!dependencies.containsKey("z"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency z for procedure ClickDroitPorteBelier!");
			return;
		}
		if (dependencies.get("itemstack") == null) {
			if (!dependencies.containsKey("itemstack"))
				DarkrpStuffMod.LOGGER.warn("Failed to load dependency itemstack for procedure ClickDroitPorteBelier!");
			return;
		}
		IWorld world = (IWorld) dependencies.get("world");
		double x = dependencies.get("x") instanceof Integer ? (int) dependencies.get("x") : (double) dependencies.get("x");
		double y = dependencies.get("y") instanceof Integer ? (int) dependencies.get("y") : (double) dependencies.get("y");
		double z = dependencies.get("z") instanceof Integer ? (int) dependencies.get("z") : (double) dependencies.get("z");
		ItemStack itemstack = (ItemStack) dependencies.get("itemstack");
		boolean opening = false;
		if ((world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorTopClosedBlock.block
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorBottomClosedBlock.block
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorTopClosedBlock.block
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorBottomClosedBlock.block
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorTopOpenBlock.block
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == LockableIronDoorBottomOpenBlock.block
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorTopOpenBlock.block
				|| (world.getBlockState(new BlockPos(x, y, z))).getBlock() == AltLockableIronDoorBottomOpenBlock.block) {
			itemstack.getOrCreateTag().putBoolean("Opening", (true));
			itemstack.getOrCreateTag().putBoolean("Opened", (false));
			itemstack.getOrCreateTag().putDouble("X", x);
			itemstack.getOrCreateTag().putDouble("Y", y);
			itemstack.getOrCreateTag().putDouble("Z", z);
			itemstack.getOrCreateTag().putDouble("OpeningTime", 0);
		}
	}
}
