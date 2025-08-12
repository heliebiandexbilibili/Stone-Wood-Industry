package com.ind.mod.item;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ToolMaterial;

import java.util.List;

public class OakPickaxe extends WoodPickaxeItem{
    public OakPickaxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public StatusEffect getMaxLevelEffect() {
        return StatusEffects.HASTE;
    }

    @Override
    public Block getBlockToMine() {
        return Blocks.CALCITE;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public List<Integer> getBaseTotalTime() {
        return List.of(5, 7, 10);
    }
}
