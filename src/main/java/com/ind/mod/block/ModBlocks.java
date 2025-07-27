package com.ind.mod.block;

import com.ind.mod.StoneWoodIndustry;
import com.ind.mod.item.ModItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;

public class ModBlocks {
    public static void registerModBlocks(){
        StoneWoodIndustry.LOGGER.info("Stone&Wood Industry>>>Registering Mod Blocks!");
    }

    public static Block register(String name, Block block){
        ModItems.register(name, new BlockItem(block, new FabricItemSettings()));
        return Registry.register(Registries.BLOCK, new Identifier(StoneWoodIndustry.MOD_ID, name), block);
    }

    public static Block SMOOTH_STONE_PRESSURE_PLATE = register("smooth_stone_pressure_plate", new PressurePlateBlock(PressurePlateBlock.ActivationRule.EVERYTHING, AbstractBlock.Settings.create(), BlockSetType.STONE));
    public static Block SMOOTH_STONE_STAIRS = register("smooth_stone_stairs", new StairsBlock(Blocks.SMOOTH_STONE.getDefaultState(), AbstractBlock.Settings.copy(Blocks.SMOOTH_STONE)));
    public static Block SMOOTH_STONE_BUTTON = register("smooth_stone_button", Blocks.createStoneButtonBlock());
}
