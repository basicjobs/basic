package www.api.com.message.web;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.RandomStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import www.api.com.message.service.MessageService;
import www.api.mba.auth.service.AuthService;

/**
 * 메시지 관리 공통 클래스
 *
 *
 * @author 정지균
 * @since 2024.01.12
 */
@Controller
public class MessageController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MessageService messageService;
    
    /**
     * 메시지 발ㅇㅀ송
     *
     * @author 정지균
     * @since 2024.01.12
     * @param MciRequest 조회 조건
     * @param Model 조회 결과
     * @return
     * @throws Exception
     */
    @RequestMapping("/api/com/checkAuthCd")
    @ResponseBody
    public Map<String, Object> checkAuthCd(@RequestBody HashMap<String, Object> map) throws Exception
    {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", "성공");
        resultMap.put("result", messageService.checkAuthCd(map));
        
        return resultMap;
    }
    
    /**
     * 메이시 발송
     *
     * @author 정지균
     * @since 2024.01.12
     * @param MciRequest 조회 조건
     * @param Model 조회 결과
     * @return
     * @throws Exception
     */
    @RequestMapping("/api/com/sendMessage")
    @ResponseBody
    public Map<String, Object> sendMessage(@RequestBody HashMap<String, Object> map) throws Exception
    {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", "성공");
        
        String authCd = RandomStringUtils.randomNumeric(6);
        map.put("authCd", authCd);
        messageService.insertAuthCd(map);
        messageService.insertBizMsg(map);
        
        return resultMap;
    }
    
    /**
     * 메이시 발송
     *
     * @author 정지균
     * @since 2024.01.12
     * @param MciRequest 조회 조건
     * @param Model 조회 결과
     * @return
     * @throws Exception
     */
    @RequestMapping("/api/com/sendEmail")
    @ResponseBody
    public Map<String, Object> sendEmail(@RequestBody HashMap<String, Object> map) throws Exception
    {
        Map<String, Object> resultMap = new HashMap<>();
        resultMap.put("msg", "성공");
        
        messageService.sendEmail(map); 
        
        return resultMap;
    }  
    
}