package com.martji.mdbhelper.model;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.martji.mdbhelper.MDBHelper;
import com.martji.mdbhelper.MRecord;

import java.io.Serializable;

/**
 * Created by Guoqing on 2016/4/26.
 */
@DatabaseTable(tableName = "a_data")
public class DataA extends MRecord implements Serializable {

    @DatabaseField(columnName = "name", canBeNull = false)
    private String name;
    @DatabaseField
    private Integer age;

    private static RuntimeExceptionDao<DataA, ?> dao;

    static {
        dao = MDBHelper.getDBDao(DataA.class);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public DataA() {

    }

    @Override
    public boolean save() {
        Dao.CreateOrUpdateStatus result = dao.createOrUpdate(this);
        return result.isCreated() || result.isUpdated();
    }

    @Override
    public boolean delete() {
        int result = dao.delete(this);
        return result == 0;
    }
}
