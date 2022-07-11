package com.example.book_e_sell;
public class mp_main_model
{
    String name,price,pic;
    mp_main_model()
    {

    }
    public mp_main_model(String name, String price, String pic)
    {
        this.pic=pic;
        this.name=name;
        this.price=price;
    }
    public String getPic()
    {
        return pic;
    }
    public void setPic(String pic)
    {
        this.pic=pic;
    }
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name=name;
    }
    public String getPrice()
    {
        return price;
    }
    public void setPrice(String price)
    {
        this.price=price;
    }
}