package com.ind.mod.item;

import com.google.common.collect.ImmutableMap;
import com.ind.mod.materials.ModArmorMaterials;
import net.minecraft.entity.Entity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class StoneArmorItem extends ArmorItem {
    private static final Map<ArmorMaterial, List<StatusEffectInstance>> MAP =
            (new ImmutableMap.Builder<ArmorMaterial, List<StatusEffectInstance>>()
                    .put(ModArmorMaterials.STONE, Arrays.asList(
                            new StatusEffectInstance(StatusEffects.SLOWNESS, 1, 2, false, false, true)
                    ))
                    .build());
    public StoneArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!world.isClient){
            if(entity instanceof PlayerEntity player && hasFullSuitArmorOn(player)){
                evaluateArmorEffects(player);
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private void evaluateArmorEffects(PlayerEntity player) {
        for(Map.Entry<ArmorMaterial, List<StatusEffectInstance>> entry: MAP.entrySet()){
            ArmorMaterial armorMaterial = entry.getKey();
            List<StatusEffectInstance> statusEffectInstanceList = entry.getValue();

            if(hasCurrectArmorOn(material, player)){
                for (StatusEffectInstance instance:statusEffectInstanceList){
                    StatusEffect effect = instance.getEffectType();
                    if(!player.hasStatusEffect(effect)){
                        player.addStatusEffect(instance);
                    }
                }
            }
        }
    }

    private boolean hasCurrectArmorOn(ArmorMaterial material, PlayerEntity player) {
        for (ItemStack stack:player.getArmorItems()){
            if(!(stack.getItem() instanceof ArmorItem)){
                return false;
            }
        }
        ArmorItem helmet = (ArmorItem) player.getInventory().getArmorStack(3).getItem();
        ArmorItem chestplate = (ArmorItem) player.getInventory().getArmorStack(2).getItem();
        ArmorItem leggings = (ArmorItem) player.getInventory().getArmorStack(1).getItem();
        ArmorItem boots = (ArmorItem) player.getInventory().getArmorStack(0).getItem();

        return helmet.getMaterial() == material && chestplate.getMaterial() == material &&
                leggings.getMaterial() == material && boots.getMaterial() == material;
    }

    private boolean hasFullSuitArmorOn(PlayerEntity player){
        for(int i=0;i<=3;i++){
            if(player.getInventory().getArmorStack(i).isEmpty()){
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean isFireproof() {
        return true;
    }
}
