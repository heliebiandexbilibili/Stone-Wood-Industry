package com.ind.mod.item;

import com.google.common.collect.ImmutableMap;
import com.ind.mod.materials.ModArmorMaterials;
import net.minecraft.block.Block;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class WoodArmorItem extends ArmorItem {
    private static final Map<ArmorMaterial, List<Block>> BLOCK_LIST = (new ImmutableMap.Builder<ArmorMaterial, List<Block>>()
            .put(ModArmorMaterials.CHERRY, Arrays.asList(
                    Blocks.CHERRY_PLANKS,
                    Blocks.CHERRY_LOG,
                    Blocks.CHERRY_LEAVES,
                    Blocks.CHERRY_WOOD,
                    Blocks.STRIPPED_CHERRY_LOG,
                    Blocks.STRIPPED_CHERRY_WOOD
            ))
            .put(ModArmorMaterials.OAK, Arrays.asList(
                    Blocks.OAK_PLANKS,
                    Blocks.OAK_LOG,
                    Blocks.OAK_LEAVES,
                    Blocks.OAK_WOOD,
                    Blocks.STRIPPED_OAK_LOG,
                    Blocks.STRIPPED_OAK_WOOD
            ))
            .put(ModArmorMaterials.DARK_OAK, Arrays.asList(
                    Blocks.DARK_OAK_PLANKS,
                    Blocks.DARK_OAK_LOG,
                    Blocks.DARK_OAK_LEAVES,
                    Blocks.DARK_OAK_WOOD,
                    Blocks.STRIPPED_DARK_OAK_LOG,
                    Blocks.STRIPPED_DARK_OAK_WOOD
            ))
            .put(ModArmorMaterials.BAMBOO, Arrays.asList(
                    Blocks.BAMBOO_PLANKS
            ))
            .put(ModArmorMaterials.JUNGLE, Arrays.asList(
                    Blocks.JUNGLE_PLANKS,
                    Blocks.JUNGLE_LOG,
                    Blocks.JUNGLE_LEAVES,
                    Blocks.JUNGLE_WOOD,
                    Blocks.STRIPPED_JUNGLE_LOG,
                    Blocks.STRIPPED_JUNGLE_WOOD
            ))
            .put(ModArmorMaterials.ACACIA, Arrays.asList(
                    Blocks.ACACIA_PLANKS,
                    Blocks.ACACIA_LOG,
                    Blocks.ACACIA_LEAVES,
                    Blocks.ACACIA_WOOD,
                    Blocks.STRIPPED_ACACIA_LOG,
                    Blocks.STRIPPED_ACACIA_WOOD
            ))
            .put(ModArmorMaterials.BIRCH, Arrays.asList(
                    Blocks.BIRCH_PLANKS,
                    Blocks.BIRCH_LOG,
                    Blocks.BIRCH_LEAVES,
                    Blocks.BIRCH_WOOD,
                    Blocks.STRIPPED_BIRCH_LOG,
                    Blocks.STRIPPED_BIRCH_WOOD
            ))
            .put(ModArmorMaterials.SPRUCE, Arrays.asList(
                    Blocks.SPRUCE_PLANKS,
                    Blocks.SPRUCE_LOG,
                    Blocks.SPRUCE_LEAVES,
                    Blocks.SPRUCE_WOOD,
                    Blocks.STRIPPED_SPRUCE_LOG,
                    Blocks.STRIPPED_SPRUCE_WOOD
            ))
            .put(ModArmorMaterials.MANGROVE, Arrays.asList(
                    Blocks.MANGROVE_PLANKS,
                    Blocks.MANGROVE_LOG,
                    Blocks.MANGROVE_LEAVES,
                    Blocks.MANGROVE_WOOD,
                    Blocks.STRIPPED_MANGROVE_LOG,
                    Blocks.STRIPPED_MANGROVE_WOOD
            ))
            .put(ModArmorMaterials.CRIMSON, Arrays.asList(
                    Blocks.CRIMSON_PLANKS,
                    Blocks.CRIMSON_STEM,
                    Blocks.NETHER_WART_BLOCK,
                    Blocks.CRIMSON_HYPHAE,
                    Blocks.STRIPPED_CRIMSON_STEM,
                    Blocks.STRIPPED_CRIMSON_HYPHAE
            ))
            .put(ModArmorMaterials.WARPED, Arrays.asList(
                    Blocks.WARPED_PLANKS,
                    Blocks.WARPED_STEM,
                    Blocks.WARPED_WART_BLOCK,
                    Blocks.WARPED_HYPHAE,
                    Blocks.STRIPPED_WARPED_STEM,
                    Blocks.STRIPPED_WARPED_HYPHAE
            ))
            .build());
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

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if(!world.isClient){
            if(entity instanceof PlayerEntity player && hasFullSuitArmorOn(player)){
                evaluateArmorEffects(player);
            }
        }
        super.inventoryTick(stack, world, entity, slot, selected);
    }

    private boolean hasFullSuitArmorOn(PlayerEntity player){
        for(int i=0;i<=3;i++){
            if(player.getInventory().getArmorStack(i).isEmpty()){
                return false;
            }
        }
        return true;
    }

    private void evaluateArmorEffects(PlayerEntity player) {
        for(Map.Entry<ArmorMaterial, List<Block>> entry: BLOCK_LIST.entrySet()){
            ArmorMaterial armorMaterial = entry.getKey();
            List<Block> BlockList = entry.getValue();

            if(hasCurrectArmorOn(armorMaterial, player)){
                for (Block block:BlockList){
                    if (player.getWorld().getBlockState(player.getBlockPos().down()).getBlock() == block && player.canHaveStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH))){
                        player.addStatusEffect(new StatusEffectInstance(StatusEffects.INSTANT_HEALTH, 20, 0));
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
}
