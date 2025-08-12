package com.ind.mod.item;

import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ToolMaterial;

import java.util.List;
import java.util.Random;

public class BirchPickaxe extends WoodPickaxeItem{
    public BirchPickaxe(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public StatusEffect getMaxLevelEffect() {
        return StatusEffects.REGENERATION;
    }

    @Override
    public Block getBlockToMine() {
        return Blocks.ANDESITE;
    }

    @Override
    public int getMaxLevel() {
        return 3;
    }

    @Override
    public List<Integer> getBaseTotalTime() {
        Random random = new Random();
        return List.of(5, 10, 15);
    }
}
