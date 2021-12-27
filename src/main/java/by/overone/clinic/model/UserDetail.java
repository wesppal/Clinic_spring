package by.overone.clinic.model;

import lombok.Data;

@Data
public class UserDetail {
    private long user_id;
    private String name;
    private String surname;
    private String address;
    private String phoneNumber;
}
