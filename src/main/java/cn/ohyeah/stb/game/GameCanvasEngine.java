package cn.ohyeah.stb.game;

import javax.microedition.lcdui.Font;
import javax.microedition.lcdui.Image;
import javax.microedition.lcdui.game.GameCanvas;
import javax.microedition.midlet.MIDlet;

import cn.ohyeah.stb.res.ResourceManager;
import cn.ohyeah.stb.res.UIResource;
import cn.ohyeah.stb.ui.TextView;
import cn.ohyeah.stb.key.KeyCode;
import cn.ohyeah.stb.key.KeyState;

/**
 * ʹ��GameCanvas����Ϸ����
 * ��Ϸ��̳и��࣬�����װ��jad�в�����ȡ���Լ�conf�ļ��Ķ�ȡ
 * ��igbt���߱������Ϸ������һ��game.conf�ļ������໹��װ��<link>ServiceWrapper</link>
 * �����Լ��û�����֮���<link>LoginInfo</link>����Ϣ������Ҫ���Ǹ����loop������
 * �̳и�����������дloop������ʵ����Ϸ�߼�
 * @author maqian
 * @version 1.0
 */
abstract public class GameCanvasEngine extends GameCanvas implements Runnable, IEngine{

	private static final byte STATE_USER_LOOP = 0;
	private static final byte STATE_DEBUG = 1;
	private static final byte STATE_LOADING = 2;
	private static final byte STATE_ERROR = 3;
	private static final byte STATE_LOAD_CONF = 4;
	private static final byte STATE_LOAD_PARAM = 5;
	private static final byte STATE_PLAY_LOGO = 6;
	private static final byte STATE_USER_LOGIN = 7;
	
	private static final String IMG_LOGO_CHINAGAMES = "/common/chinagames.jpg";
	private static final String IMG_LOADING = "/common/loading.png";
	
	private static boolean __RELEASE = true;
	private static final int __INIT_LOOP_CIRCLE = 175;
	
	private int state;
	private int subState;
	private byte[] stateStack;
	private int stateStackPointer;
	protected MIDlet midlet;
	protected KeyState keyState;
	protected SGraphics g;
	protected int screenWidth;
	protected int screenHeight;
	protected int loopCircle;
	protected boolean exit;
	protected long appStartTimeMillis;
	protected EngineService engineService;
	protected int smallFontSize, mediumFontSize, largeFontSize;
	protected DebugModule debugModule;
	private long recordMillis;
	protected int loadingProgress;
	protected String loadingMessage;
	private Image logoPic;
	private int loadinglevel;
	private String errorMessage;
	private OnlineThread onlineThread;
	
	private long recordTime;
	private boolean timePass(int millisSeconds) {
		long curTime = System.currentTimeMillis();
		if (recordTime <= 0) {
			recordTime = curTime;
		}
		else {
			if (curTime-recordTime >= millisSeconds) {
				recordTime = 0;
				return true;
			}
		}
		return false;
	}
	
	protected GameCanvasEngine(MIDlet midlet) {
		super(false);
		this.midlet = midlet;
		loopCircle = __INIT_LOOP_CIRCLE;
		appStartTimeMillis = System.currentTimeMillis();
		setFullScreenMode(true);
		keyState = new KeyState();
		g = new SGraphics(super.getGraphics(),Configurations.Abs_Coords_X,Configurations.Abs_Coords_Y);
		initFontSize();
		setDefaultFont();
		screenWidth = getWidth();
		screenHeight = getHeight();
		
		engineService = new EngineService(this);
		stateStack = new byte[16];
		debugModule = new DebugModule(this);
		UIResource.registerEngine(this);
		onlineThread = new OnlineThread(this);
	}
	
	public void pushState(byte state) {
		stateStack[stateStackPointer++] = state;
	}
	
	public int popState() {
		return stateStack[--stateStackPointer];
	}
	
	public boolean isDebugMode() {
		return !__RELEASE&&debugModule.isDebugMode();
	}
	
	public boolean isReleaseVersion() {
		return __RELEASE;
	}
	
	protected void setRelease(boolean b) {
		__RELEASE = b;
	}
	
	public boolean isRunning() {
		return !exit;
	}
	
	public void setExit() {
		exit = true;
	}
	
	public void setLoopCircle(int circle) {
		loopCircle = circle;
	}
	
	public int getLoopCircle() {
		return loopCircle;
	}
	
