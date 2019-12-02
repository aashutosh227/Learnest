package com.example.learnest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.regex.Pattern;

public class updatePassword extends AppCompatActivity {
    private EditText uname;
    private EditText old_pwd;
    private EditText new_pwd;
    private DatabaseHelper databaseHelper;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_password);

        uname = findViewById(R.id.uname);
        old_pwd = findViewById(R.id.old_pwd);
        new_pwd = findViewById(R.id.new_pwd);
        databaseHelper = new DatabaseHelper(this);

    }

    private boolean validatePassword() {
        String passwordInput = new_pwd.getText().toString().trim();

        if (passwordInput.isEmpty()) {
            new_pwd.setError("Field can't be empty");
            return false;
        } else if (!PASSWORD_PATTERN.matcher(passwordInput).matches()) {
            new_pwd.setError("Password too weak");
            return false;
        } else {
            new_pwd.setError(null);
            return true;
        }
    }

    public void confirmInput(View v) {
        if (!validatePassword()) {
            return;
        }

        Boolean checklogin = databaseHelper.CheckLogin(uname.getText().toString(), old_pwd.getText().toString());

        if(checklogin == true){

//            String input = "Username: " + uname.getText().toString();
//            input += "\n";
//            input += "Password: " + new_pwd.getText().toString();
//
//            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            Boolean update = databaseHelper.update(uname.getText().toString().trim(), new_pwd.getText().toString().trim());
            Toast.makeText(this, "Update Successful", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

        }
        else{
            Toast.makeText(this, "Invalid username or old password", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
