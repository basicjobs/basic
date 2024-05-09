package www.api.csv.customer.service;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

/**
 * 고객서비스 인터페이스 클래스
 *
 * @author 정덕규
 * @since 2024.03.05
 */
public interface CustomerService {
	
	public List<Map<String, Object>> selectNoticeList(Map<String, Object> params);
	
	public void uploadFiles(HttpServletRequest request, Map<String, Object> params) throws Exception;
	
	public void insertNotice(Map<String, Object> params);
	
	public int updateNotice(Map<String, Object> params);
	
	public int deleteNotice(Map<String, Object> params);
	
	public Map<String, Object> selectNotice(Map<String, Object> params);
	
	public List<Map<String, Object>> selectFaqList(Map<String, Object> params);
	
	public void insertFaq(Map<String, Object> params);
	
	public int updateFaq(Map<String, Object> params);
	
	public int deleteFaq(Map<String, Object> params);
	
	public Map<String, Object> selectFaq(Map<String, Object> params);
 
}
