package com.example.kidz.project_uas.DataMember;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kidz.project_uas.R;

public class LoginAdmin extends AppCompatActivity {

    EditText edTextUser, edTextPass;
    Button btnLoginAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.admin_login);

        edTextUser = (EditText) findViewById(R.id.edTextUser);
        edTextPass = (EditText) findViewById(R.id.edTextPass);
        btnLoginAdmin = (Button) findViewById(R.id.btnLoginAdmin);

        btnLoginAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = edTextUser.getText().toString();
                String password = edTextPass.getText().toString();

                if (username.equals("admin") && password.equals("admin123")) {
                    Toast.makeText(getApplicationContext(), "Login Success!",
                            Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(LoginAdmin.this, DataActivity.class);
                    LoginAdmin.this.startActivity(intent);
                    finish();
                } else {
                    AlertDialog.Builder builder = new AlertDialog.Builder(LoginAdmin.this);
                    builder.setMessage("Username or Password is Wrong!")
                            .setNegativeButton("Retry", null).create().show();
                }
            }
        });
    }
}
