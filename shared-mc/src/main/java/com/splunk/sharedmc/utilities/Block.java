package com.splunk.sharedmc.utilities;


public class Block {

    private String base_type;
    private String item;
    private Point3d location;

    public Block(String base_type, String item, Point3d location) {
        this.base_type = base_type;
        this.item = item;
        this.location = location;
    }

    public String getBase_type() {
        return base_type;
    }

    public void setBase_type(String base_type) {
        this.base_type = base_type;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public Point3d getLocation() {
        return location;
    }

    public void setLocation(Point3d location) {
        this.location = location;
    }


}
