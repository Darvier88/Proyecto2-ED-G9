/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.TDAs;

/**
 *
 * @author davsu
 */

import java.util.LinkedList;
import java.util.List;

public class TreeNode<E> {
    private int[][] matriz;
    private LinkedList<Tree<E>> children;
    private int utilidad;

    public TreeNode(int[][] matriz) {
        this.matriz = matriz;
        this.children = new LinkedList<>();
    }

    public int[][] getContent() {
        return this.matriz;
    }

    public void setContent(int[][] matriz) {
        this.matriz = matriz;
    }

    public LinkedList<Tree<E>> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<Tree<E>> children) {
        this.children = children;
    }

    public Tree getTreeChildern(int i) {
        return children.get(i);
    }

    public void addChildrenNode(Tree children) {
        this.children.add(children);
    }

    public int getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(int utilidad) {
        this.utilidad = utilidad;
    }

    public void toString_(){
        System.out.println("Matriz: ");
        for (int i = 0; i < this.matriz.length; i++) {
            for (int j = 0; j < this.matriz.length; j++) {
                System.out.print(this.matriz[i][j] + " || ");
            }
            System.out.println("------------------");
        }
        System.out.println("Utilidad: " + this.utilidad);
    }

}