package com.blogapp51.service;

import com.blogapp51.payload.ListPosterDto;
import com.blogapp51.payload.PosterDto;

public interface PosterService {

public PosterDto createPoster(PosterDto posterDto);

    void delete(long id);

    ListPosterDto fetchAll(int pageNo, int pageSize, String sortBy, String sortDir);

    PosterDto getPosterById(long id);
}
