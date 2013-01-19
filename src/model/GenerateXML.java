package model;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

import controller.IFile;

import view.DummyTab;

/**
 * Generates XML Users File for the import in the ILIAS E-Learning System.
 * $Id$
 * $LastChangedDate$
 * 
 * @author Fadi M. H. Asbih
 * @email fadi_asbih@yahoo.de
 * @version $Revision$
 * @copyright $Date$
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
public class GenerateXML {
	
	private Text login;
	private Text password;
	private Text globalRole;
	private Text title;
	private Configuration configuration;
	
	public GenerateXML() {
		// Load the Configuration.
		configuration = new Configuration();
	}

	public void GenerateXMLFile(DummyTab dt, String output) throws Exception {
		// We need a Document
		DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
		Document doc = docBuilder.newDocument();
		
		// create the root element and add it to the document
		Element root = doc.createElement("Users");
		doc.appendChild(root);

		for (int i = 1; i <= dt.getNumberField(); i++) {
			// create child element, add an attribute, and add to root
//			Element title = doc.createElement("Title");
			Element user = doc.createElement("User");
			Element grole = doc.createElement("Role");
			Element lrole = doc.createElement("Role");
			Element login = doc.createElement("Login");
			Element password = doc.createElement("Password");
			Element gender = doc.createElement("Gender");
			Element firstname = doc.createElement("Firstname");
			Element lastname = doc.createElement("Lastname");
			Element email = doc.createElement("Email");
			Element matriculation = doc.createElement("Matriculation");

			// Add Global Role
			grole.setAttribute("Id", "_1");
			grole.setAttribute("Type", "Global");
			grole.setAttribute("Action", "Assign");
			setGlobalRole(doc.createTextNode("User")); //Standard Role in ILIAS.
			grole.appendChild(getGlobalRole());
			user.appendChild(grole);
			
			// Add Local Role
			if(configuration.isLocalRole()) {
				lrole.setAttribute("Id", "_2");
				lrole.setAttribute("Type", "Local");
				lrole.setAttribute("Action", "Assign");
				Text lt = doc.createTextNode(configuration.getLocalRoleValue());
				lrole.appendChild(lt);
				user.appendChild(lrole);
			}
			
			// Add Login
			setLogin(doc.createTextNode(dt.getLoginField()+i));
			login.appendChild((Text)getLogin());
			user.appendChild(login);

			
			// Insert User Command
			user.setAttribute("Id", getLogin().getWholeText());
			user.setAttribute("Action", "Insert");
			root.appendChild(user);
			
			
			// Add Password in MD5 Form
			password.setAttribute("Type", "ILIAS3");
			setPassword(doc.createTextNode(MD5(removeSpaces(dt.getPasswordField()))));
			password.appendChild(getPassword());
			user.appendChild(password);


			// Add Gender
			Text genderText = doc.createTextNode(configuration.getGenderValue());
			gender.appendChild(genderText);
			user.appendChild(gender);
			
			
			// Add first Name
			Text firstNameText = doc.createTextNode(dt.getLoginField());
			firstname.appendChild(firstNameText);
			user.appendChild(firstname);
			// System.out.println((String)input.getColumn("Firstname").get(i));

			// Add last Name
			Text lastNameText = doc.createTextNode(i+"");
			lastname.appendChild(lastNameText);
			user.appendChild(lastname);
//			 System.out.println((String)input.getColumn("Lastname").get(i));

			// Add email
			Text mailt = doc.createTextNode("users@dummy.dummy");
			email.appendChild(mailt);
			user.appendChild(email);

			// Add matriculation
			Text matText = doc.createTextNode("xxxxxx");
			matriculation.appendChild(matText);
			user.appendChild(matriculation);

		}

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("./"+output));
		transformer.transform(source, result);
	}
	
	/**
	 * 
	 * @param input
	 * @param output
	 * @throws Exception
	 */
	public void GenerateXMLFile(IFile input, String output) throws Exception {
		
		// We need a Document
		DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
		Document doc = docBuilder.newDocument();

		// create the root element and add it to the document
		Element root = doc.createElement("Users");
		doc.appendChild(root);

		for (int i = 1; i < input.getColumn(configuration.getFirstNameLabel()).size(); i++) {
			// create child element, add an attribute, and add to root
			Element title = doc.createElement("Title");
			Element user = doc.createElement("User");
			Element grole = doc.createElement("Role");
			Element lrole = doc.createElement("Role");
			Element login = doc.createElement("Login");
			Element password = doc.createElement("Password");
			Element gender = doc.createElement("Gender");
			Element firstname = doc.createElement("Firstname");
			Element lastname = doc.createElement("Lastname");
			Element email = doc.createElement("Email");
			Element matriculation = doc.createElement("Matriculation");

			
			// Add Global Role
			grole.setAttribute("Id", "_1");
			grole.setAttribute("Type", "Global");
			grole.setAttribute("Action", "Assign");
			setGlobalRole(doc.createTextNode("User")); //Standard Role in ILIAS.
			grole.appendChild(getGlobalRole());
			user.appendChild(grole);
			
			
			// Adds Local Role, if column does exist.
			if(input.getColumn(configuration.getLocalRoleLabel()).size() != 0) {			
				lrole.setAttribute("Id", "_2");
				lrole.setAttribute("Type", "Local");
				lrole.setAttribute("Action", "Assign");
				Text lt = doc.createTextNode((String) input.getColumn(configuration.getLocalRoleLabel()).get(i));
				lrole.appendChild(lt);
				user.appendChild(lrole);
			}

			if(configuration.isLocalRole()) {
				lrole.setAttribute("Id", "_2");
				lrole.setAttribute("Type", "Local");
				lrole.setAttribute("Action", "Assign");
				Text lt = doc.createTextNode(configuration.getLocalRoleValue());
				lrole.appendChild(lt);
				user.appendChild(lrole);
			}
			
			// Add Login, if Login column doesn't exist, The generate login from firstname.lastname or M-nr
			if(configuration.isGenerateLogin()) {
				// Add Login
//				setLogin(doc.createTextNode(removeSpaces((String)input.getColumn(configuration.getFirstNameLabel()).get(i)+"."+(String)input.getColumn(configuration.getLastNameLabel()).get(i))));
				setLogin(doc.createTextNode(removeSpaces((String)input.getColumn(configuration.getMatriculationLabel()).get(i))));
				login.appendChild((Text)getLogin());
				user.appendChild(login);
			}
			else{// get the login column
				// Add Login
				setLogin(doc.createTextNode(removeSpaces((String) input.getColumn(configuration.getLoginLabel()).get(i))));
				login.appendChild((Text)getLogin());
				user.appendChild(login);

			}
			
			// Add, edit or delete User
			user.setAttribute("Id", getLogin().getWholeText());
			user.setAttribute("Action", configuration.getActionValue());
			root.appendChild(user);
//			 System.out.println(input.getColumn("Login").get(i));
			
			// Add Password in MD5 Form, If Password column dosen't exist.
			if(input.getColumn(configuration.getPasswordLabel()).size() == 0 || configuration.isGeneratePassword()) { //If Password Column dosen't exists, then password will be auto generated combined from firstname.lastname
				password.setAttribute("Type", "ILIAS3");
//				Text pass = doc.createTextNode(MD5(removeSpaces((String)input.getColumn(configuration.getFirstNameLabel()).get(i)+"."+(String)input.getColumn(configuration.getLastNameLabel()).get(i))));
				Text pass = doc.createTextNode(MD5(removeSpaces(configuration.getPasswordValue()))); 
				password.appendChild(pass);
				user.appendChild(password);
			}
			else{ // get the password column
				password.setAttribute("Type", "ILIAS3");
				Text pass = doc.createTextNode(MD5(removeSpaces((String) input.getColumn(configuration.getPasswordLabel()).get(i))));
				password.appendChild(pass);
				user.appendChild(password);
			}

			// Add Gender, if Gender column doesn't exist
			if(input.getColumn("Gender").size() == 0) {
				Text genderText = doc.createTextNode(configuration.getGenderValue());
				gender.appendChild(genderText);
				user.appendChild(gender);
//				System.out.println(input.getColumn("Gender"));
			}
			else{// get the gender column
				Text genderText = doc.createTextNode((String) input.getColumn(configuration.getGenderLabel()).get(i));
				gender.appendChild(genderText);
				user.appendChild(gender);
			}
			
			// Add Title, if column does exist.
			if(input.getColumn("Title").size() != 0) {
				Text titleText = doc.createTextNode((String) input.getColumn(configuration.getTitleLabel()).get(i));
				title.appendChild(titleText);
				user.appendChild(title);
				System.out.println(input.getColumn("Title").size());
			}
			
			// Add first Name
			Text firstNameText = doc.createTextNode((String) input.getColumn(configuration.getFirstNameLabel()).get(i));
			firstname.appendChild(firstNameText);
			user.appendChild(firstname);
			// System.out.println((String)input.getColumn("Firstname").get(i));

			// Add Last Name
			Text lastNameText = doc.createTextNode((String) input.getColumn(configuration.getLastNameLabel()).get(i));
			lastname.appendChild(lastNameText);
			user.appendChild(lastname);
//			 System.out.println((String)input.getColumn("Lastname").get(i));

			// Add email
			Text mailt = doc.createTextNode((String) input.getColumn(configuration.getEmailLabel()).get(i));
			email.appendChild(mailt);
			user.appendChild(email);

			// Add matriculation
			Text matText = doc.createTextNode((String) input.getColumn(configuration.getMatriculationLabel()).get(i));
			matriculation.appendChild(matText);
			user.appendChild(matriculation);

		}

		// write the content into xml file
		TransformerFactory transformerFactory = TransformerFactory.newInstance();
		Transformer transformer = transformerFactory.newTransformer();
		DOMSource source = new DOMSource(doc);
		StreamResult result = new StreamResult(new File("./"+output));
		transformer.transform(source, result);

	}
	
	/**
	 * 
	 * Remove spaces from string.
	 * 
	 * @param String
	 * @return String without spaces
	 */
	public String removeSpaces(String s) {
		  StringTokenizer st = new StringTokenizer(s," ",false);
		  String t="";
		  while (st.hasMoreElements()) t += st.nextElement();
		  return t;
		}
	
	/**
	 * 
	 * Help function for MD5
	 * 
	 * @param data
	 * @return String
	 */
	private static String convertToHex(byte[] data) {
		StringBuffer buf = new StringBuffer();
		for (int i = 0; i < data.length; i++) {
			int halfbyte = (data[i] >>> 4) & 0x0F;
			int two_halfs = 0;
			do {
				if ((0 <= halfbyte) && (halfbyte <= 9))
					buf.append((char) ('0' + halfbyte));
				else
					buf.append((char) ('a' + (halfbyte - 10)));
				halfbyte = data[i] & 0x0F;
			} while (two_halfs++ < 1);
		}
		return buf.toString();
	}
	
	/**
	 * 
	 * MD5 function.
	 * 
	 * @param String
	 * @return MD5 Key
	 * @throws NoSuchAlgorithmException
	 * @throws UnsupportedEncodingException
	 */
	public static String MD5(String text) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		MessageDigest md;
		md = MessageDigest.getInstance("MD5");
		byte[] md5hash = new byte[32];
		md.update(text.getBytes("iso-8859-1"), 0, text.length());
		md5hash = md.digest();
		return convertToHex(md5hash);
	}

	/**
	 * 
	 * @return login
	 */
	public Text getLogin() {
		return login;
	}
	
	/**
	 * 
	 * @param login
	 */
	public void setLogin(Text login) {
		
		this.login = login;
	}

	/**
	 * 
	 * @return configuration
	 */
	public Configuration getConfiguration() {
		return configuration;
	}
	
	/**
	 * 
	 * @param configuration
	 */
	public void setConfiguration(Configuration configuration) {
		this.configuration = configuration;
	}
	
	/**
	 * 
	 * @return title
	 */
	public Text getTitle() {
		return title;
	}
	
	/**
	 * 
	 * @param title
	 */
	public void setTitle(Text title) {
		this.title = title;
	}

	/**
	 * @return the globalRole
	 */
	public Text getGlobalRole() {
		return globalRole;
	}

	/**
	 * @param globalRole the globalRole to set
	 */
	public void setGlobalRole(Text globalRole) {
		this.globalRole = globalRole;
	}

	/**
	 * @return the password
	 */
	public Text getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(Text password) {
		this.password = password;
	}
	
}
