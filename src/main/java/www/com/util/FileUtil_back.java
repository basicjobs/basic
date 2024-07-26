package www.com.util;

import java.io.File;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.StringTokenizer;
/**
 * File Util class
 * 
 * @author cmj
 *
 */
public class FileUtil {
	
	//private FileUtil(){}
	
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
	
    public static boolean fileExists(String fileName) throws ClassNotFoundException, IOException {
        File file = new File(fileName);
        return file.isFile();
    }

    /** 파일 사지으 */
    
    public static long getFileSize(String fileName) throws ClassNotFoundException, IOException {
        long fileLength = 0;
        File file = new File(fileName);

        if(file.exists()) fileLength = file.length();
        else fileLength = 0;

        return fileLength;
    }
    
    public static boolean writeText(String filePath,String txt){
        FileWriter fw = null;

        try {
          //  mkdir(filePath); 
            fw = new FileWriter( filePath);
            fw.write(txt);
            fw.flush();
            fw.close();
        } catch(Exception e) {
            return false;
        } finally {
            try {
                if(fw != null) fw.close();
            } catch(IOException ie) {
                ie.printStackTrace();
            }
        }
        return true;
    }
    

    public static boolean writeLog(String saveDir, String fname, String log, boolean reFile){
        FileWriter fw = null;

        try {
            mkdir(saveDir); 
            fw = new FileWriter( saveDir + fname , reFile);
            fw.write(log);
            fw.flush();
            fw.close();
        } catch(Exception e) {
            return false;
        } finally {
            try {
                if(fw != null) fw.close();
            } catch(IOException ie) {
                ie.printStackTrace();
            }
        }
        return true;
    }
    
    public  String  readText(String filePath) throws Exception{ 
        
        File file = new File(filePath);  
        RandomAccessFile raf = new RandomAccessFile(file,"rw");  
         
        
        StringUtil sul = new StringUtil();
        
        String line = null; 
        String newLine  = ""; 
        
       // raf.seek(10);  
        while((line = raf.readLine()) != null){  
           newLine +=   sul.toKor(line,"UTF-8") +"\n";
           
        }
       // raf.seek(10);   
      //  raf.write("javamaster\r\n".getBytes());  
        raf.close();  
        return newLine;
        
      }
    
    public static void mkdir(String localPath) throws Exception {
        StringTokenizer st = new StringTokenizer(localPath,"/");
        StringBuffer temp = new StringBuffer();
        temp.append("/");

        while (st.hasMoreTokens()) {
            temp.append(st.nextToken());
            temp.append("/");
            makeDirectory(temp.toString());
        }
    }
    
    public static void makeDirectory(String directory) throws Exception{
        File file = new File(directory);
        if(file.exists() && file.isDirectory()) {
            return;
        } else {
            file.mkdirs();
            try {
                 //chmodFile(directory,"777");
            } catch(Exception e) {
                throw e;
            }
            return;
        }
    }
    
    
    public static void chmodFile(String fileName, String mode) throws ClassNotFoundException, IOException, InterruptedException  {
        Process p = Runtime.getRuntime().exec("/bin/chmod "+ mode + " " + fileName);
        p.waitFor();
    }
}
