package com.diepv.springsecuritydemo.mapper;

import java.util.List;

import org.mapstruct.Mapper;

import com.diepv.springsecuritydemo.dto.BannerDTO;
import com.diepv.springsecuritydemo.models.Banner;

@Mapper(componentModel = "spring")
public interface BannerMapper {

    List<BannerDTO> mapToBannersDTO(List<Banner> banners);

    List<Banner> mapToBanner(List<BannerDTO> bannerDTOs);
}
