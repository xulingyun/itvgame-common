package cn.ohyeah.stb.game;

import cn.ohyeah.stb.game.ServiceWrapper;

/**
 * �������������������(���������������иù���)
 * @author xiaochen
 *
 */
public class OnlineThread implements Runnable {

	private IEngine engine;
	public long t1, t2;
	private int period=600;	//ÿ��600�뷢��һ��������
	public OnlineThread(IEngine engine){
		this.engine = engine;
	}
	
	public void run() {
		while(true){
			t2 = System.currentTimeMillis()/1000;
			if((t2-t1)>period){
				System.out.println("�����������������");
				t1 = System.currentTimeMillis()/1000;
				ServiceWrapper sw = engine.getServiceWrapper();
				sw.sendHeartbeatPacket();
			}
		}
	}

}
