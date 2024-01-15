/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.model;

/**
 *
 * @author HP
 */
public class Jugada {
    private int row;
    private int col;
    private String simbolo;
    private int id;
    private int utilidad;

    public Jugada(int row, int col, String simbolo, int id) {
        this.row = row;
        this.col = col;
        this.simbolo = simbolo;
        this.id = id;
        this.utilidad=0;
    }
    public Jugada(int row, int col, int id) {
        this.row = row;
        this.col = col;
        this.simbolo =null;
        this.id = id;
        this.utilidad=0;
    }

    public int getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(int utilidad) {
        this.utilidad = utilidad;
    }
    

    public Jugada(int row, int col) {
        this.row = row;
        this.col = col;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getCol() {
        return col;
    }

    public void setCol(int col) {
        this.col = col;
    }

    public String getSimbolo() {
        return simbolo;
    }

    public void setSimbolo(String simbolo) {
        this.simbolo = simbolo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
