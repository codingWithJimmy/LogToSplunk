package com.splunk.sharedmc.loggable_events;


import com.splunk.sharedmc.utilities.Block;
import com.splunk.sharedmc.utilities.Instrument;
import com.splunk.sharedmc.utilities.LivingEntity;

/**
 * Almost pojo with fields for information that might be associated with a block event.
 */
public class LoggableBlockEvent extends AbstractLoggableEvent {


    private Block block;
    private LivingEntity player;
    private Instrument tool;
    private String cause;

    public LoggableBlockEvent(long gameTime, String minecraft_server, String world, BlockEventAction action) {
        super(gameTime, minecraft_server, world, "block", action.asString());
    }

    public LivingEntity getPlayer() {
        return player;
    }

    public void setPlayer(LivingEntity player) {
        this.player = player;
    }

    public Block getBlock() {
        return this.block;
    }

    public void setBlock(Block block) {
        this.block = block;
    }


    public Instrument getTool() {
        return this.tool;
    }

    public void setTool(Instrument tool) {
        this.tool = tool;
    }

    public String getCause() {
        return this.cause;
    }

    public void setCause(String cause) {
        this.cause = cause;
    }


    /**
     * Different types of actions that can occur as part of a BlockEvent.
     */
    public enum BlockEventAction {
        BREAK("break"),
        PLACE("place"),
        IGNITE("ignite");

        /**
         * The name of the action.
         */
        private final String action;

        BlockEventAction(String action) {
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
