package com.martji.mdbhelper.model;

import com.j256.ormlite.dao.RuntimeExceptionDao;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;
import com.martji.mdbhelper.MDBHelper;
import com.martji.mdbhelper.MRecord;

import java.util.List;

/**
 * Created by Guoqing on 2016/4/26.
 */
@DatabaseTable(tableName = "testdata")
public class TestData implements MRecord {

    @DatabaseField(generatedId = true)
    private Integer id;
    @DatabaseField
    private String name;
    @DatabaseField
    private Integer age;

    private RuntimeExceptionDao<TestData, ?> runtimeDao;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public TestData() {
        init();
    }

    public void init() {
        try{
            runtimeDao = MDBHelper.getHelper().getRuntimeExceptionDao(TestData.class);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean save() {
        runtimeDao.createOrUpdate(this);
        return false;
    }

    @Override
    public List<Object> listAll() {
        return null;
    }
}
