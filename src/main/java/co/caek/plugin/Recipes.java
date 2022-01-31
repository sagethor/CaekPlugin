package co.caek.plugin;

import org.bukkit.Material;
import org.bukkit.NamespacedKey;
import org.bukkit.event.Listener;
import org.bukkit.inventory.*;

import static co.caek.plugin.CaekPlugin.plugin;
import static co.caek.plugin.CaekPlugin.server;
import static org.bukkit.Material.*;

public class Recipes implements Listener {
    // Quick-make a new recipe key.
    static public NamespacedKey newKey(String key) {
        NamespacedKey newKey = new NamespacedKey(plugin, key);
        CaekPlugin.recipes.add(newKey); return newKey;
    }

    // New shaped recipes for specific ItemStack / generic Material.
    static public ShapedRecipe newShaped(String key, ItemStack item) { return new ShapedRecipe(Recipes.newKey(key), item); }
    static public ShapedRecipe newShaped(String key, Material result) { return newShaped(key, new ItemStack(result)); }
    // New shapeless recipe for a specific ItemStack / generic Material.
    static public ShapelessRecipe newShapeless(String key, ItemStack item) { return new ShapelessRecipe(Recipes.newKey(key), item); }
    static public ShapelessRecipe newShapeless(String key, Material result) { return newShapeless(key, new ItemStack(result)); }


    // Step 1: Create a new recipe with target material. (newShaped / newShapeless)
    // Step 2: Add ingredients to recipe. (setIngredient / addIngredient)
    // Step 3: Set shape and add recipe. (setShape & addRecipe)


    // Placeholder - duration 10s, efficiency 10s; maybe we do item by item smelt times.
    // To implement - cook something on top while the furnace runs... (5 seconds)
    static int furnaceTime = 200;
    // New furnace recipe for specific ItemStack / generic Material and generic Material input.
    static public void newFurnace(String key, ItemStack item, Material source) {
        server.addRecipe(new FurnaceRecipe(Recipes.newKey(key), item, source, 0.0F, furnaceTime));
    }
    static public void newFurnace(String key, Material result, Material source) { newFurnace(key, new ItemStack(result), source); }
    // Add one for RecipeChoice version if needed. (x2)

    // Placeholder - duration 5s, efficiency 10s; maybe we do item by item smelt times.
    // To implement - cook something on top while the blast furnace runs... (5 seconds)
    static int blastingTime = 100;
    // New blast furnace recipe for specific ItemStack / generic Material and generic Material input.
    static public void newBlasting(String key, ItemStack item, Material source) {
        server.addRecipe(new BlastingRecipe(Recipes.newKey(key), item, source, 0.0F, blastingTime));
    }
    static public void newBlasting(String key, Material result, Material source) { newBlasting(key, new ItemStack(result), source); }
    // Add one for RecipeChoice version if needed. (x2)

    // Placeholder - duration 20s; maybe we do item by item smelt times.
    static int campfireTime = 400;
    // New campfire recipe for a specific ItemStack / generic Material and generic Material input.
    static public void newCampfire(String key, ItemStack item, Material source) {
        server.addRecipe(new CampfireRecipe(Recipes.newKey(key), item, source, 0.0F, campfireTime));
    }
    static public void newCampfire(String key, Material result, Material source) { newCampfire(key, new ItemStack(result), source); }
    // Add one for RecipeChoice version if needed. (x2)

    // Placeholder - duration 5s, efficiency 20s; maybe we do item by item smelt times.
    static int smokingTime = 100;
    // New smoker recipe for specific ItemStack / generic Material and generic Material input.
    static public void newSmoking(String key, ItemStack item, Material source, int time) {
        server.addRecipe(new SmokingRecipe(Recipes.newKey(key), item, source, 0.0F, time));
    }
    static public void newSmoking(String key, ItemStack item, Material source) {
        newSmoking(key, item, source, smokingTime);
    }
    static public void newSmoking(String key, Material result, Material source) { newSmoking(key, new ItemStack(result), source); }
    // Add one for RecipeChoice version if needed. (x2)

    static public void campfireRecipes() {
        newCampfire("campfire_torch", TORCH, STICK);

        // several dyes are missing - mixing recipes
        newCampfire("campfire_white_dye", WHITE_DYE, LILY_OF_THE_VALLEY);
        newCampfire("campfire_black_dye", BLACK_DYE, INK_SAC); // wither rose is an alternative
        newCampfire("campfire_red_dye", RED_DYE, RED_TULIP); // poppy / rose bush / beetroot are alternatives
        newCampfire("campfire_orange_dye", ORANGE_DYE, ORANGE_TULIP);
        newCampfire("campfire_yellow_dye", YELLOW_DYE, DANDELION); // sunflower is an alternative
        newCampfire("campfire_light_blue_dye", LIGHT_BLUE_DYE, BLUE_ORCHID);
        newCampfire("campfire_blue_dye", BLUE_DYE, CORNFLOWER);
        newCampfire("campfire_magenta_dye", MAGENTA_DYE, ALLIUM); // lilac is an alternative
        newCampfire("campfire_brown_dye", BROWN_DYE, COCOA_BEANS);
        newCampfire("campfire_green_dye", GREEN_DYE, CACTUS);
        newCampfire("campfire_lime_dye", LIME_DYE, SEA_PICKLE); // add sea pickles to Blocks, use wiki as reference

        newCampfire("campfire_porkchop", COOKED_PORKCHOP, PORKCHOP);
        newCampfire("campfire_mutton", COOKED_MUTTON, MUTTON);
        newCampfire("campfire_beef", COOKED_BEEF, BEEF);
        newCampfire("campfire_chicken", COOKED_CHICKEN, CHICKEN);
        newCampfire("campfire_cod", COOKED_COD, COD);
        newCampfire("campfire_rabbit", COOKED_RABBIT, RABBIT);
        newCampfire("campfire_salmon", COOKED_SALMON, SALMON);
        newCampfire("campfire_potato", BAKED_POTATO, POTATO);
    }

    static public void addRecipes() {
        campfireRecipes();
    }
}
