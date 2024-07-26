package www.api.smp.simple.service;

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
import www.api.smp.simple.service.SimpleService;

/**
 * 조직관리1 정보 관리 구현 클래스
 *
 * @author 정지균
 * @since 2024.01.12
 */
@Service
public class SimpleService  {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommonDao dao;

    
    private String namespace = "www.api.smp.simple.Simple";

    /**
     * 등록
     *
     * @author 정지균
     * @since 2024.01.12
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertSimple(Map<String, Object> params) {	
    	
    	params.put("mberPw", sha256.encrypt(String.valueOf(params.get("mberPw"))));
    	
        dao.insert(namespace+".insertSimple", params);  		//마스터 인서트
    }
    
    /**
     * 수정
     *
     * @author 정지균
     * @since 2024.01.12
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateSimple(Map<String, Object> params) {	
    	
    	params.put("mberPw", sha256.encrypt(String.valueOf(params.get("mberPw"))));
    	
        dao.insert(namespace+".updateSimple", params);  		//마스터 인서트
    }
    
    /**
     * 삭제
     *
     * @author 정지균
     * @since 2024.01.12
     */
    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteSimple(Map<String, Object> params) {	
    	
    	params.put("mberPw", sha256.encrypt(String.valueOf(params.get("mberPw"))));
    	
        dao.insert(namespace+".deleteSimple", params);  		//마스터 인서트
    }
    
    /**
     * 심플 목록 조회
     *
     * @author 정지균
     * @since 2024.01.12
     * @param Map 조회 조건
     * @return List 조회 결과
     */
    public List<Map<String, Object>> selectSimpleList(Map<String, Object> params) {
        return dao.list(namespace + ".selectSimpleList", params);
    }
    
}