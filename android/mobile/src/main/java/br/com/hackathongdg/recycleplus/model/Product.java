package br.com.hackathongdg.recycleplus.model;

import com.parse.ParseClassName;
import com.parse.ParseFile;
import com.parse.ParseObject;

@ParseClassName("Product")
public class Product extends ParseObject {

    private final static String KEY_TITLE = "title'";
    private final static String KEY_PHOTO = "photo";
    private final static String KEY_CATEGORY = "category";

    public void setTitle(String title) {
        put(KEY_TITLE, title);
    }

    public String getTitle() {
        return getString(KEY_TITLE);
    }

    public void setPhoto(ParseFile photo) {
        put(KEY_PHOTO, photo);
    }

    public ParseFile getPhoto() {
        return getParseFile(KEY_PHOTO);
    }

    public void setCategory(String category) {
        put(KEY_CATEGORY, category);
    }

    public String getCategory() {
        return getString(KEY_CATEGORY);
    }
}
