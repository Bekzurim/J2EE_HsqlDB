/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testingwithhsqldb;

public class Product {
    
    private int ID;
    private String name;
    private float price;
    
    public Product(int ID,String name,float price){
        this.ID=ID;
        this.name=name;
        this.price=price;
    }
    
    public int getId(){
		return ID;
	}
    
    public String getName(){
		return name;
	}

    public float getPrice(){
		return price;
	}
    
}
