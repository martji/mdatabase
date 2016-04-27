package com.martji.mdbhelper;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.j256.ormlite.android.apptools.OrmLiteSqliteOpenHelper;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.support.ConnectionSource;
import com.j256.ormlite.table.TableUtils;
import com.martji.mdbhelper.model.DataA;

import java.util.ArrayList;
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

    private static List<RuntimeExceptionDao<?, ?>> daos = new ArrayList<>();

    public MDBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase database, ConnectionSource connectionSource) {
        try {
            Log.d(TAG, "onCreate");
            TableUtils.createTableIfNotExists(connectionSource, DataA.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onUpgrade(SQLiteDatabase database, ConnectionSource connectionSource, int oldVersion, int newVersion) {

    }

    @Override
    public void close() {
        super.close();
        if (daos.size() > 0) {
            for (RuntimeExceptionDao<?, ?> dao : daos) {
                dao = null;
            }
        }
    }

    public static void init(Context context) {
        mContext = context;
        helper = new MDBHelper(mContext);
    }

    public static synchronized MDBHelper getHelper() {
        if (helper == null) {
            helper = new MDBHelper(mContext);
        }
        return helper;
    }

    public static <T> RuntimeExceptionDao<T, ?> getDBDao(Class<T> c) {
        RuntimeExceptionDao<T, ?> dao= getHelper().getRuntimeExceptionDao(c);
        if (!daos.contains(dao)) {
            daos.add(dao);
        }
        return dao;
    }
}
