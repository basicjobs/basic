/** 
 * core.ifw.utl.MailUtil.java 
 */ 
package www.com.util;
 
import java.io.IOException;
import java.util.Date;
import java.util.Properties; 
import java.util.logging.Logger;

import javax.activation.CommandMap;
import javax.activation.MailcapCommandMap;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl; 
 
/** 
 * <pre> 
 *	MailUtil -  
 * <p><b>NOTE : </b></p> 
 * </pre> 
 *  
 * @author	jang.sh <jsh@inbus.co.kr> 
 * @since	2016. 11. 17. 
 * @version	1.0 
 * @see		{@link } 
 *  
 * <pre> 
 * == Modification Information == 
 * Date		Modifier		Comment 
 * ==================================================== 
 * 2016. 11. 17.	jang.sh		Initial Created. 
 * ==================================================== 
 * </pre> 
 * 
 * Copyright 1998-2016 By INBUS Co,Ltd. All rights reserved. 
 */ 
public class MailUtil {
 
	/** 
	 * <pre> 
	 * 메일을 전송한다. 
	 * </pre> 
	 * 
	 * @param sender 발신 메일주소 
	 * @param title 메일 제목 
	 * @param contents 단순 텍스트 또는 html으로 구성된 메일 내용. 
	 * @param receivers 수신자 목록. 배열로 수신자 여러명 지정 가능 
	 * @param cc 참조 목록. 배열로 여러명의 참조 지정 가능 
	 * @param isHtml 메일 내용이 html이면 true를, 아니면 false 입력. html이면 인코딩을 거쳐서 전송 
	 * @throws Exception 예외발생 시 
	 */
	public static String mailSend(String sender, String title, String contents, String[] receivers, String[] cc, boolean isHtml) { 
		 
		String isSuccess = "error"; 

		String mailHost = "mail.ktr.or.kr";				// 메일서버 호스트
		String mailPort = "25"; 				// 메일서버 포트
		String mailProtocol = "smtp";	// 메일서버 프로토콜
		String mailSmtpAuth = "true";			// 메일 인증 여부
		  
		final String user = "ktrmaster@ktr.or.kr";		// 사용자계정 
		final String pass = "!@#ktr123master#@!";			// 사용자계정 비밀번호		
		try {   

			JavaMailSenderImpl mailSender = new JavaMailSenderImpl(); 
			mailSender.setHost(mailHost);						// 메일서버 호스트 
			mailSender.setPort(Integer.parseInt(mailPort));		// 메일서버 포트
			mailSender.setUsername(user);						// 메일 인증이 필요할 경우 메일 사용자 계정, (회사메일서버는 개인 인증을 거쳐야 합니다.)
			mailSender.setPassword(pass);					// 메일 인증이 필요할 경우 메일 사용자 계정의 비밀번호, (회사메일서버는 개인 인증을 거쳐야 합니다.)
			Properties mailProps = new Properties(); 
			mailProps.setProperty("mail.transport.protocol", mailProtocol);	// 메일 전송 프로토콜
			mailProps.setProperty("mail.smtp.auth", mailSmtpAuth);			// smtp 인증 필수
			mailSender.setJavaMailProperties(mailProps);
			
			Message message = mailSender.createMimeMessage();
			
			// 발신 메일
			InternetAddress senderAddress = new InternetAddress(sender);
			message.setFrom(senderAddress);
			
			// 수신 메일주소 리스트
			InternetAddress[] recipients = new InternetAddress[receivers.length];

			for (int idx = 0; idx < recipients.length; idx++) {
				recipients[idx] = new InternetAddress(receivers[idx]);
			}

			message.setRecipients(Message.RecipientType.TO, recipients);

			// 참조리스트
			/*
			if ((cc != null) && (cc.length > 1)) {
				InternetAddress[] ccArr = new InternetAddress[cc.length];

				for (int idx = 0; idx < cc.length; idx++) {
					ccArr[idx] = new InternetAddress(cc[idx]);
				}

				message.setRecipients(Message.RecipientType.CC, ccArr);

			}
            */
			// 메일 제목
			message.setSubject(title == null ? "" : title);

			// 발송일시
			message.setSentDate(new Date());
			
			Multipart multipart = new MimeMultipart(); 
			 
			// 메일 내용(텍스트/html) 
			BodyPart msgBodyPart = new MimeBodyPart(); 
			 
			if (isHtml) { 
				msgBodyPart.setContent(contents, "text/html; charset=utf-8"); 
			} else { 
				msgBodyPart.setContent(contents, "text/plain; charset=utf-8"); 
			} 
			 
			multipart.addBodyPart(msgBodyPart); 
			
			MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap(); 
			mc.addMailcap("text/html;; x-java-content-handler=com.sun.mail.handlers.text_html"); 
			mc.addMailcap("text/xml;; x-java-content-handler=com.sun.mail.handlers.text_xml"); 
			mc.addMailcap("text/plain;; x-java-content-handler=com.sun.mail.handlers.text_plain"); 
			mc.addMailcap("multipart/*;; x-java-content-handler=com.sun.mail.handlers.multipart_mixed"); 
			mc.addMailcap("message/rfc822;; x-java-content-handler=com.sun.mail.handlers.message_rfc822"); 
			CommandMap.setDefaultCommandMap(mc);
			
			if (isHtml) { 
				message.setContent(contents, "text/html; charset=utf-8"); 
			} else { 
				message.setContent(contents, "text/plain; charset=utf-8"); 
			} 
			
			mailSender.send((MimeMessage) message); 
 
			isSuccess = "success"; 
			 
		} catch (Exception e) { 
			e.printStackTrace();
			isSuccess = "error"; 
		}
		
		return isSuccess; 
		 
	}
	
