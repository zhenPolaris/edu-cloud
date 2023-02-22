package com.zhen.msm.service;

import java.util.HashMap;

/**
 * @Author: zhen
 * @Create: 2023-02-15 14:06
 * @Description:
 */
public interface MsmService {

    //发送短信的方法
    boolean sendMsm(HashMap<String, Object> map, String phone);
}
