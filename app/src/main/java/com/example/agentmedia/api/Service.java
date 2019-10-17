package com.example.agentmedia.api;

import com.example.agentmedia.model.PaymentItem;
import com.example.agentmedia.model.PriceItem;
import com.example.agentmedia.model.ProviderItem;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface  Service {
    @GET("payment_account/allpayment")
    Call<List<PaymentItem>> getAllListPayment();

    //pulsa
    @GET("product_type/allProvider?type=1")
    Call<List<ProviderItem>> getAllListProviderPulsa();

    //paket Data
    @GET("product_type/allProvider?type=3")
    Call<List<ProviderItem>> getAllListProviderPaket();

    //getProduct
    @GET("product/ajax_action_get_product")
    Call<List<PriceItem>> getAllPrice(@Query("cat") String cat, @Query("api") String api, @Query("type") String type);
}
