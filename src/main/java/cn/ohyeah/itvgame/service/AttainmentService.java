package cn.ohyeah.itvgame.service;

import java.io.IOException;

import cn.ohyeah.itvgame.model.GameAttainment;
import cn.ohyeah.itvgame.model.GameAttainmentDesc;
import cn.ohyeah.itvgame.model.GameRanking;
import cn.ohyeah.itvgame.protocol.Constant;

/**
 * ��Ϸ�ɾͣ����У�������
 * @author maqian
 * @version 1.0
 */
public final class AttainmentService extends AbstractHttpService{
	public AttainmentService(String url) {
		super(url);
	}
	
	/**
	 * ������Ϸ�ɾ�
	 * @param accountId
	 * @param productId
	 * @param attainment
	 * @throws ServiceException
	 */
	public void save(int accountId, String userId, int productId, GameAttainment attainment) {
		try {
			initHead(Constant.PROTOCOL_TAG_ATTAINMENT, Constant.ATTAINMENT_CMD_SAVE);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeInt(accountId);
			bufferDos.writeUTF(userId);
			bufferDos.writeInt(productId);
			attainment.writeSaveRequestData(bufferDos);
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
	 * ��ȡ��Ϸ�ɾ�
	 * @param accountId
	 * @param productId
	 * @param attainmentId
	 * @return ����ֵ�ǿգ���ɹ�������null��ʧ��
	 * @throws ServiceException
	 */
	public GameAttainment read(int accountId, int productId, int attainmentId, String orderCmd, 
			java.util.Date start, java.util.Date end) {
		try {
			GameAttainment attainment = null;
			initHead(Constant.PROTOCOL_TAG_ATTAINMENT, Constant.ATTAINMENT_CMD_READ);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeInt(accountId);
			bufferDos.writeInt(productId);
			bufferDos.writeInt(attainmentId);
			if (orderCmd != null) {
				bufferDos.writeUTF(orderCmd);
			}
			else {
				bufferDos.writeUTF("");
			}
			if (start != null && end != null) {
				bufferDos.writeLong(start.getTime());
				bufferDos.writeLong(end.getTime());
			}
			else {
				bufferDos.writeLong(0);
				bufferDos.writeLong(0);
			}
			byte[] data = bufferBaos.toByteArray();
			closeBufferDataOutputStream();
			
			writeData(data);
			checkHead();
		    if (readResult() == 0 && connectionDis.available() > 0) {
		    	attainment = new GameAttainment();
		    	attainment.readReadResponseData(connectionDis);
		    }
		    return attainment;
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		finally {
			close();
		}
	}
	
	/**
	 * ������Ϸ�ɾ�
	 * @param accountId
	 * @param userId
	 * @param productId
	 * @param attainment
	 * @throws ServiceException
	 */
	public void update(int accountId, String userId, int productId, GameAttainment attainment) {
		try {
			initHead(Constant.PROTOCOL_TAG_ATTAINMENT, Constant.ATTAINMENT_CMD_UPDATE);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeInt(accountId);
			bufferDos.writeUTF(userId);
			bufferDos.writeInt(productId);
			attainment.writeUpdateRequestData(bufferDos);
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
     * <pre>
     * ��ѯ��������б�
     * ����ѯ������ǰ��1~10����¼,offset=0, limit=10
     * ����ѯ������ǰ��11-20����¼��offset=10�� limit=10
     * </pre>
     *
     * @param productId
     * @param orderCmd
     * @param start
     * @param end
     * @param offset
     * @param length
     * @return GameRanking
     */
	public GameRanking[] queryRankingList(int productId, String orderCmd, 
			java.util.Date start, java.util.Date end, int offset, int length) {
		try {
			GameRanking[] rankingList = null;
			initHead(Constant.PROTOCOL_TAG_ATTAINMENT, Constant.ATTAINMENT_CMD_QUERY_RANKING_LIST);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeInt(productId);
			if (orderCmd != null) {
				bufferDos.writeUTF(orderCmd);
			}
			else {
				bufferDos.writeUTF("");
			}
			if (start != null && end != null) {
				bufferDos.writeLong(start.getTime());
				bufferDos.writeLong(end.getTime());
			}
			else {
				bufferDos.writeLong(0);
				bufferDos.writeLong(0);
			}
			bufferDos.writeInt(offset);
			bufferDos.writeInt(length);
			byte[] data = bufferBaos.toByteArray();
			closeBufferDataOutputStream();
			
			writeData(data);
			checkHead();
		    if (readResult() == 0) {
		    	int num = connectionDis.readShort();
		    	if (num > 0) {
		    		rankingList = new GameRanking[num];
			    	for (int i = 0; i < num; ++i) {
			    		rankingList[i] = new GameRanking();
			    		rankingList[i].readQueryResponseData(connectionDis);
			    	}
		    	}
		    }
		    return rankingList;
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		finally {
			close();
		}
	}
	
	/**
	 * ��ѯ�ɾ������б�
	 * @param accountId
	 * @param productId
	 * @return GameAttainmentDesc
	 * @throws ServiceException
	 */
	public GameAttainmentDesc[] queryDescList(int accountId, int productId, String orderCmd, 
			java.util.Date start, java.util.Date end) {
		try {
			GameAttainmentDesc[] descList = null;
			initHead(Constant.PROTOCOL_TAG_ATTAINMENT, Constant.ATTAINMENT_CMD_QUERY_DESC_LIST);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeInt(accountId);
			bufferDos.writeInt(productId);
			if (orderCmd != null) {
				bufferDos.writeUTF(orderCmd);
			}
			else {
				bufferDos.writeUTF("");
			}
			if (start != null && end != null) {
				bufferDos.writeLong(start.getTime());
				bufferDos.writeLong(end.getTime());
			}
			else {
				bufferDos.writeLong(0);
				bufferDos.writeLong(0);
			}
			byte[] data = bufferBaos.toByteArray();
			closeBufferDataOutputStream();
			
			writeData(data);
			checkHead();
		    if (readResult() == 0) {
		    	int num = connectionDis.readShort();
		    	if (num > 0) {
		    		descList = new GameAttainmentDesc[num];
			    	for (int i = 0; i < num; ++i) {
			    		descList[i] = new GameAttainmentDesc();
			    		descList[i].readQueryResponseData(connectionDis);
			    	}
		    	}
		    }
		    return descList;
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		finally {
			close();
		}
	}
}
