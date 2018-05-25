package com.Roger.UsersRegister;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Usuario implements IUsuario {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long id;

    private String name;
    private String email;
    private String empresa;
    private Date fechaRegistro;

    @ElementCollection
    @Column(name="AccessTimes")
   private List<Date> accessTimes = new ArrayList<>();


    public Usuario(){}

    public Usuario(String name, String email, String empresa) {
        this.name = name;
        this.email = email;
        this.empresa = empresa;
        fechaRegistro = new Date();
    }


    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getEmpresa() {
        return empresa;
    }

    public Date getFechaRegistro() {
        return fechaRegistro;
    }

    public long getId() {
        return id;
    }

    public List<Date> getAccessTimes() {
        return accessTimes;
    }

    public void addDate(Date date){
        accessTimes.add(date);
    }
}
