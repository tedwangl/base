package com.wl.mylibrary.util.File;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * IO工具类
 * Created by sks on 2016/4/9.
 */
public class IOUtils {

    /**
     * 将输入流解析为String
     * @param is
     * @return
     * @throws Exception
     */
    public static String inputStreamToString(InputStream is){
        StringBuilder sb = new StringBuilder();
        String line = null;
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(is,"UTF-8"));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
    } catch (IOException e) {
            e.printStackTrace();
        } finally {
            close(reader);
        }
        return sb.toString();
    }

    /**
     * 将String转为输入流
     * @param str
     * @return
     */
    public static InputStream stringToInputStream(String str) {
        return new ByteArrayInputStream(str.getBytes());
    }

    /**
     * 获取文件字节数组
     * @param fileName
     * @return
     * @throws Exception
     */
    public static byte[] getFileBytes(String fileName) {
        File file = new File(fileName);
        InputStream is = null;
        ByteArrayOutputStream bos = null;
        if( file.exists() ){
            try {
                //输入流转为byte数组
                is = new FileInputStream(file);
                byte[] buffer = new byte[1024];
                int len = -1;
                bos = new ByteArrayOutputStream();
                while ((len = is.read(buffer)) != -1) {
                    bos.write(buffer, 0, len);
                }
                return bos.toByteArray();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } finally{
                close(bos);
                close(is);
            }
            return null;
        }
        return null;
    }

    /**
     * 读取文本文件
     * @param file
     * @return
     * @throws IOException
     */
    public static String readTextFile(File file) {
        try {
            InputStream is = new FileInputStream(file);
            return inputStreamToString(is);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 将文本内容写入文件
     * @param file
     * @param str
     * @throws IOException
     */
    public static void writeTextFile(File file, String str) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(file);
            writer.write(str);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(writer);
        }
    }

    /**
     * 关闭IO输入流
     * @param closeable
     */
    public static void close(Closeable closeable) {
        if (closeable != null) {
            try {
                closeable.close();
            } catch (IOException e) {
                throw new RuntimeException("IOException occurred", e);
            }
        }
    }

    /**
     * md5加密 不知道什么意思啊
     * @param url
     * @return
     */
    public String getMd5(String url){
        StringBuffer sb = new StringBuffer();
        try {
            MessageDigest digest = MessageDigest.getInstance("md5");
            digest.update(url.getBytes());
            byte[] bytes = digest.digest();
            for(byte b:bytes){
                String x = Integer.toHexString(b&0xff);//把字节转换成String类型转换成16进制
                sb.append(x.length()==1?"0"+x:x);
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return sb.toString();

    }

}
