package io.github.the_dexman.dexmod_quilt;

import com.mojang.serialization.Codec;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.block.Block;
import net.minecraft.client.gui.tooltip.TooltipComponent;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.OreVeinCreator;
import net.minecraft.world.gen.WorldGeneratorOptions;
import net.minecraft.world.gen.feature.OreFeature;
import net.minecraft.world.gen.feature.PlacedFeature;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.block.extensions.api.QuiltBlockSettings;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.quiltmc.qsl.worldgen.biome.api.BiomeModifications;
import org.quiltmc.qsl.worldgen.biome.api.BiomeSelectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DexmodQuilt implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Example Mod");
	public static final Item TEST_ITEM = new Item(new QuiltItemSettings());
	public static final KelviniteTools KELVINITE_TOOLS = new KelviniteTools();
	public static final Item KELVINITE_PICKAXE = new PickaxeItem(KELVINITE_TOOLS, 4, -2.8f, new QuiltItemSettings());
	public static final Item KELVINITE_AXE = new AxeItem(KELVINITE_TOOLS, 8, -3.1f, new QuiltItemSettings());
	public static final Item KELVINITE_SHOVEL = new ShovelItem(KELVINITE_TOOLS, 4, -2.8f, new QuiltItemSettings());
	public static final Item KELVINITE_SWORD = new SwordItem(KELVINITE_TOOLS, 6, -2.4f, new QuiltItemSettings());
	public static final Item KELVINITE_HOE = new HoeItem(KELVINITE_TOOLS, 3, -1.6f, new QuiltItemSettings());

	public static final Item KELVINITE_INGOT = new Item(new QuiltItemSettings());
	public static final Item MOLTEN_KELVINITE = new Item(new QuiltItemSettings()
		.recipeRemainder(KELVINITE_INGOT));
	public static final RegistryKey<PlacedFeature> KELVINITE_ORE_PLACED_KEY = RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("dexmod_quilt", "ore_kelvinite"));

	public static final Block KELVINITE_ORE = new Block(QuiltBlockSettings.create()

		.drops(new Identifier("dexmod_quilt:kelvinite_ore"))
		.requiresTool(true)
		.hardness(4.5f)
		.resistance(3)
		.strength(4.5f, 3)
		.sounds(BlockSoundGroup.DEEPSLATE)

	);
	public static final Item RAW_KELVINITE = new Item(new QuiltItemSettings());
	@Override
	public void onInitialize(ModContainer mod)
	{
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "strange_object"), TEST_ITEM);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "kelvinite_pickaxe"), KELVINITE_PICKAXE);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "kelvinite_axe"), KELVINITE_AXE);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "kelvinite_shovel"), KELVINITE_SHOVEL);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "kelvinite_sword"), KELVINITE_SWORD);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "kelvinite_hoe"), KELVINITE_HOE);
		Registry.register(Registries.BLOCK, new Identifier(mod.metadata().id(), "kelvinite_ore"), KELVINITE_ORE);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "kelvinite_ore"), new BlockItem(KELVINITE_ORE, new QuiltItemSettings()));
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "raw_kelvinite"), RAW_KELVINITE);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "kelvinite_ingot"), KELVINITE_INGOT);
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "molten_kelvinite"), MOLTEN_KELVINITE);



		//Add items to ingredients tab
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries ->
		{
			entries.addItem(TEST_ITEM);
			entries.addItem(RAW_KELVINITE);
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS_AND_UTILITIES).register(entries ->
		{
			entries.addItem(KELVINITE_PICKAXE);
			entries.addItem(KELVINITE_AXE);
			entries.addItem(KELVINITE_SHOVEL);
			entries.addItem(KELVINITE_HOE);
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries ->
		{
			entries.addItem(KELVINITE_SWORD);
		});

		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL_BLOCKS).register(entries ->
		{
			entries.addItem(KELVINITE_ORE.asItem());
		});

		BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(), GenerationStep.Feature.UNDERGROUND_ORES, KELVINITE_ORE_PLACED_KEY);

	}
}
