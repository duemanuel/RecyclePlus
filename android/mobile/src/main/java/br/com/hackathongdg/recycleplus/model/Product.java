package br.com.hackathongdg.recycleplus.model;

import com.parse.ParseClassName;
import com.parse.ParseObject;

@ParseClassName("Product")
public class Product extends ParseObject {

    public enum Category {
        PLASTIC("Plástico", 0xFFEF5350), METAL("Metal", 0xFFFFAB00), ELECTRONIC("Lixo Eletrônico", 0xFF607D8B), PAPER("Papel", 0xFF26C6DA), OTHER("Outros", 0xFF795548);

        private String mName;
        private int mColor;

        Category(String name, int color) {
            mName = name;
            mColor = color;
        }

        public String getName() {
            return mName;
        }

        public int getColor() {
            return mColor;
        }
    }

    private final static String KEY_TITLE = "title'";
    private final static String KEY_PHOTO = "photo";
    private final static String KEY_CATEGORY = "category";
    private final static String KEY_RECYCLED = "recycled";

    public void setTitle(String title) {
        put(KEY_TITLE, title);
    }

    public String getTitle() {
        return getString(KEY_TITLE);
    }

    public void setPhoto(String photo) {
        put(KEY_PHOTO, photo);
    }

    public String getPhoto() {
        return getString(KEY_PHOTO);
    }

    public void setCategory(Category category) {
        put(KEY_CATEGORY, category.ordinal());
    }

    public Category getCategory() {
        return Category.values()[getInt(KEY_CATEGORY)];
    }

    public void setRecycled(boolean recycled) {
        put(KEY_RECYCLED, recycled);
    }

    public boolean isRecycled() {
        return getBoolean(KEY_RECYCLED);
    }
}
