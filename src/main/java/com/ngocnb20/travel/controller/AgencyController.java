package com.ngocnb20.travel.controller;

import com.ngocnb20.travel.constant.StatusRespData;
import com.ngocnb20.travel.model.dto.resp.AgencyRespDto;
import com.ngocnb20.travel.model.dto.resp.BaseRespDto;
import com.ngocnb20.travel.model.dto.resp.BlogRespDto;
import com.ngocnb20.travel.model.dto.resp.PageRespDto;
import com.ngocnb20.travel.model.entity.Agency;
import com.ngocnb20.travel.model.entity.Blog;
import com.ngocnb20.travel.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping(path = "/agencies",produces = "application/json;charset=UTF-8")
@CrossOrigin("*")
public class AgencyController {
    @Autowired
    AgencyService agencyService;

    @GetMapping()
    public ResponseEntity<BaseRespDto> getAllAgency(){
        List<Agency> agencies=agencyService.findAll();
        List<AgencyRespDto> agencyDtos  =   agencies.stream().map(a->agencyService.ConvertDto(a)).collect(Collectors.toList());

        return ResponseEntity.ok(
                BaseRespDto.success(StatusRespData.GET_DATA_SUCCESS,agencyDtos)
        );
    }

    @GetMapping("/page/{page}")
    public ResponseEntity<BaseRespDto> getByPage(@PathVariable("page") String page, @RequestParam String search){
        if(Objects.isNull(page)){
            page="0";
        }
        int numberPage=Integer.parseInt(page)-1;
        Page<Agency> agencies=agencyService.getDataByPage(numberPage,"",search);
        int totalPage=agencies.getTotalPages();
        System.out.println("total item"+agencies.getTotalElements());
        System.out.println("total page"+agencies.getTotalPages());
        List<AgencyRespDto> agencyDtos=agencies.getContent().stream()
                .map(a->agencyService.ConvertDto(a))
                .collect(Collectors.toList());
        PageRespDto<AgencyRespDto> pageData=new PageRespDto(totalPage,agencyDtos);

        return ResponseEntity.ok(
                BaseRespDto.success(StatusRespData.GET_DATA_SUCCESS,pageData)

        );
    }

    @GetMapping(path = "/detail/{id}")
    public ResponseEntity<BaseRespDto> getBlogById(@PathVariable Long id){
        Optional<Agency> agency =   agencyService.findAgencyById(id);

        if(agency.isPresent()){
            return ResponseEntity.ok(
                    BaseRespDto.success(StatusRespData.GET_DATA_SUCCESS,agency.get())
            );
        }else {
            return ResponseEntity.ok(
                    BaseRespDto.error(StatusRespData.GET_BY_ID_FAIL)
            );
        }
    }

}
