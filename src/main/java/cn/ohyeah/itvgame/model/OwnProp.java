package cn.ohyeah.itvgame.model;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * ���ӵ�е�����Ϣ��
 * @author maqian
 * @version 1.0
 */
public class OwnProp {
	private int propId;		/*����ID*/
	private int count;		/*����*/
	
	public int getPropId() {
		return propId;
	}
	public void setPropId(int propId) {
		this.propId = propId;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public void readQueryResponseData(DataInputStream dis) throws IOException {
		propId = dis.readInt();
		count = dis.readInt();
	}
	
}
