package actions;

import base.BaseClass;
import utilities.DbUtils;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;

public class DbActions extends BaseClass {

    public static void printAllSubmissions() throws Exception {
        String sql = "SELECT * FROM submission";
        try (Connection con = DbUtils.getConnection();
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            ResultSetMetaData meta = rs.getMetaData();
            int columnCount = meta.getColumnCount();

            while (rs.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    System.out.print(meta.getColumnName(i) + "=" + rs.getString(i) + " | ");
                }
                System.out.println();
            }
        }
    }
}
