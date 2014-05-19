package pl.smsapi.api.action.phonebook;

public class PhonebookContactAdd extends AbstractContactControl<PhonebookContactAdd> {

    /**
     * Set contact phone number.
     */
    public PhonebookContactAdd setNumber(String number) {
        params.put("add_contact", number);
        return this;
    }
}
