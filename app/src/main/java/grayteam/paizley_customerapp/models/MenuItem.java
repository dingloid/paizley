package grayteam.paizley_customerapp.models;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class MenuItem {

    public String uid;
    public String itemName;
    public String price;
    public String desc;

    public MenuItem(){

    }

    public MenuItem(String uid, String itemName, String price, String desc){
        this.uid = uid;
        this.itemName = itemName;
        this.price = price;
        this.desc = desc;
    }
}
