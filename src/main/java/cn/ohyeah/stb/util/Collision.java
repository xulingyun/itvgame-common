package cn.ohyeah.stb.util;

/**
 * ��ײ���
 * @author xiaochen
 *
 */
public class Collision {
	
	/**
	 * �˷�������С������ײ������ķ��ι�����ײ���
	 * @param x1	С����x������
	 * @param y1	С����y������
	 * @param w1	С������
	 * @param h1	С����߶�
	 * @param x2	������x������
	 * @param y2	������y������
	 * @param w2	��������
	 * @param h2	������߶�
	 * @return true:������ײ�� false:δ������ײ
	 */
	public static boolean checkSquareCollision(int x1,int y1,int w1,int h1, int x2,int y2,int w2,int h2){
		if((x1 >= x2 && x1 <= (x2+w2)) && (y1 >= y2  && y1 <= (y2+h2))){
			return true;
		}else if(((x1+w1) >= x2 && (x1+w1) <= (x2+w2)) && (y1 >= y2  && y1 <= (y2+h2))){
			return true;
		}else if((x1 >= x2 && x1 <= (x2+w2)) && ((y1+h1) >= y2  && (y1+h1) <= (y2+h2))){
			return true;
		}else if(((x1+w1) >= x2 && (x1+w1) <= (x2+w2)) && ((y1+h1) >= y2  && (y1+h1) <= (y2+h2))){
			return true;
		}else {
			return false;
		}
	}

	/**
	 * �˷�������С������ײ�������Բ�ι�����ײ���
	 * @param x1	С����x������
	 * @param y1	С����y������
	 * @param w1	С������
	 * @param h1	С����߶�
	 * @param x2	������x������
	 * @param y2	������y������
	 * @param w2	��������
	 * @param h2	������߶�
	 * @return true:������ײ�� false:δ������ײ
	 */
	public static boolean checkCircularCollision(int x1,int y1,int w1,int h1, int x2,int y2,int w2,int h2){
		int r1 = w1/2, cX1 = x1+r1, cY1 = y1+h1/2;
		int r2 = w2/2, cX2 = x2+r2, cY2 = y2+h2/2;
		if(((cX2-cX1)*(cX2-cX1)+(cY2-cY1)*(cY2-cY1)) <= (r1+r2)*(r1+r2))
		{
			return true;
		}
		return false;
	}
	
	/**
	 * �˷�������С������ײ�������Բ�������ι���
	 * @param x1	С����x������
	 * @param y1	С����y������
	 * @param w1	С������
	 * @param h1	С����߶�
	 * @param x2	������x������
	 * @param y2	������y������
	 * @param w2	��������
	 * @param h2	������߶�
	 * @return true:������ײ�� false:δ������ײ
	 */
	public static boolean checkCircularToSquareCollision(int x1,int y1,int w1,int h1, int x2,int y2,int w2,int h2){
		return false;
	}
	
	/**
	 * �˷�������С������ײ������ķ�����Բ�ι���
	 * @param x1	С����x������
	 * @param y1	С����y������
	 * @param w1	С������
	 * @param h1	С����߶�
	 * @param x2	������x������
	 * @param y2	������y������
	 * @param w2	��������
	 * @param h2	������߶�
	 * @return true:������ײ�� false:δ������ײ
	 */
	public static boolean checkSquareToCircularCollision(int x1,int y1,int w1,int h1, int x2,int y2,int w2,int h2){
		int r1;
		if(w1<h1){
			r1 = w1/2;
		}else{
			r1 = h1/2;
		}
		int cX1 = x1+r1, cY1 = y1+h1/2;
		int r2 = w2/2, cX2 = x2+r2, cY2 = y2+h2/2;
		if(((cX2-cX1)*(cX2-cX1)+(cY2-cY1)*(cY2-cY1)) <= (r1+r2)*(r1+r2))
		{
			return true;
		}
		return false;
	}
}
