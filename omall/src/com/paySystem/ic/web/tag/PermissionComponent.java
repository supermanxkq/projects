package com.paySystem.ic.web.tag;

import java.io.Writer;
import javax.servlet.http.HttpServletRequest;
import org.apache.struts2.components.Component;
import com.opensymphony.xwork2.util.ValueStack;
import com.paySystem.ic.utils.Utils;
import com.paySystem.ic.web.dto.system.UserSession;

public class PermissionComponent extends Component {

	private String key;
	private String value;
	private UserSession userSession = new UserSession();

	public UserSession getUserSession() {
		return userSession;
	}

	public void setUserSession(UserSession userSession) {
		this.userSession = userSession;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public PermissionComponent(ValueStack stack, HttpServletRequest request) {
		super(stack);
	}

	@Override
	public boolean end(Writer writer, String body) {
		return super.end(writer, body);
	}

	@Override
	public boolean start(Writer writer) {
		if (Utils.checkPermission(key)) {
			return true;
		}
		return false;
	}

}
