package po;

import java.util.List;

public class ScheduleItem {
	private String id;           //课程ID
	    private String name;		//课程名
	    private String message;
	    private String teachers;		//课程老师

		public String getId() {
			return id;
		}
		public void setId(String id) {
			this.id = id;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getMessage() {
			return message;
		}
		public void setMessage(String message) {
			this.message = message;
		}
		public String getTeachers() {
			return teachers;
		}
		public void setTeachers(String teachers) {
			this.teachers = teachers;
		}
	    
	    public String toString(){
	    	
	    	
	    	
	    	return name+ "\n"+ id +"\n"+teachers + "\n" + message;
	    }
	    
	
	
}

