package com.example.sqlitedemo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    EditText editTextName, editTextNic;
    Button addBtn, viewBtn;
    ListView userList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextName = findViewById(R.id.name);
        editTextNic = findViewById(R.id.nic);
        addBtn = findViewById(R.id.add_btn);
        viewBtn = findViewById(R.id.view_btn);
        userList = findViewById(R.id.user_list);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                UserModel userModel;
                userModel = new UserModel(editTextNic.getText().toString(), editTextName.getText().toString());
                Toast.makeText(MainActivity.this, userModel.toString(), Toast.LENGTH_SHORT).show();

                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);

                boolean success = databaseHelper.addOne(userModel);
                Toast.makeText(MainActivity.this, "Success "+success, Toast.LENGTH_SHORT).show();
            }
        });

        viewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                List<UserModel> userModelList = databaseHelper.getUsers();

                ArrayAdapter userArrayAdapter = new ArrayAdapter<UserModel>(MainActivity.this, android.R.layout.simple_list_item_1, userModelList);
                userList.setAdapter(userArrayAdapter);
            }
        });

        userList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                UserModel clickedUser = (UserModel) parent.getItemAtPosition(position);
                DatabaseHelper databaseHelper = new DatabaseHelper(MainActivity.this);
                databaseHelper.deleteOne(clickedUser);
                Toast.makeText(MainActivity.this, "Deleted "+clickedUser.toString(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}