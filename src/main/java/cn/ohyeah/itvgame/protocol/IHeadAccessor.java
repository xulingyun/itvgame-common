package cn.ohyeah.itvgame.protocol;

/**
 * Э��ͷ������
 * @author maqian
 * @version 1.0
 */
public interface IHeadAccessor {

	/**
	 * Э��ͷ(���ݳ���32λ)
	 */
	public abstract int getHead();
	public abstract void setHead(int head);
	
	/**
	 * Э��汾��
	 */
	public abstract int getVersion();
	public abstract void setVersion(int version);
	
	/**
	 * ��ԭ���ݻ��߼���ǰ�����ݽ�����䣨AES������16�ֽڶ��룩��
	 * 0:�����
	 * 1:16�ֽ����
	 */
	public abstract int getPadding();
	public abstract void setPadding(int padding);
	
	/**
	 * ���������ݱ������ص������Ƿ񾭹��ֿ鷢�͡�
	 * 0:�����ݱ���δ���ֿ�
	 * 1:�ֿ飬��һ�����ݱ�
	 * 2:�ֿ飬�������ݱ�
	 * 3:δ����
	 */
	public abstract int getSplit();
	public abstract void setSplit(int split);
	
	/**
	 * ��ʶ�����ݱ��Ƿ񾭹����ܡ����ܷ�����ϵͳ����ָ�������ݱ���ʹ�õļ��ܷ�����Э�鱾��ȷ����
	 * 0:�Ǽ���
	 * 1:����
	 */
	public abstract int getCrypt();
	public abstract void setCrypt(int crypt);
	
	/**
	 * ��ʾ�����ݱ��Ƿ񾭹�ѹ��
	 * 0:��ʾδѹ��
	 * 1:��ʾѹ��
	 */
	public abstract int getCompress();
	public abstract void setCompress(int compress);
	
	/**
	 * 1��ʾ���յ�������ʱӦ�÷���Ӧ��(UDP��Ч)
	 */
	public abstract int getAck();
	public abstract void setAck(int ack);
	
	/**
	 * 1��ʾЭ��Ҫ�󷵻ز�����ΪӦ���
	 */
	public abstract int getAckparam();
	public abstract void setAckparam(int ackParam);
	
	/**
	 * ������Ự����ʱ��ʾ�Ự����
	 * ������������Ϊý������ʱ��ʾ����֡����
	 */
	public abstract int getType();
	public abstract void setType(int type);
	
	/**
	 * ����Э���ʶ
	 */
	public abstract int getTag();
	public abstract void setTag(int tag);
	
	/**
	 * ����Э������
	 */
	public abstract int getCommand();
	public abstract void setCommand(int command);
	
	
	/**
	 * �û����ݣ�8λ��
	 */
	public abstract int getUserdata();
	public abstract void setUserdata(int userData);

	

	

	

	

	

	

	

	
	
	

	

}