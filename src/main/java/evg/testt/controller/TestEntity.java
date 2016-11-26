package evg.testt.controller;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by DENNNN on 26.11.2016.
 */
public class TestEntity {
    int id;
    String name;
    List<Mails> mailsList;

    public TestEntity(int id, String name) {
        this.id = id;
        this.name = name;
        this.mailsList = new LinkedList<>();
        mailsList.add(new Mails("dddddd"));
        mailsList.add(new Mails("dddddd"));
    }

    public List<Mails> getMailsList() {
        return mailsList;
    }

    public void setMailsList(List<Mails> mailsList) {
        this.mailsList = mailsList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
