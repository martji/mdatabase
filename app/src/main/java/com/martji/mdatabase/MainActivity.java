package com.martji.mdatabase;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.martji.mdbhelper.MDBHelper;
import com.martji.mdbhelper.model.DataA;
import com.squareup.okhttp.Call;
import com.squareup.okhttp.Callback;
import com.squareup.okhttp.FormEncodingBuilder;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import java.io.IOException;
import java.net.CookieManager;
import java.net.CookiePolicy;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = "MainActivity";

    private Button btn_db;
    private Button btn_ok;
    private TextView tv;

    private OkHttpClient mOkHttpClient = new OkHttpClient();
    private String TOKEN;
    {
        CookieManager cookieManager = new CookieManager();
        cookieManager.setCookiePolicy(CookiePolicy.ACCEPT_ALL);
        mOkHttpClient.setCookieHandler(cookieManager);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_db = (Button) findViewById(R.id.btn);
        btn_ok = (Button) findViewById(R.id.btn_ok);
        tv = (TextView) findViewById(R.id.tv_content);

        MDBHelper.init(this);
        setListener();

        updateTv(DataA.listAll(DataA.class));
        getToken();
    }

    private void setListener() {
        btn_db.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DataA data = new DataA();
                data.setName("ma");
                data.setAge(10);
                data.save();

                updateTv(DataA.listAll(DataA.class));
            }
        });

        btn_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "ok http");
                getData();
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

    public void getData() {
        FormEncodingBuilder builder = new FormEncodingBuilder();
        builder.add("j_username", "admin");
        builder.add("j_password", "admin");
        builder.add("remember-me", "true");
        builder.add("submit", "Login");
        Request request = new Request.Builder()
                .url("http://123.56.204.6:8080/api/authentication")
                .post(builder.build())
                .addHeader("X-CSRF-TOKEN", TOKEN)
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {
            }

            @Override
            public void onResponse(Response response) throws IOException {
                final String result = response.body().string();
                Log.d(TAG, response.code() + ": " + result);
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        tv.setText(result);
                    }
                });
            }
        });
    }

    public void getToken() {
        Request request = new Request.Builder()
                .url("http://123.56.204.6:8080/api/account")
                .build();
        Call call = mOkHttpClient.newCall(request);
        call.enqueue(new Callback() {
            @Override
            public void onFailure(Request request, IOException e) {}
            @Override
            public void onResponse(Response response) throws IOException {
                TOKEN = ((CookieManager) mOkHttpClient.getCookieHandler()).getCookieStore().getCookies().get(1).getValue();
                Log.d(TAG, TOKEN);
            }
        });
    }
}
