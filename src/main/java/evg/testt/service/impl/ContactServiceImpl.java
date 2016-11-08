package evg.testt.service.impl;

import evg.testt.dao.ContactDao;;
import evg.testt.model.Contact;
import evg.testt.service.ContactService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by clay on 25.09.16.
 */

@Service
@Transactional
public class ContactServiceImpl extends BaseService<Contact, ContactDao> implements ContactService {

//    @Override
//    public void insert(Contact contact){
//        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//        contact.setPassword(passwordEncoder.encode(contact.getPassword()));
//        dao.save(contact);
//    }
}
