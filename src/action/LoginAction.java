package action;

import java.util.List;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

import info.Messages;
/**
 * ��ȡ��½����
 *
 */

public class LoginAction extends ActionSupport implements ModelDriven<Messages>{
	   Messages messages=new Messages();
	   List<String> hobbys=messages.getHobby();
		public String login() {
			System.out.println(messages.getName());
			for(String a:hobbys) {
				System.out.println(a);
			}
			System.out.println("����");
			return SUCCESS;
		}
		public Messages getMessages() {
			return messages;
		}
		public void setMessages(Messages messages) {
			this.messages = messages;
		}
		@Override
		public Messages getModel() {
			// TODO Auto-generated method stub
			return messages;
		}
			
}
