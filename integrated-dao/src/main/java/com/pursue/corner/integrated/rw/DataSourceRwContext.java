package com.pursue.corner.integrated.rw;

/**
 * DAO读写上下文变量
 */
public class DataSourceRwContext {

    private static final ThreadLocal<String> holder = new ThreadLocal<String>();

    public static void put(String dataSource) {
        if(dataSource!=null){
            holder.set(dataSource);
        }
    }

    public static String get() {
        return holder.get();
    }

    public static void remove() {
        holder.remove();
    }
}
