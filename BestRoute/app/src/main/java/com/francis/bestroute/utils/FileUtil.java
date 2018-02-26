package com.francis.bestroute.utils;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * wa.android.common.utils.FileUtil
 * 
 * @author guowla create at 2014-8-8 下午1:55:54 读写文件的Util类
 */
public class FileUtil {
	/**
	 * FileUtil.java [V1.00] classpath:wa.android.common.utils MethodName :
	 * writeToFile Return : boolean guowla create at 2014-8-8下午1:56:16
	 * byte[]写入对应文件路径File中
	 */
	public static boolean writeToFile(byte[] data, File file) {
		boolean result = false;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream(file);
			fos.write(data, 0, data.length);
			result = true;
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("File does not exist");
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("IOException while writing File");
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (Exception e) {
				e.printStackTrace();
				System.out.println("Exception while closing flush");
			}
		}
		return result;
	}

	/**
	 * @Title: writeFile
	 * @Description: 写文件
	 * @param filePath
	 *            文件路径
	 * @param content
	 *            内容
	 * @param append
	 *            是否追加
	 * @return
	 * @return boolean
	 * @time 2015年6月24日 下午1:58:42
	 * @author zeven
	 */
	public static void writeFile(String filePath, String content, boolean append) {
		File file = new File(filePath);
		writeFile(file,content,append);
	}

	/**
	 * @Title: writeFile
	 * @Description: 写文件
	 * @param file
	 *            文件
	 * @param content
	 *            内容
	 * @param append
	 *            是否追加
	 * @return
	 * @return boolean
	 * @time 2015年6月24日 下午1:58:42
	 * @author zeven
	 */
	public static void writeFile(File file, String content, boolean append) {
		byte[] data = content.getBytes();

		FileOutputStream fos = null;
		try {
			if (!file.exists()) {
				makeDirs(file.getAbsolutePath());
				file.createNewFile();
			}
			fos = new FileOutputStream(file, append);
			fos.write(data);
			fos.flush();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (fos != null)
					fos.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * FileUtil.java [V1.00] classpath:wa.android.common.utils MethodName :
	 * writeToAppDataFile Return : File guowla create at 2014-8-8下午1:58:24
	 * File写入Data下
	 */
	public static File writeToAppDataFile(Context context, String filename, byte[] data) {
		File file = new File(context.getFilesDir(), filename);
		System.out.println(file.getAbsolutePath());
		return writeToFile(data, file) ? file : null;
	}

	/**
	 * FileUtil.java [V1.00] classpath:wa.android.common.utils MethodName :
	 * readFromFile Return : byte[] guowla create at 2014-8-8下午1:58:20 读取文件内容方法
	 */
	public static byte[] readFromFile(String filename) throws IOException {
		File file = new File(filename);
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[10240];
			while (true) {
				int len = fis.read(buffer);
				if (len == -1) {
					break;
				} else {
					baos.write(buffer, 0, len);
				}
			}
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception while reading File");
		}
		return baos.toByteArray();
	}
	
	/** 
	* @Title: readFromFile 
	* @Description: 读文件
	* @param file
	* @return
	* @throws IOException
	* @return byte[]
	* @time 2015年6月24日 下午2:33:20
	* @author zeven
	*/
	public static byte[] readFromFile(File file) throws IOException {
		if(!file.exists()){
			return null;
		}
		FileInputStream fis = new FileInputStream(file);
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			byte[] buffer = new byte[10240];
			while (true) {
				int len = fis.read(buffer);
				if (len == -1) {
					break;
				} else {
					baos.write(buffer, 0, len);
				}
			}
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception while reading File");
		}
		return baos.toByteArray();
	}

	public static byte[] readFromAssetsFile(Context context, String fileName){
		ByteArrayOutputStream baos = new ByteArrayOutputStream();
		InputStream inputStream = null;
		try {
			inputStream = context.getAssets().open(fileName);
			byte[] buffer = new byte[10240];
			while (true) {
				int len = inputStream.read(buffer);
				if (len == -1) {
					break;
				} else {
					baos.write(buffer, 0, len);
				}
			}
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	public static boolean makeDirs(String filePath) {
		String folderName = getFolderName(filePath);

		File folder = new File(folderName);
		return (folder.exists() && folder.isDirectory()) ? true : folder.mkdirs();
	}

	public static String getFolderName(String filePath) {

		int filePosi = filePath.lastIndexOf(File.separator);
		return (filePosi == -1) ? "" : filePath.substring(0, filePosi);
	}
}
