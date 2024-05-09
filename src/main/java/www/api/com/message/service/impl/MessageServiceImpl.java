package www.api.com.message.service.impl;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import egovframework.rte.fdl.cmmn.EgovAbstractServiceImpl;
import www.com.util.CommonDao;
import www.com.util.MailUtil;
import www.api.com.message.service.MessageService;

/**
 * 메시지 관리 구현 클래스
 *
 * @author 정지균
 * @since 2024.01.12
 */
@Service
public class MessageServiceImpl extends EgovAbstractServiceImpl implements MessageService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private CommonDao dao;

    
    private String namespace = "www.api.com.message.Message";

    @Value("#{ConfigProperties['email.host']}")
    private String emailHost;
    
    @Value("#{ConfigProperties['email.port']}")
    private String emailPort;
    
    @Value("#{ConfigProperties['email.auth.id']}")
    private String emailAuthId;
    
    @Value("#{ConfigProperties['email.auth.pw']}")
    private String emailAuthPw;
    
    /**
     * 로그인
     *
     * @author 정지균
     * @since 2024.01.12
     * @param Map 조회 조건
     * @return List 조회 결과
     */ 
    @Override
    public Map<String, Object> sendMessage(Map<String, Object> params) {
    	
    	 // 0. 결과값을 담을 객체를 생성합니다.
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // 1. 타임아웃 설정시 HttpComponentsClientHttpRequestFactory 객체를 생성합니다.
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            factory.setConnectTimeout(5000); // 타임아웃 설정 5초
            factory.setReadTimeout(5000); // 타임아웃 설정 5초
 
            //Apache HttpComponents : 각 호스트(IP와 Port의 조합)당 커넥션 풀에 생성가능한 커넥션 수
            HttpClient httpClient = HttpClientBuilder.create()
                                                    .setMaxConnTotal(50)//최대 커넥션 수
                                                    .setMaxConnPerRoute(20).build();
            factory.setHttpClient(httpClient);

            // 2. RestTemplate 객체를 생성합니다.
            RestTemplate restTemplate = new RestTemplate(factory);

			MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
			String authCd = String.valueOf(params.get("authCd"));
			parameters.add("DEST_PHONE", String.valueOf(params.get("destPhone")));
			parameters.add("MSG_BODY", "[한국화학융합시험연구원(KTR)]\n인증번호[" + authCd + "]를 입력해주세요.");
			
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity formEntity = new HttpEntity<>(parameters, headers);
            
            //String url = "http://localhost:9040/core/erp/eis/EISA0001_GW_CHART_SEARCH77.do";
            
            String url = "http://192.168.223.93:9040/core/erp/eis/EISA0001_GW_CHART_SEARCH77.do";
            String result = restTemplate.postForObject(url, formEntity, String.class);
            //2. Parser
            JSONParser jsonParser = new JSONParser();
            
            //3. To Object
            Object obj = jsonParser.parse(result);
            
            //4. To JsonObject
            JSONObject jsonObj = (JSONObject) obj;
            // 반환받은 실제 데이터 정보
            resultMap.put("body", jsonObj);

        } catch (Exception e) {
            e.printStackTrace();
        }// end catch

        return resultMap;
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
    public Map<String, Object> sendMessage2() {
    	
    	 // 0. 결과값을 담을 객체를 생성합니다.
        HashMap<String, Object> resultMap = new HashMap<String, Object>();
        try {
            // 1. 타임아웃 설정시 HttpComponentsClientHttpRequestFactory 객체를 생성합니다.
            HttpComponentsClientHttpRequestFactory factory = new HttpComponentsClientHttpRequestFactory();
            factory.setConnectTimeout(5000); // 타임아웃 설정 5초
            factory.setReadTimeout(5000); // 타임아웃 설정 5초
 
            //Apache HttpComponents : 각 호스트(IP와 Port의 조합)당 커넥션 풀에 생성가능한 커넥션 수
            HttpClient httpClient = HttpClientBuilder.create()
                                                    .setMaxConnTotal(50)//최대 커넥션 수
                                                    .setMaxConnPerRoute(20).build();
            factory.setHttpClient(httpClient);

            // 2. RestTemplate 객체를 생성합니다.
            RestTemplate restTemplate = new RestTemplate(factory);

			MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
			String authCd = "222332";
			parameters.add("DEST_PHONE", "01071120423");  
			parameters.add("MSG_BODY", "[한국화학융합시험연구원(KTR)]\n인증번호[" + authCd + "]를 입력해주세요.");
			
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
            HttpEntity formEntity = new HttpEntity<>(parameters, headers);
            
            //String url = "http://localhost:9040/core/erp/eis/EISA0001_GW_CHART_SEARCH77.do";
            
            String url = "http://192.168.223.93:9040/core/erp/eis/EISA0001_GW_CHART_SEARCH77.do";
            String result = restTemplate.postForObject(url, formEntity, String.class);
            //2. Parser
            JSONParser jsonParser = new JSONParser();
            
            //3. To Object
            Object obj = jsonParser.parse(result);
            
            //4. To JsonObject
            JSONObject jsonObj = (JSONObject) obj;
            // 반환받은 실제 데이터 정보
            resultMap.put("body", jsonObj);

        } catch (Exception e) {
            e.printStackTrace();
        }// end catch

        return resultMap;
    }
    
    /**
     * 인증번호 생성
     *
     * @author 정지균
     * @since 2024.01.12
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertAuthCd(Map<String, Object> params) {
    	String authCd = RandomStringUtils.randomNumeric(6);
    	params.put("authCd", authCd);
        dao.insert(namespace+".insertAuthCd", params);  		//마스터 인서트
    }
    
    /**
     * 인증번홓 체크
     *
     * @author 정지균
     * @since 2024.01.12
     * @param Map 조회 조건
     * @return List 조회 결과
     */
    @Override
    public Map<String, Object> checkAuthCd(Map<String, Object> params) {
        return dao.selectOne(namespace + ".checkAuthCd", params);
    }
    
    
    /**
     * 인증번호 생성
     *
     * @author 정지균
     * @since 2024.01.12
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertBizMsg(Map<String, Object> params) {
    	
		String authCd = String.valueOf(params.get("authCd"));
		String msgBody = "[한국화학융합시험연구원(KTR)]\n인증번호[" + authCd + "]를 입력해주세요.";
		
		params.put("msgBody", msgBody);
		params.put("sendPhone", "0221640011");
		params.put("templateCode", "TMPL_CODE_069");
		
		params.put("emplNumb", "0000");
		params.put("sendTime", "2023-02-26");
		params.put("msgType", "MMS");
    	
        dao.insert(namespace+".insertBizMsg", params);  		//마스터 인서트
    }
    
    
    /**
     * 인증번호 생성
     *
     * @author 정지균
     * @since 2024.01.12
     */
    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void insertBizAuth(Map<String, Object> params) {
    	
		String authCd = String.valueOf(params.get("authCd"));
		String msgBody = "[한국화학융합시험연구원(KTR)]\n인증번호[" + authCd + "]를 입력해주세요.";
		params.put("msgBody", msgBody);
		params.put("sendPhone", "0221640011");
		params.put("sendTime", "2023-02-26");
		params.put("emplNumb", "0000");
		params.put("templateCode", "TMPL_CODE_069");
		params.put("msgType", "MMS");
		params.put("msgBody", msgBody);
    	
        dao.insert(namespace+".insertBizMsg", params);  		//마스터 인서트
    }

    /**
     * 메일전송
     *
     * @author 정지균
     * @since 2024.01.12
     */
    @Override 
    @Transactional(propagation = Propagation.REQUIRED)
    public void sendEmail(Map<String, Object> params) {
    	MailUtil mail = new MailUtil();
    	String[] receivers = new String[1];  
    	String sender = "ktrmaster@ktr.or.kr";
    	String title = params.get("title").toString();
    	String content = params.get("content").toString();
    	
    	receivers[0]=params.get("mailTo").toString(); 
       	String[] cos = new String[1]; 
       	cos[0]="basicjobs@naver.com";
    	
       	mail.mailSend(sender,title,content,receivers, cos, false);
    }

}