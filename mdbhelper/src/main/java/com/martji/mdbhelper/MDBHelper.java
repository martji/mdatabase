package com.martji.mdbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.support.ConnectionSource;

/**
 * Created by magq on 16/4/26.
 */
public class MDBHelper extends OrmLiteSqliteOpenHelper {

    private static final String DB_NAME = "test.db";
    private static final int DB_VERSION = 1;

    private static MDBHelper helper = null;

    public MDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    public static MDBHelper getHelper(Context context) {
        if (helper == null) {
            helper = new MDBHelper(context);
        }
        return helper;
    }
}
