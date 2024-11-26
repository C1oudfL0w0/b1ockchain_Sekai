package com.example.Sekai.utils;
/**
 * 
 * @author wujw
 *
 */
public class ConvertUtil {
	/**
	 * 字节数组转16进制
	 * @param bytes
	 * @return
	 */
	public static String byteToHexString(byte[] bytes){
		StringBuilder sb = new StringBuilder();
		for(byte b : bytes){
			String hs = Integer.toHexString(b & 0xFF);
			if(hs.length() == 1){
				sb.append("0");
			}
			sb.append(hs);
		}
		return sb.toString();
	}

	private static byte charToByte(char c){
		return (byte) "0123456789abcdef".indexOf(c);
	}
}
