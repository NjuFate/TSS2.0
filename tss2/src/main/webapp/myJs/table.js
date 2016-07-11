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
	req.open("get","http://localhost:8080/tss2/data.do?method=projectList&account=123456",true);
	req.setRequestHeader("accept","application/json");
	req.onreadystatechange = function(){
		if(req.readyState==4 && req.status==200){
			alert(req.responseText);
			eval("var result="+req.responseText);
			alert(result);
		}else{
			//alert(req.readyState);
			//alert(req.status);
		}
	};
	req.send(null);
}



//+++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++
function statistics(deal,stockID){
	var req = createAjaxObj();
    req.open("get",baseUrl+"method=statistics&stockID="+stockID,false);
    req.setRequestHeader("accept","application/json");
    req.onreadystatechange = function(){
        if (req.readyState==4 && req.status == 200) {
        	eval("var result="+req.responseText);       	
            baseData = [];
            if(stockID=="grail"){
            baseData.push(2323);
            baseData.push(result.increase);
            baseData.push(result.increaseValue);
            baseData.push(result.open);
            baseData.push(result.close);
            baseData.push(result.high);
            baseData.push(result.low);
            baseData.push(result.volume);
            baseData.push(result.volumeValue);
            }
            else{
			baseData.push(result.id);
			baseData.push(result.name);
			baseData.push(result.close);
			baseData.push(result.increase);
			baseData.push(result.increaseValue);
			baseData.push(result.open);
			baseData.push(result.close);
			baseData.push(result.swing);
			baseData.push(result.high);
			baseData.push(result.low);
			baseData.push(result.handchange);
			baseData.push(result.volume);
			baseData.push(result.volumeValue);
            }
            deal(baseData);
            
        }
    };
    req.send(null);
}