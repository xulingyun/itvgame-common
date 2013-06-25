package cn.ohyeah.stb.util;

import cn.ohyeah.stb.key.KeyState;

/**
 * Wait
 * @author maqian
 * @version 1.0
 */
public class Wait {
	
	/**
	 * ����ֹͣ��ֱ����鵽����İ����¼�
	 * @param keyState
	 */
	public static void waitForKey(KeyState keyState) {
		boolean run = true;
		while (run) {
			try {
				if (keyState.containsAnyKeyAndRemove()) {
					run = false;;
				}
				Thread.sleep(175);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
	
	
}
