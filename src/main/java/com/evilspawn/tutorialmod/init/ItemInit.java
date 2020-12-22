package com.evilspawn.tutorialmod.init;

import com.evilspawn.tutorialmod.TutorialMod;
import com.evilspawn.tutorialmod.objects.items.SpecialItem;
import net.minecraft.inventory.EquipmentSlotType;
import net.minecraft.item.*;
import net.minecraft.item.crafting.Ingredient;
import net.minecraft.potion.EffectInstance;
import net.minecraft.potion.Effects;
import net.minecraft.util.LazyValue;
import net.minecraft.util.SoundEvent;
import net.minecraft.util.SoundEvents;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;
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
    public static final Item special_item = null;

    ///// TOOLS //////
    public static final Item example_sword = null;
    public static final Item example_pickaxe = null;
    public static final Item example_axe = null;
    public static final Item example_shovel = null;
    public static final Item example_hoe = null;

    ///// ARMOR /////
    public static final Item test_helmet = null;
    public static final Item test_chestplate = null;
    public static final Item test_leggings = null;
    public static final Item test_boots = null;

    @SubscribeEvent
    public static void registerItems(final RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new Item(new Item.Properties().group(TutorialMod.TutorialItemGroup.instance)).setRegistryName("example_item"));
        event.getRegistry().register(new Item(new Item.Properties().group(TutorialMod.TutorialItemGroup.instance).food(new Food.Builder().hunger(6).saturation(1.2f).effect(new EffectInstance(Effects.ABSORPTION, 6000, 0), 0.7f).build())).setRegistryName("test_item"));
        event.getRegistry().register(new SpecialItem(new Item.Properties().group(TutorialMod.TutorialItemGroup.instance)).setRegistryName("special_item"));

        event.getRegistry().register(new SwordItem(ModItemTier.EXAMPLE, 7, 5.0f, new Item.Properties().group(TutorialMod.TutorialItemGroup.instance)).setRegistryName("example_sword"));
        event.getRegistry().register(new PickaxeItem(ModItemTier.EXAMPLE, 1, 5.0f, new Item.Properties().group(TutorialMod.TutorialItemGroup.instance)).setRegistryName("example_pickaxe"));
        event.getRegistry().register(new AxeItem(ModItemTier.EXAMPLE, 9, 5.0f, new Item.Properties().group(TutorialMod.TutorialItemGroup.instance)).setRegistryName("example_axe"));
        event.getRegistry().register(new ShovelItem(ModItemTier.EXAMPLE, 0, 5.0f, new Item.Properties().group(TutorialMod.TutorialItemGroup.instance)).setRegistryName("example_shovel"));
        event.getRegistry().register(new HoeItem(ModItemTier.EXAMPLE, 7, new Item.Properties().group(TutorialMod.TutorialItemGroup.instance)).setRegistryName("example_hoe"));

        event.getRegistry().register(new ArmorItem(ModArmorMaterial.TEST, EquipmentSlotType.HEAD, new Item.Properties().group(TutorialMod.TutorialItemGroup.instance)).setRegistryName("test_helmet"));
        event.getRegistry().register(new ArmorItem(ModArmorMaterial.TEST, EquipmentSlotType.CHEST, new Item.Properties().group(TutorialMod.TutorialItemGroup.instance)).setRegistryName("test_chestplate"));
        event.getRegistry().register(new ArmorItem(ModArmorMaterial.TEST, EquipmentSlotType.LEGS, new Item.Properties().group(TutorialMod.TutorialItemGroup.instance)).setRegistryName("test_leggings"));
        event.getRegistry().register(new ArmorItem(ModArmorMaterial.TEST, EquipmentSlotType.FEET, new Item.Properties().group(TutorialMod.TutorialItemGroup.instance)).setRegistryName("test_boots"));
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

    public enum ModArmorMaterial implements IArmorMaterial {
        TEST(TutorialMod.MOD_ID + ":test", 5, new int[]{7, 9, 11, 7}, 420, SoundEvents.ITEM_ARMOR_EQUIP_IRON, 6.9f,
                () -> { return Ingredient.fromItems(ItemInit.test_item);
        });

        private static final int[] MAX_DAMAGE_ARRAY = new int[] {16, 16, 16, 16};
        private final String name;
        private final int maxDamageFactor;
        private final int[] damageReducitonAmountArray;
        private final int enchantability;
        private final SoundEvent soundEvent;
        private final float toughness;
        private final LazyValue<Ingredient> repairMaterial;

        private ModArmorMaterial(String name, int maxDamageFactor, int[] damageReducitonAmountArray, int enchantability, SoundEvent soundEvent,
                                 float toughness, Supplier<Ingredient> repairMaterial) {
            this.name = name;
            this.maxDamageFactor = maxDamageFactor;
            this.damageReducitonAmountArray = damageReducitonAmountArray;
            this.enchantability = enchantability;
            this.soundEvent = soundEvent;
            this.toughness = toughness;
            this.repairMaterial = new LazyValue<>(repairMaterial);

        }

        @Override
        public int getDurability(EquipmentSlotType slotIn) {
            return MAX_DAMAGE_ARRAY[slotIn.getIndex()] * this.maxDamageFactor;
        }

        @Override
        public int getDamageReductionAmount(EquipmentSlotType slotIn) {
            return this.damageReducitonAmountArray[slotIn.getIndex()];
        }

        @Override
        public int getEnchantability() {
            return this.enchantability;
        }

        @Override
        public SoundEvent getSoundEvent() {
            return this.soundEvent;
        }

        @Override
        public Ingredient getRepairMaterial() {
            return this.repairMaterial.getValue();
        }

        @OnlyIn(Dist.CLIENT)
        @Override
        public String getName() {
            return this.name;
        }

        @Override
        public float getToughness() {
            return this.toughness;
        }
    }
}
