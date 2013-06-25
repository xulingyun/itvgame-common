package cn.ohyeah.stb.ui;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Image;

import cn.ohyeah.stb.game.SGraphics;

/**
 * ���ƹ���
 * @author maqian
 * @version 1.0
 */
public class DrawUtil {
	/**
	 * ��������Ӧ��ť�Ŀ��
	 * @param btnW ��ť����ͼƬ�Ŀ��
	 * @param btnBorder ��ť�߿���
	 * @param text ��ť����
	 * @param font ��ǰ����
	 * @return int
	 */
	public static int calcAdaptiveButtonWidth(int btnW, int btnBorder, String text, Font font) {
		int textW = font.stringWidth(text);
		int btnSw = textW+(btnBorder<<1);
		if (btnSw < btnW) {
			btnSw = btnW;
		}
		return btnSw;
	}
	
	/**
	 * ��������Ӧ��ť����ť�Ŀ���Զ���Ӧ���ֵĿ�ȣ�
	 * @param g
	 * @param btnBg ��ť����ԴͼƬ
	 * @param btnSrcX ��ť������ԴͼƬ�е�Xƫ��
	 * @param btnSrcY ��ť������ԴͼƬ�е�Yƫ��
	 * @param btnW ��ť���
	 * @param btnH ��ť�߶�
	 * @param btnBorder ��ť�߿���
	 * @param text ��ť����
	 * @param x ��ť��ʾλ��
	 * @param y ��ť��ʾλ��
	 * @param textColor ��ť������ɫ
	 */
	public static void drawAdaptiveButton(SGraphics g, Image btnBg, int btnSrcX, int btnSrcY, int btnW, int btnH, 
			int btnBorder, String text, int x, int y, int textColor) {
		
		int btnMidW = btnW-btnBorder-btnBorder;
		int btnFillW = btnW-btnBorder;
		Font font = g.getFont();
		int textW = font.stringWidth(text);
		int btnSw = textW+(btnBorder<<1);
		if (btnSw < btnW) {
			btnSw = btnW;
		}
		int sx = x;
		int sy = y;
		if (btnSw > btnW) {
			g.drawRegion(btnBg, btnSrcX, btnSrcY, btnFillW, btnH, 0, sx, sy, 20);
			sx += btnFillW;
			if (btnSw <= (btnFillW+btnFillW)) {
				g.drawRegion(btnBg, btnSrcX+(btnW-(btnSw-btnFillW)), btnSrcY, btnSw-btnFillW, btnH, 0, 
						sx, sy, 20);
			}
			else {
				int w = btnSw-btnFillW-btnFillW;
				while (w > 0) {
					if (w > btnMidW) {
						g.drawRegion(btnBg, btnSrcX+btnBorder, btnSrcY, btnMidW, btnH, 0, 
								sx, sy, 20);
						sx += btnMidW;
						w -= btnMidW;
					}
					else {
						g.drawRegion(btnBg, btnSrcX+btnBorder, btnSrcY, w, btnH, 0, 
								sx, sy, 20);
						sx += w;
						w -= w;
					}
				}
				g.drawRegion(btnBg, btnSrcX+btnBorder, btnSrcY, btnFillW, btnH, 0, 
						sx, sy, 20);
			}
		}
		else {
			g.drawRegion(btnBg, btnSrcX, btnSrcY, btnW, btnH, 0, sx, sy, 20);
		}
		sx = x+((btnSw-textW)>>1);
		sy = y+((btnH-font.getHeight())>>1);
		g.setColor(textColor);
		g.drawString(text, sx, sy, 20);
	}
	
	/**
	 * ������ͨ��ť��������Ӧ��ȣ�
	 * @param g
	 * @param btnBg ��ť����ͼƬ
	 * @param text ��ť����
	 * @param x ��ť��ʾλ��
	 * @param y ��ť��ʾλ��
	 * @param textColor ��ť������ɫ
	 */
	public static void drawButton(SGraphics g, Image btnBg, String text, int x, int y, int textColor) {
		Font font = g.getFont();
		int sx = x + ((btnBg.getWidth()-font.stringWidth(text))>>1);
		int sy = y + ((btnBg.getHeight()-font.getHeight())>>1);
		g.drawImage(btnBg, x, y, 20);
		g.setColor(textColor);
		g.drawString(text, sx, sy, 20);
	}
	
