package warehouse;

public class PurchaseProduct {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);

        Warehouse warehouse = new Warehouse();

        int numProductDescriptions = StdIn.readInt();
        int count = 0;

        while(count < numProductDescriptions){
            if(StdIn.readString().equals("add")){

                int currentDay = StdIn.readInt();
                int productID = StdIn.readInt();
                String productName = StdIn.readString();
                int initialProductStock = StdIn.readInt();
                int initialProductDemand = StdIn.readInt();
                warehouse.addProduct(productID, productName, initialProductStock, currentDay, initialProductDemand);
         }

            else{
                int day = StdIn.readInt();
                int ID = StdIn.readInt();
                int amount = StdIn.readInt();
                warehouse.purchaseProduct(ID, day, amount);
            }
            
            count++;
        }

        StdOut.println(warehouse);
	// Use this file to test purchaseProduct
    }
}
