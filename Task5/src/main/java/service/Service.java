package service;

import connectionUtil.DBManager;
import entity.Product;
import entity.Supplier;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class Service {
    private static String SQL_SELECT_PRODUCT_NAME_STARTED_WITH_C = "SELECT * FROM PRODUCTS p WHERE PRODUCTNAME LIKE 'C%'";
    private static String SQL_SELECT_PRODUCT_SMALLEST_PRICE = "SELECT * FROM PRODUCTS p WHERE (PRICE = (SELECT min(price) FROM PRODUCTS p2))";
    private static String SQL_SELECT_PRICE_OF_PRODUCT_FROM_USA = "SELECT * FROM PRODUCTS p WHERE (PRICE = (SELECT min(price) FROM PRODUCTS p2))";
    private static String SQL_SELECT_SUPPLIERS_THAT_SUPPLY_CONDIMENTS = "SELECT s.SUPPLIERID ,s.SUPPLIERNAME  , s.COUNTRY  ,s.CITY  FROM PRODUCTS p " +
            "INNER JOIN SUPPLIERS s ON p.SUPLIERID =s.SUPPLIERID " +
            "INNER JOIN CATEGORIES c ON p.CATEGORYID = c.CATEGORYID " +
            "WHERE c.CATEGORYNAME ='Condiments'";


    private static String SQL_INSERT_INTO_SUPPLIER = "INSERT INTO Suppliers(supplierName,City,Country) values(?,?,?)";
    private static String SQL_INSERT_INTO_PRODUCT = "INSERT INTO Products(ProductName,suplierId,categoryId,price) " +
            "VALUES (?,?,(SELECT CategoryID FROM Categories WHERE CategoryName =? LIMIT 1),?)";


    public List<Product> getProduct() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Product> list = new ArrayList<>();
        try {
            statement = DBManager.getConnection().createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_PRODUCT_NAME_STARTED_WITH_C);
            while (resultSet.next()) {
                list.add(setProduct(resultSet));
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBManager.closeResultSet(resultSet);
            DBManager.closeStatement(statement);
        }
        return list;
    }

    public Product getProductWithTheSmallestPrice() {
        Statement statement = null;
        ResultSet resultSet = null;
        Product product = new Product();
        try {
            statement = DBManager.getConnection().createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_PRODUCT_SMALLEST_PRICE);
            while (resultSet.next()) {
                setProduct(resultSet);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBManager.closeResultSet(resultSet);
            DBManager.closeStatement(statement);
        }
        return product;
    }


    public Product getProductFromUSA() {
        Statement statement = null;
        ResultSet resultSet = null;
        Product product = new Product();
        try {
            statement = DBManager.getConnection().createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_PRICE_OF_PRODUCT_FROM_USA);
            while (resultSet.next()) {
               setProduct(resultSet);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBManager.closeResultSet(resultSet);
            DBManager.closeStatement(statement);
        }
        return product;
    }


    private Product setProduct(ResultSet resultSet) throws SQLException {
        Product product = new Product();
        product.setId(resultSet.getInt("ProductID"));
        product.setName(resultSet.getString("ProductName"));
        product.setSupplierId(resultSet.getInt("suplierId"));
        product.setPrice(resultSet.getDouble("ProductID"));
        return product;
    }


    public List<Supplier> getSupplier() {
        Statement statement = null;
        ResultSet resultSet = null;
        List<Supplier> list = new ArrayList<>();
        try {
            statement = DBManager.getConnection().createStatement();
            resultSet = statement.executeQuery(SQL_SELECT_SUPPLIERS_THAT_SUPPLY_CONDIMENTS);
            while (resultSet.next()) {
                Supplier supplier = new Supplier();
                supplier.setId(resultSet.getInt("supplierID"));
                supplier.setName(resultSet.getString("supplierName"));
                supplier.setCity(resultSet.getString("City"));
                supplier.setCountry(resultSet.getString("Country"));
                list.add(supplier);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBManager.closeResultSet(resultSet);
            DBManager.closeStatement(statement);
        }
        return list;
    }


    public int addSupplier(Supplier supplier) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int last_inserted_id = 0;
        try {
            preparedStatement = DBManager.getConnection().prepareStatement(SQL_INSERT_INTO_SUPPLIER);
            preparedStatement.setString(1, supplier.getName());
            preparedStatement.setString(2, supplier.getCity());
            preparedStatement.setString(3, supplier.getCountry());
            preparedStatement.execute();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                last_inserted_id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBManager.closeResultSet(resultSet);
            DBManager.closePreparedStatement(preparedStatement);
        }
        return last_inserted_id;
    }


    public int addProducts(Product product) {
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int last_inserted_id = 0;
        try {
            preparedStatement = DBManager.getConnection().prepareStatement(SQL_INSERT_INTO_PRODUCT);
            preparedStatement.setString(1, product.getName());
            preparedStatement.setInt(2, product.getSupplierId());
            preparedStatement.setString(3, product.getCategory());
            preparedStatement.setDouble(4, product.getPrice());
            preparedStatement.execute();

            resultSet = preparedStatement.getGeneratedKeys();
            if (resultSet.next()) {
                last_inserted_id = resultSet.getInt(1);
            }
        } catch (SQLException e) {
            System.err.println(e);
        } finally {
            DBManager.closeResultSet(resultSet);
            DBManager.closePreparedStatement(preparedStatement);
        }
        return last_inserted_id;
    }


}
