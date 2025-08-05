package com.ind.mod.materials;

import net.fabricmc.yarn.constants.MiningLevels;
import net.minecraft.item.Items;
import net.minecraft.item.ToolMaterial;
import net.minecraft.recipe.Ingredient;

import java.util.function.Supplier;

public enum ModToolMaterials implements ToolMaterial {
    SMOOTH_STONE(MiningLevels.STONE, 800, 5.0F, 0.0F, 10, ()-> Ingredient.ofItems(Items.SMOOTH_STONE)),
    CRYING_OBSIDIAN(MiningLevels.NETHERITE, 2000, 8.0F, 0.0F, 15, ()->Ingredient.ofItems(Items.CRYING_OBSIDIAN)),
    OBSIDIAN(MiningLevels.NETHERITE, 2000, 8.0F, 0.0F, 15, ()->Ingredient.ofItems(Items.OBSIDIAN)),
    END_STONE(MiningLevels.STONE, 800, 8.0F, 0.0F, 10, ()->Ingredient.ofItems(Items.END_STONE)),
    BLACKSTONE(MiningLevels.STONE, 800, 8.0F, 0.0F, 10, ()->Ingredient.ofItems(Items.BLACKSTONE)),
    DEEPSLATE(MiningLevels.STONE, 800, 8.0F, 0.0F, 10, ()->Ingredient.ofItems(Items.DEEPSLATE)),
    NETHERRACK(MiningLevels.STONE, 800, 8.0F, 0.0F, 10, ()->Ingredient.ofItems(Items.NETHERRACK)),
    DIORITE(MiningLevels.STONE, 800, 8.0F, 0.0F, 10, ()->Ingredient.ofItems(Items.DIORITE)),
    GRANITE(MiningLevels.STONE, 800, 8.0F, 0.0F, 10, ()->Ingredient.ofItems(Items.GRANITE)),
    ANDESITE(MiningLevels.STONE, 800, 8.0F, 0.0F, 10, ()->Ingredient.ofItems(Items.ANDESITE)),
    BASALT(MiningLevels.STONE, 800, 8.0F, 0.0F, 10, ()->Ingredient.ofItems(Items.BASALT)),
    OAK(MiningLevels.WOOD, 400, 4.0F, 0.0F, 5, ()->Ingredient.ofItems(Items.OAK_PLANKS));
    private final int miningLevel;
    private final int itemDurability;
    private final float miningSpeed;
    private final float attackDamage;
    private final int enchantability;
    private final Supplier<Ingredient> repairIngredient;

    ModToolMaterials(int miningLevel, int itemDurability, float miningSpeed, float attackDamage, int enchantability, Supplier<Ingredient> repairIngredient) {
        this.miningLevel = miningLevel;
        this.itemDurability = itemDurability;
        this.miningSpeed = miningSpeed;
        this.attackDamage = attackDamage;
        this.enchantability = enchantability;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurability() {
        return this.itemDurability;
    }

    @Override
    public float getMiningSpeedMultiplier() {
        return this.miningSpeed;
    }

    @Override
    public float getAttackDamage() {
        return this.attackDamage;
    }

    @Override
    public int getMiningLevel() {
        return this.miningLevel;
    }

    @Override
    public int getEnchantability() {
        return this.enchantability;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return this.repairIngredient.get();
    }
}
