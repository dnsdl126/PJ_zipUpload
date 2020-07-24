<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../include/include.jsp"%> 
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div class="col-lg-10 well" style="width: 500px; margin: auto;">
			<div class="row">
			<form id="UploadForm" name="UploadForm" enctype="multipart/form-data" method="post"  >
				<input id="zipFile" type="file" name="zipFile" multiple="multiple"  /></input>
				<input type="button" value="upload" onclick="fileSubmit();">
			</form>
			</div>
		</div>

</body>
<script src="http://malsup.github.com/jquery.form.js"></script>
<script type="text/javascript">

function fileSubmit() {
   
	var form =$('#UploadForm')[0];
	
	var formData = new FormData(form);
	
		alert("진행");
		  $.ajax({
		       enctype:"multipart/form-data",
		       method:"POST",
		       url: '${path}/zipcreate',
		       processData: false,   
		       contentType: false,
		       cache: false,
		       data: formData,
		       success: function(data){  
		    	   alert(data);
		    	   
		    	   if(data == "ok") {
		    		   alert("업로드 성공!!");
		    		   location.reload(); /* 자동 새로고침 */
		    	   } else {
		       		alert("업로드 실패!!");
		    		   
		    	   }
		    			   
		    	   
		       } 
		    });
	
	
	
	
	
   
	
}

</script>	
</html>