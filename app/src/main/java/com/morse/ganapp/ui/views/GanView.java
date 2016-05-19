package com.morse.ganapp.ui.views;

import com.morse.ganapp.model.ResultEntity;

import java.util.List;

/**
 * 作者：Morse
 * 创建时间：2016/5/19 09:45
 * 功能：
 * 邮箱：zm902485jgsurjgc@163.com
 */
public interface GanView {
    public void loadMore(List<ResultEntity> entities);

    public void refresh(List<ResultEntity> entities);
}
