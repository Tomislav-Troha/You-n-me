package com.example.myapplication.settings;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class ChangePassword extends AppCompatActivity {


    EditText oldPass;
    EditText newPass;
    Button changePass;

    @Override
    protected void onCreate(@Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.change_password);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        oldPass = findViewById(R.id.txOldPassword);
        newPass = findViewById(R.id.txNewPassword);
        changePass = findViewById(R.id.btnChangePassword);

        changePass.setOnClickListener(view -> changePassword());

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

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    String oldP = "";
    String newP = "";
    public void changePassword() {

         ParseUser currentUser = ParseUser.getCurrentUser();
         String userName = ParseUser.getCurrentUser().getUsername();

         if(!isEmpty(oldPass) && !isEmpty(newPass)){
             oldP = oldPass.getText().toString();
             newP = newPass.getText().toString();

            ParseUser.logInInBackground(ParseUser.getCurrentUser().getUsername(), oldP, new LogInCallback() {
                @Override
                public void done(ParseUser user, ParseException e) {
                    if (user != null) {
                        currentUser.setPassword(newP.toString());
                        currentUser.saveInBackground();
                        ParseUser.logOut();
                        ParseUser.logInInBackground(userName, newP.toString(), new LogInCallback() {
                            @Override
                            public void done(ParseUser parseUser, ParseException e) {
                                if (e == null) {
                                    Toast.makeText(ChangePassword.this, getString(R.string.passPromjenjen), Toast.LENGTH_LONG).show();
                                } else
                                    Toast.makeText(ChangePassword.this, getString(R.string.dosloJeDoGreske), Toast.LENGTH_LONG).show();
                            }
                        });

                    } else {
                        Toast.makeText(ChangePassword.this,e.getLocalizedMessage() + " " + getString(R.string.stariPassKriv), Toast.LENGTH_LONG).show();
                    }
                }
            });
        }

        if(isEmpty(oldPass)){
            Toast t = Toast.makeText(ChangePassword.this, getString(R.string.ispuniteSvaPolja), Toast.LENGTH_SHORT);
            t.show();
            oldPass.setError("Required");
        }

        if(isEmpty(newPass)){
            newPass.setError("Required");
        }

    }
}
