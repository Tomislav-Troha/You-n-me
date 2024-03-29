package com.example.myapplication.login_register;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.myapplication.home.HomePage;
import com.example.myapplication.R;
import com.parse.LogInCallback;
import com.parse.ParseException;
import com.parse.ParseUser;

public class LoginPage extends AppCompatActivity {

    Button btnLogin;

    EditText email;
    EditText lozinka;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        btnLogin = findViewById(R.id.onClickLogin);


        email =  findViewById(R.id.editLoginEmail);
        lozinka =  findViewById(R.id.editLoginPassword);

        loginOnClick();

    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }


    public void loginOnClick() {
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              // startActivity(new Intent(LoginPage.this, HomePage.class));

             if(!isEmpty(email) && !isEmpty(lozinka)){
                    ParseUser.logInInBackground(email.getText().toString(), lozinka.getText().toString(), new LogInCallback() {
                        @Override
                        public void done(ParseUser user, ParseException e) {
                            if(user != null){
                                Toast.makeText(getApplicationContext(), getString(R.string.loginUpsijesan), Toast.LENGTH_LONG).show();
                                startActivity(new Intent(LoginPage.this, HomePage.class));
                                finish();
                            }else {
                                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
                }

                if(isEmpty(email)){
                    Toast t = Toast.makeText(LoginPage.this, getString(R.string.ispuniteSvaPolja), Toast.LENGTH_SHORT);
                    t.show();
                    email.setError("Required");
                }

                if(isEmpty(lozinka)){
                    lozinka.setError("Required");
                }
            }
        });
    }
}
