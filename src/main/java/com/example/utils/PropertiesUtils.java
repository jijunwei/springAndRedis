package com.example.utils;

import java.io.*;
import java.util.Properties;

/**
 * properties工具类
 *

 */
public class PropertiesUtils {
    private String properiesName;

    public PropertiesUtils() {

    }

    /**
     * 构造方法，读取指定文件
     * @param fileName
     */
    public PropertiesUtils(String fileName) {
        this.properiesName = fileName;
    }

    /**
     * 根据key返回值
     *
     * @param key
     * @return
     */
    public String readProperty(String key) {
        String value = "";
        InputStream inputStream = null;
        Properties p = new Properties();
        try {
            inputStream = PropertiesUtils.class.getClassLoader().getResourceAsStream(properiesName);
            BufferedReader bf = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
            p.load(bf);
            value = p.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (inputStream != null) {
                    inputStream.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return value;
    }

    /**
     * 返回Properties对象
     *
     * @return
     */
    public Properties getProperties() {
        Properties p = new Properties();
        InputStream is = null;
        try {
            is = PropertiesUtils.class.getClassLoader().getResourceAsStream(properiesName);
            p.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return p;
    }

    /**
     * 写Properties
     *
     * @param key
     * @param value
     * @return
     */
    public void writeProperty(String key, String value) {
        InputStream is = null;
        OutputStream os = null;
        Properties p = new Properties();
        try {
            is = new FileInputStream(properiesName);
            p.load(is);
            os = new FileOutputStream(PropertiesUtils.class.getClassLoader().getResource(properiesName).getFile());

            p.setProperty(key, value);
            p.store(os, key);
            os.flush();
            os.close();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != is)
                    is.close();
                if (null != os)
                    os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
