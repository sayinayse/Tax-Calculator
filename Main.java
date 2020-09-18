enum Type{
	BOOK,
	FOOD,
	MEDICAL,
	OTHER;
}

class Item{
	private String name;
	private float price;
	private Type type;
	private boolean isExempted;
	private boolean isImported;
	
	public Item(String name, float price, Type itemType, boolean isExempted, boolean isImported){
		this.name = name;
		this.type = itemType;
		this.isExempted = isExempted;
		this.isImported = isImported;
		this.setPrice(price);
	}
	
	public float getPrice(){
		return price;
	}

	public void setPrice(float price){
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
	
	public void setIsExempted(boolean isExempted){
		this.isExempted = isExempted;
	}
	
	
	public boolean getIsImported(){
		return isImported;
	}
	
	public void setIsImported(boolean isImported){
		this.isImported = isImported;
	}
	
}


public class Main{
	
	public static void main(String[] args){
		
		Item item1 = new Item("book", 12.49, );
	
		//Receipt receipt1 = new Receipt();
	
	
	}



}
