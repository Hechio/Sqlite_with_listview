package com.stevehechio.course;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditCourse extends AppCompatActivity implements View.OnClickListener {
    private EditText mCode,mTitle,mLec;
    private Button mButton;
    private CourseDatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_course);
        collectId();
        mButton.setOnClickListener(this);
    }

    private void collectId() {
        mCode=findViewById(R.id.etCode);
        mTitle=findViewById(R.id.etTitle);
        mLec=findViewById(R.id.etLecturer);
        mButton=findViewById(R.id.btnAdd);
        databaseHelper = new CourseDatabaseHelper(this);
    }

    @Override
    public void onClick(View view) {
        if (view==mButton){

            insertData();
        }

    }

    private void insertData() {
        String code = mCode.getText().toString().toUpperCase().trim();
        String title = mTitle.getText().toString().toUpperCase().trim();
        String lec = mLec.getText().toString().toUpperCase().trim();
        if (code.length() !=0 || title.length() !=0 || lec.length() !=0){
            addData(code,title,lec);
        }
        else {
            Toast.makeText(getApplicationContext(),"Fill all the fileds",Toast.LENGTH_SHORT).show();
        }


    }

    private void addData(String code, String title, String lec) {
        boolean insertData = databaseHelper.insertData(code,title,lec);

        if (insertData){
            Toast.makeText(getApplicationContext(),"Data successfully saved",Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(),MainActivity.class));
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(),"Data not inserted",Toast.LENGTH_SHORT).show();
        }

    }
}
