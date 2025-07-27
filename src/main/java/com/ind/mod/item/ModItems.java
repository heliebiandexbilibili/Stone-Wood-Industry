package com.ind.mod.item;

import com.ind.mod.StoneWoodIndustry;
import com.ind.mod.materials.ModArmorMaterials;
import com.ind.mod.materials.ModToolMaterials;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModItems {
    public static void registerModItems(){
        StoneWoodIndustry.LOGGER.info("Stone&Wood Industry>>>Registering Mod Items!");
    }

    public static Item register(String name, Item item){
        return Registry.register(Registries.ITEM, new Identifier(StoneWoodIndustry.MOD_ID, name), item);
    }
    // FUCK MOJANG
    public static final Item STONE_HELMET = register("stone_helmet", new StoneArmorItem(ModArmorMaterials.STONE, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item STONE_CHESTPLATE = register("stone_chestplate", new StoneArmorItem(ModArmorMaterials.STONE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item STONE_LEGGINGS = register("stone_leggings", new StoneArmorItem(ModArmorMaterials.STONE, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item STONE_BOOTS = register("stone_boots", new StoneArmorItem(ModArmorMaterials.STONE, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item SMOOTH_STONE_HELMET = register("smooth_stone_helmet", new SmoothStoneArmorItem(ModArmorMaterials.SMOOTH_STONE, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item SMOOTH_STONE_CHESTPLATE = register("smooth_stone_chestplate", new SmoothStoneArmorItem(ModArmorMaterials.SMOOTH_STONE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item SMOOTH_STONE_LEGGINGS = register("smooth_stone_leggings", new SmoothStoneArmorItem(ModArmorMaterials.SMOOTH_STONE, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item SMOOTH_STONE_BOOTS = register("smooth_stone_boots", new SmoothStoneArmorItem(ModArmorMaterials.SMOOTH_STONE, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item SMOOTH_STONE_PICKAXE = register("smooth_stone_pickaxe", new PickaxeItem(ModToolMaterials.SMOOTH_STONE, 2, 3.0F, new FabricItemSettings()));
    public static final Item SMOOTH_STONE_SWORD = register("smooth_stone_sword", new PickaxeItem(ModToolMaterials.SMOOTH_STONE, 7, 3.0F, new FabricItemSettings()));
    public static final Item SMOOTH_STONE_HOE = register("smooth_stone_hoe", new PickaxeItem(ModToolMaterials.SMOOTH_STONE, 2, 3.0F, new FabricItemSettings()));
    public static final Item SMOOTH_STONE_AXE = register("smooth_stone_axe", new PickaxeItem(ModToolMaterials.SMOOTH_STONE, 8, 3.0F, new FabricItemSettings()));
    public static final Item SMOOTH_STONE_SHOVEL = register("smooth_stone_shovel", new PickaxeItem(ModToolMaterials.SMOOTH_STONE, 2, 3.0F, new FabricItemSettings()));
    public static final Item OAK_HELMET = register("oak_helmet", new WoodArmorItem(ModArmorMaterials.OAK, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item OAK_CHESTPLATE = register("oak_chestplate", new WoodArmorItem(ModArmorMaterials.OAK, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item OAK_LEGGINGS = register("oak_leggings", new WoodArmorItem(ModArmorMaterials.OAK, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item OAK_BOOTS = register("oak_boots", new WoodArmorItem(ModArmorMaterials.OAK, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item SPRUCE_HELMET = register("spruce_helmet", new WoodArmorItem(ModArmorMaterials.SPRUCE, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item SPRUCE_CHESTPLATE = register("spruce_chestplate", new WoodArmorItem(ModArmorMaterials.SPRUCE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item SPRUCE_LEGGINGS = register("spruce_leggings", new WoodArmorItem(ModArmorMaterials.SPRUCE, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item SPRUCE_BOOTS = register("spruce_boots", new WoodArmorItem(ModArmorMaterials.SPRUCE, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item BIRCH_HELMET = register("birch_helmet", new WoodArmorItem(ModArmorMaterials.BIRCH, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item BIRCH_CHESTPLATE = register("birch_chestplate", new WoodArmorItem(ModArmorMaterials.BIRCH, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item BIRCH_LEGGINGS = register("birch_leggings", new WoodArmorItem(ModArmorMaterials.BIRCH, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item BIRCH_BOOTS = register("birch_boots", new WoodArmorItem(ModArmorMaterials.BIRCH, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item JUNGLE_HELMET = register("jungle_helmet", new WoodArmorItem(ModArmorMaterials.JUNGLE, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item JUNGLE_CHESTPLATE = register("jungle_chestplate", new WoodArmorItem(ModArmorMaterials.JUNGLE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item JUNGLE_LEGGINGS = register("jungle_leggings", new WoodArmorItem(ModArmorMaterials.JUNGLE, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item JUNGLE_BOOTS = register("jungle_boots", new WoodArmorItem(ModArmorMaterials.JUNGLE, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item ACACIA_HELMET = register("acacia_helmet", new WoodArmorItem(ModArmorMaterials.ACACIA, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item ACACIA_CHESTPLATE = register("acacia_chestplate", new WoodArmorItem(ModArmorMaterials.ACACIA, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item ACACIA_LEGGINGS = register("acacia_leggings", new WoodArmorItem(ModArmorMaterials.ACACIA, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item ACACIA_BOOTS = register("acacia_boots", new WoodArmorItem(ModArmorMaterials.ACACIA, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item DARK_OAK_HELMET = register("dark_oak_helmet", new WoodArmorItem(ModArmorMaterials.DARK_OAK, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item DARK_OAK_CHESTPLATE = register("dark_oak_chestplate", new WoodArmorItem(ModArmorMaterials.DARK_OAK, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item DARK_OAK_LEGGINGS = register("dark_oak_leggings", new WoodArmorItem(ModArmorMaterials.DARK_OAK, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item DARK_OAK_BOOTS = register("dark_oak_boots", new WoodArmorItem(ModArmorMaterials.DARK_OAK, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item MANGROVE_HELMET = register("mangrove_helmet", new WoodArmorItem(ModArmorMaterials.MANGROVE, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item MANGROVE_CHESTPLATE = register("mangrove_chestplate", new WoodArmorItem(ModArmorMaterials.MANGROVE, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item MANGROVE_LEGGINGS = register("mangrove_leggings", new WoodArmorItem(ModArmorMaterials.MANGROVE, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item MANGROVE_BOOTS = register("mangrove_boots", new WoodArmorItem(ModArmorMaterials.MANGROVE, ArmorItem.Type.BOOTS, new FabricItemSettings()));
    public static final Item CHERRY_HELMET = register("cherry_helmet", new WoodArmorItem(ModArmorMaterials.CHERRY, ArmorItem.Type.HELMET, new FabricItemSettings()));
    public static final Item CHERRY_CHESTPLATE = register("cherry_chestplate", new WoodArmorItem(ModArmorMaterials.CHERRY, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    public static final Item CHERRY_LEGGINGS = register("cherry_leggings", new WoodArmorItem(ModArmorMaterials.CHERRY, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    public static final Item CHERRY_BOOTS = register("cherry_boots", new WoodArmorItem(ModArmorMaterials.CHERRY, ArmorItem.Type.BOOTS, new FabricItemSettings()));
}
