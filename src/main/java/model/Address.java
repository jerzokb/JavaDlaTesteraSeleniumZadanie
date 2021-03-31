package model;

import enums.Countries;
import enums.States;
import lombok.Data;

@Data
public class Address {

    private Person person;
    private String address;
    private String city;
    private States state;
    private String zipCode;
    private Countries country;
    private String mobilePhone;
    private String alias;
}
