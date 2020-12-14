package com.weno.shop.controller;


import com.weno.shop.entity.Inquiry;
import com.weno.shop.service.InquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1")
@RestController
public class InquiryController {

    private final InquiryService inquiryService;

    @GetMapping("/inquiries")
    public ResponseEntity getAllBoards(){
        List<Inquiry> boardList = inquiryService.getAllInquiries();
        return new ResponseEntity(boardList, HttpStatus.OK);
    }

    @PostMapping("/inquiries")
    public ResponseEntity createInquiries(@RequestBody Inquiry resource){
        Inquiry inquiry = inquiryService.createInquiries(resource);
        return new ResponseEntity(inquiry, HttpStatus.CREATED);
    }

    @PutMapping("/inquiries/{id}")
    public ResponseEntity updateInquiryById(@PathVariable Long id, @RequestBody Inquiry resource){
        Inquiry inquiry = inquiryService.updateInquiryById(id, resource);
        return new ResponseEntity(inquiry, HttpStatus.OK);
    }

    @DeleteMapping("/inquiries/{id}")
    public ResponseEntity deleteInquiryById(@PathVariable Long id){
        inquiryService.deleteInquiryById(id);
        return new ResponseEntity("deleted", HttpStatus.OK);
    }
}
