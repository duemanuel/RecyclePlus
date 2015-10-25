package br.com.hackathongdg.recycleplus;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

import br.com.hackathongdg.recycleplus.model.Company;
import br.com.hackathongdg.recycleplus.model.Product;
import br.com.hackathongdg.recycleplus.model.Sale;

public class RecyclePlusApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        ParseObject.registerSubclass(Product.class);
        ParseObject.registerSubclass(Company.class);
        ParseObject.registerSubclass(Sale.class);
        Parse.initialize(this, "T9AFsqVYN1BKYhuKXtCx0xXyZO9SMqZRBSF2QzkJ", "PRXXc7Dc5xDXkigPKV8P4BW4dp37LJoxTgCHjC90");
    }
}
