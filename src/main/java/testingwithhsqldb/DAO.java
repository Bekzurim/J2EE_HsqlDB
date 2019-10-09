package testingwithhsqldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;

public class DAO {
	private final DataSource myDataSource;
	
	public DAO(DataSource dataSource) {
		myDataSource = dataSource;
	}

	/**
	 * Renvoie le nom d'un client à partir de son ID
	 * @param id la clé du client à chercher
	 * @return le nom du client (LastName) ou null si pas trouvé
	 * @throws SQLException 
	 */
	public String nameOfCustomer(int id) throws SQLException {
		String result = null;
		
		String sql = "SELECT LastName FROM Customer WHERE ID = ?";
		try (Connection myConnection = myDataSource.getConnection(); 
		     PreparedStatement statement = myConnection.prepareStatement(sql)) {
			statement.setInt(1, id); // On fixe le 1° paramètre de la requête
			try ( ResultSet resultSet = statement.executeQuery()) {
				if (resultSet.next()) {
					// est-ce qu'il y a un résultat ? (pas besoin de "while", 
                                        // il y a au plus un enregistrement)
					// On récupère les champs de l'enregistrement courant
					result = resultSet.getString("LastName");
				}
			}
		}
		// dernière ligne : on renvoie le résultat
		return result;
	}
	
        public void insertProduct(Product product) throws SQLException {
		String requete = "INSERT INTO PRODUCT VALUES(?, ?, ?)";
		try (
                     Connection connection = myDataSource.getConnection();
                     PreparedStatement stmt = connection.prepareStatement(requete)
                    ) 
                {
			stmt.setInt(1, product.getId());
			stmt.setString(2, product.getName());
			stmt.setFloat(3, product.getPrice());
			stmt.executeUpdate();
		}
	}
        
        Product findProduct(int productID) throws SQLException {
		Product client = null;
		String requete = "SELECT * FROM PRODUCT WHERE PRODUCT_ID = ?";
		try (
                     Connection connection = myDataSource.getConnection();
                     PreparedStatement stmt = connection.prepareStatement(requete)
                    ) 
                {
			stmt.setInt(1, productID);
			try (ResultSet rs = stmt.executeQuery())
                        {
				if (rs.next())
                                { 
					String name = rs.getString("NAME");
					float price = rs.getFloat("PRICE");
					client = new Product(productID, name, price);
				}
			}
		}
                return client;
	}
}
