package evg.testt.dto;

/**
 * Created by DENNNN on 30.11.2016.
 */
public class ContactDto
{
    int id;
    String firstName;


    public ContactDto(int id, String firstName) {
        this.id = id;
        this.firstName = firstName;
    }

    public ContactDto() {
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