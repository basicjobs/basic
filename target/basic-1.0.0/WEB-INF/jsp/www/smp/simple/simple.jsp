<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> 
  <!DOCTYPE html>
<html>
  <body>

<div class="form-group">
      <input type="text" class="form-control"  id="date" placeholder="date">
    </div>
    <div class="form-group">
      <input type="text" class="form-control" id="title" placeholder="enter title">
    </div>

    <div class="my-3 p-3 bg-white rounded shadow-sm" id="DivSrchList">
    </div>

    <div class="modal fade" id="updateModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
      <div class="modal-dialog" role="document">
        <div class="modal-content">
          <div class="modal-header">
            <h5 class="modal-title" id="exampleModalLabel">Add simple</h5>
            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
              <span aria-hidden="true">&times;</span>
            </button>
          </div>
          <div class="modal-body">
            <div class="form-group">
              <label for="recipient-name" class="col-form-label">title:</label>
              <input type="text" class="form-control" id="updateTitle" placeholder="enter title">
            </div>
          </div>
          <div class="modal-footer">
            <button type="button" class="btn btn-primary" onClick="simple.fnUpdate()">modify</button>
            <button type="button" class="btn btn-secondary" data-dismiss="modal">cancel</button>
            <button type="button" class="btn btn-danger" onClick="simple.fnDelete()">delete</button>
          </div>
        </div>
      </div>
    </div>

    <input type="hidden" id="simpleIdx">
    <!-- 사이트 기능 스크립트 -->
    <script>
$(document).ready(function() {

	 //$("#date").val(Util.getToday("yyyy-mm-dd"));
	 $("#date").datepicker({
		 format: 'yyyy-mm-dd'
		,autoclose: true
	 });

	 $('#date').datepicker().on('changeDate', function (ev) {
		 $("#totalAmount").text("0");
		 simple.fnSrch();
	 });

	 $("#DivSrchList").sortable(
    {
        update: function () {
        var strItems = "";

        $("#DivSrchList strong").each(function (i) {
            var div = $(this);
            strItems += div.attr("id") + ':' + (i + 1) + ',';
        });

        simple.fnOrder(strItems);
        }
    });

   $( "#DivSrchList" ).disableSelection();

	$("#title").keydown(function(key) {
		if (key.keyCode == 13) {
			simple.fnRegist();
		}
	});

	$("#updateTitle").keydown(function(key) {
		if (key.keyCode == 13) {
			$("#updateAmount").focus();
		}
	});

	$("#updateAmount").keydown(function(key) {
		if (key.keyCode == 13) {
			simple.fnUpdate();
		}
	});

	simple.fnSrch();

	$('#updateModal').on('shown.bs.modal', function () {
	    $('#updateTitle').focus();
	});

	// boardList();  
});

function boardList(){
        var surl = "/api/board/list";

		var sendData = {};
		sendData.formType = "1";
		sendData.simpleDate = $("#date").val();

		console.log(sendData);
	    $.ajax({
	        url: surl,
	        type: "post",
	        contentType: "application/json",
	        data: JSON.stringify(sendData),
	        success: function (data){
	        	console.log(data);
	        },
	        error: function(request, status, error){

	        }
	    });
}

