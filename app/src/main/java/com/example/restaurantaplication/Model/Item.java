package com.example.restaurantaplication.Model;

import java.io.Serializable;
import java.util.ArrayList;

public class Item implements Serializable {
    private ArrayList<String> icons;
    private String title;
    private String subtitle;

    public Item(ArrayList<String> icons, String title, String subtitle) {
        this.icons = icons;
        this.title = title;
        this.subtitle = subtitle;

    }

    public ArrayList<String> getIcons() {
        return icons;
    }

    public String getIcon(int position){
        return icons.get(position);
    }

    public int getIconsSize(){
        return icons.size();
    }

    public void setIcons(ArrayList<String> icons) {
        this.icons = icons;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

}
