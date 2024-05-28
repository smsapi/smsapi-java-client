package pl.smsapi.api.action.contacts;

import org.json.JSONObject;
import pl.smsapi.StringUtils;
import pl.smsapi.api.action.AbstractAction;

import java.net.URLEncoder;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class ContactsList extends AbstractAction<Contacts> {

    protected TreeMap<String, String> query = new TreeMap<>();

    @Override
    protected String endPoint() {
        if (query.isEmpty()) {
            return "contacts";
        }

        return "contacts?" +
            query
                .entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + URLEncoder.encode(entry.getValue()))
                .collect(Collectors.joining("&"));
    }

    @Override
    protected String httpMethod() {
        return "GET";
    }

    public ContactsList filterByPhoneNumber(String phoneNumber) {
        query.put("phone_number", phoneNumber);
        return this;
    }

    public ContactsList filterByEmail(String email) {
        query.put("email", email);
        return this;
    }

    public ContactsList filterByFirstName(String firstName) {
        query.put("first_name", firstName);
        return this;
    }

    public ContactsList filterByLastName(String lastName) {
        query.put("last_name", lastName);
        return this;
    }

    public ContactsList filterByGroupId(String groupId) {
        query.put("group_id", groupId);
        return this;
    }

    public ContactsList filterByGroupIds(String[] groupIds) {
        query.put("group_id", StringUtils.join(groupIds, ','));
        return this;
    }

    public ContactsList filterByGender(String gender) {
        query.put("gender", gender);
        return this;
    }

    public ContactsList filterByBirthdayDate(String birthdayDate) {
        query.put("birthday_date", birthdayDate);
        return this;
    }

    public ContactsList orderBy(String orderBy) {
        query.put("order_by", orderBy);
        return this;
    }

    public ContactsList limit(int limit) {
        query.put("limit", Integer.toString(limit));
        return this;
    }

    public ContactsList offset(int offset) {
        query.put("offset", Integer.toString(offset));
        return this;
    }

    @Override
    protected Contacts createResponse(String data) {
        return new Contacts.ContactsFromJsonFactory().createFrom(new JSONObject(data));
    }
}
