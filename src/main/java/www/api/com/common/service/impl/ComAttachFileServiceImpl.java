package www.api.com.common.service.impl;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import www.api.com.common.service.ComAttachFileService;
import www.com.util.CommonDao;
import www.com.util.FileUtil;
import www.com.util.StringUtil;

/**
 * 공통 첨부파일(첨부파일 업/다운로드 조회) 서비스 구현 클래스
 *
 * @author 정덕규
 * @since 2024.03.05
 */
@Service
public class ComAttachFileServiceImpl extends EgovAbstractServiceImpl implements ComAttachFileService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@Autowired
    private CommonDao dao;

    
    private String namespace = "www.api.com.attach.Attach";
    
    public static final String DEFAULT_SEPARATOR = "/";
    
    @Value("#{ConfigProperties['file.upload.path']}")
    private String fileUploadPath;
    
    @Value("#{ConfigProperties['temp.upload.path']}")
    private String tempUploadPath;
    
    @Value("#{ConfigProperties['outbound.upload.path']}")
    private String outboundUploadPath;
    
    @Value("#{ConfigProperties['file.rcognUploadFiles']}")
    private String fileRcognUploadFiles;

    /**
     * 파일 리스트
     *
     * @author 정덕규
     * @since 2024.03.05
     */
	public Map<String, Object> selectFileList(Map<String, Object> params) {		
		return dao.selectOne(namespace + ".selectAttachFileList", params);
	}


	/**
     * 파일 삭제
     *
     * @author 정덕규
     * @since 2024.03.05
     */
    public int deleteFile(List<Map<String, Object>> list) {

        int totCnt = 0;


        String baseUploadDir = fileUploadPath;
        
        //첨부파일 마스터 생성 TM_ATFILE, 마스터는 fileIdxx별로 1개만 생성
        for (int i = 0; i < list.size(); i++) {
            
            Map<String, Object> data = (Map<String, Object>) list.get(i);

            //넥사크로는 컨트롤단에서 request를 map에 담을 때 UPDT_USID,UPDT_DATE 를 담아와서 sessionUserInfo 사용할 필요 없음
            //첨부파일이 없는 경우 첨부파일 삭제 로직을 실행하지 않는다.
            if ((data.get("fileIdxx") != null) && (data.get("fileIdxx").toString().length() < 1)) continue;

            
            //컨트롤 단에서 삭제 리스트와 업데이트리스트를 분기시켜서 가져오기 때문에, 이 구문은 삭제리스트만 있음.
            //String rowStatus = (String) data.get("ROW_TYPE");

            String fileName = "";
            String filePath = "";
            File deleteFile = null;

            //2014-03 : 재조회 하지 않는 경우 등은 절대경로가 다를 수 있음, DB삭제시 저장상태로도 파일 삭제 위함

            //1. DB자료 삭제
            //디테일만 삭제한다.
            //디테일이 전부 삭제되면 헤더도 삭제된다.
            dao.delete(namespace + ".deleteAttachFile", data);

            //2. 파일 삭제(지정경로)
            fileName = (String) data.get("pfilName");
            filePath = (String) data.get("filePath");
            
            if (!filePath.startsWith(baseUploadDir)) {
                filePath = baseUploadDir.concat(filePath);
            }
            
            deleteFile = new File(filePath.concat(File.separatorChar + fileName));

            //파일 접근권한이 충분한지 모두 체크함
            if(deleteFile.isFile() && deleteFile.exists() && deleteFile.canWrite()) {
                deleteFile.delete();    //삭제
                totCnt++;
            }

        }
        return totCnt;

    }
    
    /**
     * 다중 첨부파일을 업로드 한다.
	 * NOTE : 다운로드 받을 첨부파일의 저장 경로, 파일명을 파라미터로 전달하여야 한다.
	 * 
     * @author 정덕규
     * @since 2024.03.05
     * @param MciRequest 조회 조건
     * @param Model 조회 결과
     * @return
     * @throws Exception
     */
    public String fileUploadMulti(HttpServletRequest request, String tagName, String menu, String atchId) throws Exception {
       
		String[] menuArr = menu.split("\\.");
		
		String directory = menuArr[2];
		String program = menuArr[3];
		if ((directory == null) || (directory.length() == 0) || (program == null) || (program.length() == 0)) {
			directory = "etc";
			program = "ETC";
		}
		
		// 임시 업로드 디렉토리
		String baseUploadPath = fileUploadPath;
		
		String prefixDir = directory.concat(DEFAULT_SEPARATOR).concat(program);
		
		String yearMonth = new SimpleDateFormat("yyyyMM").format(new Date(System.currentTimeMillis()));
		
		String uploadPath = baseUploadPath.concat(prefixDir).concat(DEFAULT_SEPARATOR).concat(yearMonth);
		
		logger.debug(" -------------------- Upload path : " + uploadPath);
		
		uploadPath = FileUtil.filePathBlackList(uploadPath);
		uploadPath = StringUtils.cleanPath(uploadPath);
		
		String uploadPathSub = uploadPath;
		uploadPathSub = uploadPath.replace(baseUploadPath, "");
		
		logger.debug(" -------------------- Upload path(sub) : " + uploadPathSub);
		
		File uploadDir = new File(uploadPath);
		if (!uploadDir.exists()) {
			uploadDir.mkdirs();
		}
		
		// 일반 Http Request 객체를 스프링 Multipart 지원 Http Request 객체로 변환
		MultipartHttpServletRequest mRequest = (MultipartHttpServletRequest) request;
		
		MultipartFile mFile = mRequest.getFile(tagName);
		File newFile = null;
		String fileName = ""; // 신규생성 파일은 timestamp + 6자리 난수
		
		String mstFileId = atchId;
		
		// 처음신규인경우
		if (StringUtil.isEmpty(atchId)) {
			// 파일ID는 현재 타임스탬프 + 8자리 랜덤 숫자
			mstFileId = System.currentTimeMillis() + RandomStringUtils.randomNumeric(8);
		}
		
		// 파일 확장자 체크
		String fileExtrn = "";
		
		String checkFileExtension = fileRcognUploadFiles;
		
		String[] checkFileExtArr = StringUtils.commaDelimitedListToStringArray(checkFileExtension);
		
		logger.debug(" -------------------- Upload file : " + tagName);
		
		// 파일 확장자를 체크한다!
		fileExtrn = mFile.getOriginalFilename().substring(mFile.getOriginalFilename().lastIndexOf(".") + 1);
		
		logger.debug(" -------------------- Upload file extension : " + fileExtrn);
		
		boolean isChkFileExt = false;
		// 파일확장자 체크 로직
		for (int chkCnt = 0; chkCnt < checkFileExtArr.length; chkCnt++) {
		
			if (fileExtrn.equalsIgnoreCase(checkFileExtArr[chkCnt])) {
				isChkFileExt = true;
				break;
			}
		}
		
		if (!isChkFileExt) {
			logger.debug("파일확장자 [" + fileExtrn + "] 은/는 업로드 할 수 없습니다.");
			throw new Exception("파일확장자 [" + fileExtrn + "] 은/는 업로드 할 수 없습니다.");
		}
		
		// 임시폴더에 파일 생성. 파일 명명 규칙은 현재 타임스탬프 + 8자리 랜덤 숫자
		fileName = mstFileId + RandomStringUtils.randomNumeric(8);
		
		// 파일 용량을 체크할 경우 context-common.xml에서 사이즈 관련 속성 삭제 후 여기에서 체크하셔도 상관없습니다.
		logger.debug(" -------------------- File : " + mFile.getOriginalFilename() + "," + " size : " + mFile.getSize()
				+ ", content-type : " + mFile.getContentType());
		
		// 업로드 할 파일을 생성한다.
		newFile = new File(uploadDir, fileName);
		
		// 파일을 업로드 폴더로 이동한다.
		mFile.transferTo(newFile);
		
		// file moved successfully..
		logger.debug(" -------------------- Multi File transfer success...");
		
		Map<String, Object> eachFile = new HashMap<String, Object>();
		eachFile.put("fileIdxx", mstFileId); // 170608600699550381633
		eachFile.put("filePath", uploadPathSub); // tmp/com/202401
		eachFile.put("pfilName", fileName); // 17060860069955038163328076832
		eachFile.put("lfilName", mFile.getOriginalFilename()); // undraw_profile_1.svg
		eachFile.put("fileSize", mFile.getSize()); // 2163
		eachFile.put("systCode", prefixDir.substring(0, 3).toUpperCase());
		eachFile.put("menuIdxx", program);
		eachFile.put("extnName", fileExtrn); // svg
		eachFile.put("fptyCode", "UPLD");
		
		logger.debug("eachFile = " + eachFile);
		
		if (dao.selectOne(namespace + ".selectAttachFile", eachFile) == null) {
			dao.insert(namespace + ".insertAttachFile", eachFile);
		} else {
			dao.update(namespace + ".updateAttachFile", eachFile);
		}
		
		return mstFileId;
    }
	
}
