function sendMessage(postData){
//var postData ='{"message":"hello","account":["123","123"],"source":"wiki"}';
//;
 
// postData = (function(obj){ // 转成post需要的字符串.
    // var str = "";
//  
    // for(var prop in obj){
        // str += prop + "=" + obj[prop] + "&";
    // }
    // return str;
// })(postData);
 
var xhr = new XMLHttpRequest();
 
xhr.open("POST", "../api/notification", true);
xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");
xhr.onreadystatechange = function(){
    var XMLHttpReq = xhr;
    if (XMLHttpReq.readyState == 4) {
        if (XMLHttpReq.status == 200) {
            var text = XMLHttpReq.responseText;
 
            console.log(text);
        }
    }
};
xhr.send(postData);
}
