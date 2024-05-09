package www.api.smp.simple.service;

import java.util.List;
import java.util.Map;


/**
 * 조직관리1 정보 관리 인터페이스 클래스
 *
 * @author 정지균
 * @since 2024.01.12
 */
public interface SimpleService { 

    public void insertSimple(Map<String, Object> params);
    
    public void updateSimple(Map<String, Object> params);
    
    public void deleteSimple(Map<String, Object> params);
    
    public List<Map<String, Object>> selectSimpleList(Map<String, Object> params);
    
}
