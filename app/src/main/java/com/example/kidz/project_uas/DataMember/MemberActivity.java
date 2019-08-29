package com.example.kidz.project_uas.DataMember;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import com.example.kidz.project_uas.R;

import java.util.ArrayList;
import java.util.List;

public class MemberActivity extends AppCompatActivity {

    List<Member> memberList;
    SQLiteDatabase mDatabase;
    ListView ListViewMember;
    MemberAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member);

        ListViewMember = (ListView) findViewById(R.id.listViewMember);
        memberList = new ArrayList<>();

        //opening the database
        mDatabase = openOrCreateDatabase(DataActivity.DATABASE_NAME, MODE_PRIVATE, null);

        //this method will display the in the list
        showMhsiswaFromDatabase();
    }

    private void showMhsiswaFromDatabase() {
        //we used rawQuery(sql, selectionargs) for fetching all the mahasiswa
        Cursor cursorMember = mDatabase.rawQuery("SELECT * FROM member", null);

        //if the cursor has some data
        if (cursorMember.moveToFirst()) {
            //looping through all the records
            do {
                //pushing each record in the mahasiswa list
                memberList.add(new Member(
                        cursorMember.getInt(0),
                        cursorMember.getString(1),
                        cursorMember.getString(2),
                        cursorMember.getString(3),
                        cursorMember.getString(4)
                ));
            } while (cursorMember.moveToNext());
        }
        //closing the cursor
        cursorMember.close();

        //creating the adapter object
        adapter = new MemberAdapter(this, R.layout.list_layout_member, memberList, mDatabase);

        //adding the adapter to listview
        ListViewMember.setAdapter(adapter);
    }

}
