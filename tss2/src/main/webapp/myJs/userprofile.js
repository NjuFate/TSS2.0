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
function getUserProfile(deal){
	//alert("get");
	var req = createAjaxObj();
	req.open("get",baseUrl+"method=userInfo",true);
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


function fillUserProfile(data){
	document.getElementById("userName").setAttribute("value",data.nickName);
	document.getElementById("email").setAttribute("value",data.email);
	document.getElementById("tel").setAttribute("value",data.tel);
	document.getElementById("qq").setAttribute("value",data.qq);
	document.getElementById("major").setAttribute("value",data.major);
	document.getElementById("grade").setAttribute("value",data.grade);
	document.getElementById("educational_id").setAttribute("value",data.educational_ID);
	document.getElementById("educational_psw").setAttribute("value",data.educational_Psw);
}

getUserProfile(fillUserProfile);


