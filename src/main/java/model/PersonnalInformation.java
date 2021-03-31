package model;

import lombok.Data;

@Data
public class PersonnalInformation {

    private Person person;
    private String email;
    private String password;
}
