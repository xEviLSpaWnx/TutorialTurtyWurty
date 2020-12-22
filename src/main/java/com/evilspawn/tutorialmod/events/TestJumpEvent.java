package com.evilspawn.tutorialmod.events;

import com.evilspawn.tutorialmod.TutorialMod;
import com.evilspawn.tutorialmod.init.BlockInit;
import net.minecraft.entity.LivingEntity;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.entity.living.LivingEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)

public class TestJumpEvent {

    @SubscribeEvent
    public static void testJumpEvent(LivingEvent.LivingJumpEvent event) {
        // TutorialMod.LOGGER.info("Test jump event is fired");
        LivingEntity livingEntity = event.getEntityLiving();
        // World world = livingEntity.getEntityWorld();
        // world.setBlockState(livingEntity.getPosition().add(0, 5, 0), BlockInit.example_block.getDefaultState());
        livingEntity.addPotionEffect(new EffectInstance(Effects.JUMP_BOOST, 600, 2));
        livingEntity.addPotionEffect(new EffectInstance(Effects.RESISTANCE, 600, 255));
        // livingEntity.setGlowing(true);
    }
}
