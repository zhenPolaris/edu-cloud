package com.zhen.oss.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: zhen
 * @Create: 2023-02-08 15:55
 * @Description:
 */
public interface OssService {

    String uploadAvatar(MultipartFile file);
}
