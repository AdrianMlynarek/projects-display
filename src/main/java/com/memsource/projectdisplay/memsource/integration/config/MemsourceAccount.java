package com.memsource.projectdisplay.memsource.integration.config;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor
@Entity
@Getter
public class MemsourceAccount {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;
    private final String username;
    private final String password;
}
