package com.mysticalmachines.simplevotelistener.configuration;

import com.mysticalmachines.simplevotelistener.SimpleVoteListener;
import ninja.leaping.configurate.commented.CommentedConfigurationNode;
import ninja.leaping.configurate.loader.ConfigurationLoader;
import ninja.leaping.configurate.objectmapping.ObjectMappingException;
import org.spongepowered.api.Sponge;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class ConfigManager {
    private SimpleVoteListener instance = SimpleVoteListener.getInstance();
    private Path defaultPath;
    private ConfigurationLoader<CommentedConfigurationNode> loader;
    private PluginConfiguration config;

    public ConfigManager(Path defaultPath, ConfigurationLoader<CommentedConfigurationNode> loader) {
        this.defaultPath = defaultPath;
        this.loader = loader;

        try {
            loadConfig();
        }
        catch (IOException | ObjectMappingException e) {
            e.printStackTrace();
        }
    }

    private void loadConfig() throws IOException, ObjectMappingException {
        if (!Files.exists(defaultPath)) {
            Sponge.getGame().getAssetManager().getAsset(instance, "simplevotelistener.conf").get().copyToFile(defaultPath);
        }

        config = loader.load().getValue(PluginConfiguration.TYPE);

        instance.getLogger().info("Configuration loaded...");
    }
}
