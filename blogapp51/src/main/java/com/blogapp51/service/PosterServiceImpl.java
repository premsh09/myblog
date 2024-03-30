package com.blogapp51.service;

import com.blogapp51.entity.Poster;
import com.blogapp51.exception.ResourceNotFound;
import com.blogapp51.payload.ListPosterDto;
import com.blogapp51.payload.PosterDto;
import com.blogapp51.repository.PosterRepository;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PosterServiceImpl implements PosterService{

    private PosterRepository posterRepository;


    private ModelMapper modelMapper;

    public PosterServiceImpl(PosterRepository posterRepository, ModelMapper modelMapper) {
        this.posterRepository = posterRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public PosterDto createPoster(PosterDto posterDto) {
        Poster poster = mapToEntity(posterDto);

        Poster savedPoster = posterRepository.save(poster);

        PosterDto dto = mapToDto(savedPoster);

        return dto;
    }

    @Override
    public void delete(long id) {
        posterRepository.deleteById(id);
    }

    @Override
    public ListPosterDto fetchAll(int pageNo, int pageSize, String sortBy, String sortDir) {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortBy).ascending()
                :Sort.by(sortBy).descending();
        Pageable pageable = PageRequest.of(pageNo, pageSize, sort);
        Page<Poster> all = posterRepository.findAll(pageable);
        List<Poster> poster = all.getContent();
        List<PosterDto> posterDtos = poster.stream().map(p -> mapToDto(p)).collect(Collectors.toList());
        ListPosterDto listPosterDto = new ListPosterDto();
        listPosterDto.setPosterDto(posterDtos);
        listPosterDto.setTotalPage(all.getTotalPages());
        listPosterDto.setTotalElement((int) all.getTotalElements());
        listPosterDto.setFirstPage(all.isFirst());
        listPosterDto.setLastPage(all.isLast());
        listPosterDto.setPageNumber(all.getNumber());
        return listPosterDto;
    }

    @Override
    public PosterDto getPosterById(long id) {
        Poster poster = posterRepository.findById(id).orElseThrow(()->new ResourceNotFound("Poster not found with id :"+id));
        return mapToDto(poster);
    }

    Poster mapToEntity(PosterDto posterDto){
            Poster poster = modelMapper.map(posterDto, Poster.class);
            return poster;
        }

        PosterDto mapToDto(Poster poster){
            PosterDto dto = modelMapper.map(poster, PosterDto.class);
            return dto;
        }
}
