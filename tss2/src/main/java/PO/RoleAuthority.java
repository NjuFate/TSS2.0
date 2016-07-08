package PO;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class RoleAuthority {
	private String RCode;
	private String FatherRCode;
	private Map<String, ModuleAuthority> module;
	
	public String toString(){
		String result = "RCode: " + RCode;
		for(String m : module.keySet()){
			result = result + " " + m;
		}
		return result;
	}
	
	
	public RoleAuthority(){
		module = new HashMap<String, ModuleAuthority>();
	}
	
	
	public String getRCode() {
		return RCode;
	}
	public void setRCode(String rCode) {
		RCode = rCode;
	}
	public String getFatherRCode() {
		return FatherRCode;
	}
	public void setFatherCode(String rName) {
		FatherRCode = rName;
	}
	public Iterator<ModuleAuthority> getModule() {
		return module.values().iterator();
	}
	
	public boolean isHold(String MCode){
		return module.containsKey(MCode);
	}
	
	public boolean isHold(String MCode, String ACode){
		return module.containsKey(MCode) && module.get(MCode).isHold(ACode);
	}
	
	public void setModule(String MCode, ModuleAuthority value) {
		module.put(MCode, value);
	}
	
	
	
	

}
