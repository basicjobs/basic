<%@ page  contentType = "text/html;charset=ksc5601"%>
<%@ page import ="java.util.*,java.text.SimpleDateFormat"%>
<%@include file="/WEB-INF/jsp/www/com/inc/common_top.jsp" %>
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

<%
        //��¥ ����
        Calendar today = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String day = sdf.format(today.getTime());

        java.util.Random ran = new Random();
        //���� ���� ����
        int numLength = 6;
        String randomStr = "";

        for (int i = 0; i < numLength; i++) {
            //0 ~ 9 ���� ���� ����
            randomStr += ran.nextInt(10);
        }

		//reqNum�� �ִ� 40byte ���� ��� ����
        String reqNum = "123456789";   //sample �������� result ��������  �������� ������ ��������� ��ȣȭ �� ����
		String certDate=day;

		String type = StringUtil.nullConvert(request.getParameter("type"));
		
		if(!StringUtil.isEmpty(type)) {
			session.setAttribute("type", type);
		}
		
		String exVar = "0000000000000000"; // ��ȣȭ�� �ӽ��ʵ�
		String certGb = "H"; // �������� H: �޴���
		String addVar = "";
		
		String domain = request.getRequestURL().toString().replace(request.getRequestURI(),"");
		
		String retUrl = domain.replace("http", "32http") + "/www/jsp/auth/nice/pcc_V3_popup_seed_v2.jsp";
		
	    //01. ��ȣȭ ��� ����
		com.sci.v2.pccv2.secu.SciSecuManager seed  = new com.sci.v2.pccv2.secu.SciSecuManager();
		
		//02. 1�� ��ȣȭ
		String encStr = "";
		String reqInfo      = id+"^"+srvNo+"^"+reqNum+"^"+certDate+"^"+certGb+"^"+addVar+"^"+exVar;  // ������ ��ȣȭ

		seed.setInfoPublic(id, sciAuthKey);  //bizsiren.com > ȸ�������� �α����� Ȯ��. 

		encStr               = seed.getEncPublic(reqInfo);
		
		//03. ������ ���� �� ����
		com.sci.v2.pccv2.secu.hmac.SciHmac hmac = new com.sci.v2.pccv2.secu.hmac.SciHmac();
		String hmacMsg  = seed.getEncReq(encStr,"HMAC");

		//03. 2�� ��ȣȭ
		reqInfo  = seed.getEncPublic(encStr + "^" + hmacMsg + "^" + "0000000000000000");  //2����ȣȭ

		//04. ȸ���� ID ó���� ���� ��ȣȭ
		reqInfo = seed.EncPublic(reqInfo + "^" + id + "^"  + "00000000");

%>

<html>
    <head>
        <title>����ſ������� ���νǸ�Ȯ�μ���  �׽�Ʈ</title>
        <script language=javascript>
			function openPCCWindow(){ 
				//â�� �����Ҷ� ũ�� �� �ͽ��÷ξ� ���� �� �׽�Ʈ �Ͻñ� �ٶ��ϴ�.
				document.reqPCCForm.action = 'https://pcc.siren24.com/pcc_V3/jsp/pcc_V3_j10_v2.jsp';
				document.reqPCCForm.submit();
			}
		</script>
    </head>
    <body onload="return openPCCWindow();">
		<!-- ���νǸ�Ȯ�μ��� ��û form --------------------------->
		<form name="reqPCCForm" method="post">
			<input type="hidden" name="reqInfo"    value = "<%=reqInfo%>">
			<input type="hidden" name="retUrl"      value = "<%=retUrl%>">
			<input type="hidden" name="verSion"	value = "2">				 <!--��� ��������-->
		</form>
	</body>
</html>