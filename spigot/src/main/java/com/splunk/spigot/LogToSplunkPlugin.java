package com.splunk.spigot;

import com.splunk.spigot.eventloggers.BlockEventLogger;
import com.splunk.spigot.eventloggers.CreatureEventLogger;
import com.splunk.spigot.eventloggers.DeathEventLogger;
import com.splunk.spigot.eventloggers.PlayerEventLogger;
import com.splunk.spigot.utilities.MCItemCatalogue;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.stream.Collectors;

public class LogToSplunkPlugin extends JavaPlugin implements Listener {
    public static final String SPLUNK_PROPERTIES = "/plugins/LogToSplunk/LogToSplunk.properties";
    private static final Logger logger = LogManager.getLogger(LogToSplunkPlugin.class.getName());
    private final String itemsFile = "/plugins/LogToSplunk/items.json";
    private String itemsFileContents = "";
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


        // Read items file
        try {

            FileInputStream inputStream;

            File configFile = new File(System.getProperty("user.dir") + itemsFile);

            inputStream = new FileInputStream(configFile);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));

            itemsFileContents = reader.lines().collect(Collectors.joining("\n"));

            reader.close();
        } catch (Exception e) {
            logger.info(e.getMessage());
        }


        MCItemCatalogue MCItems = MCItemCatalogue.getInstance();
        MCItems.parseItemsFile(itemsFileContents);

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