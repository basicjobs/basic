<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%> 
<!DOCTYPE html>
<html>
    <body>
    <h1 class="h3 mb-3 fw-normal">공지사항 등록</h1> 
    <form id="frm" name="frm" method="post">
      	<table cellspacing="0" cellpadding="0" width="700px">
        <colgroup>
        <col width="15%"/>
        <col/>
        </colgroup>
        <tr>
			<td>Title</td>
			<td><input type="text" id="brd_title" name="brd_title"/></td>
		</tr>
        <tr>
			<td>Contents</td>
			<td><textarea name="brd_contents" cols="55" rows="5" style="width: 500px;"></textarea></td>
		</tr>
		<tr>
			<td>Attach File</td>
			<td>
				<input type="file" id="bizrnoAtchId" name="bizrnoAtchId"/>
				<input type="file" id="hffcynAtchId" name="hffcynAtchId"/>
			</td>
		</tr>
		</table>
		<input type="button" value="Submit" onclick='fn_upload()'  />
		<input type="hidden" id="menu" name="menu" value="customer">
		<input type="hidden" id="fileIdxx" name="fileIdxx">
		<input type="hidden" id="realname" name="realname"/>
		<input type="hidden" id="filename" name="filename"/>
		<input type="hidden" id="filesize" name="filesize"/>		
	</form>
    <script>   

    //파일 업로드
    function fn_upload(){
       
    	var options = {
			url : '/api/customer/uploadFiles',
			cache : false,
			dataType : 'json',
			data : jQuery("#frm").serialize(),
			beforeSubmit : function(options) {
			},
			success : function(result) {
				alert(result.msg);
			}
		};
		jQuery('#frm').ajaxSubmit(options);
    }

  </script>
  </body>

</html> 