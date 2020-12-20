package com.evilspawn.tutorialmod.init;

import com.evilspawn.tutorialmod.TutorialMod;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.LazyValue;
import net.minecraftforge.common.util.Lazy;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.ObjectHolder;

import java.util.function.Supplier;

@Mod.EventBusSubscriber(modid = TutorialMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)
@ObjectHolder(TutorialMod.MOD_ID)
public class ItemInit {

    public static final Item example_item = null;
    public static final Item test_item = null;

    public static final Item example_sword = null;
    public static final Item example_pickaxe = null;
    public static final Item example_axe = null;
    public static final Item example_shovel = null;
    public static final Item example_hoe = null;

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new Item(new Item.Properties().group(TutorialMod.TutorialItemGroup.instance)).setRegistryName("example_item"));
        event.getRegistry().register(new Item(new Item.Properties().group(TutorialMod.TutorialItemGroup.instance).food(new Food.Builder().hunger(6).saturation(1.2f).effect(new EffectInstance(Effects.ABSORPTION, 6000, 0), 0.7f).build())).setRegistryName("test_item"));

        event.getRegistry().register(new SwordItem(ModItemTier.EXAMPLE, 7, 5.0f, new Item.Properties().group(TutorialMod.TutorialItemGroup.instance)).setRegistryName("example_sword"));
        event.getRegistry().register(new PickaxeItem(ModItemTier.EXAMPLE, 1, 5.0f, new Item.Properties().group(TutorialMod.TutorialItemGroup.instance)).setRegistryName("example_pickaxe"));
        event.getRegistry().register(new AxeItem(ModItemTier.EXAMPLE, 9, 5.0f, new Item.Properties().group(TutorialMod.TutorialItemGroup.instance)).setRegistryName("example_axe"));
        event.getRegistry().register(new ShovelItem(ModItemTier.EXAMPLE, 0, 5.0f, new Item.Properties().group(TutorialMod.TutorialItemGroup.instance)).setRegistryName("example_shovel"));
        event.getRegistry().register(new HoeItem(ModItemTier.EXAMPLE, 7, new Item.Properties().group(TutorialMod.TutorialItemGroup.instance)).setRegistryName("example_hoe"));
    }

    public enum ModItemTier implements IItemTier {
        EXAMPLE(4, 1500, 15.0f, 7, 250, () -> {
            return Ingredient.fromItems(ItemInit.example_item);
        });

        private final int harvestLevel;
        private final int maxUses;
        private final float efficiency;
        private final int attackDamage;
        private final int enchantability;
        private final LazyValue<Ingredient> repairMaterial;

        private ModItemTier(int harvestLevel, int maxUses, float efficiency, int attackDamage, int enchantability, Supplier<Ingredient> repairMaterial) {
            this.harvestLevel = harvestLevel;
            this.maxUses = maxUses;
            this.efficiency = efficiency;
            this.attackDamage = attackDamage;
            this.enchantability = enchantability;
            this.repairMaterial = new LazyValue<>(repairMaterial);
        }

        @Override
        public int getMaxUses() {
            return this.maxUses;
        }

        @Override
        public float getEfficiency() {
            return this.efficiency;
        }

        @Override
        public float getAttackDamage() {
            return this.attackDamage;
        }

        @Override
        public int getHarvestLevel() {
            return this.harvestLevel;
        }

        @Override
        public int getEnchantability() {
            return this.enchantability;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return this.repairMaterial.getValue();
        }
    }
}
