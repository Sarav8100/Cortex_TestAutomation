public enum Locators {

    //gmail page UI elements
    GMAIL_USERNAME("identifierId"),
    GMAIL_PROFILE_ICON("img[class='gb_Ka gbii']"),

    //eBay page UI elements
    EBAY_SEARCH("input[placeholder='Search for anything']"),
    EBAY_ITEM_LIST("li[class='s-item    s-item--watch-at-corner']"),
    EBAY_ITEM_SELECT("div[class='s-item__image']"),
    EBAY_ITEM_PRICE_US("prcIsum");

    public String value;
    Locators(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
