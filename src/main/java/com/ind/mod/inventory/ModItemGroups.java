package com.ind.mod.inventory;

import com.ind.mod.StoneWoodIndustry;
import com.ind.mod.block.ModBlocks;
import com.ind.mod.item.ModItems;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

public class ModItemGroups {
     public static void register(){
         StoneWoodIndustry.LOGGER.info("STONE&WOOD INDUSTRY>>>Registering MOD Itemgroups!");
     }

    public static ItemGroup register(String name, ItemGroup itemGroup){
        return Registry.register(Registries.ITEM_GROUP, new Identifier(StoneWoodIndustry.MOD_ID, name), itemGroup);
    }

    public static final ItemGroup STONE_WOOD_INDUSTRY_STONE = register("stone_wood_industry_stone", FabricItemGroup.builder()
            .displayName(Text.translatable(new Identifier(StoneWoodIndustry.MOD_ID, "stone_wood_industry_stone").toTranslationKey()))
            .icon(()->new ItemStack(Items.SMOOTH_STONE))
            .entries(((displayContext, entries) -> {
                entries.add(ModItems.STONE_HELMET);
                entries.add(ModItems.STONE_CHESTPLATE);
                entries.add(ModItems.STONE_LEGGINGS);
                entries.add(ModItems.STONE_BOOTS);
                entries.add(ModItems.SMOOTH_STONE_HELMET);
                entries.add(ModItems.SMOOTH_STONE_CHESTPLATE);
                entries.add(ModItems.SMOOTH_STONE_LEGGINGS);
                entries.add(ModItems.SMOOTH_STONE_BOOTS);
                entries.add(ModItems.BLACKSTONE_HELMET);
                entries.add(ModItems.BLACKSTONE_CHESTPLATE);
                entries.add(ModItems.BLACKSTONE_LEGGINGS);
                entries.add(ModItems.BLACKSTONE_BOOTS);
                entries.add(ModItems.DEEPSLATE_HELMET);
                entries.add(ModItems.DEEPSLATE_CHESTPLATE);
                entries.add(ModItems.DEEPSLATE_LEGGINGS);
                entries.add(ModItems.DEEPSLATE_BOOTS);
                entries.add(ModItems.NETHERRACK_HELMET);
                entries.add(ModItems.NETHERRACK_CHESTPLATE);
                entries.add(ModItems.NETHERRACK_LEGGINGS);
                entries.add(ModItems.NETHERRACK_BOOTS);
                entries.add(ModItems.BASALT_HELMET);
                entries.add(ModItems.BASALT_CHESTPLATE);
                entries.add(ModItems.BASALT_LEGGINGS);
                entries.add(ModItems.BASALT_BOOTS);
                entries.add(ModItems.END_STONE_HELMET);
                entries.add(ModItems.END_STONE_CHESTPLATE);
                entries.add(ModItems.END_STONE_LEGGINGS);
                entries.add(ModItems.END_STONE_BOOTS);
                entries.add(ModItems.DIORITE_HELMET);
                entries.add(ModItems.DIORITE_CHESTPLATE);
                entries.add(ModItems.DIORITE_LEGGINGS);
                entries.add(ModItems.DIORITE_BOOTS);
                entries.add(ModItems.GRANITE_HELMET);
                entries.add(ModItems.GRANITE_CHESTPLATE);
                entries.add(ModItems.GRANITE_LEGGINGS);
                entries.add(ModItems.GRANITE_BOOTS);
                entries.add(ModItems.ANDESITE_HELMET);
                entries.add(ModItems.ANDESITE_CHESTPLATE);
                entries.add(ModItems.ANDESITE_LEGGINGS);
                entries.add(ModItems.ANDESITE_BOOTS);
                entries.add(ModItems.CRYING_OBSIDIAN_HELMET);
                entries.add(ModItems.CRYING_OBSIDIAN_CHESTPLATE);
                entries.add(ModItems.CRYING_OBSIDIAN_LEGGINGS);
                entries.add(ModItems.CRYING_OBSIDIAN_BOOTS);
                entries.add(ModItems.OBSIDIAN_HELMET);
                entries.add(ModItems.OBSIDIAN_CHESTPLATE);
                entries.add(ModItems.OBSIDIAN_LEGGINGS);
                entries.add(ModItems.OBSIDIAN_BOOTS);
                entries.add(ModItems.SMOOTH_STONE_SWORD);
                entries.add(ModItems.SMOOTH_STONE_SHOVEL);
                entries.add(ModItems.SMOOTH_STONE_PICKAXE);
                entries.add(ModItems.SMOOTH_STONE_AXE);
                entries.add(ModItems.SMOOTH_STONE_HOE);
                entries.add(Blocks.SMOOTH_STONE);
                entries.add(Blocks.SMOOTH_STONE_SLAB);
                entries.add(ModBlocks.SMOOTH_STONE_STAIRS);
                entries.add(ModBlocks.SMOOTH_STONE_PRESSURE_PLATE);
                entries.add(ModBlocks.SMOOTH_STONE_BUTTON);
            })).build());
    public static final ItemGroup STONE_WOOD_INDUSTRY_WOOD = register("stone_wood_industry_wood", FabricItemGroup.builder()
            .displayName(Text.translatable(new Identifier(StoneWoodIndustry.MOD_ID, "stone_wood_industry_wood").toTranslationKey()))
            .icon(()->new ItemStack(Items.OAK_LOG))
            .entries(((displayContext, entries) -> {
                entries.add(ModItems.OAK_HELMET);
                entries.add(ModItems.OAK_CHESTPLATE);
                entries.add(ModItems.OAK_LEGGINGS);
                entries.add(ModItems.OAK_BOOTS);
                entries.add(ModItems.SPRUCE_HELMET);
                entries.add(ModItems.SPRUCE_CHESTPLATE);
                entries.add(ModItems.SPRUCE_LEGGINGS);
                entries.add(ModItems.SPRUCE_BOOTS);
                entries.add(ModItems.BIRCH_HELMET);
                entries.add(ModItems.BIRCH_CHESTPLATE);
                entries.add(ModItems.BIRCH_LEGGINGS);
                entries.add(ModItems.BIRCH_BOOTS);
                entries.add(ModItems.ACACIA_HELMET);
                entries.add(ModItems.ACACIA_CHESTPLATE);
                entries.add(ModItems.ACACIA_LEGGINGS);
                entries.add(ModItems.ACACIA_BOOTS);
                entries.add(ModItems.JUNGLE_HELMET);
                entries.add(ModItems.JUNGLE_CHESTPLATE);
                entries.add(ModItems.JUNGLE_LEGGINGS);
                entries.add(ModItems.JUNGLE_BOOTS);
                entries.add(ModItems.DARK_OAK_HELMET);
                entries.add(ModItems.DARK_OAK_CHESTPLATE);
                entries.add(ModItems.DARK_OAK_LEGGINGS);
                entries.add(ModItems.DARK_OAK_BOOTS);
                entries.add(ModItems.MANGROVE_HELMET);
                entries.add(ModItems.MANGROVE_CHESTPLATE);
                entries.add(ModItems.MANGROVE_LEGGINGS);
                entries.add(ModItems.MANGROVE_BOOTS);
                entries.add(ModItems.CHERRY_HELMET);
                entries.add(ModItems.CHERRY_CHESTPLATE);
                entries.add(ModItems.CHERRY_LEGGINGS);
                entries.add(ModItems.CHERRY_BOOTS);
                entries.add(ModItems.WARPED_HELMET);
                entries.add(ModItems.WARPED_CHESTPLATE);
                entries.add(ModItems.WARPED_LEGGINGS);
                entries.add(ModItems.WARPED_BOOTS);
                entries.add(ModItems.CRIMSON_HELMET);
                entries.add(ModItems.CRIMSON_CHESTPLATE);
                entries.add(ModItems.CRIMSON_LEGGINGS);
                entries.add(ModItems.CRIMSON_BOOTS);
                entries.add(ModItems.BAMBOO_HELMET);
                entries.add(ModItems.BAMBOO_CHESTPLATE);
                entries.add(ModItems.BAMBOO_LEGGINGS);
                entries.add(ModItems.BAMBOO_BOOTS);
            })).build());
}
