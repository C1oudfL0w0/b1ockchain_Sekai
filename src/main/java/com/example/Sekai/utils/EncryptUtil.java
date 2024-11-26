package com.example.Sekai.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.security.MessageDigest;


public class EncryptUtil {
	private static Logger logger = Logger.getLogger(EncryptUtil.class);

	/**
	 * 使用sha-1计算摘要
	 * @param content
	 * @return 40位密文
	 */
	public static String encryptSHA1(String content){
		try {
			if(StringUtils.isEmpty(content)){
				logger.error("加密明文不能为空");
			}
			MessageDigest md = MessageDigest.getInstance("SHA-1");
			byte[] result = md.digest(content.getBytes("UTF-8"));
			return ConvertUtil.byteToHexString(result);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}
	/**
	 * 使用sha-256计算摘要
	 * @param content
	 * @return 64位密文
	 */
	public static String encryptSHA256(String content){
		try {
			if(StringUtils.isEmpty(content)){
				logger.error("加密明文不能为空");
			}
			MessageDigest md = MessageDigest.getInstance("SHA-256");
			byte[] result = md.digest(content.getBytes("UTF-8"));
			return ConvertUtil.byteToHexString(result);
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		return null;
	}

}
