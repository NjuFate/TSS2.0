$(function(){
		var conn = null;
		conn = new Easemob.im.Connection();
		
		var username = getCookie("haccount");
		var pass = getCookie("psw");
		conn.open({
			user : username,
			pwd : pass,
			appKey : 'njufate#njufate'
		});
		
		conn.init({
			onOpened : function(){
				alert("成功登录");
				conn.setPresence();
			},
			onTextMessage : function(message){
				console.log(message);
				var messageContent = message.data;//文本消息体
				var from = message.from;//消息的发送者
				alert(message);
				alert(from);
				alert("成功收到消息");
			}
			
		});	
	});
	



function getCookie(cname){
	var name = cname + "=";
	var ca = document.cookie.split(';');
	for(var i=0; i<ca.length; i++) {
  		var c = ca[i].trim();
  		if (c.indexOf(name)==0) return c.substring(name.length,c.length);
  	}
	return "";
}