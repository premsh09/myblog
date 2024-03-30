package com.blogapp51.payload;

import lombok.Data;

import java.util.List;
@Data
public class ListPosterDto {
    private List<PosterDto> posterDto;
    private int totalPage;
    private int totalElement;
    private int pageNumber;
    private boolean firstPage;
    private boolean lastPage;
}
