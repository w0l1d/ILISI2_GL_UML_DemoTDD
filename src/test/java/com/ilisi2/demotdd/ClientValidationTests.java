package com.ilisi2.demotdd;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.TransactionSystemException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class ClientValidationTests {

   @Autowired
   private ClientController clientController;

   @Test
   @DisplayName("Save a valid Client")
   public void saveNewClient() {
      Client client = new Client(null, "walid", "0612345678", "valid@mail.com");
      assertDoesNotThrow(
              () -> {
                 Client savedCli = clientController.ajouterClient(client).get();
                 assertNotNull(savedCli.getUuid());
                 assertNotNull(savedCli);
              }
      );
   }

   @Test
   @DisplayName("save a new client with invalid phone")
   public void saveNewClientWithInvalidPhone() {
      Client client = new Client(null, "walid", "12345678", "valid@mail.com");

      assertThrows(TransactionSystemException.class,
              () -> clientController.ajouterClient(client),
              "phone must begin with +212 or 0");
   }

   @Test
   @DisplayName("save a new client with invalid email")
   public void saveNewClientWithInvalidEmail() {
      Client client = new Client(null, "walid", "0612345678", "email1.com");

      assertThrows(TransactionSystemException.class,
              () -> clientController.ajouterClient(client),
              "must be a well-formed email address");
   }

}
