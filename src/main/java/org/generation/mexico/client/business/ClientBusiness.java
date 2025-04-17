package org.generation.mexico.client.business;

import org.generation.mexico.client.model.Client;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ClientBusiness {

    Client insert(Client client);
    Client findById(Long id);
    List<Client> findAll();
    ResponseEntity<Client> update(Long id, Client clientUpdated);
    void deleteById(Long id);

}
