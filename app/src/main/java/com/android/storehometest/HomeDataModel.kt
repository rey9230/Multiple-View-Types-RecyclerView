package com.android.storehometest


import com.google.gson.annotations.SerializedName

data class HomeDataModel(@SerializedName("data") val data : HomeItem,
                         @SerializedName("success") val success : Boolean = true,
                         @SerializedName("message") val message : String = "",
                         @SerializedName("actionCode") val actionCode : Int = -1)

data class HomeItem(@SerializedName("shoppingCartItems") val shoppingCartItems : Int= 0,
                    @SerializedName("location") val location : Location,
                    @SerializedName("sections") val sections : ArrayList<Sections>)

data class Location(@SerializedName("location") val location : Int= 0,
                    @SerializedName("contact") val contact : String= "",
                    @SerializedName("locationToShow") val locationToShow : String= "")

data class Sections(@SerializedName("controlType") val controlType : Int= 0,
                              @SerializedName("alignment") val alignment : Int= 0,
                              @SerializedName("itemSize") val itemSize : Int= 0,
                              @SerializedName("headerText") val headerText : String= "",
                              @SerializedName("detailsText") val detailsText : String= "",
                              @SerializedName("items") val items : ArrayList<Items>)


data class Items(@SerializedName("code") val code : String= "",
                    @SerializedName("contentType") val contentType : Int= 0,
                    @SerializedName("action") val action : Int= 0,
                    @SerializedName("name") val name : String= "",
                    @SerializedName("details") val details : String= "",
                    @SerializedName("labelText") val labelText : String= "",
                    @SerializedName("labelColor") val labelColor : String= "",
                    @SerializedName("priceToShow") val priceToShow : String= "",
                    @SerializedName("oldPriceToShow") val oldPriceToShow : String= "",
                    @SerializedName("showAvailability") val showAvailability : Boolean=false,
                    @SerializedName("availability") val availability : Int= 0,
                    @SerializedName("rating") val rating : Double= 0.0,
                    @SerializedName("previewUrl") val previewUrl : String= "",
                    @SerializedName("imageUrl") val imageUrl : String= "")



