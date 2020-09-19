enum Type{
	BOOK,
	FOOD,
	MEDICAL,
	OTHER;
}

class Item{
	private String name;
	private double price;
	private Type type;
	private boolean isExempted;
	private boolean isImported;
	
	public Item(String name, double price, Type itemType){
		this.name = name;
		this.type = itemType;
		this.setPrice(price);
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
	
}

class Receipt{
	private double total;
	
	public double getTotal(){
		return total;
	}
	
	public void setTotal(double totalPrice){
		this.total = totalPrice;
	}
	
	public void calculateTax(Item item){
		if(item.getIsExempted() && item.getIsImported() != true){
			this.setTotal(item.getPrice());
		}
		
		if(item.getIsExempted() && item.getIsImported()){
			this.setTotal(item.getPrice() + (item.getPrice() * 0.05));	
		}
		
		if(item.getIsExempted() != true && item.getIsImported()){
			this.setTotal(item.getPrice() + (item.getPrice() * 0.15));
		}
		um. Complex
		if(item.getIsExempted() != true && item.getIsImported() != true){
			this.setTotal(item.getPrice() + (item.getPrice() * 0.10));
		}
		
	}
}

public class Main{
	
	public static void main(String[] args){
		
		Item item1 = new Item("book", 12.49, Type.BOOK);
		Item item2 = new Item("music CD", 14.99 ,Type.OTHER);
		/*System.out.println(item1.getName());
		System.out.println(" ");
		System.out.println(item1.getIsExempted());
		System.out.println(" ");
		System.out.println(item1.getIsImported());*/
	
	
		Receipt receipt1 = new Receipt();
		receipt1.calculateTax(item1);
		System.out.println(receipt1.getTotal());
		
		Receipt receipt2 = new Receipt();
		receipt2.calculateTax(item2);
		System.out.println(receipt2.getTotal());
		
	
	
	}



}
