package com.owais.oddity.API.Responses.ProductsByCategory;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class productsByCategoryResponse {
    @SerializedName("success")
    @Expose
    private Integer success;
    @SerializedName("message")
    @Expose
    private Message message;

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Message getMessage() {
        return message;
    }

    public void setMessage(Message message) {
        this.message = message;
    }

}
