/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.model;

import java.util.ArrayList;

/**
 *
 * @author Jonathan
 */
public class Jugador {
    private int id;
    private String nombre;
    private String tipoSimbolo;

    public Jugador(int id, String nombre, String tipoSimbolo) {
        this.id = id;
        this.nombre = nombre;
        this.tipoSimbolo = tipoSimbolo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipoSimbolo() {
        return tipoSimbolo;
    }

    public void setTipoSimbolo(String tipoSimbolo) {
        this.tipoSimbolo = tipoSimbolo;
    }
    
    

    @Override
    public String toString() {
        return "Jugador{" + "id=" + id + ", nombre=" + nombre + ", tipoSimbolo=" + tipoSimbolo + '}';
    }
    
    
}
