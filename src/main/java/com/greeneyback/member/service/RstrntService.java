package com.greeneyback.member.service;

import com.greeneyback.member.entity.RstrntEntity;
import com.greeneyback.member.helper.CSVHelper;
import com.greeneyback.member.repository.RstrntRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Service
public class RstrntService {
    @Autowired
    RstrntRepository rstrntRepository;

    public void save(MultipartFile file) {
        try {
            List<RstrntEntity> rstrntEntities = CSVHelper.csvToRstrntEntities(file.getInputStream());
            rstrntRepository.saveAll(rstrntEntities);
        } catch (IOException e) {
            throw new RuntimeException("faile to store csv data: "+e.getMessage());
        }
    }

    public List<RstrntEntity> getAllRstrntEntities() {
        return rstrntRepository.findAll();
    }
}
