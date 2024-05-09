<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
    <body>
    <div id="loginDiv" class="col-sm-5">
        <form name="frm" id="frm">
        <input type="hidden" name="loginType" value="n" >   
        <h1 class="h3 mb-3 fw-normal">로그인</h1>
        <div class="form-floating">
            <input type="text" class="form-control" name="mberId" id="mberId" placeholder="아이디">
            <label for="id"></label>
        </div>  
        <div class="form-floating">  
            <input type="password" class="form-control" name="mberPw" id="mberPw" placeholder="비밀번호">
            <label for="password"></label>
        </div>      
        <c:if test="${map.failYn eq 'Y'}">  
        	<p style="color:red;">입력하신 회원정보를 찾을 수 없습니다.</p>
        </c:if>  
        <div class="row" style="padding:5px;">
            <div class="col-md-12">
                <button class="btn btn-primary w-100 py-2" type="button" onClick="goLogin()">Login</button>
            </div>  
	    </div>
        <div class="row" style="padding:5px;">
	  	    <div class="col-md-12">
                <button class="btn btn-primary w-100 py-2" type="button" onClick="goLink('/mba/auth/join')">회원가입</button>
            </div>
	    </div>
        <div class="row" style="padding:5px;"> 
	  	    <div class="col-md-12">
                <button class="btn btn-primary w-100 py-2" type="button" onClick="goLink('/mba/auth/idFind')">아이디 찾기</button>
            </div> 
	    </div>
        <div class="row" style="padding:5px;">
	  	    <div class="col-md-12">
                <button class="btn btn-primary w-100 py-2" type="button" onClick="goLink('/mba/auth/pwFind')">비밀번호 찾기</button>
            </div>
	    </div>
    </form>
    </main>
    </div>
    <div id="authDiv" style="display:none;"> 
    	<h1 class="h3 mb-3 fw-normal">2단계 인증을 진행해 주세요</h1>
    	<p id="cryalTelnoTxt"></p>
    	<p id="timerTxt"></p>
	    <div id="loginDiv" class="col-sm-5">
	        <h1 class="h3 mb-3 fw-normal">인증번호</h1>
	        <div class="form-floating">
	            <input type="text" class="form-control" name="authCd" id="authCd" placeholder="인증번호">
	            <button type="button" id="authBtn" class="btn btn-primary" onClick="sendAuthCd()">요청</button>
	        </div>
	    </div>
        <div class="row" style="padding:5px;">
            <div class="col-md-12"> 
                <button class="btn btn-primary w-100 py-2" type="button" onClick="checkAuthCd()">확인</button>
            </div>  
	    </div>
    </div>   

    <script>
    
		function goLogin(){
	
		    if ($("#mberId").val() == "") {
		        alert("아이디를 입력해주세요.");
		        return; 
		    }
/* 		    
			if(!checkId($("#mberId").val())){
				alert("6-12자의 영문, 숫자, 기호(- _ )만 사용 가능합니다.");
				$("#mberId").focus();
				return;
			} */
			    
		    if ($("#mberPw").val() == "") {
		        alert("비밀번호를 입력해주세요.");
		        return;
		    }
		    
			if(!checkPw($("#mberPw").val())){
				alert("8자리 이상, 영문 대/소문자, 특수문자, 숫자를 조합해서 입력해주세요.");
				$("#mberPw").focus();
				return; 
				
			} 
			
		    var sendData = {}; 
			sendData.mberId = $("#mberId").val();
			sendData.mberPw = $("#mberPw").val();
		    
/*  			$.ajax({ 
				url: "/api/auth/selectLogin",
		        type: "post",
		        contentType: "application/json",
		        dataType :'json',
		 		data : JSON.stringify(sendData),
		        success: function (map){  
		        	console.log(map);  
		        },
		        error: function(request, status, error){
		            
		        }
			});     
			 */
	        $("#frm").attr("method","post");
	        $("#frm").attr("action", "/com/auth/login");  
	        $("#frm").submit();
		}
		
		function sendAuthCd(){
		    var sendData = {};
			sendData.destPhone = $("#cryalTelno").val();
		    
			$.ajax({
		        url: "/api/com/sendMessage",
		        type: "post",
		        contentType: "application/json",
		        dataType :'json',
		 		data : JSON.stringify(sendData),
		        success: function (map){
		        	console.log(map);
		        	TimerStart();
		        },  
		        error: function(request, status, error){
		            
		        }
			});  
		}
		
		function checkAuthCd(){
		    var sendData = {}; 
			sendData.destPhone = $("#cryalTelno").val();
			sendData.authCd = $("#authCd").val();
		    
			$.ajax({
		        url: "/api/com/checkAuthCd", 
		        type: "post",
		        contentType: "application/json",
		        dataType :'json',
		 		data : JSON.stringify(sendData),
		        success: function (map){
		        	console.log(map);
		        	var result = map.result;
		        	if (result != null) {   
		        		$("#mberPw").attr("type", "password");
		        		console.log("mberId = " + $("#mberId").val());
		        		console.log("mberPw = " + $("#mberPw").val());    
		    	        $("#authFrm").attr("method","post");
		    	        $("#authFrm").attr("action", "/com/auth/login");
		    	        $("#authFrm").submit();
		        	} else {
		        		alert("입력하신 인증번호가 일치하지 않습니다.");
		        	}
		        },
		        error: function(request, status, error){
		        
		        }
			});
		}

	    $(document).ready(function(){
	        $("input[name=mberPw]").keydown(function (key) {
	            if(key.keyCode == 13){//키가 13이면 실행 (엔터는 13)
	                goLogin();
	            }
	        });
	    });
    </script>
    </body>
</html>