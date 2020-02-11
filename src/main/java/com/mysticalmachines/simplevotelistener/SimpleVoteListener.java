package com.mysticalmachines.simplevotelistener;

import com.google.common.reflect.TypeToken;
import com.google.inject.Inject;
import com.mysticalmachines.simplevotelistener.configuration.ConfigManager;
import com.mysticalmachines.simplevotelistener.configuration.PluginConfiguration;
import com.mysticalmachines.simplevotelistener.storage.Storage;
import com.vexsoftware.votifier.model.Vote;
import com.vexsoftware.votifier.sponge.event.VotifierEvent;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import org.slf4j.Logger;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.config.ConfigDir;
import org.spongepowered.api.config.DefaultConfig;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.event.game.state.GamePreInitializationEvent;
import org.spongepowered.api.event.game.state.GameStartedServerEvent;
import org.spongepowered.api.event.Listener;
import org.spongepowered.api.plugin.Dependency;
import org.spongepowered.api.plugin.Plugin;
import org.spongepowered.api.plugin.PluginContainer;

import java.nio.file.Path;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Plugin(
        id = "simplevotelistener",
        name = "SimpleVoteListener",
        description = "Simple Vote Listener for Sponge",
        url = "https://mysticalmachines.com",
        authors = {
                "Rubbertjuh"
        },
        dependencies = {
                @Dependency(id = "nuvotifier")
        }
)
public class SimpleVoteListener {

    @Inject
    @DefaultConfig(sharedRoot = false)
    private Path defaultPath;

    @Inject
    @DefaultConfig(sharedRoot = false)
    private ConfigurationLoader<CommentedConfigurationNode> loader;

    @Inject
    private PluginContainer pluginContainer;

    @Inject
    private Logger logger;

    private static SimpleVoteListener instance;
    private ConfigManager configManager;
    private Storage storage;
    private Map<String, String> rewards;

    @Listener
    public void preInit(GamePreInitializationEvent event) {
        instance = this;
        configManager = new ConfigManager(defaultPath, loader);
        storage = new Storage();
        rewards = PluginConfiguration.RewardCommands.REWARDS;
    }

    @Listener
    public void onServerStart(GameStartedServerEvent event) {
        this.logger.info("Simple Vote Listener Started");
    }

    @Listener
    public void onVote(VotifierEvent event) {
        Vote vote = event.getVote();
        Optional<Player> optPlayer = Sponge.getServer().getPlayer(vote.getUsername());
        if (optPlayer.isPresent()) {
            // test
        } else {
            this.logger.info("Player: " + vote.getUsername() + " was not online, or something went wrong!");
        }
    }

    /*private void executeConfiguredCommands(Player player) {
        for (String command :
                this.executeCommandsOnVote) {
            Sponge.getCommandManager().process(Sponge.getServer().getConsole(), command
                    .replace("%player%", player.getName())
                    .replace("%playeruuid%", player.getUniqueId().toString()));
        }
    }*/

    public static SimpleVoteListener getInstance() {
        return instance;
    }

    public Logger getLogger() {
        return logger;
    }

    public Path getDefaultPath() {
        return defaultPath;
    }

    public PluginContainer getPluginContainer() {
        return  pluginContainer;
    }
}
