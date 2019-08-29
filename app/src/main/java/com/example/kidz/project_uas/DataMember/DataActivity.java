package com.example.kidz.project_uas.DataMember;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.kidz.project_uas.R;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class DataActivity extends AppCompatActivity {

    public static final String DATABASE_NAME = "MEMBER.db";

    Button textViewViewMember;
    EditText editTextName, editTextAlamat;
    Spinner spinnerMember;

    SQLiteDatabase mDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);

        textViewViewMember = (Button) findViewById(R.id.textViewViewMember);
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAlamat = (EditText) findViewById(R.id.editTextAlamat);
        spinnerMember = (Spinner) findViewById(R.id.spinnerMember);


        //creating a database
        mDatabase = openOrCreateDatabase(DATABASE_NAME, MODE_PRIVATE, null);

        createDataMemberTable();
    }


    //this method will create the table
    //as we are going to call this method everytime we will launch the application
    //I have added IF NOT EXISTS to the SQL
    //so it will only create the table when the table is not already created
    private void createDataMemberTable() {
        mDatabase.execSQL(
                "CREATE TABLE IF NOT EXISTS member (\n" +
                        "    id INTEGER NOT NULL CONSTRAINT member_pk PRIMARY KEY AUTOINCREMENT,\n" +
                        "    name varchar(200) NOT NULL,\n" +
                        "    list varchar(200) NOT NULL,\n" +
                        "    joiningdate datetime NOT NULL,\n" +
                        "    alamat varchar(200) NOT NULL\n" +
                        ");"
        );
    }

    //this method will validate the name and salary
    //dept does not need validation as it is a spinner and it cannot be empty
    private boolean inputsAreCorrect(String name, String alamat) {
        if (name.isEmpty()) {
            editTextName.setError("Tolong Isi Nama!");
            editTextName.requestFocus();
            return false;
        }

        if (alamat.isEmpty()) {
            editTextAlamat.setError("Tolong Isi Alamat!");
            editTextAlamat.requestFocus();
            return false;
        }
        return true;
    }

    //In this method we will do the create operation
    private void addDataMember() {

        String name = editTextName.getText().toString().trim();
        String alamat = editTextAlamat.getText().toString().trim();
        String list = spinnerMember.getSelectedItem().toString();

        //getting the current time for joining date
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String joiningDate = sdf.format(cal.getTime());

        //validating the inptus
        if (inputsAreCorrect(name, alamat)) {

            String insertSQL = "INSERT INTO member \n" +
                    "(name, list, joiningdate, alamat)\n" +
                    "VALUES \n" +
                    "(?, ?, ?, ?);";

            //using the same method execsql for inserting values
            //this time it has two parameters
            //first is the sql string and second is the parameters that is to be binded with the query
            mDatabase.execSQL(insertSQL, new String[]{name, list, joiningDate, alamat});

            Toast.makeText(this, "Data Member Berhasil Ditambahkan", Toast.LENGTH_SHORT).show();
        }
    }

    public void onklik(View view) {
        switch (view.getId()) {
            case R.id.buttonAddMember:

                addDataMember();

                break;
            case R.id.textViewViewMember:
                Intent i = new Intent(DataActivity.this, MemberActivity.class);
                startActivity(i);
                break;
        }
    }
}