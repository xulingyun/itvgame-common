package cn.ohyeah.stb.ui;

import javax.microedition.lcdui.Image;

import cn.ohyeah.stb.game.SGraphics;

/**
 * ͼ��������
 * @author maqian
 * @version 1.0
 */
public class ImageUtil {
	/**
	 * ���л��ƻҶȱ任ͼ��J2ME��ֻ֧��PNG��ʽͼƬ�����ڴ治��ʱ���ôη���������Ч��ƫ��
	 * @param g
	 * @param pic ԴͼƬ
	 * @param srcX ���ƿ���ԴͼƬ��Xƫ��
	 * @param srcY ���ƿ���ԴͼƬ��Yƫ��
	 * @param w ���ƿ��
	 * @param h ���Ƹ߶�
	 * @param destX ��ʾ��X����
	 * @param destY ��ʾ��Y����
	 */
	public static void drawGrayByLine(SGraphics g, Image pic, 
			int srcX, int srcY, int w, int h, int destX, int destY) {
		int []rgbData = new int[w];
		for (int hd = h-1; hd>=0; --hd) {
			pic.getRGB(rgbData, 0, w, srcX, srcY+hd, w, 1);
			for (int i = rgbData.length-1; i>=0; --i) {
		        rgbData[i] = RenderUtil.turnGray(rgbData[i]);
			}
			g.drawRGB(rgbData, 0, w, destX, destY+hd, w, 1, true);
		}
	}
	
	/**
	 * ���ƻҶȱ任ͼ��J2ME��ֻ֧��PNG��ʽͼƬ�����ڴ�ռ��㹻ʱ���ô˷���
	 * @param g
	 * @param pic ԴͼƬ
	 * @param srcX ���ƿ���ԴͼƬ��Xƫ��
	 * @param srcY ���ƿ���ԴͼƬ��Yƫ��
	 * @param w ���ƿ��
	 * @param h ���Ƹ߶�
	 * @param destX ��ʾ��X����
	 * @param destY ��ʾ��Y����
	 */
	public static void drawGray(SGraphics g, Image pic, 
			int srcX, int srcY, int w, int h, int destX, int destY) {
		int []rgbData = new int[w*h];
		Image grayImg;
		pic.getRGB(rgbData, 0, w, srcX, srcY, w, h);
		for (int i = rgbData.length-1; i>=0; --i) {
			rgbData[i] = RenderUtil.turnGray(rgbData[i]);
		}
		grayImg = Image.createRGBImage(rgbData, w, h, true);
		g.drawImage(grayImg, destX, destY, 0);
	}
	
	/**
	 * �����Ҷ�ͼ��J2ME��ֻ֧��PNG��ʽͼƬ�����ڴ�ռ��㹻ʱ���ô˷���
	 * @param pic ԴͼƬ
	 * @param srcX �������ԴͼƬ�е�Xƫ��
	 * @param srcY �������ԴͼƬ�е�Yƫ��
	 * @param w �������
	 * @param h �����߶�
	 * @return image
	 */
	public static Image createGray(Image pic, int srcX, int srcY, int w, int h) {
		int []rgbData = new int[w*h];
		pic.getRGB(rgbData, 0, w, srcX, srcY, w, h);
		for (int i = rgbData.length-1; i>=0; --i) {
			rgbData[i] = RenderUtil.turnGray(rgbData[i]);
		}
		return Image.createRGBImage(rgbData, w, h, true);
	}

