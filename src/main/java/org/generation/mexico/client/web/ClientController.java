package org.generation.mexico.client.web;

import org.generation.mexico.client.business.ClientBusiness;
import org.generation.mexico.client.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    @Autowired
    private ClientBusiness clientBusiness;


    @PostMapping
    public Client insert(@RequestBody final Client client) {
        return clientBusiness.insert(client);
    }

    @GetMapping("/{id}")
    public Client findById(@PathVariable Long id) {
        return clientBusiness.findById(id);
    }

    @GetMapping
    public List<Client> findAll() {
        return clientBusiness.findAll();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody final Client client) {
        return clientBusiness.update(id, client);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Long id) {
        clientBusiness.deleteById(id);
    }

}
