package com.morse.ganapp.ui.utils;

import android.text.TextUtils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * 作者：Morse
 * 创建时间：2016/6/15 11:49
 * 功能：
 * QQ:2450048085
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class ParseJsoup {

    public static ArrayList<String> parseType(String html) {
        if (TextUtils.isEmpty(html)) {
            return null;
        }
        Document document = Jsoup.parse(html);
        LogUtils.d(document.text() + "");
        Elements elements = document.getElementsByTag("h3");
        ArrayList<String> types = new ArrayList<>();
        for (Element element : elements) {
            types.add(element.text());
        }
        return types.size() <= 0 ? null : types;
    }

    public static ArrayList<ArrayList<HashMap<String, String>>> parseProgram(String html) {
        if (TextUtils.isEmpty(html)) {
            return null;
        }
        Document document = Jsoup.parse(html);
        ArrayList<ArrayList<HashMap<String, String>>> lists = new ArrayList<>();
        Elements elements1 = document.getElementsByTag("ul");
        for (int i = 0; i < elements1.size(); i++) {
            ArrayList<HashMap<String, String>> maps = new ArrayList<>();
            Elements links = elements1.get(i).select("a[href]");
            for (Element link : links) {
                HashMap<String, String> map = new HashMap<>();
                if(!TextUtils.isEmpty(link.text())){
                    map.put(link.text(), link.attr("abs:href"));
                    maps.add(map);
                }
            }
            lists.add(maps);
        }
        return lists;
    }

}
