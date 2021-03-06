package org.dragonet.proxy.network.translator.pc;

import org.dragonet.proxy.network.CacheKey;
import org.dragonet.proxy.network.ClientConnection;
import org.dragonet.proxy.network.cache.CachedEntity;
import org.dragonet.proxy.network.translator.PCPacketTranslator;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityHeadLookPacket;

import cn.nukkit.network.protocol.MoveEntityPacket;
import net.marfgamer.jraknet.RakNetPacket;
import sul.protocol.pocket101.play.MoveEntity;
import sul.utils.Tuples;

public class PCEntityHeadLookPacketTranslator implements PCPacketTranslator<ServerEntityHeadLookPacket> {

    @Override
    public sul.utils.Packet[] translate(ClientConnection session, ServerEntityHeadLookPacket packet) {
        CachedEntity entity = session.getEntityCache().get(packet.getEntityId());

        if (entity == null) {
            return new sul.utils.Packet[0];
        }

        MoveEntity me = new MoveEntity();

        me.entityId = packet.getEntityId() == (int) session.getDataCache().get(CacheKey.PLAYER_EID) ? 0L : packet.getEntityId();
        me.position = new Tuples.FloatXYZ((float) entity.x, (float) entity.y, (float) entity.z);
        me.pitch = (byte) entity.pitch;
        me.yaw = (byte) entity.yaw;
        me.headYaw = (byte) packet.getHeadYaw();

        return new sul.utils.Packet[] {me};
    }

}
