
package configurations;

import base.BaseClass;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import java.sql.*;
import java.util.Properties;

public class DbUtils extends BaseClass {
    private static HikariDataSource ds;

    // Initialize once (e.g., from BaseClass.setUp() after properties are loaded)
    public static synchronized void init(Properties prop) {
        if (ds != null) return;

        String url = prop.getProperty("db.url");
        String user = resolve(prop.getProperty("db.user"));
        String pass = resolve(prop.getProperty("db.password"));
        int poolSize = Integer.parseInt(prop.getProperty("db.pool.size", "3"));

        HikariConfig cfg = new HikariConfig();
        cfg.setJdbcUrl(url);
        cfg.setUsername(user);
        cfg.setPassword(pass);
        cfg.setMaximumPoolSize(poolSize);
        cfg.setPoolName("qa-db-pool");
        // optional tuning
        cfg.setConnectionTimeout(10000); // ms
        cfg.setIdleTimeout(300000);      // ms
        cfg.setMaxLifetime(1800000);     // ms

        ds = new HikariDataSource(cfg);
    }

    public static Connection getConnection() throws SQLException {
        if (ds == null) {
            throw new IllegalStateException("DB pool not initialized. Call Db.init(Properties) first.");
        }
        return ds.getConnection();
    }

    public static void closeQuietly(AutoCloseable c) {
        if (c != null) {
            try { c.close(); } catch (Exception ignored) {}
        }
    }

    public static void shutdown() {
        if (ds != null) {
            ds.close();
            ds = null;
        }
    }

        // Support ${ENV_VAR} substitution
    private static String resolve(String value) {
        if (value == null) return null;
        if (value.startsWith("${") && value.endsWith("}")) {
            String key = value.substring(2, value.length() - 1);
            return System.getenv(key);
        }
        return value;
    }
}
