<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="ERP.Department.model.vo.Department,ERP.Team.model.vo.Team, ERP.Employee.model.vo.Employee, ERP.Ward.model.vo.Ward
				,ERP.Position.model.vo.Position, java.util.ArrayList" %>
    
<%
	ArrayList<Employee> mList = (ArrayList<Employee>)request.getAttribute("mList");
	ArrayList<Department> dList = (ArrayList<Department>)request.getAttribute("dList");
	String authority = (String)request.getAttribute("authority");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<!--  아이콘 링크 -->
<link rel="icon" type="image/png" sizes="16x16"
	href="/NHMP/resources/ERP/images/common/favicon.png">



<script src="http://code.jquery.com/jquery-1.9.1.min.js"></script>
<script type="text/javascript">
 $(document).ready(function () {
	 var cnt = 0;
	 		$("#empIn").click(function(){
	 			//사원목록에서 선택적용 했을때
	 			
	 			if($(".empChk").is(":checked") === true){
	 				var arrayParam = new Array();

	 				$("input:checkbox[name=empChk]:checked").each(function(){

	 					arrayParam.push($(this).val());

	 				});
	 				
	 				
	 				$.ajax({
	 					url : "selAuEmp",
	 					data : { empid : arrayParam },
	 					traditional : true,
	 					type : "post",
	 					dataType : "json",
	 					success : function(data){
	 						
	 						var jsonStr = JSON.stringify(data);
	 						//string 을 json 객체로 바꿈
	 						var json = JSON.parse(jsonStr);
	 						
	 						var posArr = new Array();
	 						var deptArr = new Array();
	 						
	 						
	 						if(data != null){
	 							
	 							for(var i in json.list3){
	 								posArr.push(decodeURIComponent(json.list3[i].posname).replace(/\+/gi, " "));
	 								}
		 						
		 						for(var i in json.list2){
	 								deptArr.push(decodeURIComponent(json.list2[i].deptname).replace(/\+/gi, " "));
	 								}
		 						var li = 0;	
		 						for(var i in json.list1){
	 								var empName = decodeURIComponent(json.list1[i].empname).replace(/\+/gi, " ");
	 								
	 								$(".empInListTable").append("<tr class='em'id=empSel" + cnt + ">" + "<td style='padding:5px;'><input type='checkbox'style='width:17px;height:17px;' name='empChk2'value='" + json.list1[i].empid + "' class='empChk2'></td>" +
	 										"<td>" + empName + "</td><td>" + deptArr[li] + "</td><td>" + posArr[li] + "</td>" + 
	 									 	"</tr>");
	 								li++;
	 								}
		 						
	 							cnt++;
	 							
	 							return false;
	 							
	 						}
	 						
	 						
	 					},
	 					error :function(jqXHR, textStatus, errorThrown){
	 						
	 						console.log("error : " + textStatus);
	 					}
	 					
	 				});
                    
	 			}else{
	 				alert("선택된 행이 없습니다.");
	 			}
	 			var i = $("input[name=empChk]:checked").parents("tr");
                 i.remove();
	 			
	 		});
	 
	 		$("#empDel").click(function(){
	 			if($(".empChk").is(":checked") === true){
	 				var i = $("input[name=empChk]:checked").parents("tr");
	 				
	 				$(".empChk2-all").prop("checked",false);
                    i.remove();
	 			}else if($(".empChk2").is(":checked") === true){
	 				var i = $("input[name=empChk2]:checked").parents("tr");
	 				$(".empChk-all").prop("checked",false);
	 				$(".empChk2-all").prop("checked",false);
                    i.remove();
                    
	 			}else{
	 				alert("선택된 행이 없습니다.");
	 			}
	 		});
            
            //[2] 자식 요소를 갖는 li에 대해서는 블릿이미지를 plus.gif를 지정
            $('.organ li:has(ul)') //자식 요소(ul)를 갖는 요소(li)에 대해서
                .css({cursor: 'pointer', 'list-style-image':'url(/NHMP/resources/ERP/images/closeForder.PNG)'})//+기호로 설정
                .children().hide(); //자식요소 숨기기
                
             
               
               
            //[3] +로 설정된 항목에 대해서 click이벤트 적용
            $('.deptList').click(function(event){
            	var index = $(this).index();
            	if($(event.target).is(".deptList")){
            	//클릭된 해당부서 기본정보 부서명에 추가
            	$("#deptname").val($(this).text());
            	
            	//자식요소 유무 이프문
            	
            	
            	$.ajax({
      				url : "organlist",
      				data : { deptName : $(this).text().trim() },
      				type : "post",
      				dataType : "json",
      				success : function(data){
      					//전송 온 object 를 string 으로 바꿈
      					var jsonStr = JSON.stringify(data);
      					//string 을 json 객체로 바꿈
      					var json = JSON.parse(jsonStr);
      					//json 안에 list 가 들어있음.
      					for(var i in json.list){
      						
      						$("#deptcode").val(json.list[i].deptcode);
      						$("#deptcount").val(json.list[i].count);
      						if(json.list[i].teamname != null){
      						var teamName = decodeURIComponent(json.list[i].teamname).replace(/\+/gi, " ");
      						var li = $("<li class='teamList' id='t" + teamName + "'  onclick='team(this.id, " + i + ");'>" + 
      								teamName +  "<ul></ul></li>");
      						}
      						$('.deptList').eq(index).children().append(li);
      						$('.teamList').css({'list-style-image':'url(/NHMP/resources/ERP/images/closeForder.PNG)'});
      					}
      	
      					
      				},
      				error :function(jqXHR, textStatus, errorThrown){
      					console.log("error : " + textStatus);
      				}
      			});
            		
            	}
                //this == event.target으로 현재 선택된 개체에 대해서 처리
                if(this == event.target){
                    //숨겨진 상태면 보이고 -기호로 설정 그렇지 않으면 숨기고 + 기호로 설정
                      if ($(this).children("ul").is(':hidden')) {
                    	  
                    	// 보이기
                          $(this).css('list-style-image', 'url(/NHMP/resources/ERP/images/openForder.PNG)').children().slideDown();
                    	  
                        
                    }else {
                        // 숨기기
                        $(this).css('list-style-image', 'url(/NHMP/resources/ERP/images/closeForder.PNG)').children().slideUp();
                        $('.teamList').css('list-style-image', 'url(/NHMP/resources/ERP/images/closeForder.PNG)').children().slideUp();
                        
                    }
 
                }
                return false;          
            });
    $("#empChk-all").click(function(){
    	 $(".empChk").prop("checked",this.checked);
    });
    $(".empChk2-all").click(function(){
   	 $(".empChk2").prop("checked",this.checked);
   });
    
                
 });
