package com.tianx;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jetbrains.annotations.NotNull;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Main {
    public static ArrayList<Poem> poems = new ArrayList<>();

    public static void initPoems() {
        try {
            BufferedReader reader = new BufferedReader(new FileReader(new File("poem.json")));
            StringBuilder builder = new StringBuilder();
            String input;
            while ((input = reader.readLine()) != null) builder.append(input);
            JSONObject object = JSON.parseObject(builder.toString());

            for (Map.Entry<String, Object> entry : object.entrySet())
                poems.add(new Poem(entry.getKey(), (JSONObject) entry.getValue()));

            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void listPoems() {
        for (Poem poem : poems) {
            System.out.println(poem.format());
        }
    }

    public static @NotNull String repeatString(String str, int repeat) {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < repeat; ++i) builder.append(str);
        return builder.toString();
    }

    public static void generateQuestion() {
        Random random = new Random(System.currentTimeMillis());
        Poem Qpoem = poems.get(random.nextInt(poems.size()));
        for (ArrayList<String> arrayList : Qpoem.content) {
            int k = random.nextInt(arrayList.size());
            arrayList.set(k, repeatString("_ ", arrayList.get(k).length()));
        }
        System.out.println(Qpoem.format());
    }

    public static void main(String[] args) {
        initPoems();
//        listPoems();
        HashSet<String> set1 = new HashSet<>();
        HashSet<String> set2 = new HashSet<>();

        for (Poem poem : poems) {
            set1.add(poem.period);
            set2.add(poem.author);
        }
        System.out.println("set1 = " + set1);
        System.out.println("set2 = " + set2);

//        while (true) {
//            generateQuestion();
//            try {
//                Thread.sleep(3500);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }
    }
}
