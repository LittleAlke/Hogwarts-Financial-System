package com.alke.common.result;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

/**
 * :统一结果类
 * Alke
 * 2021-08-14 16:39
 */
@Data
public class R {

    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    /**
     * 构造函数私有化
     */
    private R(){

    }

    /**
     * 设置成功结果
     * @return
     */
    public static R ok(){
        R r = new R();
        r.setCode(ResponseEnum.SUCCESS.getCode());
        r.setMessage(ResponseEnum.SUCCESS.getMessage());
        return r;
    }

    /**
     * 设置错误结果
     * @return
     */
    public static R error(){
        R r = new R();
        r.setCode(ResponseEnum.ERROR.getCode());
        r.setMessage(ResponseEnum.ERROR.getMessage());
        return r;
    }

    /**
     * 设置特定的结果
     * @param responseEnum
     * @return
     */
    public static R setResult(ResponseEnum responseEnum){
        R r = new R();
        r.setCode(responseEnum.getCode());
        r.setMessage(responseEnum.getMessage());
        return r;
    }

    /**
     * 设置数据
     * @param key
     * @param value
     * @return
     */
    public R data(String key, Object value){
        this.data.put(key,value);
        return this;
    }

    /**
     * 数据直接为map的情况
     * @param map
     * @return
     */
    public R data(Map<String,Object> map){
        this.setData(map);
        return this;
    }

    /**
     * 设置特定的消息
     * @param message
     * @return
     */
    public R message(String message){
        this.setMessage(message);
        return this;
    }

    /**
     * 设置特定的响应码
     * @param code
     * @return
     */
    public R code(Integer code){
        this.setCode(code);
        return this;
    }
}
