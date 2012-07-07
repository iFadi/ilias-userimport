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

/**
 * 
 * GenerateXML.java
 * Generates XML Users File for the import in the ILIAS E-Learning System.
 * This Class needs an input(Excel file) and an output(file name).
 * 
 * @author Fadi Asbih
 * @email fadi_asbih@yahoo.de
 * @version 1.2.0  31/01/2012
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
public class GenerateXML {
	
	private Text login;
	private Text title;
	private Configuration configuration;
	
	public GenerateXML() {
		
	}

	public void GenerateXML(ReadExcel input, String output) throws Exception {
		configuration = new Configuration(); // Load the Configuration.
		// We need a Document
		DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
		Document doc = docBuilder.newDocument();

		// create the root element and add it to the document
		Element root = doc.createElement("Users");
		doc.appendChild(root);

		for (int i = 1; i < input.getColumn("Firstname").size(); i++) {
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
			Text gt = doc.createTextNode((String) input.getColumn("Global Role").get(i));
			grole.appendChild(gt);
			user.appendChild(grole);
			
			
			// Adds Local Role, if column does exist.
			if(input.getColumn("Local Role").size() != 0) {			
				lrole.setAttribute("Id", "_2");
				lrole.setAttribute("Type", "Local");
				lrole.setAttribute("Action", "Assign");
				Text lt = doc.createTextNode((String) input.getColumn("Local Role").get(i));
				lrole.appendChild(lt);
				user.appendChild(lrole);
			}

			// Add Login, if Login column doesn't exist, The generate login from firstname.lastname
			if(input.getColumn("Login").size() == 0 || configuration.isLogin()) {
				// Add Login
				setLogin(doc.createTextNode(removeSpaces((String)input.getColumn("Firstname").get(i)+"."+(String)input.getColumn("Lastname").get(i))));
				login.appendChild((Text)getLogin());
				user.appendChild(login);
			}
			else{// get the login column
				// Add Login
				setLogin(doc.createTextNode(removeSpaces((String) input.getColumn("Login").get(i))));
				login.appendChild((Text)getLogin());
				user.appendChild(login);

			}
			
			// Add User
			user.setAttribute("Id", getLogin().getWholeText());
			user.setAttribute("Action", "Insert");
			root.appendChild(user);
//			 System.out.println(input.getColumn("Login").get(i));
			
			// Add Password in MD5 Form, If Password column dosen't exist.
			if(input.getColumn("Password").size() == 0 || configuration.isLogin()) { //If Password Column dosen't exists, then password will be auto generated combined from firstname.lastname
				password.setAttribute("Type", "ILIAS3");
				Text pass = doc.createTextNode(MD5(removeSpaces((String)input.getColumn("Firstname").get(i)+"."+(String)input.getColumn("Lastname").get(i)))); 
				password.appendChild(pass);
				user.appendChild(password);
			}
			else{ // get the password column
				password.setAttribute("Type", "ILIAS3");
				Text pass = doc.createTextNode(MD5(removeSpaces((String) input.getColumn("Password").get(i))));
				password.appendChild(pass);
				user.appendChild(password);
			}

			// Add Gender, if Gender column doesn't exist
			if(input.getColumn("Gender").size() == 0) {
				Text genderText = doc.createTextNode("f");
				gender.appendChild(genderText);
				user.appendChild(gender);
			}
			else{// get the gender column
				Text genderText = doc.createTextNode((String) input.getColumn("Gender").get(i));
				gender.appendChild(genderText);
				user.appendChild(gender);
			}
			
			// Add Title, if column does exist.
			if(input.getColumn("Title").size() != 0) {
				Text titleText = doc.createTextNode((String) input.getColumn("Title").get(i));
				title.appendChild(titleText);
				user.appendChild(title);
				System.out.println((String)input.getColumn("Title").get(i));
			}
			
			// Add first Name
			Text firstNameText = doc.createTextNode((String) input.getColumn("Firstname").get(i));
			firstname.appendChild(firstNameText);
			user.appendChild(firstname);
			// System.out.println((String)input.getColumn("Firstname").get(i));

			// Add Last Name
			Text lastNameText = doc.createTextNode((String) input.getColumn("Lastname").get(i));
			lastname.appendChild(lastNameText);
			user.appendChild(lastname);
//			 System.out.println((String)input.getColumn("Lastname").get(i));

			// Add email
			Text mailt = doc.createTextNode((String) input.getColumn("Email")
					.get(i));
			email.appendChild(mailt);
			user.appendChild(email);

			// Add matriculation
			Text matText = doc.createTextNode((String) input.getColumn(
					"Matriculation").get(i));
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
	
}
