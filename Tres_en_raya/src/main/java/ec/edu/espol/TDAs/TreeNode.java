/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.TDAs;

/**
 *
 * @author davsu
 */

import ec.edu.espol.model.Jugada;
import java.util.LinkedList;
import java.util.List;
import javafx.scene.image.ImageView;

public class TreeNode<E> {
    private Jugada[][] matriz;
    private LinkedList<Tree<Jugada[][]>> children;
    private int utilidad;

    public TreeNode(Jugada[][] matriz) {
        this.matriz = matriz;
        this.children = new LinkedList<>();
        this.utilidad=0;
    }

    public Jugada[][] getContent() {
        return this.matriz;
    }

    public void setContent(Jugada[][] matriz) {
        this.matriz = matriz;
    }

    public LinkedList<Tree<Jugada[][]>> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<Tree<Jugada[][]>> children) {
        this.children = children;
    }

    public Tree getTreeChildern(int i) {
        return children.get(i);
    }

    public void addChildrenNode(Tree<Jugada[][]> children) {
        this.children.add(children);
    }

    public int getUtilidad() {
        return utilidad;
    }

    public void setUtilidad(int utilidad) {
        this.utilidad = utilidad;
    }
}