function team(id, index){
	
	 var value = $("#"+id).text();
	 
	 if($(".insertAuEmp").children("ul") != null){
		 var i = $(".ad");
		 i.remove();
	 $.ajax({
			url : "insertAuEmp",
			data : { teamName : value },
			type : "post",
			dataType : "json",
			success : function(data){
				
				//전송 온 object 를 string 으로 바꿈
				var jsonStr = JSON.stringify(data);
				//string 을 json 객체로 바꿈
				var json = JSON.parse(jsonStr);
				$("#teamname").val(value);
				//json 안에 list 가 들어있음.
				if(json != null){
					for(var i in json.list1){
							
							var empName = decodeURIComponent(json.list1[i].empname).replace(/\+/gi, " ");
						
							
							$(".empListTable").append("<tr class='ad'id=emp" + i + ">" + "<td style='padding:5px;'><input type='checkbox'style='width:17px;height:17px;' name='empChk'value='" + json.list1[i].empid + "' class='empChk'></td>" +
									"<td>" + empName + "</td>" +
								 	"</tr>");
							}
						
						for(var i in json.list3){
							var posName = decodeURIComponent(json.list3[i].posname).replace(/\+/gi, " ");
							
							$("#emp"+ i).append("<td>" + posName + "</td>");
							
						}
						
						for(var i in json.list2){
							var deptName = decodeURIComponent(json.list2[i].deptname).replace(/\+/gi, " ");
							$("#emp"+ i).append("<td>" + deptName + "</td>");
							}
				}
				
			},
			error :function(jqXHR, textStatus, errorThrown){
				console.log("error : " + textStatus);
			}
		});
	 }
	 
	 if ($("#"+id).children("ul").is(':hidden')) {
		 $("#"+id).children().slideDown();
		 $("#"+id).css('list-style-image', 'url(/NHMP/resources/ERP/images/openForder.PNG)');
	     }else{
	    	$("#"+id).children().slideUp(); 
	    	 $("#"+id).css('list-style-image', 'url(/NHMP/resources/ERP/images/closeForder.PNG)')
	     }
	return false;  
 }
function trueAndFalse() {

			if($("input:checkbox[name=empChk2]").is(":checked") === false){
				alert("선택된사원목록의 사원을 선택해주세요.");	
				return false;
				
			}else{
				var trueAndFalseDel = confirm( '해당내용으로 변경하시겠습니까?' );
			    if(trueAndFalseDel != false){
			    	window.close();
			    	return true;
			    }else
			    	return false;
			}
		
    
    return true;
	}
</script>
<style>
body{
 font-family: Georgia, "맑은 고딕", serif;
 }
