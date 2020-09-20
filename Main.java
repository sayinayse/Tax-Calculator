// Ayşe SAYIN
import java.io.File; 
import java.io.FileNotFoundException; 
import java.util.Scanner; 
import java.util.ArrayList; 
import java.math.*;
import java.math.BigDecimal;

class Item{
	private String name;
	private BigDecimal price;
	private int quantity;
	private boolean isExempted;
	private boolean isImported;
	
	public Item(int quantity, String name, BigDecimal price){
		this.name = name;
		this.setPrice(price);
		this.setQuantity(quantity);
		this.setIsExempted();
		this.setIsImported();
	}
	
	public BigDecimal getPrice(){
		return price;
	}

	public void setPrice(BigDecimal price){
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
		if(this.getName().contains("imported")){
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
	private BigDecimal total;	//Used to keep total price with tax.
	private BigDecimal taxAmount = new BigDecimal("0"); //Used to keep total tax amount for every receipt.
	private BigDecimal totalSale = new BigDecimal("0"); //Used to keep total sale amount for every receipt.
	private BigDecimal itemTax = new BigDecimal("0"); //Used to keep tax amount of every item in receipt.
	private BigDecimal importedTaxRatio = new BigDecimal("0.05");
	private BigDecimal nonExemptedTaxRatio = new BigDecimal("0.10");
	private BigDecimal exemptedAndImported = new BigDecimal("0.15");
	
	public BigDecimal getTotal(){
		return total;
	}
	
	public void setTotal(BigDecimal totalPrice){
		this.total = totalPrice;
	}
	
	public BigDecimal getTaxAmount(){
		return taxAmount;
	}
	
	public BigDecimal getTotalSale(){
		return totalSale;
	}
	
	public BigDecimal roundNumbers(BigDecimal value){
		MathContext m = new MathContext(2);  // 2 precision
		BigDecimal roundedValue = value.round(m);
		return roundedValue;
	}
	
	public void calculatePrintReceipt(ArrayList<Item> itemList){
		/*
		 * This function takes items in a receipt have. 
		 * Calculate their tax amount with considering exemption and importation.
		 * Then prints the receipt.
		*/
		for(int i = 0; i < itemList.size(); i++)
		{	
			//If an item in itemList is exempted and not imported then there will be no tax.
			if(itemList.get(i).getIsExempted() && itemList.get(i).getIsImported() != true){
				this.setTotal(itemList.get(i).getPrice());
				totalSale = totalSale.add(this.getTotal());
				
				System.out.println(itemList.get(i).getQuantity() +
									" " + itemList.get(i).getName() + 
									": " + this.getTotal());
			}
			
			//If an item in itemList is exempted and imported then there will be tax that is equal to (price*0.05).
			if(itemList.get(i).getIsExempted() && itemList.get(i).getIsImported()){
				
				itemTax = roundNumbers(itemList.get(i).getPrice().multiply(importedTaxRatio));	//Amount of tax that is taken from the item.
				taxAmount = taxAmount.add((itemTax));	//Add tax amount of a single item to total tax amount for receipt.
				
				this.setTotal(itemList.get(i).getPrice().add(itemTax));	 //Set new total price with adding tax for the item.
				totalSale = totalSale.add(this.getTotal());				//Add new total price of a single item to receipt's total amount.
				
				System.out.println(itemList.get(i).getQuantity() +		//Print receipt.
									" " + itemList.get(i).getName() + 
									": " + this.getTotal());
			}
			
			//If an item in itemList is not exempted and imported then there will be tax that is equal to (price*0.15).
			if(itemList.get(i).getIsExempted() != true && itemList.get(i).getIsImported()){	
				
				itemTax = roundNumbers(itemList.get(i).getPrice().multiply(exemptedAndImported)); 
				taxAmount = taxAmount.add(itemTax);		 
				
				this.setTotal(itemList.get(i).getPrice().add(itemTax));	
				totalSale = totalSale.add(this.getTotal());				
					
				System.out.println(itemList.get(i).getQuantity() + 		
									" " + itemList.get(i).getName() + 
									" : " + this.getTotal());
			}
			
			//If an item in itemList is not exempted and not imported then there will be tax that is equal to (price*0.10).
			if(itemList.get(i).getIsExempted() != true && itemList.get(i).getIsImported() != true){				
				
				itemTax = roundNumbers(itemList.get(i).getPrice().multiply(nonExemptedTaxRatio));
				taxAmount = taxAmount.add(itemTax);
				
				this.setTotal(itemList.get(i).getPrice().add(itemTax));	
				totalSale = totalSale.add(this.getTotal());		
				
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
	
	/*
	 * Different text files are used for every input. 
	 * ItemLists are used to keep items in a receipt.
	 */
	ArrayList<Item> itemList = new ArrayList<Item>(); 	
	ArrayList<Item> itemList1 = new ArrayList<Item>();
	ArrayList<Item> itemList2 = new ArrayList<Item>();
	ArrayList<ArrayList<Item>> listOfItemList = new ArrayList<ArrayList<Item>>();
		
	try {
		/*
		 * Text file is read for receipt 1. Items are created in here.
		 */
		File file = new File("test1.txt");
		Scanner input = new Scanner(file);
		while (input.hasNextLine()) {
			String line = input.nextLine();  	//Take the line.
			String[] words = line.split(" "); 	//Split lines into words.
			int quantity = Integer.parseInt(words[0]); //First word is quantity of the item.
			String name = words[1];						//Second word is name of the item.
			String price = words[3];	//Third one is price of the item.
			BigDecimal bigDecPrice = new BigDecimal(price);
			Item item = new Item(quantity, name, bigDecPrice); //Item is created and then added to related itemList.
			itemList.add(item);	    
		}
      listOfItemList.add(itemList);	//itemList of receipt 1 is added to the list of itemLists. 
      input.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred while reading the file.");
      e.printStackTrace();
    }
    
    try {
		/*
		 * Text file is read for receipt 2. Items are created in here.
		 */
		File file = new File("test2.txt");
		Scanner input = new Scanner(file);
		while (input.hasNextLine()) {
			String line = input.nextLine();  //Take the line.
			String[] words = line.split(" "); //Split lines into words.
			int quantity = Integer.parseInt(words[0]); //First word is quantity of the item.
			String name = words[1];						//Second word is name of the item.
			String price = words[3];	//Third one is price of the item.
			BigDecimal bigDecPrice = new BigDecimal(price);
			Item item = new Item(quantity, name, bigDecPrice); //Item is created and then added to related itemList.
			itemList1.add(item);	
		  }
		listOfItemList.add(itemList1);	//itemList of receipt 2 is added to the list of itemLists. 
		input.close();
	} catch (FileNotFoundException e) {
      System.out.println("An error occurred while reading the file.");
      e.printStackTrace();
    }
		
	try {
		/*
		 * Text file is read for receipt 3. Items are created in here.
		 */
		File file = new File("test3.txt");
		Scanner input = new Scanner(file);
		while (input.hasNextLine()) {
			String line = input.nextLine();  //Take the line.
			String[] words = line.split(" "); //Split lines into words.
			int quantity = Integer.parseInt(words[0]); //First word is quantity of the item.
			String name = words[1];						//Second word is name of the item.
			String price = words[3];	//Third one is price of the item.
			BigDecimal bigDecPrice = new BigDecimal(price);
			Item item = new Item(quantity, name, bigDecPrice); //Item is created and then added to related itemList.
			itemList2.add(item);	
	   
		  }
		listOfItemList.add(itemList2);	//itemList of receipt 3 is added to the list of itemLists. 
		input.close();
    } catch (FileNotFoundException e) {
      System.out.println("An error occurred while reading the file.");
      e.printStackTrace();
    }
		
		//Total tax amount, total sale amount are calculated for every receipt.
		//Then output is displayed on the screen.
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
