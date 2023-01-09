package com.ilisi2.demotdd;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

   @Autowired
   private ClientRepository clientRepo;

   @PostMapping({"/", ""})
   @ResponseStatus(HttpStatus.CREATED)
   public Optional<Client> ajouterClient(@Valid @NotNull @RequestBody Client client) throws IllegalArgumentException {

      client.setUuid(UUID.randomUUID());
      return Optional.of(clientRepo.save(client));
   }

}
