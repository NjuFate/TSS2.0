package logic.schedule;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.impl.cookie.PublicSuffixDomainFilter;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import po.ScheduleItem;

public class ScheduleProvider {


	private static final String URL = "http://desktop.nju.edu.cn:8080/jiaowu/login.do";//访问的登陆网址
	private static final String SURL = "http://desktop.nju.edu.cn:8080/jiaowu/student/teachinginfo/courseList.do?method=currentTermCourse";
	private static HttpClient httpClient;

	/** 
	 * 登陆到教务系统 
	 * @author xuan
	 * @param userName 用户名
	 * @param password 密码
	 * @return 成功返回true 失败返回false
	 *  
	 */  
	public boolean login(String userName,String password){  
		httpClient = new DefaultHttpClient(new ThreadSafeClientConnManager());


		HttpPost httpost = new HttpPost(URL);  
		List<NameValuePair> nvps = new ArrayList<NameValuePair>();

		nvps.add(new BasicNameValuePair("userName", userName));   
		nvps.add(new BasicNameValuePair("password", password));   
		nvps.add(new BasicNameValuePair("returnUrl", "null"));  

		/*设置字符*/  
		httpost.setEntity(new UrlEncodedFormEntity(nvps, Consts.UTF_8));  

		/*尝试登陆*/  
		HttpResponse response;  
		try {     
			response = httpClient.execute(httpost);  

			/*验证是否请求和响应都成功*/
			if(response.getStatusLine().getStatusCode() == 200){  
				return true;  
			}else{  
				httpClient = null;
				return false;  
			}  
		} catch (ClientProtocolException e) {  
			e.printStackTrace();  
		} catch (IOException e) {  
			e.printStackTrace();  
		}  

		return false;  
	}


	public List<ScheduleItem> getAllCourses(String userName,String password){
		if(!login(userName, password))
			return null;
		/*检查是否已经登陆成功*/  
		if(httpClient==null)
			return null;

		HttpGet httpGet = new HttpGet(SURL);

		HttpResponse response;
		try {
			response = httpClient.execute(httpGet);
			String htmlTxt = null;
			/*验证是否请求和响应都成功*/
			if(response.getStatusLine().getStatusCode() == 200){
				HttpEntity entity = response.getEntity();
				htmlTxt = EntityUtils.toString(entity, "utf-8");
			}else {
				return null;
			}
			List<ScheduleItem>schedule = new ArrayList<ScheduleItem>();
			Document doc = Jsoup.parse(htmlTxt);
			Elements trs = doc.select("table").select("tr");
			for(int i = 3;i<trs.size() - 1;i++){ //i的具体参数需要参照htmlTxt的格式
				Elements tds = trs.get(i).select("td");
				try {
					ScheduleItem item = new ScheduleItem();
					item.setId(tds.get(0).text());
					item.setName(tds.get(2).text());
					item.setTeachers(tds.get(4).text());
					item.setMessage(tds.get(5).text());
					schedule.add(item);
				}catch (Exception e) {
					// TODO: handle exception
					continue;
				}
			}
			return schedule;
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		return null;
	}


}



