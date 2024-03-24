package io.github.the_dexman.dexmod_quilt;

import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroups;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.quiltmc.loader.api.ModContainer;
import org.quiltmc.qsl.base.api.entrypoint.ModInitializer;
import org.quiltmc.qsl.item.setting.api.QuiltItemSettings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DexmodQuilt implements ModInitializer {
	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod name as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger("Example Mod");
	public static final Item TEST_ITEM = new Item(new QuiltItemSettings());

	@Override
	public void onInitialize(ModContainer mod)
	{
		LOGGER.info("Hello Quilt world from {}!", mod.metadata().name());
		Registry.register(Registries.ITEM, new Identifier(mod.metadata().id(), "strange_object"), TEST_ITEM);

		//Add items to ingredients tab
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries ->
		{
			entries.addItem(TEST_ITEM);
		});
	}
}
