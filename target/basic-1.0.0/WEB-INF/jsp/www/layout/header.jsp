<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<c:set var="contextPath" value="${pageContext.request.contextPath }"/>

<head>
    <title>basic</title>  
    <!-- Meta -->
    <meta charset="utf-8">  
    <meta http-equiv="pragma" content="no-cache">
    <meta name="google" content="notranslate">
    <meta name="description" content="ktr ADMIN" /> 
    <meta name="keywords" content="ktr">
    <meta name="author" content="ktr" /> 
    <meta http-equiv="Content-Security-Policy" content="upgrade-insecure-requests">

    <!-- Bootstrap core CSS -->  
    <link href="${contextPath }/static/assets/css/bootstrap.min.css" rel="stylesheet">
    <!-- Custom styles for this template -->
    <link href="${contextPath }/static/assets/css/front.css" rel="stylesheet"> 
    <!-- Bootstrap core JavaScript
        ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="${contextPath }/static/assets/js/jquery.min.js"></script>
    <script src="${contextPath }/static/assets/js/bootstrap.min.js"></script>
    <script src="${contextPath }/static/assets/js/common.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery.form/4.3.0/jquery.form.min.js"></script>
    <script src="${contextPath }/static/assets/js/bootstrap-datepicker.js"></script>
    <link href="${contextPath }/static/assets/css/bootstrap-datepicker.css" rel="stylesheet">  

    <script> 
	$(document).ready(function() {
		$("#" + window.location.pathname.split('/')[3]).addClass("active"); 
	});

	function go(url) {
		const frm = document.getElementById("frm");
		frm.action = url;
		frm.submit();
	} 

</script>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Noto+Sans+KR&display=swap');
        body{
          font-family: 'Noto Sans KR', sans-serif;
        }
    </style>
</head>