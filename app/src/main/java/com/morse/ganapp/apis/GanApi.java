package com.morse.ganapp.apis;

import com.morse.ganapp.model.ResultEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 作者：Morse
 * 创建时间：2016/5/18 14:59
 * 功能：
 * 邮箱：zm902485jgsurjgc@163.com
 */
public interface GanApi {

    /**
     * 根据类型请求数据
     *
     * @param type 类型
     * @param num  每页请求个数
     * @param page 页数
     * @return List<ResultEntity>
     */
    @GET("{type}/{num}/{page}")
    Observable<List<ResultEntity>> getGan(@Query("type") String type, @Query("num") int num, @Query("page") int page);

    /**
     * 根据时间获取每天的数据
     *
     * @param year  年
     * @param mouth 月
     * @param daily 日
     * @return ResultEntity
     */
    @GET("day/{year}/{mouth}/{daily}")
    Observable<ResultEntity> getDailyGan(@Query("year") int year, @Query("mouth") int mouth, @Query("daily") int daily);
}
