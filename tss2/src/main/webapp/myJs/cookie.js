function createCookieWithTime(cname,cvalue,exdays){
	var d = new Date();
	d.setTime(d.getTime()+(exdays*24*60*60*1000));
	var expires = "expires="+d.toGMTString();
	document.cookie = cname + "=" + cvalue+";"+expires +";path=/";
}

function createCookie(cname,cvalue){
	document.cookie = cname + "=" + cvalue +";path=/";
}

function clearCookie(name){
 	var exp = new Date();
	exp.setTime(exp.getTime() - 1);
	var cval=getCookie(name);
	//if(cval)
	document.cookie= name + "="+cval+";expires="+exp.toGMTString()+";path=/";
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