.Btn{
	
    display: inline-block;
    width:80px;
    background:#7571f9;
 	color:#fff;
  	border:none;
  	position:relative;
  	cursor:pointer;
  	transition:600ms ease all;
  	outline:none;
  	padding:1px;
  	border-radius:3px;
  	margin:4px;
}
.Btn:hover{
	 background:#fff;
  	 color:#7571f9;
  	 border: 1px solid #7571f9;
}
.organList{
		border: 1px solid gray;
		border-radius:5px;
		padding:20px;
		width:100%;
		height:500px;
		background:white;
	}
.dept-left{
	border-radius:3px;
	border: 1px solid gray;
	float:left;
	width:40%;
	padding:30px;
	height:450px;
	
}
.organ{
	border-radius:10px;
	padding:25px;
	border:1px solid gray;
	height:400px;
	width:270px;
	overflow:auto;
	font-weight: bold;
	margin-left:35px;
	
}
.organ li{
	width:250px;
}
.dept-right{
	border-radius:3px;
	
	float:right;
	width:49%;
	height:510px;
}
h2{
margin:0px;
}
.empList{
	border-radius:3px;
	border:1px solid gray;
	margin-left:3px;
	width:98%;
	height:220px;
	display:block;
	padding:3px;
}
.empInList{
	border-radius:3px;
	border:1px solid gray;
	margin-left:3px;
	width:98%;
	height:210px;
	display:block;
	padding:3px;
}
	table{
    	width:100%;
    	
    	
    }
    
    
    
    table th{
    	background: rgb(117, 113, 249, 0.5);
      	color:rgba(0, 0, 0, 1);
    	font-size: 13pt;
    	text-align:center;
    }
    table td{
    	
    	text-align:center;
    }
 .footerBtn{
 	width:100px;

    background-color: #7571f9;

    border: none;

    color:#fff;

	border-radius:3px;

    text-align: center;

    text-decoration: none;

    display: inline-block;

    font-size: 15px;

    cursor: pointer;
    
    box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);
 } 
input[type=checkbox]:checked{
	background-color: #666666;
}  
</style>
</head>
<body style="padding-right:50px; background:#F3F3F9;">
	<div class="organList">
		<div class="dept-left">
					<%if(dList != null){%>
						<div class="organ">
							<ul>
							<%for(Department d : dList){ %>
								<li class="deptList"><%= d.getDeptName()%>
								<ul></ul>
								</li>
					           <%} %>
					        </ul>
						</div>
					<%}else{ %>
						<div class="organ">
							<ul>
					            <li>간호과
					                <ul>
					                    <li>dd</li>
					                    <li>dd</li>
					                    <li>dd</li>
					                </ul> 
					           </li>
					           <li>가정의학과
					                <ul>
					                   
					                </ul>
					            </li>
					            <li>외과
					                <ul>
					                   
					                </ul>
					            </li>
					            <li>원무과
					                <ul>
					                   
					                </ul>
					            </li>
					            <li>총무과
					                <ul>
					                   
					                </ul>
					            </li>
					            <li>한방과
					                <ul>
					                   
					                </ul>
					            </li>
					        </ul>
						</div>
					<%} %>
				</div>
				<div class="dept-right">
					<div class="empList-header" style="width:100%; display:inline-block;">
					<div class="emp-header-left" style=" display:inline-block; float:left;"><h2>사원목록</h2></div>
					<div class="emp-header-right" style=" margin:0px;  float:right;"><button id="empDel"class="Btn" style="margin-top:10px;">선택삭제</button>&nbsp;<button class="Btn"id="empIn"style="margin:0px;">선택적용</button></div>
					</div>
					<div class="empList">
						<table class="empListTable" width="100%" cellpadding="2" cellspacing="0" border="1">
							<tr>
								<th><input type="checkbox" id="empChk-all" style="width:17px;height:17px;"></th>
								<th>이름</th>
								<th>병동</th>
								<th>직급</th>
							</tr>
						</table>
					</div>
					<h2>선택된 사원목록</h2>
					<div class="empInList">
					<form action="/NHMP/authorUp" method="post" onsubmit="return trueAndFalse();">
						<input type="hidden" value="<%=authority%>" name="authority">
						<table class="empInListTable" width="100%" cellpadding="2" cellspacing="0" border="1">
							<tr>
								<th><input type="checkbox" class="empChk2-all" style="width:17px;height:17px;"></th>
								<th>이름</th>
								<th>직급</th>
								<th>병동</th>
							</tr>
						</table>
					</div>
				</div>
			</div>
				<div class="submit" style="margin-left:410px; margin-top:5px;"><input type="submit" value="적용" class="footerBtn">&nbsp;<input type="button" onclick="javascript:self.close();"value="취소"id="resetBtn" class="footerBtn"></div>
				</form>
			
</body>
</html>
