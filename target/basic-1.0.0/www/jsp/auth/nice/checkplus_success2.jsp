<%@ page language="java" contentType="text/html;charset=euc-kr" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@page import="java.net.URLEncoder"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Locale"%>
<%@page import="java.util.Iterator"%>
<%@page import="org.springframework.web.context.support.WebApplicationContextUtils"%>
<%@page import="org.springframework.context.ApplicationContext"%>
<%@page import="org.apache.commons.configuration.Configuration"%>
<%@page import="org.springframework.validation.Errors"%>
<%@page import="org.springframework.validation.ObjectError"%>
<%@page import="org.springframework.context.MessageSource"%>
<%@page import="org.springframework.web.servlet.support.RequestContext"%>
<%@page import="org.springframework.validation.BindingResult"%>
<spring:eval expression="@ConfigProperties['nicecompany.sitecode']" var="sSiteCode2"/>
<spring:eval expression="@ConfigProperties['nicecompany.password']" var="sSitePassword2"/>
<%	//���� �� ������� null�� ������ �κ��� ��������ڿ��� ���� �ٶ��ϴ�.
    NiceID.Check.CPClient niceCheck = new  NiceID.Check.CPClient();

	String sEncodeData = requestReplace(request.getParameter("EncodeData"), "encodeData");

	String sSiteCode = (String) pageContext.getAttribute("sSiteCode2"); // nice �ڵ�
	String sSitePassword = (String) pageContext.getAttribute("sSitePassword2"); // nice �н�����
 
    String sCipherTime = "";			// ��ȣȭ�� �ð� 
    String sRequestNumber = "";			// ��û ��ȣ
    String sResponseNumber = "";		// ���� ������ȣ
    String sAuthType = "";				// ���� ����
    String sName = "";					// ����
    String sDupInfo = "";				// �ߺ����� Ȯ�ΰ� (DI_64 byte)
    String sConnInfo = "";				// �������� Ȯ�ΰ� (CI_88 byte)
    String sBirthDate = "";				// �������(YYYYMMDD)
    String sGender = "";				// ����
    String sNationalI nfo = "";			// ��/�ܱ������� (���߰��̵� ����)
    String sBusinessno = "";            // ����ڵ�Ϲ�ȣ  
	String sMobileNo = "";				// �޴�����ȣ
	String sMobileCo = "";				// ��Ż�
    String sMessage = "";  
    String sPlainData = "";

    int iReturn = niceCheck.fnDecode(sSiteCode, sSitePassword, sEncodeData);

    if( iReturn == 0 )
    {
        sPlainData = niceCheck.getPlainData();
        sCipherTime = niceCheck.getCipherDateTime();
        
        // ����Ÿ�� �����մϴ�.
        java.util.HashMap mapresult = niceCheck.fnParse(sPlainData);
        
        sRequestNumber  = (String)mapresult.get("REQ_SEQ");
        sResponseNumber = (String)mapresult.get("RES_SEQ");
        sAuthType		= (String)mapresult.get("AUTH_TYPE");
        sBusinessno		= (String)mapresult.get("BUSINESSNO");
        
        String session_sRequestNumber = (String)session.getAttribute("REQ_SEQ");
        if(!sRequestNumber.equals(session_sRequestNumber))
        {
            sMessage = "���ǰ� ����ġ �����Դϴ�.";
            sResponseNumber = "";
            sAuthType = "";
        }
    }
    else if( iReturn == -1)
    {
        sMessage = "��ȣȭ �ý��� �����Դϴ�.";
    }    
    else if( iReturn == -4)
    {
        sMessage = "��ȣȭ ó�� �����Դϴ�.";
    }    
    else if( iReturn == -5)
    {
        sMessage = "��ȣȭ �ؽ� �����Դϴ�.";
    }    
    else if( iReturn == -6)
    {
        sMessage = "��ȣȭ ������ �����Դϴ�.";
    }    
    else if( iReturn == -9)
    {
        sMessage = "�Է� ������ �����Դϴ�.";
    }    
    else if( iReturn == -12)
    {
        sMessage = "����Ʈ �н����� �����Դϴ�.";
    }    
    else
    {
        sMessage = "�˼� ���� ���� �Դϴ�. iReturn : " + iReturn;
    }

%>
<%!

	public String requestReplace (String paramValue, String gubun) {

        String result = "";
        
        if (paramValue != null) {
        	
        	paramValue = paramValue.replaceAll("<", "&lt;").replaceAll(">", "&gt;");

        	paramValue = paramValue.replaceAll("\\*", "");
        	paramValue = paramValue.replaceAll("\\?", "");
        	paramValue = paramValue.replaceAll("\\[", "");
        	paramValue = paramValue.replaceAll("\\{", "");
        	paramValue = paramValue.replaceAll("\\(", "");
        	paramValue = paramValue.replaceAll("\\)", "");
        	paramValue = paramValue.replaceAll("\\^", "");
        	paramValue = paramValue.replaceAll("\\$", "");
        	paramValue = paramValue.replaceAll("'", "");
        	paramValue = paramValue.replaceAll("@", "");
        	paramValue = paramValue.replaceAll("%", "");
        	paramValue = paramValue.replaceAll(";", "");
        	paramValue = paramValue.replaceAll(":", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll("#", "");
        	paramValue = paramValue.replaceAll("--", "");
        	paramValue = paramValue.replaceAll("-", "");
        	paramValue = paramValue.replaceAll(",", "");
        	
        	if(gubun != "encodeData"){
        		paramValue = paramValue.replaceAll("\\+", "");
        		paramValue = paramValue.replaceAll("/", "");
            paramValue = paramValue.replaceAll("=", "");
        	}
        	
        	result = paramValue;
            
        }
        return result;
  }
%>

<html>
<head>
    <title>NICE������ - CheckPlus �Ƚɺ������� �׽�Ʈ</title>
	<script language="JavaScript">
		function end() {
			opener.document.getElementById('bizrno').value = '<%=sBusinessno%>';
			opener.checkBizrno();  
			self.close();
		}    
		
	</script>  
</head>
<body onload="end();">  
    <center>
    <p><p><p><p>
    ���������� �Ϸ� �Ǿ����ϴ�.<br>
    <table border=1>
        <tr>
            <td>��ȣȭ�� �ð�</td>
            <td><%= sCipherTime %> (YYMMDDHHMMSS)</td>
        </tr>
        <tr>
            <td>��û ��ȣ</td>
            <td><%= sRequestNumber %></td>
        </tr>            
        <tr>
            <td>NICE���� ��ȣ</td>
            <td><%= sResponseNumber %></td>
        </tr>            
        <tr>
            <td>��������</td>
            <td><%= sAuthType %></td>
        </tr>
		<tr>
            <td>����ڵ�Ϲ�ȣ</td>
            <td><%= sBusinessno %></td>
        </tr>
		<tr>
			<td colspan="2">���� �� ������� ���� ������ ���� ���� ���Ϲ����� �� �ֽ��ϴ�. <br>
			�Ϻ� ������� null�� ���ϵǴ� ��� ��������� �Ǵ� ���μ�(02-2122-4615)�� ���ǹٶ��ϴ�.</td>
		</tr>
		</table><br><br>        
    <%= sMessage %><br>
    </center>
</body>
</html>