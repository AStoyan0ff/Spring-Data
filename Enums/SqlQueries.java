package Enums;

public enum SqlQueries {

    GET_GAMER_NAME(
        "SELECT u.user_name, u.first_name, u.last_name, " +
            "COUNT(g.id) AS games_played " +
            "FROM users u " +
            "JOIN users_games ug ON u.id = ug.user_id " +
            "JOIN games g ON ug.game_id = g.id " +
            "WHERE u.user_name = ? " +
            "GROUP BY u.id"
    ),

    GET_VILLAINS_WITH_MINIONS(
        "SELECT v.name, COUNT(*) " +
            "FROM villains v " +
            "JOIN minions_villains mv " +
            "ON v.id = mv.villain_id " +
            "GROUP BY v.id " +
            "HAVING COUNT(*) > 15 " +
            "ORDER BY COUNT(*) DESC"
    ),

    GET_MINIONS_BY_VILLAIN_ID(
        "SELECT DISTINCT m.name, m.age " +
            "FROM minions_villains mv " +
            "JOIN minions m " +
            "ON mv.minion_id = m.id " +
            "WHERE mv.villain_id = ?"
    ),

    GET_VILLAIN_NAME_BY_ID(
        """
            SELECT v.name
            FROM villains v
            WHERE v.id = ?
            """

    );
    private final String query;

    SqlQueries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }
}

