package com.example.linj.myapplication.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * @author JLin.
 * @date 2019/12/31
 * @describe
 */
public class BaiduTokenResponse implements Serializable {

    private static final long serialVersionUID = -549146872911612724L;
    /**
     * refresh_token : 25.4195fc80225d7241b7fb50891e4985f2.315360000.1893117357.282335-18145653
     * expires_in : 2592000
     * session_key : 9mzdXqfdl1FjM0doZnMlZGfhpsxVlvL7wt6UnUApYZx6ZVeNyAfuGrQwIxCLTPMmjpfYdfSco+t0lhJr03uDEcvhSjAKQg==
     * access_token : 24.eb4b3bf131f92ff43288adf09802a8f3.2592000.1580349357.282335-18145653
     * scope : public vis-ocr_ocr brain_ocr_scope brain_ocr_general brain_ocr_general_basic vis-ocr_business_license brain_ocr_webimage brain_all_scope brain_ocr_idcard brain_ocr_driving_license brain_ocr_vehicle_license vis-ocr_plate_number brain_solution brain_ocr_plate_number brain_ocr_accurate brain_ocr_accurate_basic brain_ocr_receipt brain_ocr_business_license brain_solution_iocr brain_qrcode brain_ocr_handwriting brain_ocr_passport brain_ocr_vat_invoice brain_numbers brain_ocr_business_card brain_ocr_train_ticket brain_ocr_taxi_receipt vis-ocr_household_register vis-ocr_vis-classify_birth_certificate vis-ocr_台湾通行证 vis-ocr_港澳通行证 vis-ocr_机动车检验合格证识别 vis-ocr_车辆vin码识别 vis-ocr_定额发票识别 vis-ocr_保单识别 brain_ocr_vin brain_ocr_quota_invoice brain_ocr_birth_certificate brain_ocr_household_register brain_ocr_HK_Macau_pass brain_ocr_taiwan_pass brain_ocr_vehicle_certificate brain_ocr_insurance_doc wise_adapt lebo_resource_base lightservice_public hetu_basic lightcms_map_poi kaidian_kaidian ApsMisTest_Test权限 vis-classify_flower lpq_开放 cop_helloScope ApsMis_fangdi_permission smartapp_snsapi_base iop_autocar oauth_tp_app smartapp_smart_game_openapi oauth_sessionkey smartapp_swanid_verify smartapp_opensource_openapi smartapp_opensource_recapi fake_face_detect_开放Scope vis-ocr_虚拟人物助理 idl-video_虚拟人物助理
     * session_secret : f87deab653118fc1edbb2e3458da992b
     */
    @SerializedName("refresh_token")
    private String refreshToken;
    @SerializedName("expires_in")
    private String expiresIn;
    @SerializedName("session_key")
    private String sessionKey;
    @SerializedName("access_token")
    private String accessToken;
    private String scope;
    @SerializedName("session_secret")
    private String sessionSecret;

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(String expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getSessionKey() {
        return sessionKey;
    }

    public void setSessionKey(String sessionKey) {
        this.sessionKey = sessionKey;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public String getSessionSecret() {
        return sessionSecret;
    }

    public void setSessionSecret(String sessionSecret) {
        this.sessionSecret = sessionSecret;
    }
}
