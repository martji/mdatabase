package com.martji.mdbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.martji.mdbhelper.model.TestData;

import java.sql.SQLException;
import java.util.List;

/**
 * Created by magq on 16/4/26.
 */
public class MDBHelper extends OrmLiteSqliteOpenHelper {

    private final String TAG = "MDBHelper";

    private static final String DB_NAME = "test.db";
    private static final int DB_VERSION = 1;

    private static Context mContext;
    private static MDBHelper helper = null;

    public MDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            List<Class<?>> classes = ClassFinder.find("com.martji.mdbhelper.model");
            for (Class<?> c : classes) {
                Log.d(TAG, c.toString());
                TableUtils.createTableIfNotExists(connectionSource, c);
            }

            TestData data = new TestData();
            data.setName("ma");
            data.setAge(10);
            data.save();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public static void init(Context context) {
        mContext = context;
        getHelper();
    }

    public static synchronized MDBHelper getHelper() {
        if (helper == null) {
            helper = new MDBHelper(mContext);
        }
        return helper;
    }
}
