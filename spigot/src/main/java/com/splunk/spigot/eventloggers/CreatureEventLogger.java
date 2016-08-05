package com.splunk.spigot.eventloggers;

import com.splunk.sharedmc.event_loggers.AbstractEventLogger;
import com.splunk.sharedmc.loggable_events.LoggableCreatureEvent;
import com.splunk.sharedmc.loggable_events.LoggableCreatureEvent.EntityEventAction;
import com.splunk.sharedmc.utilities.LivingEntity;
import com.splunk.sharedmc.utilities.Point3d;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Skeleton;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.CreatureSpawnEvent;
import org.bukkit.event.entity.EntityEvent;

import java.util.Properties;


public class CreatureEventLogger extends AbstractEventLogger implements Listener {

    public CreatureEventLogger(Properties properties) {
        super(properties);
    }

    @EventHandler
    public void captureSpawnEvent(CreatureSpawnEvent event) {
        if (!event.isCancelled())
            logAndSend(getLoggableEntityEvent(EntityEventAction.SPAWN, event));
    }

    private LoggableCreatureEvent getLoggableEntityEvent(EntityEventAction action, EntityEvent event) {

        final Entity entity = event.getEntity();
        final Location location = entity.getLocation();
        final World world = entity.getWorld();

        Point3d coordinates = new Point3d(location.getX(), location.getY(), location.getZ());

        LoggableCreatureEvent entityEvent = new LoggableCreatureEvent(world.getFullTime(), minecraft_server, world.getName(), action);

        if (event.getEntityType() == EntityType.SKELETON) {
            Skeleton skeleton = (org.bukkit.entity.Skeleton) event.getEntity();

            entityEvent.setEntity(new LivingEntity("creature", skeleton.getSkeletonType() + "_SKELETON", coordinates));

        } else {

            entityEvent.setEntity(new LivingEntity("creature", event.getEntityType().name(), coordinates));
        }

        return entityEvent;
    }
}
