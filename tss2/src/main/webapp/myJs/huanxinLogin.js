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
				//alert("成功登录");
				conn.setPresence();
				document.getElementById("infoIcon").setAttribute("class","fa fa-envelope fa-fw");
			},
			onTextMessage : function(message){
				var today=new Date();
				var h=today.getHours();
				var m=today.getMinutes();
				var time = h+":"+m;
				
				console.log(message);
				var messageContent = message.data;//文本消息体
				var from = message.from;//消息的发送者
				document.getElementById("infoIcon").setAttribute("class","fa fa-envelope-o fa-fw");
				var $messageLabel = $("#message");
				$messageLabel.append('<li>'+
                            '<a href="#">'+
                                '<div>'+
                                    '<strong>'+from+'</strong>'+
                                    '<span class="pull-right text-muted">'+
                                        '<em>'+time+'</em>'+
                                    '</span>'+
                                '</div>'+
                                '<div>'+messageContent+'</div>'+
                            '</a>'+
                        '</li>'+
                        '<li class="divider"></li>');
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