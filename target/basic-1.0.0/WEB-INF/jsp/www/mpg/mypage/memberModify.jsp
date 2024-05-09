<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
    <body>
    <h1 class="h3 mb-3 fw-normal">개인정보 수정</h1> 
    
<ul class="nav nav-tabs">
    <li class="nav-item" id="modifyTab">
    	<button class="nav-link active" id="modifyBtn" >개인정보 수정</button>
    </li>
    <li class="nav-item" id="passwordTab">
    	<button class="nav-link" id="passwordBtn">비밀번호 변경</button>  
    </li>
</ul> 

<div id="modifyDiv">
    <form id="sendForm">
		<div class="form-row">
		    <div class="form-group col-md-6">
		        <label for="inputEmail4">이름</label>
		        <input type="email" class="form-control" id="mberNm" name="mberNm" placeholder="이름" readonly>
		    </div>
		    <div class="form-group col-md-6">
		        <label for="inputEmail4">휴대전화번호</label>  
		        <input type="text" class="form-control" id="cryalTelno" name="cryalTelno" placeholder="휴대전화번호" readonly>
		    </div>
		</div>  

		<div class="form-row">
		    <div class="form-group col-md-6">
		        <label for="inputPassword4">주민등록번호</label>
		        <input type="text" class="form-control" id="ihidnum" name="ihidnum" placeholder="주민등록번호" readonly>  
		    </div>
		    <div class="form-group col-md-6">
		        <label for="inputPassword4">생년월일</label>     
		         <input type="text" class="form-control" id="birthday" name="birthday" readonly>
		    </div>
		</div>

		<div class="form-row">
		    <div class="form-group col-md-6">
		        <label for="inputPassword4">아이디</label>
		        <input type="text" class="form-control" id="id" name="id" placeholder="이메일" readonly>
		    </div>
		    <div class="form-group col-md-6">
		        <label for="inputPassword4">이메일</label>
		        <input type="text" class="form-control" id="email" name="email" placeholder="이메일">
		    </div>
		</div>

		<div class="form-row">
		    <div class="form-group col-md-6">
		        <label for="inputEmail4">주소</label>
		        <input type="text" class="form-control" id="zip" name="zip" placeholder="주소" style="margin-bottom:8px;" readonly>
		        <button type="button" class="btn btn-primary" onClick="openJusoPopup()">우편번호 검색</button>
		        <input type="text" class="form-control" id="detailAdres" name="detailAdres" placeholder="상세주소" style="margin-bottom:8px;" readonly>
		        <input type="text" class="form-control" id="detailAdres2" name="detailAdres2" placeholder="상세주소2" readonly>
		    </div> 
		</div>

		<div class="form-row">
		    <div class="form-group col-md-6">
		        <label for="inputPassword4">관심분야</label>
		        </br>
		        <select id="interestFieldCd" name="interestFieldCd">
		        	<option value="">선택하기</option>
		        	<option value="01">KC인증</option>
		        	<option value="02">일반전기제품</option>
		        	<option value="03">의료분야</option>
		        	<option value="04">화장품</option>
		        	<option value="05">일반의뢰접수</option>
		        </select>
		        <select id="interestFieldCd2" name="interestFieldCd2">
		        	<option value="">선택하기</option>
		        	<option value="01">KC인증</option>
		        	<option value="02">일반전기제품</option>
		        	<option value="03">의료분야</option>
		        	<option value="04">화장품</option>
		        	<option value="05">일반의뢰접수</option>
		        </select>
		        <select id="interestFieldCd3" name="interestFieldCd3">
		        	<option value="">선택하기</option>
		        	<option value="01">KC인증</option>
		        	<option value="02">일반전기제품</option>
		        	<option value="03">의료분야</option>
		        	<option value="04">화장품</option>
		        	<option value="05">일반의뢰접수</option>
		        </select>
		    </div>    
		    <div class="form-group col-md-6">
		        <label for="inputEmail4">직무</label>  
		        </br>
		        <select id="jobCd" name="jobCd">
		        	<option value="">선택하기</option>
		        	<option value="01">기획.전략</option>
		        	<option value="02">법무.사무.총무</option>
		        	<option value="03">인사.HR</option>
		        	<option value="04">회계.세무</option>  
		        	<option value="04">마케팅.광고.MD</option>ㄴ
		        	<option value="05">개발.데이터</option>
		        	<option value="06">디자인</option>
		        	<option value="07">물류.무역</option>
		        	<option value="08">운전.운송.배송</option>
		        	<option value="09">영업</option>
		        	<option value="10">고객상담.TM</option>
		        	<option value="11">금융.보험</option>
		        	<option value="12">식.음료</option>
		        	<option value="13">교객서비스.리테일</option>
		        	<option value="14">엔지니어링.설계</option>  
		        	<option value="15">제조.생산</option>
		        	<option value="16">교육</option>
		        	<option value="17">건축.시설</option>
		        	<option value="18">의료.바이오</option>
		        	<option value="19">미디어.몬화.스포츠</option>
		        	<option value="20">공공.복지</option>
		        </select>
		    </div>    
		</div>
		<div class="form-row">
		    <div class="form-group col-md-6">
		        <label for="inputEmail4">부서</label>
		        <input type="text" class="form-control" id="deptNm" name="deptNm" placeholder="부서">
		    </div>
		    <div class="form-group col-md-6">
		        <label for="inputPassword4">직책</label>
		        <input type="text" class="form-control" id="dursNm" name="dursNm" placeholder="직책">
		    </div> 
			<div class="form-group col-md-6">
		        <label for="inputPassword4">직위</label>
		        </br>
		        <select id="positionCd" name="positionCd">
		        	<option value="">선택하기</option>
		        	<option value="01">사원</option>
		        	<option value="02">주임</option>  
		        	<option value="03">대리</option>  
		        	<option value="04">과장</option>
		        	<option value="05">차장</option>
		        	<option value="06">부장</option>
		        	<option value="07">이사</option>
		        	<option value="08">상무</option>
		        	<option value="09">대표이사</option>
		        	<option value="10">연구원</option>
		        	<option value="11">선임연구원</option>
		        	<option value="12">책임연구원</option>
		        </select>
		    </div>
	    </div>

		<div class="form-row">
		    <div class="form-group col-md-6">
		        <label for="recommendTeam">추천 소속</label>
		        <input type="text" class="form-control" id="recommendTeam" name="recommendTeam" placeholder="추천 소속"> 
		    </div>  
		    <div class="form-group col-md-6">
		        <label for="recommendNm">추천 이름</label>  
		        <input type="text" class="form-control" id="recommendNm" name="recommendNm" placeholder="추천 이름">
		    </div>
		</div>   


		
		<button type="button" class="btn btn-primary" onClick="modify()">수정하기</button>
		<input type="hidden" id="mberSeq" name="mberSeq" value=1>
		<input type="hidden" id="mberSeCd" name="mberSeCd" value='A'>
		<input type="hidden" id="registerId" name="registerId" value='A'>
		<input type="hidden" id="changerId" name="changerId" value='A'>
		<input type="hidden" id="sportHdqrDeptCd" name="sportHdqrDeptCd" value='A'>
		<input type="hidden" id="cstmrSttusCd" name="cstmrSttusCd" value='A'>
		<input type="hidden" id="dmstcOvseaSeCd" name="dmstcOvseaSeCd" value='A'>
		<input type="hidden" id="cstmrSeCd" name="cstmrSeCd" value='A'>
		<input type="hidden" id="zrpctaxBsnmYn" name="zrpctaxBsnmYn" value='A'>
		<input type="hidden" id="zrpctaxBsnmYn" name="nationCd" value='KO'>
		<input type="hidden" id="zrpctaxBsnmYn" name="ihidnum" value='910916'>
		<input type="hidden" name="duplicateYn" id="duplicateYn" >
    </form>
