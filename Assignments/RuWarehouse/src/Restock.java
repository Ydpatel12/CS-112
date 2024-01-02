package warehouse;

public class Restock {
    public static void main(String[] args) {
        StdIn.setFile(args[0]);
        StdOut.setFile(args[1]);
        
        Warehouse warehouse = new Warehouse();

        int numProductDescriptions = StdIn.readInt();
        int count = 0;

        while (count < numProductDescriptions){
            if(StdIn.readString().equals("add")){
                int currentDay = StdIn.readInt();
                int productID = StdIn.readInt();
                String productName = StdIn.readString();
                int initialProductStock = StdIn.readInt();
                int initialProductDemand = StdIn.readInt();
                warehouse.addProduct(productID, productName, initialProductStock, currentDay, initialProductDemand);
            }
            else{
                int ID = StdIn.readInt();
                int restock = StdIn.readInt();
                warehouse.restockProduct(ID, restock);
            }
    
            count++;

        }
        StdOut.println(warehouse);

	// Uset his file to test restock
    }
}
