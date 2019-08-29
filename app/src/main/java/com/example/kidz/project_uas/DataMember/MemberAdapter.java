package com.example.kidz.project_uas.DataMember;

import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kidz.project_uas.R;

import java.util.List;


public class MemberAdapter extends ArrayAdapter<Member> {

    Context mCtx;
    int listLayoutRes;
    List<Member> memberList;
    SQLiteDatabase mDatabase;

    public MemberAdapter(Context mCtx, int listLayoutRes, List<Member> memberList, SQLiteDatabase mDatabase) {
        super(mCtx, listLayoutRes, memberList);

        this.mCtx = mCtx;
        this.listLayoutRes = listLayoutRes;
        this.memberList = memberList;
        this.mDatabase = mDatabase;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(listLayoutRes, null);

        final Member member = memberList.get(position);


        TextView textViewName = view.findViewById(R.id.textViewName);
        TextView textViewList = view.findViewById(R.id.textViewList);
        TextView textViewAlamat = view.findViewById(R.id.textViewAlamat);
        TextView textViewJoiningDate = view.findViewById(R.id.textViewJoiningDate);


        textViewName.setText(member.getName());
        textViewList.setText(member.getList());
        textViewAlamat.setText(member.getAlamat());
        textViewJoiningDate.setText(member.getJoiningDate());


        Button buttonDelete = view.findViewById(R.id.buttonDeleteMember);
        Button buttonEdit = view.findViewById(R.id.buttonEditMember);

        //adding a clicklistener to button
        buttonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateDataMember(member);
            }
        });

        //the delete operation
        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);
                builder.setTitle("Are you sure?");
                builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        String sql = "DELETE FROM member WHERE id = ?";
                        mDatabase.execSQL(sql, new Integer[]{member.getId()});
                        reloadMemberFromDatabase();
                    }
                });
                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {

                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });

        return view;
    }

    private void updateDataMember(final Member member) {
        final AlertDialog.Builder builder = new AlertDialog.Builder(mCtx);

        LayoutInflater inflater = LayoutInflater.from(mCtx);
        View view = inflater.inflate(R.layout.dialog_update_member, null);
        builder.setView(view);


        final EditText editTextName = view.findViewById(R.id.editTextName);
        final EditText editTextAlamat = view.findViewById(R.id.editTextAlamat);
        final Spinner spinnerMember = view.findViewById(R.id.spinnerMember);

        editTextName.setText(member.getName());
        editTextAlamat.setText(member.getAlamat());

        final AlertDialog dialog = builder.create();
        dialog.show();

        view.findViewById(R.id.buttonUpdateMember).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String alamat = editTextAlamat.getText().toString().trim();
                String list = spinnerMember.getSelectedItem().toString();

                if (name.isEmpty()) {
                    editTextName.setError("Nama Tidak Boleh Kosong");
                    editTextName.requestFocus();
                    return;
                }

                if (alamat.isEmpty()) {
                    editTextAlamat.setError("Tolong Isi Angkatan Anda");
                    editTextAlamat.requestFocus();
                    return;
                }

                String sql = "UPDATE member \n" +
                        "SET name = ?, \n" +
                        "list = ?, \n" +
                        "alamat = ? \n" +
                        "WHERE id = ?;\n";

                mDatabase.execSQL(sql, new String[]{name, list, alamat, String.valueOf(member.getId())});
                Toast.makeText(mCtx, "Member Telah Diupdate", Toast.LENGTH_SHORT).show();
                reloadMemberFromDatabase();

                dialog.dismiss();
            }
        });
    }

    private void reloadMemberFromDatabase() {
        Cursor cursorMember = mDatabase.rawQuery("SELECT * FROM member", null);
        if (cursorMember.moveToFirst()) {
            memberList.clear();
            do {
                memberList.add(new Member(
                        cursorMember.getInt(0),
                        cursorMember.getString(1),
                        cursorMember.getString(2),
                        cursorMember.getString(3),
                        cursorMember.getString(4)
                ));
            } while (cursorMember.moveToNext());
        }
        cursorMember.close();
        notifyDataSetChanged();
    }

}
