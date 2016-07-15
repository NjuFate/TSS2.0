package logic.schedule;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.httpclient.Cookie;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.cookie.CookiePolicy;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.httpclient.methods.PostMethod;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import po.ScheduleItem;

public class ScheduleProvider {
	
    // 登陆 Url  
    private static final String loginUrl = "http://jwas2.nju.edu.cn:8080/jiaowu/login.do";  
    // 需登陆后访问的 Url  
    private static final String dataUrl = "http://jwas2.nju.edu.cn:8080/jiaowu/student/teachinginfo/courseList.do?method=currentTermCourse";  

    
    public List<ScheduleItem> getAllCourses(String userName, String password){
    	List<ScheduleItem>schedule;
    	String tmpcookies = null;
    	HttpClient httpClient = null;
        PostMethod postMethod = null;
    
    
    try {  
        // 设置 HttpClient 接收 Cookie,用与浏览器一样的策略  
    	CookieClient client = getCookieClient(userName, password);
    	tmpcookies = client.getCookie();
    	httpClient = client.getHttpClient();
    	postMethod = client.getPostMethod();
        // 进行登陆后的操作
        GetMethod getMethod = new GetMethod(dataUrl);  
        // 每次访问需授权的网址时需带上前面的 cookie 作为通行证  
        getMethod.setRequestHeader("cookie", tmpcookies);  
        // 你还可以通过 PostMethod/GetMethod 设置更多的请求后数据  
        // 例如，referer 从哪里来的，UA 像搜索引擎都会表名自己是谁，无良搜索引擎除外  
        postMethod.setRequestHeader("Referer", "http://www.cc");  
        postMethod.setRequestHeader("User-Agent", "www Spot");  
        httpClient.executeMethod(getMethod);  

        InputStream inputStream = getMethod.getResponseBodyAsStream();   
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream));   
        StringBuffer stringBuffer = new StringBuffer();   
        String str= "";   
        while((str = br.readLine()) != null){   
            stringBuffer.append(str+"\n");   
        }   
        String text = stringBuffer.toString();
        schedule = new ArrayList<ScheduleItem>();
        Document doc = Jsoup.parse(text);
        Elements trs = doc.select("table").select("tr");
        for(int i = 3;i<trs.size() - 1;i++){
            Elements tds = trs.get(i).select("td");
            ScheduleItem item = new ScheduleItem();
            item.setId(tds.get(0).text());
            item.setName(tds.get(2).text());
            item.setTeachers(tds.get(4).text());
            item.setMessage(tds.get(5).text());
            schedule.add(item);
            
//            for(int j = 0;j<tds.size();j++){	
//                String t = tds.get(j).text();
//                System.out.println(t);
//            }
            
        }
        return schedule;
    } catch (Exception e) {  
        e.printStackTrace();  
    }  
   return null;
    }
    
    
    public boolean login(String userName, String password){
    	CookieClient client = getCookieClient(userName, password);
    	String cookie = client.getCookie();
    	return cookie.contains(userName);
    }

    
    private CookieClient getCookieClient(String userName, String password){
    	
    	HttpClient httpClient = new HttpClient();  
        // 模拟登陆，按实际服务器端要求选用 Post 或 Get 请求方式  
        PostMethod postMethod = new PostMethod(loginUrl);  
        // 设置登陆时要求的信息，用户名和密码  
        NameValuePair[] data = { 
        		new NameValuePair("userName", userName),  
                new NameValuePair("password", password),
                new NameValuePair("returnUrl", null) };  
        postMethod.setRequestBody(data);  
        
        
        try {  
            // 设置 HttpClient 接收 Cookie,用与浏览器一样的策略  
            httpClient.getParams().setCookiePolicy(  
                    CookiePolicy.BROWSER_COMPATIBILITY);  
            httpClient.executeMethod(postMethod);  
            // 获得登陆后的 Cookie  
            Cookie[] cookies = httpClient.getState().getCookies();  
            StringBuffer tmpcookies = new StringBuffer();  
            for (Cookie c : cookies) {  
                tmpcookies.append(c.toString() + ";");  
            } 
            return new CookieClient(httpClient, postMethod, tmpcookies.toString());
            }catch (Exception e) {
				// TODO: handle exception
            	e.printStackTrace();
			} 
        return null;
    }
    
    
    
    public class CookieClient{
    	private HttpClient httpClient;
    	private PostMethod postMethod;
        private String cookie;
		public HttpClient getHttpClient() {
			return httpClient;
		}
		
		public CookieClient(HttpClient httpClient, PostMethod postMethod,String cookie) {
			// TODO Auto-generated constructor stub
			this.cookie = cookie;
			this.httpClient = httpClient;
			this.postMethod = postMethod;
		}
		
		public void setHttpClient(HttpClient httpClient) {
			this.httpClient = httpClient;
		}
		public String getCookie() {
			return cookie;
		}
		public void setCookie(String cookie) {
			this.cookie = cookie;
		}

		public PostMethod getPostMethod() {
			return postMethod;
		}

		public void setPostMethod(PostMethod postMethod) {
			this.postMethod = postMethod;
		}
    	
    	
    }
 
    
}  

