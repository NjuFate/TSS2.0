package logic.message;

import model.json.TokenResponse;
import net.sf.json.JSONObject;

public class MessageReciver {
	
	public String getTokenFromJsonStr(String jsonStr){
		if(jsonStr.indexOf("[") != -1){  
            jsonStr = jsonStr.replace("[", "");  
        }  
        if(jsonStr.indexOf("]") != -1){  
            jsonStr = jsonStr.replace("]", "");  
        }
        JSONObject obj = new JSONObject().fromObject(jsonStr);
        TokenResponse jb = (TokenResponse)JSONObject.toBean(obj,TokenResponse.class);//将建json对象转换为Person对象  
        return jb.getAccess_token();//返回一个Person对象  
       
	}
}
