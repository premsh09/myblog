package com.blogapp51.payload;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class PosterDto {

    private long id;
    @NotEmpty
    @Size(min = 3, message = "Title should be minimum 3 characters")
    private String title;
    private String description;
    private String content;
//    @NotEmpty
//    @Size(min = 10, max = 10, message = "Mobile Number should be 10 digits")
//    private String mobile;
}