	private void initFontSize() {
		Font font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_SMALL);
		smallFontSize = font.getHeight();
		font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
		mediumFontSize = font.getHeight();
		font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_LARGE);
		largeFontSize = font.getHeight();
	}
	
	public void setFont(int size, boolean isBold) {
		Font font = null;
		if(!isBold){
			if (size <= smallFontSize) {
				font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_SMALL);
			}
			else if (size <= mediumFontSize) {
				if (size >= smallFontSize+((mediumFontSize-smallFontSize)>>1)) {
					font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
				}
				else {
					font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_SMALL);
				}
			}
			else if (size <= largeFontSize) {
				if (size >= mediumFontSize+((largeFontSize-mediumFontSize)>>1)) {
					font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_LARGE);
				}
				else {
					font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_MEDIUM);
				}
			}
			else {
				font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_PLAIN, Font.SIZE_LARGE);
			}
		}else{
			if (size <= smallFontSize) {
				font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_SMALL);
			}
			else if (size <= mediumFontSize) {
				if (size >= smallFontSize+((mediumFontSize-smallFontSize)>>1)) {
					font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
				}
				else {
					font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_SMALL);
				}
			}
			else if (size <= largeFontSize) {
				if (size >= mediumFontSize+((largeFontSize-mediumFontSize)>>1)) {
					font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE);
				}
				else {
					font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_MEDIUM);
				}
			}
			else {
				font = Font.getFont(Font.FACE_MONOSPACE, Font.STYLE_BOLD, Font.SIZE_LARGE);
			}
		}
		g.setFont(font);
	}

	public void setDefaultFont() {
		setFont(20,false);
	}
	
	public int getScreenWidth() {
		return screenWidth;
	}

	public int getScreenHeight() {
		return screenHeight;
	}

	public final void flushGraphics() {
		if (isDebugMode()) {
			debugModule.showDebugInfo(g);
		}
		super.flushGraphics();
	}
	
	public SGraphics getSGraphics() {
		return g;
	}

	public KeyState getKeyState() {
		return keyState;
	}
	
	public Font getFont() {
		return g.getFont();
	}
	
	public void setFont(Font font) {
		g.setFont(font);
	}
	
	public final void keyPressed(int keyCode) {
		keyState.keyPressed(keyCode);
		if (!__RELEASE) {
			debugModule.checkDebugCmd(keyCode, keyState.getKeyChar());
		}
	}
	
	public final void keyReleased(int keyCode) {
		keyState.keyReleased(keyCode);
	}

	public final void run() {
		state = STATE_LOAD_CONF;
		runLoop();
		midlet.notifyDestroyed();
	}
	
	private void runLoop() {
		recordMillis = System.currentTimeMillis();
		while (isRunning()) {
			try {
				switch (state) {
				case STATE_USER_LOOP: 
					loop();
					break;
				case STATE_LOADING:
					//loading();
					break;
				case STATE_LOAD_CONF:
					loadConf();
					break;
				case STATE_LOAD_PARAM:
					loadParam();
					break;
				case STATE_PLAY_LOGO:
					playLogo();
					break;
				case STATE_USER_LOGIN:
					userLogin();
					break;
				case STATE_DEBUG:
					debug();
					break;
				case STATE_ERROR:
					error();
					break;
				default:
					throw new RuntimeException("��Ч��״̬����: state="+state);
				}
				flushGraphics();
				logicAfterShow();
				trySleep();
			}
			catch (Throwable t) {
				t.printStackTrace();
				System.out.println("\n�������쳣�����¼�쳣����ʱ�������ģ����������쳣��Ϣ�ύ������Ա����, Thanks!");
				System.gc();
				errorMessage = "�������쳣��"+t.getMessage()
							+"\n������ɵĲ������Ǹ�⣬���ǻᾡ���޸������⣬��л���Ĺ�ע!\n�밴������˳�!";
				state = STATE_ERROR;
			}
		}
	}
	
	private void showLoading(String path, int bgColor) {
		g.setColor(bgColor);
		g.fillRect(-Configurations.Abs_Coords_X, -Configurations.Abs_Coords_Y, screenWidth, screenHeight);
		if(Configurations.getInstance().isTelcomOperatorsTelcomgd()){
			if (logoPic == null) {
				logoPic = ResourceManager.loadImage(path);
			}
			g.drawImage(logoPic, 0, 0, 20);
		}else{
			if (logoPic == null) {
				logoPic = ResourceManager.loadImage(IMG_LOADING);
			}
			int x = screenWidth/2 - logoPic.getWidth()/2;
			int y = 290;
	    	g.drawImage(logoPic, x, y, 20);
	    	
	    	g.setColor(0x00BDCD);
	    	g.fillRect(screenWidth/2 - 220, 380, loadinglevel, 26);
	    	g.setColor(0x00FFFF);
	    	g.drawRect(screenWidth/2 - 220, 380, 440, 26);
			if(loadinglevel<440){
	    		loadinglevel+=10;
	    	}else{
	    		state = STATE_USER_LOOP;
	    		clearLoadingRes();
	    		g.setColor(0x000000);
	    		g.fillRect(-Configurations.Abs_Coords_X, -Configurations.Abs_Coords_Y, screenWidth, screenHeight);
	    	}
		}
	}
	
	private void userLogin() {
		if (subState == 0) {
			recordTime = System.currentTimeMillis();
            subState = 3;
		}else if (subState == 1) {
			if (keyState.containsAndRemove(KeyCode.OK)) {
				subState = 0;
				errorMessage = "";
			}
			else {
				if (keyState.containsAnyKey()) {
					setExit();
				}
			}
		}else if (subState == 2) {
			if (timePass(3000)) {
				if(Configurations.getInstance().isTelcomOperatorsTelcomgd()){
					state = STATE_USER_LOOP;
					clearLoadingRes();
				}else{
					state = STATE_PLAY_LOGO;
				}
				
				if(Configurations.getInstance().isTelcomOperatorsTianweiSZ()){
					onlineThread.t1 = System.currentTimeMillis()/1000;
					new Thread(onlineThread).start();
				}
			}
		}else if (subState == 3) {
            if (engineService.userLogin()) {
                subState = 2;
            }
            else {
                errorMessage = "�û���¼ʧ�ܡ�"+"\n";
                errorMessage += "ԭ�� "+engineService.getLoginMessage()+"\n\n";
                errorMessage += "#R�밴#Yȷ��\\OK��#R���ԣ��밴#Y������#R�˳�";
                showError();
                subState = 1;
            }
        }
	}
	
	private void playLogo() {
		if (Configurations.getInstance().isTelcomOperatorsTelcomgd()) {
			showLoading(IMG_LOGO_CHINAGAMES, 0XC7A774);
			if (timePass(3000)) {
				state = STATE_USER_LOGIN;
				logoPic = null;
			}
		}else {
			showLoading(IMG_LOADING, 0x004455);
			if(loadinglevel == 220){  //��������һ��ʱ���е��룬������ɺ��������
				state = STATE_USER_LOGIN;
			}
		}
	}

	private void loadParam() {
		ParamManager pm = getParamManager();
		if (pm.parse()) {
			state = STATE_PLAY_LOGO;
		}
		else {
			errorMessage = "У�����ʧ�ܡ�"+"\n";
			errorMessage += pm.getErrorMessage()+"\n";
			errorMessage += "#R�밴#Y�����#R�˳���";
			state = STATE_ERROR;
		}
	}

	private void loadConf() {
		Configurations.loadConfigurations();
		if (Configurations.isLoadConfSuccess()) {
			state = STATE_LOAD_PARAM;
		}
		else {
			errorMessage = "��ȡ����ʧ�ܡ�"+"\n";
			errorMessage += "ԭ��: "+Configurations.getErrorMessage()+"\n";
			errorMessage += "#R�밴#Y�����#R�˳���";
			state = STATE_ERROR;
		}
	}

	
	private void clearLoadingRes() {
		logoPic = null;
	}
	
	private void error() {
		if (keyState.containsAnyKey()) {
			setExit();
		}
		showError();
	}
	
	protected void showError() {
		g.setColor(0);
		g.fillRect(-Configurations.Abs_Coords_X, -Configurations.Abs_Coords_Y, screenWidth, screenHeight);
		g.setColor(0XFFFFFF);
		TextView.showMultiLineText(g, errorMessage, 1, 10, 20, screenWidth, screenHeight);
	}
	
	private void debug() {
		// TODO Auto-generated method stub
		throw new RuntimeException("�ݲ�֧��");
	}
	
	public void addDebugUserMessage(String msg) {
		if (isDebugMode()) {
			debugModule.addDebugUserMessage(msg);
		}
	}

	protected void loop() {
		String msg = "����дloop������ʵ����Ϸѭ��";
		System.out.println(msg);
		int sx = (screenWidth-g.getFont().stringWidth(msg))/2;
		int sy = (screenHeight-g.getFont().getHeight())/2;
		g.setClip(0, 0, screenWidth, screenHeight);
		g.setColor(0);
		g.fillRect(-Configurations.Abs_Coords_X, -Configurations.Abs_Coords_Y, screenWidth, screenHeight);
		g.setColor(-1);
		g.drawString(msg, sx, sy, 0);
	}
	
	protected void logicAfterShow() {}
	

	public String getAppProperty(String key) {
		return midlet.getAppProperty(key);
	}

	public EngineService getEngineService() {
		return engineService;
	}

	public ParamManager getParamManager() {
		return engineService.getParamManager();
	}

	public ServiceWrapper getServiceWrapper() {
		return engineService.getServiceWrapper();
	}
	
	public ServiceWrapper getServiceWrapper(String server) {
		return engineService.getServiceWrapper(server);
	}
	
	public boolean isOffline() {
		return engineService.isOffline();
	}

	public long getAppStartMillis() {
		return appStartTimeMillis;
	}

	public void quitCurrentState() {
		state = popState();
	}

	public void trySleep() {
		this.trySleep(loopCircle);
	}
	
	public void trySleep(int milliseconds) {
		long now = System.currentTimeMillis();
		int sleepTime = (int)(milliseconds-(now-recordMillis));
		recordMillis = now;
		if (sleepTime < 0) {
			sleepTime = 0;
		}
		else if (sleepTime > milliseconds){
			sleepTime = milliseconds;
		}
		try {
			System.gc();
			Thread.sleep(sleepTime);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}
