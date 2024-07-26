package www.com.util;

import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * <pre>
 * Statements
 *
 * <pre>
 *
 * @ClassName   : JavaGen.java
 * @Description : 클래스 설명을 기술합니다.
 * @author Jigyun
 * @since 2022. 12. 09.
 * @version 1.0
 * @see
 * @Modification Information
 *
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2022. 12. 09.     JiGyun     최초 생성
 * </pre>
 */

public class JavaGenApi {
    /******************************************************************
     * 설정 부분
     *
     * ****************************************************************/

    private static String full_path= "/src/main/java";
    private static String up_pk_name ="com";       // 최상위 레벨(최상위 패키지명 )
    private static String middle_pk_name = "www/api/brd";   // 중분류(중분류 업무 패키지명)
    private static String down_pk_name = "board";   // 하분류(하분류 업무 패키지명)
    private static String service_name = "Board";   //자바 앞에 붙는 서비스 명으로 앞의 글자 대문자!!!!!! service,dao,impl,web 경로 및 filename 이 설정 된다.
    private static String service_file_name = "Board";

    private static String unitBizName="basic";
    private static String NaDa ="정지균";           // 작성자 명
    private static String screenTitle = "게시판";    // 메뉴명 한글 설명을 넣으면 될듯합니다.
    private static String functionDesc = "CRUD";   // MVC 의 기능 설명

    private static String db_table = "board";   // 테이블명
    private static String db_key = "BOARD_IDX";   // 테이블키

    private static String db_idx = "get" + service_name + "Idx";   // 테이블키
    private static List db_list = null;   // 테이블 필드리스트
    private static HashMap db_map = null;   // 테이블 필드명
    // oracle 설정값

    private static String dbType = "mysql";
    //private static String driver = "org.mariadb.jdbc.Driver"; //db driver
    private static String driver = "com.mysql.cj.jdbc.Driver"; //db driver
    private static String user = "user";	//db 계정 아이디
    private static String password = "rego12ms";	//db 계정 비밀번호
    private static String server_ip = "jdbc:mysql://175.126.73.82:3306/nfi";		//db server ip 주소

/*
    // mysql, mariadb 설정값
    private static String dbType = "mysql";
    private static String driver = "com.mysql.cj.jdbc.Driver"; //db driver
    private static String user = "limac";	//db 계정 아이디
    private static String password = "limac2022";	//db 계정 비밀번호
    private static String server_ip = "jdbc:mysql://106.246.228.154:3306/limac";		//db server ip 주소

    // mssql 설정값
    /*
	private static String dbType = "cubrid";
    private static String driver = "cubrid.jdbc.driver.CUBRIDDriver"; //db driver
    private static String user = "dba";	//db 계정 아이디
    private static String password = "test";	//db 계정 비밀번호
    private static String server_ip = "jdbc:cubrid:210.220.48.107:33000:test:::";		//db server ip 주소
	*/
    // cubrid 설정값
    /*
	private static String dbType = "cubrid";
    private static String driver = "cubrid.jdbc.driver.CUBRIDDriver"; //db driver
    private static String user = "dba";	//db 계정 아이디
    private static String password = "test";	//db 계정 비밀번호
    private static String server_ip = "jdbc:cubrid:210.220.48.107:33000:test:::";		//db server ip 주소
    
    private static String dbType = "mariadb";
    private static String driver = "oracle.jdbc.driver.OracleDriver"; //db driver
    private static String user = "voucher";	//db 계정 아이디
    private static String password = "voucher2022!";	//db 계정 비밀번호
    private static String server_ip = "jdbc:oracle:thin:@210.220.48.43:1521:orcl";		//db server ip 주소
    
    */

    /******************************************************************
     * 가져오는 부분 : 건들지 마시오.
     *
     * ***************************************************************/

