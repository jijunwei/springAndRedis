package com.example.utils.enordecryptutil;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * 使用SHA-1生成摘要工具类
 * @author Ryan
 *
 */

public class SignatureUtil {

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

	private static final String encryptionAlgorithm = "SHA-1";
	
	private static final String CHARSET = "utf-8";
	
	private String token;

	public String bytesToHexString(byte[] bytes) {
		char[] hexChars = new char[bytes.length * 2];
		for (int j = 0; j < bytes.length; j++) {
			int v = bytes[j] & 0xFF;
			hexChars[j * 2] = hexArray[v >>> 4];
			hexChars[j * 2 + 1] = hexArray[v & 0x0F];
		}
		return new String(hexChars);
	}

	public byte[] hexStringToBytes(String s) {
		int len = s.length();
		byte[] data = new byte[len / 2];
		for (int i = 0; i < len; i += 2) {
			data[i / 2] = (byte) ((Character.digit(s.charAt(i), 16) << 4) + Character
					.digit(s.charAt(i + 1), 16));
		}
		return data;
	}

	/**
	 * 使用指定算法生成消息摘要，默认是md5
	 * 
	 * @param strSrc
	 *            , a string will be encrypted; <br/>
	 * @param encName
	 *            , the algorithm name will be used, dafault to "MD5"; <br/>
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public String digest(String strSrc, String encName) throws UnsupportedEncodingException {
		MessageDigest md = null;
		String strDes = null;
		byte[] bt = strSrc.getBytes(CHARSET);
		try {
			if (encName == null || encName.equals("")) {
				encName = "MD5";
			}
			md = MessageDigest.getInstance(encName);
			md.update(bt);
			strDes = bytesToHexString(md.digest()); // to HexString
		} catch (NoSuchAlgorithmException e) {
			logger.debug("Invalid algorithm: " + encName);
			return null;
		}
		return strDes;
	}

	/**
	 * 根据md5Msg、token来生成签名
	 * @param md5Msg
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	public String generateSignature(String md5Msg) throws UnsupportedEncodingException {
		String signature = null;
		if (StringUtils.isNotBlank(md5Msg) && StringUtils.isNotBlank(token)){
			List<String> srcList = new ArrayList<String>();
			srcList.add(md5Msg);
			srcList.add(token);
			// 按照字典序逆序拼接参数
			Collections.sort(srcList);
			Collections.reverse(srcList);
			StringBuilder sb = new StringBuilder();
			for (int i = 0; i < srcList.size(); i++) {
				sb.append(srcList.get(i));
			}
			signature = digest(sb.toString(), encryptionAlgorithm);
			srcList.clear();
			srcList = null;
		}
		return signature;
	}

	/**
	 * 验证签名: <br/>
	 * 1.根据token、md5Msg计算一次签名;<br/>
	 * 2.比较传过来的签名以及计算出的签名是否一致;
	 * @param signature
	 * @param md5Msg
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	public boolean isValid(String signature,String md5Msg) throws UnsupportedEncodingException {
		String calculatedSignature = generateSignature(md5Msg);
		logger.debug("calculated signature: \n" + calculatedSignature);
		if (StringUtils.equals(calculatedSignature, signature)) {
			return true;
		} else {
			return false;
		}
	}



	/*public static void main(String[] args) throws UnsupportedEncodingException {

		SignatureUtil generator = new SignatureUtil();
		generator.setToken("F8FEA26B321153B634A893F801DA6CDF");
		String xmlString = "f7feb93307f8009e5a7958533935d44151ca0448710d7a02bd6290db746abae4965cfce77a1af44822b2229a5179decce159963e40696204afa62ca6e343207813088945ee77b64c47a2dd369d21fb46954c0f927e65e96e7740a3d6bc23bd6edbc032e0530e12d357466061a7331479811a62fc8f6fabe778936016b9736947058439a1dfefd3af0dabe075febe11b32ca97ae274b47d3fab971c57d903f4b344b2218d8d565317e6c9780e4f4600e3c78f7fc3e9b3dbf29306d5bbfc1ca1ecce5e54a835ed048939d51c90b8a5255deefa87ae16e3546e0b7ba7bb887faf1c7055c9cca8d22e448551a2395d70c23d09194e1889059201b40647bd6be5654eb83d751efa2f6568410a7877032e6c20a90b29684aa68239d34c678829c053ae07b4d597adbade4fbbae07ad0ec6094f039917358a831ba9289ed3e5a974d43deb1fca6f121f48849195e5760550d500b61c86424242175206eb8eefe019f4db0666b7902781631990c1395aaed82ce30f7118905f2a74391ec42a884b8be35a88331419506beb1748d5d83b821d641073d7366e3fb6f40d55a2929cf7e7c4d76b61657393bb71abf354f21b9ef223222e6ba94f944c890e994b1889168d4cb6587385b47840453d5e546444d484cbce13304426b0122975e484c895c90dfc847aa632648f3dd3b8ded4e87888f6df37ea48677802b31b6887bbc45754dafcadb4a2acad282b48e311e78a25cc0db7ca51dde2ee992680e7ec37c5ae91444ff870f114d23bc67d28c3408c5534b8286faf976faa48e170a855ca9535224a3603a17806e95e085f40f3629849319a439d751a06a988eb6385292fe5c36f6262787382b6a6b093712245a1171e3f2d39e0c9ca80bd43e8ab7291f64be12328dda3ea90574b1408f706ae3cdbc02a1453190a415c7fa09bb0e5c6eb8be35cbbce02375c931c8c22217f6d7c344f09aa479621b5a098b58dadc197e655d7d974f415";
		LOG.debug(xmlString.getBytes("utf-8").length);
		String digest = generator.digest(xmlString, "MD5");
		LOG.debug("*"+digest);
		LOG.debug(digest.getBytes().length);
	//	String token = generator.findTokenById(appid);
		String signature = generator.generateSignature(digest);
		LOG.debug(signature);
//		boolean isValid = generator.isValid(signature, appid, digest, millis);
		boolean isValid = generator.isValid(signature, digest);
		LOG.debug(isValid);
	}*/

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
}

