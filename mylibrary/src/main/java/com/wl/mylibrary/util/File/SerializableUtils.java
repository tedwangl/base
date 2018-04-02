package com.wl.mylibrary.util.File;

import com.wl.mylibrary.application.CoreApplication;
import com.wl.mylibrary.util.MD5Utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 序列化工具类
 * Created by sks on 2016/4/9.
 */
public class SerializableUtils {

    /**
     * 序列化数据
     * @param fileName
     * @param t
     */
    public static <T> void setSerializable(String fileName,T t) {
        ObjectOutputStream oos = null;
        try {
            File file = new File(CoreApplication.FILE_DIR +
                    MD5Utils.md5(fileName));
            String a = file.getAbsolutePath();
            String b = file.getPath();
            FileOutputStream fos = new FileOutputStream(new File(CoreApplication.FILE_DIR +
                MD5Utils.md5(fileName)));

            oos = new ObjectOutputStream(fos);

            oos.writeObject(t);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally{
            IOUtils.close(oos);
        }
    }

    /**
     * 反序列化数据
     * @param fileName 缓存文件名
     * @param <T>
     * @return
     */
    public static <T> T getSerializable(String fileName) {
        ObjectInputStream oos = null;
        try {
            File file = new File(CoreApplication.FILE_DIR + MD5Utils.md5(fileName));
            if (!file.exists()){
                file.createNewFile();
            }

            if (file.length() == 0){
                return null;
            }

            FileInputStream fis = new FileInputStream(file);
            oos = new ObjectInputStream(fis);
            return (T)oos.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(oos);
        }
        return null;
    }

    /**
     * 反序列化数据
     * @param fileName 缓存文件名
     * @param time 缓存文件有效时间
     * @param <T>
     * @return
     */
    public static <T> T getSerializable(String fileName,long time) {
        ObjectInputStream oos = null;
        try {
            File file = new File(CoreApplication.FILE_DIR + MD5Utils.md5(fileName));
            if (!file.exists()){
                file.createNewFile();
            }

            if (file.length() == 0){
                return null;
            }

            long lastTime = file.lastModified();
            long nowTime=System.currentTimeMillis();
            if(nowTime-lastTime>time){
                return null;
            }

            FileInputStream fis = new FileInputStream(file);
            oos = new ObjectInputStream(fis);
            return (T)oos.readObject();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            IOUtils.close(oos);
        }
        return null;
    }

    /**
     * 删除序列化数据
     * @param fileName
     */
    public static boolean deleteSerializable(String fileName){
        File file = new File(CoreApplication.FILE_DIR + MD5Utils.md5(fileName));
        if (file.exists()){
            return file.delete();
        }
        return false;
    }

}
