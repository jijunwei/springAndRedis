package com.example.utils;

import org.apache.commons.lang.StringUtils;
import org.slf4j.LoggerFactory;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class SignatureUtil {
    final static org.slf4j.Logger logger = LoggerFactory.getLogger(SignatureUtil.class);

    private static final char[] hexArray = "0123456789ABCDEF".toCharArray();

    private String encryptionAlgorithm = "SHA-1";

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
     */
    public String digest(String strSrc, String encName) {
        MessageDigest md = null;
        String strDes = null;
        byte[] bt = strSrc.getBytes();
        try {
            if (encName == null || encName.equals("")) {
                encName = "MD5";
            }
            md = MessageDigest.getInstance(encName);
            md.update(bt);
            strDes = bytesToHexString(md.digest()); // to HexString
        } catch (NoSuchAlgorithmException e) {
            logger.error("Invalid algorithm: " + encName);
            return null;
        }
        return strDes;
    }

    /**
     * 根据appid、token、lol以及时间戳来生成签名
     *
     * @param appid
     * @param token
     * @param lol
     * @param millis
     * @return
     */
    public String generateSignature(String appid, String token, String lol,
                                    long millis) {
        String timestamp = String.valueOf(millis);
        String signature = null;
        if (StringUtils.isNotBlank(token) && StringUtils.isNotBlank(timestamp)
                && StringUtils.isNotBlank(appid)) {
            List<String> srcList = new ArrayList<>();
            srcList.add(timestamp);
            srcList.add(appid);
            srcList.add(token);
            srcList.add(lol);
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
     * 1.根据appid获取该渠道的token;<br/>
     * 2.根据appid、token、lol以及时间戳计算一次签名;<br/>
     * 3.比较传过来的签名以及计算出的签名是否一致;
     * @param signature
     * @param appid
     * @param lol
     * @param millis
     * @return
     */
    public boolean isValid(String signature, String appid, String lol,
                           long millis) {
        String token = findTokenById(appid);
        String calculatedSignature = generateSignature(appid, token, lol,
                millis);
        logger.info("calculated signature: \n" + calculatedSignature);
        if (StringUtils.equals(calculatedSignature, signature)) {
            return true;
        } else {
            return false;
        }
    }

    /**
     * FIXME For demo only, should be a different string in production.
     * @param appid
     * @return
     */
    public String findTokenById(String appid) {
        String token = "#@!1234567890!@#";
        return token;
    }

    public static void main(String[] args) {
        SignatureUtil generator = new SignatureUtil();
        String xmlString = "<root><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name><name>test</name></root>";
        System.out.println(xmlString.getBytes().length);
        String digest = generator.digest(xmlString, "MD5");
        System.out.println(digest);
        System.out.println(digest.getBytes().length);
        String appid = "canairport001";
        String token = generator.findTokenById(appid);
        long millis = System.currentTimeMillis();
        String signature = generator.generateSignature(appid, token, digest,
                millis);
        System.out.println(signature);
        boolean isValid = generator.isValid(signature, appid, digest, millis);
        System.out.println(isValid);
    }
}
