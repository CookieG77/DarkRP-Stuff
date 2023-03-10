package cookieg.darkrp_stuff;

import net.minecraftforge.fml.network.PacketDistributor;
import net.minecraftforge.fml.network.NetworkEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.common.util.FakePlayer;
import net.minecraftforge.common.capabilities.ICapabilitySerializable;
import net.minecraftforge.common.capabilities.CapabilityManager;
import net.minecraftforge.common.capabilities.CapabilityInject;
import net.minecraftforge.common.capabilities.Capability;

import net.minecraft.world.storage.WorldSavedData;
import net.minecraft.world.server.ServerWorld;
import net.minecraft.world.World;
import net.minecraft.world.IWorld;
import net.minecraft.world.IServerWorld;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.Direction;
import net.minecraft.network.PacketBuffer;
import net.minecraft.nbt.INBT;
import net.minecraft.nbt.CompoundNBT;
import net.minecraft.entity.player.ServerPlayerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.Entity;
import net.minecraft.client.Minecraft;

import java.util.function.Supplier;

public class DarkrpStuffModVariables {
	public DarkrpStuffModVariables(DarkrpStuffModElements elements) {
		elements.addNetworkMessage(WorldSavedDataSyncMessage.class, WorldSavedDataSyncMessage::buffer, WorldSavedDataSyncMessage::new,
				WorldSavedDataSyncMessage::handler);
		elements.addNetworkMessage(PlayerVariablesSyncMessage.class, PlayerVariablesSyncMessage::buffer, PlayerVariablesSyncMessage::new,
				PlayerVariablesSyncMessage::handler);
		FMLJavaModLoadingContext.get().getModEventBus().addListener(this::init);
	}

	private void init(FMLCommonSetupEvent event) {
		CapabilityManager.INSTANCE.register(PlayerVariables.class, new PlayerVariablesStorage(), PlayerVariables::new);
	}

