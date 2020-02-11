package com.mysticalmachines.simplevotelistener.configuration;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.reflect.TypeToken;
import ninja.leaping.configurate.objectmapping.Setting;
import ninja.leaping.configurate.objectmapping.serialize.ConfigSerializable;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ConfigSerializable
public class PluginConfiguration {
    public final static TypeToken<PluginConfiguration> TYPE = TypeToken.of(PluginConfiguration.class);

    @Setting
    General general = new General();

    @Setting
    MySQL mysql = new MySQL();

    @Setting
    RewardCommands commands = new RewardCommands();

    @ConfigSerializable
    public static class General {
        @Setting("storage-method")
        public static String STORAGE_METHOD = "h2";
    }

    @ConfigSerializable
    public static class MySQL {
        @Setting("hostname")
        public static String HOSTNAME = "127.0.0.1";

        @Setting("username")
        public static String USERNAME = "root";

        @Setting("password")
        public static String PASSWORD = "";

        @Setting("database")
        public static String DATABASE = "simplevotelistener_db";
    }

    @ConfigSerializable
    public static class RewardCommands {
        @Setting("rewards")
        public static Map<String, String> REWARDS = ImmutableMap.of();

        @Setting("ifPlayerOnlineWhenVoting")
        public static List<String> PLAYER_ONLINE_COMMANDS = ImmutableList.of();

        @Setting("ifPlayerOfflineWhenVoting")
        public static List<String> PLAYER_OFFLINE_COMMANDS = ImmutableList.of();
    }
}
