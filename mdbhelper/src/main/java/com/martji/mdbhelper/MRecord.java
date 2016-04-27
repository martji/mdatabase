package com.martji.mdbhelper;

import com.google.gson.annotations.SerializedName;
import com.j256.ormlite.field.DatabaseField;

import java.io.Serializable;
import java.util.List;

/**
 * Created by magq on 16/4/27.
 */
public abstract class MRecord implements MRecordInterface, Serializable {

    @SerializedName("mid")
    @DatabaseField(generatedId = true)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public static <T> List<?> listAll(Class<T> c) {
        return MDBHelper.getDBDao(c).queryForAll();
    }
}
