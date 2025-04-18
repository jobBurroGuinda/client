package org.generation.mexico.client.web;

//import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.generation.mexico.client.business.ClientBusiness;
import org.generation.mexico.client.model.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

@RestController
@RequestMapping("/api/clients")
@Tag(name = "Client Controller", description = "Client management API of Generation Mexico")
public class ClientController {

    @Autowired
    private ClientBusiness clientBusiness;

    public ClientController() {
    }

    public ClientController(ClientBusiness clientBusiness) {
        this.clientBusiness = clientBusiness;
    }




    @GetMapping
    @Operation(summary = "Show all clients", description = "Show all clients saved on database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "List all clients",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(
                                    type = "array"
                            )
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(
                                    type = "string",
                                    example =
                                            "Server error"
                            )
                    )
            )
    })
    public List<Client> findAll() {
        return clientBusiness.findAll();
    }


    @GetMapping("/{id}")
    @Operation(summary = "Find a client by id", description = "Find a client by their id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client found",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(
                                    type = "object"
                            )
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(
                                    type = "string",
                                    example =
                                            "Server error"
                            )
                    )
            )
    })
    public Client findById(@PathVariable Long id) {
        return clientBusiness.findById(id);
    }


    @PostMapping
    @Operation(summary = "Save a client", description = "Save a client on database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Client saved",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(
                                    type = "object"
                            )
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(
                                    type = "string",
                                    example =
                                            "Server error"
                            )
                    )
            )
    })
    public Client insert(@RequestBody final Client client) {
        return clientBusiness.insert(client);
    }


    @PutMapping("/{id}")
    @Operation(summary = "Update a client", description = "Update the client data from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client updated",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(
                                    type = "object"
                            )
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(
                                    type = "string",
                                    example =
                                            "Server error"
                            )
                    )
            )
    })
    public ResponseEntity<Client> update(@PathVariable Long id, @RequestBody final Client client) {
        return clientBusiness.update(id, client);
    }


    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a client", description = "Delete a client from database")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Client deleted",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(
                                    type = "void"
                            )
                    )
            ),
            @ApiResponse(responseCode = "500", description = "Server error",
                    content = @Content(
                            mediaType = "text/plain",
                            schema = @Schema(
                                    type = "string",
                                    example =
                                            "Server error"
                            )
                    )
            )
    })
    public void deleteById(@PathVariable Long id) {
        clientBusiness.deleteById(id);
    }


}
