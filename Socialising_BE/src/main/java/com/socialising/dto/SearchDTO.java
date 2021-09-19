package com.socialising.dto;

import java.util.List;

public class SearchDTO<T> {
    private List<T> data;
    private int pageIndex;
    private int pageSize;
    private int total;
}
