package cn.ohyeah.stb.util;

import java.io.UnsupportedEncodingException;
import java.util.Vector;

/**
 * ת������
 * @author maqian
 * @version 1.0
 */
public class ConvertUtil {
	public static final byte BigEndian = 0;
	public static final byte LittleEndian = 1;
	
	/*16����ת����*/
	private static char[] hexTable = {
		'0', '1', '2', '3',
		'4', '5', '6', '7',
		'8', '9', 'A', 'B',
		'C', 'D', 'E', 'F'
	};
	
	/**
	 * �ֽ�����ת��Ϊ16�����ַ���
	 * @param srcBytes
	 */
	public static String bytesToHexStr(byte[] srcBytes) {
		String dest = "";
		for (int i = 0; i < srcBytes.length; ++i) {
			int high = (srcBytes[i]>>4)&0XF;
			int low = srcBytes[i]&0XF;
			dest += hexTable[high];
			dest += hexTable[low];
		}
		return dest;
	}
	
	/**
	 * 16�����ַ���ת��Ϊ�ֽ�����
	 * @param hexStr
	 */
	public static byte[] hexStrToBytes(String hexStr) {
		char[] srcChars = hexStr.toCharArray(); 
		byte[] srcBytes = new byte[srcChars.length>>1];
		int pos = 0;
		for (int i = 0; i < srcBytes.length; ++i) {
			int high = Character.digit(srcChars[pos], 16);
			++pos;
			int low = Character.digit(srcChars[pos], 16);
			++pos;
			srcBytes[i] = (byte)(((high<<4)|low)&0XFF);
		}
		return srcBytes;
	}
	
	/**
	 * stringת��ΪGB2312�����16�����ַ���
	 * @param str
	 */
	public static String strToHexStr(String str) {
		return strToHexStr(str, "GB2312");
	}
	
