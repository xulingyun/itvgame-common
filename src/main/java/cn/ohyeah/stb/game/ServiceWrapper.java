package cn.ohyeah.stb.game;

import cn.ohyeah.itvgame.model.Authorization;
import cn.ohyeah.itvgame.model.GameAttainment;
import cn.ohyeah.itvgame.model.GameAttainmentDesc;
import cn.ohyeah.itvgame.model.GameRanking;
import cn.ohyeah.itvgame.model.GameRecord;
import cn.ohyeah.itvgame.model.GameRecordDesc;
import cn.ohyeah.itvgame.model.LoginInfo;
import cn.ohyeah.itvgame.model.OwnProp;
import cn.ohyeah.itvgame.model.Prop;
import cn.ohyeah.itvgame.model.PurchaseRecord;
import cn.ohyeah.itvgame.model.SubscribePayType;
import cn.ohyeah.itvgame.model.SubscribeProperties;
import cn.ohyeah.itvgame.model.SubscribeRecord;
import cn.ohyeah.itvgame.protocol.Constant;
import cn.ohyeah.itvgame.service.AccountService;
import cn.ohyeah.itvgame.service.AttainmentService;
import cn.ohyeah.itvgame.service.PropService;
import cn.ohyeah.itvgame.service.PurchaseService;
import cn.ohyeah.itvgame.service.RecordService;
import cn.ohyeah.itvgame.service.SubscribeService;
import cn.ohyeah.itvgame.service.SystemService;

/**
 * �����װ�࣬�Է��������˼򵥵İ�װ��
 * Ϊ�˱�֤�̰߳�ȫ�����ȴ����µĶ����ٵ���
 * @author maqian
 * @version 1.0
 */
public final class ServiceWrapper {
	private static final String OFFLINE_MSG = "����ģʽ��֧�ִ˲���";
	private static boolean offline;
	
	private String server;
	private IEngine engine;
	private ParamManager paramManager;
	private EngineService engineService;
	
	private int result;
	private String message;
	
	
	public ServiceWrapper(IEngine engine, String server) {
		this.engine = engine;
		this.engineService = engine.getEngineService();
		this.paramManager = engineService.getParamManager();
		this.server = server;
		offline = paramManager.offline;
	}
	
