package model;

public class Configuration {

	private boolean password;
	private boolean login;
	
	public Configuration() {
		setPassword(false);
		setLogin(false);
	}

	public boolean isPassword() {
		return password;
	}

	public void setPassword(boolean password) {
		this.password = password;
	}

	public boolean isLogin() {
		return login;
	}

	public void setLogin(boolean login) {
		this.login = login;
	}
}
