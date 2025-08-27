package com.ind.mod.block;

import com.ind.mod.StoneWoodIndustry;
import com.ind.mod.item.ModItems;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.block.*;
import net.minecraft.item.BlockItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.Direction;

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
    public static Block OBSIDIAN_STAIRS = register("obsidian_stairs", new StairsBlock(Blocks.OBSIDIAN.getDefaultState(), AbstractBlock.Settings.copy(Blocks.OBSIDIAN)));
    public static Block OBSIDIAN_SLAB = register("obsidian_slab", new SlabBlock(AbstractBlock.Settings.copy(Blocks.OBSIDIAN)));

    public static Block PETRIFIED_OAK_LOG = register("petrified_oak_log", new PillarBlock(AbstractBlock.Settings.create().mapColor((state) -> state.get(PillarBlock.AXIS) == Direction.Axis.Y ? MapColor.OAK_TAN : MapColor.SPRUCE_BROWN).strength(2.0F, 6.0f).sounds(BlockSoundGroup.WOOD).requiresTool()));
    public static Block STRIPPED_PETRIFIED_OAK_LOG = register("stripped_petrified_oak_log", new PillarBlock(AbstractBlock.Settings.create().mapColor((state) -> MapColor.OAK_TAN).strength(2.0F, 6.0f).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static Block PETRIFIED_OAK_PLANKS = register("petrified_oak_planks", new Block(AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).strength(2.0F, 6.0F).sounds(BlockSoundGroup.STONE).requiresTool()));
    public static Block PETRIFIED_OAK_STAIRS = register("petrified_oak_stairs", new StairsBlock(PETRIFIED_OAK_PLANKS.getDefaultState(), AbstractBlock.Settings.create().mapColor(MapColor.OAK_TAN).strength(2.0f, 6.0f).sounds(BlockSoundGroup.STONE).requiresTool()));
}
