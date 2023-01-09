package com.ilisi2.demotdd;


import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class Ilisi2GlUmlDemoTddApplicationTests {

   @LocalServerPort
   private int port;
   @Autowired
   private TestRestTemplate restTemplate;
   @Test
   void contextLoads() {
   }

   @Test
   @DisplayName("Integration Test : test posting a valid client")
   public void clientShouldBeSavedSuccessfully() {
      Client client = new Client(null, "walid", "0634567895", "mail@mail.com");
      HttpEntity<Client> clientHttpEntity = new HttpEntity<>(client);

      ResponseEntity<Client> response = this.restTemplate
              .postForEntity("http://localhost:" + port + "/api/clients/",
                      clientHttpEntity,
                      Client.class);

      assertAll(
              () -> assertEquals(response.getStatusCode(), HttpStatusCode.valueOf(201)),
              () -> assertNotNull(response.getBody()),
              () -> assertNotNull(response.getBody().getUuid())
      );
   }
}
