package com.ind.mod.item;

import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ToolMaterial;
import net.minecraft.item.ToolMaterials;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public abstract class WoodPickaxeItem extends PickaxeItem {
    private static final List<Block> BLOCKS_PER_LEVEL = List.of(
            Blocks.STONE,
            Blocks.COAL_ORE,
            Blocks.IRON_ORE,
            Blocks.CRIMSON_NYLIUM,
            Blocks.WARPED_NYLIUM,
            Blocks.BASALT,
            Blocks.NETHER_BRICKS,
            Blocks.BLACKSTONE,
            Blocks.END_STONE,
            Blocks.PURPUR_BLOCK
    );

    private static final int TOTAL_TIME_PER_LEVEL = 40;

    public WoodPickaxeItem(ToolMaterial material, int attackDamage, float attackSpeed, Settings settings) {
        super(material, attackDamage, attackSpeed, settings);
    }

    @Override
    public ItemStack getDefaultStack() {
        ItemStack stack = super.getDefaultStack();
        initNbtIfNeeded(stack);
        return stack;
    }

    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        if (!world.isClient &&
                selected &&
                entity instanceof LivingEntity livingEntity &&
                isMaxLevel(stack)) {

            // 施加满级效果（固定1级，持续5秒）
            livingEntity.addStatusEffect(new StatusEffectInstance(
                    getMaxLevelEffect(),
                    100, // 5秒 (20 ticks/秒)
                    0,   // 效果等级I
                    true, // 显示粒子
                    false // 不显示图标（避免UI混乱）
            ));
        }
    }

    public static boolean isMaxLevel(ItemStack stack) {
        return getLevel(stack) >= BLOCKS_PER_LEVEL.size();
    }

    public abstract StatusEffect getMaxLevelEffect();

    private void initNbtIfNeeded(ItemStack stack) {
        if (!stack.hasNbt() || !stack.getNbt().contains("lvl")) {
            NbtCompound nbt = stack.getOrCreateNbt();
            nbt.putInt("lvl", 0);
            nbt.putInt("mt", 0);
            nbt.putBoolean("hasNotifiedMaxLevel", false);
        }
    }

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!(miner instanceof PlayerEntity player)) {
            return super.postMine(stack, world, state, pos, miner);
        }

        initNbtIfNeeded(stack);
        int currentLevel = getLevel(stack);
        if (currentLevel >= BLOCKS_PER_LEVEL.size()) {
            if (!stack.getNbt().getBoolean("hasNotifiedMaxLevel")) {
                player.sendMessage(Text.of("§6✧ 你的镐子已经满级了！"), false);
                stack.getNbt().putBoolean("hasNotifiedMaxLevel", true); // 标记已提醒
            }
            return super.postMine(stack, world, state, pos, miner);
        }

        Block requiredBlock = BLOCKS_PER_LEVEL.get(currentLevel);
        if (state.getBlock() != requiredBlock) {
            return super.postMine(stack, world, state, pos, miner);
        }

        NbtCompound nbt = stack.getNbt();
        int mineTime = nbt.getInt("mt") + 1;
        nbt.putInt("mt", mineTime);
        player.sendMessage(Text.literal(
                "§a✔ 进度: §e" + mineTime + "§a/§b" + TOTAL_TIME_PER_LEVEL +
                        " §7(§a" + requiredBlock.getName().getString() + "§7)"
        ), true);

        if (mineTime >= TOTAL_TIME_PER_LEVEL) {
            int newLevel = currentLevel + 1;
            nbt.putInt("lvl", newLevel);
            nbt.putInt("mt", 0);
            if(newLevel >= 10){
                player.sendMessage(Text.of("§6✧ 你的镐子已经满级了！"), false);
                nbt.putBoolean("hasNotifiedMaxLevel", true); // 标记已提醒
                return super.postMine(stack, world, state, pos, miner);
            }
            player.sendMessage(Text.of("§6✧ 镐子升级到 " + newLevel + " 级！§2(下一目标：" + BLOCKS_PER_LEVEL.get(newLevel).getName().getString() + ")"), false);
            player.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 1.0F, 1.0F);
            player.addExperience(newLevel * 10);
        }

        return super.postMine(stack, world, state, pos, miner);
    }

    public static int getLevel(ItemStack stack) {
        if (!stack.hasNbt()) {
            return 0;
        }
        NbtCompound nbt = stack.getNbt();
        return nbt.contains("lvl") ? nbt.getInt("lvl") : 0;
    }

    @Override
    public boolean isSuitableFor(ItemStack stack, BlockState state) {
        int level = getLevel(stack);
        if (level < BLOCKS_PER_LEVEL.size() && state.getBlock() == BLOCKS_PER_LEVEL.get(level)) {
            return true;
        }
        if (level >= 3) {
            return state.isIn(BlockTags.PICKAXE_MINEABLE);
        }
        return super.isSuitableFor(stack, state);
    }

    @Override
    public void appendTooltip(ItemStack stack, World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        int level = getLevel(stack);
        if (level < BLOCKS_PER_LEVEL.size()) {
            Block target = BLOCKS_PER_LEVEL.get(level);
            tooltip.add(Text.literal("§7现在等级: §e" + level + "§7/§b" + BLOCKS_PER_LEVEL.size()));
            tooltip.add(Text.literal("§7目标: §a" + target.getName().getString()));
            tooltip.add(Text.literal("§7进度: §e" + stack.getOrCreateNbt().getInt("mt") + "§7/§b" + TOTAL_TIME_PER_LEVEL));
        } else {
            tooltip.add(Text.literal("§6✦ 已满级"));
            tooltip.add(Text.literal("§2✦ 在主手时："));
            tooltip.add(Text.literal("§7   获得" + getMaxLevelEffect().getName().getString() + "I效果"));
        }
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        int level = getLevel(stack);

        if (level < BLOCKS_PER_LEVEL.size() && state.getBlock() == BLOCKS_PER_LEVEL.get(level)) {
            return ToolMaterials.WOOD.getMiningSpeedMultiplier();
        }

        return super.getMiningSpeedMultiplier(stack, state);
    }
}