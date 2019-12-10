package com.owais.oddity.SQLite.ORM;

import android.content.Context;
import android.database.Cursor;

import java.util.List;

public interface InterfaceORM<T> {

    T cursorToObject(Cursor cursor);

    void add(Context context, T t);

    void clearAll(Context context);

    List<T> getAll(Context context);

}