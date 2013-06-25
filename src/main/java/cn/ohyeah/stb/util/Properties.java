package cn.ohyeah.stb.util;

import java.util.Enumeration;
import java.util.Hashtable;

import cn.ohyeah.stb.res.ResourceManager;

/**
 * �����࣬���������ֵ�������ļ�<br/>
 * ��ֵ����ʽ��key=value
 * @author maqian
 * @version 1.0
 */
public class Properties {
	private Hashtable props = new Hashtable();
	public Properties() {
	}
	
	/**
	 * ��ȡָ����key��ֵ
	 * @param key
	 */
	public String get(String key) {
		return (String)props.get(key);
	}
	
	/**
	 * �Ƴ�ָ����key��ֵ
	 * @param key
	 */
	public String remove(String key) {
		return (String)props.remove(key);
	}
	
	/**
	 * ���(key,value)��ֵ��
	 * @param key
	 * @param value
	 */
	public void put(String key, String value) {
		props.put(key, value);
	}
	
	/**
	 * �ж��Ƿ������key
	 * @param key
	 */
	public boolean containsKey(String key) {
		return props.containsKey(key);
	}

	/**
	 * ����property�ļ�
	 * @param filePath
	 * @param charset
	 */
	public void parseFile(String filePath, String charset) {
		parseData(ResourceManager.loadString(filePath, charset));
	}
	
	/**
	 * ����property�ַ���
	 * @param data
	 */
	public void parseData(String data) {
		int scanPos = 0;
		int searchPos = 0;
		int dataLen = data.length();
		String key = null;
		String value = null;
		while (scanPos < dataLen) {
			while (data.charAt(scanPos) == '#') {
				searchPos = data.indexOf('\n');
				if (searchPos >= 0) {
					scanPos = searchPos+1;
				}
				else {
					break;
				}
			}
			searchPos = data.indexOf('=', scanPos);
			key = data.substring(scanPos, searchPos).trim();
			scanPos = searchPos+1;
			searchPos = data.indexOf('\n', scanPos);
			if (searchPos > 0) {
				if (data.charAt(searchPos-1) == '\r') {
					value = data.substring(scanPos, searchPos-1).trim();
				}
				else {
					value = data.substring(scanPos, searchPos).trim();
				}
				scanPos = searchPos+1;
			}
			else {
				value = data.substring(scanPos).trim();
				scanPos = dataLen;
			}
			if (key!=null && !"".equals(key)) {
				props.put(key, value);
			}
		}
	}
	
	/**
	 * ��properties�ļ�ֵ�Դ�ӡ����׼�����
	 */
	public void print() {
		Enumeration e = props.keys();
		while (e.hasMoreElements()) {
			String key = (String)e.nextElement();
			System.out.println(key+" ==> "+props.get(key));
		}
	}
}
