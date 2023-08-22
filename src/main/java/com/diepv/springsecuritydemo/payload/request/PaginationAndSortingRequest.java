package com.diepv.springsecuritydemo.payload.request;

import java.util.List;

import lombok.Data;

@Data
public class PaginationAndSortingRequest {
    private int page;

    private int size;

    private List<String> sortByList;

    private List<String> sortOrderList;

    private Long categoryId;
}
