package br.com.hackathongdg.recycleplus.model;

import com.parse.ParseClassName;
import com.parse.ParseGeoPoint;
import com.parse.ParseObject;
import com.parse.ParseRelation;

@ParseClassName("Company")
public class Company extends ParseObject {

    private final static String KEY_NAME = "name'";
    private final static String KEY_LOCATION = "location";
    private final static String KEY_SALES = "sales";

    public void setName(String name) {
        put(KEY_NAME, name);
    }

    public String getName() {
        return getString(KEY_NAME);
    }

    public void setLocation(ParseGeoPoint geoPoint) {
        put(KEY_LOCATION, geoPoint);
    }

    public ParseGeoPoint getLocation() {
        return getParseGeoPoint(KEY_LOCATION);
    }

    public ParseRelation<ParseObject> getSales() {
        return getRelation(KEY_SALES);
    }
}