	/**
	 * ���л���͸���任ͼ��J2ME��ֻ֧��PNG��ʽͼƬ�����ڴ治��ʱ���ôη���������Ч��ƫ��
	 * @param g
	 * @param pic ԴͼƬ
	 * @param srcX ���ƿ���ԴͼƬ��Xƫ��
	 * @param srcY ���ƿ���ԴͼƬ��Yƫ��
	 * @param w ���ƿ��
	 * @param h ���Ƹ߶�
	 * @param destX ��ʾ��X����
	 * @param destY ��ʾ��Y����
	 * @param alpha alphaͨ��ֵ
	 */
	public static void drawTransparenceByLine(SGraphics g, Image pic, 
			int srcX, int srcY, int w, int h, int destX, int destY, int alpha) {

		int []rgbData = new int[w];
		if (alpha < 0 || alpha > 255) {
			return;
		}
		for (int hd = h-1; hd>=0; --hd) {
			pic.getRGB(rgbData, 0, w, srcX, srcY+hd, w, 1);
			for (int i = rgbData.length-1; i>=0; --i) {
				rgbData[i] = RenderUtil.turnTransparence(rgbData[i], alpha);
			}
			g.drawRGB(rgbData, 0, w, destX, destY+hd, w, 1, true);
		}
	}
	/**
	 * ����͸���任ͼ��J2ME��ֻ֧��PNG��ʽͼƬ�����ڴ�ռ��㹻ʱ���ô˷���
	 * @param g
	 * @param pic ԴͼƬ
	 * @param srcX ���ƿ���ԴͼƬ��Xƫ��
	 * @param srcY ���ƿ���ԴͼƬ��Yƫ��
	 * @param w ���ƿ��
	 * @param h ���Ƹ߶�
	 * @param destX ��ʾ��X����
	 * @param destY ��ʾ��Y����
	 * @param alpha alphaͨ��ֵ
	 */
	public static void drawTransparence(SGraphics g, Image pic, 
			int srcX, int srcY, int w, int h, int destX, int destY, int alpha) {
		
		int []rgbData = new int[w*h];
		Image transparenceImg;
		if (alpha < 0 || alpha > 255) {
			return;
		}
		pic.getRGB(rgbData, 0, w, srcX, srcY, w, h);
		for (int i = rgbData.length-1; i>=0; --i) {
			rgbData[i] = RenderUtil.turnTransparence(rgbData[i], alpha);
		}
		transparenceImg = Image.createRGBImage(rgbData, w, h, true);
		g.drawImage(transparenceImg, destX, destY, 0);
	}
	
	/**
	 * ����͸���任ͼ��J2ME��ֻ֧��PNG��ʽͼƬ�����ڴ�ռ��㹻ʱ���ô˷���
	 * @param pic ԴͼƬ
	 * @param srcX ���ƿ���ԴͼƬ��Xƫ��
	 * @param srcY ���ƿ���ԴͼƬ��Yƫ��
	 * @param w ���ƿ��
	 * @param h ���Ƹ߶�
	 * @param alpha alphaͨ��ֵ
	 * @return image
	 */
	public static Image createTransparence(Image pic, 
			int srcX, int srcY, int w, int h, int alpha) {

		int []rgbData = new int[w*h];
		if (alpha < 0 || alpha > 255) {
			return null;
		}
		pic.getRGB(rgbData, 0, w, srcX, srcY, w, h);
		for (int i = rgbData.length-1; i>=0; --i) {
			rgbData[i] = RenderUtil.turnTransparence(rgbData[i], alpha);
		}
		return Image.createRGBImage(rgbData, w, h, true);
	}
	/**
	 * ���л������ȱ任ͼ��J2ME��ֻ֧��PNG��ʽͼƬ�����ڴ治��ʱ���ôη���������Ч��ƫ��
	 * @param g
	 * @param pic ԴͼƬ
	 * @param srcX ���ƿ���ԴͼƬ��Xƫ��
	 * @param srcY ���ƿ���ԴͼƬ��Yƫ��
	 * @param w ���ƿ��
	 * @param h ���Ƹ߶�
	 * @param destX ��ʾ��X����
	 * @param destY ��ʾ��Y����
	 * @param delta
	 */
	public static void drawBrightByLine(SGraphics g, Image pic, 
			int srcX, int srcY, int w, int h, int destX, int destY, int delta) {

		int []rgbData = new int[w];
		if (delta == 0) {
			return;
		}
		for (int hd = h-1; hd >= 0; --hd) {
			pic.getRGB(rgbData, 0, w, srcX, srcY+hd, w, 1);
			for (int i = rgbData.length-1; i>=0; --i) {
				rgbData[i] = RenderUtil.turnBright(rgbData[i], delta);
			}
			g.drawRGB(rgbData, 0, w, destX, destY+hd, w, 1, true);
		}
	}
	/**
	 * �������ȱ任ͼ��J2ME��ֻ֧��PNG��ʽͼƬ�����ڴ�ռ��㹻ʱ���ô˷���
	 * @param g
	 * @param pic ԴͼƬ
	 * @param srcX ���ƿ���ԴͼƬ��Xƫ��
	 * @param srcY ���ƿ���ԴͼƬ��Yƫ��
	 * @param w ���ƿ��
	 * @param h ���Ƹ߶�
	 * @param destX ��ʾ��X����
	 * @param destY ��ʾ��Y����
	 * @param delta
	 */
	public static void drawBright(SGraphics g, Image pic, 
			int srcX, int srcY, int w, int h, int destX, int destY, int delta) {
		
		int []rgbData = new int[w*h];
		Image brightImg;
		if (delta == 0) {
			return;
		}
		pic.getRGB(rgbData, 0, w, srcX, srcY, w, h);
		for (int i = rgbData.length-1; i>=0; --i) {
			rgbData[i] = RenderUtil.turnBright(rgbData[i], delta);
		}
		brightImg = Image.createRGBImage(rgbData, w, h, true);
		g.drawImage(brightImg, destX, destY, 0);
	}
	/**
	 * �������ȱ任ͼ��J2ME��ֻ֧��PNG��ʽͼƬ�����ڴ�ռ��㹻ʱ���ô˷���
	 * @param pic ԴͼƬ
	 * @param srcX ���ƿ���ԴͼƬ��Xƫ��
	 * @param srcY ���ƿ���ԴͼƬ��Yƫ��
	 * @param w ���ƿ��
	 * @param h ���Ƹ߶�
	 * @param destX ��ʾ��X����
	 * @param destY ��ʾ��Y����
	 * @param delta
	 * @return image
	 */
	public static Image createBright(Image pic, 
			int srcX, int srcY, int w, int h, int destX, int destY, int delta) {
		
		int []rgbData = new int[w*h];
		if (delta == 0) {
			return null;
		}
		pic.getRGB(rgbData, 0, w, srcX, srcY, w, h);
		for (int i = rgbData.length-1; i>=0; --i) {
			rgbData[i] = RenderUtil.turnBright(rgbData[i], delta);
		}
		return Image.createRGBImage(rgbData, w, h, true);
	}
	
