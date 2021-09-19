package com.socialising.service.impl;

import com.socialising.converter.BrandConverter;
import com.socialising.dto.BrandDTO;
import com.socialising.repository.BrandRepository;
import com.socialising.service.IBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandService implements IBrandService {

    @Autowired
    private BrandRepository brandRepository;

    @Autowired
    private BrandConverter brandConverter;

    @Override
    public List<BrandDTO> findAll() {
        return brandRepository.findAll().stream().map(item -> brandConverter.convertToDTO(item)).collect(Collectors.toList());
    }

    @Override
    public BrandDTO findById(Long id) {
        return brandConverter.convertToDTO(brandRepository.findById(id).get());
    }

    @Override
    @Transactional
    public BrandDTO save(BrandDTO newBrand) {
        return brandConverter.convertToDTO(brandRepository.save(brandConverter.convertToEntity(newBrand)));
    }

    @Override
    @Transactional
    public BrandDTO update(Long id, BrandDTO updateBrand) {
        return brandConverter.convertToDTO(brandRepository.save(brandConverter.convertToEntity(updateBrand)));
    }

    @Override
    @Transactional
    public void delete(long[] ids) {
        for (long id: ids) {
            brandRepository.deleteById(id);
        }
    }

}
