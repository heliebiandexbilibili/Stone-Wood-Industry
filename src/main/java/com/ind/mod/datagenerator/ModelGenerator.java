package com.ind.mod.datagenerator;

import com.ind.mod.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class ModelGenerator extends FabricModelProvider {
    public ModelGenerator(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(ModItems.SMOOTH_STONE_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SMOOTH_STONE_AXE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SMOOTH_STONE_HOE, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SMOOTH_STONE_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(ModItems.SMOOTH_STONE_SWORD, Models.HANDHELD);
        itemModelGenerator.register(ModItems.OAK_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.OAK_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.OAK_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.OAK_BOOTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPRUCE_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPRUCE_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPRUCE_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.SPRUCE_BOOTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.BIRCH_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.BIRCH_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BIRCH_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.BIRCH_BOOTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.ACACIA_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.ACACIA_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.ACACIA_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.ACACIA_BOOTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.JUNGLE_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.JUNGLE_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.JUNGLE_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.JUNGLE_BOOTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.DARK_OAK_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.DARK_OAK_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.DARK_OAK_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.DARK_OAK_BOOTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.MANGROVE_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.MANGROVE_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.MANGROVE_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.MANGROVE_BOOTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHERRY_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHERRY_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHERRY_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.CHERRY_BOOTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLACKSTONE_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLACKSTONE_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLACKSTONE_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.BLACKSTONE_BOOTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.DEEPSLATE_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.DEEPSLATE_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.DEEPSLATE_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.DEEPSLATE_BOOTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.BAMBOO_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.BAMBOO_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.BAMBOO_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.BAMBOO_BOOTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRIMSON_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRIMSON_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRIMSON_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.CRIMSON_BOOTS, Models.GENERATED);
        itemModelGenerator.register(ModItems.WARPED_HELMET, Models.GENERATED);
        itemModelGenerator.register(ModItems.WARPED_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(ModItems.WARPED_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(ModItems.WARPED_BOOTS, Models.GENERATED);
    }
}
