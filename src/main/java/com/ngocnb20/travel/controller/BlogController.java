package com.ngocnb20.travel.controller;

import com.ngocnb20.travel.constant.StatusRespData;
import com.ngocnb20.travel.model.dto.resp.BaseRespDto;
import com.ngocnb20.travel.model.dto.resp.BlogRespDto;
import com.ngocnb20.travel.model.dto.resp.PageRespDto;
import com.ngocnb20.travel.model.entity.Blog;
import com.ngocnb20.travel.repository.BlogRepository;
import com.ngocnb20.travel.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/blogs",produces = "application/json;charset=UTF-8")
@CrossOrigin("*")
public class BlogController extends BaseController {
    @Autowired
    BlogService blogService;
    @Autowired
    BlogRepository blogRepository;


    @GetMapping()
    public ResponseEntity<BaseRespDto> getAllBlog(){
        List<Blog> blogs=blogService.findAll();
        List<BlogRespDto> blogRespDtos=blogs.stream().map(b->blogService.convertDto(b)).collect(Collectors.toList());

        return ResponseEntity.ok(
                BaseRespDto.success(StatusRespData.GET_DATA_SUCCESS,blogRespDtos)
        );
    }


    @GetMapping("/page/{page}")
    public ResponseEntity<BaseRespDto> getAllBlogByPage(@PathVariable("page") String page,@RequestParam String search){
        if(Objects.isNull(page)){
            page="0";
        }
        int numberPage=Integer.parseInt(page)-1;
        Page<Blog> blogs=blogService.getBlogs(numberPage,"",search);
        int totalPage=blogs.getTotalPages();
        System.out.println("total item"+blogs.getTotalElements());
        System.out.println("total page"+blogs.getTotalPages());
        List<BlogRespDto> blogRespDtos=blogs.getContent().stream()
                                            .map(b->blogService.convertDto(b))
                                            .collect(Collectors.toList());
        PageRespDto<BlogRespDto> pageData=new PageRespDto(totalPage,blogRespDtos);

        return ResponseEntity.ok(
                BaseRespDto.success(StatusRespData.GET_DATA_SUCCESS,pageData)

        );
    }


    @GetMapping(path = "/detail/{id}")
    public ResponseEntity<BaseRespDto> getBlogById(@PathVariable Long id){
        Optional<Blog> blog=blogService.findBlogById(id);

        if(blog.isPresent()){
            return ResponseEntity.ok(
                    BaseRespDto.success(StatusRespData.GET_DATA_SUCCESS,blog.get())
            );
        }else {
            return ResponseEntity.ok(
                    BaseRespDto.error(StatusRespData.GET_BY_ID_FAIL)
            );
        }
    }


    @GetMapping(path = "/top/{id}")
    public ResponseEntity<BaseRespDto> getTopBlogNew(@PathVariable Long id){
        List<Blog> blogs= blogRepository.getBlogTop(id);
        List<BlogRespDto> blogRespDtos=blogs.stream().map(b ->blogService.convertDto(b)).collect(Collectors.toList());
        return ResponseEntity.ok(
                    BaseRespDto.success(StatusRespData.GET_DATA_SUCCESS,blogRespDtos)
            );
        }
    }



