package com.ind.mod.item;

import com.ind.mod.StoneWoodIndustry;
import com.ind.mod.enchantment.ModEnchantment;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.enchantment.EnchantmentHelper;
import net.minecraft.enchantment.Enchantments;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffect;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.tag.BlockTags;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundEvents;
import net.minecraft.text.Text;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.RaycastContext;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class WoodPickaxeItem extends PickaxeItem {
    private static volatile List<Item> REQUIRED_PICKAXES = null;
    // 修改为粒子效果的伤害范围
    private static final int DAMAGE_RANGE = 20;
    private static final float DAMAGE_AMOUNT = 10.0f;

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
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack stack = user.getStackInHand(hand);
        int pickaxeLevel = getLevel(stack);

        // 检查是否潜行并右键
        if (user.isSneaking()) {
            // 0级不破坏方块
            if (pickaxeLevel == 0) {
                return TypedActionResult.fail(stack);
            }

            // 发射粒子效果并对沿途实体造成伤害
            if (!world.isClient) {
                HitResult result = shootParticleBeam((ServerWorld) world, user, stack, DAMAGE_RANGE);
                if(result.getType() == HitResult.Type.BLOCK && canMineBlock(world, new BlockPos((int) result.getPos().x, (int) result.getPos().y, (int) result.getPos().z), user)) stack.damage(5, user, (p) -> p.sendToolBreakStatus(hand));
                user.getItemCooldownManager().set(this, 20 - EnchantmentHelper.getLevel(Enchantments.EFFICIENCY, stack)); // 1秒冷却

                // 播放发射声音
                user.playSound(SoundEvents.ENTITY_ENDER_DRAGON_SHOOT, 1.0F, 1.0F);
            } else {
                // 客户端也播放声音
                user.playSound(SoundEvents.ENTITY_ENDER_DRAGON_SHOOT, 1.0F, 1.0F);
            }
            return TypedActionResult.success(stack);
        }

        return TypedActionResult.pass(stack);
    }

    private HitResult shootParticleBeam(ServerWorld world, PlayerEntity player, ItemStack pickaxeStack, int range) {
        Vec3d startPos = player.getEyePos();
        Vec3d lookVec = player.getRotationVec(1.0F);
        Vec3d endPos = startPos.add(lookVec.multiply(range));

        // 射线检测，找出击中的方块
        BlockHitResult hitResult = world.raycast(new RaycastContext(
                startPos, endPos,
                RaycastContext.ShapeType.COLLIDER,
                RaycastContext.FluidHandling.NONE,
                player
        ));

        // 创建粒子效果路径
        createParticlePath(world, startPos, hitResult.getPos());

        // 对路径上的所有实体造成伤害
        damageEntitiesAlongPath(world, player, startPos, hitResult.getPos());

        // 破坏路径上的方块（根据镐子等级）
        if (hitResult.getType() == HitResult.Type.BLOCK) {
            destroyBlocksAlongPath(world, player, pickaxeStack, hitResult);
        }

        return hitResult;
    }

    private void destroyBlocksAlongPath(ServerWorld world, PlayerEntity player, ItemStack pickaxeStack, BlockHitResult hitResult) {
        int pickaxeLevel = getLevel(pickaxeStack);
        if (pickaxeLevel <= 0) return; // 0级不破坏方块

        Vec3d lookVec = player.getRotationVec(1.0F);
        BlockPos hitPos = hitResult.getBlockPos();

        // 根据镐子等级决定向前挖掘的层数

        // 沿着视线方向向前挖掘方块
        destroyBlocksAlongSightLine(world, player, hitPos, lookVec, pickaxeLevel);
    }

    private void destroyBlocksAlongSightLine(ServerWorld world, PlayerEntity player, BlockPos startPos, Vec3d lookVec, int layers) {
        // 横向3x3范围

        // 将视线向量转换为主要方向（用于确定横向偏移）
        Direction primaryDirection = Direction.getFacing(lookVec.x, lookVec.y, lookVec.z);

        for (int distance = 0; distance <= layers - 1; distance++) {
            // 计算当前距离的目标中心点
            Vec3d centerOffset = lookVec.multiply(distance);
            BlockPos centerPos = startPos.add(
                    (int) Math.round(centerOffset.x),
                    (int) Math.round(centerOffset.y),
                    (int) Math.round(centerOffset.z)
            );

            // 破坏当前距离的3x3区域
            destroyNxNArea(world, player, centerPos, primaryDirection, 3 + EnchantmentHelper.getLevel(ModEnchantment.EXCAVATION_RANGE, player.getMainHandStack()));
        }
    }

    private void destroyNxNArea(ServerWorld world, PlayerEntity player, BlockPos centerPos, Direction direction, int width) {
        // 根据主要方向确定横向平面
        for (int x = -width / 2; x <= width / 2; x++) {
            for (int y = -width / 2; y <= width / 2; y++) {
                BlockPos targetPos = calculateLateralOffset(centerPos, direction, x, y);

                if (canMineBlock(world, targetPos, player)) {
                    // 破坏方块并掉落物品
                    world.breakBlock(targetPos, true, player);

                    // 添加破坏粒子效果
                    world.spawnParticles(
                            ParticleTypes.CRIT,
                            targetPos.getX() + 0.5, targetPos.getY() + 0.5, targetPos.getZ() + 0.5,
                            3,
                            0.2, 0.2, 0.2,
                            0.05
                    );
                }
            }
        }
    }

    private BlockPos calculateLateralOffset(BlockPos center, Direction direction, int xOffset, int yOffset) {
        // 根据主要方向计算横向偏移
        return switch (direction) {
            case UP, DOWN -> center.add(xOffset, 0, yOffset);
            case NORTH, SOUTH -> center.add(xOffset, yOffset, 0);
            case EAST, WEST -> center.add(0, yOffset, xOffset);
        };
    }

    private boolean canMineBlock(World world, BlockPos pos, PlayerEntity player) {
        BlockState state = world.getBlockState(pos);
        return (!state.isIn(BlockTags.NEEDS_DIAMOND_TOOL) && !state.isIn(BlockTags.NEEDS_IRON_TOOL) && !state.isIn(BlockTags.NEEDS_STONE_TOOL) && state.getBlock().getHardness() >= 0) || player.isCreative();
    }


    private void createParticlePath(ServerWorld world, Vec3d start, Vec3d end) {
        double distance = start.distanceTo(end);
        int particleCount = (int) (distance * 2);

        for (int i = 0; i < particleCount; i++) {
            double progress = (double) i / particleCount;
            Vec3d particlePos = start.lerp(end, progress);

            // 创建巨大的粒子效果
            world.spawnParticles(
                    ParticleTypes.DRAGON_BREATH,
                    particlePos.x, particlePos.y, particlePos.z,
                    5,
                    0.5, 0.5, 0.5,
                    0.1
            );

            // 添加额外的粒子效果增强视觉效果
            world.spawnParticles(
                    ParticleTypes.ELECTRIC_SPARK,
                    particlePos.x, particlePos.y, particlePos.z,
                    3,
                    0.3, 0.3, 0.3,
                    0.05
            );
        }
    }

    private void damageEntitiesAlongPath(ServerWorld world, PlayerEntity player, Vec3d start, Vec3d end) {
        // 计算路径的边界框
        Box pathBox = new Box(start, end).expand(2.0);

        // 获取路径上的所有实体
        List<LivingEntity> entities = world.getEntitiesByClass(
                LivingEntity.class,
                pathBox,
                entity -> entity != player && entity.isAlive()
        );

        for (LivingEntity entity : entities) {
            // 检查实体是否在路径附近
            if (isEntityNearPath(entity, start, end, 2.0)) {
                entity.damage(player.getDamageSources().playerAttack(player), DAMAGE_AMOUNT);

                // 添加击退效果
                Vec3d knockbackDir = entity.getPos().subtract(player.getPos()).normalize();
                entity.addVelocity(knockbackDir.x * 0.5, 0.5, knockbackDir.z * 0.5);

                // 添加粒子效果在命中点
                world.spawnParticles(
                        ParticleTypes.EXPLOSION,
                        entity.getX(), entity.getY() + entity.getHeight() / 2, entity.getZ(),
                        3,
                        0.5, 0.5, 0.5,
                        0.1
                );
            }
        }
    }

    private boolean isEntityNearPath(LivingEntity entity, Vec3d start, Vec3d end, double radius) {
        Vec3d entityPos = entity.getPos().add(0, entity.getHeight() / 2, 0);
        return distanceToLineSegment(entityPos, start, end) <= radius;
    }

    private double distanceToLineSegment(Vec3d point, Vec3d lineStart, Vec3d lineEnd) {
        Vec3d lineVec = lineEnd.subtract(lineStart);
        double lineLength = lineVec.length();
        Vec3d lineDir = lineVec.normalize();

        Vec3d pointVec = point.subtract(lineStart);
        double projection = pointVec.dotProduct(lineDir);

        if (projection < 0) {
            return point.distanceTo(lineStart);
        } else if (projection > lineLength) {
            return point.distanceTo(lineEnd);
        } else {
            Vec3d closestPoint = lineStart.add(lineDir.multiply(projection));
            return point.distanceTo(closestPoint);
        }
    }

    // 以下保持原有的所有方法不变...
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
        if (world.isClient || !(entity instanceof PlayerEntity player)) return;

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

        // 修改功能提示
        tooltip.add(Text.literal("§5✦ 潜行右键："));
        tooltip.add(Text.literal("§7   发射能量波对前方" + DAMAGE_RANGE + "格内实体造成" + DAMAGE_AMOUNT + "点伤害"));
        tooltip.add(Text.literal("§7   破坏前方" + (level > 0 ? level : "0") + "层" + (3 + EnchantmentHelper.getLevel(ModEnchantment.EXCAVATION_RANGE, stack)) + "x" + (3 + EnchantmentHelper.getLevel(ModEnchantment.EXCAVATION_RANGE, stack)) + "范围的方块"));
        if (level == 0) {
            tooltip.add(Text.literal("§c   需要至少1级才能破坏方块"));
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