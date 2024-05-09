package www.api.mba.auth.service.impl;

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
import www.api.mba.auth.service.AuthService;

/**
 * 조직관리1 정보 관리 구현 클래스
 *
 * @author 정지균
 * @since 2024.01.12
 */
@Service
public class AuthServiceImpl extends EgovAbstractServiceImpl implements AuthService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommonDao dao;

    
    private String namespace = "www.api.mba.auth.Auth";

    /**
     * 회원가입
     *
     * @author 정지균
     * @since 2024.01.12
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertMember(Map<String, Object> params) {	
    	Sha256 sha256 = new Sha256();
    	params.put("mberPw", sha256.encrypt(String.valueOf(params.get("mberPw"))));
    	
        dao.insert(namespace+".insertMember", params);  		//마스터 인서트
    }
    
    /**
     * 로그인
     *
     * @author 정지균
     * @since 2024.01.12
     * @param Map 조회 조건
     * @return List 조회 결과
     */
    @Override
    public Map<String, Object> selectLogin(Map<String, Object> params) {
    	Sha256 sha256 = new Sha256();
    	params.put("mberPw", sha256.encrypt(String.valueOf(params.get("mberPw")))); 
    	System.out.println("mberPw  = " + params.get("mberPw"));
        return dao.selectOne(namespace + ".selectLogin", params);
    }

    /** 
     * 아이디비밀번호찾기
     *
     * @author 정지균
     * @since 2024.01.12
     * @param Map 조회 조건
     * @return List 조회 결과
     */
    @Override
    public Map<String, Object> selectIdPwFind(Map<String, Object> params) {
        return dao.selectOne(namespace + ".selectIdPwFind", params);
    }
    
    /**
     * 아이디 중복체크 
     *
     * @author 정지균
     * @since 2024.01.12
     * @param Map 조회 조건
     * @return List 조회 결과
     */
    @Override
    public Map<String, Object> duplicateId(Map<String, Object> params) {
        return dao.selectOne(namespace + ".duplicateId", params);
    }
    
    
    /**
     * 아이디 찾기 
     *
     * @author 정지균
     * @since 2024.01.12
     * @param Map 조회 조건
     * @return List 조회 결과
     */
    @Override
    public Map<String, Object> findId(Map<String, Object> params) {
        return dao.selectOne(namespace + ".findId", params);
    }
    
    /**
     * 비밀번호 변경 
     *
     * @author 정지균
     * @since 2024.01.12
     * @param Map 조회 조건
     * @return List 조회 결과
     */
    @Override
    public int changePassword(Map<String, Object> params) {
    	Sha256 sha256 = new Sha256();
    	params.put("password", sha256.encrypt(String.valueOf(params.get("password"))));
        return dao.update(namespace + ".changePassword", params);
    }
    
}
