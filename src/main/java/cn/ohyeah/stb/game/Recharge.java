package cn.ohyeah.stb.game;

/**
 * ��ֵ������
 * @author xiaochen
 *
 */
public class Recharge {
	
	IEngine engine;
	
	public Recharge(IEngine engine){
		this.engine = engine;
	}
	
	public int recharge(){
		System.out.println("telcom:"+Configurations.getInstance().getTelcomOperators());
		if(Configurations.getInstance().isTelcomOperatorsTianweiSZ()){
			return new StateRechargeWinsideTW(engine).recharge();    //����������
		}else if(Configurations.getInstance().isTelcomOperatorsTelcomhn()
				&& Configurations.getInstance().isServiceProviderWinside()){
			return new StateRechargehn(engine).recharge();			 //���������
		}else if(Configurations.getInstance().isTelcomOperatorsTelcomhn()
				&& Configurations.getInstance().isServiceProviderShengYi()){
			return new StateRechargeShengyihn(engine).recharge();    //ʢ�����
		}else{
			return new StateRecharge(engine).recharge();
		}
	}

}
