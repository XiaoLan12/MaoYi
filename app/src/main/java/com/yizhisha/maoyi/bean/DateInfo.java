package com.yizhisha.maoyi.bean;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * 作者：Yzp on 2017-04-14 10:53
 * 邮箱：15111424807@163.com
 * QQ: 486492302
 */
public class DateInfo {

    public JSONObject DateInfo(){
        //定义假数据
        JSONObject jsonObjects = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("attr_name","尺码");
            JSONArray jsonArray1 = new JSONArray();
            JSONObject jsonObject1 = new JSONObject();
            jsonObject1.put("attr_value","S");
            jsonObject1.put("img_url","https://img.alicdn.com/bao/uploaded/i3/TB1GWrsPpXXXXc.XFXXXXXXXXXX_!!0-item_pic.jpg_60x60q90.jpg");
            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("attr_value","M");
            jsonObject2.put("img_url","https://img.alicdn.com/imgextra/i1/748323357/TB2SYMtlXXXXXajXpXXXXXXXXXX_!!748323357.jpg_60x60q90.jpg");
            JSONObject jsonObject3 = new JSONObject();
            jsonObject3.put("attr_value","L");
            jsonObject3.put("img_url","https://img.alicdn.com/imgextra/i1/748323357/TB2ODqmmVXXXXbBXpXXXXXXXXXX_!!748323357.jpg_60x60q90.jpg");
            JSONObject jsonObject4 = new JSONObject();
            jsonObject4.put("attr_value","XL");
            jsonObject4.put("img_url","https://img.alicdn.com/imgextra/i1/748323357/TB2mVa5mVXXXXXeXXXXXXXXXXXX_!!748323357.jpg_60x60q90.jpg");
            JSONObject jsonObject5 = new JSONObject();
            jsonObject5.put("attr_value","XXL");
            jsonObject5.put("img_url","https://img.alicdn.com/imgextra/i2/748323357/TB2OrA3itFopuFjSZFHXXbSlXXa_!!748323357.jpg_60x60q90.jpg");
            jsonArray1.put(jsonObject1);
            jsonArray1.put(jsonObject2);
            jsonArray1.put(jsonObject3);
            jsonArray1.put(jsonObject4);
            jsonArray1.put(jsonObject5);
            jsonObject.put("attr_list",jsonArray1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        JSONObject jsonObject1 = new JSONObject();
        try {
            jsonObject1.put("attr_name","颜色");
            JSONArray jsonArray1 = new JSONArray();
            JSONObject jsonObject2 = new JSONObject();
            jsonObject2.put("attr_value","赤色");
            jsonObject2.put("img_url","https://img.alicdn.com/bao/uploaded/i3/TB1GWrsPpXXXXc.XFXXXXXXXXXX_!!0-item_pic.jpg_60x60q90.jpg");
            JSONObject jsonObject3 = new JSONObject();
            jsonObject3.put("attr_value","橙色");
            jsonObject3.put("img_url","https://img.alicdn.com/imgextra/i1/748323357/TB2SYMtlXXXXXajXpXXXXXXXXXX_!!748323357.jpg_60x60q90.jpg");
            JSONObject jsonObject4 = new JSONObject();
            jsonObject4.put("attr_value","黄色");
            jsonObject4.put("img_url","https://img.alicdn.com/imgextra/i1/748323357/TB2ODqmmVXXXXbBXpXXXXXXXXXX_!!748323357.jpg_60x60q90.jpg");
            JSONObject jsonObject5 = new JSONObject();
            jsonObject5.put("attr_value","绿色");
            jsonObject5.put("img_url","https://img.alicdn.com/imgextra/i1/748323357/TB2mVa5mVXXXXXeXXXXXXXXXXXX_!!748323357.jpg_60x60q90.jpg");
            JSONObject jsonObject6 = new JSONObject();
            jsonObject6.put("attr_value","青色");
            jsonObject6.put("img_url","https://img.alicdn.com/imgextra/i4/748323357/TB213aPnXXXXXc3XpXXXXXXXXXX_!!748323357.jpg_60x60q90.jpg");
            JSONObject jsonObject7 = new JSONObject();
            jsonObject7.put("attr_value","蓝色");
            jsonObject7.put("img_url","https://img.alicdn.com/imgextra/i4/748323357/TB209VEdiC9MuFjSZFoXXbUzFXa_!!748323357.jpg_60x60q90.jpg");
            JSONObject jsonObject8 = new JSONObject();
            jsonObject8.put("attr_value","紫色");
            jsonObject8.put("img_url","https://img.alicdn.com/imgextra/i2/748323357/TB2OrA3itFopuFjSZFHXXbSlXXa_!!748323357.jpg_60x60q90.jpg");
            jsonArray1.put(jsonObject2);
            jsonArray1.put(jsonObject3);
            jsonArray1.put(jsonObject4);
            jsonArray1.put(jsonObject5);
            jsonArray1.put(jsonObject6);
            jsonArray1.put(jsonObject7);
            jsonArray1.put(jsonObject8);
            jsonObject1.put("attr_list",jsonArray1);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        jsonArray.put(jsonObject);
        jsonArray.put(jsonObject1);

        try {
            jsonObjects.put("goods_types",jsonArray);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObjects;
    }
}
