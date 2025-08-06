package com.ind.mod.item;

import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.text.Text;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class UltimateWoodPickaxe extends PickaxeItem {
    public static final List<StatusEffect> list = List.of(
            StatusEffects.INSTANT_HEALTH,
            StatusEffects.ABSORPTION,
            StatusEffects.REGENERATION,
            StatusEffects.FIRE_RESISTANCE,
            StatusEffects.JUMP_BOOST,
            StatusEffects.RESISTANCE,
            StatusEffects.WATER_BREATHING,
            StatusEffects.SATURATION,
            StatusEffects.HASTE,
            StatusEffects.NIGHT_VISION,
            StatusEffects.STRENGTH
    );
    public UltimateWoodPickaxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient &&
                selected &&
                entity instanceof LivingEntity livingEntity) {
            for(StatusEffect statusEffect: list){
                if(livingEntity.canHaveStatusEffect(new StatusEffectInstance(statusEffect, 100, 0))){
                    livingEntity.addStatusEffect(new StatusEffectInstance(statusEffect, 100, 0));
                }
            }
        }
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        tooltip.add(Text.literal("§7在主手时:"));
        for(StatusEffect statusEffect: list){
            tooltip.add(Text.literal("  §2" + statusEffect.getName().getString() + "I(00:05)"));
        }
    }
}
