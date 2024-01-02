package com.ezen03.tieshoe.model;

import lombok.Data;

import java.util.ArrayList;

@Data
public class naverLoginBeans {
    private String name;
    private String nickName;
    private String email;
    private ArrayList<String> response;
}
