/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.yellowme.conekta.objects;

/**
 *
 * @author javier
 */
public class Cash{

    private String type;

    public Cash(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }        
    
    public String getNameType() {
        return "cash";
    }

    @Override
    public String toString() {
        return "Cash{" + "type=" + type + '}';
    }        
    
}