	public String SendMail(String sender, String title, String contents, String[] receivers, String[] cc, boolean isHtml, String[] attachFiles) 
		   throws Exception
    {
		String isSuccess = "error"; 
		String mailHost = CoreProperties.getProperty("email.host"); 				// 메일서버 호스트
		String mailPort = CoreProperties.getProperty("email.port"); 				// 메일서버 포트
		String mailProtocol = CoreProperties.getProperty("email.protocol");		// 메일서버 프로토콜
		String mailSmtpAuth = CoreProperties.getProperty("email.auth");			// 메일 인증 여부
		  
		final String user = CoreProperties.getProperty("email.auth.id");			// 사용자계정 
		final String pass = CoreProperties.getProperty("email.auth.pw");			// 사용자계정 비밀번호	
		
        try{
			Properties props = new Properties();
	                   props.setProperty("mail.transport.protocol", mailProtocol);
	                   props.setProperty("mail.host", mailHost);
	                   props.put("mail.smtp.auth", mailSmtpAuth);
	                   props.put("mail.smtp.port", mailPort);
	                   props.put("mail.smtp.socketFactory.port", mailPort);
	                   //props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
	                   props.put("mail.smtp.socketFactory.fallback", "false");
	                   props.put("mail.smtp.starttls.enable","true");
	                   props.setProperty("mail.smtp.quitwait", "false");
	        
	        Authenticator auth = new Authenticator(){
	        	protected PasswordAuthentication getPasswordAuthentication(){
	        		return new PasswordAuthentication(user,pass);
	        	}
	        };
	        
	        Session     session = Session.getDefaultInstance(props, auth);
	        MimeMessage message = new MimeMessage(session);
	                    message.setSubject(title);
	        //message.setRecipient(Message.RecipientType.TO, new InternetAddress(receivers[0]));
	                    
	        // set the from and to address
	        InternetAddress addressFrom = new InternetAddress(sender);
	        message.setFrom(addressFrom);
	  
	        InternetAddress[] addressTo = new InternetAddress[receivers.length];
	        for (int i = 0; i < receivers.length; i++) {
	            addressTo[i] = new InternetAddress(receivers[i]);
	        }
	        //addressTo[receivers.length] = new InternetAddress("ktrmaster@ktr.or.kr");
	        
	        message.setRecipients(Message.RecipientType.TO, addressTo);
	        
	        Multipart    mp   = new MimeMultipart();
	        MimeBodyPart mbp1 = new MimeBodyPart();
	                     mbp1.setContent(contents, "text/html; charset=UTF-8");
	                     //mbp1.setText(contents);
	                     mp.addBodyPart(mbp1);
	       // /* 	        
           // adds attachments
           if (attachFiles != null && attachFiles.length > 0) {
                 for (String filePath : attachFiles) {
                     MimeBodyPart attachPart = new MimeBodyPart();
                     try {
                    	 attachPart.attachFile(filePath);
                     } catch (IOException ex) {
                             ex.printStackTrace();
                     }
                     mp.addBodyPart(attachPart);
                 }
            } 
            //*/             
	        MailcapCommandMap mc = (MailcapCommandMap) CommandMap.getDefaultCommandMap();
	                          mc.addMailcap("text/html;; x-java-content-handler=comsun.mail.handlers.text_html");
	                          mc.addMailcap("text/xml;; x-java-content-handler=comsun.mail.handlers.text_xml");
	                          mc.addMailcap("text/plain;; x-java-content-handler=comsun.mail.handlers.text_plain");
	                          mc.addMailcap("multipart/*;; x-java-content-handler=comsun.mail.handlers.multipart_mixed");
	                          mc.addMailcap("message/rfc822;; x-java-content-handler=comsun.mail.handlers.message_rfc822");
	        CommandMap.setDefaultCommandMap(mc);
	        
	        message.setContent(mp);
	        Transport.send(message);
	        
			isSuccess = "success";
        }catch(MessagingException e){
			e.printStackTrace();
			isSuccess = "error"; 
        }
		return isSuccess; 
	}
	
