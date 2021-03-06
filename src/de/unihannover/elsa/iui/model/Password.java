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
	private String passwordToHash;
	private String MD5Value;
	
	
	public Password() {
		setType("PLAIN");
	}
	
	public Password(String value) throws NoSuchAlgorithmException {
		setType("PLAIN");
		setPasswordToHash(value);
		setMD5Value(value);
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
//		setMD5Value(this.getPasswordToHash()); // needed when reloading a xml file.
		return this.passwordToHash;
	}
	
	/**
	 * @param passwordToHash
	 * @throws NoSuchAlgorithmException 
	 */
	public void setMD5Value(String passwordToHash) {
        // Create MessageDigest instance for MD5
        MessageDigest md;
        StringBuilder sb = new StringBuilder();
        
		try {
			md = MessageDigest.getInstance("MD5");
	        //Add password bytes to digest
	        md.update(passwordToHash.getBytes());
	        //Get the hash's bytes 
	        //This bytes[] has bytes in decimal format;
	        //Convert it to hexadecimal format
	        byte[] bytes = md.digest();
	        for(int i=0; i< bytes.length ;i++)
	        {
	            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
	        }
		} catch (NoSuchAlgorithmException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        //Get complete hashed password in hex format
        this.MD5Value = sb.toString();
	}

	/**
	 * @return the passwordToHash
	 */
	@XmlAttribute(name="PasswordToHash")
	public String getPasswordToHash() {
		return passwordToHash;
	}

	/**
	 * @param passwordToHash the passwordToHash to set
	 */
	public void setPasswordToHash(String passwordToHash) {
		this.passwordToHash = passwordToHash;
	}

}
