package com.ind.mod.item;

import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;

public class WoodArmorItem extends ArmorItem {
    public WoodArmorItem(ArmorMaterial material, Type type, Settings settings) {
        super(material, type, settings);
    }

    @Override
    public boolean isFireproof() {
        return false;
    }

    @Override
    public boolean damage(DamageSource source) {
        if(source.getSource() instanceof PlayerEntity player && source.getSource().isOnFire() && !player.isCreative()){
            for(ItemStack item:source.getSource().getArmorItems()){
                if(item.getDamage() + 2 < item.getMaxDamage()){
                    item.setDamage(item.getDamage() + 2);
                } else {
                    item.decrement(1);
                }

            }
        }
        return super.damage(source);
    }
}
