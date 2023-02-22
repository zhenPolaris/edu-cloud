package com.zhen.oss.controller;

import com.zhen.commonutils.R;
import com.zhen.oss.service.OssService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Author: zhen
 * @Create: 2023-02-08 16:01
 * @Description:
 */
@Api(description = "阿里云文件管理")
@CrossOrigin
@RestController
@RequestMapping("/eduoss/fileoss")
public class OssController {

    @Autowired
    private OssService ossService;


    @ApiOperation(value = "文件上传")
    @PostMapping("/upload")
    public R uploadOssFile(@RequestParam("file")MultipartFile file){
        String s = ossService.uploadAvatar(file);
        return R.ok().data("url", s).message("文件上传成功");
    }
}
