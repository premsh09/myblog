package com.blogapp51.controller;

import com.blogapp51.payload.ListPosterDto;
import com.blogapp51.payload.PosterDto;
import com.blogapp51.service.PosterService;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/poster")
public class PosterController {

    private PosterService posterService;

    public PosterController(PosterService posterService) {
        this.posterService = posterService;
    }

    // http://localhost:8080/api/poster
    @PostMapping
    public ResponseEntity<?> createPoster(@Valid @RequestBody PosterDto posterDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return new ResponseEntity<>(bindingResult.getFieldError().getDefaultMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        PosterDto dto = posterService.createPoster(posterDto);
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    // http://localhost:8080/api/poster/1
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePoster(@PathVariable long id){
        posterService.delete(id);
        return new ResponseEntity<>("Post is deleted!", HttpStatus.OK);
    }

    //http://localhost:8080/api/poster?pageNo=0&pageSize=5&sortBy=title&sortDir=desc
    @GetMapping
    public ResponseEntity<ListPosterDto> fetchAll(
         @RequestParam(name = "pageNo",defaultValue = "0",required = false) int pageNo,
         @RequestParam(name = "pageSize", defaultValue = "5", required = false) int pageSize,
         @RequestParam(name = "sortBy", defaultValue = "id", required = false) String sortBy,
         @RequestParam(name = "sortDir", defaultValue = "asc", required = false) String sortDir
    ){
        ListPosterDto listPosterDto = posterService.fetchAll(pageNo, pageSize, sortBy, sortDir);
        return new ResponseEntity<>(listPosterDto, HttpStatus.OK);
    }

    // http://localhost:8080/api/poster/2
    @GetMapping("/{id}")
    public ResponseEntity<PosterDto> getPosterById(@PathVariable long id){
        PosterDto dto = posterService.getPosterById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }
}
