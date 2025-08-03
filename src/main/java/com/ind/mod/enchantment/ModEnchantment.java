package com.ind.mod.enchantment;

import com.ind.mod.StoneWoodIndustry;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModEnchantment {
    public static Enchantment register(String id, Enchantment enchantment){
        return Registry.register(Registries.ENCHANTMENT, new Identifier(StoneWoodIndustry.MOD_ID, id), enchantment);
    }
    public static void registerModEnchantment(){
        StoneWoodIndustry.LOGGER.info("Stone & Wood Industry>>>Registering Mod Enchantments!");
    }

    public static final Enchantment HARDEN = register("harden", new HardenEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR, EquipmentSlot.values()));
    public static final Enchantment SPEED = register("speed", new SpeedEnchantment(Enchantment.Rarity.RARE, EnchantmentTarget.ARMOR, EquipmentSlot.FEET));
    public static final Enchantment UNDYING = register("undying", new UndyingEnchantment());
}
