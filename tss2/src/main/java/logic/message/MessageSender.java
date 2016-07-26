package logic.message;

import java.io.IOException;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import model.json.Message;
import model.json.MessageBody;
import model.json.TokenBody;
import net.sf.json.JSONObject;



public class MessageSender {
	//private static Logger logger = LoggerFactory.getLogger(MessageSender.class);
	MessageReciver msgReciver = new MessageReciver();
	
	/**
	 * token 取到后会持续7天但是每次取得值不一样
	 * @param url
	 * @param client_id
	 * @param client_secret
	 * @return
	 */
	public String getToken(String url,String client_id,String client_secret){
		PostMethod post = new PostMethod(url);
		post.setRequestHeader("Content-Type","application/json");
		//post.setRequestHeader("Authorization","Bearer YWMto-SQ1k8LEeaW8sVQnPJZHQAAAVdBHRyTueCPo41HFjRucAhEy7LcvorkyvY");
		TokenBody obj = new TokenBody();
		obj.setGrant_type("client_credentials");
		obj.setClient_id(client_id);
		obj.setClient_secret(client_secret);
		//
		JSONObject json = JSONObject.fromObject(obj);//将java对象转换为json对象  
	    String str = json.toString();
		post.setRequestBody(str);
		post.releaseConnection();
		
		HttpClient httpClient = new HttpClient();
		String result = null;
		try {
			httpClient.executeMethod(post);
			String response = post.getResponseBodyAsString();
			result = msgReciver.getTokenFromJsonStr(response);
			//System.out.println(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
	public  String sendMessage(Message msg,String[] target,String from){
		String url = "https://a1.easemob.com/njufate/njufate/messages";
		PostMethod post = new PostMethod(url);
		post.setRequestHeader("Content-Type","application/json;charset=UTF-8");
//		//
//		String url = "https://a1.easemob.com/njufate/njufate/token";
//		String client_id = "YXA6S74tkE4lEeaYET-XvTPfjw";
//		String client_secret = "YXA6AD8gVqnNDtelGKgvNvM4RXFFfag";
		String token = getToken("https://a1.easemob.com/njufate/njufate/token","YXA6S74tkE4lEeaYET-XvTPfjw","YXA6AD8gVqnNDtelGKgvNvM4RXFFfag");
		post.setRequestHeader("Authorization","Bearer "+token);
		
		//
		MessageBody msgBody = new MessageBody();
		msgBody.setMsg(msg);
		msgBody.setTarget(target);
		msgBody.setTarget_type("users");
		msgBody.setFrom(from);
	
		JSONObject json = JSONObject.fromObject(msgBody);//将java对象转换为json对象  
	    String str = json.toString();
		post.setRequestBody(str);
		post.releaseConnection();
		//
		HttpClient httpClient = new HttpClient();
		String result = null;
		try {
			httpClient.executeMethod(post);
			String response = post.getResponseBodyAsString();
			//result = msgReciver.getTokenFromJsonStr(response);
			System.out.println(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	
//	public static void main(String agrs[]){
//		MessageSender msgSender = new MessageSender();
//		String token = msgSender.getToken("https://a1.easemob.com/njufate/njufate/token","YXA6S74tkE4lEeaYET-XvTPfjw","YXA6AD8gVqnNDtelGKgvNvM4RXFFfag");
//		System.out.println(token);
//	}
	
	
	
	
		
	
}
