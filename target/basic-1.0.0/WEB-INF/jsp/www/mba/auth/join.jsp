<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
    <body>
    <h1 class="h3 mb-3 fw-normal">회원가입</h1> 
    <form id="sendForm">
		<div class="form-row">
		    <div class="form-group col-md-6">
		        <label for="inputPassword4">이메일</label>
		        <input type="text" class="form-control" id="mberId" name="mberId" placeholder="이메일">
		    </div>
		    <div class="form-group col-md-6">
		        <label for="inputEmail4">이름</label>
		        <input type="email" class="form-control" id="mberNm" name="mberNm" placeholder="이름" value="" readonly>
		    </div>
		</div>
		
		<div class="form-row">
		    <div class="form-group col-md-6">
		        <label for="inputEmail4">비밀번호</label>
		        <input type="password" class="form-control" id="mberPw" name="mberPw" placeholder="비밀번호">
		    </div>
		    <div class="form-group col-md-6">  
		        <label for="inputPassword4">비밀번호 확인</label>
		        <input type="password" class="form-control" id="mberPwConfirm" name="passwordConfirm" placeholder="비밀번호 확인">
		    </div>
		</div>
		
		<button type="button" class="btn btn-primary" onClick="insert()">회원가입</button>
	</form>
	
    <script>
    
	function insert() {
		if($("#mberId").val() == "") {
			alert("아이디를 입력해주세요.");
			$("#mberId").focus();
			return;
		}
		
	/* 	if(!checkId($("#mberId").val())){
			alert("6-12자의 영문, 숫자, 기호(- _ )만 사용 가능합니다.");
			$("#mberId").focus();
			return;  
		} */
		
		if($("#mberPw").val() == "") {
			alert("비밀번호를 입력해주세요.");
			$("#mberPw").focus();
			return;
		}
		
		if(!checkPw($("#mberPw").val())){
			alert("8자리 이상, 영문 대/소문자, 특수문자, 숫자를 조합해서 입력해주세요.");
			$("#mberPw").focus();
			return;
		}
		
		if($("#mberPwConfirm").val() == "") {
			alert("비밀번호 확인을 입력해주세요.");
			$("#mberPwConfirm").focus();
			return;
		}

		if(!($("#mberPw").val() == $("#mberPwConfirm").val())) {
			alert("비밀번호가 일지하지 않습니다.");
			$("#mberPwConfirm").focus();
			return;  
		}
		var formData = $("#sendForm").serializeObject();
 		  
 		console.log(formData);
 		$.ajax({
	        url: "/api/auth/insertMember",
	        type: "post",
	        contentType: "application/json;charset=utf-8",
	        dataType :'json',
	 		data : JSON.stringify(formData),
	        success: function (map){
                alert("회원가입이 완료되었습니다.");
	        	goLink("/mba/auth/login");  
	        },
	        error: function(request, status, error){
	            
	        }
	   });
	}
	
	$(document).ready(function(){
        $("input[name=name]").keydown(function (key) {
            if(key.keyCode == 13){//키가 13이면 실행 (엔터는 13)
            	insert();
            }
        });
    });
  </script>
  </body>

</html>