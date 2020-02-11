package com.mysticalmachines.simplevotelistener.storage;

import com.mysticalmachines.simplevotelistener.SimpleVoteListener;
import com.mysticalmachines.simplevotelistener.configuration.PluginConfiguration;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.service.sql.SqlService;

import java.io.File;
import java.sql.Connection;
import java.sql.SQLException;

public class SQLManager {
    private static SqlService sql = Sponge.getServiceManager().provideUnchecked(SqlService.class);

    public static Connection getSQLConnection() {
        try {
            return sql.getDataSource(getURL()).getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static String getURL() {
        if (PluginConfiguration.General.STORAGE_METHOD.equals("mysql")) {
             return "jdbc:mysql://" + PluginConfiguration.MySQL.HOSTNAME +
                    "/" + PluginConfiguration.MySQL.DATABASE +
                    "?user=" + PluginConfiguration.MySQL.USERNAME +
                    "&password=" + PluginConfiguration.MySQL.PASSWORD;
        }
        else {
            return "jdbc:h2:./" + SimpleVoteListener.getInstance().getPluginContainer().getId() + File.separator + "storage.db";
        }
    }
}
