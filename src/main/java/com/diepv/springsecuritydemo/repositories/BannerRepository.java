package com.diepv.springsecuritydemo.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.diepv.springsecuritydemo.models.Banner;

public interface BannerRepository extends JpaRepository<Banner, Long> {

}