	/**
	 * stringת��encoding�����16�����ַ���
	 * @param str
	 * @param encoding
	 */
	public static String strToHexStr(String str, String encoding)
	{
		if (str == null) {
			return null;
		}
		try {
			return bytesToHexStr(str.getBytes(encoding));
		}
		catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * GB2312�����16�����ַ���ת��Ϊstring
	 * @param hexStr
	 */
	public static String hexStrToStr(String hexStr) {
		return hexStrToStr(hexStr, "GB2312");
	}
	
	/**
	 * encoding�����16�����ַ���ת��Ϊstring
	 * @param hexStr
	 * @param encoding
	 */
	public static String hexStrToStr(String hexStr, String encoding)
	{
		if (hexStr == null) {
			return null;
		}
		if (hexStr.length() <= 0) {
			return "";
		}
		try {
			return new String(hexStrToBytes(hexStr), encoding);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	/**
	 * ������ֽ����byte����ת��Ϊlong
	 * @param bytes
	 */
	public static long toLongBigEndian(byte[] bytes)
	{
		return toLong(bytes, BigEndian);
	}
	
	/**
	 * ��С���ֽ����byte����ת��Ϊlong
	 * @param bytes
	 */
	public static long toLongLittleEndian(byte[] bytes)
	{
		return toLong(bytes, LittleEndian);
	}
	
	/**
	 * ����endianָ�����ֽ���byte����ת��Ϊlong
	 * @param bytes
	 * @param endian
	 */
	public static long toLong(byte[] bytes, int endian)
	{
		long value = 0;
		if (endian == BigEndian)
		{
			value = (((int)bytes[0])<<56)
					|(((int)bytes[1]&0XFF)<<48)
					|(((int)bytes[2]&0XFF)<<40)
					|(((int)bytes[3]&0XFF)<<32)
					|(((int)bytes[4]&0XFF)<<24)
					|(((int)bytes[5]&0XFF)<<16)
					|(((int)bytes[6]&0XFF)<<8)
					|((int)bytes[7]&0XFF);
		}
		else
		{
			value = (((int)bytes[7])<<56)
					|(((int)bytes[6]&0XFF)<<48)
					|(((int)bytes[5]&0XFF)<<40)
					|(((int)bytes[4]&0XFF)<<32)
					|(((int)bytes[3]&0XFF)<<24)
					|(((int)bytes[2]&0XFF)<<16)
					|(((int)bytes[1]&0XFF)<<8)
					|((int)bytes[0]&0XFF);
		}
		return value;
	}
	
	/**
	 * ������ֽ����byte����ת��Ϊint
	 * @param bytes
	 */
	public static int toIntBigEndian(byte[] bytes)
	{
		return toInt(bytes, BigEndian);
	}
	
	/**
	 * ��С���ֽ����byte����ת��Ϊint
	 * @param bytes
	 */
	public static int toIntLittleEndian(byte[] bytes)
	{
		return toInt(bytes, LittleEndian);
	}
	
	/**
	 * ����endianָ�����ֽ���byte����ת��Ϊint
	 * @param bytes
	 * @param endian
	 */
	public static int toInt(byte[] bytes, int endian)
	{
		int value = 0;
		if (endian == BigEndian)
		{
			value = (((int)bytes[0])<<24)
					|(((int)bytes[1]&0XFF)<<16)
					|(((int)bytes[2]&0XFF)<<8)
					|((int)bytes[3]&0XFF);
		}
		else
		{
			value = (((int)bytes[3])<<24)
					|(((int)bytes[2]&0XFF)<<16)
					|(((int)bytes[1]&0XFF)<<8)
					|((int)bytes[0]&0XFF);
		}
		return value;
	}
	
	/**
	 * ������ֽ����byte����ת��Ϊshort
	 * @param bytes
	 */
	public static short toShortBigEndian(byte[] bytes)
	{
		return toShort(bytes, BigEndian);
	}
	
	/**
	 * ��С���ֽ����byte����ת��Ϊshort
	 * @param bytes
	 */
	public static short toShortLittleEndian(byte[] bytes)
	{
		return toShort(bytes, LittleEndian);
	}
	
	/**
	 * ����endianָ�����ֽ���byte����ת��Ϊshort
	 * @param bytes
	 * @param endian
	 */
	public static short toShort(byte[] bytes, int endian)
	{
		short value = 0;
		if (endian == BigEndian)
		{
			value = (short)(((bytes[0]<<8)&0XFF00)|(bytes[1]&0XFF));
		}
		else
		{
			value = (short)(((bytes[1]<<8)&0XFF00)|(bytes[0]&0XFF));
		}
		return value;
	
	}
	
	/**
	 * ����endianָ�����ֽ���shortת��byte����
	 * @param value
	 * @param endian
	 */
	public static byte[] toBytes(short value, int endian) 
	{
		byte[] bytes = new byte[2];
		if (endian == BigEndian) {
			bytes[0] = (byte)(value>>>8);
			bytes[1] = (byte)(value&0XFF);
		}
		else {
			bytes[0] = (byte)(value&0XFF);
			bytes[1] = (byte)(value>>>8);
		}
		return bytes;
	}
	
	/**
	 * ��shortת��Ϊ����ֽ����byte����
	 * @param value
	 */
	public static byte[] toBytesBigEndian(short value)
	{
		return toBytes(value, BigEndian);
	}
	
	/**
	 * ��shortת��ΪС���ֽ����byte����
	 * @param value
	 */
	public static byte[] toBytesLittleEndian(short value)
	{
		return toBytes(value, LittleEndian);
	}
	
	/**
	 * ����endianָ�����ֽ���intת��byte����
	 * @param value
	 * @param endian
	 */
	public static byte[] toBytes(int value, int endian)
	{
		byte[] bytes = new byte[4];
		if (endian == BigEndian) {
			bytes[0] = (byte)(value>>>24);
			bytes[1] = (byte)((value>>>16)&0XFF);
			bytes[2] = (byte)((value>>>8)&0XFF);
			bytes[3] = (byte)(value&0XFF);
		}
		else {
			bytes[0] = (byte)(value&0XFF);
			bytes[1] = (byte)((value>>>8)&0XFF);
			bytes[2] = (byte)((value>>>16)&0XFF);
			bytes[3] = (byte)(value>>>24);
		}
		return bytes;
	}
	
	/**
	 * ��intת��Ϊ����ֽ����byte����
	 * @param value
	 */
	public static byte[] toBytesBigEndian(int value)
	{
		return toBytes(value, BigEndian);
	}
	
	/**
	 * ��intת��ΪС���ֽ����byte����
	 * @param value
	 */
	public static byte[] toBytesLittleEndian(int value)
	{
		return toBytes(value, LittleEndian);
	}
	
	/**
	 * ����endianָ�����ֽ���longת��byte����
	 * @param value
	 * @param endian
	 */
	public static byte[] toBytes(long value, int endian) {
		byte[] bytes = new byte[8];
		if (endian == BigEndian) {
			bytes[0] = (byte)(value>>>56);
			bytes[1] = (byte)((value>>>48)&0XFF);
			bytes[2] = (byte)((value>>>40)&0XFF);
			bytes[3] = (byte)((value>>>32)&0XFF);
			bytes[4] = (byte)((value>>>24)&0XFF);
			bytes[5] = (byte)((value>>>16)&0XFF);
			bytes[6] = (byte)((value>>>8)&0XFF);
			bytes[7] = (byte)(value&0XFF);
		}
		else {
			bytes[7] = (byte)(value>>>56);
			bytes[6] = (byte)((value>>>48)&0XFF);
			bytes[5] = (byte)((value>>>40)&0XFF);
			bytes[4] = (byte)((value>>>32)&0XFF);
			bytes[3] = (byte)((value>>>24)&0XFF);
			bytes[2] = (byte)((value>>>16)&0XFF);
			bytes[1] = (byte)((value>>>8)&0XFF);
			bytes[0] = (byte)(value&0XFF);
		}
		return bytes;
	}
	
	/**
	 * ��longת��ΪС���ֽ����byte����
	 * @param value
	 */
	public static byte[] toBytesLittleEndian(long value) {
		return toBytes(value, LittleEndian);
	}
	
	/**
	 * ��longת��Ϊ����ֽ����byte����
	 * @param value
	 */
	public static byte[] toBytesBigEndian(long value) {
		return toBytes(value, BigEndian);
	}
	
	/**
	 * ��stringת��Ϊencoding�����byte����
	 * @param value
	 * @param encoding
	 */
	public static byte[] toBytes(String value, String encoding)
	{
		try {
			return value.getBytes(encoding);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * ��stringת��ΪGB2312�����byte����
	 * @param value
	 */
	public static byte[] toBytes(String value)
	{
		return toBytes(value, "GB2312");
	}
	
	/**
	 * ��GB2312�����byte����ת��Ϊstring
	 * @param value
	 */
	public static String toString(byte[] value) {
		return toString(value, "GB2312");
	}
	
	/**
	 * ��encoding�����byte����ת��Ϊstring
	 * @param value
	 */
	public static String toString(byte[] value, String encoding) {
		try {
			return new String(value, encoding);
		} catch (UnsupportedEncodingException e) {
			throw new RuntimeException(e.getMessage());
		}
	}
	
	/**
	 * ����ַ���
	 * @param original
	 * @param regex
	 */
	public static String[] split(String original, String regex) {
		// ȡ�Ӵ�����ʼλ��
		int startIndex = 0;
		
		// ����������ȷ���Vector�� ע��Ӧ������
		Vector v = new Vector();
		
		// ���صĽ���ַ�������
		String[] str = null;
		
		// �洢ȡ�Ӵ�ʱ��ʼλ��
		int index = 0;
		
		// ���ƥ���Ӵ���λ��
		startIndex = original.indexOf(regex);
		
		// �����ʼ�ַ�����λ��С���ַ����ĳ��ȣ���֤��û��ȡ���ַ���ĩβ��
		// -1����ȡ����ĩβ
		// �жϵ�������ѭ����������
		while (startIndex < original.length() && startIndex != -1) {
			
			String temp = original.substring(index, startIndex);
			
			// ȡ�Ӵ�
			v.addElement(temp);
			
			// ����ȡ�Ӵ�����ʼλ��
			index = startIndex + regex.length();
			
			// ���ƥ���Ӵ���λ��
			startIndex = original.indexOf(regex, startIndex + regex.length());
			
		}
		// ȡ�������Ӵ�
		v.addElement(original.substring(index + 1 - regex.length()));
		
		// ��Vector����ת��������
		str = new String[v.size()];
		for (int i = 0; i < v.size(); i++)
			str[i] = (String) v.elementAt(i);
		// �������ɵ�����
		return str;
	}
}
