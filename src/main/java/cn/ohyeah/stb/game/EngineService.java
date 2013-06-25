package cn.ohyeah.stb.game;

import cn.ohyeah.itvgame.model.Authorization;
import cn.ohyeah.itvgame.model.LoginInfo;
import cn.ohyeah.itvgame.model.SubscribeProperties;
import cn.ohyeah.stb.util.DateUtil;

/**
 * ��������࣬��Ҫ�Ƕ����ṩ�������õ�ͳһ�ӿ�
 * @author maqian
 * @version 1.0
 */

public final class EngineService {
	
	/**
	 * �û���¼ʱ��
	 */
	java.util.Date loginTime;	
	
	/**
	 * ��¼�ɹ�ʱ�Ļ�����ʱ��
	 */
	long loginTimeMillis;		
	
	/**
	 * ��Ȩ��Ϣ
	 */
	Authorization auth;
	
	/**
	 * ��������
	 */
	SubscribeProperties subProps;
	
	/**
	 * ���û���
	 */
	int availablePoints;		
	
	/**
	 * ��ǰ���
	 */
	int balance;				
	
	private IEngine engine;
	private ParamManager pm;
	
	private boolean loginSuccessful;
	private String loginMessage;
	
	/**
	 * ��ͯ�����ܣ���ֵ�ɹ����´ξͲ���Ҫ��������
	 */
	public boolean isRechrageSuccess;   
	
	/**
	 * ��ֵ����
	 */
	public String passWord;				
	
	public EngineService(IEngine engine) {
		this.engine = engine;
		this.pm = new ParamManager(engine);
	}
	
	private void printParams() {
		System.out.println("loginTime: "+DateUtil.formatTimeStr(loginTime));
		System.out.println("server: "+pm.server);
		System.out.println("buyURL: "+pm.buyURL);
		System.out.println("accountId: "+pm.accountId);
		System.out.println("userId: "+pm.userId);
		System.out.println("accountName: "+pm.accountName);
		System.out.println("userToken: "+pm.userToken);
		System.out.println("productId: "+pm.productId);
		System.out.println("productName: "+pm.productName);
		System.out.println("appName: "+pm.appName);
		System.out.println("checkKey: "+pm.checkKey);
		
		subProps.print();
		auth.print();
	}
	
	public String toString() {
		String msg = "loginTime"+" ==> "+DateUtil.formatTimeStr(loginTime)+"\n";
		msg += "server"+" ==> "+pm.server+"\n";
		msg += "buyURL"+" ==> "+pm.buyURL+"\n";
		msg += "accountId"+" ==> "+pm.accountId+"\n";
		msg += "userId"+" ==> "+pm.userId+"\n";
		msg += "accountName"+" ==> "+pm.accountName+"\n";
		msg += "userToken"+" ==> "+pm.userToken+"\n";
		msg += "productId"+" ==> "+pm.productId+"\n";
		msg += "productName"+" ==> "+pm.productName+"\n";
		msg += "appName"+" ==> "+pm.appName+"\n";
		msg += "checkKey"+" ==> "+pm.checkKey;
		return msg;
	}
	
	private void assignLoginInfo(LoginInfo info) {
		pm.accountId = info.getAccountId();
		pm.productId = info.getProductId();
		pm.productName = info.getProductName();
		loginTime = info.getSystemTime();
		loginTimeMillis = System.currentTimeMillis();
		assignSubProps(info.getSubProps());
		
		auth = info.getAuth();
	}
	
	private void assignSubProps(SubscribeProperties subProps) {
		this.subProps = subProps;
		if (Configurations.getInstance().isTelcomOperatorsTelcomgd()) {
			try {
				availablePoints = Integer.parseInt(pm.myDXScore);
			}
			catch (Exception e) {
				e.printStackTrace();
				availablePoints = 0;
			}
		}
		else {
			availablePoints = subProps.getAvailablePoints();
		}
		balance = subProps.getBalance();
	}
	
