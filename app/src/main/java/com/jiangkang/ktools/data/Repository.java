package com.jiangkang.ktools.data;

/**
 * Created by jiangkang on 2017/11/8.
 * description：Repository标识类
 */

public interface Repository<T> {


    T create(T t);

    T update(T t);

    T insert(T t);

    T query(String query);

    boolean delete(T t);

}
