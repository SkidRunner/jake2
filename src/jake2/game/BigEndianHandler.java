/*
 * BigEndianHandler.java
 * Copyright (C) 2003
 * 
 * $Id: BigEndianHandler.java,v 1.1 2004-07-07 19:58:52 hzi Exp $
 */
package jake2.game;


/**
 * BigEndianHandler
 */
public final class BigEndianHandler extends EndianHandler {

	/* (non-Javadoc)
	 * @see quake2.EndianHandler#BigFloat(float)
	 */
	public float BigFloat(float f) {
		return f;
	}

	/* (non-Javadoc)
	 * @see quake2.EndianHandler#BigShort(short)
	 */
	public short BigShort(short s) {
		return s;
	}

	/* (non-Javadoc)
	 * @see quake2.EndianHandler#BigLong(int)
	 */
	public int BigLong(int i) {
		return i;
	}

	/* (non-Javadoc)
	 * @see quake2.EndianHandler#LittleFloat(float)
	 */
	public float LittleFloat(float f) {
		return swapFloat(f);
	}

	/* (non-Javadoc)
	 * @see quake2.EndianHandler#LittleShort(short)
	 */
	public short LittleShort(short s) {
		return swapShort(s);
	}

	/* (non-Javadoc)
	 * @see quake2.EndianHandler#LittleLong(int)
	 */
	public int LittleLong(int i) {
		return swapInt(i);
	}

}
