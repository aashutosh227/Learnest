package com.example.learnest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.os.Handler;
import android.widget.TextView;
import android.util.Patterns;
import java.util.regex.Pattern;
import android.widget.Toast;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    DbHandler db;
    DatabaseHelper databaseHelper;

    private static final Pattern PASSWORD_PATTERN =
            Pattern.compile("^" +
                    //"(?=.*[0-9])" +         //at least 1 digit
                    //"(?=.*[a-z])" +         //at least 1 lower case letter
                    //"(?=.*[A-Z])" +         //at least 1 upper case letter
                    "(?=.*[a-zA-Z])" +      //any letter
                    "(?=.*[@#$%^&+=])" +    //at least 1 special character
                    "(?=\\S+$)" +           //no white spaces
                    ".{4,}" +               //at least 4 characters
                    "$");

    RelativeLayout rellay1, rellay2,rellay3;
    LinearLayout signUp_linlay;
    private TextView appNameView;
    private EditText uname;
    private EditText u_pwd;

    Handler handler1 = new Handler();
    Runnable runnable1 = new Runnable() {
        @Override
        public void run() {
            rellay1.setVisibility(View.VISIBLE);
            rellay2.setVisibility(View.VISIBLE);
            rellay3.setVisibility(View.VISIBLE);
            signUp_linlay.setVisibility(View.VISIBLE);
        }
    };

    Handler handler2 = new Handler();
    Runnable runnable2 = new Runnable() {
        @Override
        public void run() {
            appNameView.setVisibility(View.VISIBLE);
        }
    };

    private boolean validateUsername() {
        String usernameInput = uname.getText().toString().trim();

        if (usernameInput.isEmpty()) {
            uname.setError("Field can't be empty");
            return false;
        } else if (usernameInput.length() > 15) {
            uname.setError("Username too long");
            return false;
        } else {
            uname.setError(null);
            return true;
        }
    }

    private boolean validatePassword() {
        String passwordInput = u_pwd.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            u_pwd.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            u_pwd.setError("Password too weak");
            return false;
        } else {
            u_pwd.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {
        if (validateUsername() && validatePassword()) {

//            return;
//            int id= checkUser(new User(uname.getText().toString(),u_pwd.getText().toString()));
//            if(id==-1)
//            {
//                Toast.makeText(MainActivity.this,"User Does Not Exist",Toast.LENGTH_SHORT).show();
//            }
//            else
//            {
//                Toast.makeText(MainActivity.this,"User Exist "+uname.getText().toString(),Toast.LENGTH_SHORT).show();
//            }

            Boolean checklogin = databaseHelper.CheckLogin(uname.getText().toString(), u_pwd.getText().toString());

            if(checklogin == true){

                String input = "Username: " + uname.getText().toString();
                input += "\n";
                input += "Password: " + u_pwd.getText().toString();

                Toast.makeText(this, "Login Successful", Toast.LENGTH_SHORT).show();

                Intent intent = new Intent(this,Walkthrough.class);
                startActivity(intent);

            }
            else{
                Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
            }

        }

//        String input = "Username: " + uname.getText().toString();
//        input += "\n";
//        input += "Password: " + u_pwd.getText().toString();
//
//        Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
//
//        Intent intent = new Intent(this,homeActivity.class);
//        startActivity(intent);

    }

    public int checkUser(User user)
    {
        return db.checkUser(user);
    }

    public void toRegisterPage(View v){
        Intent intent = new Intent(this,registrationActivity.class);
        startActivity(intent);
    }
// Commit test
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        databaseHelper = new DatabaseHelper(this);

        uname = findViewById(R.id.uname);
        u_pwd = findViewById(R.id.user_pwd);

        rellay1 = (RelativeLayout) findViewById(R.id.rellay1);
        rellay2 = (RelativeLayout) findViewById(R.id.rellay2);
        rellay3 = (RelativeLayout) findViewById(R.id.rellay3);
        signUp_linlay = (LinearLayout) findViewById(R.id.linlay2);
        appNameView = findViewById(R.id.AppName_view);

        handler2.postDelayed(runnable2, 3000);
        handler1.postDelayed(runnable1, 3000); //2000 is the timeout for the splas
    }


}
