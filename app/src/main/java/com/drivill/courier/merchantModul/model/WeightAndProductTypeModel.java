package com.drivill.courier.merchantModul.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class WeightAndProductTypeModel {

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


    public class Data {

        @SerializedName("product_type")
        @Expose
        private List<ProductType> productType = null;
        @SerializedName("weight_list")
        @Expose
        private List<WeightList> weightList;

        public List<ProductType> getProductType() {
            return productType;
        }

        public void setProductType(List<ProductType> productType) {
            this.productType = productType;
        }

        public List<WeightList> getWeightList() {
            return weightList;
        }


    }

    public static class ProductType {

        @SerializedName("id")
        @Expose
        private Integer id;
        @SerializedName("name")
        @Expose
        private String name;
        @SerializedName("status")
        @Expose
        private String status;
        @SerializedName("created_at")
        @Expose
        private String createdAt;
        @SerializedName("updated_at")
        @Expose
        private String updatedAt;

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

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        @NotNull
        @Override
        public String toString() {
            return name;
        }
    }


    public static class WeightList {

      /*  @SerializedName("500 GM")
        @Expose
        private String _500Gm;
        @SerializedName("1 KG")
        @Expose
        private String _1Kg;
        @SerializedName("2 KG")
        @Expose
        private String _2Kg;
        @SerializedName("3 KG")
        @Expose
        private String _3Kg;
        @SerializedName("4 KG")
        @Expose
        private String _4Kg;
        @SerializedName("5 KG")
        @Expose
        private String _5Kg;
        @SerializedName("6 KG")
        @Expose
        private String _6Kg;
        @SerializedName("7 KG")
        @Expose
        private String _7Kg;
        @SerializedName("Upto 7 KG")
        @Expose
        private String upto7KG;

        public String get500Gm() {
            return _500Gm;
        }

        public void set500Gm(String _500Gm) {
            this._500Gm = _500Gm;
        }

        public String get1Kg() {
            return _1Kg;
        }

        public void set1Kg(String _1Kg) {
            this._1Kg = _1Kg;
        }

        public String get2Kg() {
            return _2Kg;
        }

        public void set2Kg(String _2Kg) {
            this._2Kg = _2Kg;
        }

        public String get3Kg() {
            return _3Kg;
        }

        public void set3Kg(String _3Kg) {
            this._3Kg = _3Kg;
        }

        public String get4Kg() {
            return _4Kg;
        }

        public void set4Kg(String _4Kg) {
            this._4Kg = _4Kg;
        }

        public String get5Kg() {
            return _5Kg;
        }

        public void set5Kg(String _5Kg) {
            this._5Kg = _5Kg;
        }

        public String get6Kg() {
            return _6Kg;
        }

        public void set6Kg(String _6Kg) {
            this._6Kg = _6Kg;
        }

        public String get7Kg() {
            return _7Kg;
        }

        public void set7Kg(String _7Kg) {
            this._7Kg = _7Kg;
        }

        public String getUpto7KG() {
            return upto7KG;
        }

        public void setUpto7KG(String upto7KG) {
            this.upto7KG = upto7KG;
        }*/

        @SerializedName("id")
        @Expose
        private String id;
        @SerializedName("name")
        @Expose
        private String name;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @NotNull
        @Override
        public String toString() {
            return name;
        }

    }


}
