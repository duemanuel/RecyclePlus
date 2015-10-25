package br.com.hackathongdg.recycleplus.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Sale")
public class Sale extends ParseObject {

    private final static String KEY_NAME = "name";
    private final static String KEY_DESCRIPTION = "description";
    private final static String KEY_COMPANY = "company";

    public void setName(String name) {
        put(KEY_NAME, name);
    }

    public String getName() {
        return getString(KEY_NAME);
    }

    public void setDescription(String description) {
        put(KEY_DESCRIPTION, description);
    }

    public String getDescription() {
        return getString(KEY_DESCRIPTION);
    }

    public ParseObject getCompany() {
        return getParseObject(KEY_COMPANY);
    }

    public void setCompany(ParseObject company) {
        put(KEY_COMPANY, company);
    }
}