	@SubscribeEvent
	public void onPlayerLoggedIn(PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.getPlayer().world.isRemote()) {
			WorldSavedData mapdata = MapVariables.get(event.getPlayer().world);
			WorldSavedData worlddata = WorldVariables.get(event.getPlayer().world);
			if (mapdata != null)
				DarkrpStuffMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new WorldSavedDataSyncMessage(0, mapdata));
			if (worlddata != null)
				DarkrpStuffMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new WorldSavedDataSyncMessage(1, worlddata));
		}
	}

	@SubscribeEvent
	public void onPlayerChangedDimension(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (!event.getPlayer().world.isRemote()) {
			WorldSavedData worlddata = WorldVariables.get(event.getPlayer().world);
			if (worlddata != null)
				DarkrpStuffMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) event.getPlayer()),
						new WorldSavedDataSyncMessage(1, worlddata));
		}
	}

	public static class WorldVariables extends WorldSavedData {
		public static final String DATA_NAME = "darkrp_stuff_worldvars";

		public WorldVariables() {
			super(DATA_NAME);
		}

		public WorldVariables(String s) {
			super(s);
		}

		@Override
		public void read(CompoundNBT nbt) {
		}

		@Override
		public CompoundNBT write(CompoundNBT nbt) {
			return nbt;
		}

		public void syncData(IWorld world) {
			this.markDirty();
			if (world instanceof World && !world.isRemote())
				DarkrpStuffMod.PACKET_HANDLER.send(PacketDistributor.DIMENSION.with(((World) world)::getDimensionKey),
						new WorldSavedDataSyncMessage(1, this));
		}

		static WorldVariables clientSide = new WorldVariables();

		public static WorldVariables get(IWorld world) {
			if (world instanceof ServerWorld) {
				return ((ServerWorld) world).getSavedData().getOrCreate(WorldVariables::new, DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class MapVariables extends WorldSavedData {
		public static final String DATA_NAME = "darkrp_stuff_mapvars";
		public double modtick = 0;

		public MapVariables() {
			super(DATA_NAME);
		}

		public MapVariables(String s) {
			super(s);
		}

		@Override
		public void read(CompoundNBT nbt) {
			modtick = nbt.getDouble("modtick");
		}

		@Override
		public CompoundNBT write(CompoundNBT nbt) {
			nbt.putDouble("modtick", modtick);
			return nbt;
		}

		public void syncData(IWorld world) {
			this.markDirty();
			if (world instanceof World && !world.isRemote())
				DarkrpStuffMod.PACKET_HANDLER.send(PacketDistributor.ALL.noArg(), new WorldSavedDataSyncMessage(0, this));
		}

		static MapVariables clientSide = new MapVariables();

		public static MapVariables get(IWorld world) {
			if (world instanceof IServerWorld) {
				return ((IServerWorld) world).getWorld().getServer().getWorld(World.OVERWORLD).getSavedData().getOrCreate(MapVariables::new,
						DATA_NAME);
			} else {
				return clientSide;
			}
		}
	}

	public static class WorldSavedDataSyncMessage {
		public int type;
		public WorldSavedData data;

		public WorldSavedDataSyncMessage(PacketBuffer buffer) {
			this.type = buffer.readInt();
			this.data = this.type == 0 ? new MapVariables() : new WorldVariables();
			this.data.read(buffer.readCompoundTag());
		}

		public WorldSavedDataSyncMessage(int type, WorldSavedData data) {
			this.type = type;
			this.data = data;
		}

		public static void buffer(WorldSavedDataSyncMessage message, PacketBuffer buffer) {
			buffer.writeInt(message.type);
			buffer.writeCompoundTag(message.data.write(new CompoundNBT()));
		}

		public static void handler(WorldSavedDataSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					if (message.type == 0)
						MapVariables.clientSide = (MapVariables) message.data;
					else
						WorldVariables.clientSide = (WorldVariables) message.data;
				}
			});
			context.setPacketHandled(true);
		}
	}

	@CapabilityInject(PlayerVariables.class)
	public static Capability<PlayerVariables> PLAYER_VARIABLES_CAPABILITY = null;

	@SubscribeEvent
	public void onAttachCapabilities(AttachCapabilitiesEvent<Entity> event) {
		if (event.getObject() instanceof PlayerEntity && !(event.getObject() instanceof FakePlayer))
			event.addCapability(new ResourceLocation("darkrp_stuff", "player_variables"), new PlayerVariablesProvider());
	}

	private static class PlayerVariablesProvider implements ICapabilitySerializable<INBT> {
		private final LazyOptional<PlayerVariables> instance = LazyOptional.of(PLAYER_VARIABLES_CAPABILITY::getDefaultInstance);

		@Override
		public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
			return cap == PLAYER_VARIABLES_CAPABILITY ? instance.cast() : LazyOptional.empty();
		}

		@Override
		public INBT serializeNBT() {
			return PLAYER_VARIABLES_CAPABILITY.getStorage().writeNBT(PLAYER_VARIABLES_CAPABILITY, this.instance.orElseThrow(RuntimeException::new),
					null);
		}

		@Override
		public void deserializeNBT(INBT nbt) {
			PLAYER_VARIABLES_CAPABILITY.getStorage().readNBT(PLAYER_VARIABLES_CAPABILITY, this.instance.orElseThrow(RuntimeException::new), null,
					nbt);
		}
	}

	private static class PlayerVariablesStorage implements Capability.IStorage<PlayerVariables> {
		@Override
		public INBT writeNBT(Capability<PlayerVariables> capability, PlayerVariables instance, Direction side) {
			CompoundNBT nbt = new CompoundNBT();
			nbt.putDouble("AnimationGUI", instance.AnimationGUI);
			nbt.putBoolean("Freezed", instance.Freezed);
			nbt.putBoolean("Handcuffed", instance.Handcuffed);
			nbt.putString("IdentityCardExpiration", instance.IdentityCardExpiration);
			nbt.putString("IdentityCardGivenName", instance.IdentityCardGivenName);
			nbt.putString("IdentityCardOriginCountry", instance.IdentityCardOriginCountry);
			nbt.putString("IdentityCardResidentSince", instance.IdentityCardResidentSince);
			nbt.putString("IdentityCardSurname", instance.IdentityCardSurname);
			nbt.putDouble("LockAnimationGui", instance.LockAnimationGui);
			nbt.putDouble("Lockpick_position", instance.Lockpick_position);
			nbt.putBoolean("Lockpicking", instance.Lockpicking);
			nbt.putString("MethMixerInfo1", instance.MethMixerInfo1);
			nbt.putBoolean("TurningLock", instance.TurningLock);
			return nbt;
		}

		@Override
		public void readNBT(Capability<PlayerVariables> capability, PlayerVariables instance, Direction side, INBT inbt) {
			CompoundNBT nbt = (CompoundNBT) inbt;
			instance.AnimationGUI = nbt.getDouble("AnimationGUI");
			instance.Freezed = nbt.getBoolean("Freezed");
			instance.Handcuffed = nbt.getBoolean("Handcuffed");
			instance.IdentityCardExpiration = nbt.getString("IdentityCardExpiration");
			instance.IdentityCardGivenName = nbt.getString("IdentityCardGivenName");
			instance.IdentityCardOriginCountry = nbt.getString("IdentityCardOriginCountry");
			instance.IdentityCardResidentSince = nbt.getString("IdentityCardResidentSince");
			instance.IdentityCardSurname = nbt.getString("IdentityCardSurname");
			instance.LockAnimationGui = nbt.getDouble("LockAnimationGui");
			instance.Lockpick_position = nbt.getDouble("Lockpick_position");
			instance.Lockpicking = nbt.getBoolean("Lockpicking");
			instance.MethMixerInfo1 = nbt.getString("MethMixerInfo1");
			instance.TurningLock = nbt.getBoolean("TurningLock");
		}
	}

	public static class PlayerVariables {
		public double AnimationGUI = 0.0;
		public boolean Freezed = false;
		public boolean Handcuffed = false;
		public String IdentityCardExpiration = "\"28/07/2025\"";
		public String IdentityCardGivenName = "\"LeGentil\"";
		public String IdentityCardOriginCountry = "\"\"France";
		public String IdentityCardResidentSince = "\"29/12/2004\"";
		public String IdentityCardSurname = "\"\"Cookie";
		public double LockAnimationGui = 0;
		public double Lockpick_position = 0;
		public boolean Lockpicking = false;
		public String MethMixerInfo1 = "\"NA\"";
		public boolean TurningLock = false;

		public void syncPlayerVariables(Entity entity) {
			if (entity instanceof ServerPlayerEntity)
				DarkrpStuffMod.PACKET_HANDLER.send(PacketDistributor.PLAYER.with(() -> (ServerPlayerEntity) entity),
						new PlayerVariablesSyncMessage(this));
		}
	}

	@SubscribeEvent
	public void onPlayerLoggedInSyncPlayerVariables(PlayerEvent.PlayerLoggedInEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void onPlayerRespawnedSyncPlayerVariables(PlayerEvent.PlayerRespawnEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void onPlayerChangedDimensionSyncPlayerVariables(PlayerEvent.PlayerChangedDimensionEvent event) {
		if (!event.getPlayer().world.isRemote())
			((PlayerVariables) event.getPlayer().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()))
					.syncPlayerVariables(event.getPlayer());
	}

	@SubscribeEvent
	public void clonePlayer(PlayerEvent.Clone event) {
		PlayerVariables original = ((PlayerVariables) event.getOriginal().getCapability(PLAYER_VARIABLES_CAPABILITY, null)
				.orElse(new PlayerVariables()));
		PlayerVariables clone = ((PlayerVariables) event.getEntity().getCapability(PLAYER_VARIABLES_CAPABILITY, null).orElse(new PlayerVariables()));
		clone.AnimationGUI = original.AnimationGUI;
		clone.Freezed = original.Freezed;
		clone.IdentityCardExpiration = original.IdentityCardExpiration;
		clone.IdentityCardGivenName = original.IdentityCardGivenName;
		clone.IdentityCardOriginCountry = original.IdentityCardOriginCountry;
		clone.IdentityCardResidentSince = original.IdentityCardResidentSince;
		clone.IdentityCardSurname = original.IdentityCardSurname;
		clone.LockAnimationGui = original.LockAnimationGui;
		clone.MethMixerInfo1 = original.MethMixerInfo1;
		if (!event.isWasDeath()) {
			clone.Handcuffed = original.Handcuffed;
			clone.Lockpick_position = original.Lockpick_position;
			clone.Lockpicking = original.Lockpicking;
			clone.TurningLock = original.TurningLock;
		}
	}

	public static class PlayerVariablesSyncMessage {
		public PlayerVariables data;

		public PlayerVariablesSyncMessage(PacketBuffer buffer) {
			this.data = new PlayerVariables();
			new PlayerVariablesStorage().readNBT(null, this.data, null, buffer.readCompoundTag());
		}

		public PlayerVariablesSyncMessage(PlayerVariables data) {
			this.data = data;
		}

		public static void buffer(PlayerVariablesSyncMessage message, PacketBuffer buffer) {
			buffer.writeCompoundTag((CompoundNBT) new PlayerVariablesStorage().writeNBT(null, message.data, null));
		}

		public static void handler(PlayerVariablesSyncMessage message, Supplier<NetworkEvent.Context> contextSupplier) {
			NetworkEvent.Context context = contextSupplier.get();
			context.enqueueWork(() -> {
				if (!context.getDirection().getReceptionSide().isServer()) {
					PlayerVariables variables = ((PlayerVariables) Minecraft.getInstance().player.getCapability(PLAYER_VARIABLES_CAPABILITY, null)
							.orElse(new PlayerVariables()));
					variables.AnimationGUI = message.data.AnimationGUI;
					variables.Freezed = message.data.Freezed;
					variables.Handcuffed = message.data.Handcuffed;
					variables.IdentityCardExpiration = message.data.IdentityCardExpiration;
					variables.IdentityCardGivenName = message.data.IdentityCardGivenName;
					variables.IdentityCardOriginCountry = message.data.IdentityCardOriginCountry;
					variables.IdentityCardResidentSince = message.data.IdentityCardResidentSince;
					variables.IdentityCardSurname = message.data.IdentityCardSurname;
					variables.LockAnimationGui = message.data.LockAnimationGui;
					variables.Lockpick_position = message.data.Lockpick_position;
					variables.Lockpicking = message.data.Lockpicking;
					variables.MethMixerInfo1 = message.data.MethMixerInfo1;
					variables.TurningLock = message.data.TurningLock;
				}
			});
			context.setPacketHandled(true);
		}
	}
}
