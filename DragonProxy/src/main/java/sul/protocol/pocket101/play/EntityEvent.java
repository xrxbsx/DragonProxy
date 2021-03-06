/*
 * This file was automatically generated by sel-utils and
 * released under the GNU General Public License version 3.
 *
 * License: https://github.com/sel-project/sel-utils/blob/master/LICENSE
 * Repository: https://github.com/sel-project/sel-utils
 * Generated from https://github.com/sel-project/sel-utils/blob/master/xml/protocol/pocket101.xml
 */
package sul.protocol.pocket101.play;

import sul.utils.*;

public class EntityEvent extends Packet {

	public static final byte ID = (byte)29;

	public static final boolean CLIENTBOUND = true;
	public static final boolean SERVERBOUND = true;

	// event id
	public static final byte HURT_ANIMATION = 2;
	public static final byte DEATH_ANIMATION = 3;
	public static final byte TAME_FAIL = 6;
	public static final byte TAME_SUCCESS = 7;
	public static final byte SHAKE_WET = 8;
	public static final byte USE_ITEM = 9;
	public static final byte EAT_GRASS_ANIMATION = 10;
	public static final byte FISH_HOOK_BUBBLES = 11;
	public static final byte FISH_HOOK_POSITION = 12;
	public static final byte FISH_HOOK_HOOK = 13;
	public static final byte FISH_HOOK_TEASE = 14;
	public static final byte SQUID_INK_CLOUD = 15;
	public static final byte AMBIENT_SOUND = 16;
	public static final byte RESPAWN = 17;

	public long entityId;
	public byte eventId;
	public int unknown2;

	public EntityEvent() {}

	public EntityEvent(long entityId, byte eventId, int unknown2) {
		this.entityId = entityId;
		this.eventId = eventId;
		this.unknown2 = unknown2;
	}

	@Override
	public int length() {
		return Buffer.varlongLength(entityId) + Buffer.varintLength(unknown2) + 2;
	}

	@Override
	public byte[] encode() {
		this._buffer = new byte[this.length()];
		this.writeBigEndianByte(ID);
		this.writeVarlong(entityId);
		this.writeBigEndianByte(eventId);
		this.writeVarint(unknown2);
		return this.getBuffer();
	}

	@Override
	public void decode(byte[] buffer) {
		this._buffer = buffer;
		readBigEndianByte();
		entityId=this.readVarlong();
		eventId=readBigEndianByte();
		unknown2=this.readVarint();
	}

	public static EntityEvent fromBuffer(byte[] buffer) {
		EntityEvent ret = new EntityEvent();
		ret.decode(buffer);
		return ret;
	}

	@Override
	public String toString() {
		return "EntityEvent(entityId: " + this.entityId + ", eventId: " + this.eventId + ", unknown2: " + this.unknown2 + ")";
	}

}