</div>
<div id="passwordDiv" style="display:none;">
	</br>
    <div class="col-sm-5">
        <form name="frm" id="frm" method="post">
        <input type="hidden" name="mberNo"  id="mberNo" value="${userVO.mberNo}" >
        <div class="form-floating">
            <input type="password" class="form-control" name="password" id="password" placeholder="새로운 비밀번호">
            <label for="id"></label>
        </div>
        <div class="form-floating">
            <input type="password" class="form-control" name="passwordConfirm" id="passwordConfirm" placeholder="새로운 비밀번호 확인">
            <label for="password"></label>
        </div>
        <div class="row">
            <div class="col-md-12">
                <button class="btn btn-primary w-100 py-2" type="button" onClick="changePassword()">비밀번호 변경</button>
            </div>
	    </div>
  	    </form>
    </div>  
</div>
  
    
    

    <script>   
  	
    function selectMember(){
	    var sendData = {};

        $.ajax({
            url: "/api/mypage/selectMember",
            type: "post",
            contentType: "application/json",
            data: JSON.stringify(sendData),
            success: function (map){
	        	var result = map.result;
	        	if (result != null) {
		        	$("#mberNm").val(result.mberNm);
		        	$("#cryalTelno").val(result.cryalTelno);
		        	$("#ihidnum").val(result.ihidnum);
		        	$("#birthday").val(result.birthday);
		        	$("#id").val(result.id);
		        	$("#email").val(result.email);
		        	$("#interestFieldCd").val(result.interestFieldCd);
		        	$("#interestFieldCd2").val(result.interestFieldCd2);
		        	$("#interestFieldCd3").val(result.interestFieldCd3);
		        	$("#detailAdres").val(result.detailAdres);
		        	$("#zip").val(result.zip);
		        	$("#jobCd").val(result.jobCd);
		        	$("#deptNm").val(result.deptNm);
		        	$("#dursNm").val(result.dursNm);
		        	$("#positionCd").val(result.positionCd);
		        	$("#recommendTeam").val(result.recommendTeam);
		        	$("#recommendNm").val(result.recommendNm);
	        	}
            },
            error: function(request, status, error){
            
            }
        }); 
    }
    
	function modify() {
		if($("#email").val() == "") {
			alert("이메일을 입력해주세요.");
			$("#email").focus();
			return;
		}
		
		if($("#interestFieldCd").val() == "") {
			alert("관심분야를 선택해주세요."); 
			$("#interestFieldCd").focus();
			return;
		}
		
		if($("#detailAdres").val() == "") {
			alert("주소를 입력해주세요."); 
			$("#detailAdres").focus();  
			return;
		}
		
		$("#detailAdres").val($("#detailAdres").val() + " " + $("#detailAdres2").val());  
		
 		var formData = $("#sendForm").serializeObject();
 		console.log(formData);  
 		$.ajax({
	        url: "/api/mypage/modifyMember",
	        type: "post",
	        contentType: "application/json;charset=utf-8", 
	        dataType :'json',
	 		data : JSON.stringify(formData),   
	        success: function (map){
                alert("수정이 완료되었습니다.");  
	        },
	        error: function(request, status, error){
	            
	        }
	   });
	}
	
	function changeTab(tab){
		if (tab == "modify"){
			$("#passwordBtn").removeClass("active");
			$("#passwordDiv").hide();
			$("#modifyBtn").addClass("active");
			$("#modifyDiv").show();
		} else{
			$("#modifyBtn").removeClass("active");
			$("#modifyDiv").hide();
			$("#passwordBtn").addClass("active");
			$("#passwordDiv").show();
		}  
	}
	
	function changePassword(){  
	    if ($("#password").val() == "") {
	        alert("비밀번호를 입력해주세요.");
	        return;
	    }
	    
		if(!checkPw($("#password").val())){
			alert("8자리 이상, 영문 대/소문자, 특수문자, 숫자를 조합해서 입력해주세요.");
			$("#password").focus();
			return;
		}
		
		if(!($("#password").val() == $("#passwordConfirm").val())) {
			alert("비밀번호가 일지하지 않습니다.");
			$("#passwordConfirm").focus();
			return;
		}
	    
		var sendData = {};
		sendData.mberNo = $("#mberNo").val(); 
		sendData.password = $("#password").val();
		
	    $.ajax({
	        url: "/api/auth/changePassword",
	        type: "post",
	        contentType: "application/json",
	        data: JSON.stringify(sendData),
	        success: function (map){
                alert("비밀번호가 변경되었습니다.");
	        },
	        error: function(request, status, error){
	            
	        }
	    }); 
	}  
	
	$(document).ready(function(){
        $("input[name=name]").keydown(function (key) {
            if(key.keyCode == 13){//키가 13이면 실행 (엔터는 13)
            	modify();
            }
        });
        selectMember();
        
        $("#modifyBtn").click(function(){
        	changeTab('modify');
        });
        
        $("#passwordBtn").click(function(){
            changeTab("password");
        });
        
    });  
  </script>
  </body>

</html> 