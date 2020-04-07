package com.splunk.spigot.eventloggers;

import com.splunk.sharedmc.event_loggers.AbstractEventLogger;
import com.splunk.sharedmc.loggable_events.LoggableBlockEvent;
import com.splunk.sharedmc.loggable_events.LoggableBlockEvent.BlockEventAction;
import com.splunk.sharedmc.utilities.Instrument;
import com.splunk.sharedmc.utilities.LivingEntity;
import com.splunk.sharedmc.utilities.Point3d;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.block.BlockEvent;
import org.bukkit.event.block.BlockIgniteEvent;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.inventory.ItemStack;

import java.util.Properties;


/**
 * Handles the logging of block events.
 */
public class BlockEventLogger extends AbstractEventLogger implements Listener {

    public BlockEventLogger(Properties properties) {
        super(properties);
    }

    /**
     *
     * @param event
     */
    @EventHandler
    public void captureBreakEvent(BlockBreakEvent event) {
        // Only log event if it is successful
        if (!event.isCancelled())
            logAndSend(getLoggableBlockEvent(BlockEventAction.BREAK, event));

    }

    @EventHandler
    public void capturePlaceEvent(BlockPlaceEvent event) {
        if (!event.isCancelled())
            logAndSend(getLoggableBlockEvent(BlockEventAction.PLACE, event));
    }


    @EventHandler
    public void captureIgniteEvent(BlockIgniteEvent event) {
        if (!event.isCancelled())
            logAndSend(getLoggableBlockEvent(BlockEventAction.IGNITE, event));
    }


    private LoggableBlockEvent getLoggableBlockEvent(BlockEventAction action, BlockEvent event) {


        // Pull a couple of objects from the event.
        final Block block = event.getBlock();
        final Location location = block.getLocation();
        final World world = block.getWorld();


        LoggableBlockEvent blockEvent = new LoggableBlockEvent(world.getFullTime(), minecraft_server, world.getName(), action);

        Point3d boxLocation = new Point3d(location.getX(), location.getY(), location.getZ());
        blockEvent.setBlock(new com.splunk.sharedmc.utilities.Block(block.getType().toString(), getBlockName(block), boxLocation));


        if (event instanceof BlockBreakEvent) {

            Player player = ((BlockBreakEvent) event).getPlayer();

            LivingEntity spEntity = new LivingEntity("player", player.getDisplayName(), new Point3d(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()));
            spEntity.setCurrentHealth(player.getHealth());
            spEntity.setMaxHealth(player.getMaxHealth());
            blockEvent.setPlayer(spEntity);


            ItemStack instrument = ((BlockBreakEvent) event).getPlayer().getInventory().getItemInMainHand();
            Instrument tool = new Instrument(instrument.getType().toString());
            for (Enchantment key : instrument.getEnchantments().keySet()) {

                tool.addEnchantment(key.getName().toString() + ":" + instrument.getEnchantments().get(key));
            }


            blockEvent.setTool(tool);


        } else if (event instanceof BlockPlaceEvent) {
            Player player = ((BlockPlaceEvent) event).getPlayer();

            LivingEntity spEntity = new LivingEntity("player", player.getDisplayName(), new Point3d(player.getLocation().getX(), player.getLocation().getY(), player.getLocation().getZ()));
            spEntity.setCurrentHealth(player.getHealth());
            spEntity.setMaxHealth(player.getMaxHealth());
            blockEvent.setPlayer(spEntity);
        } else if (event instanceof BlockIgniteEvent) {


            switch (((BlockIgniteEvent) event).getCause()) {
                case ENDER_CRYSTAL:
                    blockEvent.setCause("ENDER_CRYSTAL");
                    break;
                case EXPLOSION:
                    blockEvent.setCause("EXPLOSION");
                    break;
                case FIREBALL:
                    blockEvent.setCause("FIREBALL");
                    break;
                case FLINT_AND_STEEL:
                    blockEvent.setCause("FLINT_AND_STEEL");
                    break;
                case LAVA:
                    blockEvent.setCause("LAVA");
                    break;
                case LIGHTNING:
                    blockEvent.setCause("LIGHTNING");
                    break;
                case SPREAD:
                    blockEvent.setCause("SPREAD");
                    break;
            }
        }


        return blockEvent;
    }

    public String getBlockName(Block block) {
        String blockName = block.getType().toString();
        return blockName;
    }

}