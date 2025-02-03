package com.example.itmo.extended.model.dto.response;

import com.example.itmo.extended.model.dto.request.UserInfoReq;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class UserInfoResp extends UserInfoReq {
    private Long id;
}
