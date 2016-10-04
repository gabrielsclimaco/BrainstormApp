package com.reforcointeligente.brainstormapp.Controller.Parse;

import com.parse.Parse;
import com.parse.ParseObject;
import com.reforcointeligente.brainstormapp.Model.Student;

public class ParseInitializer extends android.app.Application {

    @Override
    public void onCreate() {
        super.onCreate();

        initializeParse();
    }

    private boolean initializeParse(){
        try {
            Parse.initialize(new Parse.Configuration.Builder(this)
                    .applicationId("brainstorm-freelancer")
                    .server("http://brainstorm-freelancer.herokuapp.com")
//                    .clientKey("kijasijijasiasjsiajalllkaosiajhsis")
                    .build()
            );

            return true;
        } catch (Exception e) {
            return false;
        }
    }

//    public boolean registerParserSubClasses() {
//        try {
//            ParseObject.registerSubclass(Student.class);
//            return true;
//        } catch (Exception e) {
//            return false;
//        }
//    }
}
