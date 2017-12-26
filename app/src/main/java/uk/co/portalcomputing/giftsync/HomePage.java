package uk.co.portalcomputing.giftsync;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SharedPreferences sharedPreferences =
                this.getSharedPreferences("Account",MODE_PRIVATE);
        //get current logged in state
        if(sharedPreferences.getBoolean("LoggedIn",true)){//TODO make "false" when 'login' and 'account create' has been implemented
            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        } else {
            Intent intent = new Intent(this, LoginOrReg.class);
            startActivity(intent);
        }
        finish();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
    }
}
