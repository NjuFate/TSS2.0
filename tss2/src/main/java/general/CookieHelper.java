package general;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CookieHelper {
	public static String getCookieByName(String cookieName,HttpServletRequest request){
		String result = "";
		Cookie[] cookies = request.getCookies();//这样便可以获取一个cookie数组
		for(Cookie cookie : cookies){
		    String name = cookie.getName();// get the cookie name
		    if(name.equals(cookieName)){
		    	result = cookie.getValue();}
		    else{
		    	continue;
		    }
		}
		return result;
	}
	
	public static void addNewCookie(String cookieName,String cookieValue,HttpServletResponse response){
		Cookie cookie;
		cookie = new Cookie(cookieName,cookieValue );
		cookie.setPath("/");	
		response.addCookie(cookie);
	}
}
