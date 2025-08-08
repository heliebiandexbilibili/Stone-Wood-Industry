package com.ind.mod.enchantment;

import com.ind.mod.StoneWoodIndustry;
import com.ind.mod.materials.ModArmorMaterials;
import com.ind.mod.utils.LightningHelper;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ItemStack;

import java.util.Map;

public class UndyingEnchantment extends Enchantment {
    protected UndyingEnchantment() {
        super(Rarity.RARE, EnchantmentTarget.ARMOR_CHEST, new EquipmentSlot[]{EquipmentSlot.CHEST});
    }

    @Override
    public void onUserDamaged(LivingEntity user, Entity attacker, int level) {
        if(!user.getWorld().isClient && !attacker.getWorld().isClient){
            if(user.getHealth() <= user.getMaxHealth() * 0.4 && user instanceof PlayerEntity player){
                StoneWoodIndustry.LOGGER.info("onUserDamaged Start.(UndyingEnchantment.java)");
                player.setHealth(user.getMaxHealth() / 2);
                LightningHelper.spawnLightningAroundPlayer(player, 5, level);
                int ol = EnchantmentHelper.getLevel(ModEnchantment.UNDYING, player.getInventory().getArmorStack(2));
                if(EnchantmentHelper.getLevel(ModEnchantment.UNDYING, player.getInventory().getArmorStack(2)) - 1 > 0){
                    int nl = ol - 1;
                    removeEnchantment(player.getInventory().getArmorStack(2), ModEnchantment.UNDYING);
                    player.getInventory().getArmorStack(2).addEnchantment(ModEnchantment.UNDYING, nl);
                } else {
                    removeEnchantment(player.getInventory().getArmorStack(2), ModEnchantment.UNDYING);
                }
                StoneWoodIndustry.LOGGER.info("onUserDamaged Stop.(UndyingEnchantment.java)");
            }
        }
    }

    @Override
    public boolean isAcceptableItem(ItemStack stack) {
        return stack.getItem() instanceof ArmorItem item && (item.getMaterial() == ModArmorMaterials.OBSIDIAN || item.getMaterial() == ModArmorMaterials.CRYING_OBSIDIAN) && item.getSlotType() == EquipmentSlot.CHEST;
    }

    @Override
    public int getMaxLevel() {
        return 10;
    }

    public static void removeEnchantment(ItemStack stack, Enchantment enchantmentToRemove) {
        if (stack.isEmpty()) return;
        Map<Enchantment, Integer> enchantments = EnchantmentHelper.get(stack);
        enchantments.remove(enchantmentToRemove);
        stack.removeSubNbt("Enchantments");
        EnchantmentHelper.set(enchantments, stack);
    }
}
