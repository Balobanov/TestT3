package evg.testt.dao;


import evg.testt.model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ContactDao extends JpaRepository<Contact, Integer> {
}
