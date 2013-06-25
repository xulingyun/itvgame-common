package cn.ohyeah.stb.game;

/**
 * ���������࣬����У��jad����
 * @author maqian
 * @version 1.0
 */
final class ParamManager {
	public static final String ENTRANCE_OHYEAH = "ohyeah";
	public static final String ENTRANCE_THE9 = "the9";
	public static final String ENTRANCE_WINSIDE = "winside";
	public static final String ENTRANCE_WINSIDEGD = "winsidegd";
	
	
	int[] rechargeAmounts;	/*��ֵ����б�*/
	
	String stbType;			/*�����������*/
	String enterURL;		/*�����������*/
	String zyUserToken;		/*�����������*/
	String myDXScore;		/*�����������*/
	String hosturl;			/*�����������, �ղز���*/
	String code;			/*�����������*/
	String timeStmp;		/*�����������*/
	
	String dijoyAppID;			/*�����������*/
	String dijoyRechargeUrl;	/*�����������, ��ֵҳ��URL��ַ*/
	String dijoyPlatformExt;	/*�����������, ��ҳƽ̨���ݸ�Ӧ�õ���չ��Ϣ*/
	String dijoyReturnUrl;		/*�����������*/
	String dijoyHomeUrl;		/*�����������*/
	//String dijoyAppExt;		/*�����������*/
	
	String shengyiCPID;			/*ʢ���������*/
	String shengyiCPPassWord;	/*ʢ���������*/
	String shengyiUserIdType;	/*ʢ���������*/
	String shengyiProductId;	/*ʢ���������*/
	
	String feeaccount;			/*�����˺�*/
	String returnurl;			/*���ص�ַ*/
	String dwjvl;				/*ƽ̨������֤*/
	String opcomkey;			/*�����ʶ*/
	String paysubway;			/*��ֵ���ͣ�����/����*/
	String user_group_id;		/*��Ӫ�̵�����ʶ*/
	String appId;				/*appId, ����ʹ�ò���*/
	
	String spid;			/*��Ӧ��ID�����κ��������ֵʱ��Ҫ*/
	String gameid;			/*��ϷID�����κ��������ֵʱ��Ҫ*/
	String buyURL;			/*��ѯԪ���Ϳ۳�Ԫ����������ַ*/
	String checkKey;		/*MD5�����ַ���*/
	
	String server;			/*��Ϸ��������ַ*/
	int accountId;			/*�û��˺�*/
	String userId;			/*������ID*/
	String accountName;		/*�û��ǳ�*/
	String userToken;		
	int productId;			/*��ƷID*/
	String productName;		/*��Ʒ��������*/
	String appName;			/*��ƷӢ������*/
	
	boolean offline;
	
	private String errorMessage;
	private boolean parseSuccessful;
	private IEngine engine;
	
	public ParamManager(IEngine engine) {
		this.engine = engine;
	}
	
	public boolean parse() {
		try {
			parseSuccessful = true;
			errorMessage = "";
			parseParam();
			if (!parseSuccessful) {
				System.out.println(errorMessage);
			}
		}
		catch (Exception e) {
			parseSuccessful = false;
			errorMessage += e.getMessage()+"\n";
		}
		return parseSuccessful;
	}
	
	private void parseParam() {
		String off = engine.getAppProperty("offline");
		if (off != null && ("yes".equals(off)||"on".equals(off)||"true".equals(off))) {
			offline = true;
			System.out.println("[����] ==> "+"����ģʽ��������ģʽֻ���ڲ��ԣ��ڼ佫�����ӷ�����");
			System.out.println("[����] ==> "+"����ر�����ģʽ���뽫jad�ļ������ò���offline��ֵ����Ϊ\"false\"");
		}
		else {
			offline = false;
		}
		
		Configurations conf = Configurations.getInstance();
		System.out.println("telcomOperators:"+conf.getTelcomOperators());
		System.out.println("serviceProvider:"+conf.getServiceProvider());
		if (conf.isServiceProviderWinside()) {
			if (conf.isTelcomOperatorsTelcomgd()) {
				parseWinsidegdPlatParam();
			}
			else {
				parseWinsidePlatParam();
			}
		}
		else if (conf.isServiceProviderThe9()
				|| conf.isServiceProviderOhyeah()) {
			parseOhyeahPlatParam();
		}else if(conf.isServiceProviderDijoy()){
			parseDijoyPlatParam();
		}else if(conf.isServiceProviderShengYi()){
			parseShengYiPlatParam();
		}else if(conf.isServiceProviderShiXian()){
			parseShiXianPlatParam();
		}
		else {
			parseSuccessful = false;
			errorMessage += "[����] ==> "+"δ֪����ڲ���"+conf.getServiceProvider()+"\n";
		}
		
		String amounts = conf.getPrice();
		if (amounts == null || "".equals(amounts)) {
			amounts = getStringParam("price");
		}
		if (amounts != null && !"".equals(amounts)) {
			parseAmounts(amounts);
		}
	}
	
