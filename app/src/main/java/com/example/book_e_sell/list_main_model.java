package com.example.book_e_sell;

public class list_main_model {
    String category,name,price,pic;

    list_main_model()
    {

    }
    public list_main_model(String category, String name,String price, String pic)
    {
        this.category = category;
        this.name=name;
        this.price=price;
        this.pic=pic;
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
    public String getCategory()
    {
        return category;
    }
    public void setCategory(String category)
    {
        this.category=category;
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
