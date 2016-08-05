package com.splunk.sharedmc.loggable_events;


import com.splunk.sharedmc.utilities.LivingEntity;
import com.splunk.sharedmc.utilities.Point3d;

public class LoggablePlayerEvent extends AbstractLoggableEvent {


    private LivingEntity player;
    private Point3d src;
    private Point3d dest;

    public LoggablePlayerEvent(long gameTime, String minecraft_server, String world, LoggablePlayerEvent.PlayerEventAction action) {
        super(gameTime, minecraft_server, world, "player", action.asString());
    }

    public Point3d getDest() {
        return dest;
    }

    public void setDest(Point3d dest) {
        this.dest = dest;
    }

    public LivingEntity getPlayer() {
        return this.player;
    }

    public void setPlayer(LivingEntity player) {
        this.player = player;
    }

    public Point3d getSrc() {
        return this.src;
    }

    public void setSrc(Point3d src) {
        this.src = src;
    }

    /**
     * Different types of actions that can occur as part of a PlayerEvent.
     */
    public enum PlayerEventAction {
        PLAYER_LOGIN("login"),
        PLAYER_LOGOUT("logout"),
        MOVE("move"),
        TELEPORT("teleport");

        /**
         * The name of the action.
         */
        private final String action;

        PlayerEventAction(String action) {
            this.action = action;
        }

        /**
         * String representation of the action.
         *
         * @return The action in friendly String format.
         */
        public String asString() {
            return action;
        }
    }
}
