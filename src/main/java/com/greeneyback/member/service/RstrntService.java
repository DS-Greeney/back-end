package com.greeneyback.member.service;

import com.greeneyback.member.dto.RstrntDTO;
import com.greeneyback.member.entity.RstrntEntity;
import com.greeneyback.member.repository.RstrntRepository;
import com.greeneyback.member.repository.impl.RstrntRepositoryImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RstrntService {
      /*
    @Autowired
    RstrntRepository rstrntRepository;


    public void save(MultipartFile file) {
        try {
            List<RstrntEntity> rstrntEntities = CSVHelper.csvToRstrntEntities(file.getInputStream());
            rstrntRepository.saveAll(rstrntEntities);
        } catch (IOException e) {
            throw new RuntimeException("fail to store csv data: "+e.getMessage());
        }
    }

    public List<RstrntEntity> getAllRstrntEntities() {
        return rstrntRepository.findAll();
    }

     */

    private final RstrntRepository rstrntRepository;

    @Autowired
    private final RstrntRepositoryImpl rstrntRepositoryImpl;

    public List<RstrntEntity> findAllRstrntEntities() {
        return rstrntRepository.findAll();
    }

    public Optional<RstrntEntity> findRstrntDetail(String rstrntId) {
        Optional<RstrntEntity> restaurant = rstrntRepository.findById(rstrntId);
        return restaurant;
    }

    public void restaurantSave(RstrntDTO rstrntDTO) {
        RstrntEntity rstrntEntity = RstrntEntity.toRstrntEntity(rstrntDTO);
        rstrntRepository.save(rstrntEntity);
    }

    public List<RstrntEntity> findByMyLocation(HashMap<String, Double> myLocation) {
        return rstrntRepositoryImpl.findByLocation(myLocation);
    }

}
