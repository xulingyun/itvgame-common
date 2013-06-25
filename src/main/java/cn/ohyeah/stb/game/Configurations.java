package cn.ohyeah.stb.game;

import cn.ohyeah.stb.util.Properties;

/**
 * ��Ϸ������
 * @author maqian
 * @version 1.0
 */
public class Configurations {
	private static String DEFAULT_CONF_PATH = "/conf/game.conf";
	public static final String TAG_RECHARGE = "recharge";
	public static final String TAG_EXCHANGE = "exchange";
	public static final String TAG_COMMON = "common";
	public static final String TAG_OK = "ok";
	public static final String TAG_BACK = "back";
	public static final String TAG_CANCEL = "cancel";
	public static final String TAG_NONSUPPORT = "nonsupport";
	public static final String TAG_TELCOMSH = "telcomsh";			/*�Ϻ�����*/
	public static final String TAG_TELCOMGD = "telcomgd";			/*�㶫����*/
	public static final String TAG_TELCOMJS = "telcomjs";			/*���յ���*/
	public static final String TAG_TELCOMAH = "telcomah";			/*���յ���*/
	public static final String TAG_TELCOMFJ = "telcomfj";			/*��������*/
	public static final String TAG_TELCOMHN = "telcomhn";			/*���ϵ���*/
	public static final String TAG_TIANWEISZ = "tianweisz";			/*��������*/
	public static final String TAG_TELCOMGS = "telcomgs";			/*�������*/
	public static final String TAG_TELCOMCOMMON = "telcomCommon";	/*��������*/

	public static final String TAG_OHYEAH = "ohyeah";				/*ŷҮƽ̨*/
	public static final String TAG_WINSIDE = "winside";				/*������ƽ̨*/
	public static final String TAG_THE9 = "the9";					/*�ų�ƽ̨*/
	public static final String TAG_DIJOY = "dijoy";					/*����ƽ̨*/
	public static final String TAG_SHENGYI = "shengyi";				/*ʢ��ƽ̨*/
	public static final String TAG_SHIXIAN = "shixian";				/*����ƽ̨*/
	
	public static final short Abs_Coords_X = 0, Abs_Coords_Y = 0;
	public static final String USERID_SUFFIX = "@iptv2";			/*ͬ�˺ź�׺*/
	
	private static Configurations instance = new Configurations();
	private static boolean success;
	private static String errorMessage;
	private String telcomOperators;
	private String serviceProvider;
	private String favorWay;
	private String rechargeWay;
	private String subscribeFocus;
	private String rechargeCmd;
	private String price;
    private String appName;
    private String appId;
	
	public static Configurations loadConfigurations() {
		return loadConfigurations(DEFAULT_CONF_PATH);
	}
	
	public static Configurations loadConfigurations(String path) {
		try {
			Properties props = new Properties();
			props.parseFile(path, "UTF-8");
			instance.setProperties(props);
			success = true;
		}
		catch (Exception e) {
			success = false;
			errorMessage = e.getMessage();
		}
		return instance;
	}
	
	private void setProperties(Properties props) {
		telcomOperators = props.get("telcomOperators");
		serviceProvider = props.get("serviceProvider");
		favorWay = props.get("favorWay");
		rechargeWay = props.get("rechargeWay");
		subscribeFocus = props.get("subscribeFocus");
		rechargeCmd = props.get("rechargeCmd");
		price = props.get("price");
        appName = props.get("appName");
        appId = props.get("appId");
	}
	
	public static Configurations getInstance() {
		return instance;
	}
	
	public static boolean isLoadConfSuccess() {
		return success;
	}
	
	public static String getErrorMessage() {
		return errorMessage;
	}
	
	public String getTelcomOperators() {
		return telcomOperators;
	}
	
	public String getServiceProvider() {
		return serviceProvider;
	}
	
	public boolean isTelcomOperatorsTelcomsh() {
		return TAG_TELCOMSH.equals(telcomOperators);
	}
	
	public boolean isTelcomOperatorsTelcomhn() {
		return TAG_TELCOMHN.equals(telcomOperators);
	}
	
	public boolean isTelcomOperatorsTelcomgd() {
		return TAG_TELCOMGD.equals(telcomOperators);
	}
	
	public boolean isTelcomOperatorsTelcomfj() {
		return TAG_TELCOMFJ.equals(telcomOperators);
	}
	
	public boolean isTelcomOperatorsCommon() {
		return TAG_TELCOMCOMMON.equals(telcomOperators);
	}
	
	public boolean isTelcomOperatorsTelcomah() {
		return TAG_TELCOMAH.equals(telcomOperators);
	}
	
	public boolean isTelcomOperatorsTianweiSZ() {
		return TAG_TIANWEISZ.equals(telcomOperators);
	}
	public boolean isTelcomOperatorsTelcomgs() {
		return TAG_TELCOMGS.equals(telcomOperators);
	}
	
	public boolean isServiceProviderOhyeah() {
		return TAG_OHYEAH.equals(serviceProvider);
	}
	
	public boolean isServiceProviderWinside() {
		return TAG_WINSIDE.equals(serviceProvider);
	}
	
	public boolean isServiceProviderThe9() {
		return TAG_THE9.equals(serviceProvider);
	}
	
	public boolean isServiceProviderDijoy() {
		return TAG_DIJOY.equals(serviceProvider);
	}
	
	public boolean isServiceProviderShengYi() {
		return TAG_SHENGYI.equals(serviceProvider);
	}
	
	public boolean isServiceProviderShiXian() {
		return TAG_SHIXIAN.equals(serviceProvider);
	}
	
	public String getFavorWay() {
		return favorWay;
	}
	
	public boolean isFavorWayNonsupport() {
		return TAG_NONSUPPORT.equals(favorWay);
	}
	
	public boolean isFavorWayTelcomgd() {
		return TAG_TELCOMGD.equals(favorWay);
	}
	
	public String getSubscribeFocus() {
		return subscribeFocus;
	}
	
	public boolean isSubscribeFocusOk() {
		return TAG_OK.equals(subscribeFocus);
	}
	
	public String getRechargeWay() {
		return rechargeWay;
	}
	
	public boolean isRechargeWayTelcomgd() {
		return TAG_TELCOMGD.equals(rechargeWay);
	}
	
	public boolean isRechargeWayTelcomfj() {
		return TAG_TELCOMFJ.equals(rechargeWay);
	}
	
	public boolean isRechargeWayCommon() {
		return TAG_COMMON.equals(rechargeWay);
	}
	
	public boolean isRechargeCmdExchange() {
		return TAG_EXCHANGE.equals(rechargeCmd);
	}
	
	public boolean isRechargeCmdRecharge() {
		return TAG_RECHARGE.equals(rechargeCmd);
	}
	
	public String getRechargeCmd() {
		String cmd = "��ֵ";
		if (isRechargeCmdExchange()) {
			cmd = "�һ�";
		}
		else {
			cmd = "��ֵ";
		}
		return cmd;
	}
	
	public String getPrice() {
		return price;
	}

    public String getAppName() {
        return appName;
    }
    
    public String getAppId(){
    	return appId;
    }
    
   /* public String getDijoyPayKey(){
    	return dijoyPayKey;
    }*/
	
	public String toString() {
		return telcomOperators+";"+serviceProvider+";"+favorWay
				+";"+rechargeWay+";"+subscribeFocus+";"+rechargeCmd+";"+price;
	}
}
