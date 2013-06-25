package cn.ohyeah.stb.util;

/**
 * �ֽ����鴦����
 * @author maqian
 * @version 1.0
 */
public class BytesUtil {

	/**
	 * ��byte���͵�valueд���ֽ�����dest��indexλ��
	 * @param dest
	 * @param index
	 * @param value
	 */
	public static void writeByte(byte[] dest, int index, byte value) {
		dest[index] = value;
	}
	
	/**
	 * ��byte[]���͵�valueд���ֽ�����dest��indexλ��,��д������ǰ��д������ĳ���
	 * @param dest
	 * @param index
	 * @param value
	 */
	public static void writeByteArray(byte[] dest, int index, byte[] value) {
		writeInt(dest, index, value.length);
		writeBytes(dest, index+4, value);
	}
	
	/**
	 * ��short���͵�valueд���ֽ�����dest��indexλ��
	 * @param dest
	 * @param index
	 * @param value
	 */
	public static void writeShort(byte[] dest, int index, short value) {
		dest[index] = (byte)(value>>>8);
		dest[index+1] = (byte)(value&0XFF);
	}
	
	/**
	 * ��int���͵�valueд���ֽ�����dest��indexλ��
	 * @param dest
	 * @param index
	 * @param value
	 */
	public static void writeInt(byte[] dest, int index, int value) {
		dest[index] = (byte)(value>>>24);
		dest[index+1] = (byte)((value>>>16)&0XFF);
		dest[index+2] = (byte)((value>>>8)&0XFF);
		dest[index+3] = (byte)(value&0XFF);
	}
	
	/**
	 * ��long���͵�valueд���ֽ�����dest��indexλ��
	 * @param dest
	 * @param index
	 * @param value
	 */
	public static void writeLong(byte[] dest, int index, long value) {
		dest[index] = (byte)(value>>>56);
		dest[index+1] = (byte)((value>>>48)&0XFF);
		dest[index+2] = (byte)((value>>>40)&0XFF);
		dest[index+3] = (byte)((value>>>32)&0XFF);
		dest[index+4] = (byte)((value>>>24)&0XFF);
		dest[index+5] = (byte)((value>>>16)&0XFF);
		dest[index+6] = (byte)((value>>>8)&0XFF);
		dest[index+7] = (byte)(value&0XFF);
	}
	
	/**
	 * ��float���͵�valueд���ֽ�����dest��indexλ��
	 * @param dest
	 * @param index
	 * @param value
	 */
	public static void writeFloat(byte[] dest, int index, float value) {
		writeInt(dest, index, Float.floatToIntBits(value));
	}
	
	/**
	 * ��double���͵�valueд���ֽ�����dest��indexλ��
	 * @param dest
	 * @param index
	 * @param value
	 */
	public static void writeDouble(byte[] dest, int index, double value) {
		writeLong(dest, index, Double.doubleToLongBits(value));
	}
	
	/**
	 * ��byte[]���͵�valueд���ֽ�����dest��indexλ��,��д�����鳤�ȣ�ֱ��д������
	 * @param dest
	 * @param index
	 * @param value
	 */
	public static void writeBytes(byte[] dest, int index, byte[] value) {
		System.arraycopy(value, 0, dest, index, value.length);
	}
	
	/**
	 * ��byte[]����value��ָ�����֣�д���ֽ�����dest��indexλ��,��д�����鳤�ȣ�ֱ��д������
	 * @param dest
	 * @param index
	 * @param value
	 * @param offset
	 * @param len
	 */
	public static void writeBytes(byte[] dest, int index, byte[] value, int offset, int len) {
		System.arraycopy(value, offset, dest, index, len);
	}
	
	/**
	 * ��src��indexλ�ã���ȡbyte
	 * @param src
	 * @param index
	 */
	public static byte readByte(byte[] src, int index) {
		return src[index];
	}
	
	/**
	 * ��src��indexλ�ã���ȡbyte[]���ȶ�ȡint���͵����鳤��len���ٸ���len��ȡ����
	 * @param src
	 * @param index
	 */
	public static byte[] readByteArray(byte[] src, int index) {
		int len = readInt(src, index);
		return readBytes(src, index+4, len);
	}
	
	/**
	 * ��src��indexλ�ã���ȡshort
	 * @param src
	 * @param index
	 */
	public static short readShort(byte[] src, int index) {
		return (short)(((src[index]<<8)&0XFF00)|(src[index+1]&0XFF));
	}
	
	/**
	 * ��src��indexλ�ã���ȡint
	 * @param src
	 * @param index
	 */
	public static int readInt(byte[] src, int index) {
		return (((int)src[index])<<24)
				|(((int)src[index+1]&0XFF)<<16)
				|(((int)src[index+2]&0XFF)<<8)
				|((int)src[index+3]&0XFF);
	}
	
	/**
	 * ��src��indexλ�ã���ȡlong
	 * @param src
	 * @param index
	 */
	public static long readLong(byte[] src, int index) {
		return (((int)src[index])<<56)
				|(((int)src[index+1]&0XFF)<<48)
				|(((int)src[index+2]&0XFF)<<40)
				|(((int)src[index+3]&0XFF)<<32)
				|(((int)src[index+4]&0XFF)<<24)
				|(((int)src[index+5]&0XFF)<<16)
				|(((int)src[index+6]&0XFF)<<8)
				|((int)src[index+7]&0XFF);
	}
	
	/**
	 * ��src��indexλ�ã���ȡfloat
	 * @param src
	 * @param index
	 */
	public static float readFloat(byte[] src, int index) {
		return Float.intBitsToFloat(readInt(src, index));
	}
	
	/**
	 * ��src��indexλ�ã���ȡdouble
	 * @param src
	 * @param index
	 */
	public static double readDouble(byte[] src, int index) {
		return Double.longBitsToDouble(readLong(src, index));
	}
	
	/**
	 * ��src��indexλ�ã���ȡָ������len��byte[]
	 * @param src
	 * @param index
	 * @param len
	 */
	public static byte[] readBytes(byte[] src, int index, int len) {
		byte[] value = new byte[len];
		System.arraycopy(src, index, value, 0, len);
		return value;
	}
	
}
