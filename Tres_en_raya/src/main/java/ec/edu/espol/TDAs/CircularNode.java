/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.TDAs;

/**
 *
 * @author Jonathan
 */
public class CircularNode<E> {
    private E data;
    private CircularNode<E> next;
    private CircularNode<E> previous;

    public CircularNode(E data, CircularNode<E> next, CircularNode<E> previous) {
        this.data = data;
        this.next = next;
        this.previous = previous;
    }
    
    public CircularNode(E content) {
        this(content, null, null);
    }

    public E getData() {
        return data;
    }

    public void setData(E data) {
        this.data = data;
    }

    public CircularNode<E> getNext() {
        return next;
    }

    public void setNext(CircularNode<E> next) {
        this.next = next;
    }

    public CircularNode<E> getPrevious() {
        return previous;
    }

    public void setPrevious(CircularNode<E> previous) {
        this.previous = previous;
    }

   
    
}
