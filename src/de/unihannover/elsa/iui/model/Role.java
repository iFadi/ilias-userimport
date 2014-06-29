package de.unihannover.elsa.iui.model;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;
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

@XmlType(name="Role")
public class Role {

	private String id;
	private String type;
	private String action;
	
	public Role() {
		this(null,null);
	}
	
	public Role(String type) {
		this(null,type);
	}
	
	public Role(String id, String type) {
		setId(id);
		setType(type);
		setAction("Assign");
	}

	/**
	 * @return the id
	 */
	@XmlAttribute(name="Id")
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
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
	 * @return the action
	 */
	@XmlAttribute(name="Action")
	public String getAction() {
		return action;
	}

	/**
	 * @param action the action to set
	 */
	public void setAction(String action) {
		this.action = action;
	}
	
	/**
	 * @return the value
	 */
	@XmlValue
	public String getValue() {
		return this.id;
	}
}
