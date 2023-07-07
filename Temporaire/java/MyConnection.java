package myConnection;

import java.sql.*;

import models.interne.Person;

public class MyConnection {

    public static Connection GetConnection() throws Exception{
        String url = "jdbc:postgresql://localhost:5432/framework";
        String username = "postgres";
        String password = "johan";
        Connection connection = null;
        try 
        {
            Class.forName("org.postgresql.Driver"); // Charge la classe du pilote JDBC PostgreSQL
            connection = DriverManager.getConnection(url, username, password);
            connection.setAutoCommit(false);
        } catch (Exception e) 
        {
            throw new Exception(e.getMessage());
        }
        return connection;
    }

    public static void AddPerson(String nom, String email, String mdp, String profile) throws Exception
    {
        String query = "INSERT INTO person ( idperson, nom, email, mdp, profile) VALUES ( DEFAULT, ?, ?, ?, ? );";
        Connection connection = null;
        try 
        {
            connection = MyConnection.GetConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, nom);
            statement.setString(2, email);
            statement.setString(3, mdp);
            statement.setString(4, profile);
            statement.executeUpdate();
            connection.commit();
            connection.close();
        } catch (Exception e) {
            connection.rollback();
            connection.close();
            throw new Exception(e.getMessage());
        }
    }

    public static Person GetPersonByEmail_and_Mdp(String email, String motDePasse) throws Exception
    {
        String query = "SELECT * FROM person WHERE email = ? AND mdp = ?";
        Person person = null;
        Connection connection = null;
        try 
        {
            connection = MyConnection.GetConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, motDePasse);
            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                String profile = resultSet.getString("profile");
                person = new Person(email, motDePasse, profile);
            }
            connection.close();
        } catch (SQLException e) {
            connection.close();
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }

        return person;
    }

    public void UpdatePerson(Person person) throws Exception{
        String query = "UPDATE person SET profile = ? WHERE idperson = ?";
        Connection connection = null;
        try 
        {
            connection = MyConnection.GetConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, person.getProfile());
            statement.setInt(2, person.getIdPerson());
            statement.executeUpdate();
            connection.commit();
            connection.close();
        } catch (Exception e) {
            connection.close();
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }

    // Méthode pour supprimer une person de la table
    public void supprimerPerson(Person person) throws Exception{
        String query = "DELETE FROM person WHERE idperson = ?";
        Connection connection = null;
        try 
        {
            connection = MyConnection.GetConnection();
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setInt(1, person.getIdPerson());
            statement.executeUpdate();
            connection.commit();
            connection.close();
        } catch (Exception e) {
            connection.close();
            e.printStackTrace();
            throw new Exception(e.getMessage());
        }
    }
}