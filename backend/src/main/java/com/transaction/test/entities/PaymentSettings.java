package com.transaction.test.entities;

public class PaymentSettings {
    private String app_token;
    private String app_secret;
    private Boolean accept_card = true;
    private long amount;
    private String success_link;
    private String fail_link;
    private long session_timeout_secs = 1200;
    private String developer_tracking_id;

    public PaymentSettings(String app_token, String app_secret, Boolean accept_card, long amount, String success_link, String fail_link, long session_timeout_secs, String developer_tracking_id) {
        this.app_token = app_token;
        this.app_secret = app_secret;
        this.accept_card = accept_card;
        this.amount = amount;
        this.success_link = success_link;
        this.fail_link = fail_link;
        this.session_timeout_secs = session_timeout_secs;
        this.developer_tracking_id = developer_tracking_id;
    }

    public PaymentSettings(String app_token, String app_secret, long amount, String success_link, String fail_link, long session_timeout_secs, String developer_tracking_id) {
        this.app_token = app_token;
        this.app_secret = app_secret;
        this.amount = amount;
        this.success_link = success_link;
        this.fail_link = fail_link;
        this.session_timeout_secs = session_timeout_secs;
        this.developer_tracking_id = developer_tracking_id;
    }

    public PaymentSettings(String app_token, String app_secret, Boolean accept_card, long amount, String success_link, String fail_link, String developer_tracking_id) {
        this.app_token = app_token;
        this.app_secret = app_secret;
        this.accept_card = accept_card;
        this.amount = amount;
        this.success_link = success_link;
        this.fail_link = fail_link;
        this.developer_tracking_id = developer_tracking_id;
    }

    public PaymentSettings(String app_token, String app_secret, long amount, String success_link, String fail_link, String developer_tracking_id) {
        this.app_token = app_token;
        this.app_secret = app_secret;
        this.amount = amount;
        this.success_link = success_link;
        this.fail_link = fail_link;
        this.developer_tracking_id = developer_tracking_id;
    }

    public String getApp_token() {
        return app_token;
    }

    public void setApp_token(String app_token) {
        this.app_token = app_token;
    }

    public String getApp_secret() {
        return app_secret;
    }

    public void setApp_secret(String app_secret) {
        this.app_secret = app_secret;
    }

    public Boolean getAccept_card() {
        return accept_card;
    }

    public void setAccept_card(Boolean accept_card) {
        this.accept_card = accept_card;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

    public String getSuccess_link() {
        return success_link;
    }

    public void setSuccess_link(String success_link) {
        this.success_link = success_link;
    }

    public String getFail_link() {
        return fail_link;
    }

    public void setFail_link(String fail_link) {
        this.fail_link = fail_link;
    }

    public long getSession_timeout_secs() {
        return session_timeout_secs;
    }

    public void setSession_timeout_secs(long session_timeout_secs) {
        this.session_timeout_secs = session_timeout_secs;
    }

    public String getDeveloper_tracking_id() {
        return developer_tracking_id;
    }

    public void setDeveloper_tracking_id(String developer_tracking_id) {
        this.developer_tracking_id = developer_tracking_id;
    }
}
