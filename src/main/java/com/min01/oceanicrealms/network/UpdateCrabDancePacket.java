package com.min01.oceanicrealms.network;

import java.util.UUID;
import java.util.function.Supplier;

import com.min01.oceanicrealms.entity.living.EntityCrab;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.core.BlockPos;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

public class UpdateCrabDancePacket 
{
	private final UUID entityUUID;
	private final boolean isDance;
	private final BlockPos jukebox;
	
	public UpdateCrabDancePacket(UUID entityUUID, boolean isDance, BlockPos jukebox) 
	{
		this.entityUUID = entityUUID;
		this.isDance = isDance;
		this.jukebox = jukebox;
	}

	public UpdateCrabDancePacket(FriendlyByteBuf buf)
	{
		this.entityUUID = buf.readUUID();
		this.isDance = buf.readBoolean();
		this.jukebox = buf.readBlockPos();
	}

	public void encode(FriendlyByteBuf buf)
	{
		buf.writeUUID(this.entityUUID);
		buf.writeBoolean(this.isDance);
		buf.writeBlockPos(this.jukebox);
	}
	
	public static class Handler 
	{
		public static boolean onMessage(UpdateCrabDancePacket message, Supplier<NetworkEvent.Context> ctx) 
		{
			ctx.get().enqueueWork(() ->
			{
				Entity entity = OceanicUtil.getEntityByUUID(ctx.get().getSender().level, message.entityUUID);
				if(entity instanceof EntityCrab crab) 
				{
					crab.jukebox = message.jukebox.equals(BlockPos.ZERO) ? null : message.jukebox;
					crab.setDance(message.isDance);
				}
			});

			ctx.get().setPacketHandled(true);
			return true;
		}
	}
}
