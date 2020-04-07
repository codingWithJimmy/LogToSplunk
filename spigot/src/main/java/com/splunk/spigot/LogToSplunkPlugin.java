package com.splunk.spigot;

import com.splunk.spigot.eventloggers.BlockEventLogger;
import com.splunk.spigot.eventloggers.CreatureEventLogger;
import com.splunk.spigot.eventloggers.DeathEventLogger;
import com.splunk.spigot.eventloggers.PlayerEventLogger;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;

public class LogToSplunkPlugin extends JavaPlugin implements Listener {
    public static final String SPLUNK_PROPERTIES = "/plugins/LogToSplunk/LogToSplunk.properties";
    private static final Logger logger = LogManager.getLogger(LogToSplunkPlugin.class.getName());
    private Properties properties;

    /**
     * Called when the mod is initialized.
     */
    @Override
    public void onEnable() {
        // Could probably move this to the AbstractEventLogger in shared
        properties = new Properties();
        final String path = System.getProperty("user.dir") + SPLUNK_PROPERTIES;
        try (final FileReader reader = new FileReader(new File(path))) {

            properties.load(reader);
        } catch (final Exception e) {
            logger.warn(
                    String.format(
                            "Unable to load properties for LogToSplunkMod at %s! Default values will be used.", path),
                    e);
        }


        if (Boolean.valueOf(properties.getProperty("splunk.craft.logging.block.enable", "true")))
            getServer().getPluginManager().registerEvents(new BlockEventLogger(properties), this);

        if (Boolean.valueOf(properties.getProperty("splunk.craft.logging.player.enable", "true")))
            getServer().getPluginManager().registerEvents(new PlayerEventLogger(properties), this);

        if (Boolean.valueOf(properties.getProperty("splunk.craft.logging.creature.enable", "true")))
            getServer().getPluginManager().registerEvents(new CreatureEventLogger(properties), this);

        if (Boolean.valueOf(properties.getProperty("splunk.craft.logging.death.enable", "true")))
            getServer().getPluginManager().registerEvents(new DeathEventLogger(properties), this);


    }

    /**
     * Logs and sends messages to be prepared for Splunk.
     *
     * @param message The message to log.
     */
    private void logAndSend(String message) {
        logger.info(message);
    }
}