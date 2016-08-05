package com.splunk.sharedmc.loggable_events;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


/**
 * Classes extending this benefit from a convenient way to get a Json representation, time of
 * creation and event type, world name, coordinates and location.
 */
public class AbstractLoggableEvent implements LoggableEvent {

    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    protected String category;
    private long gameTime;
    private String minecraft_server;
    private String world;

    private String action;


    /**
     * Default Constructor
     */
    public AbstractLoggableEvent(long gameTime, String minecraft_server, String world, String category, String action) {
        this.gameTime = gameTime;
        this.minecraft_server = minecraft_server;
        this.world = world;

        this.category = category;
        this.action = action;
    }

    /**
     * @return the full in-game time on this world
     */
    public long getGameTime() {
        return this.gameTime;
    }

    /**
     * @param gameTime the full in-game time on this world
     */
    public void setGameTime(long gameTime) {
        this.gameTime = gameTime;
    }


    /**
     * @return the name of user-defined Minecraft server
     */
    public String getMinecraftServer() {
        return this.minecraft_server;
    }

    /**
     * @param minecraft_server the name of user-defined Minecraft server
     */
    public void setMinecraftServer(String minecraft_server) {
        this.minecraft_server = minecraft_server;
    }

    /**
     * @return the name of the world
     */
    public String getWorld() {
        return this.world;
    }

    /**
     * @param world the name of the world
     */
    public void setWorld(String world) {
        this.world = world;
    }


    /**
     * @return the category of the event
     */
    public String getCategory() {
        return this.category;
    }

    /**
     * @param category the category of the event
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the event action
     */
    public String getAction() {
        return this.action;
    }

    /**
     * @param action the event action
     */
    public void setAction(String action) {
        this.action = action;
    }

    @Override
    public String toJSON() {
        return gson.toJson(this);
    }


}
