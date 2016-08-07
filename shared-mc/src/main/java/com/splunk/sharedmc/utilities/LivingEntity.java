package com.splunk.sharedmc.utilities;

import java.util.ArrayList;
import java.util.List;

public class LivingEntity {

    private String type;
    private String name;
    private Point3d location;
    private List potions;
    private double currentHealth;
    private double maxHealth;

    public LivingEntity(String type, String name, Point3d location, List potions, double currentHealth, double maxHealth) {
        this.type = type;
        this.name = name;
        this.location = location;
        this.potions = potions;
        this.currentHealth = currentHealth;
        this.maxHealth = maxHealth;
    }

    public LivingEntity(String type, String name) {
        this.type = type;
        this.name = name.replaceAll("§\\S", ""); // Remove the formatting codes;
        this.potions = new ArrayList();


    }

    public LivingEntity(String type, String name, Point3d location) {
        this.type = type;
        this.name = name.replaceAll("§\\S", ""); // Remove the formatting codes;
        this.location = location;
        this.potions = new ArrayList();
    }

    public LivingEntity() {
    }

    public Point3d getLocation() {
        return location;
    }

    public void setLocation(Point3d location) {
        this.location = location;
    }

    public List getPotions() {
        return potions;
    }

    public void setPotions(List potions) {
        this.potions = potions;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = Math.round(currentHealth * 100.00) / 100.00;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = Math.round(maxHealth * 100.00) / 100.00;
    }

    public void addPotions(String item) {
        potions.add(item);
    }
}
