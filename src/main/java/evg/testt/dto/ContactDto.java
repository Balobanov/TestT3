package evg.testt.dto;

import java.util.List;

/**
 * Created by DENNNN on 30.11.2016.
 */
public class ContactDto
{
    int id;
    String firstName;
    List<Email> emails;

    public ContactDto() {
    }

    public ContactDto(int id, String firstName, List<Email> emails) {
        this.id = id;
        this.firstName = firstName;
        this.emails = emails;
    }

    public List<Email> getEmails() {
        return emails;
    }

    public void setEmails(List<Email> emails) {
        this.emails = emails;
    }

    public int getId() {

        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}