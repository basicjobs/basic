package www.com.util;

import java.io.File;

/**
 * File Util class
 * 
 * @author cmj
 *
 */
public class FileUtil {
	
	private FileUtil(){}
	
	/**
	 * 파일명의 확장자를 가져온다.
	 * 
	 * @param file
	 * @return 파일명의 확장자
	 */
	public static String getFileExtention(File file){
		String fileName = file.getName();
		String fileExt = "";
		if(fileName.indexOf(".") != -1){
			fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).trim();
		}
		
		return fileExt;
	}
	
	/**
	 * 파일의 경로에서 상대 경로 표시된 부분을 삭제한다.
	 *
	 * @param value - 치환하고자 하는 파일 경로 문자열
	 * @return 파일 경로에서 상대경로(../../ 또는 ..\..)를 제거한다.
	 */
	public static String filePathBlackList(String value) {
		
		String returnValue = value;
		
		if ((returnValue == null) || returnValue.trim().equals("")) {
			return "";
		}

		returnValue = returnValue.replaceAll("\\.\\./", "");	// ../	제거
		returnValue = returnValue.replaceAll("\\.\\.\\\\", "");	// ..\	제거

		return returnValue;
	}
}
