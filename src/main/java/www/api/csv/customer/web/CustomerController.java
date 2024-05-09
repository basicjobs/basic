package www.api.csv.customer.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import www.api.csv.customer.service.CustomerService;

/**
 * 고객서비스 구현 컨트롤러
 *
 * @author 정덕규
 * @since 2024.03.05
 */
@Controller
public class CustomerController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CustomerService customerService;

    /**
     * 공지사항 리스트
     *
     * @author 정덕규
     * @since 2024.03.05
     * @param MciRequest 조회 조건
     * @param Model 조회 결과
     * @return
     * @throws Exception
     */
    @RequestMapping("/api/customer/noticeList")
    @ResponseBody
    public Map<String, Object> noticeList(@RequestBody HashMap<String, Object> map) throws Exception
    {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", "성공");
        resultMap.put("result", customerService.selectNoticeList(map));
        return resultMap;
    }
    
    /**
     * 파일 업로드
     *
     * @author 정덕규
     * @since 2024.03.05
     * @param MciRequest 저장할 데이터
     * @param Model 저장 결과
     * @return
     * @throws Exception
     */
    @RequestMapping("/api/customer/uploadFiles")
    @ResponseBody
    public Map<String, Object> uploadFiles(HttpServletRequest request, @RequestParam HashMap<String, Object> map) throws Exception
    {
        Map<String, Object> resultMap = new HashMap<>();
        customerService.uploadFiles(request, map);
        
        resultMap.put("msg", "성공적으로 업로드되었습니다.");
        return resultMap;
    }
    
    /**
     * 공지사항 등록
     *
     * @author 정덕규
     * @since 2024.03.05
     * @param MciRequest 저장할 데이터
     * @param Model 저장 결과
     * @return
     * @throws Exception
     */
    @RequestMapping("/api/customer/insertNotice")
    @ResponseBody
    public Map<String, Object> insertNotice(@RequestBody HashMap<String, Object> map) throws Exception
    {
        Map<String, Object> resultMap = new HashMap<>();
        customerService.insertNotice(map);
        
        resultMap.put("msg", "성공적으로 등록되었습니다.");
        return resultMap;
    }
    
    /**
     * 공지사항 수정
     *
     * @author 정덕규
     * @since 2024.03.05
     * @param MciRequest 저장할 데이터
     * @param Model 저장 결과
     * @return
     * @throws Exception
     */
    @RequestMapping("/api/customer/updateNotice")
    @ResponseBody
    public Map<String, Object> updateNotice(@RequestBody HashMap<String, Object> map) throws Exception
    {
        Map<String, Object> resultMap = new HashMap<>();
        customerService.updateNotice(map);
        
        resultMap.put("msg", "성공적으로 수정되었습니다.");
        return resultMap;
    }
    
    /**
     * 공지사항 삭제
     *
     * @author 정덕규
     * @since 2024.03.05
     * @param MciRequest 저장할 데이터
     * @param Model 저장 결과
     * @return
     * @throws Exception
     */
    @RequestMapping("/api/customer/deleteNotice")
    @ResponseBody
    public Map<String, Object> deleteNotice(@RequestBody HashMap<String, Object> map) throws Exception
    {
        Map<String, Object> resultMap = new HashMap<>();
        customerService.deleteNotice(map);
        
        resultMap.put("msg", "성공적으로 삭제되었습니다.");
        return resultMap;
    }
    
    /**
     * 공지사항 조회
     *
     * @author 정덕규
     * @since 2024.03.05
     * @param MciRequest 조회 조건
     * @param Model 조회 결과
     * @return
     * @throws Exception
     */
    @RequestMapping("/api/customer/selectNotice")
    @ResponseBody
    public Map<String, Object> selectNotice(@RequestBody HashMap<String, Object> map) throws Exception
    {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", "성공");
        resultMap.put("result", customerService.selectNotice(map));
        return resultMap;
    }
    
    /**
     * FAQ 리스트
     *
     * @author 정덕규
     * @since 2024.03.05
     * @param MciRequest 조회 조건
     * @param Model 조회 결과
     * @return
     * @throws Exception
     */
    @RequestMapping("/api/customer/faqList")
    @ResponseBody
    public Map<String, Object> faqList(@RequestBody HashMap<String, Object> map) throws Exception
    {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", "성공");
        resultMap.put("result", customerService.selectFaqList(map));
        return resultMap;
    }
    
    /**
     * FAQ 등록
     *
     * @author 정덕규
     * @since 2024.03.05
     * @param MciRequest 저장할 데이터
     * @param Model 저장 결과
     * @return
     * @throws Exception
     */
    @RequestMapping("/api/customer/insertFaq")
    @ResponseBody
    public Map<String, Object> insertFaq(@RequestBody HashMap<String, Object> map) throws Exception
    {
        Map<String, Object> resultMap = new HashMap<>();
        customerService.insertFaq(map);
        
        resultMap.put("msg", "성공적으로 등록되었습니다.");
        return resultMap;
    }
    
    /**
     * FAQ 수정
     *
     * @author 정덕규
     * @since 2024.03.05
     * @param MciRequest 저장할 데이터
     * @param Model 저장 결과
     * @return
     * @throws Exception
     */
    @RequestMapping("/api/customer/updateFaq")
    @ResponseBody
    public Map<String, Object> updateFaq(@RequestBody HashMap<String, Object> map) throws Exception
    {
        Map<String, Object> resultMap = new HashMap<>();
        customerService.updateFaq(map);
        
        resultMap.put("msg", "성공적으로 수정되었습니다.");
        return resultMap;
    }
    
    /**
     * FAQ 삭제
     *
     * @author 정덕규
     * @since 2024.03.05
     * @param MciRequest 저장할 데이터
     * @param Model 저장 결과
     * @return
     * @throws Exception
     */
    @RequestMapping("/api/customer/deleteFaq")
    @ResponseBody
    public Map<String, Object> deleteFaq(@RequestBody HashMap<String, Object> map) throws Exception
    {
        Map<String, Object> resultMap = new HashMap<>();
        customerService.deleteFaq(map);
        
        resultMap.put("msg", "성공적으로 삭제되었습니다.");
        return resultMap;
    }
    
    /**
     * FAQ 조회
     *
     * @author 정덕규
     * @since 2024.03.05
     * @param MciRequest 조회 조건
     * @param Model 조회 결과
     * @return
     * @throws Exception
     */
    @RequestMapping("/api/customer/selectFaq")
    @ResponseBody
    public Map<String, Object> selectFaq(@RequestBody HashMap<String, Object> map) throws Exception
    {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", "성공");
        resultMap.put("result", customerService.selectFaq(map));
        return resultMap;
    }
    
}