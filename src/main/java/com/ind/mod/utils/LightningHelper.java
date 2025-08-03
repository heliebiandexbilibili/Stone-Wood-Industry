package com.ind.mod.utils;

import com.ind.mod.StoneWoodIndustry;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EvokerFangsEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Box;

import java.util.List;

public class LightningHelper {
    public static void spawnLightningAroundPlayer(PlayerEntity player, int radius, int level) {
        if (player.getWorld() instanceof ServerWorld serverWorld) {
            Box box = new Box(player.getX() - 5, player.getY() - 5, player.getZ()-5, player.getX()+5, player.getY() + 5, player.getZ()+5);
            List<LivingEntity> list = serverWorld.getEntitiesByClass(LivingEntity.class, box, entity -> entity.canTarget(player) && entity != player);
            for(LivingEntity entity:list){
                EvokerFangsEntity lightning = new EvokerFangsEntity(EntityType.EVOKER_FANGS, serverWorld);
                lightning.refreshPositionAfterTeleport(entity.getX(), entity.getY(), entity.getZ()); // 设置闪电位置
                lightning.setOwner(player);

                // 生成闪电
                serverWorld.spawnEntity(lightning);
                entity.damage(entity.getDamageSources().playerAttack(player), (float) (entity.getHealth() * (level * 0.05)));
            }
            
            
            StoneWoodIndustry.LOGGER.debug(String.valueOf(list));
            
        }
    }
}