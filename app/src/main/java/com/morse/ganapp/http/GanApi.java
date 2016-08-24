package com.morse.ganapp.http;

import com.morse.ganapp.model.GanBean;
import com.morse.ganapp.model.ResultEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;

/**
 * 作者：Morse
 * 创建时间：2016/5/18 14:59
 * 功能：
 * 邮箱：zm902485jgsurjgc@163.com
 */
public interface GanApi {

//    @GET()
//    void getArtcle();

    /**
     * 根据类型请求数据
     *
     * @param type 类型
     * @param num  每页请求个数
     * @param page 页数
     * @return List<ResultEntity>
     */
    @GET("data/{type}/{num}/{page}")
    Observable<GanBean<List<ResultEntity>>> getGan(@Path("type") String type, @Path("num") int num, @Path("page") int page);

    /**
     * 根据类型请求数据
     *
     * @param type 类型
     * @param num  每页请求个数
     * @return List<ResultEntity>
     */
    @GET("random/data/{type}/{num}")
    Observable<GanBean<List<ResultEntity>>> getGan(@Path("type") String type, @Path("num") int num);

    /**
     * 根据时间获取每天的数据
     *
     * @param year  年
     * @param mouth 月
     * @param daily 日
     * @return ResultEntity
     */
    @GET("day/{year}/{mouth}/{daily}")
    Observable<GanBean<List<ResultEntity>>> getDailyGan(@Path("year") int year, @Path("mouth") int mouth, @Path("daily") int daily);
}
