package de.unihannover.elsa.iui.model;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import de.unihannover.elsa.iui.util.DateUtil;
import de.unihannover.elsa.iui.util.LocalDateAdapter;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Model class for a ILIAS User.
 *
 * @author Fadi Asbih
 */

public class User {

    private Role globalRole;
    private Role localRole;
	private StringProperty login;
	private Password password;
    private StringProperty firstName;
    private StringProperty lastName;
    private StringProperty email;
    private StringProperty matriculation;
    private String timeLimitUnlimited;
    private StringProperty timeLimitFrom;
    private StringProperty timeLimitUntil;
    private StringProperty gender;
    private StringProperty street;
    private IntegerProperty postalCode;
    private StringProperty city;
    private ObjectProperty<LocalDate> birthday;

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
        this.localRole = new Role("some local role","Local");
        this.gender = new SimpleStringProperty("f");
        this.login = new SimpleStringProperty("");
        this.password = new Password();
        this.email = new SimpleStringProperty("student@eklausur.elsa");
        this.matriculation = new SimpleStringProperty("");
        this.timeLimitUnlimited = "1";
        this.timeLimitFrom = new SimpleStringProperty(DateUtil.getTodaysDate());
        this.timeLimitUntil = new SimpleStringProperty(DateUtil.getDateLater(10));
        this.street = new SimpleStringProperty("Some Street");
        this.postalCode = new SimpleIntegerProperty(12345);
        this.city = new SimpleStringProperty("Some City");
        this.birthday = new SimpleObjectProperty<LocalDate>(LocalDate.of(2000, 1, 1));
    }
    
    @XmlElement(name = "Role ")
    public Role getGlobalRole() {
        return globalRole;
    }

    public void setGlobalRole(Role globalRole) {
        this.globalRole = globalRole;
    }
    
    @XmlElement(name = "Role")
    public Role getLocalRole() {
        return localRole;
    }

    public void setLocalRole(Role localRole) {
        this.localRole = localRole;
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
    
    @XmlElement(name = "Password")
    public Password getPassword() {
        return this.password;
    }

    public void setPassword(Password password) {
        this.password = password;
    }

    
	@XmlElement(name = "Email")
    public String getEmail() {
        return email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    public StringProperty EmailProperty() {
        return email;
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
    
	@XmlElement(name = "TimeLimitUnlimited")
    public String getTimeLimitUnlimited() {
        return 	timeLimitUnlimited;
    }

    public void setTimeLimitUnlimited(String timeLimitUnlimited) {
        this.timeLimitUnlimited = timeLimitUnlimited;
    }
    
    @XmlElement(name = "TimeLimitFrom")
    public String getTimeLimitFrom() {
        return 	timeLimitFrom.get();
    }

    public void setTimeLimitFrom(String timeLimitFrom) {
        this.timeLimitFrom.set(timeLimitFrom);
    }

    public StringProperty timeLimitFromProperty() {
        return timeLimitFrom;
    }
    
	@XmlElement(name = "TimeLimitUntil")
    public String getTimeLimitUntil() {
        return 	timeLimitUntil.get();
    }

    public void setTimeLimitUntil(String timeLimitUntil) {
        this.timeLimitUntil.set(timeLimitUntil);
    }

    public StringProperty timeLimitUntilProperty() {
        return timeLimitUntil;
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
    
    @XmlAttribute(name="Id")
    public String getId() {
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

