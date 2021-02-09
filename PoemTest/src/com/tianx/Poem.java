package com.tianx;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;

public class Poem {
    public String title, author, period;
    public ArrayList<ArrayList<String>> content = new ArrayList<>();

    public Poem(String title, JSONObject object) {
        this.title = title;
        this.author = (String) object.get("author");
        this.period = (String) object.get("period");
        JSONArray contentArray = (JSONArray) object.get("content");
        for (Object o : contentArray) {
            JSONArray obj = (JSONArray) o;

            ArrayList<String> lines = new ArrayList<>();
            for (Object o1 : obj) lines.add((String) o1);
            content.add(lines);
        }
    }

    public String format() {
        StringBuilder builder = new StringBuilder();

        for (ArrayList<String> arrayList : content) {
            String str = arrayList.toString().replace(',', '，');
            builder.append(str, 1, str.length() - 1);
            builder.append("。\n");
        }

        return String.format("%s\n[%s]%s\n%s",
                this.title,
                this.period,
                this.author,
                builder.toString());
    }

    @Override
    public String toString() {
        return String.format("Poem{title='%s', author='%s', period='%s', content=%s}",
                this.title,
                this.author,
                this.period,
                this.content.toString());
    }
}

