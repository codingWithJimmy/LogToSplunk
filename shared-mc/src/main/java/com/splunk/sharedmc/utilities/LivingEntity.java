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

    public double getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
    }

    public double getMaxHealth() {
        return maxHealth;
    }

    public void setMaxHealth(double maxHealth) {
        this.maxHealth = maxHealth;
    }

    public void addPotions(String item) {
        potions.add(item);
    }
}
