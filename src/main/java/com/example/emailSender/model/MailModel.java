package com.example.emailSender.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MailModel {

    private String firstName;
    private String lastName;
    private String location;
    private int year;
    private String from;
    private String to;
    private String name;
    private String subject;
    private String content;
    private Map<String, String> model;
}