	public void setupOfflineParam() {
		loginTime = new java.util.Date();
		loginTimeMillis = System.currentTimeMillis();
		auth = new Authorization();
		auth.setAuthorizationType(Authorization.AUTHORIZATION_FREE);

		subProps = new SubscribeProperties();
		subProps.setSupportSubscribe(true);
		subProps.setSubscribeAmountUnit("Ԫ");
		subProps.setSubscribeCashToAmountRatio(1);
		subProps.setSupportSubscribeByPoints(true);
		subProps.setPointsUnit("����");
		subProps.setAvailablePoints(0);
		subProps.setCashToPointsRatio(100);
		subProps.setSupportRecharge(true);
		subProps.setExpendAmountUnit("Ԫ��");
		subProps.setExpendCashToAmountRatio(10);
		subProps.setBalance(0);
		subProps.setRechargeRatio(10);
	}
	
	
	public boolean isLoginSuccessful() {
		return loginSuccessful;
	}
	
	public String getLoginMessage() {
		return loginMessage;
	}
	
	private void setLoginSuccessful() {
		loginSuccessful = true;
	}
	
	public boolean userLogin() {
		if (!pm.offline) {
			if (!isLoginSuccessful()) {
				try {
					ServiceWrapper sw = engine.getServiceWrapper();
					LoginInfo info = sw.userLogin();
					if (sw.isServiceSuccessful()) {
						setLoginSuccessful();
						assignLoginInfo(info);
						System.out.println("�û���¼�ɹ�:");
						printParams();
					}
					else {
						loginMessage = sw.getServiceMessage();
					}
				}
				catch (Exception e) {
					loginMessage = e.getMessage();
					System.out.println(e.getMessage());
					e.printStackTrace();
				}
			}
			return isLoginSuccessful();
		}
		else {
			setupOfflineParam();
			setLoginSuccessful();
			printParams();
			return isLoginSuccessful();
		}
	}
	
	public boolean isOffline() {
		return pm.offline;
	}
	
	public String getRechargeCommand() {
		return Configurations.getInstance().getRechargeCmd();
	}
	
	public String getUserId() {
		return pm.userId;
	}
	
	public String getAccountName() {
		return pm.accountName;
	}
	
	public String getProductName() {
		return pm.productName;
	}
	
	public boolean isSupportRecharge() {
		return subProps.isSupportRecharge();
	}
	
	public String getExpendAmountUnit() {
		return subProps.getExpendAmountUnit();
	}
	
	public int getRechargeRatio() {
		return subProps.getRechargeRatio();
	}
	
	public boolean isSupportSubscribe() {
		return subProps.isSupportSubscribe();
	}
	
	public String getSubscribeAmountUnit() {
		return subProps.getSubscribeAmountUnit();
	}
	
	public int getSubscribeCashToAmountRatio() {
		return subProps.getSubscribeCashToAmountRatio();
	}
	
	public int getExpendCashToAmountRatio() {
		return subProps.getExpendCashToAmountRatio();
	}
	
	public boolean isSupportSubscribeByPoints() {
		return subProps.isSupportSubscribeByPoints();
	}
	
	public String getPointsUnit() {
		return subProps.getPointsUnit();
	}
	
	public int getAvailablePoints() {
		return availablePoints;
	}
	
	public int getBalance() {
		return balance;
	}
	
	public int getCashToPointsRatio() {
		return subProps.getCashToPointsRatio();
	}
	
	public java.util.Date getLoginTime() {
		if (pm.offline) {
			return new java.util.Date();
		}
		else {
			return loginTime;
		}
	}
	
	public java.util.Date getCurrentTime() {
		if (pm.offline) {
			return new java.util.Date();
		}
		else {
			long pastMillis = System.currentTimeMillis() - loginTimeMillis;
			return new java.util.Date(loginTime.getTime()+pastMillis);
		}
	}
	
	public Authorization getAuthorization() {
		return auth;
	}
	
	public int[] getRechargeAmounts() {
		return pm.rechargeAmounts;
	}
	
	public int calcSubscribeAmount(int amount) {
		return amount*getSubscribeCashToAmountRatio();
	}
	
	public int calcExpendAmount(int amount) {
		return (short)(amount*getExpendCashToAmountRatio()/getRechargeRatio());
	}
	
	public ServiceWrapper getServiceWrapper() {
		return new ServiceWrapper(engine, pm.server);
	}
	
	public ServiceWrapper getServiceWrapper(String server) {
		return new ServiceWrapper(engine, server);
	}
	
	ParamManager getParamManager() {
		return pm;
	}
}
