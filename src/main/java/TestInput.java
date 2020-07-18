public enum TestInput {
    URL_GMAIL("https://gmail.com/"),
    USERNAME("mytesting.selemium123@gmail.com"),
    USERNAME_BLANK(""),
    INVALID_USERNAME("?&%$"),
    PASSWORD("Rockwell@123"),

    EBAY_URL("https://www.ebay.com/"),
    EBAY_SEARCH_ITEM("Electric Guitar");

    public String value;
    TestInput(String value)
    {
        this.value = value;
    }

    public String getValue()
    {
        return value;
    }
}
