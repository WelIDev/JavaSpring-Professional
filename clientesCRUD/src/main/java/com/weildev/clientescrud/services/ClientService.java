package com.weildev.clientescrud.services;

import com.weildev.clientescrud.dto.ClientDTO;
import com.weildev.clientescrud.entities.Client;
import com.weildev.clientescrud.repository.ClientRepository;
import com.weildev.clientescrud.services.exceptions.DatabaseException;
import com.weildev.clientescrud.services.exceptions.ResourceNotFoundException;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.LazyInitializationException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Transactional(readOnly = true)
    public Page<ClientDTO> findAll(Pageable pageable) {
        Page<Client> clients = clientRepository.findAll(pageable);
        return clients.map(ClientDTO::new);
    }

    @Transactional(readOnly = true)
    public ClientDTO findById(Long id) {
        Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
        return new ClientDTO(client);
    }

    @Transactional
    public ClientDTO insert(ClientDTO clientDTO) {
        Client client = new Client();
        BeanUtils.copyProperties(clientDTO, client);
        client = clientRepository.save(client);
        return new ClientDTO(client);
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public ClientDTO update(Long id, ClientDTO clientDTO) {
        try {
            Client client = clientRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Resource not found"));
            BeanUtils.copyProperties(clientDTO, client, "id");
            client = clientRepository.save(client);
            return new ClientDTO(client);
        }catch (EntityNotFoundException e ){
            throw new ResourceNotFoundException("Resource not found");
        }
    }

    @Transactional(propagation = Propagation.SUPPORTS)
    public void delete(Long id) {
        if (!clientRepository.existsById(id)) {
            throw new ResourceNotFoundException("Resource not found");
        }
        try {
            clientRepository.deleteById(id);
        }catch (DataIntegrityViolationException e){
            throw new DatabaseException("Referential integrity failure");
        }
    }
}
