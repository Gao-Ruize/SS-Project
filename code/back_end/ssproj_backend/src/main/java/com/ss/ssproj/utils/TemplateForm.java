package com.ss.ssproj.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;

public class TemplateForm {
    String touser;
    String template_id;
    JSONObject data;

    public void setData(JSONObject data) {
        this.data = data;
    }

    public JSONObject getData() {
        return this.data;
    }

    public void setTemplate_id(String template_id) {
        this.template_id = template_id;
    }

    public String getTemplate_id() {
        return this.template_id;
    }

    public void setTouser(String touser) {
        this.touser = touser;
    }

    public String getTouser() {
        return this.touser;
    }

    public TemplateForm () {}

    public TemplateForm (String touser, String template_id, JSONObject data) {
        this.touser = touser;
        this.template_id = template_id;
        this.data = data;
    }
}
