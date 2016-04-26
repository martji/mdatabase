package com.martji.mdbhelper;

import java.util.List;

/**
 * Created by magq on 16/4/26.
 */
public interface MRecord {
    boolean save();
    List<Object> listAll();
}
