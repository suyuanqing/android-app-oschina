package com.usian.android_app_oschina.model.sql.bean;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

/**
 * Created by 苏元庆 on 2017/5/25.
 */
@DatabaseTable(tableName = "search")
public class SearchHistory {

    @DatabaseField(generatedId = true)
    private int id;

    @DatabaseField(columnName = "history")
    private String history;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }



    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }
}
