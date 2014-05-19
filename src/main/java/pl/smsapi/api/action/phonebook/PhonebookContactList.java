package pl.smsapi.api.action.phonebook;

import org.json.JSONObject;
import pl.smsapi.StringUtils;
import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.ContactsResponse;

public class PhonebookContactList extends AbstractAction<ContactsResponse> {

    public PhonebookContactList() {
        setJson(true);
        params.put("list_contacts", "1");
    }

    /**
     * Set filter contacts by phone number.
     */
    public PhonebookContactList filterByNumber(String number) {
        params.put("number", number);
        return this;
    }

    /**
     * Set filter contacts by group name.
     */
    public PhonebookContactList filterByGroup(String group) {
        params.put("groups", group);
        return this;
    }

    /**
     * Set filter contacts by group names.
     */
    public PhonebookContactList filterByGroups(String[] groups) {
        params.put("groups", StringUtils.join(groups, ';'));
        return this;
    }

    /**
     * The result list will contain contacts with given chars string.
     */
    public PhonebookContactList filterByText(String text) {
        params.put("text_search", text);
        return this;
    }

    /**
     * Set filter by gender.
     */
    public PhonebookContactList filterByGender(String gender) {
        params.put("gender", gender);
        return this;
    }

    /**
     * Set order parameter.
     */
    public PhonebookContactList orderBy(String orderBy) {
        params.put("order_by", orderBy);
        return this;
    }

    /**
     * Set order direction.
     */
    public PhonebookContactList orderDir(String orderDir) {
        params.put("order_dir", orderDir);
        return this;
    }

    /**
     * Set result limit.
     */
    public PhonebookContactList limit(int limit) {
        params.put("limit", Integer.toString(limit));
        return this;
    }

    /**
     * Set result offset.
     */
    public PhonebookContactList offset(int offset) {
        params.put("offset", Integer.toString(offset));
        return this;
    }

    protected ContactsResponse createResponse(String data) {
        JSONObject jsonObject = new JSONObject(data);
        return new ContactsResponse(jsonObject.getInt("count"), jsonObject.getJSONArray("list"));
    }

    @Override
    protected String endPoint() {
        return "phonebook.do";
    }
}