	public String sendMail(String title, String contents, String mailTo) 
			   throws Exception
	    {
			String isSuccess = "error"; 
			String mailHost = CoreProperties.getProperty("email.host"); 				// 메일서버 호스트
			String mailPort = CoreProperties.getProperty("email.port"); 				// 메일서버 포트
			String mailProtocol = CoreProperties.getProperty("email.protocol");		// 메일서버 프로토콜
			String mailSmtpAuth = CoreProperties.getProperty("email.auth");			// 메일 인증 여부
			  
			final String user = CoreProperties.getProperty("email.auth.id");			// 사용자계정 
			final String pass = CoreProperties.getProperty("email.auth.pw");			// 사용자계정 비밀번호	
			try {
	    	Properties props = System.getProperties(); //SMTP 정보를 담는 객체

	        props.put("mail.smtp.host", mailHost);
	        props.put("mail.smtp.port", mailPort);
	        props.put("mail.smtp.auth", "true");
	        props.put("mail.smtp.ssl.enable", "false");
//	      props.put("mail.smtp.ssl.trust", host);
	        
	        //SMTP 인증
	        Session session = Session.getDefaultInstance(props, new Authenticator() {
	            String un = user;
	            String pw = pass; 

	            protected PasswordAuthentication getPasswordAuthentication(){
	                return new PasswordAuthentication(un, pw);
	            }
	        });
	        session.setDebug(true);
			
	        //매일 정보를 담는 각체
        
            Message mimeMessage = new MimeMessage(session);
            mimeMessage.setFrom(new InternetAddress("ktrmaster@ktr.or.kr"));
            mimeMessage.setRecipient(Message.RecipientType.TO, new InternetAddress(mailTo));

            //      mimeMessage.setRecipient(Message.RecipientType.CC, new InternetAddress(mailTo)); 
            //      mimeMessage.setRecipient(Message.RecipientType.BCC, new InternetAddress(mailTo));

            mimeMessage.setSubject(title);
            mimeMessage.setContent(contents, "text/html; charset=UTF-8");
            //mimeMessage.setText(content);
            Transport.send(mimeMessage);

            isSuccess = "true";
	        }catch(MessagingException e){
				e.printStackTrace();
				isSuccess = "error";
	        }
			return isSuccess; 
		}
	 
} 
