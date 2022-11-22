package com.drivill.courier.merchantModul.model;

import android.content.Context;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.drivill.courier.BaseActivity;
import com.drivill.courier.R;
import com.drivill.courier.utils.AppUtil;

import java.io.Serializable;

public class ShipmentCreateModel implements Serializable {
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private Data data;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public class Data implements Serializable {

        @SerializedName("receiver_name")
        @Expose
        private String receiverName;
        @SerializedName("contact_no")
        @Expose
        private String contactNo;
        @SerializedName("product_detail")
        @Expose
        private String productDetail;
        @SerializedName("product_type")
        @Expose
        private String productType;
        @SerializedName("product_weight")
        @Expose
        private String productWeight;
        @SerializedName("note")
        @Expose
        private String note;
        @SerializedName("s_thana")
        @Expose
        private String sThana;
        @SerializedName("s_district")
        @Expose
        private String sDistrict;
        @SerializedName("s_division")
        @Expose
        private String sDivision;
        @SerializedName("d_thana")
        @Expose
        private String dThana;
        @SerializedName("d_district")
        @Expose
        private String dDistrict;
        @SerializedName("d_division")
        @Expose
        private String dDivision;
        @SerializedName("s_address")
        @Expose
        private String sAddress;
        @SerializedName("d_address")
        @Expose
        private String dAddress;
        @SerializedName("shipment_type")
        @Expose
        private String shipmentType;
        @SerializedName("merchant_id")
        @Expose
        private Integer merchantId;
        @SerializedName("shipment_no")
        @Expose
        private String shipmentNo;
        @SerializedName("pickup_date")
        @Expose
        private String pickupDate;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("id")
        @Expose
        private Integer id;

        @SerializedName("s_latitude")
        @Expose
        private String s_latitude;

        @SerializedName("s_longitude")
        @Expose
        private String s_longitude;

        @SerializedName("d_latitude")
        @Expose
        private String d_latitude;
        @SerializedName("d_longitude")
        @Expose
        private String d_longitude;

        @SerializedName("shipment_cost")
        @Expose
        private String shipment_cost;

        @SerializedName("cod_amount")
        @Expose
        private String cod_amount;

        @SerializedName("sdivision")
        @Expose
        private Sdivision sdivision;
        @SerializedName("ddivision")
        @Expose
        private Ddivision ddivision;
        @SerializedName("sdistrict")
        @Expose
        private Sdistrict sdistrict;
        @SerializedName("ddistrict")
        @Expose
        private Ddistrict ddistrict;
        @SerializedName("sthana")
        @Expose
        private Sthana sthana;
        @SerializedName("dthana")
        @Expose
        private Dthana dthana;
        @SerializedName("merchant")
        @Expose
        private Merchant merchant;

        public String getCod_amount() {
            return cod_amount;
        }

        public void setCod_amount(String cod_amount) {
            this.cod_amount = cod_amount;
        }

        public String getShipment_cost() {
            return shipment_cost;
        }

        public void setShipment_cost(String shipment_cost) {
            this.shipment_cost = shipment_cost;
        }

        public String getS_latitude() {
            return s_latitude;
        }

        public void setS_latitude(String s_latitude) {
            this.s_latitude = s_latitude;
        }

        public String getS_longitude() {
            return s_longitude;
        }

        public void setS_longitude(String s_longitude) {
            this.s_longitude = s_longitude;
        }

        public String getD_latitude() {
            return d_latitude;
        }

        public void setD_latitude(String d_latitude) {
            this.d_latitude = d_latitude;
        }

        public String getD_longitude() {
            return d_longitude;
        }

        public void setD_longitude(String d_longitude) {
            this.d_longitude = d_longitude;
        }

        public String getReceiverName() {
            return receiverName;
        }

        public void setReceiverName(String receiverName) {
            this.receiverName = receiverName;
        }

        public String getContactNo() {
            return contactNo;
        }

        public void setContactNo(String contactNo) {
            this.contactNo = contactNo;
        }

        public String getProductDetail() {
            return productDetail;
        }

        public void setProductDetail(String productDetail) {
            this.productDetail = productDetail;
        }

        public String getProductType() {
            return productType;
        }

        public void setProductType(String productType) {
            this.productType = productType;
        }

        public String getProductWeight() {
            return productWeight;
        }

        public void setProductWeight(String productWeight) {
            this.productWeight = productWeight;
        }

        public String getNote() {
            return note;
        }

        public void setNote(String note) {
            this.note = note;
        }

        public String getsThana() {
            return sThana;
        }

        public void setsThana(String sThana) {
            this.sThana = sThana;
        }

        public String getsDistrict() {
            return sDistrict;
        }

        public void setsDistrict(String sDistrict) {
            this.sDistrict = sDistrict;
        }

        public String getsDivision() {
            return sDivision;
        }

        public void setsDivision(String sDivision) {
            this.sDivision = sDivision;
        }

        public String getdThana() {
            return dThana;
        }

        public void setdThana(String dThana) {
            this.dThana = dThana;
        }

        public String getdDistrict() {
            return dDistrict;
        }

        public void setdDistrict(String dDistrict) {
            this.dDistrict = dDistrict;
        }

        public String getdDivision() {
            return dDivision;
        }

        public void setdDivision(String dDivision) {
            this.dDivision = dDivision;
        }

        public String getsAddress() {
            return sAddress;
        }

        public void setsAddress(String sAddress) {
            this.sAddress = sAddress;
        }

        public String getdAddress() {
            return dAddress;
        }

        public void setdAddress(String dAddress) {
            this.dAddress = dAddress;
        }

        public String getShipmentType() {
            return shipmentType;
        }

        public void setShipmentType(String shipmentType) {
            this.shipmentType = shipmentType;
        }


        public Integer getMerchantId() {
            return merchantId;
        }

        public void setMerchantId(Integer merchantId) {
            this.merchantId = merchantId;
        }

        public String getShipmentNo() {
            return shipmentNo;
        }

        public void setShipmentNo(String shipmentNo) {
            this.shipmentNo = shipmentNo;
        }

        public String getPickupDate() {
            return pickupDate;
        }

        public void setPickupDate(String pickupDate) {
            this.pickupDate = pickupDate;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public Sdivision getSdivision() {
            return sdivision;
        }

        public void setSdivision(Sdivision sdivision) {
            this.sdivision = sdivision;
        }

        public Ddivision getDdivision() {
            return ddivision;
        }

        public void setDdivision(Ddivision ddivision) {
            this.ddivision = ddivision;
        }

        public Sdistrict getSdistrict() {
            return sdistrict;
        }

        public void setSdistrict(Sdistrict sdistrict) {
            this.sdistrict = sdistrict;
        }

        public Ddistrict getDdistrict() {
            return ddistrict;
        }

        public void setDdistrict(Ddistrict ddistrict) {
            this.ddistrict = ddistrict;
        }

        public Sthana getSthana() {
            return sthana;
        }

        public void setSthana(Sthana sthana) {
            this.sthana = sthana;
        }

        public Dthana getDthana() {
            return dthana;
        }

        public void setDthana(Dthana dthana) {
            this.dthana = dthana;
        }

        public Merchant getMerchant() {
            return merchant;
        }

        public void setMerchant(Merchant merchant) {
            this.merchant = merchant;
        }

        public class Ddistrict implements Serializable {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("division_id")
            @Expose
            private Integer divisionId;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("bn_name")
            @Expose
            private String bnName;
            @SerializedName("lat")
            @Expose
            private String lat;
            @SerializedName("lon")
            @Expose
            private String lon;
            @SerializedName("url")
            @Expose
            private String url;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getDivisionId() {
                return divisionId;
            }

            public void setDivisionId(Integer divisionId) {
                this.divisionId = divisionId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getBnName() {
                return bnName;
            }

            public void setBnName(String bnName) {
                this.bnName = bnName;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

        }

        public class Ddivision implements Serializable {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("bn_name")
            @Expose
            private String bnName;
            @SerializedName("url")
            @Expose
            private String url;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getBnName() {
                return bnName;
            }

            public void setBnName(String bnName) {
                this.bnName = bnName;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

        }

        public class Dthana implements Serializable {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("district_id")
            @Expose
            private Integer districtId;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("bn_name")
            @Expose
            private String bnName;
            @SerializedName("url")
            @Expose
            private String url;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getDistrictId() {
                return districtId;
            }

            public void setDistrictId(Integer districtId) {
                this.districtId = districtId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getBnName() {
                return bnName;
            }

            public void setBnName(String bnName) {
                this.bnName = bnName;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

        }

        public class Merchant implements Serializable {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("mobile")
            @Expose
            private String mobile;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getMobile() {
                return mobile;
            }

            public void setMobile(String mobile) {
                this.mobile = mobile;
            }

        }

        public class Sdistrict implements Serializable {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("division_id")
            @Expose
            private Integer divisionId;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("bn_name")
            @Expose
            private String bnName;
            @SerializedName("lat")
            @Expose
            private String lat;
            @SerializedName("lon")
            @Expose
            private String lon;
            @SerializedName("url")
            @Expose
            private String url;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getDivisionId() {
                return divisionId;
            }

            public void setDivisionId(Integer divisionId) {
                this.divisionId = divisionId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getBnName() {
                return bnName;
            }

            public void setBnName(String bnName) {
                this.bnName = bnName;
            }

            public String getLat() {
                return lat;
            }

            public void setLat(String lat) {
                this.lat = lat;
            }

            public String getLon() {
                return lon;
            }

            public void setLon(String lon) {
                this.lon = lon;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

        }

        public class Sdivision implements Serializable {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("bn_name")
            @Expose
            private String bnName;
            @SerializedName("url")
            @Expose
            private String url;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getBnName() {
                return bnName;
            }

            public void setBnName(String bnName) {
                this.bnName = bnName;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

        }

        public class Sthana implements Serializable {

            @SerializedName("id")
            @Expose
            private Integer id;
            @SerializedName("district_id")
            @Expose
            private Integer districtId;
            @SerializedName("name")
            @Expose
            private String name;
            @SerializedName("bn_name")
            @Expose
            private String bnName;
            @SerializedName("url")
            @Expose
            private String url;

            public Integer getId() {
                return id;
            }

            public void setId(Integer id) {
                this.id = id;
            }

            public Integer getDistrictId() {
                return districtId;
            }

            public void setDistrictId(Integer districtId) {
                this.districtId = districtId;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getBnName() {
                return bnName;
            }

            public void setBnName(String bnName) {
                this.bnName = bnName;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

        }


        public boolean isValidData1(Context context) {
            if (getsDivision() == null || getsDivision().isEmpty() &&
                    getdDivision() == null || getdDivision().isEmpty()) {
                ((BaseActivity) context).showMessage(context.getString(R.string.pls_select_dvision));
                return false;
            } else if (getsDistrict() == null || getsDistrict().isEmpty() &&
                    getdDistrict() == null || getdDistrict().isEmpty()) {
                ((BaseActivity) context).showMessage(context.getString(R.string.pls_select_dist));
                return false;
            } else if (getsThana() == null || getsThana().isEmpty() &&
                    getdThana() == null || getdThana().isEmpty()) {
                ((BaseActivity) context).showMessage(context.getString(R.string.pls_select_thana));
                return false;
            }/* else if (getPickupDate() == null || getPickupDate().equals("")) {
                ((BaseActivity) context).showMessage(context.getString(R.string.pls_select_date));
                return false;
            }*/ else if (getsAddress() == null || getsAddress().isEmpty() ||
                    getdAddress() == null || getdAddress().isEmpty()) {
                ((BaseActivity) context).showMessage(context.getString(R.string.enter_address));
                return false;
            } else return true;

        }


        public boolean isValidData2(Context context) {
            if (getReceiverName() == null || getReceiverName().isEmpty()) {
                ((BaseActivity) context).showMessage(context.getString(R.string.pls_enter_riciv_name));
                return false;
            } else if (getContactNo() == null || !AppUtil.isValidPhone(getContactNo())) {
                if (getContactNo().isEmpty()) {
                    ((BaseActivity) context).showMessage(context.getString(R.string.pls_enter_riciv_contect));
                } else
                    ((BaseActivity) context).showMessage(context.getString(R.string.mobile_num_is_invalid));

                return false;
            } else if (getProductType() == null || getProductType().isEmpty()) {
                ((BaseActivity) context).showMessage(context.getString(R.string.select_product));
                return false;
            } else if (getProductWeight() == null || getProductWeight().isEmpty()) {
                ((BaseActivity) context).showMessage(context.getString(R.string.select_productW));
                return false;
            } /*else if (getProductDetail() == null || getProductDetail().isEmpty()) {
                ((BaseActivity) context).showMessage(context.getString(R.string.enter_product));
                return false;
            }*/
            else if (getPickupDate() == null || getPickupDate().equals("")) {
                ((BaseActivity) context).showMessage(context.getString(R.string.pls_select_date));
                return false;
            } else return true;

        }


        public boolean isValidData3(Context context) {

            if (getdDivision() == null || getdDivision().isEmpty()) {
                ((BaseActivity) context).showMessage(context.getString(R.string.pls_select_dvision));
                return false;
            } else if ( getdDistrict() == null || getdDistrict().isEmpty()) {
                ((BaseActivity) context).showMessage(context.getString(R.string.pls_select_dist));
                return false;
            } else if (getdThana() == null || getdThana().isEmpty()) {
                ((BaseActivity) context).showMessage(context.getString(R.string.pls_select_thana));
                return false;
            }/* else if (getPickupDate() == null || getPickupDate().equals("")) {
                ((BaseActivity) context).showMessage(context.getString(R.string.pls_select_date));
                return false;
            }*/ else if (getdAddress() == null || getdAddress().isEmpty()) {
                ((BaseActivity) context).showMessage(context.getString(R.string.enter_address));
                return false;
            }
           else if (getReceiverName() == null || getReceiverName().isEmpty()) {
                ((BaseActivity) context).showMessage(context.getString(R.string.pls_enter_riciv_name));
                return false;
            } else if (getContactNo() == null || !AppUtil.isValidPhone(getContactNo())) {
                if (getContactNo().isEmpty()) {
                    ((BaseActivity) context).showMessage(context.getString(R.string.pls_enter_riciv_contect));
                } else
                    ((BaseActivity) context).showMessage(context.getString(R.string.mobile_num_is_invalid));

                return false;
            } else if (getProductType() == null || getProductType().isEmpty()) {
                ((BaseActivity) context).showMessage(context.getString(R.string.select_product));
                return false;
            } else if (getProductWeight() == null || getProductWeight().isEmpty()) {
                ((BaseActivity) context).showMessage(context.getString(R.string.select_productW));
                return false;
            } /*else if (getProductDetail() == null || getProductDetail().isEmpty()) {
                ((BaseActivity) context).showMessage(context.getString(R.string.enter_product));
                return false;
            }*/
            else if (getPickupDate() == null || getPickupDate().equals("")) {
                ((BaseActivity) context).showMessage(context.getString(R.string.pls_select_date));
                return false;
            } else return true;

        }
    }
}
