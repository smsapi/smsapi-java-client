package pl.smsapi.api.action.contacts;

import org.json.JSONObject;
import pl.smsapi.StringUtils;
import pl.smsapi.api.action.AbstractAction;

public class ContactsList extends AbstractAction<Contacts> {
    @Override
    protected String endPoint() {
        return "contacts";
    }

    @Override
    protected String httpMethod() {
        return "GET";
    }

    public ContactsList filterByPhoneNumber(String phoneNumber) {
        params.put("phone_number", phoneNumber);
        return this;
    }

    public ContactsList filterByEmail(String email) {
        params.put("email", email);
        return this;
    }

    public ContactsList filterByFirstName(String firstName) {
        params.put("first_name", firstName);
        return this;
    }

    public ContactsList filterByLastName(String lastName) {
        params.put("last_name", lastName);
        return this;
    }

    public ContactsList filterByGroupId(String groupId) {
        params.put("group_id", groupId);
        return this;
    }

    public ContactsList filterByGroupIds(String[] groupIds) {
        params.put("group_id", StringUtils.join(groupIds, ','));
        return this;
    }

    public ContactsList filterByGender(String gender) {
        params.put("gender", gender);
        return this;
    }

    public ContactsList filterByBirthdayDate(String birthdayDate) {
        params.put("birthday_date", birthdayDate);
        return this;
    }

    public ContactsList orderBy(String orderBy) {
        params.put("order_by", orderBy);
        return this;
    }

    public ContactsList limit(int limit) {
        params.put("limit", Integer.toString(limit));
        return this;
    }

    public ContactsList offset(int offset) {
        params.put("offset", Integer.toString(offset));
        return this;
    }

    @Override
    protected Contacts createResponse(String data) {
        return new Contacts.ContactsFromJsonFactory().createFrom(new JSONObject(data));
    }
}
