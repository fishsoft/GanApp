package com.morse.ganapp.model;

import com.google.gson.annotations.SerializedName;

/**
 * 作者：Morse
 * 创建时间：2016/5/18 17:29
 * 功能：
 * 邮箱：zm902485jgsurjgc@163.com
 */
public class GanBean<T> {

    /**
     * error : false
     * results : [{"_id":"573bdf8d6776591c9fd0cd5e","createdAt":"2016-05-18T11:20:45.857Z","desc":"Android Tips 8","publishedAt":"2016-05-18T12:18:37.235Z","source":"chrome","type":"Android","url":"http://www.wangchenlong.org/2016/05/18/tips/1605/181-android-tips-8/","used":true,"who":"蒋朋"},{"_id":"573b41f36776591ca2f31b7c","createdAt":"2016-05-18T00:08:19.804Z","desc":"Android Dynamic Action，简称DA，是一种简便、可变Action的实现方案。你可以像访问网页一样地访问Activity","publishedAt":"2016-05-18T12:18:37.235Z","source":"chrome","type":"Android","url":"https://github.com/benniaobuguai/android-dynamic-action","used":true,"who":"Jason"},{"_id":"573b36176776591ca2f31b7a","createdAt":"2016-05-17T23:17:43.345Z","desc":"完全自定义的Android安全键盘","publishedAt":"2016-05-18T12:18:37.235Z","source":"chrome","type":"Android","url":"https://github.com/ziyeyouhu/CustomizeKeyboard","used":true,"who":"Jason"},{"_id":"573aa7b66776591ca2f31b6c","createdAt":"2016-05-17T13:10:14.9Z","desc":"导航选项卡","publishedAt":"2016-05-18T12:18:37.235Z","source":"chrome","type":"Android","url":"https://github.com/DevLight-Mobile-Agency/NavigationTabStrip","used":true,"who":"大熊"},{"_id":"5739f5d46776591ca5328241","createdAt":"2016-05-17T00:31:16.532Z","desc":"送给小白的设计说明书（二）","publishedAt":"2016-05-17T12:17:17.785Z","source":"web","type":"Android","url":"http://blog.csdn.net/dd864140130/article/details/51429926","used":true,"who":"Dong dong Liu"},{"_id":"5739d0ae6776591ca681f899","createdAt":"2016-05-16T21:52:46.215Z","desc":"《从零开始开发一款Android App》系列文章的第五篇：设计模式篇","publishedAt":"2016-05-17T12:17:17.785Z","source":"web","type":"Android","url":"http://www.jianshu.com/p/fa92ca51bdb0","used":true,"who":null},{"_id":"57398ecc6776591ca681f893","createdAt":"2016-05-16T17:11:40.624Z","desc":"Android 插件化原理解析\u2014Service的插件化","publishedAt":"2016-05-17T12:17:17.785Z","source":"chrome","type":"Android","url":"http://weishu.me/2016/05/11/understand-plugin-framework-service/","used":true,"who":"鲍永章"},{"_id":"5739829f6776591c9fd0cd3b","createdAt":"2016-05-16T16:19:43.245Z","desc":"仿汽车报价大全的sidebar","publishedAt":"2016-05-17T12:17:17.785Z","source":"web","type":"Android","url":"https://github.com/AlexLiuSheng/AnimSideBar","used":true,"who":"AlexLiu"},{"_id":"573979486776591ca532823e","createdAt":"2016-05-16T15:39:52.977Z","desc":"一个很赞的RecyclerView Adapter辅助类","publishedAt":"2016-05-17T12:17:17.785Z","source":"web","type":"Android","url":"http://droidyue.com/blog/2016/05/09/baserecyclerviewadapterhelper/","used":true,"who":"陈宇明"},{"_id":"57392df66776591ca2f31b54","createdAt":"2016-05-16T10:18:30.37Z","desc":"一个垂直方向的DrawerLayout,抽屉从上向下展开","publishedAt":"2016-05-16T11:58:08.802Z","source":"chrome","type":"Android","url":"https://github.com/corerzhang/VerticalDrawerLayout","used":true,"who":"Jason"}]
     */
    @SerializedName("error")
    private boolean error;

    /**
     * _id : 573bdf8d6776591c9fd0cd5e
     * createdAt : 2016-05-18T11:20:45.857Z
     * desc : Android Tips 8
     * publishedAt : 2016-05-18T12:18:37.235Z
     * source : chrome
     * type : Android
     * url : http://www.wangchenlong.org/2016/05/18/tips/1605/181-android-tips-8/
     * used : true
     * who : 蒋朋
     */
    @SerializedName("results")
    private T results;

    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public T getResults() {
        return results;
    }

    public void setResults(T results) {
        this.results = results;
    }

}