	/**
	 * ����ͼƬ���֣�����ͼƬҪ��0~9˳��������У�ÿ������Ԫ�صȿ�
	 * @param g
	 * @param img ����ͼƬ
	 * @param num ����ʾ������
	 * @param x ��ʾλ��
	 * @param y ��ʾλ��
	 * @param gap ÿ�����ּ�ļ��
	 * @return int
	 */
	public static int drawNumber(SGraphics g, Image img, int num, int x, int y, int gap) {
		String number = Integer.toString(num);
		int imgW = img.getWidth()/10;
		int imgH = img.getHeight();
		int sx = x;
		int sy = y;
		for (int i = 0; i < number.length(); ++i) {
			g.drawRegion(img, imgW*Character.digit(number.charAt(i), 10), 0, imgW, imgH, 0, sx, sy, 20);
			sx += imgW+gap;
		}
		return sx-x;
	}
	
	/**
	 * ���ƴ����ŵ�ͼƬ���֣�����ͼƬҪ��0~9,+,-˳��������У�ÿ������Ԫ�صȿ�
	 * @param g
	 * @param img ����ͼƬ
	 * @param num ����ʾ������
	 * @param x ��ʾλ��
	 * @param y ��ʾλ��
	 * @param gap ÿ�����ּ�ļ��
	 * @return int
	 */
	public static int drawNumberWithSymbol(SGraphics g, Image img, int num, int x, int y, int gap) {
		String number = num>=0?("+"+Integer.toString(num)):Integer.toString(num);
		int imgW = img.getWidth()/12;
		int imgH = img.getHeight();
		int sx = x;
		int sy = y;
		
		if (number.charAt(0) == '+') {
			g.drawRegion(img, imgW*10, 0, imgW, imgH, 0, sx, sy, 20);
		}
		else {
			g.drawRegion(img, imgW*11, 0, imgW, imgH, 0, sx, sy, 20);
		}
		sx += imgW+gap;
		for (int i = 1; i < number.length(); ++i) {
			g.drawRegion(img, imgW*Character.digit(number.charAt(i), 10), 0, imgW, imgH, 0, sx, sy, 20);
			sx += imgW+gap;
		}
		return sx-x;
	}
	
	/**
	 * ���ƾ��ο�
	 * @param g
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param lineW
	 * @param color
	 */
	public static void drawRect(SGraphics g, int x, int y, int w, int h, int lineW, int color) {
		g.setColor(color);
		drawRect(g, x, y, w, h, lineW);
	}
	
	/**
	 * ���ƾ��ο�
	 * @param g
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 * @param lineW
	 */
	public static void drawRect(SGraphics g, int x, int y, int w, int h, int lineW) {
		int sx = x-1, sy = y-1, sw = w+1, sh = h+1;
		g.drawRect(sx, sy, sw, sh);
		for (int i = 1; i < lineW; ++i) {
			--sx;
			--sy;
			sw+=2;
			sh+=2;
			g.drawRect(sx, sy, sw, sh);
		}
	}
	

	/**
	 * ���ƴ�ɫ����
	 * @param g
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public static void drawRect(SGraphics g, int x, int y, int w, int h) {
		int sx = x-1, sy = y-1, sw = w+1, sh = h+1;
		g.drawRect(sx, sy, sw, sh);
		for (int i = 1; i < h/2+1; ++i) {
			++sx;
			++sy;
			sw-=2;
			sh-=2;
			g.drawRect(sx, sy, sw, sh);
		}
	}
	
	/**
	 * ���ƴ�ɫ����
	 * @param col
	 * @param g
	 * @param x
	 * @param y
	 * @param w
	 * @param h
	 */
	public static void drawRect(int col, SGraphics g, int x, int y, int w, int h) {
		int sx = x-1, sy = y-1, sw = w+1, sh = h+1;
		int color = g.getColor();
		g.setColor(col);
		g.drawRect(sx, sy, sw, sh);
		for (int i = 1; i < h/2+1; ++i) {
			++sx;
			++sy;
			sw-=2;
			sh-=2;
			g.drawRect(sx, sy, sw, sh);
		}
		g.setColor(color);
	}
}
