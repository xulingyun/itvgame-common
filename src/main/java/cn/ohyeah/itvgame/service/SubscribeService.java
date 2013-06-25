package cn.ohyeah.itvgame.service;

import java.io.IOException;

import cn.ohyeah.itvgame.model.SubscribePayType;
import cn.ohyeah.itvgame.model.SubscribeRecord;
import cn.ohyeah.itvgame.protocol.Constant;

/**
 * ����������
 * @author maqian
 * @version 1.0
 */
public final class SubscribeService extends AbstractHttpService{
	public SubscribeService(String url) {
		super(url);
	}
	
	/**
	 * ��Ʒ����
	 * @param buyURL
	 * @param accountId
	 * @param accountName
	 * @param userToken
	 * @param productId
	 * @param purchaseId
	 * @throws ServiceException
	 */
	public void subscribe(String buyURL, int accountId, String accountName, String userToken, 
			int productId, int purchaseId, String remark, String checkKey) {
		subscribe(buyURL, accountId, accountName, userToken, productId, purchaseId, SubscribePayType.PAY_TYPE_BILL, remark, checkKey);
	}
	
	public void subscribe(String buyURL, int accountId, String accountName, String userToken, 
			int productId, int purchaseId, int payType, String remark, String checkKey) {
		try {
			initHead(Constant.PROTOCOL_TAG_SUBSCRIBE, Constant.SUBSCRIBE_CMD_SUBSCRIBE);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeUTF(buyURL);
			bufferDos.writeInt(accountId);
			bufferDos.writeUTF(accountName);
			bufferDos.writeUTF(userToken);
			bufferDos.writeInt(productId);
			bufferDos.writeInt(purchaseId);
			bufferDos.writeInt(payType);
			bufferDos.writeUTF(remark);
			bufferDos.writeUTF(checkKey);
			byte[] data = bufferBaos.toByteArray();
			closeBufferDataOutputStream();
			
			writeData(data);
			checkHead();
			readResult();
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		finally {
			close();
		}
	}
	
	/**
	 * ��Ϸ�ڶ�����Ʒ
	 * @param buyURL
	 * @param accountId
	 * @param accountName
	 * @param userToken
	 * @param productId
	 * @param subscribeType
	 * @param remark
	 * @throws ServiceException
	 */
	public void subscribeProduct(String buyURL, int accountId, String accountName, String userToken, 
			int productId, String subscribeType, String remark, String checkKey) {
		subscribeProduct(buyURL, accountId, accountName, userToken, productId, subscribeType, SubscribePayType.PAY_TYPE_BILL, remark, checkKey);
	}
	
	public void subscribeProduct(String buyURL, int accountId, String accountName, String userToken, 
			int productId, String subscribeType, int payType, String remark, String checkKey) {
		try {
			initHead(Constant.PROTOCOL_TAG_SUBSCRIBE, Constant.SUBSCRIBE_CMD_SUBSCRIBE_PRODUCT);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeUTF(buyURL);
			bufferDos.writeInt(accountId);
			bufferDos.writeUTF(accountName);
			bufferDos.writeUTF(userToken);
			bufferDos.writeInt(productId);
			bufferDos.writeUTF(subscribeType);
			bufferDos.writeInt(payType);
			bufferDos.writeUTF(remark);
			bufferDos.writeUTF(checkKey);
			byte[] data = bufferBaos.toByteArray();
			closeBufferDataOutputStream();
			
			writeData(data);
			checkHead();
			readResult();
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		finally {
			close();
		}
	}
	
	public void subscribeProductShengyi(int accountId, String accountName, String userToken, 
			int productId, String subscribeType, String remark, String shengyiCPID,
			String shengyiCPPassWord, String shengyiUserIdType, String shengyiProductId) {
		subscribeProductShengyi(accountId, accountName, userToken, productId, subscribeType, SubscribePayType.PAY_TYPE_BILL,
				remark, shengyiCPID, shengyiCPPassWord, shengyiUserIdType, shengyiProductId);
	}
	
	public void subscribeProductShengyi(int accountId, String accountName, String userToken,	
			int productId, String subscribeType, byte payTypeBill, String remark, String shengyiCPID, 
			String shengyiCPPassWord, String shengyiUserIdType, String shengyiProductId){
		try {
			initHead(Constant.PROTOCOL_TAG_SUBSCRIBE, Constant.SUBSCRIBE_CMD_SUBSCRIBE_PRODUCT_SHENGYI);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeInt(accountId);
			bufferDos.writeUTF(accountName);
			bufferDos.writeUTF(userToken);
			bufferDos.writeInt(productId);
			bufferDos.writeUTF(subscribeType);
			bufferDos.writeInt(payTypeBill);
			bufferDos.writeUTF(remark);
			bufferDos.writeUTF(shengyiCPID);
			bufferDos.writeUTF(shengyiCPPassWord);
			bufferDos.writeUTF(shengyiUserIdType);
			bufferDos.writeUTF(shengyiProductId);
			byte[] data = bufferBaos.toByteArray();
			closeBufferDataOutputStream();
			
			writeData(data);
			checkHead();
			readResult();
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		finally {
			close();
		}
	}

	/**
	 * ��Ϸ���ֵ
	 * rate��ʾ��ֵ�һ���������1RMB=10Ԫ����rateӦ��Ϊ10��
	 * ע�⣺rate<=0����ʾ�����������õ�Ĭ��rateֵ
	 * @param buyURL
	 * @param accountId
	 * @param accountName
	 * @param userToken
	 * @param productId
	 * @param amount
	 * @param ratio
	 * @param remark
	 * @return ���س�ֵ������
	 * @throws ServiceException
	 */
	public int recharge(String buyURL, int accountId, String accountName, String userToken, 
			int productId, int amount, int ratio, String remark, String checkKey, String spid, String password) {
		return recharge(buyURL, accountId, accountName, userToken, productId, amount, ratio, 
				SubscribePayType.PAY_TYPE_BILL, remark, checkKey, spid, password);
	}
	
	public int recharge(String buyURL, int accountId, String accountName, String userToken, 
			int productId, int amount, int ratio, int payType, String remark, String checkKey, String spid, String password) {
		try {
			int balance = -1;
			initHead(Constant.PROTOCOL_TAG_SUBSCRIBE, Constant.SUBSCRIBE_CMD_RECHARGE);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeUTF(buyURL);
			bufferDos.writeInt(accountId);
			bufferDos.writeUTF(accountName);
			bufferDos.writeUTF(userToken);
			bufferDos.writeInt(productId);
			bufferDos.writeInt(amount);
			bufferDos.writeInt(ratio);
			bufferDos.writeInt(payType);
			bufferDos.writeUTF(remark);
			bufferDos.writeUTF(checkKey);
			bufferDos.writeUTF(spid);
			if (password == null) {
				bufferDos.writeUTF("");
			}
			else {
				bufferDos.writeUTF(password);
			}
			byte[] data = bufferBaos.toByteArray();
			closeBufferDataOutputStream();
			
			writeData(data);
			checkHead();
			if (readResult() == 0) {
				balance = connectionDis.readInt();
			}
			return balance;
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		finally {
			close();
		}
	}
	
	/**
	 * winsidegd��ֵ�ӿ�
	 * @param buyURL
	 * @param accountId
	 * @param accountName
	 * @param userToken
	 * @param productId
	 * @param amount
	 * @param ratio
	 * @param remark
	 * @param checkKey
	 * @param spid
	 * @param gameid
	 * @param enterURL
	 * @param stbType
	 * @return ���س�ֵ������
	 */
	public int rechargeWinsidegd(String buyURL, int accountId, String accountName, String userToken, 
			int productId, int amount, int ratio, String remark, String checkKey, String spid,
			String gameid, String enterURL, String stbType, String password) {
		return rechargeWinsidegd(buyURL, accountId, accountName, userToken, productId, amount, ratio, 
				SubscribePayType.PAY_TYPE_BILL, remark, checkKey, spid, gameid, enterURL, stbType, password);
	}
	
	public int rechargeWinsidegd(String buyURL, int accountId, String accountName, String userToken, 
			int productId, int amount, int ratio, int payType, String remark, String checkKey, String spid,
			String gameid, String enterURL, String stbType, String password) {
		try {
			int balance = -1;
			initHead(Constant.PROTOCOL_TAG_SUBSCRIBE, Constant.SUBSCRIBE_CMD_RECHARGE_WINSIDEGD);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeUTF(buyURL);
			bufferDos.writeInt(accountId);
			bufferDos.writeUTF(accountName);
			bufferDos.writeUTF(userToken);
			bufferDos.writeInt(productId);
			bufferDos.writeInt(amount);
			bufferDos.writeInt(ratio);
			bufferDos.writeInt(payType);
			bufferDos.writeUTF(remark);
			bufferDos.writeUTF(checkKey);
			bufferDos.writeUTF(spid);
			bufferDos.writeUTF(gameid);
			bufferDos.writeUTF(enterURL);
			bufferDos.writeUTF(stbType);
			if (password == null) {
				bufferDos.writeUTF("");
			}
			else {
				bufferDos.writeUTF(password);
			}
			byte[] data = bufferBaos.toByteArray();
			closeBufferDataOutputStream();
			
			writeData(data);
			checkHead();
			if (readResult() == 0) {
				balance = connectionDis.readInt();
			}
			return balance;
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		finally {
			close();
		}
	}
	
	public int rechargeShiXian(String buyURL, int accountId, String accountName, String userToken, 
			int productId, int amount, int ratio,  String remark, String checkKey, String feeaccount,
			String returnurl, String dwjvl, String opcomkey, String paysubway, String gameid, String user_group_id,
			String password, String appId){
		return rechargeShiXian(buyURL, accountId, accountName, userToken, productId, amount, ratio, 
				SubscribePayType.PAY_TYPE_BILL, remark, checkKey, feeaccount, returnurl, dwjvl, opcomkey,
				paysubway, gameid, user_group_id, password, appId);
	}
	
	public int rechargeShiXian(String buyURL, int accountId, String accountName, String userToken, 
			int productId, int amount, int ratio, int payType, String remark, String checkKey, String feeaccount,
			String returnurl, String dwjvl, String opcomkey, String paysubway, String gameid, String user_group_id,
			String password, String appId){
		try {
			int balance = -1;
			initHead(Constant.PROTOCOL_TAG_SUBSCRIBE, Constant.SUBSCRIBE_CMD_RECHARGE_SHIXIAN);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeUTF(buyURL);
			bufferDos.writeInt(accountId);
			bufferDos.writeUTF(accountName);
			bufferDos.writeUTF(userToken);
			bufferDos.writeInt(productId);
			bufferDos.writeInt(amount);
			bufferDos.writeInt(ratio);
			bufferDos.writeInt(payType);
			bufferDos.writeUTF(remark);
			bufferDos.writeUTF(checkKey);
			bufferDos.writeUTF(feeaccount);
			bufferDos.writeUTF(returnurl);
			bufferDos.writeUTF(dwjvl);
			bufferDos.writeUTF(opcomkey);
			bufferDos.writeUTF(paysubway);
			bufferDos.writeUTF(gameid);
			bufferDos.writeUTF(user_group_id);
			bufferDos.writeUTF(appId);
			if (password == null) {
				bufferDos.writeUTF("");
			}
			else {
				bufferDos.writeUTF(password);
			}
			byte[] data = bufferBaos.toByteArray();
			closeBufferDataOutputStream();
			
			writeData(data);
			checkHead();
			if (readResult() == 0) {
				balance = connectionDis.readInt();
			}
			return balance;
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		finally {
			close();
		}
	}
	
	public int rechargeShengYi(String buyURL, int accountId, String accountName, String userToken, 
			int productId, int amount, int ratio, String remark, String checkKey, String shengyiCPID,
			String shengyiCPPassWord, String shengyiUserIdType, String shengyiProductId, String password){
		return rechargeShengYi(buyURL, accountId, accountName, userToken, productId, amount, ratio,
				SubscribePayType.PAY_TYPE_BILL, remark, checkKey, shengyiCPID, shengyiCPPassWord, shengyiUserIdType,
				shengyiProductId,password);
	}
	
	private int rechargeShengYi(String buyURL, int accountId, String accountName, String userToken,	int productId, 
			int amount,	int ratio, byte payTypeBill, String remark, String checkKey,String shengyiCPID, 
			String shengyiCPPassWord, String shengyiUserIdType, String shengyiProductId, String password) {

		try {
			int balance = -1;
			initHead(Constant.PROTOCOL_TAG_SUBSCRIBE, Constant.SUBSCRIBE_CMD_RECHARGE_SHENGYI);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeUTF(buyURL);
			bufferDos.writeInt(accountId);
			bufferDos.writeUTF(accountName);
			bufferDos.writeUTF(userToken);
			bufferDos.writeInt(productId);
			bufferDos.writeInt(amount);
			bufferDos.writeInt(ratio);
			bufferDos.writeInt(payTypeBill);
			bufferDos.writeUTF(remark);
			bufferDos.writeUTF(checkKey);
			bufferDos.writeUTF(shengyiCPID);
			bufferDos.writeUTF(shengyiCPPassWord);
			bufferDos.writeUTF(shengyiUserIdType);
			bufferDos.writeUTF(shengyiProductId);
			bufferDos.writeUTF(password);
			System.out.println("password="+password);
			byte[] data = bufferBaos.toByteArray();
			closeBufferDataOutputStream();
			
			writeData(data);
			checkHead();
			if (readResult() == 0) {
				balance = connectionDis.readInt();
			}
			return balance;
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		finally {
			close();
		}
	
	}

	/**
	 * <pre>
	 * ��ѯ��ֵ��¼
	 * ����ѯ�����1~10����¼,offset=0, length=10
	 * ����ѯ�����11-20����¼��offset=10�� length=10
	 * </pre>
	 * @param userId
	 * @param productId
	 * @param offset
	 * @param length
	 * @return ���طǿգ��ɹ�������ʧ��
	 * @throws ServiceException 
	 */
	public SubscribeRecord[] querySubscribeRecord(String userId, int productId, int offset, int length) {
		try {
			SubscribeRecord[] srList = null;
			initHead(Constant.PROTOCOL_TAG_SUBSCRIBE, Constant.SUBSCRIBE_CMD_QUERY_SUBSCRIBE_RECORD);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeUTF(userId);
			bufferDos.writeInt(productId);
			bufferDos.writeInt(offset);
			bufferDos.writeInt(length);
			byte[] data = bufferBaos.toByteArray();
			closeBufferDataOutputStream();
			
			writeData(data);
			checkHead();
		    if (readResult() == 0) {
		    	int num = connectionDis.readShort();
		    	if (num > 0) {
		    		srList = new SubscribeRecord[num];
			    	for (int i = 0; i < num; ++i) {
			    		srList[i] = new SubscribeRecord();
			    		srList[i].readQueryResponseData(connectionDis);
			    	}
		    	}
		    }
		    return srList;
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		finally {
			close();
		}
	}
	
	
	/**
	 * ��ѯ�˻����
	 * @param buyURL
	 * @param accountId
	 * @param accountName
	 * @param productId
	 * @return ���ɹ�����������ʧ�ܣ�������
	 * @throws ServiceException
	 */
	public int queryBalance(String buyURL, int accountId, String accountName, int productId) {
		try {
			int balance = -10000;
			initHead(Constant.PROTOCOL_TAG_SUBSCRIBE, Constant.SUBSCRIBE_CMD_QUERY_BALANCE);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeUTF(buyURL);
			bufferDos.writeInt(accountId);
			bufferDos.writeUTF(accountName);
			bufferDos.writeInt(productId);
			byte[] data = bufferBaos.toByteArray();
			closeBufferDataOutputStream();
			
			writeData(data);
			checkHead();
		    if (readResult() == 0) {
		    	balance = connectionDis.readInt();
		    }
		    return balance;
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		finally {
			close();
		}
	}
}