    private static String maxUp_pkg_name = ""+up_pk_name+"/"+middle_pk_name+"/"+down_pk_name;   //생성되는 패키지명
    private static String service_teplate_pkg = "template";
    private static String service_path = "";
    private static String service_java_name = "" ;
    private static String dao_path = "";
    private static String dao_java_name= "";
    private static String impl_path = "";
    private static String impl_java_name="";
    private static String web_path="";
    private static String web_java_name="";
    private static String vo_path="";
    private static String vo_java_name="";
    private static String ctrlDescription = screenTitle+" 위한 클래스로 "+functionDesc+"에 대한 컨트롤을 관리한다.";
    private static String servDescription = screenTitle+" 위한 클래스로 "+functionDesc+"에 대한 서비스를 관리한다.";
    private static String implDescription = screenTitle+" 위한 클래스로 "+functionDesc+"에 대한 비지니스 로직을 처리한다.";
    private static String daoDescription  = screenTitle+" 정보를 DB에서  "+functionDesc+" 처리한다.";
    private static String makeDayDescription="";
    private static String createTime = "";
    private static FileUtil fn = null ;

    //Src Template
    private static String template_pkg_path="res[.]template[.]";
    private static String template_do_path= "/www/api/com/sample";
    private static String service_templat_name = "Template";
    private static String teplate_path = "";

