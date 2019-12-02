package com.example.learnest;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class deleteAccount extends AppCompatActivity {

    private EditText uname;
    private EditText pwd;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_accoun);
        uname = findViewById(R.id.uname);
        pwd = findViewById(R.id.pwd);

        databaseHelper = new DatabaseHelper(this);

    }

    public void confirmInput(View v) {

        Boolean checklogin = databaseHelper.CheckLogin(uname.getText().toString(), pwd.getText().toString());

        if(checklogin == true){

//            String input = "Username: " + uname.getText().toString();
//            input += "\n";
//            input += "Password: " + new_pwd.getText().toString();
//
//            Toast.makeText(this, input, Toast.LENGTH_SHORT).show();
            Boolean delete = databaseHelper.delete(uname.getText().toString().trim());
            Toast.makeText(this, "Delete Successful", Toast.LENGTH_SHORT).show();

            Intent intent = new Intent(this,MainActivity.class);
            startActivity(intent);

        }
        else{
            Toast.makeText(this, "Invalid username or password", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void finish() {
        super.finish();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
