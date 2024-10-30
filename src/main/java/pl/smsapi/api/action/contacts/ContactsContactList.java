package pl.smsapi.api.action.contacts;

import org.json.JSONObject;
import pl.smsapi.StringUtils;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.contacts.ContactsContactListResponse;

/**
 * @deprecated use @link ContactsList instead
 */
@Deprecated
public class ContactsContactList extends AbstractAction<ContactsContactListResponse> {
    public ContactsContactList filterByPhoneNumber(String phoneNumber) {
        params.put("phone_number", phoneNumber);
        return this;
    }

    public ContactsContactList filterByEmail(String email) {
        params.put("email", email);
        return this;
    }

    public ContactsContactList filterByFirstName(String firstName) {
        params.put("first_name", firstName);
        return this;
    }

    public ContactsContactList filterByLastName(String lastName) {
        params.put("last_name", lastName);
        return this;
    }

    public ContactsContactList filterByGroupId(String groupId) {
        params.put("group_id", groupId);
        return this;
    }

    public ContactsContactList filterByGroupIds(String[] groupIds) {
        params.put("group_id", StringUtils.join(groupIds, ','));
        return this;
    }

    public ContactsContactList filterByGender(String gender) {
        params.put("gender", gender);
        return this;
    }

    public ContactsContactList filterByBirthdayDate(String birthdayDate) {
        params.put("birthday_date", birthdayDate);
        return this;
    }

    public ContactsContactList orderBy(String orderBy) {
        params.put("order_by", orderBy);
        return this;
    }

    public ContactsContactList limit(int limit) {
        params.put("limit", Integer.toString(limit));
        return this;
    }

    public ContactsContactList offset(int offset) {
        params.put("offset", Integer.toString(offset));
        return this;
    }

    @Override
    protected String endPoint() {
        return "";
    }

    @Override
    protected String httpMethod() {
        return "GET";
    }

    @Override
    protected ContactsContactListResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new ContactsContactListResponse(jsonObject.getInt("size"), jsonObject.getJSONArray("collection"));
    }
}
