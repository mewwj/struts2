package info;

import java.util.List;

/**
 * @author ÍõÎÄ½Ü
 *
 */
public class Messages {
		     private String name;
		     private String password;
		     private String gender;
		     private List<String> hobby; 
		     
		     public Messages(String name, String password, String gender, List<String> hobby) {
				super();
				this.name = name;
				this.password = password;
				this.gender = gender;
				this.hobby = hobby;
			}
			public Messages() {
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public String getPassword() {
				return password;
			}
			public void setPassword(String password) {
				this.password = password;
			}
			public String getGender() {
				return gender;
			}
			public void setGender(String gender) {
						this.gender = gender;
			}
			public List<String> getHobby() {
				return hobby;
			}
			public void setHobby(List<String> hobby) {
				this.hobby = hobby;
			}
}
