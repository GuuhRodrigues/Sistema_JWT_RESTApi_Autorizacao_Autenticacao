package com.example.jwt_restapi.repository;

import com.example.jwt_restapi.model.ClimaEntity;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClimaRepository extends MongoRepository<ClimaEntity, String> {
// Métodos de CRUD já estão disponíveis
//findAll, findById, save, deleteById
}