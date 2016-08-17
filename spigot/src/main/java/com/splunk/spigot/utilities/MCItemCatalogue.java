package com.splunk.spigot.utilities;

import com.google.gson.Gson;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class MCItemCatalogue implements Iterable<MCItem> {

    private static MCItemCatalogue instance = null;
    private Set<MCItem> MCItems = new HashSet<>();

    public static MCItemCatalogue getInstance() {
        if (instance == null) {
            instance = new MCItemCatalogue();
        }

        return instance;
    }

    public Set<MCItem> getMCItems() {
        return MCItems;
    }

    public void setMCItems(Set<MCItem> MCItems) {
        this.MCItems = MCItems;
    }

    public void parseItemsFile(String contents) {

        Gson gson = new Gson();

        // Using an array to populate the items because we have to do some modifications to the values.
        MCItem mcItems[] = gson.fromJson(contents, MCItem[].class);

        for (MCItem item : mcItems) {

            // Replace spaces with underscores
            item.setName(item.getName().replace(" ", "_"));

            // Have to do some translation on the text_type to match the Spigot Material class.
            switch (item.getText_type()) {
                case "birch_stairs":
                    item.setText_type("BIRCH_WOOD_STAIRS");
                    break;
                case "stone_stairs":
                    item.setText_type("COBBLESTONE_STAIRS");
                    break;
                case "cobblestone_wall":
                    item.setText_type("COBBLE_WALL");
                    break;
                case "command_block":
                    item.setText_type("COMMAND");
                    break;
                case "repeating_command_block":
                    item.setText_type("COMMAND_REPEATING");
                    break;
                case "chain_command_block":
                    item.setText_type("COMMAND_CHAIN");
                    break;
                case "enchanting_table":
                    item.setText_type("ENCHANTMENT_TABLE");
                    break;
                case "end_portal":
                    item.setText_type("ENDER_PORTAL");
                    break;
                case "end_portal_frame":
                    item.setText_type("ENDER_PORTAL_FRAME");
                    break;
                case "end_stone":
                    item.setText_type("ENDER_STONE");
                    break;
                case "lit_redstone_ore":
                    item.setText_type("GLOWING_REDSTONE_ORE");
                    break;
                case "monster_egg":
                    item.setText_type("MONSTER_EGGS");
                    break;
                case "wooden_slab":
                    item.setText_type("WOOD_DOUBLE_STEP");
                    break;
                case "stone_slab":
                    item.setText_type("STEP");
                    break;
                case "double_wooden_slab":
                    item.setText_type("WOOD_STEP");
                    break;
                case "double_stone_slab":
                    item.setText_type("DOUBLE_STEP");
                    break;
                case "stained_hardened_clay":
                    item.setText_type("STAINED_CLAY");
                    break;
                case "noteblock":
                    item.setText_type("NOTE_BLOCK");
                    break;
                case "crafting_table":
                    item.setText_type("WORKBENCH");
                    break;
                case "snow":
                    item.setText_type("SNOW_BLOCK");
                    break;
                case "slime":
                    item.setText_type("SLIME_BLOCK");
                    break;
                case "lit_pumpkin":
                    item.setText_type("JACK_O_LANTERN");
                    break;
                case "leaves2":
                    item.setText_type("LEAVES_2");
                    break;
                case "log2":
                    item.setText_type("LOG_2");
                    break;
                case "nether_brick_fence":
                    item.setText_type("NETHER_FENCE");
                    break;
                case "hardened_clay":
                    item.setText_type("HARD_CLAY");
                    break;
                case "brown_mushroom_block":
                    item.setText_type("HUGE_MUSHROOM_1");
                    break;
                case "red_mushroom_block":
                    item.setText_type("HUGE_MUSHROOM_2");
                    break;
                case "piston":
                    item.setText_type("PISTON_BASE");
                    break;
                case "sticky_piston":
                    item.setText_type("PISTON_STICKY_BASE");
                    break;
                case "iron_bars":
                    item.setText_type("IRON_FENCE");
                    break;
                case "redstone_lamp":
                    item.setText_type("REDSTONE_LAMP_OFF");
                    break;
                case "lit_redstone_lamp":
                    item.setText_type("REDSTONE_LAMP_ON");
                    break;
                case "mycelium":
                    item.setText_type("MYCEL");
                    break;
                case "jungle_stairs":
                    item.setText_type("JUNGLE_WOOD_STAIRS");
                    break;
                case "stone_brick_stairs":
                    item.setText_type("SMOOTH_STAIRS");
                    break;
                case "stonebrick":
                    item.setText_type("SMOOTH_BRICK");
                    break;
                case "spruce_stairs":
                    item.setText_type("SPRUCE_WOOD_STAIRS");
                    break;
                case "glass_pane":
                    item.setText_type("THIN_GLASS");
                    break;
                case "trapdoor":
                    item.setText_type("TRAP_DOOR");
                    break;
                case "planks":
                    item.setText_type("WOOD");
                    break;
                case "oak_stairs":
                    item.setText_type("WOOD_STAIRS");
                    break;
                case "standing_sign":
                    item.setText_type("SIGN_POST");
                    break;
                case "rail":
                    item.setText_type("RAILS");
                    break;
                case "wooden_pressure_plate":
                    item.setText_type("WOOD_PLATE");
                    break;
                case "golden_rail":
                    item.setText_type("POWERED_RAIL");
                    break;
                case "cake":
                    item.setText_type("CAKE_BLOCK");
                    break;
                case "repeater":
                    item.setText_type("DIODE_BLOCK_OFF");
                    break;
                case "unpowered_repeater":
                    item.setText_type("DIODE_BLOCK_ON");
                    break;
                case "unlit_redstone_torch":
                    item.setText_type("REDSTONE_TORCH_OFF");
                    break;
                case "redstone_torch":
                    item.setText_type("REDSTONE_TORCH_ON");
                    break;
                case "snow_layer":
                    item.setText_type("SNOW");
                    break;
                case "heavy_weighted_pressure_plate":
                    item.setText_type("STONE_PLATE");
                    break;
                case "red_flower":
                    item.setText_type("RED_ROSE");
                    break;
                case "waterlily":
                    item.setText_type("WATER_LILY");
                    break;
                case "nether_wart":
                    item.setText_type("NETHER_WARTS");
                    break;
                case "bed":
                    item.setText_type("BED_BLOCK");
                    break;
                case "piston_head":
                    item.setText_type("PISTON_EXTENSION");
                    break;
                case "iron_door":
                    item.setText_type("IRON_DOOR_BLOCK");
                    break;
                case "reeds":
                    item.setText_type("SUGAR_CANE_BLOCK");
                    break;
                case "farmland":
                    item.setText_type("SOIL");
                    break;
                default:
                    item.setText_type(item.getText_type().toUpperCase());
                    break;
            }

            MCItems.add(item);

        }

    }

    public Iterator<MCItem> iterator() {
        return MCItems.iterator();
    }
}
