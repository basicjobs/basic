package www.api.com.sample.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import www.com.util.CommonDao;
import www.com.util.Sha256;

/**
 * 조직관리1 정보 관리 구현 클래스
 *
 * @author 정지균
 * @since 2024.01.12
 */
@Service
public class TemplateService  {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommonDao dao;

    
    private String namespace = "www.api.smp.template.Template";

    /**
     * 등록
     *
     * @author 정지균
     * @since 2024.01.12
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertTemplate(Map<String, Object> params) {	
        dao.insert(namespace+".insertTemplate", params);  		//마스터 인서트
    }
    
    /**
     * 수정
     *
     * @author 정지균
     * @since 2024.01.12
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateTemplate(Map<String, Object> params) {	
        dao.insert(namespace+".updateTemplate", params);  		//마스터 인서트
    }
    
    /**
     * 삭제
     *
     * @author 정지균
     * @since 2024.01.12
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteTemplate(Map<String, Object> params) {	
        dao.insert(namespace+".deleteTemplate", params);  		//마스터 인서트
    }
    
    /**
     * 심플 목록 조회
     *
     * @author 정지균
     * @since 2024.01.12
     * @param Map 조회 조건
     * @return List 조회 결과
     */
    public List<Map<String, Object>> selectTemplateList(Map<String, Object> params) {
        return dao.list(namespace + ".selectTemplateList", params);
    }
    
}