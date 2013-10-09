/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.yellowme.conekta.objects;

import java.math.BigInteger;

/**
 *
 * @author javier
 */
public class LineItems {
    
    private String name;
    private String description;
    private String sku;
    private BigInteger unit_price;
    private BigInteger price;
    private BigInteger quantity;
    private String type;

    public LineItems(String name, String description, String sku, BigInteger unitPrice, BigInteger price, BigInteger quantity, String type) {
        this.name = name;
        this.description = description;
        this.sku = sku;
        this.unit_price = unitPrice;
        this.price = price;
        this.quantity = quantity;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public BigInteger getUnitPrice() {
        return unit_price;
    }

    public void setUnitPrice(BigInteger unitPrice) {
        this.unit_price = unitPrice;
    }

    public BigInteger getPrice() {
        return price;
    }

    public void setPrice(BigInteger price) {
        this.price = price;
    }

    public BigInteger getQuantity() {
        return quantity;
    }

    public void setQuantity(BigInteger quantity) {
        this.quantity = quantity;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "LineItems{" + "name=" + name + ", description=" + description + ", sku=" + sku + ", unit_price=" + unit_price + ", price=" + price + ", quantity=" + quantity + ", type=" + type + '}';
    }

    
    
}
