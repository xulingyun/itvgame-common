package cn.ohyeah.itvgame.service;

import java.io.IOException;

import cn.ohyeah.itvgame.model.GameRecord;
import cn.ohyeah.itvgame.model.GameRecordDesc;
import cn.ohyeah.itvgame.protocol.Constant;

/**
 * ��Ϸ��¼������
 * @author maqian
 * @version 1.0
 */
public final class RecordService extends AbstractHttpService{
	public RecordService(String url) {
		super(url);
		System.out.println("url:"+url);
	}
	
	/**
	 * ������Ϸ��¼
	 * @param accountId
	 * @param productId
	 * @param record
	 * @throws ServiceException
	 */
	public void save(int accountId, int productId, GameRecord record) {
		try {
			initHead(Constant.PROTOCOL_TAG_RECORD, Constant.RECORD_CMD_SAVE);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeInt(accountId);
			bufferDos.writeInt(productId);
			record.writeSaveRequestData(bufferDos);
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
	 * ������Ϸ��¼
	 * @param accountId
	 * @param productId
	 * @param record
	 * @throws ServiceException 
	 */
	public void update(int accountId, int productId, GameRecord record) {
		try {
			initHead(Constant.PROTOCOL_TAG_RECORD, Constant.RECORD_CMD_UPDATE);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeInt(accountId);
			bufferDos.writeInt(productId);
			record.writeUpdateRequestData(bufferDos);
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
	 * ��ȡ��Ϸ��¼
	 * @param accountId
	 * @param productId
	 * @param recordId
	 * @return ���طǿգ���ɹ�������ʧ��
	 * @throws ServiceException 
	 */
	public GameRecord read(int accountId, int productId, int recordId) {
		try {
			GameRecord record = null;
			initHead(Constant.PROTOCOL_TAG_RECORD, Constant.RECORD_CMD_READ);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeInt(accountId);
			bufferDos.writeInt(productId);
			bufferDos.writeInt(recordId);
			byte[] data = bufferBaos.toByteArray();
			closeBufferDataOutputStream();
			
			writeData(data);
			checkHead();
		    if (readResult() == 0 && connectionDis.available() > 0) {
		    	record = new GameRecord();
		    	record.readReadResponseData(connectionDis);
		    }
		    return record;
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		finally {
			close();
		}
	}
	
	/**
	 * ��ȡ��Ϸ��¼�����б�
	 * @param accountId
	 * @param productId
	 * @return ���طǿգ���ɹ�������ʧ��
	 * @throws ServiceException 
	 */
	public GameRecordDesc[] queryDescList(int accountId, int productId) {
		try {
			GameRecordDesc[] descList = null;
			initHead(Constant.PROTOCOL_TAG_RECORD, Constant.RECORD_CMD_QUERY_DESC_LIST);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeInt(accountId);
			bufferDos.writeInt(productId);
			byte[] data = bufferBaos.toByteArray();
			closeBufferDataOutputStream();
			
			writeData(data);
			checkHead();
		    if (readResult() == 0) {
		    	int num = connectionDis.readShort();
		    	if (num > 0) {
			    	descList = new GameRecordDesc[num];
			    	for (int i = 0; i < num; ++i) {
			    		descList[i] = new GameRecordDesc();
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
