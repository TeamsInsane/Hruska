package me.TeamsInsane.Hruska;

import me.TeamsInsane.Hruska.Items.Pumpkin_ex;
import me.TeamsInsane.Hruska.Items.S_allium;
import me.TeamsInsane.Hruska.events.ListenerRegistrable;
import me.TeamsInsane.Hruska.registry.Registerable;
import me.TeamsInsane.Hruska.utils.Configuration;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.Set;

public final class Core extends JavaPlugin {

    private static Core plugin;
    public static Configuration configuration;

    private static final Set<Registerable> REGISTERABLES = Set.of(
            new ListenerRegistrable()
    );

    @Override
    public void onEnable() {
        configuration = new Configuration(this);
        configuration.saveConfig();
        configuration.reloadConfig();

        plugin = this;

        Pumpkin_ex pumpkin_ex = new Pumpkin_ex();
        pumpkin_ex.recipeFurnace();

        REGISTERABLES.forEach(it -> it.register(this));
    }

    @Override
    public void onDisable() {

    }

    public static Core getInstance(){
        return plugin;
    }
}
