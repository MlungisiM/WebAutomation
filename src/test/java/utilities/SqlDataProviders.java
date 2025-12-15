
package utilities;

import org.testng.annotations.DataProvider;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SqlDataProviders {

    @DataProvider(name = "validPolicies")
    public Object[][] validPolicies() throws Exception {
        String sql = "SELECT * FROM submission";
        List<Object[]> rows = new ArrayList<>();

        try (Connection con = DbUtils.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String policyNo = rs.getString("policy_no");
                String user = rs.getString("username");
                String pass = rs.getString("password");
                rows.add(new Object[]{ policyNo, user, pass });
            }
        }
        return rows.toArray(new Object[0][]);
    }
}
