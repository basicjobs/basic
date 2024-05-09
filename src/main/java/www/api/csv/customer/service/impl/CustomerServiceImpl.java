package www.api.csv.customer.service.impl;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import www.api.com.common.service.ComAttachFileService;
import www.api.csv.customer.service.CustomerService;
import www.com.util.CommonDao;

/**
 * 고객서비스 구현 클래스
 *
 * @author 정덕규
 * @since 2024.03.05
 */
@Service
public class CustomerServiceImpl extends EgovAbstractServiceImpl implements CustomerService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommonDao dao;
    
    @Autowired
    private ComAttachFileService comAttachFileService;

    
    private String namespace = "www.api.csv.customer.Customer";
    
    /**
     * 공지사항 리스트
     *
     * @author 정덕규
     * @since 2024.03.05
     */
    public List<Map<String, Object>> selectNoticeList(Map<String, Object> params) {
		return dao.list(namespace + ".selectNoticeList", params);
	}
    
    /**
     * 파일 업로드
     *
     * @author 정덕규
     * @since 2024.03.05
     */
    public void uploadFiles(HttpServletRequest request, Map<String, Object> params) throws Exception {
    	String bizrnoAtchId = comAttachFileService.fileUploadMulti(request, "bizrnoAtchId", namespace, (String)params.get("bizrnoAtchId"));
    	String hffcynAtchId = comAttachFileService.fileUploadMulti(request, "hffcynAtchId", namespace, (String)params.get("hffcynAtchId"));
    	logger.debug("사업자등록증 첨부ID = " + bizrnoAtchId);
    	logger.debug("재직증명서 첨부ID = " + hffcynAtchId);
	}
    
    /**
     * 공지사항 등록
     *
     * @author 정덕규
     * @since 2024.03.05
     */
    public void insertNotice(Map<String, Object> params) {
		dao.insert(namespace + ".insertNotice", params);
	}
	
    /**
     * 공지사항 수정
     *
     * @author 정덕규
     * @since 2024.03.05
     */
	public int updateNotice(Map<String, Object> params) {
		return dao.update(namespace + ".updateNotice", params);
	}
	
	/**
     * 공지사항 삭제
     *
     * @author 정덕규
     * @since 2024.03.05
     */
	public int deleteNotice(Map<String, Object> params) {
		return dao.delete(namespace + ".deleteNotice", params);
	}
	
	/**
     * 공지사항 조회
     *
     * @author 정덕규
     * @since 2024.03.05
     */
	public Map<String, Object> selectNotice(Map<String, Object> params) {
		return dao.selectOne(namespace + ".selectNotice", params);
	}
    
    
	/**
     * FAQ 리스트
     *
     * @author 정덕규
     * @since 2024.03.05
     */
    public List<Map<String, Object>> selectFaqList(Map<String, Object> params) {
		return dao.list(namespace + ".selectFaqList", params);
	}

    /**
     * FAQ 등록
     *
     * @author 정덕규
     * @since 2024.03.05
     */
	public void insertFaq(Map<String, Object> params) {
		dao.insert(namespace + ".insertFaq", params);
	}
	
	/**
     * FAQ 수정
     *
     * @author 정덕규
     * @since 2024.03.05
     */
	public int updateFaq(Map<String, Object> params) {
		return dao.update(namespace + ".updateFaq", params);
	}
	
	/**
     * FAQ 삭제
     *
     * @author 정덕규
     * @since 2024.03.05
     */
	public int deleteFaq(Map<String, Object> params) {
		return dao.delete(namespace + ".deleteFaq", params);
	}
	
	/**
     * FAQ 조회
     *
     * @author 정덕규
     * @since 2024.03.05
     */
	public Map<String, Object> selectFaq(Map<String, Object> params) {
		return dao.selectOne(namespace + ".selectFaq", params);
	}
 
}
