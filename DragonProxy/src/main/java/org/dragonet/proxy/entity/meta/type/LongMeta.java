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
package org.dragonet.proxy.entity.meta.type;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;

import org.dragonet.proxy.entity.meta.EntityMetaData;
import org.dragonet.proxy.entity.meta.EntityMetaDataObject;

public class LongMeta implements EntityMetaDataObject {

    public long data;

    public LongMeta(long data) {
        this.data = data;
    }

    @Override
    public int type() {
        return EntityMetaData.Constants.DATA_TYPE_LONG;
    }

    @Override
    public byte[] encode() {
        ByteBuffer buff = ByteBuffer.allocate(8);
        buff.order(ByteOrder.LITTLE_ENDIAN);
        buff.putLong(this.data);
        return buff.array();
    }

}
