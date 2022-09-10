package com.harsha.demo.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContactRequest {
    private Integer id;
    private String name;
    private String number;
    private Integer userId;
}
