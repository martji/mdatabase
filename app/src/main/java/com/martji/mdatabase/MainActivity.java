package com.martji.mdatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.martji.mdbhelper.MDBHelper;
import com.martji.mdbhelper.model.DataA;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private Button btn;
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn = (Button) findViewById(R.id.btn);
        tv = (TextView) findViewById(R.id.tv_content);

        MDBHelper.init(this);
        setListener();

        updateTv(DataA.listAll());
    }

    private void setListener() {
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataA data = new DataA();
                data.setName("ma");
                data.setAge(10);
                data.save();

                updateTv(DataA.listAll());
            }
        });
    }

    public void updateTv(List<?> datas) {
        if (datas == null) {
            return;
        }
        String out = "";
        for (Object data : datas) {
            DataA td = (DataA) data;
            out += td.getId() + "\t " + td.getName() + "\t " + td.getAge() + "\n";
        }
        tv.setText(out);
    }
}
