package www.api.com.message.service;

import java.util.Map;


/**
 * 조직관리1 정보 관리 인터페이스 클래스
 *
 * @author 정지균
 * @since 2024.01.12
 */
public interface MessageService {
	
    public Map<String, Object> sendMessage(Map<String, Object> params);
    
    public Map<String, Object> sendMessage2(); 


    public void insertAuthCd(Map<String, Object> params);
    
    public Map<String, Object> checkAuthCd(Map<String, Object> params);
    
    public void insertBizMsg(Map<String, Object> params);
    
    public void insertBizAuth(Map<String, Object> params);
    
    public void sendEmail(Map<String, Object> params);

}
