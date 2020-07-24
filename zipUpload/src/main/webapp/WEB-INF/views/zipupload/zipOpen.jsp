<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file = "../include/include.jsp"%> 

<!DOCTYPE html>
<html>
<head>
	<title>zip  압축  </title>
</head>
<style type="text/css">

	table {
		width : 500px;
		height: 80px;
		margin: auto; 
	}
	table, th, td {
		border : 1px solid black;
		text-align: center;
	}

</style>
<body>
	<div class="col-lg-10 well" style="width: 500px; margin: auto;">
		<div class="row">
		<form id="UploadForm" name="UploadForm" enctype="multipart/form-data" method="post"  >
			<input id="zipopen" type="file" name="Open" /></input>
			<input type="button" value="upload" onclick="zipOpen();"/>
		</form>
		</div>
	</div>


	 <fieldset>
        <legend>트리뷰 구현하기</legend>
        <ul>
            <li>게시판</li>
            <li>자바과정-기초
                <ul>
                    <li>기본문법</li>
                    <li>AWT/SWING</li>
                    <li>JDBC</li>
                    <li>자바예제</li>
                    <li>자바복습</li>
                </ul>
           </li>
           <li>웹프로그래밍
                <ul>
                    <li>JSP&amp;Servlet</li>
                    <li>프레임워크
                        <ul>
                            <li>struts2(스트럿츠2)</li>
                            <li>Spring(스프링)</li>
                        </ul>
                    </li>
                </ul>
            </li>
        </ul>
   
    </fieldset>
</body>
<script src="http://malsup.github.com/jquery.form.js"></script>
<script type="text/javascript">
function zipOpen() {
	   
	var form =$('#UploadForm')[0];
	
	var formData = new FormData(form);
	
		  $.ajax({
		       enctype:"multipart/form-data",
		       method:"POST",
		       url: '${path}/zipOpen',
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
/* $(document).ready(function () {
    //[1] 리스트의 기본 모양 지정 : <ul>를 자식으로 가지지 않는 li의 블릿기호는 기본 모양
    $('li:not(:has(ul))').css({ cursor: 'default', 'list-style-image':'none'});
   
    //[2] 자식 요소를 갖는 li에 대해서는 블릿이미지를 plus.gif를 지정
    $('li:has(ul)') //자식 요소(ul)를 갖는 요소(li)에 대해서
        .css({cursor: 'pointer', 'list-style-image':'url(plus.gif)'})//+기호로 설정
        .children().hide(); //자식요소 숨기기
       
    //[3] +로 설정된 항목에 대해서 click이벤트 적용
    $('li:has(ul)').click(function(event){
                   
        //this == event.target으로 현재 선택된 개체에 대해서 처리
        if(this == event.target){
            //숨겨진 상태면 보이고 -기호로 설정 그렇지 않으면 숨기고 + 기호로 설정
              if ($(this).children().is(':hidden')) {
                // 보이기
                $(this).css('list-style-image', 'url(minus.gif)').children().slideDown();
            }
            else {
                // 숨기기
                $(this).css('list-style-image', 'url(plus.gif)').children().slideUp();
            }

        }
        return false;          
    });
}); */



</script>


	
</html>