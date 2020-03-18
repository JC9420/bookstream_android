package com.workpath.bookstreamapp.moudles.goods;

import com.workpath.bookstreamapp.moudles.Base;

/**
 * Created by JayChen on 2020/3/18.
 */

public class BuyCoogsMoudle extends Base {

    int imageId;
    String bookName;
    String fromName;
    int order;
    double price;
    int whoGet;//0表示平台，1表示私人

    public BuyCoogsMoudle(int imageId, String bookName, String fromName, int order, double price, int whoGet) {
        this.imageId = imageId;
        this.bookName = bookName;
        this.fromName = fromName;
        this.order = order;
        this.price = price;
        this.whoGet = whoGet;
    }

    public int getImageId() {
        return imageId;
    }

    public void setImageId(int imageId) {
        this.imageId = imageId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getFromName() {
        return fromName;
    }

    public void setFromName(String fromName) {
        this.fromName = fromName;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getWhoGet() {
        return whoGet;
    }

    public void setWhoGet(int whoGet) {
        this.whoGet = whoGet;
    }
}
