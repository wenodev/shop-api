package com.weno.shop.inquiry;

import com.weno.shop.inquiry.Inquiry;
import com.weno.shop.inquiry.InquiryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
@Service
public class InquiryService {

    private final InquiryRepository inquiryRepository;

    @Transactional
    public Inquiry createInquiries(Inquiry resource){
        return inquiryRepository.save(resource);
    }

    @Transactional
    public List<Inquiry> getAllInquiries(){
        return inquiryRepository.findAll();
    }

    @Transactional
    public Inquiry getInquiryById(Long id){
        return inquiryRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Inquiry updateInquiryById(Long id, Inquiry resource){

        Inquiry inquiry = inquiryRepository.findById(id).orElseThrow();
        inquiry.updateInquiries(resource);

        return inquiry;
    }

    @Transactional
    public void deleteInquiryById(Long id){
        inquiryRepository.deleteById(id);
    }
}