	/**
	 * ��ѯ��Ȩ
	 * �ж�ĳ�û���ĳ������Ϸ�Ƿ��м�Ȩ(�Ƿ��Ѱ���)
	 * @return ��Ȩ��Ϣ
	 */
	public Authorization queryAuthorization() {
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return null;
		}
		try {
			AccountService accountService = new AccountService(server);
			Authorization auth = accountService.getAuthorization(paramManager.accountId, paramManager.productId);
			result = accountService.getResult();
			if (result != 0) {
				message = accountService.getMessage();
			}
			return auth;
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
			return null;
		}
	}
	
	/**
	 * ��ѯ��������
	 * @return ��������
	 */
	public SubscribeProperties querySubscribeProperties() {
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return null;
		}
		try {
			AccountService accountService = new AccountService(server);
			SubscribeProperties subProps = accountService.getSubscribeProperties(paramManager.buyURL, 
					paramManager.accountId, paramManager.accountName, paramManager.userToken, paramManager.productId, paramManager.checkKey);
			result = accountService.getResult();
			if (result != 0) {
				message = accountService.getMessage();
			}
			return subProps;
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
			return null;
		}
	}
	
	/**
	 * �û�����
	 * @return ������Ϣ
	 */
	public LoginInfo userLogin() {
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return null;
		}
		try {
			AccountService accountService = new AccountService(server);
			LoginInfo info = accountService.userLogin(paramManager.buyURL, 
					paramManager.userId, paramManager.accountName, paramManager.userToken, paramManager.appName, paramManager.checkKey);
			result = accountService.getResult();
			if (result != 0) {
				message = accountService.getMessage();
			}
			return info;
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
			return null;
		}
	}
	
	/**
	 * ��ѯ�ɾ�������Ϣ
	 * @return ��Ϸ�ɾ�����
	 */
	public GameAttainmentDesc[] queryAttainmentDescList() {
		return queryAttainmentDescList("desc", null, null);
	}
	
	/**
	 * ��ѯ�ɾ�������Ϣ
	 * @param start ��ʼʱ��
	 * @param end	����ʱ��
	 * @return ��Ϸ�ɾ�����
	 */
	public GameAttainmentDesc[] queryAttainmentDescList(java.util.Date start, java.util.Date end) {
		return queryAttainmentDescList("desc", start, end);
	}
	
	/**
	 * ��ѯ�ɾ�������Ϣ
	 * @param orderCmd ����ʽ
	 * @param start	��ʼʱ��
	 * @param end	����ʱ��
	 * @return ��Ϸ�ɾ�����
	 */
	public GameAttainmentDesc[] queryAttainmentDescList(String orderCmd, java.util.Date start, java.util.Date end) {
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return null;
		}
		try {
			AttainmentService attainmentService = new AttainmentService(server);
			GameAttainmentDesc[] descList = attainmentService.queryDescList(paramManager.accountId, 
					paramManager.productId, orderCmd, start, end);
			result = attainmentService.getResult();
			if (result != 0) {
				message = attainmentService.getMessage();
			}
			return descList;
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
			return null;
		}
	}
	
	/**
	 * ��ѯ�����б�
	 * @param offset	��ʼλ��
	 * @param length	��¼����
	 * @return ��Ϸ����
	 */
	public GameRanking[] queryRankingList(int offset, int length) {
		return queryRankingList("desc", null, null, offset, length);
	}
	
	/**
	 * ��ѯ�����б�
	 * @param start	��ʼʱ��
	 * @param end	����ʱ��
	 * @param offset	��ʼλ��
	 * @param length	��¼����
	 * @return ��Ϸ����
	 */
	public GameRanking[] queryRankingList(java.util.Date start, java.util.Date end, int offset, int length) {
		return queryRankingList("desc", start, end, offset, length);
	}
	
	/**
	 * ��ѯ�����б�
	 * @param orderCmd	����ʽ
	 * @param start	��ʼʱ��
	 * @param end	����ʱ��
	 * @param offset	��ʼλ��
	 * @param length	��¼����
	 * @return ��Ϸ����
	 */
	public GameRanking[] queryRankingList(String orderCmd, java.util.Date start, java.util.Date end, int offset, int length) {
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return null;
		}
		try {
			AttainmentService attainmentService = new AttainmentService(server);
			GameRanking[] rankList = attainmentService.queryRankingList(paramManager.productId, 
					orderCmd, start, end, offset, length);
			result = attainmentService.getResult();
			if (result != 0) {
				message = attainmentService.getMessage();
			}
			return rankList;
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
			return null;
		}
	}
	
	/**
	 * ��ȡ��Ϸ�ɾ�
	 * @param attainmentId �ɾ�id
	 * @return	��Ϸ�ɾ�
	 */
	public GameAttainment readAttainment(int attainmentId) {
		return readAttainment(attainmentId, "desc", null, null);
	}
	
	/**
	 * ��ȡ��Ϸ�ɾ�
	 * @param attainmentId	�ɾ�id
	 * @param start	��ʼʱ��
	 * @param end	����ʱ��
	 * @return ��Ϸ�ɾ�
	 */
	public GameAttainment readAttainment(int attainmentId, java.util.Date start, java.util.Date end) {
		return readAttainment(attainmentId, "desc", start, end);
	}
	
	/**
	 * ��ȡ��Ϸ�ɾ�
	 * @param attainmentId	�ɾ�id
	 * @param orderCmd	����ʽ
	 * @param start	��ʼʱ��
	 * @param end	����ʱ��
	 * @return	��Ϸ�ɾ�
	 */
	public GameAttainment readAttainment(int attainmentId, String orderCmd, java.util.Date start, java.util.Date end) {
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return null;
		}
		try {
			AttainmentService attainmentService = new AttainmentService(server);
			GameAttainment ga = attainmentService.read(paramManager.accountId, paramManager.productId, 
					attainmentId, orderCmd, start, end);
			result = attainmentService.getResult();
			if (result != 0) {
				message = attainmentService.getMessage();
			}
			return ga;
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
			return null;
		}
	}
	
	/**
	 * ������Ϸ�ɾ�
	 * @param attainment
	 */
	public void saveAttainment(GameAttainment attainment) {
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return;
		}
		try {
			AttainmentService attainmentService = new AttainmentService(server);
			attainmentService.save(paramManager.accountId, paramManager.userId, paramManager.productId, attainment);
			result = attainmentService.getResult();
			if (result != 0) {
				message = attainmentService.getMessage();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
		}
	}
	
	/**
	 * ������Ϸ�ɾ�
	 * @param attainment
	 */
	public void updateAttainment(GameAttainment attainment) {
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return;
		}
		try {
			AttainmentService attainmentService = new AttainmentService(server);
			attainmentService.update(paramManager.accountId, paramManager.userId, paramManager.productId, attainment);
			result = attainmentService.getResult();
			if (result != 0) {
				message = attainmentService.getMessage();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
		}
	}
	
	/**
	 * ��ѯ�û���ĳһ����Ϸ�Ϲ���ĵ����б�
	 * @return	�û������б�
	 */
	public OwnProp[] queryOwnPropList() {
		if (engine.isDebugMode()) {
			result = 0;
			return null;
		}
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return null;
		}
		try {
			PropService propService = new PropService(server);
			OwnProp[] ownProps = propService.queryOwnPropList(paramManager.accountId, paramManager.productId);
			result = propService.getResult();
			if (result != 0) {
				message = propService.getMessage();
			}
			return ownProps;
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
			return null;
		}
	}
	
	/**
	 * ��ѯĳһ����Ϸ�ĵ����б�
	 * @return	�����б�
	 */
	public Prop[] queryGamePropList() {
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return null;
		}
		try {
			PropService propService = new PropService(server);
			Prop[] props = propService.queryPropList(paramManager.productId);
			result = propService.getResult();
			if (result != 0) {
				message = propService.getMessage();
			}
			return props;
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
			return null;
		}
	}
	
	/**
	 * ͬ������
	 * @param propId ����id
	 * @param count ��������
	 */
	public void synProps(int propId, int count) {
		synProps(new int[]{propId}, new int[]{count});
	}
	
	/**
	 * ͬ������
	 * @param propIds	����id�б�
	 * @param counts	���������б�
	 */
	public void synProps(int[] propIds, int[] counts) {
		if (engine.isDebugMode()) {
			result = 0;
			return;
		}
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return;
		}
		try {
			PropService propService = new PropService(server);
			propService.synProps(paramManager.accountId, paramManager.productId, propIds, counts);
			result = propService.getResult();
			if (result != 0) {
				message = propService.getMessage();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
		}
	}
	
	/**
	 * ���µ���
	 * @param propId	����id
	 * @param num	��������
	 */
	public void useProps(int propId, int num) {
		useProps(new int[]{propId}, new int[]{num});
	}
	
	/**
	 * ���µ���
	 * @param propIds	����id�б�
	 * @param nums	���������б�
	 */
	public void useProps(int[] propIds, int[] nums) {
		if (engine.isDebugMode()) {
			result = 0;
			return;
		}
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return;
		}
		try {
			PropService propService = new PropService(server);
			propService.useProps(paramManager.accountId, paramManager.productId, propIds, nums);
			result = propService.getResult();
			if (result != 0) {
				message = propService.getMessage();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
		}
	}
	
	/**
	 * �û�����
	 * @param amount
	 * @param remark
	 */
	public void expend(int amount, String remark) {
		if (engine.isDebugMode()) {
			result = 0;
			return;
		}
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return;
		}
		try {
			PurchaseService purchaseService = new PurchaseService(server);
			int b = purchaseService.expend(paramManager.buyURL, paramManager.accountId, paramManager.accountName, 
						paramManager.userToken, paramManager.productId, amount, remark, paramManager.checkKey);
			result = purchaseService.getResult();
			if (result == 0) {
				if (b >= 0) {
					engineService.balance -= b;
				}
				else {
					engineService.balance += b;
				}
			}
			else {
				message = purchaseService.getMessage();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
		}
	}
	
	/**
	 * ���ڹ������
	 * @param price	�۸�
	 * @param propId	����id
	 * @param remark	����
	 */
	public void expend(int price, int propId, String remark) {
		if (engine.isDebugMode()) {
			result = 0;
			return;
		}
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return;
		}
		try {
			PurchaseService purchaseService = new PurchaseService(server);
			int b = 0;
			if(Configurations.getInstance().isTelcomOperatorsTelcomfj()){
				b = purchaseService.expendWinsideLack(paramManager.buyURL, paramManager.accountId, paramManager.accountName, 
						paramManager.userToken, paramManager.productId, propId, price, remark, paramManager.checkKey);
			}else if(Configurations.getInstance().isServiceProviderDijoy()){
				b = purchaseService.expendDijoy(paramManager.buyURL, paramManager.accountId,
                        paramManager.accountName, paramManager.userToken, paramManager.productId, price, propId,
                        remark,paramManager.dijoyAppID, paramManager.checkKey, paramManager.dijoyPlatformExt);
			}
			result = purchaseService.getResult();
			if (result == 0) {
				if (b >= 0) {
					engineService.balance -= b;
				}
				else {
					engineService.balance += b;
				}
			}
			else {
				message = purchaseService.getMessage();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
		}
	}
	
	/**
	 * �Ϻ���������(�������)
	 * @param propId ����id
	 * @param remark ����
	 */
	public void expendTelcomsh(int propId, String remark) {
		if (engine.isDebugMode()) {
			result = 0;
			return;
		}
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return;
		}
		try {
			PurchaseService purchaseService = new PurchaseService(server);
			int b = purchaseService.expendTelcomsh(paramManager.buyURL,  paramManager.userId,paramManager.userToken,paramManager.accountName,
					paramManager.accountId,paramManager.productId, propId, remark,paramManager.gameid);
			result = purchaseService.getResult();
			message = purchaseService.getMessage();
			if(result == 0){
				engineService.balance = b;
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
		}
	}
	
	/**
	 * �������
	 * @param propId
	 * @param propCount
	 * @param remark
	 */
	public void purchaseProp(int propId, int propCount, String remark) {
		if (engine.isDebugMode()) {
			result = 0;
			return;
		}
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return;
		}
		try {
			PurchaseService purchaseService = new PurchaseService(server);
			int	b = purchaseService.purchaseProp(paramManager.buyURL, paramManager.accountId, paramManager.accountName, 
						paramManager.userToken, paramManager.productId, propId, propCount, remark, paramManager.checkKey);
			result = purchaseService.getResult();
			if (result == 0) {
				if (b >= 0) {
					engineService.balance -= b;
				}
				else {
					engineService.balance += b;
				}
			}
			else {
				message = purchaseService.getMessage();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
		}
	}
	
	/**
	 * ��ѯ�����¼
	 * @param offset	��ʼλ��
	 * @param length	��¼����
	 * @return  �����¼
	 */
	public PurchaseRecord[] queryPurchaseRecord(int offset, int length) {
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return null;
		}
		try {
			PurchaseService purchaseService = new PurchaseService(server);
			PurchaseRecord[] pr = purchaseService.queryPurchasePropRecord(paramManager.accountId, 
					paramManager.productId, offset, length);
			result = purchaseService.getResult();
			if (result != 0) {
				message = purchaseService.getMessage();
			}
			return pr;
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
			return null;
		}
	}
	
	/**
	 * ��ѯ��¼������Ϣ
	 * @return ��Ϸ��¼����
	 */
	public GameRecordDesc[] queryRecordDescList() {
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return null;
		}
		try {
			RecordService recordService = new RecordService(server);
			GameRecordDesc[] descs = recordService.queryDescList(paramManager.accountId, paramManager.productId);
			result = recordService.getResult();
			if (result != 0) {
				message = recordService.getMessage();
			}
			return descs;
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
			return null;
		}
	}
	
	/**
	 * ��ȡ��Ϸ��¼
	 * @param recordId	��¼id
	 * @return	��Ϸ��¼
	 */
	public GameRecord readRecord(int recordId) {
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return null;
		}
		try {
			RecordService recordService = new RecordService(server);
			GameRecord gr = recordService.read(paramManager.accountId, paramManager.productId, recordId);
			result = recordService.getResult();
			if (result != 0) {
				message = recordService.getMessage();
			}
			if (result == Constant.EC_RECORD_NOT_EXIST) {
				result = 0;
			}
			return gr;
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
			return null;
		}
	}
	
	/**
	 * ������Ϸ��¼
	 * @param record
	 */
	public void saveRecord(GameRecord record) {
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return;
		}
		try {
			RecordService recordService = new RecordService(server);
			recordService.save(paramManager.accountId, paramManager.productId, record);
			result = recordService.getResult();
			if (result != 0) {
				message = recordService.getMessage();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
		}
	}
	
	/**
	 * ������Ϸ��¼
	 * @param record
	 */
	public void updateRecord(GameRecord record) {
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return;
		}
		try {
			RecordService recordService = new RecordService(server);
			recordService.update(paramManager.accountId, paramManager.productId, record);
			result = recordService.getResult();
			if (result != 0) {
				message = recordService.getMessage();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
		}
	}
	
	/**
	 * ��ѯ���
	 */
	public void queryBalance() {
		if (engine.isDebugMode()) {
			result = 0;
			return;
		}
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return;
		}
		try {
			SubscribeService subscribeService = new SubscribeService(server);
			int b = subscribeService.queryBalance(paramManager.buyURL, paramManager.accountId, paramManager.accountName, paramManager.productId);
			result = subscribeService.getResult();
			if (result == 0) {
				engineService.balance = b;
			}
			else {
				message = subscribeService.getMessage();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
		}
	}
	
	/**
	 * ��ѯ������¼
	 * @param offset	��ʼλ��
	 * @param length	��¼����
	 * @return ������¼�б�
	 */
	public SubscribeRecord[] querySubscribeRecord(int offset, int length) {
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return null;
		}
		try {
			SubscribeService subscribeService = new SubscribeService(server);
			SubscribeRecord[] sr = subscribeService.querySubscribeRecord(paramManager.userId, paramManager.productId, offset, length);
			result = subscribeService.getResult();
			if (result != 0) {
				message = subscribeService.getMessage();
			}
			return sr;
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
			return null;
		}
	}
	
	/**
	 * ��ֵ
	 * @param amount	���
	 * @param remark	����
	 * @param password	����
	 */
	public void recharge(int amount, String remark, String password) {
		if (engine.isDebugMode()) {
			result = 0;
			return;
		}
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return;
		}
		try {
			SubscribeService subscribeService = new SubscribeService(server);
			int b = 0;
			if (Configurations.getInstance().isTelcomOperatorsTelcomgd()) {
				b = subscribeService.rechargeWinsidegd(paramManager.buyURL, paramManager.accountId, paramManager.accountName, 
						paramManager.userToken, paramManager.productId, amount, engineService.subProps.getRechargeRatio(), 
						remark, paramManager.checkKey, paramManager.spid, paramManager.gameid, paramManager.enterURL, paramManager.stbType, password);
			}
			else {
				b = subscribeService.recharge(paramManager.buyURL, paramManager.accountId, paramManager.accountName, 
						paramManager.userToken, paramManager.productId, amount,	engineService.subProps.getRechargeRatio(), 
						remark, paramManager.checkKey, paramManager.spid, password);
			}
			result = subscribeService.getResult();
			if (result == 0) {
				if (b > 0) {
					engineService.balance += b;
				}
				else {
					engineService.balance = 0;
				}
			}
			else {
				message = subscribeService.getMessage();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
		}
	}
	
	/**
	 * ��ֵ
	 * @param amount	���
	 * @param payType	�˵�����(����֧�����˵�֧��)
	 * @param remark	����
	 * @param password	����
	 */
	public void recharge(int amount, int payType, String remark, String password) {
		if (engine.isDebugMode()) {
			result = 0;
			return;
		}
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return;
		}
		try {
			SubscribeService subscribeService = new SubscribeService(server);
			int b = 0;
			if (Configurations.getInstance().isTelcomOperatorsTelcomgd()) {
				b = subscribeService.rechargeWinsidegd(paramManager.buyURL, paramManager.accountId, paramManager.accountName, 
						paramManager.userToken, paramManager.productId, amount,	engineService.subProps.getRechargeRatio(), 
						payType, remark, paramManager.checkKey, paramManager.spid, paramManager.gameid, 
						paramManager.enterURL, paramManager.stbType, password); 
			}else if(Configurations.getInstance().isServiceProviderShengYi()){
				b = subscribeService.rechargeShengYi(paramManager.buyURL, paramManager.accountId, 
						paramManager.accountName, paramManager.userToken, paramManager.productId, 
						amount, engineService.subProps.getRechargeRatio(), remark, paramManager.checkKey, 
						paramManager.shengyiCPID, paramManager.shengyiCPPassWord, paramManager.shengyiUserIdType,
						paramManager.shengyiProductId,password);
			}else if(Configurations.getInstance().isServiceProviderShiXian()){
				b = subscribeService.rechargeShiXian(paramManager.buyURL, paramManager.accountId, 
						paramManager.accountName, paramManager.userToken, paramManager.productId, 
						amount, engineService.subProps.getRechargeRatio(), remark, paramManager.checkKey,
						paramManager.feeaccount, paramManager.returnurl, paramManager.dwjvl, paramManager.opcomkey, 
						paramManager.paysubway, paramManager.gameid, paramManager.user_group_id,password, paramManager.appId);
			}
			else {
				b = subscribeService.recharge(paramManager.buyURL, paramManager.accountId, paramManager.accountName, 
						paramManager.userToken, paramManager.productId, amount,	engineService.subProps.getRechargeRatio(), 
						payType, remark, paramManager.checkKey, paramManager.spid, password); 
			}
			result = subscribeService.getResult();
			if (result == 0) {
				if (b > 0) {
					engineService.balance += b;
				}
				else {
					engineService.balance = 0;
				}
				if (payType == SubscribePayType.PAY_TYPE_POINTS) {
					engineService.availablePoints -= amount;
				}
			}
			else {
				message = subscribeService.getMessage();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
		}
	}
	
	/**
	 * ����(��Ҫ�ǰ�����Ϸ�Ķ���)
	 * @param purchaseId
	 * @param remark
	 */
	public void subscribe(int purchaseId, String remark) {
		if (engine.isDebugMode()) {
			result = 0;
			return;
		}
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return;
		}
		try {
			SubscribeService subscribeService = new SubscribeService(server);
			subscribeService.subscribe(paramManager.buyURL, paramManager.accountId, paramManager.accountName, 
					paramManager.userToken, paramManager.productId, purchaseId, remark, paramManager.checkKey);
			result = subscribeService.getResult();
			if (result != 0) {
				message = subscribeService.getMessage();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
		}
	}
	
	/**
	 * ����(��Ҫ�ǰ�����Ϸ�Ķ���)
	 * @param purchaseId
	 * @param payType �˵�����(����֧�����˵�֧��)
	 * @param remark
	 */
	public void subscribe(int purchaseId, int payType, String remark) {
		if (engine.isDebugMode()) {
			result = 0;
			return;
		}
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return;
		}
		try {
			SubscribeService subscribeService = new SubscribeService(server);
			subscribeService.subscribe(paramManager.buyURL, paramManager.accountId, paramManager.accountName, 
					paramManager.userToken, paramManager.productId, purchaseId, payType, remark, paramManager.checkKey);
			result = subscribeService.getResult();
			if (result != 0) {
				message = subscribeService.getMessage();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
		}
	}
	
	/**
	 * ��Ʒ����
	 * @param subscribeType
	 * @param remark
	 */
	public void subscribeProduct(String subscribeType, String remark) {
		if (engine.isDebugMode()) {
			result = 0;
			return;
		}
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return;
		}
		try {
			SubscribeService subscribeService = new SubscribeService(server);
			if(Configurations.getInstance().isServiceProviderShengYi()){
				subscribeService.subscribeProductShengyi(paramManager.accountId, 
						paramManager.accountName, paramManager.userToken, paramManager.productId, 
						subscribeType, remark, paramManager.shengyiCPID, paramManager.shengyiCPPassWord,
						paramManager.shengyiUserIdType, paramManager.shengyiProductId);
			}else{
				subscribeService.subscribeProduct(paramManager.buyURL, paramManager.accountId, paramManager.accountName, 
						paramManager.userToken, paramManager.productId, subscribeType, remark, paramManager.checkKey);
			}
			result = subscribeService.getResult();
			if (result != 0) {
				message = subscribeService.getMessage();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
		}
	}
	
	/**
	 * ��Ʒ����
	 * @param subscribeType
	 * @param remark
	 */
	public void subscribeProduct(String subscribeType, int payType, String remark) {
		if (engine.isDebugMode()) {
			result = 0;
			return;
		}
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return;
		}
		try {
			SubscribeService subscribeService = new SubscribeService(server);
			subscribeService.subscribeProduct(paramManager.buyURL, paramManager.accountId, paramManager.accountName, 
					paramManager.userToken, paramManager.productId, subscribeType, payType, remark, paramManager.checkKey);
			result = subscribeService.getResult();
			if (result != 0) {
				message = subscribeService.getMessage();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
		}
	}
	
	/**
	 * ��ѯϵͳʱ��
	 * @return ��ǰϵͳʱ��
	 */
	public java.util.Date querySystemTime() {
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return null;
		}
		try {
			SystemService systemService = new SystemService(server);
			java.util.Date t = systemService.getSystemTime();
			result = systemService.getResult();
			if (result != 0) {
				message = systemService.getMessage();
			}
			return t;
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
			return null;
		}
	}
	
	/**
	 * ����ղ�(�㶫����)
	 */
	public void addFavoritegd() {
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return ;
		}
		try {
			SystemService systemService = new SystemService(server);
			systemService.addFavoritegd(paramManager.hosturl, paramManager.accountId, paramManager.userId, paramManager.accountName, 
					paramManager.productId, paramManager.gameid, paramManager.spid, paramManager.code, paramManager.timeStmp);
			result = systemService.getResult();
			if (result != 0) {
				message = systemService.getMessage();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
		}
	}
	
	/**
	 * ���������ֵ����(������)
	 */
	public void gotoRechargePage() {
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return ;
		}
		try {
			SystemService systemService = new SystemService(server);
			systemService.gotoRechargePage(paramManager.buyURL, paramManager.userId);
			result = systemService.getResult();
			if (result != 0) {
				message = systemService.getMessage();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
		}
	}
	
	/**
	 *	����������(��������������) 
	 */
	public void sendHeartbeatPacket(){
		if (offline) {
			result = -1;
			message = OFFLINE_MSG;
			return ;
		}
		try {
			SystemService systemService = new SystemService(server);
			systemService.sendHeartbeatPacket(paramManager.buyURL, paramManager.userId, paramManager.appName);
			result = systemService.getResult();
			if (result != 0) {
				message = systemService.getMessage();
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			result = -1;
			message = e.getMessage();
		}
	}
	
	/**
	 * ��ȡ������
	 * @return 0�ɹ��� ������ʧ��
	 */
	public int getServiceResult() {
		return result;
	}
	
	/**
	 * ��ȡ���÷���֮�����Ϣ
	 * @return ��Ϣ����
	 */
	public String getServiceMessage() {
		return message;
	}
	
	/**
	 * �ж�ִ��ĳ�ν����Ƿ�ɹ�
	 * ÿ��ִ��һ����������Ľ�����Ҫ���ø÷����ж�
	 * �����Ƿ�ɹ��������getServiceResult�����Ƿ񷵻�0���ж�
	 * @return true, false
	 */
	public boolean isServiceSuccessful() {
		return result == 0;
	}

}
