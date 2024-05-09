package www.api.smp.simple.web;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import www.api.mba.auth.service.AuthService;
import www.api.smp.simple.service.SimpleService;
import www.com.user.service.UserSessionManager;

/**
 * 조직관리1 정보 관리 Request를 Servlet으로 Mapping하고 ViewPage로 결과값을 리턴해주는 컨트롤 클래스
 *
 * @author 정지균
 * @since 2024.01.12
 */
@Controller
public class SimpleController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SimpleService simpleService;

    /**
     * 등록
     *
     * @author 정지균
     * @since 2024.01.12
     * @param MciRequest 저장할 데이터
     * @param Model 저장 결과
     * @return
     * @throws Exception
     */
    @RequestMapping("/api/simple/insertSimple")
    @ResponseBody
    public Map<String, Object> insertSimple(@RequestBody HashMap<String, Object> map) throws Exception
    {
        Map<String, Object> resultMap = new HashMap<>();
        simpleService.insertSimple(map);
        
        resultMap.put("msg", "성공적으로 등록되었습니다.");
        return resultMap;
    }
    
    /**
     * 수정
     *
     * @author 정지균
     * @since 2024.01.12
     * @param MciRequest 저장할 데이터
     * @param Model 저장 결과
     * @return
     * @throws Exception
     */
    @RequestMapping("/api/simple/updateSimple")
    @ResponseBody
    public Map<String, Object> updateSimple(@RequestBody HashMap<String, Object> map) throws Exception
    {
        Map<String, Object> resultMap = new HashMap<>();
        simpleService.updateSimple(map);
        
        resultMap.put("msg", "성공적으로 등록되었습니다.");
        return resultMap;
    }
    
    /**
     * 삭제
     *
     * @author 정지균
     * @since 2024.01.12
     * @param MciRequest 저장할 데이터
     * @param Model 저장 결과
     * @return
     * @throws Exception
     */
    @RequestMapping("/api/simple/deleteSimple")
    @ResponseBody
    public Map<String, Object> deleteSimple(@RequestBody HashMap<String, Object> map) throws Exception
    {
        Map<String, Object> resultMap = new HashMap<>();
        simpleService.deleteSimple(map);
        
        resultMap.put("msg", "성공적으로 등록되었습니다.");
        return resultMap;
    }
    
    
    

    /**
     * 목록 조회
     *
     * @author 정지균
     * @since 2024.01.12
     * @param MciRequest 조회 조건
     * @param Model 조회 결과
     * @return
     * @throws Exception
     */
    @RequestMapping("/api/simple/selectSimpleList")
    @ResponseBody
    public Map<String, Object> selectSimpleList(@RequestBody HashMap<String, Object> map) throws Exception
    {
        if (UserSessionManager.isUserLogined()) {   	
        }
    	
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", "성공");
        resultMap.put("result", simpleService.selectSimpleList(map));
        return resultMap;
    }
    
}