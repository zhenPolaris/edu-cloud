package com.zhen.cms.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import com.zhen.cms.entity.CrmBanner;
import com.zhen.cms.entity.vo.BannerQuery;

import java.util.List;

/**
 * @author zhen
 * @since 2023-02-14 14:08:32
 */
public interface CrmBannerService extends IService<CrmBanner> {

    List<CrmBanner> getAllBanner();

    void pageQuery(Page<CrmBanner> bannerPage, BannerQuery bannerQuery);
}

