package co.caek.plugin;

import org.bukkit.*;
import org.bukkit.event.world.WorldInitEvent;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.Collection;

import static org.bukkit.GameRule.*;

public class CaekPlugin extends JavaPlugin {
    static Server server;
    static Plugin plugin;
    static PluginManager manager;
    static Collection<NamespacedKey> recipes = new ArrayList<>();

    @Override
    public void onEnable() {
        // Fired when the server enables the plugin.
        plugin = this;
        server = getServer();
        manager = server.getPluginManager();
        getLogger().info("Clearing vanilla recipes...");
        Bukkit.clearRecipes();
        getLogger().info("Adding custom recipes...");
        manager.registerEvents(new Recipes(), this);
        Recipes.addRecipes();
        getLogger().info("Loading Blocks module...");
        manager.registerEvents(new Blocks(), this);
        getLogger().info("Loading Character module...");
        manager.registerEvents(new Character(), this);
    }

    public void onWorldInitEvent(WorldInitEvent event) {
        // compare this against config files
        World w = event.getWorld();
        getLogger().info("Setting gamerules for " + w.getName());
        w.setGameRule(ANNOUNCE_ADVANCEMENTS, false);
        w.setGameRule(COMMAND_BLOCK_OUTPUT, true);
        w.setGameRule(DISABLE_ELYTRA_MOVEMENT_CHECK, true); // review
        w.setGameRule(DISABLE_RAIDS, true); // review
        w.setGameRule(DO_DAYLIGHT_CYCLE, true);
        w.setGameRule(DO_ENTITY_DROPS, true); // review
        w.setGameRule(DO_FIRE_TICK, false); // review
        w.setGameRule(DO_IMMEDIATE_RESPAWN, false); // review
        w.setGameRule(DO_INSOMNIA, false); // review
        w.setGameRule(DO_LIMITED_CRAFTING, true); // review
        w.setGameRule(DO_MOB_LOOT, true); // review
        w.setGameRule(DO_MOB_SPAWNING, true);
        w.setGameRule(DO_PATROL_SPAWNING, true); // review
        w.setGameRule(DO_TILE_DROPS, true); // review
        w.setGameRule(DO_TRADER_SPAWNING, true); // review
        w.setGameRule(DO_WEATHER_CYCLE, true);
        w.setGameRule(DROWNING_DAMAGE, true);
        w.setGameRule(FALL_DAMAGE, true);
        w.setGameRule(FIRE_DAMAGE, true);
        w.setGameRule(FORGIVE_DEAD_PLAYERS, true); // review
        w.setGameRule(FREEZE_DAMAGE, true);
        w.setGameRule(KEEP_INVENTORY, false); // review
        w.setGameRule(LOG_ADMIN_COMMANDS, true);
        w.setGameRule(MAX_COMMAND_CHAIN_LENGTH, 1); // review
        w.setGameRule(MAX_ENTITY_CRAMMING, 2);
        w.setGameRule(MOB_GRIEFING, false); // review
        w.setGameRule(NATURAL_REGENERATION, false); // review
        w.setGameRule(PLAYERS_SLEEPING_PERCENTAGE, 100);
        w.setGameRule(RANDOM_TICK_SPEED, 0);
        w.setGameRule(REDUCED_DEBUG_INFO, true);
        w.setGameRule(SEND_COMMAND_FEEDBACK, false);
        w.setGameRule(SHOW_DEATH_MESSAGES, false);
        w.setGameRule(SPAWN_RADIUS, 256);
        w.setGameRule(SPECTATORS_GENERATE_CHUNKS, false);
        w.setGameRule(UNIVERSAL_ANGER, false);
    }
}

