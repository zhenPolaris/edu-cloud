package com.zhen.servicebase.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor  //生成有参数构造方法
@NoArgsConstructor   //生成无参数构造
public class SystemException extends RuntimeException {
    private Integer code;//状态码
    private String message;//异常信息
}
