package com.test.filelibrary;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MyClass {
    public static String KEY = "istest";

    public static void main(String[] args) {
        try {
            splitFile();
//            getAllFileFromPath("D:\\bCoreApk\\chunks");

//            encryptFile(new File("D:\\bCoreApk.so"), new File("E:\\" + getRandomString(16) + "k.gif"), KEY.getBytes());
//            encryptFile(new File("D:\\bCoreApk.so"), new File("E:\\" + getRandomString(16) + "m.gif"), KEY.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void splitFile() throws IOException {

        File sourceFile = new File("D:\\coreApk\\release.apk");
        String chunkFileFolder = "D:\\coreApk\\chunks";
        int chunkFileSize = 2 * 2048 * 2048;
        int chunkFileNum = (int) Math.ceil(sourceFile.length() * 1.0 / chunkFileSize);
        RandomAccessFile readFile = new RandomAccessFile(sourceFile, "r");
        byte[] bytes = new byte[2048];
        for (int i = 0; i < chunkFileNum; i++) {
            File chunkFile = new File(chunkFileFolder + "\\" + "part_" + i);
            int len = -1;
            RandomAccessFile writeFile = new RandomAccessFile(chunkFile, "rw");
            while ((len = readFile.read(bytes)) != -1) {
                writeFile.write(bytes, 0, len);
                if (chunkFile.length() >= chunkFileSize) {
                    break;
                }
            }
            writeFile.close();
        }
        readFile.close();
    }


    //
    public static List<File> getAllFileFromPath(String path) throws Exception{
        ArrayList<File> files = new ArrayList<>();

        File file = new File(path);
        File[] listFiles = file.listFiles();
        if (listFiles != null && listFiles.length > 0) {
            for (File f : listFiles) {
                if (f.isFile()) {
                    System.out.println("file-> " + f);
                    String outFilePath = "D:\\bCoreApk\\chunks\\encry";
                    encryptFile(f,new File(outFilePath),KEY.getBytes());
                    files.add(f);
                }
            }
        }
        return files;
    }


    public static String getRandomString(int length) {
        String str = "abcdefghijklmnopqrstuvwxyz";
        Random random = new Random();
        StringBuffer sb = new StringBuffer();
        for (int i = 0; i < length; i++) {
            int number = random.nextInt(26);
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    public static byte[] encrypt(byte[] data, byte[] key) {
        if (data == null || data.length == 0 || key == null || key.length == 0) {
            return data;
        }
        byte[] result = new byte[data.length];
        //
        for (int i = 0; i < data.length; i++) {
            //
            result[i] = (byte) (data[i] ^ key[i % key.length] ^ (i & 0xFF));
        }
        return result;
    }

    public static void encryptFile(File inFile, File outFile,byte[] key) throws Exception {
        String inFilePath ="D:\\bCoreApk\\chunks";
        String outFilePath = "D:\\bCoreApk\\chunks\\encry";
        InputStream in = null;
        OutputStream out = null;
        try {
            //
            in = new FileInputStream(inFile);
            //
            //
            out = new BufferedOutputStream(new FileOutputStream(outFile), 10240);

            int b = -1;
            long i = 0;

            //
            while ((b = in.read()) != -1) {
                //
                b = (b ^ key[(int) (i % key.length)] ^ (int) (i & 0xFF));
                //
                out.write(b);
                //
                i++;
            }
            out.flush();

        } finally {
            in.close();
            out.close();
        }
    }

}