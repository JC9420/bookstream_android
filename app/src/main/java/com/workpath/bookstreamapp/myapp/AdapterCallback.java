package com.workpath.bookstreamapp.myapp;

/**
 * Created by JayChen on 2020/2/11.
 */

public interface AdapterCallback<Datas> {
    /**
     * 更新数据
     * @param data
     * @param holder
     */
    void update(Datas data, MyAdapter.MyViewHolder<Datas> holder);
}
