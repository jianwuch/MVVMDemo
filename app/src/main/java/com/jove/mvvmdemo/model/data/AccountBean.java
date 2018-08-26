package com.jove.mvvmdemo.model.data;

/**
 * author : jove.chen
 * e-mail : jianwuch@qq.com
 * date   : 18-8-23下午4:10
 * desc   :
 * version: 1.0
 */
public class AccountBean {
    public long id;
    public String name;
    public String gender;

    @Override
    public String toString() {
        return name + ":" + id + "/" + gender;
    }
}
