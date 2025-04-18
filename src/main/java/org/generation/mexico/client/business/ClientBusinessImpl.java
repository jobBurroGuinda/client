package org.generation.mexico.client.business;

import org.generation.mexico.client.dao.ClientRepository;
import org.generation.mexico.client.model.Client;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.Optional;

@Service
public class ClientBusinessImpl implements ClientBusiness {

    @Autowired
    private ClientRepository clientRepository;

    private static final Logger LOGGER = LoggerFactory.getLogger(ClientBusinessImpl.class);


    @Override
    public Client insert(Client client) {
        try {
            Client clientSaved = clientRepository.save(client);
            String message = String.format("The client %s %s has been upgraded", clientSaved.getName(), clientSaved.getLastName());
            LOGGER.info("The client has been saved");
            LOGGER.info(message);
            return clientSaved;
        } catch (Exception e) {
            LOGGER.error("The client have not been saved");
            LOGGER.error(e.getMessage());
            return null;
        }
    }

    @Override
    public Client findById(Long id) {
        return clientRepository.findById(id).orElse(null);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public ResponseEntity<Client> update(Long id, Client clientNewData) {
        Optional<Client> client = clientRepository.findById(id);
        if (client.isPresent()) {
            client.get().setName(clientNewData.getName());
            client.get().setLastName(clientNewData.getLastName());
            client.get().setEmail(clientNewData.getEmail());
            Client clientUpdated = clientRepository.save(client.get());
            return new ResponseEntity<>(clientUpdated, HttpStatus.OK);
        }
        return new ResponseEntity<>(new Client(), HttpStatus.NOT_FOUND);
    }

    @Override
    public void deleteById(Long id) {
        clientRepository.deleteById(id);
    }

}
