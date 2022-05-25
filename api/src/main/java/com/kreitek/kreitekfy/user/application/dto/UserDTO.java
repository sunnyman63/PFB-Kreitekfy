package com.kreitek.kreitekfy.user.application.dto;

import java.io.Serializable;

public class UserDTO implements Serializable {

    private Long idUser;
    private boolean admin;
    private String name;

    public UserDTO() {
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUsuario) {
        this.idUser = idUsuario;
    }

    public boolean isAdmin() {
        return admin;
    }

    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}