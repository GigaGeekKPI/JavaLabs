package com.Jlabs.ApiGateway.Comment;

public class Comment {
    private Integer id;
    private String content;
    private Integer customerId;
    private Integer clothesId;

    Comment() {}

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public Integer getClothesId() {
        return clothesId;
    }

    public void setClothesId(Integer clothesId) { this.clothesId = clothesId; }
}