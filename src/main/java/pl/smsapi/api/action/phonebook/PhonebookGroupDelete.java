package pl.smsapi.api.action.phonebook;

import pl.smsapi.api.action.AbstractAction;
import pl.smsapi.api.response.RawResponse;

public class PhonebookGroupDelete extends AbstractAction<RawResponse> {

    /**
     * Set group to delete.
     */
    public PhonebookGroupDelete name(String groupname) {
        params.put("delete_group", groupname);
        return this;
    }

    /**
     * Set flag on true to remove contacts from group.
     * If this flag is false or unset contact will be only unbind from group.
     */
    public PhonebookGroupDelete removeContacts(boolean remove) {
        if (remove) {
            params.put("remove_contacts", "1");
        } else {
            params.remove("remove_contacts");
        }

        return this;
    }

    protected RawResponse createResponse(String data) {
        return new RawResponse(data);
    }

    @Override
    protected String endPoint() {
        return "phonebook.do";
    }
}
