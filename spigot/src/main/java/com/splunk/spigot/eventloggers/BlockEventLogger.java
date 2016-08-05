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


        switch (block.getType()) {
            case STONE:
                String[] StoneBlockNames = {
                        "STONE", "GRANITE", "POLISHED GRANITE", "DIORITE", "POLISHED_DIORITE",
                        "ANDESITE", "POLISHED_ANDESITE"
                };
                return StoneBlockNames[block.getState().getData().toItemStack(1).getDurability()];

            case DIRT:
                String[] DirtBlockNames = {
                        "DIRT", "COARSE DIRT", "PODZOL"
                };
                return DirtBlockNames[block.getState().getData().toItemStack(1).getDurability()];

            case SAND:
                String[] SandBlockNames = {
                        "SAND", "RED_SAND"
                };
                return SandBlockNames[block.getState().getData().toItemStack(1).getDurability()];

            case LOG:
                String[] LogBlockNames = {
                        "OAK_LOG", "SPRUCE_LOG", "BIRCH_LOG", "JUNGLE_LOG"
                };
                return LogBlockNames[block.getState().getData().toItemStack(1).getDurability()];

            case LOG_2:
                String[] Log2BlockNames = {
                        "ACACIA_LOG", "DARK_OAK_LOG"
                };
                return Log2BlockNames[block.getState().getData().toItemStack(1).getDurability()];

            case LEAVES:
                // multiple values represent decay states.
                String[] LeavesBlockNames = {
                        "OAK_LEAVES", "SPRUCE_LEAVES", "BIRCH_LEAVES", "JUNGLE_LEAVES",
                        "OAK_LEAVES", "SPRUCE_LEAVES", "BIRCH_LEAVES", "JUNGLE_LEAVES",
                        "OAK_LEAVES", "SPRUCE_LEAVES", "BIRCH_LEAVES", "JUNGLE_LEAVES",
                        "OAK_LEAVES", "SPRUCE_LEAVES", "BIRCH_LEAVES", "JUNGLE_LEAVES"

                };
                return LeavesBlockNames[block.getState().getData().toItemStack(1).getDurability()];
            case LEAVES_2:
                // multiple values represent decay states.
                String[] Leaves2BlockNames = {
                        "ACACIA_LEAVES", "DARK_OAK_LEAVES",
                        "ACACIA_LEAVES", "DARK_OAK_LEAVES",
                        "ACACIA_LEAVES", "DARK_OAK_LEAVES",
                        "ACACIA_LEAVES", "DARK_OAK_LEAVES"

                };
                return Leaves2BlockNames[block.getState().getData().toItemStack(1).getDurability()];

            case WOOD:
                String[] woodBlockNames = {
                        "OAK_WOOD_PLANKS", "SPRUCE_WOOD_PLANKS", "BIRCH_WOOD_PLANKS", "JUNGLE_WOOD_PLANKS",
                        "ACACIA_WOOD_PLANKS", "DARK_OAK_WOOD_PLANKS"
                };
                return woodBlockNames[block.getState().getData().toItemStack(1).getDurability()];

            case SANDSTONE:
                // multiple values represent decay states.
                String[] SandStoneBlockNames = {
                        "SANDSTONE", "CHISELED_SANDSTONE", "SMOOTH_SANDSTONE"
                };
                return SandStoneBlockNames[block.getState().getData().toItemStack(1).getDurability()];

            case RED_SANDSTONE:
                // multiple values represent decay states.
                String[] RedSandStoneBlockNames = {
                        "RED_SANDSTONE", "CHISELED_RED_SANDSTONE", "SMOOTH_RED_SANDSTONE"
                };
                return RedSandStoneBlockNames[block.getState().getData().toItemStack(1).getDurability()];

            case RED_ROSE:
                String[] RedRoseBlockNames = {
                        "POPPY", "BLUE_ORCHID", "ALLIUM", "AZURE_BLUET", "RED_TULIP", "ORANGE_TULIP", "WHITE_TULIP",
                        "PINK_TULIP", "OXEYE_DAISY"
                };
                return RedRoseBlockNames[block.getState().getData().toItemStack(1).getDurability()];

            case YELLOW_FLOWER:
                String[] YellowFlowerBlockNames = {
                        "DANDELION"
                };
                return YellowFlowerBlockNames[block.getState().getData().toItemStack(1).getDurability()];

            case DOUBLE_PLANT:
                String[] DoublePlantBlockNames = {
                        "SUNFLOWER", "LILAC", "DOUBLE_TALLGRASS", "LARGE_FERN", "ROSE_BUSH", "PEONY"
                };
                return DoublePlantBlockNames[block.getState().getData().toItemStack(1).getDurability()];

            case HARD_CLAY:
                String[] HardenedClayBlockNames = {
                        "WHITE_HARDENED_CLAY", "ORANGE_HARDENED_CLAY", "MAGENTA_HARDENED_CLAY", "LIGHTBLUE_HARDENED_CLAY",
                        "YELLOW_HARDENED_CLAY", "LIME_HARDENED_CLAY", "PINK_HARDENED_CLAY", "GRAY_HARDENED_CLAY",
                        "LIGHTGRAY_HARDENED_CLAY", "CYAN_HARDENED_CLAY", "PURPLE_HARDENED_CLAY", "BLUE_HARDENED_CLAY",
                        "BROWN_HARDENED_CLAY", "GREEN_HARDENED_CLAY", "RED_HARDENED_CLAY", "BLACK_HARDENED_CLAY"
                };
                return HardenedClayBlockNames[block.getState().getData().toItemStack(1).getDurability()];

            case STAINED_CLAY:
                String[] StainedClayBlockNames = {
                        "WHITE_STAINED_CLAY", "ORANGE_STAINED_CLAY", "MAGENTA_STAINED_CLAY", "LIGHTBLUE_STAINED_CLAY",
                        "YELLOW_STAINED_CLAY", "LIME_STAINED_CLAY", "PINK_STAINED_CLAY", "GRAY_STAINED_CLAY",
                        "LIGHTGRAY_STAINED_CLAY", "CYAN_STAINED_CLAY", "PURPLE_STAINED_CLAY", "BLUE_STAINED_CLAY",
                        "BROWN_STAINED_CLAY", "GREEN_STAINED_CLAY", "RED_STAINED_CLAY", "BLACK_STAINED_CLAY"
                };
                return StainedClayBlockNames[block.getState().getData().toItemStack(1).getDurability()];

            case WOOL:
                String[] WoolBlockNames = {
                        "WHITE_WOOL", "ORANGE_WOOL", "MAGENTA_WOOL", "LIGHTBLUE_WOOL",
                        "YELLOW_WOOL", "LIME_WOOL", "PINK_WOOL", "GRAY_WOOL",
                        "LIGHTGRAY_WOOL", "CYAN_WOOL", "PURPLE_WOOL", "BLUE_WOOL",
                        "BROWN_WOOL", "GREEN_WOOL", "RED_WOOL", "BLACK_WOOL"
                };
                return WoolBlockNames[block.getState().getData().toItemStack(1).getDurability()];

            case SPONGE:
                String[] SpongeBlockNames = {
                        "SPONGE", "WET_SPONGE"
                };

                return SpongeBlockNames[block.getState().getData().toItemStack(1).getDurability()];

            case DOUBLE_STEP:
                String[] DoubleStoneSlabBlockNames = {"DOUBLE_STONE_SLAB", "DOUBLE_SANDSTONE_SLAB", "DOUBLE_WOODEN_SLAB", "DOUBLE_COBBLESTONE_SLAB",
                        "DOUBLE_BRICKS_SLAB", "DOUBLE_STONE_BRICK_SLAB", "DOUBLE_NETHER_BRICK_SLAB", "DOUBLE_QUARTZ_SLAB",
                        "SMOOTH_DOUBLE_STONE_SLAB", "SMOOTH_DOUBLE_SANDSTONE_SLAB"
                };
                return DoubleStoneSlabBlockNames[block.getState().getData().toItemStack(1).getDurability()];

            case STEP:
                String[] StoneSlabBlockNames = {
                        "STONE_SLAB", "SANDSTONE_SLAB", "WOODEN_SLAB", "COBBLESTONE_SLAB",
                        "BRICKS_SLAB", "STONE_BRICK_SLAB", "NETHER_BRICK_SLAB", "QUARTZ_SLAB",
                        "SMOOTH_STONE_SLAB", "SMOOTH_SANDSTONE_SLAB"
                };
                return StoneSlabBlockNames[block.getState().getData().toItemStack(1).getDurability()];

            case WOOD_STEP:
                String[] WoodSlabBlockNames = {
                        "OAK_WOOD_SLAB", "SPRUCE_WOOD_SLAB", "BIRCH_WOOD_SLAB", "JUNGLE_WOOD_SLAB",
                        "ACACIA_WOOD_SLAB", "DARK_OAK_WOOD_SLAB"
                };
                return WoodSlabBlockNames[block.getState().getData().toItemStack(1).getDurability()];

            case PRISMARINE:
                String[] PrismarineBLockNames = {
                        "PRISMARINE", "PRISMARINE_BRICKS", "DARK_PRISMARINE"
                };
                return PrismarineBLockNames[block.getState().getData().toItemStack(1).getDurability()];

            default:
                return block.getType().name();


        }
    }

}