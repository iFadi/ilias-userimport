package model;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.util.Properties;
import java.util.prefs.Preferences;

/**
 * 
 * Configuration.java
 * 
 * $Author$
 * $Revision$
 * $HeadURL$
 * $id$
 * $LastChangedDate$
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

	private boolean generatePassword; 
	private boolean generateLogin; // Generate Login based on firstname.lastname
	private boolean localRole;
	private boolean dummy; // Generate dummy data for testing.
	private char timeLimitUnlimited;
	
	private String actionLabel;
	private String titleLabel;
	private String firstNameLabel; 
	private String lastNameLabel;
	private String loginLabel;
	private String globalRoleLabel;
	private String localRoleLabel;
	private String matriculationLabel;
	private String passwordLabel;
	private String genderLabel;
	private String emailLabel;
	
	private String localRoleValue;
	private String timeLimitFrom;
	private String timeLimitUntil;
	private String actionValue;
	private String passwordValue;
	private String genderValue;
	private char CSVSymbol;
	
	public Configuration() {
				
		try {
			Properties properties = new Properties();

			
			BufferedInputStream stream = new BufferedInputStream(new FileInputStream("configuration.properties"));
			properties.load(stream);
			stream.close();
			
			//Labels
			setActionLabel(properties.getProperty("actionLabel"));
			setTitleLabel(properties.getProperty("titleLabel"));
			setLoginLabel(properties.getProperty("loginLabel"));
			setPasswordLabel(properties.getProperty("passwordLabel"));
			setFirstNameLabel(properties.getProperty("firstNameLabel"));
			setLastNameLabel(properties.getProperty("lastNameLabel"));
			setGlobalRoleLabel(properties.getProperty("globalRoleLabel"));
			setLocalRoleLabel(properties.getProperty("localRoleLabel"));
			setMatriculationLabel(properties.getProperty("matriculationLabel"));
			setEmailLabel(properties.getProperty("emailLabel"));
			setGenderLabel(properties.getProperty("genderLabel"));
			
			//Values
			setActionValue(properties.getProperty("actionValue"));
			setLocalRoleValue(properties.getProperty("localRoleValue"));
			setPasswordValue(properties.getProperty("passwordValue"));
			setGenderValue(properties.getProperty("genderValue"));
			setCSVSymbol(properties.getProperty("CSV").charAt(0));
			setTimeLimitUnlimited(Boolean.parseBoolean(properties.getProperty("timeLimitUnlimited")));
			setTimeLimitFrom(properties.getProperty("timeLimitFrom"));
			setTimeLimitFrom(properties.getProperty("timeLimitUntil"));
			
			//Boolean
			setLocalRole(Boolean.parseBoolean(properties.getProperty("localRole")));
			setGeneratePassword(Boolean.parseBoolean(properties.getProperty("generatePassword")));
			setGenerateLogin(Boolean.parseBoolean(properties.getProperty("generateLogin")));
			
		} catch (Exception e) {
			System.out.println("No configuration.properties File Found.");
		}
		
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

	/**
	 * @return the titleLabel
	 */
	public String getTitleLabel() {
		return titleLabel;
	}

	/**
	 * @param titleLabel the titleLabel to set
	 */
	public void setTitleLabel(String titleLabel) {
		this.titleLabel = titleLabel;
	}

	/**
	 * @return the generatePassword
	 */
	public boolean isGeneratePassword() {
		return generatePassword;
	}

	/**
	 * @param generatePassword the generatePassword to set
	 */
	public void setGeneratePassword(boolean generatePassword) {
		this.generatePassword = generatePassword;
	}

	/**
	 * @return the localRoleLabel
	 */
	public String getLocalRoleLabel() {
		return localRoleLabel;
	}

	/**
	 * @param localRoleLabel the localRoleLabel to set
	 */
	public void setLocalRoleLabel(String localRoleLabel) {
		this.localRoleLabel = localRoleLabel;
	}

	/**
	 * @return the localRole
	 */
	public boolean isLocalRole() {
		return localRole;
	}

	/**
	 * @param localRole the localRole to set
	 */
	public void setLocalRole(boolean localRole) {
		this.localRole = localRole;
	}

	/**
	 * @return the localRoleValue
	 */
	public String getLocalRoleValue() {
		return localRoleValue;
	}

	/**
	 * @param localRoleValue the localRoleValue to set
	 */
	public void setLocalRoleValue(String localRoleValue) {
		this.localRoleValue = localRoleValue;
	}

	/**
	 * @return the passwordValue
	 */
	public String getPasswordValue() {
		return passwordValue;
	}

	/**
	 * @param passwordValue the passwordValue to set
	 */
	public void setPasswordValue(String passwordValue) {
		this.passwordValue = passwordValue;
	}

	/**
	 * @return the genderValue
	 */
	public String getGenderValue() {
		return genderValue;
	}

	/**
	 * @param genderValue the genderValue to set
	 */
	public void setGenderValue(String genderValue) {
		this.genderValue = genderValue;
	}

	/**
	 * @return the actionLabel
	 */
	public String getActionLabel() {
		return actionLabel;
	}

	/**
	 * @param actionLabel the actionLabel to set
	 */
	public void setActionLabel(String actionLabel) {
		this.actionLabel = actionLabel;
	}

	/**
	 * @return the actionValue
	 */
	public String getActionValue() {
		return actionValue;
	}

	/**
	 * @param actionValue the actionValue to set
	 */
	public void setActionValue(String actionValue) {
		this.actionValue = actionValue;
	}

	/**
	 * @return the cSVSymbol
	 */
	public char getCSVSymbol() {
		return CSVSymbol;
	}

	/**
	 * @param cSVSymbol the cSVSymbol to set
	 */
	public void setCSVSymbol(char cSVSymbol) {
		CSVSymbol = cSVSymbol;
	}

	/**
	 * @return the timeLimitFrom
	 */
	public String getTimeLimitFrom() {
		return timeLimitFrom;
	}

	/**
	 * @param timeLimitFrom the timeLimitFrom to set
	 */
	public void setTimeLimitFrom(String timeLimitFrom) {
		this.timeLimitFrom = timeLimitFrom;
	}

	/**
	 * @return the timeLimitUntil
	 */
	public String getTimeLimitUntil() {
		return timeLimitUntil;
	}

	/**
	 * @param timeLimitUntil the timeLimitUntil to set
	 */
	public void setTimeLimitUntil(String timeLimitUntil) {
		this.timeLimitUntil = timeLimitUntil;
	}

	/**
	 * @return the timeLimitUnlimited
	 */
	public char getTimeLimitUnlimited() {
		return timeLimitUnlimited;
	}

	/**
	 * @param timeLimitUnlimited the timeLimitUnlimited to set
	 */
	public void setTimeLimitUnlimited(boolean timeLimitUnlimited) {
		if(timeLimitUnlimited)
			this.timeLimitUnlimited = 0;
		else
			this.timeLimitUnlimited = 1;
	}

}
