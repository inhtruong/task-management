package com.cg.taskmanagement.utils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DBUtils {
    public static void close(ResultSet resultset) {
        try {
            if (resultset == null)
                return;
            resultset.close();

        } catch (SQLException sqle) {
            // SQLException
        }
    }

    public static void close(Statement statement) {
        try {
            if (statement == null)
                return;
            statement.close();

        } catch (SQLException sqle) {
            // SQLException
        }
    }

    public static void close(Connection connection) {
        try {
            if (connection == null)
                return;
            connection.close();

        } catch (SQLException sqle) {
            // SQLException
        }
    }
}
