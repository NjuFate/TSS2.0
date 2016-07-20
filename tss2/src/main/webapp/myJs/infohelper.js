function addNotification(message,sender,time){
	
	var $info = $("#infobody");
	var $li = $("<li> </li>").addClass("left clearfix");
	$li.append('<span class="chat-img pull-left"> <img src="http://placehold.it/50/55C1E7/fff" alt="User Avatar" class="img-circle" /> </span>'+
	'<div class="chat-body clearfix">' +
                                        '<div class="header">'+
                                            '<strong class="primary-font">'+sender+'</strong>'+
                                            '<small class="pull-right text-muted">'+
                                                '<i class="fa fa-clock-o fa-fw"></i> '+ time +
                                            '</small>'+
                                      	'</div>'+
                                        '<p>'+
                                            message+
                                        '</p>'+
    '</div>');
	$info.append($li);
}

