package com.min01.oceanicrealms.network;

import java.util.UUID;
import java.util.function.Supplier;

import com.min01.oceanicrealms.entity.living.EntityCrab;
import com.min01.oceanicrealms.util.OceanicUtil;

import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.world.entity.Entity;
import net.minecraftforge.network.NetworkEvent;

public class UpdateCrabDancePacket 
{
	private final UUID entityUUID;
	private final boolean dance;
	
	public UpdateCrabDancePacket(UUID entityUUID, boolean dance) 
	{
		this.entityUUID = entityUUID;
		this.dance = dance;
	}

	public UpdateCrabDancePacket(FriendlyByteBuf buf)
	{
		this.entityUUID = buf.readUUID();
		this.dance = buf.readBoolean();
	}

	public void encode(FriendlyByteBuf buf)
	{
		buf.writeUUID(this.entityUUID);
		buf.writeBoolean(this.dance);
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
					crab.isDance = message.dance;
				}
			});

			ctx.get().setPacketHandled(true);
			return true;
		}
	}
}
