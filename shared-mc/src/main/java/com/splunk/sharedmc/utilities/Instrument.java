package com.splunk.sharedmc.utilities;

import java.util.ArrayList;
import java.util.List;

public class Instrument {

    private String item;
    private List enchantments;


    public Instrument(String item) {

        this.enchantments = new ArrayList();
        this.item = item;
    }


    public void addEnchantment(String item) {


        enchantments.add(item);
    }


}
