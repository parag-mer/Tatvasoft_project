package com.example.book_e_sell;

public class na_main_model {
    String name,price,pic;
    na_main_model()
    {

    }
    public na_main_model(String name, String price, String pic) {
        this.name = name;
        this.price = price;
        this.pic = pic;
    }

    public String getpic() {
        return pic;
    }

    public void setpic(String pic) {
        this.pic = pic;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getprice() {
        return price;
    }

    public void setprice(String price) {
        this.price = price;
    }
}
