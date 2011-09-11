package model;

import java.io.File;
import java.io.StringWriter;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Observable;
import java.util.StringTokenizer;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;

/**
 * GenerateXML.java
 * Generates XML Users File for the import in the ILIAS E-Learning System.
 * This Class needs an input(Excel) and an output(file name).
 * 
 * @author Fadi Asbih
 * @email fadi_asbih@yahoo.de
 * @version 1.1.0
 * @copyright 2011
 * 
 * 
 */
public class GenerateXML {
	
	public GenerateXML() {
		
	}

	public void GenerateXML(ReadExcel input, String output) throws Exception {
		// We need a Document
		DocumentBuilderFactory dbfac = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder = dbfac.newDocumentBuilder();
		Document doc = docBuilder.newDocument();

		// create the root element and add it to the document
		Element root = doc.createElement("Users");
		doc.appendChild(root);

		for (int i = 1; i < input.getColumn("Login").size(); i++) {
			// create child element, add an attribute, and add to root
			Element user = doc.createElement("User");
			Element grole = doc.createElement("Role");
			Element login = doc.createElement("Login");
			Element password = doc.createElement("Password");
			Element gender = doc.createElement("Gender");
			Element firstname = doc.createElement("Firstname");
			Element lastname = doc.createElement("Lastname");
			Element email = doc.createElement("Email");
			Element matriculation = doc.createElement("Matriculation");

			// Add User
			user.setAttribute("Id", (String) input.getColumn("Login").get(i));
			user.setAttribute("Action", "Insert");
			root.appendChild(user);
//			 System.out.println(input.getColumn("Login").get(i));

			// Add Global Role
			grole.setAttribute("Id", "_1");
			grole.setAttribute("Type", "Global");
			grole.setAttribute("Action", "Assign");
			Text gt = doc.createTextNode((String) input
					.getColumn("Global Role").get(i));
			grole.appendChild(gt);
			user.appendChild(grole);

			// Add Login
			Text loginText = doc.createTextNode(removeSpaces((String) input.getColumn("Login").get(i)));
			login.appendChild(loginText);
			user.appendChild(login);

			// Add Password in MD5 Form
			password.setAttribute("Type", "ILIAS3");
			Text pass = doc.createTextNode(MD5(removeSpaces((String) input.getColumn("Password").get(i))));
			password.appendChild(pass);
			user.appendChild(password);

			// Add Gender
			if(input.getColumn("Gender").size() == 0) {
				Text genderText = doc.createTextNode("f");
				gender.appendChild(genderText);
				user.appendChild(gender);
			}
			else{
				Text genderText = doc.createTextNode((String) input.getColumn("Gender").get(i));
				gender.appendChild(genderText);
				user.appendChild(gender);
			}

			// Add first Name
			Text firstNameText = doc.createTextNode((String) input.getColumn(
					"Firstname").get(i));
			firstname.appendChild(firstNameText);
			user.appendChild(firstname);
			// System.out.println((String)input.getColumn("Firstname").get(i));

			// Add Last Name
			Text lastNameText = doc.createTextNode((String) input.getColumn(
					"Lastname").get(i));
			lastname.appendChild(lastNameText);
			user.appendChild(lastname);
			// System.out.println((String)input.getColumn("Lastname").get(i));

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

		// //set up a transformer
		// TransformerFactory transfac = TransformerFactory.newInstance();
		// Transformer trans = transfac.newTransformer();
		// trans.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "yes");
		// trans.setOutputProperty(OutputKeys.INDENT, "yes");
		//
		// //create string from xml tree
		// StringWriter sw = new StringWriter();
		// StreamResult result = new StreamResult(sw);
		// DOMSource source = new DOMSource(doc);
		// trans.transform(source, result);
		// String xmlString = sw.toString();
		// System.out.println(xmlString);
//		System.out.println("Done!");

	}
	
	public String removeSpaces(String s) {
		  StringTokenizer st = new StringTokenizer(s," ",false);
		  String t="";
		  while (st.hasMoreElements()) t += st.nextElement();
		  return t;
		}

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

	public static String MD5(String text) throws NoSuchAlgorithmException,
			UnsupportedEncodingException {
		MessageDigest md;
		md = MessageDigest.getInstance("MD5");
		byte[] md5hash = new byte[32];
		md.update(text.getBytes("iso-8859-1"), 0, text.length());
		md5hash = md.digest();
		return convertToHex(md5hash);
	}
	
}
