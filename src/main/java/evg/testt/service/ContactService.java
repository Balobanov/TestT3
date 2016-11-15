package evg.testt.service;

import evg.testt.model.Contact;

import java.util.List;

/**
 * Created by clay on 25.09.16.
 */
public interface ContactService extends Service<Contact>{
    List<Contact> findByFirstName(String firstName);
    List<Contact> findByFirstNameAndLastName(String firstName, String lastName);
}
