package com.ind.mod.item;

import com.ind.mod.StoneWoodIndustry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public abstract class WoodPickaxeItem extends PickaxeItem {
    private static List<Item> REQUIRED_PICKAXES = null;


    private static List<Item> getRequiredPickaxes() {
        if (REQUIRED_PICKAXES == null) {
            synchronized (WoodPickaxeItem.class) {
                if (REQUIRED_PICKAXES == null) {
                    List<Item> temp = new ArrayList<>();
                    try {

                        addPickaxeIfRegistered(temp, ModItems.OAK_PICKAXE);
                        addPickaxeIfRegistered(temp, ModItems.SPRUCE_PICKAXE);
                        addPickaxeIfRegistered(temp, ModItems.BIRCH_PICKAXE);
                        addPickaxeIfRegistered(temp, ModItems.JUNGLE_PICKAXE);
                        addPickaxeIfRegistered(temp, ModItems.ACACIA_PICKAXE);
                        addPickaxeIfRegistered(temp, ModItems.MANGROVE_PICKAXE);
                        addPickaxeIfRegistered(temp, ModItems.DARK_OAK_PICKAXE);
                        addPickaxeIfRegistered(temp, ModItems.CHERRY_PICKAXE);
                        addPickaxeIfRegistered(temp, ModItems.BAMBOO_PICKAXE);
                        addPickaxeIfRegistered(temp, ModItems.CRIMSON_PICKAXE);
                        addPickaxeIfRegistered(temp, ModItems.WARPED_PICKAXE);

                        if (temp.isEmpty()) {
                            StoneWoodIndustry.LOGGER.error("没有有效的镐子类型被注册！");
                        }
                    } catch (Exception e) {
                        StoneWoodIndustry.LOGGER.error("初始化镐子列表失败", e);
                    }
                    REQUIRED_PICKAXES = Collections.unmodifiableList(temp);
                }
            }
        }
        return REQUIRED_PICKAXES;
    }


    private static void addPickaxeIfRegistered(List<Item> list, Item pickaxe) {
        if (pickaxe != null) {
            list.add(pickaxe);
        } else {
            StoneWoodIndustry.LOGGER.warn("检测到未注册的镐子: " + pickaxe);
        }
    }

    public int getTotalTimePerLevel(int level){
        return getBaseTotalTime().get(level);
    }

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
            livingEntity.addStatusEffect(new StatusEffectInstance(
                    getMaxLevelEffect(),
                    100,
                    0,
                    true,
                    false
            ));
        }
        if (world.isClient || !(entity instanceof PlayerEntity)) return;

        PlayerEntity player = (PlayerEntity) entity;

        int craftableAmount = calculateCraftableAmount(player);

        if (craftableAmount > 0) {
            bulkCraftUltimatePickaxes(player, craftableAmount);
        }
    }
    private int calculateCraftableAmount(PlayerEntity player) {

        int netherStars = player.getInventory().count(Items.NETHER_STAR);

        int minPickaxes = getRequiredPickaxes().stream()
                .mapToInt(type -> countLevel10Pickaxes(player, type))
                .min()
                .orElse(0);

        return Math.min(netherStars, minPickaxes);
    }

    private int countLevel10Pickaxes(PlayerEntity player, Item pickaxeType) {
        int count = 0;
        for (int i = 0; i < player.getInventory().size(); i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if (stack.isOf(pickaxeType) && isLevel10Pickaxe(stack)) {
                count++;
            }
        }
        return count;
    }

    private boolean isLevel10Pickaxe(ItemStack stack) {
        return stack.hasNbt() &&
                stack.getNbt().contains("lvl") &&
                stack.getNbt().getInt("lvl") >= 10;
    }

    private void bulkCraftUltimatePickaxes(PlayerEntity player, int amount) {
        removeMaterials(player, amount);
        ItemStack result = new ItemStack(ModItems.ULTIMATE_WOOD_PICKAXE, amount);
        if (!player.getInventory().insertStack(result)) {
            player.dropItem(result, false);
        }
        player.sendMessage(Text.literal("§a成功合成终极木镐！"), false);
        player.playSound(SoundEvents.ENTITY_PLAYER_LEVELUP, 1.0f, 1.0f);
    }

    private void removeMaterials(PlayerEntity player, int amount) {
        removeItems(player, Items.NETHER_STAR, amount);

        for (Item pickaxe : getRequiredPickaxes()) {
            removeLevel10Pickaxes(player, pickaxe, amount);
        }
    }

    private void removeItems(PlayerEntity player, Item item, int amount) {
        int remaining = amount;
        for (int i = 0; i < player.getInventory().size() && remaining > 0; i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if (stack.isOf(item)) {
                int remove = Math.min(stack.getCount(), remaining);
                player.getInventory().removeStack(i, remove);
                remaining -= remove;
            }
        }
    }

    private void removeLevel10Pickaxes(PlayerEntity player, Item pickaxe, int amount) {
        int remaining = amount;
        for (int i = 0; i < player.getInventory().size() && remaining > 0; i++) {
            ItemStack stack = player.getInventory().getStack(i);
            if (stack.isOf(pickaxe) && isLevel10Pickaxe(stack)) {
                int remove = Math.min(stack.getCount(), remaining);
                player.getInventory().removeStack(i, remove);
                remaining -= remove;
            }
        }
    }

    public boolean isMaxLevel(ItemStack stack) {
        return getLevel(stack) >= this.getMaxLevel();
    }

    public abstract StatusEffect getMaxLevelEffect();

    public abstract Block getBlockToMine();

    public abstract int getMaxLevel();

    public void putIntNbtIfNull(NbtCompound nbt, String key, int value){
        if(!nbt.contains(key)){
            nbt.putInt(key, value);
        }
    }

    public void putBoolNbtIfNull(NbtCompound nbt, String key, boolean bool){
        if(!nbt.contains(key)){
            nbt.putBoolean(key, bool);
        }
    }

    private void initNbtIfNeeded(ItemStack stack) {
        if (!stack.hasNbt()) {
            Random random = new Random();
            NbtCompound nbt = stack.getOrCreateNbt();
            putIntNbtIfNull(nbt, "lvl", 0);
            putIntNbtIfNull(nbt, "mt", 0);
            putBoolNbtIfNull(nbt, "hasNotifiedMaxLevel", false);
        }
    }

    public abstract List<Integer> getBaseTotalTime();

    @Override
    public boolean postMine(ItemStack stack, World world, BlockState state, BlockPos pos, LivingEntity miner) {
        if (!(miner instanceof PlayerEntity player)) {
            return super.postMine(stack, world, state, pos, miner);
        }

        initNbtIfNeeded(stack);
        int currentLevel = getLevel(stack);
        if (currentLevel >= getMaxLevel()) {
            if (!stack.getNbt().getBoolean("hasNotifiedMaxLevel")) {
                player.sendMessage(Text.of("§6✧ 你的镐子已经满级了！"), false);
                stack.getNbt().putBoolean("hasNotifiedMaxLevel", true);
            }
            return super.postMine(stack, world, state, pos, miner);
        }

        Block requiredBlock = getBlockToMine();
        if (state.getBlock() != requiredBlock) {
            return super.postMine(stack, world, state, pos, miner);
        }

        NbtCompound nbt = stack.getNbt();
        int mineTime = nbt.getInt("mt") + 1;
        nbt.putInt("mt", mineTime);
        player.sendMessage(Text.literal(
                "§a✔ 进度: §e" + mineTime + "§a/§b" + getTotalTimePerLevel(currentLevel) +
                        " §7(§a" + requiredBlock.getName().getString() + "§7)"
        ), true);

        if (mineTime >= getTotalTimePerLevel(currentLevel)) {
            int newLevel = currentLevel + 1;
            nbt.putInt("lvl", newLevel);
            nbt.putInt("mt", 0);
            if(newLevel >= 10){
                player.sendMessage(Text.of("§6✧ 你的镐子已经满级了！"), false);
                nbt.putBoolean("hasNotifiedMaxLevel", true);
                return super.postMine(stack, world, state, pos, miner);
            }
            player.sendMessage(Text.of("§6✧ 镐子升级到 " + newLevel + " 级！"), false);
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
        if (level < getMaxLevel() && state.getBlock() == getBlockToMine()) {
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
        if (level < getMaxLevel()) {
            Block target = getBlockToMine();
            tooltip.add(Text.literal("§7现在等级: §e" + level + "§7/§b" + getMaxLevel()));
            tooltip.add(Text.literal("§7目标: §a" + target.getName().getString()));
            tooltip.add(Text.literal("§7进度: §e" + stack.getOrCreateNbt().getInt("mt") + "§7/§b" + getTotalTimePerLevel(level)));
        } else {
            tooltip.add(Text.literal("§6✦ 已满级"));
            tooltip.add(Text.literal("§2✦ 在主手时："));
            tooltip.add(Text.literal("§7   获得" + getMaxLevelEffect().getName().getString() + "I效果"));
        }
    }

    @Override
    public float getMiningSpeedMultiplier(ItemStack stack, BlockState state) {
        int level = getLevel(stack);
        if (level < getMaxLevel() && state.getBlock() == getBlockToMine()) {
            return ToolMaterials.WOOD.getMiningSpeedMultiplier();
        }
        return super.getMiningSpeedMultiplier(stack, state);
    }
}