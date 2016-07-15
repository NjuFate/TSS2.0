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
function getProjectTable(deal,account){
	var req = createAjaxObj();
	req.open("get",baseUrl+"method=projectList&account="+account,true);
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
	 index = 0;
	 var $table = $("#course-table>tbody");
 	 $table.empty();
	 for(var i=0;i<data.length;i++){
	 	index ++;
		var $tr = $("<tr> </tr>");
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
	var x=document.getElementById('course-table').rows[index].cells;
	var courseno= x[0].innerText;
	var coursename=x[1].innerText;
	document.cookie = "courseno" + "=" + courseno +";path=/";
	document.cookie = "coursename" + "=" + coursename +";path=/";
	window.location.href="../views/teacher_upload.html";
}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
