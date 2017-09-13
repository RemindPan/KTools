package com.jiangkang.tools.struct;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by jiangkang on 2017/9/13.
 */

public class JsonGenerator {

    private JSONObject object = new JSONObject();

    private JSONArray array = new JSONArray();


    public JsonGenerator put(String name, Object value){
        JSONObject tmpObj = object;
        try {
            object.put(name,value);
        } catch (JSONException e) {
            object = tmpObj;
        }
        return this;
    }


    public JSONObject gen() {
        return object;
    }
}
