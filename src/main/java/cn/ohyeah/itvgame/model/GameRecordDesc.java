package cn.ohyeah.itvgame.model;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * ��Ϸ��¼������
 * @author maqian
 * @version 1.0
 */
public class GameRecordDesc {
	private int recordId;
	private int playDuration;	/*��Ϸʱ��(��λ����)*/
	private int scores;			/*��Ϸ����*/
	private String time;
	private String remark;
	
	public int getRecordId() {
		return recordId;
	}
	
	public void setRecordId(int recordId) {
		this.recordId = recordId;
	}

	public String getTime() {
		return time;
	}
	public void setTime(String time) {
		this.time = time;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	
	public void setPlayDuration(int playDuration) {
		this.playDuration = playDuration;
	}
	public int getPlayDuration() {
		return playDuration;
	}
	public void setScores(int scores) {
		this.scores = scores;
	}
	public int getScores() {
		return scores;
	}
	
	public void readQueryResponseData(DataInputStream dis) throws IOException {
		recordId = dis.readInt();
		playDuration = dis.readInt();
		scores = dis.readInt();
		remark = dis.readUTF();
		time = dis.readUTF();
	}
}
