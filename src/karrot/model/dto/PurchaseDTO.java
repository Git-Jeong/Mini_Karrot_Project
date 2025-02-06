package karrot.model.dto;

public class PurchaseDTO {
    private int purchaseId; //PURCㅍASEID 값을 넣을 것
    private String UserId; //USERID를 넣을 것
    private int productId; // PRODUCTID 값을 넣을 것 
    private String pruductName;
    private int price;

    public int getPrice() {
    	return price;
    }
    
    public String getName() {
    	return pruductName;
    } 

    public int getProductId() {
        return productId;
    }

    public String getUserId() {
        return UserId;
    }

    public int getPurchaseId() {
        return purchaseId;
    }

    public PurchaseDTO(int purchaseId, String pruductName, int price ) {
        this.purchaseId = purchaseId;
        this.pruductName = pruductName;
        this.price = price; 
    }

}
