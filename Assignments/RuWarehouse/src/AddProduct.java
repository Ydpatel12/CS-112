package warehouse;

/*
 * Use this class to test to addProduct method.
 */
public class AddProduct {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);

        Warehouse warehouse = new Warehouse();

        int numProductDescriptions = StdIn.readInt();
        int count = 0;

        while (count < numProductDescriptions){

            int currentDay = StdIn.readInt();
            int productID = StdIn.readInt();
            String productName = StdIn.readString();
            int initialProductStock = StdIn.readInt();
            int initialProductDemand = StdIn.readInt();

            warehouse.addProduct(productID, productName, initialProductStock, currentDay, initialProductDemand);
            count++;
        }
        
        StdOut.println(warehouse);
	// Use this file to test addProduct
    }
}
