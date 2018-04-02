package com.wl.mylibrary.util;

import com.google.gson.TypeAdapter;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.IOException;
import java.lang.reflect.Type;

/**
 * json按key排序输出
 */
public class SensorTypeAdapter extends TypeAdapter<T> {

    @Override
    public void write(JsonWriter out, T value) throws IOException {
        out.beginObject();
        //按自定义顺序输出字段信息
        /*out.name("time").value(value.time);
        out.name("sensorType").value(value.sensorType);
        out.name("floats").value(value.floats.toString());*/
        out.endObject();
    }

    @Override
    public T read(JsonReader in) throws IOException {
        return null;
    }

    }