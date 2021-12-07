package com.example.digitaleye.Model;

public class Item {

    String fName, lName;
    String price;

    public Item(){
        price = "Not found";
        fName = "Not";
        lName = "found";
    }

    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public String getlName() {
        return lName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
