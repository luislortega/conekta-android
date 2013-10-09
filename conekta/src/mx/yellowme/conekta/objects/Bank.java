/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.yellowme.conekta.objects;

/**
 *
 * @author javier
 */
public class Bank{

    private String type;

    public Bank(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Bank{" + "type=" + type + '}';
    }

    
    
}
