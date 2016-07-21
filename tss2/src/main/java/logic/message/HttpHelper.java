package logic.message;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;

import model.json.Message;
import model.json.MessageBody;
import model.json.TokenBody;
import net.sf.json.JSONObject;

public class HttpHelper {
	private static HttpMethod getMethod(String url,String param)throws IOException{
		GetMethod get = new GetMethod(url+"?"+param);
		get.releaseConnection();
		return get;
	}
	
	private static HttpMethod postMethod(String url)throws IOException{
		PostMethod post = new PostMethod(url);
		post.setRequestHeader("Content-Type","application/json");
		post.setRequestHeader("Authorization","Bearer YWMto-SQ1k8LEeaW8sVQnPJZHQAAAVdBHRyTueCPo41HFjRucAhEy7LcvorkyvY");
		TokenBody obj = new TokenBody();
		obj.setGrant_type("client_credentials");
		obj.setClient_id("YXA6S74tkE4lEeaYET-XvTPfjw");
		obj.setClient_secret("YXA6AD8gVqnNDtelGKgvNvM4RXFFfag");
		//
		MessageBody msgBody = new MessageBody();
		Message msg = new Message();
		msg.setType("txt");
		msg.setMsg("hello");
		msgBody.setFrom("admin");
		msgBody.setMsg(msg);
		String[] target ={"xiaohong","xiaoming"};
		msgBody.setTarget(target);
		msgBody.setTarget_type("users");
		
		JSONObject json = JSONObject.fromObject(msgBody);//将java对象转换为json对象  
	    String str = json.toString();
		post.setRequestBody(str);
		post.releaseConnection();
		return post;
	}
	
	
	public void HttpContoller(){
		String url = "https://a1.easemob.com/njufate/njufate/messages";
		String param = "{'grant_type':'client_credentials','client_id':'YXA6S74tkE4lEeaYET-XvTPfjw','client_secret':'YXA6AD8gVqnNDtelGKgvNvM4RXFFfag'}";
		HttpMethod method = null;
		HttpClient httpClient = new HttpClient();
		try {
			method = postMethod(url);
			httpClient.executeMethod(method);
			String response = method.getResponseBodyAsString();
			System.out.println(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static void main(String args[]){
		HttpHelper httpHelper = new HttpHelper();
		httpHelper.HttpContoller();
	}
}
