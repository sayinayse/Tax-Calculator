import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;

class Item{
	private String name;
	private double price;
	private int quantity;
	private Type type;
	private boolean isExempted;
	private boolean isImported;
	
	public Item(int quantity, String name, double price){
		this.name = name;
		this.setPrice(price);
		this.setQuantity(quantity);
		this.setIsExempted();
		this.setIsImported();
	}
	
	public double getPrice(){
		return price;
	}

	public void setPrice(double price){
		this.price = price;
	}
	
	public String getName(){
		return name;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public int getQuantity(){
		return quantity;
	}
	
	public void setQuantity(int qnt){
		this.quantity = qnt;
	}
	
	public boolean getIsExempted(){
		return isExempted;
	}
	
	public void setIsExempted(){
		if(this.getName().contains("book") || this.getName().contains("pill") || this.getName().contains("chocolate")){
			this.isExempted = true;
		}
		else{
			this.isExempted = false;
		}
		
	}
	
	public boolean getIsImported(){
		return isImported;
	}
	
	public void setIsImported(){
		if(this.name.contains("imported")){
			this.isImported = true;
		}
		else{
			this.isImported = false;
		}
	
	}
	
	public void printItem(){
		System.out.println("Quantity: " + this.getQuantity() + 
							" Item: " + this.getName() + 
							" Price: " + this.getPrice());
	}
	
}

class Receipt{
	private double total;
	private double taxAmount = 0;
	private double totalSale = 0;
	
	public double getTotal(){
		return total;
	}
	
	public void setTotal(double totalPrice){
		this.total = totalPrice;
	}
	
	public double getTaxAmount(){
		return taxAmount;
	}
	
	public void calculatePrintReceipt(ArrayList<Item> itemList){
		for(int i = 0; i < itemList.size(); i++)
		{	
			if(itemList.get(i).getIsExempted() && itemList.get(i).getIsImported() != true){
				
				this.setTotal(itemList.get(i).getPrice());
				totalSale += this.getTotal();
				
				System.out.println(itemList.get(i).getQuantity() +
									" " + itemList.get(i).getName() + 
									": " + this.getTotal());
			}
			
			if(itemList.get(i).getIsExempted() && itemList.get(i).getIsImported()){
				
				taxAmount += (itemList.get(i).getPrice() * 0.05);
				this.setTotal(itemList.get(i).getPrice() + (itemList.get(i).getPrice() * 0.05));	
				totalSale += this.getTotal();
				
				System.out.println(itemList.get(i).getQuantity() +
									" " + itemList.get(i).getName() + 
									": " + this.getTotal());
			}
			
			if(itemList.get(i).getIsExempted() != true && itemList.get(i).getIsImported()){
				
				taxAmount += (itemList.get(i).getPrice() * 0.15);
				this.setTotal(itemList.get(i).getPrice() + (itemList.get(i).getPrice() * 0.15));
				totalSale += this.getTotal();
				
				System.out.println(itemList.get(i).getQuantity() + 
									" " + itemList.get(i).getName() + 
									" : " + this.getTotal());
			}
			
			if(itemList.get(i).getIsExempted() != true && itemList.get(i).getIsImported() != true){
				
				taxAmount += (itemList.get(i).getPrice() * 0.10);
				this.setTotal(itemList.get(i).getPrice() + (itemList.get(i).getPrice() * 0.10));
				totalSale += this.getTotal();
				
				System.out.println(itemList.get(i).getQuantity() +
									" " + itemList.get(i).getName() + 
									": " + this.getTotal());
			}
			
			
		}
		System.out.println(" ");
		System.out.println("Tax Amount: " + taxAmount);
		System.out.println("Total Sale: " + totalSale);
	}
	
}


public class Main{
	
	public static void main(String[] args){
	
	ArrayList<Item> itemList = new ArrayList<Item>();
	ArrayList<Item> itemList1 = new ArrayList<Item>();
	ArrayList<Item> itemList2 = new ArrayList<Item>();
	ArrayList<ArrayList<Item>> listOfItemList = new ArrayList<ArrayList<Item>>();
		
	try {
      File file = new File("test1.txt");
      Scanner input = new Scanner(file);
      while (input.hasNextLine()) {
        String line = input.nextLine();  //take the line
		String[] words = line.split(" "); //take words
		int quantity = Integer.parseInt(words[0]); //first one is quantity
		String name = words[1];
		float price = Float.parseFloat(words[3]);
		Item item = new Item(quantity, name, price); 
		itemList.add(item);	
        
      }
      listOfItemList.add(itemList);
      input.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred while reading the file.");
      e.printStackTrace();
    }
    
    	try {
      File file = new File("test2.txt");
      Scanner input = new Scanner(file);
      while (input.hasNextLine()) {
        String line = input.nextLine();  //take the line
		String[] words = line.split(" "); //take words
		int quantity = Integer.parseInt(words[0]); //first one is quantity
		String name = words[1];
		float price = Float.parseFloat(words[3]);
		Item item = new Item(quantity, name, price); 
		itemList1.add(item);	

      }
      listOfItemList.add(itemList1);
      input.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred while reading the file.");
      e.printStackTrace();
    }
		
		try {
      File file = new File("test3.txt");
      Scanner input = new Scanner(file);
      while (input.hasNextLine()) {
        String line = input.nextLine();  //take the line
		String[] words = line.split(" "); //take words
		int quantity = Integer.parseInt(words[0]); //first one is quantity
		String name = words[1];
		float price = Float.parseFloat(words[3]);
		Item item = new Item(quantity, name, price); 
		itemList2.add(item);	
   
      }
      listOfItemList.add(itemList2);
      input.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred while reading the file.");
      e.printStackTrace();
    }
		
		for(int i = 0; i < listOfItemList.size(); i++)
		{
			System.out.println("Receipt " + (i + 1));
			Receipt receipt = new Receipt();
			receipt.calculatePrintReceipt(listOfItemList.get(i));
			System.out.println("**End of the Receipt " + (i + 1) + "**");
			System.out.println(" ");
		}
	
	}

}
