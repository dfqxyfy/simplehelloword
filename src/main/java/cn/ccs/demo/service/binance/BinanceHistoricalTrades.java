package cn.ccs.demo.service.binance;

import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "binanceHistoricalTrades")
public class BinanceHistoricalTrades {
    private String id;// 28457,
    private String price;// "4.00000100",
    private String qty;// "12.00000000",
    private String time;// 1499865549590,
    private String isBuyerMaker;// true,
    private String isBestMatch;// true

    private String symbol;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getQty() {
        return qty;
    }

    public void setQty(String qty) {
        this.qty = qty;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getIsBuyerMaker() {
        return isBuyerMaker;
    }

    public void setIsBuyerMaker(String isBuyerMaker) {
        this.isBuyerMaker = isBuyerMaker;
    }

    public String getIsBestMatch() {
        return isBestMatch;
    }

    public void setIsBestMatch(String isBestMatch) {
        this.isBestMatch = isBestMatch;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }
}
