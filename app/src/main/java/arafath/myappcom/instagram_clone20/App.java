package arafath.myappcom.instagram_clone20;

import android.app.Application;

import com.parse.Parse;

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("H3ijFFpxIYkzNjvTNIdKau7FFUY5JL7KbXWaXVYA")
                // if defined
                .clientKey("cXCTE9og1t44CKS6b5CLNDXGVTn9C1cuWPklhDRv")
                .server("https://parseapi.back4app.com/")
                .build()
        );
    }
}
