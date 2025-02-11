package com.weildev.clientescrud.repository;

import com.weildev.clientescrud.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
