import java.io.File;  // Import the File class
import java.io.FileNotFoundException;  // Import this class to handle errors
import java.util.Scanner; // Import the Scanner class to read text files
import java.util.ArrayList;

enum Type{
	BOOK,
	FOOD,
	MEDICAL,
	OTHER;
}

class Item{
	private String name;
	private double price;
	private int quantity;
	private Type type;
	private boolean isExempted;
	private boolean isImported;
	
	public Item(int quantity, String name, double price, Type itemType){
		this.name = name;
		this.type = itemType;
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
		if(this.getName().contains("book") || this.getName().contains("phill") || this.getName().contains("chocolate")){
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
		System.out.println("Quantity: " + this.getQuantity() + " Item: " + this.getName() + " Price: " + this.getPrice());
	}
	
}

class Receipt{
	private double total;
	
	public double getTotal(){
		return total;
	}
	
	public void setTotal(double totalPrice){
		this.total = totalPrice;
	}
	
	public void calculateTax(ArrayList<Item> itemList){
		for(int i = 0; i < itemList.size(); i++)
		{	
			if(itemList.get(i).getIsExempted() && itemList.get(i).getIsImported() != true){
				this.setTotal(itemList.get(i).getPrice());
				System.out.println("Quantity: " + itemList.get(i).getQuantity() + " Item: " + itemList.get(i).getName() + " Total Price: " + this.getTotal());
			}
			
			if(itemList.get(i).getIsExempted() && itemList.get(i).getIsImported()){
				this.setTotal(itemList.get(i).getPrice() + (itemList.get(i).getPrice() * 0.05));	
				System.out.println("Quantity: " + itemList.get(i).getQuantity() + " Item: " + itemList.get(i).getName() + " Total Price: " + this.getTotal());
			}
			
			if(itemList.get(i).getIsExempted() != true && itemList.get(i).getIsImported()){
				this.setTotal(itemList.get(i).getPrice() + (itemList.get(i).getPrice() * 0.15));
				System.out.println("Quantity: " + itemList.get(i).getQuantity() + " Item: " + itemList.get(i).getName() + " Total Price: " + this.getTotal());
			}
			
			if(itemList.get(i).getIsExempted() != true && itemList.get(i).getIsImported() != true){
				this.setTotal(itemList.get(i).getPrice() + (itemList.get(i).getPrice() * 0.10));
				System.out.println("Quantity: " + itemList.get(i).getQuantity() + " Item: " + itemList.get(i).getName() + " Total Price: " + this.getTotal());
			}
			
		}
		
	}
	
	/*public void printReceipt(ArrayList<Item> itemList){
		for(int i = 0; i < itemList.size(); i++)
		{
			System.out.println("Quantity: " + itemList.get(i).getQuantity() + " Item: " + itemList.get(i).getName() + " Total Price: " + this.getTotal(itemList.get(i).getPrice()));
		}
	
	}*/
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
		Item item = new Item(quantity, name, price, Type.OTHER); //şimdilik TYPE koydum. kaldır bunu.
		itemList.add(item);	
        
        //System.out.println(line);
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
		Item item = new Item(quantity, name, price, Type.OTHER); //şimdilik TYPE koydum. kaldır bunu.
		itemList1.add(item);	
        
        //System.out.println(line);
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
		Item item = new Item(quantity, name, price, Type.OTHER); //şimdilik TYPE koydum. kaldır bunu.
		itemList2.add(item);	
        
        //System.out.println(line);
      }
      listOfItemList.add(itemList2);
      input.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred while reading the file.");
      e.printStackTrace();
    }
		
	
		/*for(int i = 0; i < listOfItemList.size(); i++)
		{
			for(int j = 0; j < listOfItemList.get(i).size(); j++)
			{
				listOfItemList.get(i).get(j).printItem();
			}
			System.out.println("**");
		}*/
		
		for(int i = 0; i < listOfItemList.size(); i++)
		{
			Receipt receipt = new Receipt();
			receipt.calculateTax(listOfItemList.get(i));
			//receipt.printReceipt(listOfItemList.get(i));
			System.out.println("****");
		}
	
	/*
		Item item1 = new Item(1, "book", 12.49, Type.BOOK);
		Item item2 = new Item(1, "music CD", 14.99 ,Type.OTHER);
		//item1.printItem();
	
	
		Receipt receipt1 = new Receipt();
		receipt1.calculateTax(item1);
		System.out.println(receipt1.getTotal());
		
		Receipt receipt2 = new Receipt();
		receipt2.calculateTax(item2);
		System.out.println(receipt2.getTotal());
		*/
	
	
	}



}
