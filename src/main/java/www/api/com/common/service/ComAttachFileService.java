package www.api.com.common.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;


/**
 * 공통 첨부파일(첨부파일 업/다운로드 조회) 인터페이스 클래스
 *
 * @author 정덕규
 * @since 2024.03.05
 */
public interface ComAttachFileService {
	
    
	/**
     * 파일 리스트
     *
     * @author 정덕규
     * @since 2024.03.05
     */
	public Map<String, Object> selectFileList(Map<String, Object> params);
	
	
	/**
     * 파일 삭제
     *
     * @author 정덕규
     * @since 2024.03.05
     */
    public int deleteFile(List<Map<String, Object>> list);
    
    /**
     * 
	 * 다중 첨부파일을 업로드 한다.
	 * NOTE : 다운로드 받을 첨부파일의 저장 경로, 파일명을 파라미터로 전달하여야 한다.
	 * 
     * @author 정덕규
     * @since 2024.03.05
     */
    public String fileUploadMulti(HttpServletRequest request, String tagName, String menu, String atchId) throws Exception;
}
