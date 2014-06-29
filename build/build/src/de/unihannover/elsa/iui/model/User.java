package de.unihannover.elsa.iui.model;

import java.time.LocalDate;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import de.unihannover.elsa.iui.util.LocalDateAdapter;

/**
 * Model class for a ILIAS User.
 *
 * @author Marco Jakob
 * @author Fadi Asbih
 */
public class User {

    private final Role globalRole;
    private final Role localRole;
	private final StringProperty login;
//	private final StringProperty password;
    private final StringProperty firstName;
    private final StringProperty lastName;
//    private final StringProperty email;
    private final StringProperty matriculation;
    private final StringProperty gender;
    private final StringProperty street;
    private final IntegerProperty postalCode;
    private final StringProperty city;
    private final ObjectProperty<LocalDate> birthday;

    /**
     * Default constructor.
     */
    public User() {
        this(null, null);
    }

    /**
     * Constructor with some initial data.
     * 
     * @param firstName
     * @param lastName
     */
    public User(String firstName, String lastName) {
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);

        // Some initial dummy data, just for convenient testing.
        this.globalRole = new Role("User", "Global");
        this.localRole = new Role("Belastungstest_teilnehmern_28.06.2011", "Local");
        this.gender = new SimpleStringProperty("f");
        this.login = new SimpleStringProperty("");
        this.matriculation = new SimpleStringProperty("");
        this.street = new SimpleStringProperty("");
        this.postalCode = new SimpleIntegerProperty();
        this.city = new SimpleStringProperty("");
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(2000, 1, 1));
    }
    
	@XmlElement(name = "Login")
    public String getLogin() {
        return login.get();
    }

    public void setLogin(String login) {
        this.login.set(login);
    }

    public StringProperty LoginProperty() {
        return login;
    }
    
	@XmlElement(name = "Matriculation")
    public String getMatriculation() {
        return 	matriculation.get();
    }

    public void setMatriculation(String matriculation) {
        this.matriculation.set(matriculation);
    }

    public StringProperty MatriculationProperty() {
        return matriculation;
    }
    
    @XmlElement(name = "Firstname")
    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }
    
    @XmlElement(name = "Lastname")
    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public StringProperty lastNameProperty() {
        return lastName;
    }
    
    @XmlElement(name = "Street")
    public String getStreet() {
        return street.get();
    }

    public void setStreet(String street) {
        this.street.set(street);
    }

    public StringProperty streetProperty() {
        return street;
    }
    
    @XmlElement(name = "PostalCode")
    public int getPostalCode() {
        return postalCode.get();
    }

    public void setPostalCode(int postalCode) {
        this.postalCode.set(postalCode);
    }

    public IntegerProperty postalCodeProperty() {
        return postalCode;
    }
    
    @XmlElement(name = "City")
    public String getCity() {
        return city.get();
    }

    public void setCity(String city) {
        this.city.set(city);
    }

    public StringProperty cityProperty() {
        return city;
    }
    
    @XmlElement(name = "Birthday")
    @XmlJavaTypeAdapter(LocalDateAdapter.class)
    public LocalDate getBirthday() {
        return birthday.get();
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday.set(birthday);
    }

    public ObjectProperty<LocalDate> birthdayProperty() {
        return birthday;
    }
    
	@XmlElement(name = "Gender")
    public String getGender() {
        return gender.get();
    }

    public void setGender(String gender) {
        this.gender.set(gender);
    }

    public StringProperty GenderProperty() {
        return gender;
    }
    
    @XmlElement(name = "Role")
    public Role getGlobalRole() {
        return globalRole;
    }

    public void setGlobalRole(String globalRole) {
        this.globalRole.setId(globalRole);
    }
    
    @XmlElement(name = "Role")
    public Role getLocalRole() {
        return localRole;
    }

    public void setLocalRole(String localRole) {
        this.localRole.setId(localRole);
    }
    
    @XmlAttribute(name="Id")
    public String getGlobal() {
    	return getFirstName()+"."+getLastName();
    }
    
    @XmlAttribute(name="Action")
    public String getAction() {
    	return "Insert";
    }
    
    @XmlAttribute(name="Language")
    public String getLanguage() {
    	return "de";
    }
}

