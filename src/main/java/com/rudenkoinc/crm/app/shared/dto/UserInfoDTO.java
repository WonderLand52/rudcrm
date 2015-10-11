package com.rudenkoinc.crm.app.shared.dto;

/**
 * @author Illia Rudenko
 */

public class UserInfoDTO {

    private String firstName;
    private String secondName;

    public UserInfoDTO(String firstname, String secondName) {
        this.firstName = firstname;
        this.secondName = secondName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String userName) {
        this.firstName = userName;
    }

    public String getSecondName() {
        return secondName;
    }
}
