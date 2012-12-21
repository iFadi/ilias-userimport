package model;

/**
 * 
 * Configuration.java
 * 
 * @author Fadi Asbih
 * @email fadi_asbih@yahoo.de
 * @version 1.2.0  04/07/2012
 * @copyright 2012
 * 
 * TERMS AND CONDITIONS:
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 * 
 */
public class Configuration {

	private boolean password; 
	private boolean generateLogin; // Generate Login based on firstname.lastname
	private boolean dummy; //Generate dummy data for testing.
	private String firstNameLabel; 
	private String lastNameLabel;
	private String loginLabel;
	private String globalRoleLabel;
	private String globalLocalLabel;
	private String matriculationLabel;
	private String passwordLabel;
	private String genderLabel;
	private String emailLabel;
	
	public Configuration() {
		setLoginLabel("Login");
		setPasswordLabel("Password");
		setFirstNameLabel("Firstname");
		setLastNameLabel("Lastname");
		setGlobalRoleLabel("Role");
		setMatriculationLabel("Matriculation");
		setEmailLabel("Email");
		setGenderLabel("Gender");
		setPassword(true);
		setGenerateLogin(true);
	}

	public boolean isPassword() {
		return password;
	}

	public void setPassword(boolean password) {
		this.password = password;
	}

	public boolean isDummy() {
		return dummy;
	}

	public void setDummy(boolean dummy) {
		this.dummy = dummy;
	}

	/**
	 * @return the firstNameLabel
	 */
	public String getFirstNameLabel() {
		return firstNameLabel;
	}

	/**
	 * @param firstNameLabel the firstNameLabel to set
	 */
	public void setFirstNameLabel(String firstNameLabel) {
		this.firstNameLabel = firstNameLabel;
	}

	/**
	 * @return the lastNameLabel
	 */
	public String getLastNameLabel() {
		return lastNameLabel;
	}

	/**
	 * @param lastNameLabel the lastNameLabel to set
	 */
	public void setLastNameLabel(String lastNameLabel) {
		this.lastNameLabel = lastNameLabel;
	}

	/**
	 * @return the generateLogin
	 */
	public boolean isGenerateLogin() {
		return generateLogin;
	}

	/**
	 * @param generateLogin the generateLogin to set
	 */
	public void setGenerateLogin(boolean generateLogin) {
		this.generateLogin = generateLogin;
	}

	/**
	 * @return the loginLabel
	 */
	public String getLoginLabel() {
		return loginLabel;
	}

	/**
	 * @param loginLabel the loginLabel to set
	 */
	public void setLoginLabel(String loginLabel) {
		this.loginLabel = loginLabel;
	}

	/**
	 * @return the globalRoleLabel
	 */
	public String getGlobalRoleLabel() {
		return globalRoleLabel;
	}

	/**
	 * @param globalRoleLabel the globalRoleLabel to set
	 */
	public void setGlobalRoleLabel(String globalRoleLabel) {
		this.globalRoleLabel = globalRoleLabel;
	}

	/**
	 * @return the globalLocalLabel
	 */
	public String getGlobalLocalLabel() {
		return globalLocalLabel;
	}

	/**
	 * @param globalLocalLabel the globalLocalLabel to set
	 */
	public void setGlobalLocalLabel(String globalLocalLabel) {
		this.globalLocalLabel = globalLocalLabel;
	}

	/**
	 * @return the matriculationLabel
	 */
	public String getMatriculationLabel() {
		return matriculationLabel;
	}

	/**
	 * @param matriculationLabel the matriculationLabel to set
	 */
	public void setMatriculationLabel(String matriculationLabel) {
		this.matriculationLabel = matriculationLabel;
	}

	/**
	 * @return the passwordLabel
	 */
	public String getPasswordLabel() {
		return passwordLabel;
	}

	/**
	 * @param passwordLabel the passwordLabel to set
	 */
	public void setPasswordLabel(String passwordLabel) {
		this.passwordLabel = passwordLabel;
	}

	/**
	 * @return the genderLabel
	 */
	public String getGenderLabel() {
		return genderLabel;
	}

	/**
	 * @param genderLabel the genderLabel to set
	 */
	public void setGenderLabel(String genderLabel) {
		this.genderLabel = genderLabel;
	}

	/**
	 * @return the emailLabel
	 */
	public String getEmailLabel() {
		return emailLabel;
	}

	/**
	 * @param emailLabel the emailLabel to set
	 */
	public void setEmailLabel(String emailLabel) {
		this.emailLabel = emailLabel;
	}

}
