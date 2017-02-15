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
package org.dragonet.proxy.protocol.packet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.Inet4Address;
import java.net.InetAddress;
import org.dragonet.proxy.utilities.io.PEBinaryWriter;

public class ServerHandshakePacket extends PEPacket {

    public InetAddress addr;
    public short port;
    public long session;
    public long session2;

    @Override
    public int pid() {
        return PEPacketIDs.SERVER_HANDSHAKE;
    }

    @Override
    public void encode() {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            PEBinaryWriter writer = new PEBinaryWriter(bos);
            writer.writeByte((byte) (this.pid() & 0xFF));
            writer.writeAddress(addr, port);
            writer.writeShort((short) 0);
            writer.writeAddress(Inet4Address.getByName("127.0.0.1"), (short) 0);
            writer.writeAddress(Inet4Address.getByName("0.0.0.0"), (short) 0);
            writer.writeAddress(Inet4Address.getByName("0.0.0.0"), (short) 0);
            writer.writeAddress(Inet4Address.getByName("0.0.0.0"), (short) 0);
            writer.writeAddress(Inet4Address.getByName("0.0.0.0"), (short) 0);
            writer.writeAddress(Inet4Address.getByName("0.0.0.0"), (short) 0);
            writer.writeAddress(Inet4Address.getByName("0.0.0.0"), (short) 0);
            writer.writeAddress(Inet4Address.getByName("0.0.0.0"), (short) 0);
            writer.writeAddress(Inet4Address.getByName("0.0.0.0"), (short) 0);
            writer.writeAddress(Inet4Address.getByName("0.0.0.0"), (short) 0);
            writer.writeLong(this.session);
            writer.writeLong(this.session2);
            this.setData(bos.toByteArray());
        } catch (IOException e) {
        }
    }

    @Override
    public void decode() {
    }
}
