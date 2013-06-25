package cn.ohyeah.itvgame.service;

import java.io.IOException;

import cn.ohyeah.itvgame.model.OwnProp;
import cn.ohyeah.itvgame.model.Prop;
import cn.ohyeah.itvgame.protocol.Constant;

/**
 * ���߷�����
 * @author maqian
 * @version 1.0
 */
public final class PropService extends AbstractHttpService{
	public PropService(String url) {
		super(url);
	}
	
	/**
	 * ��ѯ��Ϸ�����б�
	 * @param productId
	 * @return ���طǿգ����ѯ�ɹ�������ʧ��
	 * @throws ServiceException
	 */
	public Prop[] queryPropList(int productId) {
		try {
			Prop[] propList = null;
			initHead(Constant.PROTOCOL_TAG_PROP, Constant.PROP_CMD_QUERY_PROP_LIST);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeInt(productId);
			byte[] data = bufferBaos.toByteArray();
			closeBufferDataOutputStream();
			
			writeData(data);
			checkHead();
		    if (readResult() == 0) {
		    	int num = connectionDis.readShort();
		    	if (num > 0) {
		    		propList = new Prop[num];
			    	for (int i = 0; i < num; ++i) {
			    		propList[i] = new Prop();
			    		propList[i].readQueryResponseData(connectionDis);
			    	}
		    	}
		    }
		    return propList;
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		finally {
			close();
		}
	}
	
	/**
	 * ��ѯ���ӵ�еĵ����б�
	 * @param accountId
	 * @param productId
	 * @return ���طǿգ���ɹ�������ʧ��
	 * @throws ServiceException
	 */
	public OwnProp[] queryOwnPropList(int accountId, int productId) {
		try {
			OwnProp[] propList = null;
			initHead(Constant.PROTOCOL_TAG_PROP, Constant.PROP_CMD_QUERY_OWN_PROP_LIST);
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
		    		propList = new OwnProp[num];
			    	for (int i = 0; i < num; ++i) {
			    		propList[i] = new OwnProp();
			    		propList[i].readQueryResponseData(connectionDis);
			    	}
		    	}
		    }
		    return propList;
		} catch (IOException e) {
			throw new ServiceException(e.getMessage());
		}
		finally {
			close();
		}
	}
	
	/**
	 * ʹ�õ���
	 * @param accountId
	 * @param productId
	 * @param propIds
	 * @param nums
	 * @throws ServiceException
	 */
	public void useProps(int accountId, int productId, int[] propIds, int[] nums){
		if (propIds.length==0 || nums.length==0 || propIds.length!=nums.length) {
			throw new ServiceException("propIds����nums���ȴ���");
		}
		try {
			initHead(Constant.PROTOCOL_TAG_PROP, Constant.PROP_CMD_USE_PROPS);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeInt(accountId);
			bufferDos.writeInt(productId);
			bufferDos.writeShort(propIds.length);
			for (int i = 0; i < propIds.length; ++i) {
				bufferDos.writeInt(propIds[i]);
				bufferDos.writeInt(nums[i]);
			}
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
	 * ͬ������
	 * @param accountId
	 * @param productId
	 * @param propIds
	 * @param counts
	 * @throws ServiceException
	 */
	public void synProps(int accountId, int productId, int[] propIds, int[] counts) {
		if (propIds.length==0 || counts.length==0 || propIds.length!=counts.length) {
			throw new ServiceException("propIds����nums���ȴ���");
		}
		try {
			initHead(Constant.PROTOCOL_TAG_PROP, Constant.PROP_CMD_SYN_PROPS);
			openBufferDataOutputStream();
			bufferDos.writeInt(headWrapper.getHead());
			bufferDos.writeInt(accountId);
			bufferDos.writeInt(productId);
			bufferDos.writeShort(propIds.length);
			for (int i = 0; i < propIds.length; ++i) {
				bufferDos.writeInt(propIds[i]);
				bufferDos.writeInt(counts[i]);
			}
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
}
