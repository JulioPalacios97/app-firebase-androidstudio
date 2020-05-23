package com.example.newapp;

public class RegisterStore {

    private String paciente, edad, problema, cama;

    public RegisterStore() {
    }

    public RegisterStore(String paciente, String edad, String problema, String cama) {
        this.paciente = paciente;
        this.edad = edad;
        this.problema = problema;
        this.cama = cama;
    }

    public String getCama() {
        return cama;
    }

    public void setCama(String cama) {
        this.cama = cama;
    }

    public String getPaciente() {
        return paciente;
    }

    public void setPaciente(String paciente) {
        this.paciente = paciente;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getProblema() {
        return problema;
    }

    public void setProblema(String problema) {
        this.problema = problema;
    }
}
