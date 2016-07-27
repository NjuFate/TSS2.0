package logic.project;

import java.io.IOException;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.GetMethod;

import model.json.MessageBody;
import net.sf.json.JSONObject;

public class AssociateSource {
	public String getAssociatedId(String pptId){
		String url = "http://110.173.17.140:8080/api/relation/ppt/"+pptId;
		return getResponse(url);
	}
	
	public String getWikiInfo(String wikiId){
		String url = "http://121.42.184.4:8080/api/entry/"+wikiId;
		return getResponse(url);
	}
	
	public String getQuestionInfo(String qid){
		String url ="http://110.173.17.140:8080/api/question?id="+qid;
		return getResponse(url);
	}
	
	public String getResponse(String url){
		GetMethod get = new GetMethod(url);
		get.setRequestHeader("Content-Type","application/json;charset=UTF-8");
		//String token = getToken("https://a1.easemob.com/njufate/njufate/token","YXA6S74tkE4lEeaYET-XvTPfjw","YXA6AD8gVqnNDtelGKgvNvM4RXFFfag");
		//get.setRequestHeader("accept","application/json");

		get.releaseConnection();
		//
		HttpClient httpClient = new HttpClient();
		String response = null;
		try {
			httpClient.executeMethod(get);
			response = get.getResponseBodyAsString();
			//result = msgReciver.getTokenFromJsonStr(response);
			System.out.println(response);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return response;
	}
	
}
