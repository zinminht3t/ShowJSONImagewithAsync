package me.zinminhtet.jsonimage;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;

public class EmpDetailsActivity extends Activity {

    @SuppressLint("StaticFieldLeak")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_emp_details);
        Intent i = getIntent();
        String eid = i.getStringExtra("eid");

        new AsyncTask<String, Void, Employee>() {
            @Override
            protected Employee doInBackground(String... params) {
                return Employee.getEmp(params[0]);
            }

            @Override
            protected void onPostExecute(Employee result) {
                show(result);
            }
        }.execute(eid);

    }

    @SuppressLint("StaticFieldLeak")
    void show(Employee emp) {
        int[] ids = {R.id.editText1, R.id.editText2, R.id.editText3, R.id.editText4};
        String[] keys = {"Name", "Id", "Salary", "Address"};
        for (int i = 0; i < keys.length; i++) {
            EditText e = findViewById(ids[i]);
            e.setText(emp.get(keys[i]));
        }

        new AsyncTask<String, Void, Bitmap>() {
            @Override
            protected Bitmap doInBackground(String... params) {
                return Employee.getPhoto(false, params[0]);
            }

            @Override
            protected void onPostExecute(Bitmap result) {
                ImageView image = (ImageView) findViewById(R.id.imageView);
                image.setImageBitmap(result);
            }
        }.execute(emp.get("Id"));
    }
}
