var linkUrl = "http://139.129.54.63/tss2/api/pptTimeLine";
var timeline  = new Array("","timeline-inverted");
var timestyle = new Array("timeline-badge","timeline-badge warning","timeline-badge danger","timeline-badge info","timeline-badge success");
var istyle = new Array("fa fa-check","fa fa-credit-card","fa fa-bomb","fa fa-save","fa fa-graduation-cap");

function createAjaxObj(){
	var req;
	if(window.XMLHttpRequest){
		req = new XMLHttpRequest();
	}else{
		req = new ActiveXObject("Msxml2.XMLHTTP");
	}
	return req;
}


function setFrame(addPPTNotification){
	var req = createAjaxObj();
	req.open("get",linkUrl,true);
	
	req.onreadystatechange = function(){
		if(req.readyState==4 && req.status==200){
            var obj = eval("("+req.responseText+")"); 
            
            for(i=0;i<obj.length;i++){
			addPPTNotification(timeline[i%2], obj[i]["courseno"], obj[i]["fileName"], obj[i]["updateTime"],obj[i]["uploadBy"]);
            }

		}else{
//			alert(req.readyState);
//			alert(req.status);
		}
	};
	req.send(null);
}



function addPPTNotification(timeline, courseno, fileName, updateTime, uploadBy){
	
	var $info = $("#time-lines");
	var $li = $("<li> </li>").addClass(timeline);
	$li.append('<div class="'+  timestyle[Math.floor(Math.random()*5)] +'"><i class="'+istyle[Math.floor(Math.random()*5)]+'"></i>'+
            '</div>' +
            '<div class="timeline-panel">'+
                '<div class="timeline-heading">' + 
                    '<h4 class="timeline-title">'+courseno+ ' '+ fileName + '</h4>'+
                    '<p><small class="text-muted"><i class="fa fa-clock-o"></i>'+ updateTime +  ' by ' + uploadBy +'</small>' +
                    '</p>'+
                '</div>'+
                '<div class="timeline-body">'+
                    '<p>no description</p>'+
                '</div>'+
           '</div>');
	$info.append($li);
}