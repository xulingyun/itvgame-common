package cn.ohyeah.stb.ui;

import cn.ohyeah.stb.game.SGraphics;


/**
 * �˵��ӿ�
 * @author maqian
 * @version 1.0
 */
public interface IMenu {
	public int getHilightIndex();
	public void setHilightIndex(int index);
	public void show(SGraphics g);
	public void nextItem();
	public void prevItem();
}
