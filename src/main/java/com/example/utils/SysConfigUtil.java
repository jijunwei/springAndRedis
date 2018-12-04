package com.example.utils;

import java.io.*;
import java.util.Properties;

public class SysConfigUtil {
    private static String envPath;
    public SysConfigUtil() {
        String osType = System.getProperties().getProperty("os.name").toLowerCase();
        if (osType.indexOf("linux") > -1) {
            envPath = "opt" + File.separator + "sysparams" + File.separator + "slms" + File.separator + "sysParamas.properties";
        } else if (osType.indexOf("windows") > -1) {
            envPath = "C:" + File.separator + "sysparams" + File.separator + "slms" + File.separator + "sysParamas.properties";
        } else if (osType.indexOf("mac") > -1) {
            envPath = "opt" + File.separator + "sysparams" + File.separator + "slms" + File.separator + "sysParamas.properties";
        }else {
            System.out.println("您的操作系统不再支持范围，请联系开发人员");
        }
    }

    /**
     * 根据key返回值
     *
     * @param key
     * @return
     */
    public static String readProperty(String key) {
        String value = "";
        InputStream inputStream = null;
        Properties p = new Properties();
        try {
            FileInputStream in = new FileInputStream(envPath);
            p.load(in);
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
    public static Properties getProperties() {
        Properties p = new Properties();
        InputStream is = null;
        try {
            FileInputStream in = new FileInputStream(envPath);
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
    public static void writeProperty(String key, String value) {
        InputStream is = null;
        OutputStream os = null;
        Properties p = new Properties();
        try {
            is = new FileInputStream(envPath);
            p.load(is);
            os = new FileOutputStream(envPath);
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
