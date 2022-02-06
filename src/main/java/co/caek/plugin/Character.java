package co.caek.plugin;

import com.destroystokyo.paper.event.player.PlayerJumpEvent;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.entity.*;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.entity.*;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.inventory.EntityEquipment;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import java.util.Objects;

// should we add final keyboards to the events?

public class Character implements Listener {
    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent event) {
        Player player = event.getPlayer();
        player.discoverRecipes(CaekPlugin.recipes);
        if (player.getGameMode() != GameMode.SURVIVAL) player.setGameMode(GameMode.SURVIVAL);
    }

    @EventHandler
    public void onPlayerJump(PlayerJumpEvent event) {
        // When jumping, chance to create desire path.
        Block block = event.getTo().getBlock();
        Player player = event.getPlayer();
        if (lrng8.XinSixten(1) && lrng8.XinSixten(1)) {
            switch (block.getType()) {
                case GRASS_BLOCK -> block.setType(Material.DIRT_PATH);
                case ICE -> block.setType(Material.WATER);
            }
        }
    }

    @EventHandler
    public void onProjectileHitEvent(ProjectileHitEvent event) {
        Projectile projectile = event.getEntity();
        if (!(projectile instanceof AbstractArrow arrow)) return;
        if (projectile instanceof Trident) return;
        // enable picking up of mob arrows - 3/16 chance
        if (lrng8.rand() > 12) {
            arrow.setPickupStatus(AbstractArrow.PickupStatus.ALLOWED);
        } else {
            arrow.remove();
        }
    }

    @EventHandler
    public void onPlayerInteractEvent(PlayerInteractEvent event) {
        // Prevent trampling of Farmland
        if (event.getAction() == Action.PHYSICAL && Objects.requireNonNull(event.getClickedBlock()).getType() == Material.FARMLAND) event.setCancelled(true);
    }

    @EventHandler
    public void onEntityShootBowEvent(EntityShootBowEvent event) {
        LivingEntity mob = event.getEntity();
        if (!(mob instanceof Skeleton)) return;
        ItemStack bow = event.getBow();
        assert bow != null;
        ItemMeta meta = bow.getItemMeta();
        if (!(meta instanceof Damageable dmg)) return;
        int damage = lrng8.rand() * 3;
        if (dmg.getDamage() + damage <= bow.getType().getMaxDurability()) {
            dmg.setDamage(dmg.getDamage() + damage);
            bow.setItemMeta(dmg);
        } else {
            dmg.setDamage(bow.getType().getMaxDurability());
            bow.setItemMeta(dmg);
        }
    }

    @EventHandler
    public void onEntityDamageByEntity(EntityDamageByEntityEvent event) {
        if (event.getDamager() instanceof Player player) {
            ItemStack weapon = player.getInventory().getItemInMainHand();
            // placeholder improvised weapons - consider changing item damage when crafted / description
            switch (weapon.getType()) {
                case STICK -> event.setDamage(2);
                case TORCH -> {
                    if (lrng8.rand() > 7) event.getEntity().setFireTicks(60);
                    event.setDamage(0);
                }
            }
        }
    }

    @EventHandler
    public void onCreatureSpawn(CreatureSpawnEvent event) {
        LivingEntity creature = event.getEntity();
        switch (creature.getType()) {
            // temporarily normalize equipment of mobs...
            case ZOMBIE -> {
                EntityEquipment equipment = creature.getEquipment();
                assert equipment != null;
                equipment.clear();
                // test
                equipment.setItemInMainHand(new ItemStack(Material.WOODEN_SWORD));
            }
            case SKELETON -> {
                EntityEquipment equipment = creature.getEquipment();
                assert equipment != null;
                equipment.clear();
                equipment.setItemInMainHand(new ItemStack(Material.BOW));
            }
        }
    }


    @EventHandler
    public void onEntityDeath(EntityDeathEvent event) {
        LivingEntity entity = event.getEntity();
        Location loc = entity.getLocation();
        switch (entity.getType()) {
            case ZOMBIE -> {
                EntityEquipment equipment = entity.getEquipment();
                assert equipment != null;
                ItemStack[] armor = equipment.getArmorContents();
                for (ItemStack itemStack : armor) {
                    Blocks.dropItem(loc, itemStack);
                }
                Blocks.dropItem(loc, equipment.getItemInMainHand());
                Blocks.dropItem(loc, equipment.getItemInOffHand());
                Blocks.dropItem(loc, Material.ROTTEN_FLESH, 2);
            }
            case SKELETON -> {
                EntityEquipment equipment = entity.getEquipment();
                assert equipment != null;
                ItemStack[] armor = equipment.getArmorContents();
                for (ItemStack itemStack : armor) {
                    Blocks.dropItem(loc, itemStack);
                }
                Blocks.dropItem(loc, equipment.getItemInOffHand());
                Blocks.dropItem(loc, Material.BONE, 2);

                ItemStack main_hand = equipment.getItemInMainHand();
                if (main_hand.getType() == Material.BOW) {
                    ItemMeta meta = main_hand.getItemMeta();
                    if (!(meta instanceof Damageable dmg)) return;
                    int damage = dmg.getDamage();
                    if (damage != main_hand.getType().getMaxDurability()) {
                        Blocks.dropItem(loc, main_hand);
                        Blocks.dropItem(loc, Material.ARROW, (main_hand.getType().getMaxDurability() - damage / 16));
                    }
                } else {
                    Blocks.dropItem(loc, main_hand);
                }
            }
        }
    }

    @EventHandler
    public void onEntityDrop(EntityDropItemEvent event) {
        Entity entity = event.getEntity();
        switch (entity.getType()) {
            case ZOMBIE, SKELETON -> event.setCancelled(true);
        }

    }
}
