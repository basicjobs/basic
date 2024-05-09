 package www.csv.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.view.RedirectView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.List;

/**
 * @ClassName   : WebCsvController.java
 * @Description : 고객서비스관련 화면 호출 컨트롤러
 * @author 정덕규
 * @since 2024. 03. 05.
 * @version 1.0
 * @see
 * @Modification Information
 * <pre>
 *     since          author              description
 *  ===========    =============    ===========================
 *  2024. 03. 05.     정덕규                   최초 생성
 * </pre>
 */
@Controller
@RequestMapping("/csv")
public class WebCsvController {

    /**
     * 공지사항 리스트 페이지 호출
     */
    @RequestMapping(value="/customer/noticeList")
    public String noticeList(ModelMap model) throws Exception {
    	
    	/*******************************************************
        //기본값 처리 
        ******************************************************/

    	/*******************************************************
        //Service호출 
        ******************************************************/

        /*******************************************************
        //리턴값 처리
        ******************************************************/	

        
        return "csv/customer/noticeList";
    }
    
    /**
     * 공지사항 등록 페이지 호출
     */
    @RequestMapping(value="/customer/noticeModify")
    public String noticeModify(ModelMap model) throws Exception {
    	
    	/*******************************************************
        //기본값 처리 
        ******************************************************/

    	/*******************************************************
        //Service호출 
        ******************************************************/

        /*******************************************************
        //리턴값 처리
        ******************************************************/	

        
        return "csv/customer/noticeModify";
    }
    
    /**
     * faq 페이지 호출
     */
    @RequestMapping(value="/customer/faqList")
    public String faqList(ModelMap model) throws Exception {
    	
    	/*******************************************************
        //기본값 처리 
        ******************************************************/

    	/*******************************************************
        //Service호출 
        ******************************************************/

        /*******************************************************
        //리턴값 처리
        ******************************************************/	

        
        return "csv/customer/faqList";
    }

}