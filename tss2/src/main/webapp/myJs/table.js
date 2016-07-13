var baseUrl = "http://localhost:8080/tss2/data.do?";
function createAjaxObj(){
	var req;
	if(window.XMLHttpRequest){
		req = new XMLHttpRequest();
	}else{
		req = new ActiveXObject("Msxml2.XMLHTTP");
	}
	return req;
}
function getProjectTable(deal){
	var req = createAjaxObj();
	req.open("get",baseUrl+"method=projectList&account=123456",true);
	req.setRequestHeader("accept","application/json");
	req.onreadystatechange = function(){
		if(req.readyState==4 && req.status==200){
			//alert(req.responseText);
			eval("var result="+req.responseText);
			deal(result);
		}else{
			//alert(req.readyState);
			//alert(req.status);
		}
	};
	req.send(null);
}

function fillProjectTable(data){
	 var $table = $("#course-table>tbody");
 	 $table.empty();
	 for(var i=0;i<data.length;i++){
		var $tr = $("<tr> </tr>").addClass("gradeA");
 	    //alert($table)
 	    //alert($tr)
	 	$tr.append($("<td><a href='#' onclick='getSourceDoc();'>"+data[i].courseno+"</td>"));
	 	$tr.append($("<td><a href='#' onclick='getSourceDoc();'>"+data[i].coursename+"</td>"));
	 	$tr.append($("<td></td>").text(data[i].instrutor));
     	$tr.append($("<td></td>").text(data[i].semester));
     	$tr.append($("<td></td>").text(data[i].teaching_assistants));
     	$table.append($tr);
	 }
		
}

function getSourceDoc(){
	window.location.href="../views/teacher_upload.html";
}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
