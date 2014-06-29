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
//	private String passwordToHash;
	private String MD5Value;
	
	
	public Password() {
		this("ILIAS3");
	}

	public Password(String type) {
		setType(type);
	}
	
	public Password(String type, String value) throws NoSuchAlgorithmException {
		setType(type);
//		setPasswordToHash(value);
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
	 */
	@XmlValue
	public String getValue() {
		return this.MD5Value;
	}
	
	/**
	 * @param passwordToHash
	 * @throws NoSuchAlgorithmException 
	 */
	public void setMD5Value(String passwordToHash) throws NoSuchAlgorithmException {
        // Create MessageDigest instance for MD5
        MessageDigest md = MessageDigest.getInstance("MD5");
        //Add password bytes to digest
        md.update(passwordToHash.getBytes());
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
        this.MD5Value = sb.toString();
	}

//	/**
//	 * @return the passwordToHash
//	 */
//	public String getPasswordToHash() {
//		return passwordToHash;
//	}
//
//	/**
//	 * @param passwordToHash the passwordToHash to set
//	 */
//	public void setPasswordToHash(String passwordToHash) {
//		this.passwordToHash = passwordToHash;
//	}
}