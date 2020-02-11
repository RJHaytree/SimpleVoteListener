package com.mysticalmachines.simplevotelistener.storage;

import com.mysticalmachines.simplevotelistener.SimpleVoteListener;
import com.mysticalmachines.simplevotelistener.configuration.PluginConfiguration;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Storage {
    private Connection connection;

    public Storage() {
        createTables();
        if (PluginConfiguration.General.STORAGE_METHOD.equals("mysql")) {
            SimpleVoteListener.getInstance().getLogger().info("MySQL storage loaded...");
        }
        else {
            SimpleVoteListener.getInstance().getLogger().info("H2 Storage loaded...");
        }
    }

    private void createTables() {
        try (Connection connection = SQLManager.getSQLConnection()) {
            PreparedStatement ps = connection.prepareStatement("CREATE TABLE IF NOT EXISTS `tbl_player_vote_data` ( `player_uuid` VARCHAR(36) NOT NULL PRIMARY KEY, `total_votes` INT NOT NULL );");
            ps.execute();
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
