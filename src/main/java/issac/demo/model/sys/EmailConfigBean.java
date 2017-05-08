package issac.demo.model.sys;

public class EmailConfigBean extends SysConfigModuleBean {
	private String host;
	private String protocol = "smtp";
	private String port = "25";
	private String from;
	private String username;
	private String password;
	private String timeout = "10000";
	private String auth = "true";


	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public String getProtocol() {
		return protocol;
	}

	public void setProtocol(String protocol) {
		this.protocol = protocol;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getFrom() {
		return from;
	}

	public void setFrom(String from) {
		this.from = from;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getTimeout() {
		return timeout;
	}

	public void setTimeout(String timeout) {
		this.timeout = timeout;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	@Override
	public String toString() {
		return "EmailConfig [host=" + host + ", protocol=" + protocol + ", port=" + port + ", from=" + from + ", username=" + username + ", password=" + password + ", timeout=" + timeout + ", auth=" + auth + "]";
	}


}
