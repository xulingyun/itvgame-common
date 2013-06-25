package cn.ohyeah.itvgame.model;

import java.io.DataInputStream;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * ��Ʒ��Ȩ��
 * @author maqian
 * @version 1.0
 */
public class Authorization {
	public static final byte AUTHORIZATION_INVALID = 0;		//��Ч��Ȩ
	public static final byte AUTHORIZATION_FREE = 1;		//���
	public static final byte AUTHORIZATION_TRY = 2;			//����Ȩ��
	public static final byte AUTHORIZATION_PERIOD = 3;		//��ʱ��Ȩ��
	public static final byte AUTHORIZATION_COUNT = 4;		//����Ϸ����Ȩ��
	public static final byte AUTHORIZATION_TIME = 5;		//����Ϸʱ��Ȩ��
	public static final byte AUTHORIZATION_MONTH = 6;		//����Ȩ��
	public static final byte AUTHORIZATION_MEMBER = 7;		//��ͨ��ԱȨ��
	public static final byte AUTHORIZATION_GROUP = 8;		//����Ȩ��
	public static final byte AUTHORIZATION_VIP = 9;			//VIPȨ��
	public static final byte AUTHORIZATION_SUPER = 10;		//����Ȩ��
	
	private int authorizationType;
	private int leftTryNumber;
	private int leftValidSeconds;
	private int leftValidCount;
	private java.util.Date authorizationStartTime;
	private java.util.Date authorizationEndTime;
	
	public int getAuthorizationType() {
		return authorizationType;
	}
	public void setAuthorizationType(int authorizationType) {
		this.authorizationType = authorizationType;
	}
	
	public String getAuthTypeStr() {
		String typeStr = "δ֪Ȩ��";
		switch (authorizationType) {
		case AUTHORIZATION_INVALID: typeStr = "��Ч"; break;
		case AUTHORIZATION_FREE: typeStr = "���"; break;
		case AUTHORIZATION_TRY: typeStr = "����Ч��"; break;
		case AUTHORIZATION_COUNT: typeStr = "����Ϸ����"; break;
		case AUTHORIZATION_TIME: typeStr = "����Ϸʱ��"; break;
		case AUTHORIZATION_MONTH: typeStr = "����"; break;
		default: typeStr = "δ֪Ȩ��, authType="+authorizationType;
		}
		return typeStr;
	}
	
	/**
	 * ��ʽ��ʱ���ַ�����"yyyy/MM/dd HH:mm:ss"
	 * @param time
	 * @return string
	 */
	public static String formatTimeStr(Date time) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeZone(TimeZone.getTimeZone("GMT+08:00"));
		calendar.setTime(time);
		int year = calendar.get(Calendar.YEAR);
		int month = calendar.get(Calendar.MONTH);
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		int minute = calendar.get(Calendar.MINUTE);
		int second = calendar.get(Calendar.SECOND);
		return year+"/"+(month+1)+"/"+day+" "+hour+":"+minute+":"+second;
	}
	
	public void print() {
		System.out.println("AuthType: "+getAuthTypeStr());
		if (authorizationType == AUTHORIZATION_TRY) {
			System.out.println("LeftTryNum: "+leftTryNumber);
		}
		else if (authorizationType == AUTHORIZATION_COUNT) {
			System.out.println("LeftValidCount: "+leftValidCount);
		}
		else if (authorizationType == AUTHORIZATION_TIME) {
			System.out.println("LeftValidSeconds: "+leftValidSeconds);
		}
		else if (authorizationType == AUTHORIZATION_PERIOD) {
			System.out.println("AuthStart: "+formatTimeStr(authorizationStartTime));
			System.out.println("AuthEnd: "+formatTimeStr(authorizationEndTime));
		}
	}
	
	public int getLeftTryNumber() {
		return leftTryNumber;
	}
	public void setLeftTryNumber(int leftTryNumber) {
		this.leftTryNumber = leftTryNumber;
	}
	public int getLeftValidSeconds() {
		return leftValidSeconds;
	}
	public void setLeftValidSeconds(int leftValidSeconds) {
		this.leftValidSeconds = leftValidSeconds;
	}
	public int getLeftValidCount() {
		return leftValidCount;
	}
	public void setLeftValidCount(int leftValidCount) {
		this.leftValidCount = leftValidCount;
	}
	public java.util.Date getAuthorizationStartTime() {
		return authorizationStartTime;
	}
	public void setAuthorizationStartTime(java.util.Date authorizationStartTime) {
		this.authorizationStartTime = authorizationStartTime;
	}
	public java.util.Date getAuthorizationEndTime() {
		return authorizationEndTime;
	}
	public void setAuthorizationEndTime(java.util.Date authorizationEndTime) {
		this.authorizationEndTime = authorizationEndTime;
	}
	
	public void readAuthorization(DataInputStream dis) throws IOException {
		authorizationType = dis.readInt();
		leftTryNumber = dis.readInt();
		leftValidSeconds = dis.readInt();
		leftValidCount = dis.readInt();
		authorizationStartTime = new java.util.Date(dis.readLong());
		authorizationEndTime = new java.util.Date(dis.readLong());
	}
	
}
