package com.example.agentmedia.api;

import com.example.agentmedia.model.PaymentItem;
import com.example.agentmedia.model.PriceItem;
import com.example.agentmedia.model.ProviderItem;
import com.example.agentmedia.model.topup.RiwayatTopupItem;
import com.google.gson.JsonObject;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface  Service {
    @GET("payment_account/allpayment")
    Call<List<PaymentItem>> getAllListPayment();


    //gethistoryTopup
    @FormUrlEncoded
    @POST("topup/ajax_action_get_history_topup")
    Call<List<RiwayatTopupItem>> getHistoryTopup(@Field("id_member") String id_member);

    //pulsa
    @GET("product_type/allProvider?type=1")
    Call<List<ProviderItem>> getAllListProviderPulsa();

    //paket Data
    @GET("product_type/allProvider?type=3")
    Call<List<ProviderItem>> getAllListProviderPaket();

    //getProduct
    @GET("product/ajax_action_get_product")
    Call<List<PriceItem>> getAllPrice(@Query("cat") String cat, @Query("api") String api, @Query("type") String type);

    // Fungsi register
    @FormUrlEncoded
    @POST("member/ajax_action_register")
    Call<ResponseBody> registerRequest(@Field("name_member") String nama,
                                       @Field("password_member") String password,
                                       @Field("no_hp") String no,
                                       @Field("ktp_member") String ktp);

    // Fungsi login
    @FormUrlEncoded
    @POST("login/ajax_action_login")
    Call<ResponseBody> PassswordRequest(@Field("no_hp") String no_hp,
                                       @Field("password_member") String password_member);

    // Fungsi Topup
    @FormUrlEncoded
    @POST("topup/ajax_action_topup")
    Call<ResponseBody> topupRequest(@Field("id_member") String id_member,
                                        @Field("nominal") String nominal,
                                        @Field("id_payment_account") String id_payment_account);
    // Fungsi konfirmasi topup
    @Multipart
    @POST("topup/ajax_action_topup_konfirmation")
    Call<JsonObject> topupKonfirmationRequest(@Part MultipartBody.Part file,
                                                @Part("id_member") RequestBody nominal,
                                                @Part("id_topup") RequestBody  id_topup);


    // Fungsi getDetail topup
    @FormUrlEncoded
    @POST("topup/ajax_action_get_detail_topup")
    Call<JsonObject> topupDetailRequest(@Field("id_member") String id_member,
                                        @Field("id_topup") String id_topup);


    // Fungsi getSaldo
    @FormUrlEncoded
    @POST("member/ajax_action_get_member")
    Call<JsonObject> getSaldoRequest(@Field("id_member") String id_member);

}
