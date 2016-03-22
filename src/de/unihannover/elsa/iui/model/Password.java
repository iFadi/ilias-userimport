package de.unihannover.elsa.iui.model;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlValue;

/**
 * @author Fadi Asbih
 *
 * Copyright (c) 2014
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
@XmlRootElement(name="Password")
public class Password {

	private String type;
	private String plainPassword;
	private String password;
	
	
	public Password() {
		setType("ILIAS3");
	}
	
	public Password(String value) throws NoSuchAlgorithmException {
		setType("ILIAS3");
		setPlainPassword(value);
		setPassword(value);
	}
	
	public Password(String type, String value) throws NoSuchAlgorithmException {
		setType(type);
		setPlainPassword(value);
		setPassword(value);
	}
	/**
	 * @return the type
	 */
	@XmlAttribute(name="Type")
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}
	/**
	 * @return the value
	 * @throws NoSuchAlgorithmException 
	 */
	@XmlValue
	public String getValue() throws NoSuchAlgorithmException {
		setPassword(this.getPlainPassword()); // needed when reloading a xml file.
		if(this.getType() == "ILIAS3")
			return this.password;
		else
			return this.plainPassword;
	}
	
	/**
	 * @param plainPassword
	 * @throws NoSuchAlgorithmException 
	 */
	public void setPassword(String plainPassword) throws NoSuchAlgorithmException {
        // Create MessageDigest instance for MD5
        MessageDigest md = MessageDigest.getInstance("MD5");
        //Add password bytes to digest
        md.update(plainPassword.getBytes());
        //Get the hash's bytes 
        byte[] bytes = md.digest();
        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        //Get complete hashed password in hex format
        this.password = sb.toString();
	}

	/**
	 * @return the plainPassword
	 */
	@XmlAttribute(name="plainPassword")
	public String getPlainPassword() {
		return plainPassword;
	}

	/**
	 * @param plainPassword the plainPassword to set
	 */
	public void setPlainPassword(String plainPassword) {
		this.plainPassword = plainPassword;
	}
}