	private void parseAmounts(String amounts) {
		try {
			int prevPos = 0;
			int scanPos = 0;
			int amountCount = 1;
			if (!amounts.startsWith("/") && !amounts.endsWith("/") && amounts.indexOf("//")<0) {
				while (scanPos < amounts.length()) {
					if (amounts.charAt(scanPos) == '/') {
						++amountCount;
					}
					++scanPos;
				}
				rechargeAmounts = new int[amountCount];
				
				scanPos = 0;
				amountCount = 0;
				while (scanPos < amounts.length()) {
					if (amounts.charAt(scanPos) == '/') {
						rechargeAmounts[amountCount] = Integer.parseInt(amounts.substring(prevPos, scanPos));
						++amountCount;
						prevPos = scanPos+1;
					}
					++scanPos;
				}
				rechargeAmounts[amountCount] = Integer.parseInt(amounts.substring(prevPos));
			}
			else {
				parseSuccessful = false;
				errorMessage += "[����] ==> "+"����"+"\""+"price"+"\""+"��ʽ����"+"\n";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			parseSuccessful = false;
			errorMessage += "[����] ==> "+"����"+"\""+"price"+"\""+"��ʽ����"+"\n";
		}
	}
	
	private void parseOhyeahPlatParam() {
		server = getStringParam("server");
		userId = getStringParam("userId");
		int index = userId.indexOf(Configurations.USERID_SUFFIX);
		if(index>=0){
			userId = userId.substring(0, index);
			System.out.println("userId-->>"+userId);
		}
		accountName = getStringParam("accountName");
		userToken = getStringParam("userToken");
		appName = getStringParam("appName");
		buyURL = "";
		gameid = getStringParam("gameid");
		spid = "";//getStringParam("spid");;
		checkKey = "";
	}
	
	private void parseDijoyPlatParam() {
		userId = getStringParam("UserID");
		dijoyHomeUrl = getStringParam("HomeUrl");
		server = getStringParam("loginUrl");
		accountName = getStringParam("LoginID");
        appName = Configurations.getInstance().getAppName();
        if (appName == null || "".equals(appName)) {
		    appName = getStringParam("appName");
        }
		dijoyAppID = getStringParam("AppID");
		dijoyReturnUrl = getStringParam("ReturnUrl");
		dijoyPlatformExt = getStringParamDijoy("PlatformExt"); //�ò�������Ϊ��
		buyURL = getStringParam("BuyService");
		userToken = "";
		spid = "";
		//checkKey = Configurations.getInstance().getDijoyPayKey();
        //if (checkKey == null || "".equals(checkKey)) {
    	checkKey = getStringParam("payKey");
        //}
	}
	
	private void parseShengYiPlatParam(){
		server = getStringParam("server");
		userId = getStringParam("userId");
		accountName = getStringParam("accountName");
		userToken = getStringParam("userToken");
		appName = getStringParam("appName");
		shengyiCPID = getStringParam("cpId");
		shengyiCPPassWord = getStringParam("cpPassWord");
		shengyiUserIdType = getStringParam("userIdType");
		shengyiProductId = getStringParam("shengyiProductID");
		buyURL = "";
		gameid = "";
		spid = "";
		checkKey = "";
	}
	
	private void parseShiXianPlatParam() {
		server = getStringParam("loginurl");
		userId = getStringParam("tvplat#numbercode");
		userToken = ""/*getStringParam("USER_TOKEN")*/;
		appName = getStringParam("appname");
		feeaccount = getStringParam("tvplat#feeaccount");
		returnurl = ""/*getStringParam("tvplat#returnurl")*/;
		dwjvl = getStringParam("tvplat#dwjvl");
		opcomkey = getStringParam("tvplat#opcomkey");
		paysubway = getStringParam("tvplat#paysubway");
		user_group_id = ""/*getStringParam("USER_GROUP_ID")*/;
		buyURL = getStringParam("buyURL");
		gameid = ""/*getStringParam("tvplat#gameid")*/;
		spid = "";
		checkKey = "";
		accountName = userId;
		appId = Configurations.getInstance().getAppId();
	}
	
	private void parseWinsidegdPlatParam() {
		server = getStringParam("w_server");
		userId = getStringParam("userid");
		accountName = getStringParam("iptvname");
		spid = getStringParam("spid");
		gameid = getStringParam("gameid");
		appName = getStringParam("product");
		checkKey = getStringParam("checkKey");
		buyURL = getStringParam("buyURL");
		zyUserToken = getStringParam("zyUserToken");
		stbType = getStringParam("stbType");
		enterURL = getStringParam("enterURL");
		myDXScore = getStringParam("myDXScore");
		hosturl = getStringParam("hosturl");
		code = getStringParam("gameCode");
		timeStmp = getStringParam("timeStmp");
		userToken = zyUserToken;
	}
	
	private void parseWinsidePlatParam() {
		server = getStringParam("loginurl");
		userId = getStringParam("userid");
		accountName = getStringParam("username");
		userToken = getStringParam("userToken");
		gameid = getStringParam("gameid");
		spid = getStringParam("spid");
		appName = getStringParam("product");
		checkKey = getStringParam("checkKey");
		buyURL = getStringParam("buyURL");
	}
	
	private String getStringParam(String paramName) {
		String paramValue = null;
		try {
			paramValue = engine.getAppProperty(paramName).trim();
			if ("".equals(paramValue)) {
				parseSuccessful = false;
				errorMessage += "[��Ϣ] ==> "+"��ȡ����"+"\""+paramName+"\""+"ʧ��"+"\n";
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			parseSuccessful = false;
			errorMessage += "[��Ϣ] ==> "+"��ȡ����"+"\""+paramName+"\""+"ʧ��"+"\n";
		}
		return paramValue;
	}
	
	private String getStringParamDijoy(String paramName) {
		String paramValue = null;
		try {
			paramValue = engine.getAppProperty(paramName).trim();
		}
		catch (Exception e) {
			e.printStackTrace();
			parseSuccessful = false;
			errorMessage += "[��Ϣ] ==> "+"��ȡ����"+"\""+paramName+"\""+"ʧ��"+"\n";
		}
		return paramValue;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public boolean isParseSuccess() {
		return parseSuccessful;
	}
}