    /** 
     * Statements
     *
     * @param args
     */
    public static void main(String[] args) { 
        // TODO Auto-generated method stub
        StringUtil st= new StringUtil();
        makeDayDescription = st.getDate();
        fn  = new FileUtil();
        try {
            File file = new File(".");
            full_path = file.getCanonicalPath()+ full_path;    //실행되는 물리적인경로
            teplate_path = full_path+"/"+template_do_path;  //탬플릿 위치 경로
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        //경로 설정값
        getServicePath();


        //파일생성
        createController();
        createService();
        //createMapper();
        //createImpl();
        createSql();
        //createVO();

        System.out.println("=====================================================");
        System.out.println("생성이 완료되었습니다. 반드시 vaild.xml 작업하세요!!!!");
        System.out.println("=====================================================");
    }


    public static void  getServicePath() {
        StringBuffer br = new StringBuffer() ;
        /**  패키지명 경로 설정  */
        br.delete(0, br.length());
        br.append(up_pk_name);
        if (!"".equals(middle_pk_name)) {
            br.append("/");
            br.append(middle_pk_name);
            if (!"".equals(down_pk_name)) {
                br.append("/");
                br.append(down_pk_name);
            }
        }
        maxUp_pkg_name = br.toString();


        /**  controller 경로 설정  */
        br.delete(0, br.length());
        br.append(full_path);
        br.append("/");
        br.append(maxUp_pkg_name);
        br.append("/");
        br.append("web");
        br.append("/");
        web_path = br.toString();
        web_java_name = service_file_name+"Controller";


        /** service 경로 설정 */
        br.delete(0, br.length());
        br.append(full_path);
        br.append("/");
        br.append(maxUp_pkg_name);
        br.append("/");
        br.append("service") ;
        br.append("/");

        service_path = br.toString();
        service_java_name =service_file_name+"Service";

        /** dao 경로 설정 */
        br.delete(0, br.length());
        br.append(full_path);
        br.append("/");
        br.append(maxUp_pkg_name);
        br.append("/");
        br.append("dao");
        br.append("/");
        dao_path = br.toString();
        dao_java_name =  service_file_name+"Mapper";

        /** impl 경로 설정 */
        br.delete(0, br.length());
        br.append(full_path);
        br.append("/");
        br.append(maxUp_pkg_name);
        br.append("/");
        br.append("service") ;
        br.append("/");
        br.append("Impl");
        br.append("/");
        impl_path = br.toString();
        impl_java_name =service_file_name+"Service"+"Impl";


        /** vo 경로 설정 */
        br.delete(0, br.length());
        br.append(full_path);
        br.append("/");
        br.append(maxUp_pkg_name);
        br.append("/");
        br.append("service") ;
        br.append("/");
        vo_path = br.toString();
        vo_java_name =service_file_name+"VO";

        /** template 경로 설정 */
    }


    /** 컨트롤러 생성   */
    public static void  createController() {
        try {
            /** 서비스 에 대한  파일 생성 처리 */
            if(fn.fileExists(web_path+"/"+web_java_name+".java")){
                System.out.println(web_path+"안에 같은 파일이 존재 합니다. 생성하지 않습니다.");
            }else {
                /** 서비스 원본 대상 */
                String original_java = teplate_path + "/web/"+ service_templat_name+"Controller.java";

                // 원본 대상 읽기 처리
                try {
                    /** 원본 대상 존재 유무 확인 */
                    if(fn.fileExists(original_java)) {
                        System.out.println("controller 탬플릿 파일이 존재 합니다. 복사합니다.");
                        String newLine  =   fn.readText(original_java) ;
                        //패키지명변경

                        newLine = newLine.replaceAll("com.basic.api.sample", maxUp_pkg_name.replaceAll("/", "."));
                        newLine = newLine.replaceAll("TemplateService", service_java_name);
                        newLine = newLine.replaceAll("templateService", service_java_name.substring(0,1).toLowerCase()+service_java_name.substring(1,service_java_name.length()));
                        newLine = newLine.replaceAll("TemplateController", web_java_name);
                        newLine = newLine.replaceAll("Template", service_name);
                        newLine = newLine.replaceAll("template", service_name.substring(0,1).toLowerCase()+service_name.substring(1,service_name.length()));
                        newLine = newLine.replaceAll("screenTitle", screenTitle);
                        newLine = newLine.replaceAll("getIdx", db_idx);
                        newLine = newLine.replaceAll("ctrlDescription", ctrlDescription);
                        newLine = newLine.replaceAll("2012. 00. 00.", makeDayDescription);
                        newLine = newLine.replaceAll("unitBizName", unitBizName);
                        newLine = newLine.replaceAll("NaDa", NaDa);
                        //System.out.println("newLine == " +newLine);
                        if(newLine !=null ){
                            fn.makeDirectory(web_path);
                            fn.writeText(web_path+"/"+web_java_name+".java", newLine);
                        }
                        newLine = "";
                    } else {
                        System.out.println("controller 탬플릿 파일이 존재 하지 않습니다.");
                    }

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /** 서비스 파일 생성 */
    public static void  createService() {
        try {
            /** 서비스 에 대한  파일 생성 처리 */
            if(fn.fileExists(service_path+"/"+service_java_name+".java")){
                System.out.println(service_path+"안에 파일이 존재 합니다. 생성하지 않습니다.");
            }else {
                /** 서비스 원본 대상 */
                String original_java = teplate_path + "/service/"+ service_templat_name+"Service.java";
                // 원본 대상 읽기 처리
                try {
                    /** 원본 대상 존재 유무 확인 */
                    if(fn.fileExists(original_java)){
                        System.out.println("service 탬플릿 파일이 존재 합니다. 복사합니다.");
                        String newLine  =   fn.readText(original_java) ;
                        //패키지명변경
                        newLine = newLine.replaceAll("com.basic.api.sample", maxUp_pkg_name.replaceAll("/", "."));
                        newLine=newLine.replaceAll("TemplateService", service_java_name);
                        newLine=newLine.replaceAll("templateService", service_java_name.substring(0,1).toLowerCase()+service_java_name.substring(1,service_java_name.length()));
                        newLine = newLine.replaceAll(template_pkg_path, up_pk_name+"."+middle_pk_name+".");
                        newLine = newLine.replaceAll("Template", service_name);
                        newLine = newLine.replaceAll("template", service_name.substring(0,1).toLowerCase()+service_name.substring(1,service_name.length()));
                        newLine = newLine.replaceAll("servDescription", servDescription);
                        newLine = newLine.replaceAll("screenTitle", screenTitle);
                        newLine = newLine.replaceAll("2012. 00. 00.", makeDayDescription);
                        newLine = newLine.replaceAll("unitBizName", unitBizName);
                        newLine = newLine.replaceAll("NaDa", NaDa);
                        if(newLine !=null ){
                            fn.makeDirectory(service_path);
                            fn.writeText(service_path+"/"+service_java_name+".java", newLine);
                        }
                        newLine = "";
                    }else {
                        System.out.println("service 탬플릿 파일이 존재 하지 않습니다.");
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /** dao 생성 */
    public static void  createMapper() {
        try {
            /** 서비스 에 대한  파일 생성 처리 */
            if(fn.fileExists(dao_path+"/"+dao_java_name+".java")){
                System.out.println(dao_path+"안에 파일이 존재 합니다. 생성하지 않습니다.");
            } else {
                /** 서비스 원본 대상 */
                String original_java = teplate_path + "/dao/"+ service_templat_name+"Mapper.java";
                // 원본 대상 읽기 처리
                try {
                    /** 원본 대상 존재 유무 확인 */
                    if(fn.fileExists(original_java)){
                        System.out.println("dao 탬플릿 파일이 존재 합니다. 복사합니다.");
                        String newLine  =   fn.readText(original_java) ;
                        //패키지명변경
                        newLine = newLine.replaceAll("com.basic.api.sample", maxUp_pkg_name.replaceAll("/", "."));
                        newLine = newLine.replaceAll("TemplateDAO", dao_java_name);
                        newLine = newLine.replaceAll("templateDAO", dao_java_name.substring(0,1).toLowerCase()+dao_java_name.substring(1,dao_java_name.length()));
                        newLine = newLine.replaceAll(template_pkg_path, up_pk_name+"."+middle_pk_name+".");
                        newLine = newLine.replaceAll(template_pkg_path, up_pk_name+"."+middle_pk_name+".");
                        newLine = newLine.replaceAll("Template", service_name);
                        newLine = newLine.replaceAll("template", service_name.substring(0,1).toLowerCase()+service_name.substring(1,service_name.length()));
                        newLine = newLine.replaceAll("daoDescription", daoDescription);
                        newLine = newLine.replaceAll("screenTitle", screenTitle);
                        newLine = newLine.replaceAll("2012. 00. 00.", makeDayDescription);
                        newLine = newLine.replaceAll("unitBizName", unitBizName);
                        newLine = newLine.replaceAll("NaDa", NaDa);
                        if(newLine !=null ){
                            fn.makeDirectory(dao_path);
                            fn.writeText(dao_path+"/"+dao_java_name+".java", newLine);
                        }
                        newLine = "";
                    } else {
                        System.out.println("dao 탬플릿 파일이 존재 하지 않습니다.");
                    }

                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /** impl 파일 생성 */
    public static void  createImpl() {
        try {
            /** 서비스 에 대한  파일 생성 처리 */
            if(fn.fileExists(impl_path.replaceAll("Impl", "impl")+"/"+impl_java_name+".java")){
                System.out.println(impl_path+"안에 파일이 존재 합니다. 생성하지 않습니다.");
            }else {
                /** 서비스 원본 대상 */
                String original_java = teplate_path + "/service/impl/"+ service_templat_name+"ServiceImpl.java";
                // 원본 대상 읽기 처리
                try {
                    /** 원본 대상 존재 유무 확인 */
                    if(fn.fileExists(original_java)){
                        System.out.println("impl 탬플릿 파일이 존재 합니다. 복사합니다.");
                        String newLine  =   fn.readText(original_java) ;
                        //패키지명변경
                        newLine = newLine.replaceAll("com.basic.api.sample", maxUp_pkg_name.replaceAll("/", "."));
                        newLine = newLine.replaceAll("TemplateImpl", impl_java_name);
                        //서비스 명 변경
                        newLine = newLine.replaceAll("templateService", service_java_name.substring(0,1).toLowerCase()+service_java_name.substring(1,service_java_name.length()));
                        //dao 명 변경
                        newLine = newLine.replaceAll("TemplateService", service_java_name.substring(0,1).toUpperCase()+service_java_name.substring(1,service_java_name.length()));
                        newLine = newLine.replaceAll("templateDAO", service_java_name.substring(0,1).toLowerCase()+service_java_name.substring(1,service_java_name.length()).replaceAll("Service", "")+"DAO");
                        newLine = newLine.replaceAll("TemplateDAO", service_java_name.substring(0,1).toUpperCase()+service_java_name.substring(1,service_java_name.length()).replaceAll("Service", "")+"DAO");
                        newLine = newLine.replaceAll(template_pkg_path, up_pk_name+"."+middle_pk_name+".");
                        newLine = newLine.replaceAll("Template", service_name);
                        newLine = newLine.replaceAll("template", service_name.substring(0,1).toLowerCase()+service_name.substring(1,service_name.length()));
                        newLine = newLine.replaceAll("implDescription", implDescription);
                        newLine = newLine.replaceAll("screenTitle", screenTitle);
                        newLine = newLine.replaceAll("2012. 00. 00.", makeDayDescription);
                        newLine = newLine.replaceAll("unitBizName", unitBizName);
                        newLine = newLine.replaceAll("NaDa", NaDa);
                        if(newLine !=null ){
                            String makeTempDir = impl_path.replaceAll("Impl", "impl");
                            fn.makeDirectory(makeTempDir);
                            fn.writeText(makeTempDir+"/"+impl_java_name+".java", newLine);
                        }
                        newLine = "";
                    } else {
                        System.out.println("impl 탬플릿 파일이 존재 하지 않습니다.");
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    /** vo 파일 생성 */
    public static void  createVO() {
        try {
            if(fn.fileExists(vo_path+"/"+vo_java_name+".java")){
                System.out.println(vo_path+"안에 파일이 존재 합니다. 생성하지 않습니다.");
            } else {
                // 원본 대상 읽기 처리
                try {
                    /** 서비스 원본 대상 */
                    String original_java = teplate_path + "/service/"+ service_templat_name+"VO.java";
                    /** 원본 대상 존재 유무 확인 */
                    if(fn.fileExists(original_java)){
                        System.out.println("vo 탬플릿 파일이 존재 합니다. 복사합니다.");

                        String newLine  =   fn.readText(original_java);
                        HashMap columnMap = getTableMap();

                        String variable = "";		//변수 선언부분
                        String getset = "";		//getSet 영역

                        int mapI = 1;
                        for (int i=0; i < db_list.size(); i++) {
                            String key = (String)db_list.get(i);

                            //variable 넣기
                            variable += "\tprivate String "+convert2CamelCase(key) +"; ";
                            if(columnMap.get(key).toString().split("#").length <= 1 ) {
                                //
                            }
                            else if (columnMap.get(key).toString().indexOf("clob") >= 0) {
                                variable += "		//"+columnMap.get(key).toString().split("#")[1];
		                    /*
		                    } else if (columnMap.get(key).toString().indexOf("int") >= 0) {
		                    	variable += "private int "+convert2CamelCase(key)+";		//"+columnMap.get(key).toString().split("#")[1]+" \n";
		                    */
                            } else {
                                System.out.println(columnMap.get(key).toString());
                                variable += "		//"+columnMap.get(key).toString().split("#")[1];
                            }

                            variable += " \n";

                            getset += "\n";
                            if (columnMap.get(key).toString().indexOf("clob") >= 0) {
                                //get작업
                                getset += "\t";
                                getset += "public String " +convert2CamelCase("get_"+key)+"() { \n";
                                getset += "\t\t";
                                getset += "return "+convert2CamelCase(key)+";\n";
                                getset += "\t";
                                getset += "}\n";
                                //set작업
                                getset += "\t";
                                getset += "public void " +convert2CamelCase("set_"+key)+"(String "+convert2CamelCase(key)+") { \n";
                                getset += "\t\t";
                                getset += "this."+convert2CamelCase(key)+" = "+ convert2CamelCase(key) +";\n";
                                getset += "\t";
                                getset += "}\n";
		                    /*
		                     * int로 했을시 null포인트 에러나서.. 수정할게 많아서.. 뺀다.
		                    } else if (columnMap.get(key).toString().indexOf("int") >= 0) {
		                    	//get작업
		                    	getset += "\t";
		                    	getset += "public int " +convert2CamelCase("get_"+key)+"() { \n";
		                    	getset += "\t\t";
		                    	getset += "return "+convert2CamelCase(key)+";\n";
		                    	getset += "\t";
		                    	getset += "}\n";
		                    	//set작업
		                    	getset += "\t";
		                    	getset += "public void " +convert2CamelCase("set_"+key)+"(int "+convert2CamelCase(key)+") { \n";
		                    	getset += "\t\t";
		                    	getset += "this."+convert2CamelCase(key)+" = "+ convert2CamelCase(key) +";\n";
		                    	getset += "\t";
		                    	getset += "}\n";
		                    */
                            } else {
                                //get작업
                                getset += "\t";
                                getset += "public String " +convert2CamelCase("get_"+key)+"() { \n";
                                getset += "\t\t";
                                getset += "return "+convert2CamelCase(key)+";\n";
                                getset += "\t";
                                getset += "}\n";
                                //set작업
                                getset += "\t";
                                getset += "public void " +convert2CamelCase("set_"+key)+"(String "+convert2CamelCase(key)+") { \n";
                                getset += "\t\t";
                                getset += "this."+convert2CamelCase(key)+" = "+ convert2CamelCase(key) +";\n";
                                getset += "\t";
                                getset += "}\n";
                            }
                            mapI++;

                        }

                        newLine = newLine.replaceAll("//VARIABLE", variable);
                        newLine = newLine.replaceAll("//GETSET", getset);


                        //패키지명변경
                        newLine = newLine.replaceAll("com.basic.api.sample", maxUp_pkg_name.replaceAll("/", "."));
                        newLine = newLine.replaceAll("templateDAO", service_java_name.substring(0,1).toLowerCase()+service_java_name.substring(1,service_java_name.length()).replaceAll("Service", "")+"DAO");
                        newLine = newLine.replaceAll("TemplateDAO", service_java_name.substring(0,1).toUpperCase()+service_java_name.substring(1,service_java_name.length()).replaceAll("Service", "")+"DAO");
                        newLine = newLine.replaceAll("Template", service_name);
                        newLine = newLine.replaceAll("implDescription", implDescription);
                        newLine = newLine.replaceAll("screenTitle", screenTitle);
                        newLine = newLine.replaceAll("2012. 00. 00.", makeDayDescription);
                        newLine = newLine.replaceAll("unitBizName", unitBizName);
                        newLine = newLine.replaceAll("NaDa", NaDa);
                        if(newLine !=null ){
                            fn.writeText(vo_path+"/"+vo_java_name+".java", newLine);
                        }
                        newLine = "";
                    } else {
                        System.out.println("vo 탬플릿 파일이 존재 하지 않습니다.");
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }



    /** sql 파일 생성 */
    public static void  createSql() {
        try {
            String original_java = full_path.replace("java", "resources")+ "/mapper/api/TemplateApiMapper.xml";
            if(fn.fileExists(original_java.replace("Template", service_name))) {
                System.out.println(original_java.replace("Template", service_name)+"안에 파일이 존재 합니다. 생성하지 않습니다.");
            } else {
                // 원본 대상 읽기 처리
                try {
                    /** 원본 대상 존재 유무 확인 */
                    if(fn.fileExists(original_java)){
                        System.out.println("sql 탬플릿 파일이 존재 합니다. 복사합니다.");
                        String newLine  =   fn.readText(original_java); 
                        HashMap columnMap = getTableMap();

                        String columns_ = "";		//db컬럼 - 실제 db명 SS_IDX
                        String columnsAdd_ = "";		//db컬럼 - 실제 db명 SS_IDX
                        String values_ = "";		//dbvlaue - ssIdx
                        String resultMap_ = "";		//resultVo
                        String columnUpdate_ = "";

                        int mapI = 1;
                        for (int i=0; i < db_list.size(); i++) {

                            String key = (String)db_list.get(i);

                            if (!"".equals(columns_)) {
                                columns_ += "\t\t\t,";
                            }
                            columns_ += key.toUpperCase() + "\n";

                            if (!db_key.equals(key.toUpperCase())) {
                                //columns_ 넣기
                                if (!"".equals(columnsAdd_)) {
                                    columnsAdd_ += "\t\t\t,";
                                }
                                columnsAdd_ += key.toUpperCase() + "\n";

                                //values_ 값 넣기
                                if (!"".equals(values_)) {
                                    values_ += "\t\t\t,";
                                }
                                if (key.indexOf("_DATE") >= 0) {
                                    values_ += "now()\n";
                                } else {
                                    values_ += "#{"+convert2CamelCase(key)+"}\n";
                                }
                                //resultMap_ 값 넣기
                                if (!"".equals(resultMap_)) {
                                    resultMap_ += "\t\t";
                                }

                                if (columnMap.get(key).toString().indexOf("clob") >= 0) {
                                    resultMap_ += "<result property=\""+convert2CamelCase(key)+"\" column=\""+key.toUpperCase()+"\" javaType=\"string\" />\n";
                                /*
                                 * int로 했을시 null포인트 에러나서.. 수정할게 많아서.. 뺀다.
                                } else if (columnMap.get(key).toString().indexOf("int") >= 0) {
                                    resultMap_ += "<result property=\""+convert2CamelCase(key)+"\" column=\""+key.toUpperCase()+"\" javaType=\"string\" />\n";
                                */
                                } else {
                                    resultMap_ += "<result property=\""+convert2CamelCase(key)+"\" column=\""+key.toUpperCase()+"\" javaType=\"string\" />\n";
                                }
                            }

                            //columnUpdate_ 값 넣기
                            if (!db_key.equals(key.toUpperCase())) {
                                //키값 아닌것만 업데이트 한다.
                                if (!"".equals(columnUpdate_)) {
                                    columnUpdate_ += "\t\t\t";
                                }
                                columnUpdate_ += "<if test='"+convert2CamelCase(key)+" != null and "+convert2CamelCase(key)+" != \"\" '>\n";
                                columnUpdate_ += "\t\t\t\t";
                                if (columnMap.size() == mapI) {
                                    columnUpdate_ += key.toUpperCase()+ " = #{"+ convert2CamelCase(key) +"}\n";
                                } else {
                                    columnUpdate_ += key.toUpperCase()+ " = #{"+ convert2CamelCase(key) +"},\n";
                                }
                                columnUpdate_ += "\t\t\t";
                                columnUpdate_ += "</if>\n";
                            }
                            mapI++;
                        }

                        newLine = newLine.replaceAll("values_", values_);
                        newLine = newLine.replaceAll("columnsAdd_", columnsAdd_);
                        newLine = newLine.replaceAll("columns_", columns_);
                        newLine = newLine.replaceAll("table_", db_table);
                        newLine = newLine.replaceAll("resultMap_", resultMap_);
                        newLine = newLine.replaceAll("columnUpdate_", columnUpdate_);
                        newLine = newLine.replaceAll("columnId_", db_key.toUpperCase());
                        newLine = newLine.replaceAll("valueId_", "#{"+convert2CamelCase(db_key)+"}");

 
                        //패키지명변경
                        newLine = newLine.replaceAll("com.basic.api.sample", maxUp_pkg_name.replaceAll("/", "."));
                        newLine = newLine.replaceAll("templateDAO", service_java_name.substring(0,1).toLowerCase()+service_java_name.substring(1,service_java_name.length()).replaceAll("Service", "")+"DAO");
                        newLine = newLine.replaceAll("TemplateDAO", service_java_name.substring(0,1).toUpperCase()+service_java_name.substring(1,service_java_name.length()).replaceAll("Service", "")+"DAO");
                        newLine = newLine.replaceAll("Template", service_name);
                        newLine = newLine.replaceAll("template", service_name.substring(0,1).toLowerCase()+service_name.substring(1,service_name.length()));
                        newLine = newLine.replaceAll("implDescription", implDescription);
                        newLine = newLine.replaceAll("screenTitle", screenTitle);
                        newLine = newLine.replaceAll("2012. 00. 00.", makeDayDescription);
                        newLine = newLine.replaceAll("unitBizName", unitBizName);
                        newLine = newLine.replaceAll("NaDa", NaDa);
                        if(newLine !=null ){
                            fn.writeText(original_java.replace("Template", service_name), newLine);
                        }
                        newLine = "";
                    } else {
                        System.out.println("sql 탬플릿 파일이 존재 하지 않습니다.");
                    }
                } catch (Exception e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static HashMap getTableMap() {
        if (db_list != null) {
            return db_map;
        }
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        List arrList= new ArrayList();
        HashMap columnMap= new HashMap(); //DB의 Field의 정보 모음
        try{
            java.util.Properties info = new java.util.Properties();
            info.put("remarksReporting","true");
            info.put("user", user);			//DB아이디
            info.put("password", password);	//DB비밀번호
            Class.forName(driver);
            conn = DriverManager.getConnection(server_ip, info);
            DatabaseMetaData databaseMetaData = conn.getMetaData();
            rs = databaseMetaData.getColumns(null, null, db_table.toUpperCase(), "%");
            ResultSetMetaData rm = rs.getMetaData();

            while(rs.next())
            {
            	/*
            	//단건씩
	        	System.out.println("COLUMN_NAME="+rs.getString("COLUMN_NAME"));	//컬럼 이름
	        	System.out.println("DATA_TYPE="+rs.getString("DATA_TYPE"));		//java.sql.Types의 SQL 유형
	        	System.out.println("TYPE_NAME="+rs.getString("TYPE_NAME"));		//데이터 소스에 따른 유형 이름
	        	System.out.println("IS_NULLABLE="+rs.getString("IS_NULLABLE"));	// "NO"는 열이 명백하게 NULL 값을 허용하지 않음을 의미하며, "YES"는 열이 NULL 값을 허용함을 의미합니다. 공백 문자열은 알 수 없다는 뜻입니다
	        	System.out.println("NULLABLE="+rs.getString("NULLABLE"));		//NULL 허용 여부 ,columnNoNulls - NULL 값을 허용하지 않음 , columnNullable - 명백하게 NULL 값을 허용 , columnNullableUnknown - null 허용 여부를 알 수 없음 
	        	System.out.println("COLUMN_SIZE="+rs.getString("COLUMN_SIZE"));	//열 크기. 문자 또는 날짜 유형의 경우 문자의 최대 수, 숫자 또는 십진수 유형의 경우 유효 자리입니다
	        	System.out.println("ORDINAL_POSITION="+rs.getString("ORDINAL_POSITION"));	//테이블내의 열 색인 
	        	System.out.println("REMARKS="+rs.getString("REMARKS"));		// 열을 설명하는 주석
	        	System.out.println("COLUMN_DEF="+rs.getString("COLUMN_DEF")); //기본값 
	        	System.out.println("DECIMAL_DIGITS="+rs.getString("DECIMAL_DIGITS")); //소수 자릿수 
	        	System.out.println("=================================================");
	        	*/
            	
	        	/*
	        	//for문 돌면서 rs.getString 가져오는 예제
            	for (int i = 0 ; i < rm.getColumnCount() ; i++ ) {
                    System.out.println(rm.getColumnName( i + 1 ) + ": \t\t" + rs.getString( i + 1 ));
            	}
            	*/
                String sTypeStr ="";	//db타입
                String remark ="";		//db주석
                if ("CLOB".equals(rs.getString("TYPE_NAME"))) {
                    sTypeStr    = "clob";
                } else if ("BLOB".equals(rs.getString("TYPE_NAME"))) {
                    sTypeStr    = "clob";
                } else if ("NUMBER".equals(rs.getString("TYPE_NAME"))) {
                    sTypeStr    = "int";
                } else if ("INTEGER".equals(rs.getString("TYPE_NAME"))) {
                    sTypeStr    = "int";
                } else if ("LONG".equals(rs.getString("TYPE_NAME"))) {
                    sTypeStr    = "int";
                } else {
                    sTypeStr    = "string";
                }

                if (rs.getString("REMARKS") != null) {
                    remark = rs.getString("REMARKS");
                }
                columnMap.put(rs.getString("COLUMN_NAME"), sTypeStr+"#"+remark);
                arrList.add(rs.getString("COLUMN_NAME"));
            }
            
            /*
            stmt = conn.createStatement();
            String sql = "select * from "+db_table +" where 0=1";
            rs = stmt.executeQuery(sql);
            
            ResultSetMetaData rsmd = rs.getMetaData(); //resultSet의 컬럼정보를 가져온다. 
            int columnCount = rsmd.getColumnCount();   //컬럼 갯수
            
            //column 정보 담기
            for(int i = 1; i <= columnCount; i++)
            { 
                String sTypeStr ="";
                if (rsmd.getColumnType(i) == Types.BLOB) {
                    sTypeStr    = "clob";
                } else if (rsmd.getColumnType(i) == Types.CLOB) {
                    sTypeStr    = "clob";
                } else if (rsmd.getColumnType(i) == Types.NUMERIC) {
                    sTypeStr    = "int";
                } else {
                    sTypeStr    = "string";
                }
                
                columnMap.put(rsmd.getColumnName(i), sTypeStr);
            }
            */
            db_list = arrList;
            db_map = columnMap;
        } catch(Exception e) {
            System.out.println(e);
        } finally{
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
        return columnMap;
    }

    public static String convert2CamelCase(String underScore){
        if(underScore.indexOf('_') < 0 && Character.isLowerCase(underScore.charAt(0)))
            return underScore;
        StringBuilder result = new StringBuilder();
        boolean nextUpper = false;
        int len = underScore.length();
        for(int i = 0; i < len; i++)
        {
            char currentChar = underScore.charAt(i);
            if(currentChar == '_')
            {
                nextUpper = true;
                continue;
            }
            if(nextUpper)
            {
                result.append(Character.toUpperCase(currentChar));
                nextUpper = false;
            } else
            {
                result.append(Character.toLowerCase(currentChar));
            }
        }

        return result.toString();
    }

}
