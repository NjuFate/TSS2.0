var linkUrl = "http://110.173.17.140:8080/api/relation/ppt/";
var divClass = ("alert alert-success alert-dismissable","alert alert-info alert-dismissable","alert alert-warning alert-dismissable","alert alert-danger alert-dismissable");


function createAjaxObj(){
	var req;
	if(window.XMLHttpRequest){
		req = new XMLHttpRequest();
	}else{
		req = new ActiveXObject("Msxml2.XMLHTTP");
	}
	return req;
}


function doGet(url){ 
	// 注意在传参数值的时候最好使用encodeURI处理一下，以防出现乱码 
	var req = createAjaxObj(); 
	req.open("GET",url); 
//	req.setRequestHeader("accept","application/json");
	req.setRequestHeader("Access-Control-Allow-Origin", "*");
	req.send(null); 
	req.onreadystatechange = function(){
		if(req.readyState==4 && req.status==200){
			//alert(req.responseText);
			alert("var result="+req.responseText);
			
		}else{
			//alert(req.readyState);
			alert('fail');
		}
	};
}



    function CallJSONPServer(url){                                 // 调用JSONP服务器，url为请求服务器地址    
       var oldScript = document.getElementById(url);       // 如果页面中注册了调用的服务器，则重新调用
       if(oldScript){
       oldScript.setAttribute("src",url);
       return;
       }
       var script = document.createElement("script");       // 如果未注册该服务器，则注册并请求之
       script.setAttribute("type", "text/javascript");
       script.setAttribute("src",url);
       script.setAttribute("id", url);
       document.body.appendChild(script);
   }
  

    
   function lo(){
//	   $.getJSON("http://110.173.17.140:8080/api/relation/ppt/16072000000?callback=?", function(data) {
//            
////	        alert(json.pptId);
//	 	   alert("caonimeim");
//
//
//	    });
	   
	   $.ajax({
		     type:"GET",
		      url: 'http://139.129.54.63/tss2/api/ppt?id=16072000000',
		      dataType: 'jsonp',
		      success: function(data) {
		    	  alert('ok');
   }});
	   
   }




function wikilink(pptid){
	
	
	getProjectTable();
 	//创建div, button, a
	var div = document.createElement('div');
	var button = document.createElement('button');
	var a = document.createElement('a')


	button.setAttribute('class','close');
	button.setAttribute('type', 'button');
	button.setAttribute('data-dismiss','alert');
	button.setAttribute('aria-hidden', 'true');
	button.innerHTML = '&times;';


	a.setAttribute('href','#');
	a.setAttribute('class','alert-link');
	a.innerHTML = 'click me!';

	div.setAttribute('class','alert alert-success alert-dismissable')
	div.appendChild(button);
	div.innerHTML = 'fuck you ';
	div.appendChild(a);
	var element = document.getElementById('Wiki_Linking');
	element.appendChild(div); 
	return ;
}


function ruanhulink(id){
	
	
	
	
	
	
	
	
	
}




function createAjaxObj(){
	var req;
	if(window.XMLHttpRequest){
		req = new XMLHttpRequest();
	}else{
		req = new ActiveXObject("Msxml2.XMLHTTP");
	}
	return req;
}




function getProjectTable(){
	var req = createAjaxObj();
	req.open("get","http://139.129.54.63/tss2/api/ppt?id=16072000000",true);
	req.setRequestHeader("Access-Control-Allow-Origin", "*");
	req.onreadystatechange = function(){
		if(req.readyState==4 && req.status==200){
			//alert(req.responseText);
			eval("var result="+req.responseText);
		}else{
			//alert(req.readyState);
			//alert(req.status);
		}
	};
	req.send(null);
}