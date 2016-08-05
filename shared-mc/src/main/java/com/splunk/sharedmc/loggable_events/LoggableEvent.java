package com.splunk.sharedmc.loggable_events;

import com.splunk.sharedmc.utilities.Point3d;

public interface LoggableEvent {

    /**
     * @return the full in-game time on this world
     */
    long getGameTime();

    /**
     * @param gameTime the full in-game time on this world
     */
    void setGameTime(long gameTime);


    /**
     * @return the name of user-defined Minecraft server
     */
    String getMinecraftServer();

    /**
     * @param minecraft_server the name of user-defined Minecraft server
     */
    void setMinecraftServer(String minecraft_server);

    /**
     * @return the name of the world
     */
    String getWorld();

    /**
     * @param world the name of the world
     */
    void setWorld(String world);


    /**
     * @return the category of the event
     */
    String getCategory();

    /**
     * @param category the category of the event
     */
    void setCategory(String category);

    /**
     * @return the event action
     */
    String getAction();

    /**
     * @param action the event action
     */
    void setAction(String action);

    /**
     * Gets a JSON String of this object.
     *
     * @return JSON representing this object.
     */
    String toJSON();

}
