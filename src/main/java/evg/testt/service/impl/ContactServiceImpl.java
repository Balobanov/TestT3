package evg.testt.service.impl;

import evg.testt.dao.ContactDao;;
import evg.testt.model.Contact;
import evg.testt.service.ContactService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.Query;
import java.util.List;

/**
 * Created by clay on 25.09.16.
 */

@Service
@Transactional
public class ContactServiceImpl extends BaseService<Contact, ContactDao> implements ContactService {

    @Override
    public List<Contact> findByFirstName(String firstName) {
        Query q = em.createQuery("from contacts c where c.firstName like :name");
        q.setParameter("name", "%" + firstName + "%");
        return q.getResultList();
    }

    @Override
    public List<Contact> findByLastName(String lastName) {
        Query q = em.createQuery("from contacts c where c.lastName like :name");
        q.setParameter("name", "%" + lastName + "%");
        return q.getResultList();
    }
//    @Override
//    public void insert(Contact contact){
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        contact.setPassword(passwordEncoder.encode(contact.getPassword()));
//        dao.save(contact);
//    }
}
