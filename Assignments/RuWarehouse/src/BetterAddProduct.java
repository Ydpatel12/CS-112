package warehouse;

/*
 * Use this class to test the betterAddProduct method.
 */ 
public class BetterAddProduct {
    public static void main(String[] args) {
        StdIn.setFile("betteraddproduct.in");
        StdOut.setFile("betteraddproduct.out");

        Warehouse warehouse = new Warehouse();

        int numProductDescriptions = StdIn.readInt();
        int count = 0;

        while (count < numProductDescriptions){

            int currentDay = StdIn.readInt();
            int productID = StdIn.readInt();
            String productName = StdIn.readString();
            int initialProductStock = StdIn.readInt();
            int initialProductDemand = StdIn.readInt();

            warehouse.betterAddProduct(productID, productName, initialProductStock, currentDay, initialProductDemand);
            count++;
        }
        
        StdOut.println(warehouse);
        
        // Use this file to test betterAddProduct
    }
}