	/**
	 * ͼƬ���ţ�J2ME��ֻ֧��PNG��ʽͼƬ��
	 * @param pic ԴͼƬ
	 * @param srcX �������ԴͼƬ��Xƫ��
	 * @param srcY �������ԴͼƬ��Yƫ��
	 * @param w �����Ŀ��
	 * @param h �����ĸ߶�
	 * @param desW �任���ͼƬ���
	 * @param desH �任���ͼƬ�߶�
	 * @return image
	 */
	public static Image zoomImage(Image pic, int srcX, int srcY, int w, int h, int desW, int desH) {  
        // �����ֵ��  
        int[] tabY = new int[desH];  
        int[] tabX = new int[desW];  
  
        int sb = 0;  
        int db = 0;  
        int tems = 0;  
        int temd = 0;  
        int distance = h > desH ? h : desH;  
        for (int i = 0; i <= distance; i++) { /* ��ֱ���� */  
            tabY[db] = sb;  
            tems += h;  
            temd += desH;  
            if (tems > distance) {
                tems -= distance;  
                sb++;  
            }
            if (temd > distance) {
                temd -= distance;  
                db++;  
            }
        }
  
        sb = 0;  
        db = 0;  
        tems = 0;  
        temd = 0;  
        distance = w > desW ? w : desW;  
        for (int i = 0; i <= distance; i++) { /* ˮƽ���� */  
            tabX[db] = (short) sb;  
            tems += w;  
            temd += desW;  
            if (tems > distance) {
                tems -= distance;  
                sb++;  
            }
            if (temd > distance) {
                temd -= distance;  
                db++;  
            }
        }
  
        // ���ɷŴ���С��ͼ������buf  
        int[] srcBuf = new int[w * h];
        pic.getRGB(srcBuf, 0, w, srcX, srcY, w, h);  
        int[] desBuf = new int[desW * desH];  
        int dx = 0;  
        int dy = 0;  
        int sy = 0;  
        int oldy = -1;  
        for (int i = 0; i < desH; i++) {
            if (oldy == tabY[i]) {
                System.arraycopy(desBuf, dy - desW, desBuf, dy, desW);  
            } else {
                dx = 0;  
                for (int j = 0; j < desW; j++) {  
                    desBuf[dy + dx] = srcBuf[sy + tabX[j]];  
                    dx++;  
                }  
                sy += (tabY[i] - oldy) * w;  
            }
            oldy = tabY[i];  
            dy += desW;  
        }
  
        return Image.createRGBImage(desBuf, desW, desH, true);  
    } 
}
