package me.TeamsInsane.Hruska.events;

import me.TeamsInsane.Hruska.Items.S_allium;
import me.TeamsInsane.Hruska.events.darkDungeon.*;
import me.TeamsInsane.Hruska.Core;
import me.TeamsInsane.Hruska.registry.Registerable;
import org.bukkit.event.Listener;

import java.util.Set;

public class ListenerRegistrable implements Registerable {

    private static final Set<Listener> LISTENERS = Set.of(
            new Key(), new Healing(),  new EndPortalClick(), new CarvedPumpkinRightClick(),
            new DarkKey(), new DropperLeftClick(), new EmeraldBlockBreak(), new InkSackRightClick(), new JackLanternRightClick(),
            new TrappedChestRightClick(), new PickLock(), new S_allium()
    );

    @Override
    public void register(final Core core){
        LISTENERS.forEach(it -> core.getServer().getPluginManager().registerEvents(it, core));
    }
}
