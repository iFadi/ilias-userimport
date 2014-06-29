package de.unihannover.elsa.iui.model;

import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Helper class to wrap a list of users. This is used for saving the
 * list of users to XML.
 * 
 * @author Marco Jakob
 */
@XmlRootElement(name = "Users")
public class UserListWrapper {

    private List<User> users;

    @XmlElement(name = "User")
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}