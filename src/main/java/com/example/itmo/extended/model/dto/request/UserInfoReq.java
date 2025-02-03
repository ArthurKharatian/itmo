package com.example.itmo.extended.model.dto.request;

import com.example.itmo.extended.model.enums.Gender;
import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserInfoReq {
   private String email;
   private String password;
   private String firstName;
   private String lastName;
   private String middleName;
   private Integer age;
   private Gender gender;
}
