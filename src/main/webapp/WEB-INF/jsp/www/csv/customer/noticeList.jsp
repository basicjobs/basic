<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
    <body>
    <h1 class="h3 mb-3 fw-normal">공지사항</h1> 
    <div id="wrapper">
		<div class="cBody">

			<div class="subWrap">
				<div class="inner1100"
					data-controller="controller/COCommonController controller/coc/COCBoardArtclController"
					data-page="list" data-board-type="NORMAL">
					<form name="searchForm" id="searchForm">
						<input type="hidden" id="page" name="page" value="1">
						<input type="hidden" id="pageSize" name="pageSize" value="3">
						
						<div class="searchBox mt40">
							<select id="searchField" name="searchField" title="검색어 선택">
								<option value="">전체</option> 
								<option value="ntceSbjt">제목</option>
								<option value="bultCntt">내용</option>
							</select> <input type="text" id="searchText" name="searchText" value="" title="검색어를 입력해주세요."
								placeholder="검색어를 입력해주세요." style="width: 400px;" maxlength="15">
							<a href="javascript:fn_notice(1);" class="grybtH50" id="btn_search">검색</a>
						</div>
					</form>
					<div class="boardType4 mt20">
						<table>
							<colgroup>
								<col width="80px">
								<col width="200px">
								<col width="*">
								<col width="150px">
								<col width="150px">
								<col width="130px">
								<col width="100px">
							</colgroup>
							<thead>
								<tr>
									<th scope="col">번호</th>
									<th scope="col">분야</th>
									<th scope="col">제목</th>
									<th scope="col">첨부</th>
									<th scope="col">작성자</th>
									<th scope="col">등록일</th>
									<th scope="col">조회수</th>
								</tr>
							</thead>
							<tbody id="result_area">
								<tr>
									<td>1223</td>
									<td>[공지]</td>
									<td class="taL"><a href="javascript:"
										onclick="commonCtrl.setViewPage('detailsKey', '60545', './view.do');">
											디지털 시험성적서 이용 및 발급</a></td>
									<td>
										<ul class="boardFileIcon">
											<li><a
												href="/cmm/fms/FileDown.do?fileId=FILE_000000000008601&amp;fileSn=0"
												title="다운로드"> <img
													src="/static/assets/img/zip_icon.png" alt="zip 파일">
											</a></li>
										</ul>
									</td>
									<td>정보전략실</td>
									<td>2023-08-14</td>
									<td>1707</td>
								</tr>
								<tr class="">
									<td>1222</td>
									<td>[공지]</td>
									<td class="taL"><a href="javascript:"
										onclick="commonCtrl.setViewPage('detailsKey', '65044', './view.do');">
											2024년 해외규격인증획득지원사업 패스트트랙 및 일반...</a></td>
									<td>
										<ul class="boardFileIcon">
											<li><a
												href="/cmm/fms/FileDown.do?fileId=FILE_000000000008601&amp;fileSn=0"
												title="다운로드"> <img
													src="/static/assets/img/zip_icon.png" alt="zip 파일">
											</a></li>
										</ul>
									</td>
									<td>정보전략실</td>
									<td>2024-02-29</td>
									<td>117</td>
								</tr>
								<tr>
									<td>1221</td>
									<td>[분야]</td>
									<td class="taL"><a href="javascript:"
										onclick="commonCtrl.setViewPage('detailsKey', '64980', './view.do');">
											2024년도 스마트특성화기반구축 기업지원사업 공고 (초...</a></td>
									<td>
										<ul class="boardFileIcon">
											<li><a
												href="/cmm/fms/FileDown.do?fileId=FILE_000000000008601&amp;fileSn=0"
												title="다운로드"> <img
													src="/static/assets/img/zip_icon.png" alt="zip 파일">
											</a></li>
										</ul>
									</td>
									<td>정보전략실</td>
									<td>2024-02-26</td>
									<td>109</td>
								</tr>
								<tr class="">
									<td>1220</td>
									<td>[공지]</td>
									<td class="taL"><a href="javascript:"
										onclick="commonCtrl.setViewPage('detailsKey', '64978', './view.do');">
											2024년도 산업혁신기반구축사업 「바이오인터페이싱 인체...</a></td>
									<td>
										<ul class="boardFileIcon">
											<li><a
												href="/cmm/fms/FileDown.do?fileId=FILE_000000000008601&amp;fileSn=0"
												title="다운로드"> <img
													src="/static/assets/img/zip_icon.png" alt="zip 파일">
											</a></li>
										</ul>
									</td>
									<td>정보전략실</td>
									<td>2024-02-26</td>
									<td>78</td>
								</tr>
								<tr class="">
									<td>1219</td>
									<td>[공지]</td>
									<td class="taL"><a href="javascript:"
										onclick="commonCtrl.setViewPage('detailsKey', '64917', './view.do');">
											[2024년 스마트특성화 기반구축 사업] 기술지원사업 ...</a></td>
									<td>
										<ul class="boardFileIcon">
											<li><a
												href="/cmm/fms/FileDown.do?fileId=FILE_000000000008601&amp;fileSn=0"
												title="다운로드"> <img
													src="/static/assets/img/zip_icon.png" alt="zip 파일">
											</a></li>
										</ul>
									</td>
									<td>정보전략실</td>
									<td>2024-02-22</td>
									<td>158</td>
								</tr>
								<tr>
									<td>1218</td>
									<td>[분야]</td>
									<td class="taL"><a href="javascript:"
										onclick="commonCtrl.setViewPage('detailsKey', '64903', './view.do');">
											2024년도 한국화학융합시험연구원 충북 화장품 지원사업...</a></td>
									<td>
										<ul class="boardFileIcon">
											<li><a
												href="/cmm/fms/FileDown.do?fileId=FILE_000000000008601&amp;fileSn=0"
												title="다운로드"> <img
													src="/static/assets/img/zip_icon.png" alt="zip 파일">
											</a></li>
										</ul>
									</td>
									<td>정보전략실</td>
									<td>2024-02-21</td>
									<td>91</td>
								</tr>
								<tr>
									<td>1217</td>
									<td>[공지]</td>
									<td class="taL"><a href="javascript:"
										onclick="commonCtrl.setViewPage('detailsKey', '64820', './view.do');">
											2024 KTR GS인증 기준 설명회 안내</a></td>
									<td></td>
									<td>정보전략실</td>
									<td>2024-02-15</td>
									<td>447</td>
								</tr>
								<tr>
									<td>1216</td>
									<td>[공지]</td>
									<td class="taL"><a href="javascript:"
										onclick="commonCtrl.setViewPage('detailsKey', '64700', './view.do');">
											2024년도 인체피부 일차자극 시험 일정 안내</a></td>
									<td>
										<ul class="boardFileIcon">
											<li><a
												href="/cmm/fms/FileDown.do?fileId=FILE_000000000008601&amp;fileSn=0"
												title="다운로드"> <img
													src="/static/assets/img/zip_icon.png" alt="zip 파일">
											</a></li>
										</ul>
									</td>
									<td>정보전략실</td>
									<td>2024-02-05</td>
									<td>255</td>
								</tr>
								<tr class="">
									<td>1215</td>
									<td>[공지]</td>
									<td class="taL"><a href="javascript:"
										onclick="commonCtrl.setViewPage('detailsKey', '64623', './view.do');">
											2024년 제1차 단기수출 해외규격시험 매칭 지원기업 ...</a></td>
									<td>
										<ul class="boardFileIcon">
											<li><a
												href="/cmm/fms/FileDown.do?fileId=FILE_000000000008601&amp;fileSn=0"
												title="다운로드"> <img
													src="/static/assets/img/zip_icon.png" alt="zip 파일">
											</a></li>
										</ul>
									</td>
									<td>정보전략실</td>
									<td>2024-01-31</td>
									<td>292</td>
								</tr>
								<tr>
									<td>1214</td>
									<td>[공지]</td>
									<td class="taL"><a href="javascript:"
										onclick="commonCtrl.setViewPage('detailsKey', '64521', './view.do');">
											살생물제 승인 전략 및 중처범 대응방안 세미나 개최 안...</a></td>
									<td></td>
									<td>정보전략실</td>
									<td>2024-01-26</td>
									<td>213</td>
								</tr>
							</tbody>
						</table>
					</div>
					<div class="paging" id="paging_area">
						<a href="#" data-page="first" class="first"
							onclick="commonCtrl.pagination(1); return false;">첫 페이지</a><a
							href="#" data-page="prev" class="prev"
							onclick="commonCtrl.pagination(1); return false;">이전 페이지</a><a
							href="#" data-page="1" class="on" onclick="return false;"><span>1</span></a><a
							href="#" data-page="2"
							onclick="commonCtrl.pagination(2); return false;"><span>2</span></a><a
							href="#" data-page="3"
							onclick="commonCtrl.pagination(3); return false;"><span>3</span></a><a
							href="#" data-page="4"
							onclick="commonCtrl.pagination(4); return false;"><span>4</span></a><a
							href="#" data-page="5"
							onclick="commonCtrl.pagination(5); return false;"><span>5</span></a><a
							href="#" data-page="6"
							onclick="commonCtrl.pagination(6); return false;"><span>6</span></a><a
							href="#" data-page="7"
							onclick="commonCtrl.pagination(7); return false;"><span>7</span></a><a
							href="#" data-page="8"
							onclick="commonCtrl.pagination(8); return false;"><span>8</span></a><a
							href="#" data-page="9"
							onclick="commonCtrl.pagination(9); return false;"><span>9</span></a><a
							href="#" data-page="10"
							onclick="commonCtrl.pagination(10); return false;"><span>10</span></a><a
							href="#" data-page="next" class="next"
							onclick="commonCtrl.pagination(11); return false;">다음 페이지</a><a
							href="#" data-page="end" class="end"
							onclick="commonCtrl.pagination(123); return false;">마지막 페이지</a>
					</div>

				</div>
				<!--// inner1100 -->
				<!-- 2023-02-22 FDA 의료기기 인증제도 페이지 담당자 안내 수정 요건때문에 예외처리 후 하드코딩으로 수정 -->
			</div>
			<!-- //  subWrap -->
		</div>
	</div>
    <script>   
    $(function(){
    	//=============== 맨처음 로딩시 호출 시작 ===============
    	fn_notice(1);
    	//=============== 맨처음 로딩시 호출 끝 ================

    	//검색 엔터버튼
    	$("#searchText").keypress(function (e) {
            if(e.which === 13){
            	fn_notice(1);
                return false;
            }
        });
    });

    //조회
    function fn_notice(page){

        page = (page == null || page == "") ? "1" : page;	// 페이지 번호
        $("#page").val(page);

        var formData = $("#searchForm").serializeObject();
        $.ajax({
        	url:'/api/customer/noticeList',
            type:'post',
            contentType: "application/json;charset=utf-8", 
            dataType :'json',
            data : JSON.stringify (formData),
            success:function(data){
                if(data.result.length > 0) {
                    var noticeHtml = '';

                    $("#result_area").show();

                    $(data.result).each(function(index){

                        noticeHtml += '<tr>';
                        noticeHtml += '  <td>' + this.rnum + '</td>';
                        noticeHtml += '  <td>' + this.poptName + '</td>';
                        noticeHtml += '  <td class="taL"><a href="javascript:" onclick="setViewPage(' + "'" + this.evSralNumb + "'" + ');">' + this.ntceSbjt + '</a></td>'
                        if(!isEmpty(this.fileIdxx)){
                        	noticeHtml += '  <td><ul class="boardFileIcon"><li><a href="/cmm/fms/FileDown.do?fileId=FILE_000000000008601&amp;fileSn=0" title="다운로드">';
                        	noticeHtml += '  <img src="/static/assets/img/zip_icon.png" alt="zip 파일"></a></li></ul></td>';
                        }else {
                        	noticeHtml += '  <td></td>';
                        }
                        noticeHtml += '  <td>' + this.boardWriter + '</td>';
                        noticeHtml += '  <td>' + this.instDate + '</td>';
                        noticeHtml += '  <td>' + this.viewCnt + '</td>';
                        noticeHtml += '</tr>';

                    });
                    $('#result_area').html(noticeHtml);

                    $("#paging_area").show();
                    $('#paging_area').html(pageNavi(page, data.result[0].totCnt, $("#pageSize").val(), 4, "fn_notice"));

                } else {
                    $("#result_area").hide();
                    $("#paging_area").hide();
                }

            },
            error: function(request, status, error){
                //console.log("connection fail");
            }
        });

    };
    
  </script>
  </body>

</html> 