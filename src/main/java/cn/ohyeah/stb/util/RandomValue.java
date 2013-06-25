package cn.ohyeah.stb.util;

import java.util.Random;

/**
 * ���������
 * @author maqian
 * @version 1.0
 */
public class RandomValue {
	private static Random random = new Random();
	
	/**
	 * ����0<=result<range�����������
	 * @param range
	 */
	public static int getRandInt(int range) {
		return random.nextInt(range);
	}
	
	/**
	 * ����start<=result<end���������
	 * @param start
	 * @param end
	 */
	public static int getRandInt(int start, int end) {
		return random.nextInt(end-start)+start;
	}
}
