var baseUrl = "http://139.129.54.63/tss2/data.do?";
function createAjaxObj(){
	var req;
	if(window.XMLHttpRequest){
		req = new XMLHttpRequest();
	}else{
		req = new ActiveXObject("Msxml2.XMLHTTP");
	}
	return req;
}
function getDocTable(deal,courseno,father,deal2){
	var req = createAjaxObj();
	req.open("get",baseUrl+"method=fileList&courseno="+courseno+"&father="+father,true);
	req.setRequestHeader("accept","application/json");
	req.onreadystatechange = function(){
		if(req.readyState==4 && req.status==200){
			//alert(req.responseText);
			eval("var result="+req.responseText);
			deal(result);
			deal2(courseno,father);
		}else{
			//alert(req.readyState);
			//alert(req.status);
		}
	};
	req.send(null);
}

function openFolder(index){
	var courseno = getCookie("courseno");
	var x=document.getElementById('doclist-table').rows[index].cells;
	var father= x[0].innerText;
	getDocTable(fillDocTable,courseno,father,recordCoursenoAndFather);
}

function test(){
	//alert("get");
}

function fillDocTable(data){
	 var index=0; 
	 var $table = $("#doclist-table>tbody");
 	 $table.empty();
	 for(var i=0;i<data.length;i++){
	 	index ++;
		var $tr = $("<tr class='gradeA'> </tr>");
		//alert(data[i].folder);
 	    if(
 	    	data[i].isFolder){$tr.append($("<td><img border='0' src='../img/folder.jpg' width='20' height='20'><a id=index href='#' onclick='openFolder("+index+");'>"+data[i].fileName+"</td>")) ;}
 	    else{
 	    	$tr.append($("<td><img border='0' src='../img/doc.jpg' width='18' height='18'><a href=../downloadFile/download?path="+data[i].path+">"+data[i].fileName+"</td>"));
 	    }
	 	$tr.append($("<td></td>").text(data[i].updateTime));
	 	$tr.append($("<td></td>").text(data[i].uploadBy));
	 	$tr.append($("<td> <a id=index href='../pages/ppt_info'> click me</td>"));
     	$table.append($tr);
	 }
		
}

function getCookie(cname){
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for(var i=0; i<ca.length; i++) {
  		var c = ca[i].trim();
  		if (c.indexOf(name)==0) return c.substring(name.length,c.length);
  	}
	return "";
}

function recordCoursenoAndFather(courseno,father){
	document.getElementById('disabled_courseno').setAttribute("placeholder",courseno);
	document.getElementById('disabled_folder').setAttribute("placeholder",father);
	document.cookie = "courseno" + "=" + courseno +";path=/";
	//alert("get");
	document.cookie = "father" + "=" + father +";path=/";
	//alert("get");
	//alert(document.cookie);
}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
var courseno = getCookie("courseno");
var coursename = getCookie("coursename");
getDocTable(fillDocTable,courseno,coursename,recordCoursenoAndFather);