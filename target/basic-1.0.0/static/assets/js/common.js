$.fn.serializeObject = function() {
    var result = {};
    var extend = function(i, element) {
        var node = result[element.name]
        if ("undefined" !== typeof node && node !== null) {
            if ($.isArray(node)) {
                node.push(element.value)
	        } else {
	            result[element.name] = [node, element.value]
	        }
        } else {
            result[element.name] = element.value
        }
    }

    $.each(this.serializeArray(), extend);
    return result;
}

function checkId(id) {
    var reg= /^[a-zA-z0-9-_]{6,12}$/;
    return reg.test(id);
}


function checkPw(pw) {
	var reg = /^(?=.*[a-zA-Z])(?=.*[0-9])(?=.*[!@#$%^&*?_]).{8,50}$/;
	return reg.test(pw);
}

//이메일 정규식 체크
function checkEmail(email) {
	var reg = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i;
	return reg.test(email);
}

//주민등록번호 유효성 체크
function checkJumin(no) {
 
    // -제거 
    no = no.split('-').join('');
 
    var arr_ssn = [];
    var compare = [2,3,4,5,6,7,8,9,2,3,4,5];
    var sum     = 0;
 
    // 입력값 체크
    if (no.match('[^0-9]')) {
        return false; 
    }
 
    // 자리수 체크
    if (no.length != 13) {
        return false;
    }    
 
    // 공식: M = (11 - ((2×A + 3×B + 4×C + 5×D + 6×E + 7×F + 8×G + 9×H + 2×I + 3×J + 4×K + 5×L) % 11)) % 10
    for (var i = 0; i < 13; i++) { 
        arr_ssn[i] = no.substring(i,i+1); 
    }
    
    for (var i = 0; i < 12; i++) {
        sum = sum + (arr_ssn[i] * compare[i]); 
    }
 
    sum = (11 - (sum % 11)) % 10;
    
    if (sum != arr_ssn[12]) { 
        return false; 
    }
 
    return true;
 
}

function goLink(link) {
	location.href = link;
}

function openJusoPopup(){
	var pop = window.open("/mba/auth/jusoPopup","pop","width=590,height=420, scrollbars=yes, resizable=yes");
}

function jusoCallBack(roadFullAddr,roadAddrPart1,addrDetail,roadAddrPart2,engAddr, jibunAddr, zipNo, admCd, rnMgtSn, bdMgtSn,detBdNmList,bdNm,bdKdcd,siNm,sggNm,emdNm,liNm,rn,udrtYn,buldMnnm,buldSlno,mtYn,lnbrMnnm,lnbrSlno,emdNo){
    // 팝업페이지에서 주소입력한 정보를 받아서, 현 페이지에 정보를 등록합니다.
	$("#zip").val(zipNo);
    $("#detailAdres").val(roadAddrPart1);
    $("#detailAdres2").val(addrDetail);
}

function isEmpty(arg){
	try {
		if (arg == null || arg == undefined || (arg + "") == "") return true;
		else return false;
	} catch (e) {
		return true;
	}
}

function pageNavi(pageNo, totalCount, rowCount, pageCount, func) {
	var totalPage = Math.ceil(totalCount / rowCount);
	var startPage = (Math.ceil(pageNo / pageCount) - 1) * pageCount + 1;
	var prevPage = startPage - 1;
	if(prevPage == 0) prevPage = 1;
	var nextPage = (parseInt(startPage,10) + parseInt(pageCount,10));
	if(nextPage > totalPage) nextPage = totalPage;

	var pageHtml = "";

	// << btnFirst
	pageHtml += "<button type='button' onclick='javascript:"+func+"(1);' class='navi first' data-page='1'><span class='sr_only'>첫 페이지로 이동</span></button>";

    // < btnPrev
	pageHtml += "<button type='button' onclick='javascript:"+func+"("+(prevPage)+");' class='navi prev' data-page='1'><span class='sr_only'>이전 페이지로 이동</span></button>";

    // 1 2 3 [4] 5 6 7 number
    //pageHtml+="<span>"
    for (var i=startPage; i<Number(startPage)+Number(pageCount); i++) {
        if (totalPage < i) break;
        if (i == pageNo) {
            pageHtml += "<span class='current'>"+ i +"<span class='sr_only'>현재 페이지</span></span>";
        } else {
            pageHtml += "<button type='button' onclick='javascript:"+func+"("+i+");' title='"+ i +" 페이지 이동' data-page='"+ i +"'>"+ i +"</button>";
        }

        if (i == totalPage)
            break;
    }

    // > btnNext
    pageHtml += "<button type='button' onclick='javascript:"+func+"("+nextPage+")' class='navi next' data-page='3'><span class='sr_only'>다음 페이지로 이동</span></button>";
    
    // >> btnLast
    pageHtml += "<button type='button' onclick='javascript:"+func+"("+totalPage+")' class='navi end' data-page='6'><span class='sr_only'>마지막 페이지로 이동</span></button>";
    return pageHtml;
};