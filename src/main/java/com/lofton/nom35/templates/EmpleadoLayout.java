/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.lofton.nom35.templates;

/**
 *
 * @author cadri
 */
public class EmpleadoLayout {

    private String Nombre;

    private String Apellido_Paterno;

    private String Apellido_Materno;

    private String Edad;

    private String Sexo;

    private String Estado_Civil;

    private String Grado_Académico;

    private String Puesto;

    private String Área;

    private String Jornada;

    private Integer Años_laborando;

    private String Usuario;

    private String Contraseña;

    private Integer fila;

    private String tipoError;

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getApellido_Paterno() {
        return Apellido_Paterno;
    }

    public void setApellido_Paterno(String Apellido_Paterno) {
        this.Apellido_Paterno = Apellido_Paterno;
    }

    public String getApellido_Materno() {
        return Apellido_Materno;
    }

    public void setApellido_Materno(String Apellido_Materno) {
        this.Apellido_Materno = Apellido_Materno;
    }

    public String getSexo() {
        return Sexo;
    }

    public void setSexo(String Sexo) {
        this.Sexo = Sexo;
    }

    public String getEstado_Civil() {
        return Estado_Civil;
    }

    public void setEstado_Civil(String Estado_Civil) {
        this.Estado_Civil = Estado_Civil;
    }

    public String getGrado_Académico() {
        return Grado_Académico;
    }

    public void setGrado_Académico(String Grado_Académico) {
        this.Grado_Académico = Grado_Académico;
    }

    public String getPuesto() {
        return Puesto;
    }

    public void setPuesto(String Puesto) {
        this.Puesto = Puesto;
    }

    public String getÁrea() {
        return Área;
    }

    public void setÁrea(String Área) {
        this.Área = Área;
    }

    public String getJornada() {
        return Jornada;
    }

    public void setJornada(String Jornada) {
        this.Jornada = Jornada;
    }

    public Integer getAños_laborando() {
        return Años_laborando;
    }

    public void setAños_laborando(Integer Años_laborando) {
        this.Años_laborando = Años_laborando;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }

    public String getContraseña() {
        return Contraseña;
    }

    public void setContraseña(String Contraseña) {
        this.Contraseña = Contraseña;
    }

    public EmpleadoLayout(String Nombre, String Apellido_Paterno, String Apellido_Materno, String Edad, String Sexo, String Estado_Civil, String Grado_Académico, String Puesto, String Área, String Jornada, Integer Años_laborando, String Usuario, String Contraseña, Integer fila) {
        this.Nombre = Nombre;
        this.Apellido_Paterno = Apellido_Paterno;
        this.Apellido_Materno = Apellido_Materno;
        this.Edad = Edad;
        this.Sexo = Sexo;
        this.Estado_Civil = Estado_Civil;
        this.Grado_Académico = Grado_Académico;
        this.Puesto = Puesto;
        this.Área = Área;
        this.Jornada = Jornada;
        this.Años_laborando = Años_laborando;
        this.Usuario = Usuario;
        this.Contraseña = Contraseña;
        this.fila = fila;
        this.tipoError = "OK";
    }

    public EmpleadoLayout() {
    }

    @Override
    public String toString() {
        return "EmpleadoLayout{" + "Nombre=" + Nombre + ", Apellido_Paterno=" + Apellido_Paterno + ", Apellido_Materno=" + Apellido_Materno + ", Edad=" + Edad + ", Sexo=" + Sexo + ", Estado_Civil=" + Estado_Civil + ", Grado_Acad\u00e9mico=" + Grado_Académico + ", Puesto=" + Puesto + ", \u00c1rea=" + Área + ", Jornada=" + Jornada + ", A\u00f1os_laborando=" + Años_laborando + ", Usuario=" + Usuario + ", Contrase\u00f1a=" + Contraseña + ", fila=" + fila + ", tipoError=" + tipoError + '}';
    }

    public String getEdad() {
        return Edad;
    }

    public void setEdad(String Edad) {
        this.Edad = Edad;
    }

    public Integer getFila() {
        return fila;
    }

    public void setFila(Integer fila) {
        this.fila = fila;
    }

    public String getTipoError() {
        return tipoError;
    }

    public void setTipoError(String tipoError) {
        this.tipoError = tipoError;
    }

}

