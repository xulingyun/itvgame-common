package cn.ohyeah.itvgame.model;

import java.io.DataInputStream;
import java.io.IOException;

/**
 * 订购相关属性类
 * @author maqian
 * @version 1.0
 */
public class SubscribeProperties {
	/**
	 * 是否支持订购
	 */
	private boolean supportSubscribe;
	
	/**
	 * 订购的货币单位(如：元)
	 */
	private String subscribeAmountUnit;
	
	/**
	 * 人民币对订购使用的货币(如：元)的比例
	 */
	private int subscribeCashToAmountRatio;
	
	/**
	 * 是否支持积分订购
	 */
	private boolean supportSubscribeByPoints;
	
	/**
	 * 积分单位
	 */
	private String pointsUnit;
	
	/**
	 * 用户当前有效积分
	 */
	private int availablePoints;
	
	/**
	 * 订购使用的货币(如：元)对积分的比例
	 */
	private int cashToPointsRatio;
	
	/**
	 * 是否支持充值
	 */
	private boolean supportRecharge;
	
	/**
	 * 游戏中消费的货币单位(如：游戏币、元宝等)
	 */
	private String expendAmountUnit;
	
	/**
	 * 游戏中消费的货币单位(如：游戏币、元宝等)对订购使用的货币(如：元)的比例
	 */
	private int expendCashToAmountRatio;
	
	/**
	 * 用户余额(单位是游戏中消费的货币单位)
	 */
	private int balance;
	
	/**
	 * 充值比例(人民币对游戏币的比例)
	 */
	private int rechargeRatio;
	
	public boolean isSupportRecharge() {
		return supportRecharge;
	}
	public void setSupportRecharge(boolean supportRecharge) {
		this.supportRecharge = supportRecharge;
	}
	public boolean isSupportSubscribe() {
		return supportSubscribe;
	}
	public void setSupportSubscribe(boolean supportSubscribe) {
		this.supportSubscribe = supportSubscribe;
	}
	public String getSubscribeAmountUnit() {
		return subscribeAmountUnit;
	}
	public void setSubscribeAmountUnit(String subscribeAmountUnit) {
		this.subscribeAmountUnit = subscribeAmountUnit;
	}
	public boolean isSupportSubscribeByPoints() {
		return supportSubscribeByPoints;
	}
	public void setSupportSubscribeByPoints(boolean supportSubscribeByPoints) {
		this.supportSubscribeByPoints = supportSubscribeByPoints;
	}
	public String getPointsUnit() {
		return pointsUnit;
	}
	public void setPointsUnit(String pointsUnit) {
		this.pointsUnit = pointsUnit;
	}
	public int getAvailablePoints() {
		return availablePoints;
	}
	public void setAvailablePoints(int availablePoints) {
		this.availablePoints = availablePoints;
	}
	public int getCashToPointsRatio() {
		return cashToPointsRatio;
	}
	public void setCashToPointsRatio(int cashToPointsRatio) {
		this.cashToPointsRatio = cashToPointsRatio;
	}

	public void setExpendAmountUnit(String expendAmountUnit) {
		this.expendAmountUnit = expendAmountUnit;
	}
	public String getExpendAmountUnit() {
		return expendAmountUnit;
	}
	public void setRechargeRatio(int rechargeRatio) {
		this.rechargeRatio = rechargeRatio;
	}
	public int getRechargeRatio() {
		return rechargeRatio;
	}
	
	public void setBalance(int balance) {
		this.balance = balance;
	}
	public int getBalance() {
		return balance;
	}
	public void setSubscribeCashToAmountRatio(int cashToAmountRatio) {
		this.subscribeCashToAmountRatio = cashToAmountRatio;
	}
	public int getSubscribeCashToAmountRatio() {
		return subscribeCashToAmountRatio;
	}
	
	public int getExpendCashToAmountRatio() {
		return expendCashToAmountRatio;
	}
	public void setExpendCashToAmountRatio(int expendCashToAmountUnit) {
		this.expendCashToAmountRatio = expendCashToAmountUnit;
	}
	public void readSubscribeProperties(DataInputStream dis) throws IOException {
		supportSubscribe = dis.readBoolean();
		subscribeAmountUnit = dis.readUTF();
		subscribeCashToAmountRatio = dis.readInt();
		supportSubscribeByPoints = dis.readBoolean();
		pointsUnit = dis.readUTF();
		availablePoints = dis.readInt();
		cashToPointsRatio = dis.readInt();
		supportRecharge = dis.readBoolean();
		expendAmountUnit = dis.readUTF();
		expendCashToAmountRatio = dis.readInt();
		balance = dis.readInt();
		rechargeRatio = dis.readInt();
	}

	public void print() {
		System.out.println("supportSubscribe: "+supportSubscribe);
		System.out.println("subscribeAmountUnit: "+subscribeAmountUnit);
		System.out.println("cashToAmountRatio: "+subscribeCashToAmountRatio);
		System.out.println("supportSubscribeByPoints: "+supportSubscribeByPoints);
		System.out.println("pointsUnit: "+pointsUnit);
		System.out.println("availablePoints: "+availablePoints);
		System.out.println("cashToPointsRatio: "+cashToPointsRatio);
		System.out.println("supportRecharge: "+supportRecharge);
		System.out.println("expendAmountUnit: "+expendAmountUnit);
		System.out.println("expendCashToAmountRatio: "+expendCashToAmountRatio);
		System.out.println("balance: "+balance);
		System.out.println("rechargeRatio: "+rechargeRatio);
	}
}
