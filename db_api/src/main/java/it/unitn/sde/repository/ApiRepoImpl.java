package it.unitn.sde.repository;

import it.unitn.sde.model.Result;
import org.springframework.stereotype.Repository;
import it.unitn.sde.config.DataSource;
import org.springframework.beans.factory.annotation.Autowired;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ApiRepoImpl implements ApiRepo {
    @Autowired
    private DataSource dataSource;
    @Override
    public Boolean addVote(String option) {
        try(Connection connection = dataSource.getConnection();) {
            
            final String insert = "UPDATE optionvotes SET votes = votes + 1 WHERE option = ?";
            try (PreparedStatement statement = connection.prepareStatement(insert)) {
                statement.setString(1, option);
                if (statement.executeUpdate() > 1)
                    return false;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

        return true;
    }
    @Override
    public Boolean deleteVote(String option) {
        try(Connection connection = dataSource.getConnection();) {
            
            final String insert = "UPDATE optionvotes SET votes = votes - 1 WHERE option = ? and votes>0";
            try (PreparedStatement statement = connection.prepareStatement(insert)) {
                statement.setString(1, option);
                if (statement.executeUpdate() > 1)
                    return false;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

        return true;
    }

    @Override
    public List<Result> getVotes() {
        List<Result> results = new ArrayList<>();
        try(Connection connection = dataSource.getConnection();) {
            final String insert = "SELECT * FROM optionvotes WHERE option = ? OR option = ?";
            try (PreparedStatement statement = connection.prepareStatement(insert)) {
                statement.setString(1, "cats");
                statement.setString(2, "dogs");

                ResultSet rs = statement.executeQuery();
                // We should have exactly two rows.
                for (int i = 0; i < 2; i++) {
                    rs.next();
                    Result result = new Result();
                    result.put(rs.getString("option").trim(), rs.getInt("votes"));
                    results.add(result);
                }
                // A third row implies a malformed database
                if (rs.next())
                    return null;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

        return results;
    }

    @Override
    public Boolean exists(String option) {
        try(Connection connection = dataSource.getConnection();) {
            final String text = "SELECT count(*) AS option_count FROM optionvotes WHERE option = ? ";
            try (PreparedStatement statement = connection.prepareStatement(text)) {
                statement.setString(1, option);
                ResultSet rs = statement.executeQuery();
                if (!rs.next())
                    return null;
                if (rs.getInt("option_count") == 0)
                    return false;
                if (rs.getInt("option_count") != 1)
                    // Violation of primary key constraint
                    return null;
            } catch (SQLException ex) {
                ex.printStackTrace();
                return null;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            return null;
        }

        return true;
    }
}
