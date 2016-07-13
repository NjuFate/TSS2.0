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
function getDocTable(deal){
	var req = createAjaxObj();
	req.open("get",baseUrl+"method=fileList&courseno=123123&coursename=SE&semester=2016fall&father=coursno",true);
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

function openFolder(index){
	var x=document.getElementById('doclist-table').rows[index].cells;
	var fileName = x[0].innerText;
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
 	    	data[i].folder){$tr.append($("<td><img border='0' src='../img/folder.jpg' width='20' height='20'><a id=index href='#' onclick='openFolder("+index+");'>"+data[i].fileName+"</td>")) ;}
 	    else{
 	    	$tr.append($("<td><img border='0' src='../img/doc.jpg' width='18' height='18'><a href="+data[i].path+">"+data[i].fileName+"</td>"));
 	    }
	 	$tr.append($("<td></td>").text(data[i].updateTime));
	 	$tr.append($("<td></td>").text(data[i].uploadBy));
     	//$tr.append($("<td></td>").text(data[i].semester));
     	//$tr.append($("<td></td>").text(data[i].teaching_assistants));
     	$table.append($tr);
	 }
		
}

//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++