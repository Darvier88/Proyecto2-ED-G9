/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.model;

/**
 *
 * @author ASUS VIVOBOOK PRO
 */
public class ResultadoDado {
    private String lado;
    private int numero;

    public ResultadoDado(String lado, int numero) {
        this.lado = lado;
        this.numero = numero;
    }

    public String getLado() {
        return lado;
    }

    public int getNumero() {
        return numero;
    }
    
}
