package evg.testt.dto;

import evg.testt.model.Activity;
import evg.testt.model.Contact;;

import javax.validation.Valid;

/**
 * Created by DENNNN on 14.11.2016.
 */

public class ContactActivityDTO {

    @Valid
    private Contact contact;

    @Valid
    private Activity activity;

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public Activity getActivity() {
        return activity;
    }

    public void setActivity(Activity activity) {
        this.activity = activity;
    }
}
