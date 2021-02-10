import connectionUtil.DBManager;
import entity.Product;
import entity.Supplier;
import service.Service;

public class Main {
    public static void main(String[] args) {
        DBManager.getConnection();
        Service service = new Service();
        System.out.println(service.getProduct());
        System.out.println( service.getProductFromUSA());
        System.out.println(service.getSupplier());
        System.out.println(service.getProductWithTheSmallestPrice());


        Supplier supplier = new Supplier();
        supplier.setName("Norske Meierier");
        supplier.setCountry("Ukraine");
        supplier.setCity("Lviv");

        int idSupplier =service.addSupplier(supplier);

        Product product = new Product();
        product.setName("Green tea");
        product.setSupplierId(idSupplier);
        product.setPrice(10);
        product.setCategory("Beverages");

        service.addProducts(product);
    }
}
