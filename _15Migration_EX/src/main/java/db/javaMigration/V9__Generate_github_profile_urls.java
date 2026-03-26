package db.javaMigration;

import org.flywaydb.core.api.migration.BaseJavaMigration;
import org.flywaydb.core.api.migration.Context;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class V9__Generate_github_profile_urls extends BaseJavaMigration {

    @Override
    public void migrate(Context context) throws Exception {

        try (Statement st = context.getConnection().createStatement()) {

            st.execute(
            """
                ALTER TABLE developers
                ADD COLUMN github_profile_url VARCHAR(200)
            """);
        }

        try (
            Statement st = context.getConnection().createStatement();

            ResultSet rs = st.executeQuery(
                """
                    SELECT id, github_username
                    FROM developers
                """);

            PreparedStatement ps = context.getConnection().prepareStatement(
                """
                    UPDATE developers
                    SET github_profile_url = ?
                    WHERE id = ?
                """)
        ) {
            while (rs.next()) {

                long id = rs.getLong("id");
                String githubUsername = rs.getString("github_username");
                String githubProfileUrl = "https://github.com/" + githubUsername;

                ps.setString(1, githubProfileUrl);
                ps.setLong(2, id);
                ps.executeUpdate();
            }
        }

        try (Statement st = context.getConnection().createStatement()) {

            st.execute
            ("""
                ALTER TABLE developers
                MODIFY COLUMN github_profile_url VARCHAR(200) NOT NULL
            """);
        }
    }
}
