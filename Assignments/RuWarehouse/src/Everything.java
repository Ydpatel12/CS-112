package warehouse;

/*
 * Use this class to put it all together.
 */ 
public class Everything {
    public static void main(String[] args) {
        StdIn.setFile("everything.in");
        StdOut.setFile("everything.out");

        Warehouse warehouse = new Warehouse();

        int numProductDescriptions = StdIn.readInt();
        int count = 0;

        
        while(count < numProductDescriptions){
            String bruh = StdIn.readString();
            
                if(bruh.equals("add")){
                int currentDay = StdIn.readInt();
                int productID = StdIn.readInt();
                String productName = StdIn.readString();
                int initialProductStock = StdIn.readInt();
                int initialProductDemand = StdIn.readInt();
                warehouse.addProduct(productID, productName, initialProductStock, currentDay, initialProductDemand);
            }
            else if(bruh.equals("purchase")){
                int day = StdIn.readInt();
                int ID = StdIn.readInt();
                int amount = StdIn.readInt();
                warehouse.purchaseProduct(ID, day, amount);
            }
            else if(bruh.equals("restock")){
                int ID = StdIn.readInt();
                int restock = StdIn.readInt();
                warehouse.restockProduct(ID, restock);
            }
            else if(bruh.equals("delete")){
                int ID = StdIn.readInt();
                warehouse.deleteProduct(ID);
            }
            count++;
        }
        StdOut.println(warehouse);
	// Use this file to test all methods
    }
}
