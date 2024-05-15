package www.api.com.sample.web;

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
import www.api.com.sample.service.TemplateService;
import www.com.user.service.UserSessionManager;

/**
 * 조직관리1 정보 관리 Request를 Servlet으로 Mapping하고 ViewPage로 결과값을 리턴해주는 컨트롤 클래스
 *
 * @author 정지균
 * @since 2024.01.12
 */
@Controller
public class TemplateController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private TemplateService templateService;

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
    @RequestMapping("/api/template/insertTemplate")
    @ResponseBody
    public Map<String, Object> insertTemplate(@RequestBody HashMap<String, Object> map) throws Exception
    {
        Map<String, Object> resultMap = new HashMap<>();
        templateService.insertTemplate(map);
        
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
    @RequestMapping("/api/template/updateTemplate")
    @ResponseBody
    public Map<String, Object> updateTemplate(@RequestBody HashMap<String, Object> map) throws Exception
    {
        Map<String, Object> resultMap = new HashMap<>();
        templateService.updateTemplate(map);
        
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
    @RequestMapping("/api/template/deleteTemplate")
    @ResponseBody
    public Map<String, Object> deleteTemplate(@RequestBody HashMap<String, Object> map) throws Exception
    {
        Map<String, Object> resultMap = new HashMap<>();
        templateService.deleteTemplate(map);
        
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
    @RequestMapping("/api/template/selectTemplateList")
    @ResponseBody
    public Map<String, Object> selectTemplateList(@RequestBody HashMap<String, Object> map) throws Exception
    {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", "성공");
        resultMap.put("result", templateService.selectTemplateList(map));
        return resultMap;
    }
    
}