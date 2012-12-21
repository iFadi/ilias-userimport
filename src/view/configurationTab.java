/**
 * 
 */
package view;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * 
 * ConfigurationTab.java
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

public class ConfigurationTab extends JPanel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1025794571581526405L;
	private JLabel loginLabel;


	private JLabel passwordLabel;
	private JTextField loginField;
	private JTextField passwordField;
	
	public ConfigurationTab() {
		setLayout(new BorderLayout());
	}
	
	public JLabel getLoginLabel() {
		return loginLabel;
	}

	public void setLoginLabel(JLabel loginLabel) {
		this.loginLabel = loginLabel;
	}

	public JLabel getPasswordLabel() {
		return passwordLabel;
	}

	public void setPasswordLabel(JLabel passwordLabel) {
		this.passwordLabel = passwordLabel;
	}

	public JTextField getLoginField() {
		return loginField;
	}

	public void setLoginField(JTextField loginField) {
		this.loginField = loginField;
	}

	public JTextField getPasswordField() {
		return passwordField;
	}

	public void setPasswordField(JTextField passwordField) {
		this.passwordField = passwordField;
	}
}