var simple = {
	// 스케줄 목록
	fnSrch : function() {
		var surl = "/api/simple/selectSimpleList";

		var sendData = {};
		sendData.formType = "1";
		sendData.simpleDate = $("#date").val();
		$("#DivSrchList").empty();
		console.log(sendData);
	    $.ajax({
	        url: surl,
	        type: "post",
	        contentType: "application/json",
	        data: JSON.stringify(sendData),
	        success: function (data){
	        	console.log(data);
	        	var list = data.result;  
	        	if ( list.length > 0 ) {
	        		simple.fnSetList(list);
	        		//Stat.fnStat();
	        	} else {
	        		$("#totalAmount").text("0");
	        		$("#DivSrchList").empty();
	        	}
	        },
	        error: function(request, status, error){

	        }
	    });
	},
	// 스케줄 목록 그리기
	fnSetList : function(list) {
		var txt ='' ;
		txt+='<div class="media text-muted pt-3"> ';
		/* txt+='  <svg class="bd-placeholder-img mr-2 rounded" width="24" height="24" xmlns="http://www.w3.org/2000/svg" preserveAspectRatio="xMidYMid slice" focusable="false" role="img" aria-label="Placeholder: 32x32"><title>Placeholder</title><rect width="100%" height="100%" fill="#007bff"/><text x="50%" y="50%" fill="#007bff" dy=".3em">32x32</text></svg>';  */
		txt+='  <p class="media-body pb-3 mb-0 small lh-100 border-bottom border-gray">';
		txt+='      <strong class="d-block text-gray-dark order">';
		txt+='  	    <span class="title">';
		txt+='  	    </span>';
		txt+='          <input type="hidden" class="amountVal">';
		txt+='      </strong>';
	    txt+='  </p>';
	    txt+=' </div>';

		for (var i=0; i<list.length ; i++ ) {
			$("#DivSrchList").append(txt);
			var target = $("#DivSrchList div.text-muted:last");
			simple.setData(target, list[i] );
			if (list.length == (i + 1)) {
				//simple.fnTotalCount();
			}
		}
	},
	// 스케줄 목록 데이터 세팅
	setData : function(target, data) {
		target.attr("ondblclick", "simple.showUpdateModal('" + data.simpleIdx + "','" + data.title + "','" + data.amount + "')");
		target.find("span.title").text(data.title);
		target.find("strong.order").attr("id", data.simpleIdx);
	},
	// 스케줄 등록하기
	fnRegist : function() {
		if($("#title").val() == "") {
			alert("제목을 입력해주세요.");
			$("#title").focus();
			return;
		}

		var sendData = {};
		sendData.title = $("#title").val();
		sendData.simpleDate = $("#date").val();
		sendData.simpleIdx = "";
	    console.log(sendData);
	    $.ajax({
	        url: "/api/simple/insertSimple",
	        type: "post",
	        contentType: "application/json",
	        data: JSON.stringify(sendData),
	        success: function (data){
                simple.fnSrch();
                $("#title").val("");
                $("#title").focus();
	        },
	        error: function(request, status, error){
	            // openAlertPopup('에러!');
	        }
	    });
	},
	// 업데이트 모달보여주기
	showUpdateModal : function(simpleIdx, title, amount) {
		$("#simpleIdx").val(simpleIdx);
		$("#updateTitle").val(title);
		$("#updateAmount").val(amount);
		$('#updateModal').modal('show');
	},
	// 스케쥴 수정하기
	fnUpdate : function() {
		var surl = "/api/simple/updateSimple";

		if($("#updateTitle").val() == "") {
			alert("Please enter title");
			$("#updateTitle").focus();
			return;
		}

		if($("#updateAmount").val() == "") {
			alert("Please enter amount");
			$("#updateAmount").focus();
			return;
		}
		sendData = {};
		sendData.title = $("#updateTitle").val();
		sendData.amount = $("#updateAmount").val();
		sendData.simpleIdx = $("#simpleIdx").val();
		sendData.simpleDate = $("#date").val();

	    $.ajax({
	        url: surl,
	        type: "post",
	        contentType: "application/json",
	        data: JSON.stringify(sendData),
	        success: function (data){
                simple.fnSrch();
                $('#updateModal').modal('hide');
	        },
	        error: function(request, status, error){
	            // openAlertPopup('에러!');
	        }
	    });
	},
	// 스케쥴 삭제하기
	fnDelete : function() {
		var surl = "/api/simple/deleteSimple";

		var sendData = {};
		sendData.simpleIdx = $("#simpleIdx").val();

	    $.ajax({
	        url: surl,
	        type: "post",
	        contentType: "application/json",
	        data: JSON.stringify(sendData),
	        success: function (data){
 	            simple.fnSrch();
				$('#updateModal').modal('hide');
	        },
	        error: function(request, status, error){
	            // openAlertPopup('에러!');
	        }
	    });
	},
	// 스케쥴 순서 맞추기
	fnOrder : function(strItems) {
		console.log(strItems);
		var surl = "/api/simple/order";

		var sendData = {};
		sendData.orderArray = strItems;
	    $.ajax({
	        url: surl,
	        type: "post",
	        contentType: "application/json",
	        data: JSON.stringify(sendData),
	        success: function (data){
	            simple.fnSrch();
	        },
	        error: function(request, status, error){
	            // openAlertPopup('에러!');
	        }
	    });
	},
	// 숫자 새기
	fnTotalCount : function() {
		var totalAmount = 0;
	    $("#DivSrchList .amountVal").each(function (i) {
	        var amount = Number($(this).val());
			console.log("amount = " + amount);
			totalAmount = totalAmount + amount;
	    });
	    console.log("totalAmount = " + totalAmount);
	    //$("#totalAmount").text(Util.numberToComma(totalAmount));
	}  
}

</script>
  </body>
</html>