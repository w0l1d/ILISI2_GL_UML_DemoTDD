package com.ilisi2.demotdd;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import java.util.UUID;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class Client {
   @Id
   private UUID uuid;
   @Length(min = 4)
   private String nom;
   @Pattern(message = "phone must begin with +212 or 0", regexp = "((\"+212\")|(0))[0-9]{9}")
   private String phone;
   @Email
   private String email;

}
