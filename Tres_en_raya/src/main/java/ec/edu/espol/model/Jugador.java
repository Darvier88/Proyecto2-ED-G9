/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.model;

import java.util.ArrayList;
import java.util.Collections;

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
    public Jugador() {
        this.id = 0;
        this.nombre = null;
        this.tipoSimbolo = null;
    }
    public Jugador(String nombre) {
        this.id = 0;
        this.nombre = nombre;
        this.tipoSimbolo = null;
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
    public static ArrayList<Jugador> randomizarSimbolos(){
        ArrayList<Jugador> players= new ArrayList<>();
        ArrayList<String> pieceTypes = new ArrayList<>();
        pieceTypes.add("O");
        pieceTypes.add("X");
        Collections.shuffle(pieceTypes);
        for(int i=0; i<pieceTypes.size();i++){
            Jugador j = new Jugador();
            j.setId(1);
            j.setTipoSimbolo(pieceTypes.get(i));
            players.add(j);
        }
        return players;
    }
}
