package br.com.hackathongdg.recycleplus;

import android.app.Application;

import com.parse.Parse;

public class RecyclePlusApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.enableLocalDatastore(this);
        Parse.initialize(this, "T9AFsqVYN1BKYhuKXtCx0xXyZO9SMqZRBSF2QzkJ", "PRXXc7Dc5xDXkigPKV8P4BW4dp37LJoxTgCHjC90");
    }
}
