package com.example.myapplication.settings;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.example.myapplication.login_register.MainActivity;
import com.parse.ParseUser;

public class Setting extends AppCompatActivity {


    RelativeLayout logOut;
    RelativeLayout changePass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.setting);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        logOut = findViewById(R.id.onClickLogOut);
        changePass = findViewById(R.id.onClickGoToChangePass);

        logOut.setOnClickListener(view -> logOutUser());
        changePass.setOnClickListener(view -> gotoChangePass());

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;

            default:
                // If we got here, the user's action was not recognized.
                // Invoke the superclass to handle it.
                return super.onOptionsItemSelected(item);
        }
    }


    public void logOutUser() {
        if (ParseUser.getCurrentUser() != null) {
            ParseUser.logOut();
            Toast.makeText(Setting.this, getString(R.string.odjava), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(Setting.this, MainActivity.class));
                } else { // user is out
                }
    }

    public void gotoChangePass() {
        startActivity(new Intent(Setting.this, ChangePassword.class));
    }

}
