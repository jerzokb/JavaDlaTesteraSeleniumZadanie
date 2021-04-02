package model;

import lombok.Data;

@Data
public class PersonalInformation {

    private Person person;
    private String email;
    private String password;
}
