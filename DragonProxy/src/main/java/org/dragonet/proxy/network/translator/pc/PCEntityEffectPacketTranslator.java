/*
 * GNU LESSER GENERAL PUBLIC LICENSE
 *                       Version 3, 29 June 2007
 *
 * Copyright (C) 2007 Free Software Foundation, Inc. <http://fsf.org/>
 * Everyone is permitted to copy and distribute verbatim copies
 * of this license document, but changing it is not allowed.
 *
 * You can view LICENCE file for details. 
 *
 * @author The Dragonet Team
 */
package org.dragonet.proxy.network.translator.pc;

import org.dragonet.proxy.network.CacheKey;
import org.dragonet.proxy.network.ClientConnection;
import org.dragonet.proxy.network.cache.CachedEntity;
import org.dragonet.proxy.network.translator.PCPacketTranslator;
import org.spacehq.mc.protocol.data.MagicValues;
import org.spacehq.mc.protocol.packet.ingame.server.entity.ServerEntityEffectPacket;

import cn.nukkit.network.protocol.MobEffectPacket;
import net.marfgamer.jraknet.RakNetPacket;
import sul.protocol.pocket101.play.MobEffect;

public class PCEntityEffectPacketTranslator implements PCPacketTranslator<ServerEntityEffectPacket> {

    @Override
    public sul.utils.Packet[] translate(ClientConnection session, ServerEntityEffectPacket packet) {
        CachedEntity entity = session.getEntityCache().get(packet.getEntityId());
        if (entity == null) {
            return new sul.utils.Packet[0];
        }
        int effectId = MagicValues.value(Integer.class, packet.getEffect());

        MobEffect eff = new MobEffect();
        eff.entityId = packet.getEntityId() == (int) session.getDataCache().get(CacheKey.PLAYER_EID) ? 0 : packet.getEntityId();
        eff.effect = effectId;
        if (eff.effect == -1) {// Is this the correct way to do this?
            return new sul.utils.Packet[0]; //Not supported
        }
        if (entity.effects.contains(effectId)) {
            eff.eventId = MobEffectPacket.EVENT_MODIFY;
        } else {
            eff.eventId = MobEffectPacket.EVENT_ADD;
            entity.effects.add(effectId);
        }
        eff.amplifier = packet.getAmplifier();
        eff.duration = packet.getDuration();
        eff.particles = packet.getShowParticles();
        
        return new sul.utils.Packet[] {eff};
    }

}
