package cn.ohyeah.itvgame.service;

import java.io.IOException;

import javax.microedition.io.HttpConnection;

import cn.ohyeah.itvgame.model.PurchaseRecord;
import cn.ohyeah.itvgame.model.SubscribePayType;
import cn.ohyeah.itvgame.protocol.Constant;

/**
 * ��Ϸ�Ʒѷ�����
 * @author maqian
 * @version 1.0
 */
public final class PurchaseService extends AbstractHttpService{
	public PurchaseService(String url) {
		super(url);
	}
	
	/**
	 * ������ߣ���Ҫ����Ԫ������ң�
	 * @param buyURL
	 * @param accountId
	 * @param accountName
	 * @param userToken
	 * @param productId
	 * @param propId
	 * @param propCount
	 * @param remark
	 * @return ���طǸ���ɹ�������ʧ��
	 * @throws ServiceException
	 */
	public int purchaseProp(String buyURL, int accountId, String accountName, String userToken, 
			int productId, int propId, int propCount, String remark, String checkKey) {
		try {
			int balance = -1;
			initHead(Constant.PROTOCOL_TAG_PURCHASE, Constant.PURCHASE_CMD_PURCHASE_PROP);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeUTF(buyURL);
			bufferDos.writeInt(accountId);
			bufferDos.writeUTF(accountName);
			bufferDos.writeUTF(userToken);
			bufferDos.writeInt(productId);
			bufferDos.writeInt(propId);
			bufferDos.writeInt(propCount);
			bufferDos.writeUTF(remark);
			bufferDos.writeUTF(checkKey);
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
	 * ���ѽ��,amount,���ѽ���λԪ������ң�
	 * @param buyURL
	 * @param accountId
	 * @param accountName
	 * @param userToken
	 * @param productId
	 * @param amount
	 * @param remark
	 * @return ���طǸ����ɹ�������ʧ��
	 * @throws ServiceException 
	 */
	public int expend(String buyURL, int accountId, String accountName, String userToken, 
			int productId, int amount, String remark, String checkKey) {
		try {
			int balance = -1;
			initHead(Constant.PROTOCOL_TAG_PURCHASE, Constant.PURCHASE_CMD_EXPEND);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeUTF(buyURL);
			bufferDos.writeInt(accountId);
			bufferDos.writeUTF(accountName);
			bufferDos.writeUTF(userToken);
			bufferDos.writeInt(productId);
			bufferDos.writeInt(amount);
			bufferDos.writeUTF(remark);
			bufferDos.writeUTF(checkKey);
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
	
	/**/
	public int expendWinsideLack(String buyURL, int accountId, String accountName, String userToken, 
			int productId, int propId, int amount, String remark, String checkKey) {
		try {
			int balance = -1;
			initHead(Constant.PROTOCOL_TAG_PURCHASE, Constant.PURCHASE_CMD_EXPEND_WINSIDE_LACK);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeUTF(buyURL);
			bufferDos.writeInt(accountId);
			bufferDos.writeUTF(accountName);
			bufferDos.writeUTF(userToken);
			bufferDos.writeInt(productId);
			bufferDos.writeInt(propId);
			bufferDos.writeInt(amount);
			bufferDos.writeInt(SubscribePayType.PAY_TYPE_BILL);
			bufferDos.writeUTF(remark);
			bufferDos.writeUTF(checkKey);
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
	
	public int expendDijoy(String buyURL, int accountId, String accountName, String userToken, 
			int productId, int amount,int propId, String remark, String appId, String checkKey,
			String platformExt) {
		return expendDijoy(buyURL, accountId, accountName, userToken, productId, amount,  propId,
				SubscribePayType.PAY_TYPE_BILL, remark, appId, checkKey, platformExt);
	}
	
	public int expendDijoy(String buyURL, int accountId, String accountName, String userToken, 
			int productId, int amount, int propId, int payType, String remark, String appId, String checkKey,
			String platformExt) {
		try {
			int balance = -1;
			initHead(Constant.PROTOCOL_TAG_PURCHASE, Constant.PURCHASE_CMD_EXPEND_DIJOY);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeUTF(buyURL);
			bufferDos.writeInt(accountId);
			bufferDos.writeUTF(accountName);
			bufferDos.writeUTF(userToken);
			bufferDos.writeInt(productId);
			bufferDos.writeInt(amount);
			bufferDos.writeInt(propId);
			bufferDos.writeInt(payType);
			bufferDos.writeUTF(remark);
			bufferDos.writeUTF(appId);
			bufferDos.writeUTF(checkKey);
			bufferDos.writeUTF(platformExt);
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
	 * �Ϻ����Ź������
	 */
	public int expendTelcomsh(String buyURL,String useId,String userToken,String accountName,int accountId,
			int productId, int propId, String remark, String gameid) {
		try {
			int balance = -1;
			initHead(Constant.PROTOCOL_TAG_PURCHASE, Constant.PURCHASE_CMD_EXPEND_TELCOMSH);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeUTF(buyURL);
			bufferDos.writeInt(accountId);
			bufferDos.writeUTF(accountName);
			bufferDos.writeUTF(userToken);
			bufferDos.writeInt(productId);
			bufferDos.writeInt(propId);
			bufferDos.writeInt(SubscribePayType.PAY_TYPE_BILL);
			bufferDos.writeUTF(remark);
			bufferDos.writeUTF(gameid);
			byte[] data = bufferBaos.toByteArray();
			closeBufferDataOutputStream();
			
			writeData(data);
			checkHead();
			if (readResult() == 0) {
				balance = connectionDis.readInt();
				return balance;
			}
			return 0;
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		finally {
			close();
		}
	}
	
	public int expendTelcomsh_(String buyURL,String useId,
			int productId, int propId, String remark, String gameid) {
		try {
			int balance = -1;
			initHead(Constant.PROTOCOL_TAG_PURCHASE, Constant.PURCHASE_CMD_EXPEND_TELCOMSH);
			openBufferDataOutputStream();
			
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeUTF(buyURL);
			bufferDos.writeInt(productId);
			bufferDos.writeInt(propId);
			bufferDos.writeInt(SubscribePayType.PAY_TYPE_BILL);
			bufferDos.writeUTF(remark);
			bufferDos.writeUTF(gameid);
			byte[] data = bufferBaos.toByteArray();
			closeBufferDataOutputStream();
			
			writeData(data);
			int rc = httpConnection.getResponseCode();
			if (rc != HttpConnection.HTTP_OK) {
		        throw new ServiceException("Http Response Code: " + rc);
		    }
		    openConnectionDataInputStream();
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
	 * ��ѯ���Ѽ�¼
	 * ����ѯ�����1~10����¼,offset=0, length=10
	 * ����ѯ�����11-20����¼��offset=10, length=10
	 * </pre>
	 * @param accountId
	 * @param productId
	 * @param offset
	 * @param length
	 * @return ���طǿգ���ɹ�������ʧ��
	 * @throws ServiceException
	 */
	public PurchaseRecord[] queryPurchasePropRecord(int accountId, int productId, int offset, int length) {
		PurchaseRecord[] purchaseList = null;
		try {
			initHead(Constant.PROTOCOL_TAG_PURCHASE, Constant.PURCHASE_CMD_QUERY_PURCHASE_RECORD);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeInt(accountId);
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
		    		purchaseList = new PurchaseRecord[num];
			    	for (int i = 0; i < num; ++i) {
			    		purchaseList[i] = new PurchaseRecord();
			    		purchaseList[i].readQueryResponseData(connectionDis);
			    	}
		    	}
		    }
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		finally {
			close();
		}
		return purchaseList;
	}
}
