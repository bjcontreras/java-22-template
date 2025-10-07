package com.javbre.service;

import com.javbre.dto.UserCreateRequest;
import com.javbre.persistence.entity.UserEntity;
import com.javbre.persistence.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppService {

    private final UserRepository userRepository;

    public AppService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional
    public String create(UserCreateRequest req) {
        UserEntity entity = new UserEntity();
        entity.setName(req.getName());
        entity.setLastName(req.getLastName());
        entity.setTelephoneNumber(req.getTelephoneNumber());
        userRepository.save(entity);
        return "Registro creado con éxito";
    }

}

