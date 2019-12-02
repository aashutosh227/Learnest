package com.example.learnest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

import static android.util.Patterns.EMAIL_ADDRESS;

public class registrationActivity extends AppCompatActivity {

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
    //DatabaseHelper1 mdatabase;
    EditText uname,mail,password,cpassword;
    Button register,cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        databaseHelper = new DatabaseHelper(this);

        uname = (EditText) findViewById(R.id.username);
        mail = (EditText) findViewById(R.id.mail);
        password = (EditText) findViewById(R.id.pass);
        cpassword = (EditText) findViewById(R.id.cpass);
        register = (Button) findViewById(R.id.button);
        cancel = (Button) findViewById(R.id.button2);
        //mdatabase = new DatabaseHelper1(this);

    }

    private boolean validateEmail() {
        String emailInput = mail.getText().toString().trim();
        if (emailInput.isEmpty()){
            mail.setError("Field can't be empty");
            return false;
        } else if (!EMAIL_ADDRESS.matcher(emailInput).matches()) {
            mail.setError("Please enter a valid email address");
            return false;
        } else {
            mail.setError(null);
            return true;
        }
    }

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
        String passwordInput = password.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            password.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            password.setError("Password too weak");
            return false;
        } else {
            password.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {
        if (!validateEmail() | !validateUsername() | !validatePassword()) {
            return;
        }

        Boolean checkusername = databaseHelper.CheckUsername(uname.getText().toString().trim());
        if(checkusername == true){
            Boolean insert = databaseHelper.Insert(uname.getText().toString().trim(), password.getText().toString().trim());
            Toast.makeText(this, "Registration Successful", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        else{
            Toast.makeText(this, "Username already taken", Toast.LENGTH_SHORT).show();
        }

        String input = "Email: " + mail.getText().toString();
        input += "\n";
        input += "Username: " + uname.getText().toString();
        input += "\n";
        input += "Password: " + password.getText().toString();

//        db=new DbHandler(registrationActivity.this);
////inserting dummy users
//        db.addUser(new User(uname.getText().toString(), password.getText().toString()));


    }
}
