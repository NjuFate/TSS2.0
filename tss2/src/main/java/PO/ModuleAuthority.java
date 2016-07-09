package PO;

import java.util.ArrayList;
import java.util.Iterator;



public class ModuleAuthority {
	private int MID;
	private String MCode;
	private String MName;
	private int FatherMID;
	private String other;
	
	private ArrayList<String> privilege;
	
	
	public String toString(){
		String result = "MName:" + MName ;
		for(String p:privilege){
			result = result + " " + p;
		}
		return result;
		
	}
	
	public ModuleAuthority(){
		privilege = new ArrayList<String>();
	}

	
	public int getMID() {
		return MID;
	}
	public void setMID(int mID) {
		MID = mID;
	}
	public String getMCode() {
		return MCode;
	}
	public void setMCode(String mCode) {
		MCode = mCode;
	}
	public String getMName() {
		return MName;
	}
	public void setMName(String mName) {
		MName = mName;
	}
	public int getFatherMID() {
		return FatherMID;
	}
	public void setFatherMID(int fatherMID) {
		FatherMID = fatherMID;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	public Iterator<String> getPrivilege() {
		return privilege.iterator();
	}
	
	public boolean isHold(String ACode){
		return privilege.contains(ACode);
	}
	
	public void setPrivilege(String ACode) {
		privilege.add(ACode);
	}
	
	

}
