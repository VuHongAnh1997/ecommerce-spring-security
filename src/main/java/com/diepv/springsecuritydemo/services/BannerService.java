package com.diepv.springsecuritydemo.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.diepv.springsecuritydemo.dto.BannerDTO;
import com.diepv.springsecuritydemo.mapper.BannerMapper;
import com.diepv.springsecuritydemo.models.Banner;
import com.diepv.springsecuritydemo.repositories.BannerRepository;

@Service
public class BannerService {

    @Autowired
    private BannerRepository bannerRepository;

    @Autowired
    private BannerMapper bannerMapper;

    public List<BannerDTO> getAllBanner() {
        List<Banner> banners = bannerRepository.findAll();
        return bannerMapper.mapToBannersDTO(banners);
    }

}
