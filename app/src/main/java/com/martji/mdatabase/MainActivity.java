package com.martji.mdatabase;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.martji.mdbhelper.MDBHelper;
import com.martji.mdbhelper.model.TestData;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MDBHelper.init(this);
    }
}
