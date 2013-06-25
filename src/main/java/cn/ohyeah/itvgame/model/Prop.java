package cn.ohyeah.itvgame.model;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * ��Ϸ������
 * @author maqian
 * @version 1.0
 */
public class Prop {
	private int propId;
	private String propName;
	private int price;				/*�۸�*/
	private int feeCode;			/*�۷ѵ�*/
	private String description;		/*����*/
	
	public int getFeeCode() {
		return feeCode;
	}
	public void setFeeCode(int feeCode) {
		this.feeCode = feeCode;
	}
	public int getPropId() {
		return propId;
	}
	public void setPropId(int propId) {
		this.propId = propId;
	}
	public String getPropName() {
		return propName;
	}
	public void setPropName(String propName) {
		this.propName = propName;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public int getPrice() {
		return price;
	}
	public void readQueryResponseData(DataInputStream dis) throws IOException {
		propId = dis.readInt();
		propName = dis.readUTF();
		price = dis.readInt();
		description = dis.readUTF();
	}
}
