var linkUrl = "http://localhost:8888/tss2/api/relation?pptId=";
var wikiUrl = "http://localhost:8888/tss2/api/entry?wikiId=";
var questionUrl = "http://localhost:8888/tss2/api/question?qId=";
var divClass = new Array("alert alert-success alert-dismissable","alert alert-info alert-dismissable","alert alert-warning alert-dismissable","alert alert-danger alert-dismissable");

//function Get(){	
//alert('ko');
//$.ajax({
//type:"GET",
//url: 'http://localhost:8888/tss2/api/relation?pptId=16072000000?callback=?',
//dataType: 'jsonp',
//success: function(data) {
//alert('ok');
//}});

//}



function createAjaxObj(){
	var req;
	if(window.XMLHttpRequest){
		req = new XMLHttpRequest();
	}else{
		req = new ActiveXObject("Msxml2.XMLHTTP");
	}
	return req;
}


function setFrame(addWiki, addQuestion, addWikiNode, addQuestionNode, id){
	var req = createAjaxObj();
	req.open("get",linkUrl + id,true);
	
	req.onreadystatechange = function(){
		if(req.readyState==4 && req.status==200){
            var obj = eval("("+req.responseText+")"); 
            
            for(i=0;i<obj["wikiIds"].length;i++)
            addWiki(addWikiNode, wikiUrl, obj["wikiIds"], i);
           
            for(i=0;i<obj["questionIds"].length;i++)
			addQuestion(addQuestionNode, questionUrl, obj["questionIds"], i);

		}else{
//			alert(req.readyState);
//			alert(req.status);
		}
	};
	req.send(null);
}

function addWiki(addNode, baseUrl, idList, i){
	var req = createAjaxObj();
	req.open("get",baseUrl + idList[i],true);
	req.setRequestHeader("accept", "application/json;charset=UTF-8")
	req.onreadystatechange = function(){
		if(req.readyState==4 && req.status==200){
            var obj = eval("("+req.responseText+")");         
            addNode(obj["title"], obj["url"], obj["summary"]);
		}else{
//			alert(req.readyState);
//			alert(req.status);
		}
	};
	req.send(null);
	
}

function addQuestion(addNode, baseUrl, idList, i){
	var req = createAjaxObj();
	req.open("get",baseUrl + idList[i],true);
	req.setRequestHeader("accept", "application/json;charset=UTF-8")
	req.onreadystatechange = function(){
		if(req.readyState==4 && req.status==200){
            var obj = eval("("+req.responseText+")");         
            addNode(obj["title"], obj["questionUrl"]);
		}else{
//			alert(req.readyState);
//			alert(req.status);
		}
	};
	req.send(null);
	
}




function addWikiNode(title, url, summary){
	var $info = $("#wiki-link");
	var $div = $("<div> </div>").addClass(divClass[Math.floor(Math.random()*4)]);
	$div.append('<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>'+
			'<h4>' + 
			title + 
			'</h4>' +			
			summary + " " + 
			'<a href="' + url + '" target="_blank" class="alert-link">link-to-it'+
	'</a>');
	$info.append($div);

}

function addQuestionNode(title, url){
	var $info = $("#ruan-link");
	var $div = $("<div> </div>").addClass(divClass[Math.floor(Math.random()*4)]);
	$div.append('<button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>'+
			title + " " +
			'<a href="' + url + '" target="_blank" class="alert-link">link-to-it'+
	'</a>');
	$info.append($div);
}


function addPPTNode(name, title , url){
	var $info = $("#ppt-line");
	var $div = $("<div> </div>").addClass("panel panel-default");
	$div.append(
	 '<div class="panel-heading">' + 
	'<h4 class="panel-title">' + 
		'<a data-toggle="collapse" data-parent="#accordion"' +
			'href="#collapseOne">' + name + '</a>' +
	'</h4>' +
  '</div>' +
'<div id="collapseOne" class="panel-collapse collapse in">' +
	'<div class="panel-body">'+ title + " " +'<a href="' + url + '" target="_blank" class="alert-link">link-to-it'+
	'</a>' +
	'</div>' + '</div>');
	$info.append($div);
	

}