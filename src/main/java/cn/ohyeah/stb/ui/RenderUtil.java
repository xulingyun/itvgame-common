package cn.ohyeah.stb.ui;

/**
 * ��Ⱦ����
 * @author maqian
 * @version 1.0
 */
public class RenderUtil {
	/**
	 * ����ɫ�Ҷȱ任
	 * @param color
	 * @return color
	 */
	public static int turnGray(int color) {
		int red = (color&0XFF0000)>>16;
		int green = (color&0XFF00)>>8;
		int blue = color&0XFF;
		int gray = (red+(green<<1)+blue)>>2;
		//gray =  (r*19595 + g*38469 + b*7472) >> 16;
       return ((gray<<16)|(gray<<8)|gray)|(color&0xff000000);
	}
	
	/**
	 * ����ɫ͸���任
	 * @param color
	 * @param alpha
	 * @return color
	 */
	public static int turnTransparence(int color, int alpha) {
		return (color&0XFFFFFF)|(alpha<<24);
	}
	
	/**
	 * ����ɫ���ȱ任
	 * @param color
	 * @param delta
	 * @return color
	 */
	public static int turnBright(int color, int delta, int destColor) {
		int destRed = (destColor&0XFF0000)>>16;
		int destGreen = (destColor&0XFF00)>>8;
		int destBlue = destColor&0XFF;
		
		int red = ((color&0XFF0000)>>16)+delta;
		if (delta > 0) {
			if (red > destRed) {
				red = destRed;
			}
		}
		else {
			if (red < destRed) {
				red = destRed;
			}
		}

		int green = ((color&0XFF00)>>8)+delta;
		if (delta > 0) {
			if (green > destGreen) {
				green = destGreen;
			}
		}
		else {
			if (green < destGreen) {
				green = destGreen;
			}
		}

		int blue = (color&0XFF)+delta;
		if (delta > 0) {
			if (blue > destBlue) {
				blue = destBlue;
			}
		}
		else {
			if (blue < destBlue) {
				blue = destBlue;
			}
		}
        return ((red<<16)|(green<<8)|blue)|(color&0xff000000);
	}
	
	/**
	 * ����ɫ���ȱ任
	 * @param color
	 * @param delta
	 * @return color
	 */
	public static int turnBright(int color, int delta) {
		if (delta > 0) {
			return turnBright(color, delta, 0XFFFFFF);
		}
		else {
			return turnBright(color, delta, 0);
		}
	}
}
