





function addPPTNotification(t){
	
	var $info = $("#time-lines");
	var $li = $("<li> </li>")
	$li.append('<div class="timeline-badge"><i class="fa fa-check"></i>'+
            '</div>' +
            '<div class="timeline-panel">'+
                '<div class="timeline-heading">' + 
                    '<h4 class="timeline-title">SE1 Chapter03</h4>'+
                    '<p><small class="text-muted"><i class="fa fa-clock-o"></i> 11 hours ago by liuqing</small>' +
                    '</p>'+
                '</div>'+
                '<div class="timeline-body">'+
                    '<p>一起来学Java吧</p>'+
                '</div>'+
           '</div>');
	$info.append($li);
}