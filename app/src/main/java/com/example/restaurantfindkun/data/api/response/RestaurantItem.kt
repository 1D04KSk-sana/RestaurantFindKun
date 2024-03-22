package com.example.restaurantfindkun.data.api.response

import com.google.gson.annotations.SerializedName
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

data class RestaurantItem(
    @SerializedName("name")
    val name: String,
    @SerializedName("smallarea")
    val smallArea: String,
    @SerializedName("storecatch")
    val storeCatch: String,
    @SerializedName("image")
    val image: String
)

@Root(name = "shop", strict = false)
data class Shop(
    @field:Element(name = "id")
    var id: String? = null,

    @field:Element(name = "name", required = false)
    var name: String? = null,

    @field:Element(name = "logo_image", required = false)
    var logoImage: String? = null,

    @field:Element(name = "name_kana", required = false)
    var nameKana: String? = null,

    @field:Element(name = "address", required = false)
    var address: String? = null,

    @field:Element(name = "station_name", required = false)
    var stationName: String? = null,

    @field:Element(name = "ktai_coupon", required = false)
    var ktaiCoupon: String? = null,

    @field:Element(name = "large_service_area", required = false)
    var largeServiceArea: LargeServiceArea? = null,

    @field:Element(name = "service_area", required = false)
    var serviceArea: ServiceArea? = null,

    @field:Element(name = "large_area", required = false)
    var largeArea: LargeArea? = null,

    @field:Element(name = "middle_area", required = false)
    var middleArea: MiddleArea? = null,

    @field:Element(name = "small_area", required = false)
    var smallArea: SmallArea? = null,

    @field:Element(name = "lat", required = false)
    var lat: String? = null,

    @field:Element(name = "lng", required = false)
    var lng: String? = null,

    @field:Element(name = "genre", required = false)
    var genre: Genre? = null,

    @field:Element(name = "sub_genre", required = false)
    var subGenre: SubGenre? = null,

    @field:Element(name = "budget", required = false)
    var budget: Budget? = null,

    @field:Element(name = "catch", required = false)
    var catchText: String? = null,

    @field:Element(name = "capacity", required = false)
    var capacity: String? = null,

    @field:Element(name = "access", required = false)
    var access: String? = null,

    @field:Element(name = "mobile_access", required = false)
    var mobileAccess: String? = null,

    @field:Element(name = "urls", required = false)
    var urls: Urls? = null,

    @field:Element(name = "photo", required = false)
    var photo: Photo? = null,

    @field:Element(name = "open", required = false)
    var open: String? = null,

    @field:Element(name = "close", required = false)
    var close: String? = null,

    @field:Element(name = "party_capacity", required = false)
    var partyCapacity: String? = null,

    @field:Element(name = "other_memo", required = false)
    var otherMemo: String? = null,

    @field:Element(name = "shop_detail_memo", required = false)
    var shopDetailMemo: String? = null,

    @field:Element(name = "budget_memo", required = false)
    var budgetMemo: String? = null,

    @field:Element(name = "wedding", required = false)
    var wedding: String? = null,

    @field:Element(name = "course", required = false)
    var course: String? = null,

    @field:Element(name = "free_drink", required = false)
    var freeDrink: String? = null,

    @field:Element(name = "free_food", required = false)
    var freeFood: String? = null,

    @field:Element(name = "private_room", required = false)
    var privateRoom: String? = null,

    @field:Element(name = "horigotatsu", required = false)
    var horigotatsu: String? = null,

    @field:Element(name = "tatami", required = false)
    var tatami: String? = null,

    @field:Element(name = "card", required = false)
    var card: String? = null,

    @field:Element(name = "non_smoking", required = false)
    var nonSmoking: String? = null,

    @field:Element(name = "charter", required = false)
    var charter: String? = null,

    @field:Element(name = "parking", required = false)
    var parking: String? = null,

    @field:Element(name = "barrier_free", required = false)
    var barrierFree: String? = null,

    @field:Element(name = "show", required = false)
    var show: String? = null,

    @field:Element(name = "karaoke", required = false)
    var karaoke: String? = null,

    @field:Element(name = "band", required = false)
    var band: String? = null,

    @field:Element(name = "tv", required = false)
    var tv: String? = null,

    @field:Element(name = "lunch", required = false)
    var lunch: String? = null,

    @field:Element(name = "midnight", required = false)
    var midnight: String? = null,

    @field:Element(name = "english", required = false)
    var english: String? = null,

    @field:Element(name = "pet", required = false)
    var pet: String? = null,

    @field:Element(name = "child", required = false)
    var child: String? = null,

    @field:Element(name = "wifi", required = false)
    var wifi: String? = null,

    @field:Element(name = "coupon_urls", required = false)
    var couponUrls: CouponUrls? = null
)

@Root(name = "large_service_area", strict = false)
data class LargeServiceArea(
    @field:Element(name = "code")
    var code: String? = null,

    @field:Element(name = "name")
    var name: String? = null
)

@Root(name = "service_area", strict = false)
data class ServiceArea(
    @field:Element(name = "code")
    var code: String? = null,

    @field:Element(name = "name")
    var name: String? = null
)

@Root(name = "large_area", strict = false)
data class LargeArea(
    @field:Element(name = "code")
    var code: String? = null,

    @field:Element(name = "name")
    var name: String? = null
)

@Root(name = "middle_area", strict = false)
data class MiddleArea(
    @field:Element(name = "code")
    var code: String? = null,

    @field:Element(name = "name")
    var name: String? = null
)

@Root(name = "small_area", strict = false)
data class SmallArea(
    @field:Element(name = "code")
    var code: String? = null,

    @field:Element(name = "name")
    var name: String? = null
)

@Root(name = "genre", strict = false)
data class Genre(
    @field:Element(name = "name")
    var name: String? = null,

    @field:Element(name = "catch")
    var catchText: String? = null,

    @field:Element(name = "code")
    var code: String? = null
)

@Root(name = "sub_genre", strict = false)
data class SubGenre(
    @field:Element(name = "name")
    var name: String? = null,

    @field:Element(name = "code")
    var code: String? = null
)

@Root(name = "budget", strict = false)
data class Budget(
    @field:Element(name = "code")
    var code: String? = null,

    @field:Element(name = "name")
    var name: String? = null,

    @field:Element(name = "average")
    var average: String? = null
)

@Root(name = "urls", strict = false)
data class Urls(
    @field:Element(name = "pc")
    var pc: String? = null
)

@Root(name = "photo", strict = false)
data class Photo(
    @field:Element(name = "pc")
    var pc: PcPhoto? = null,

    @field:Element(name = "mobile")
    var mobile: MobilePhoto? = null
)

@Root(name = "pc", strict = false)
data class PcPhoto(
    @field:Element(name = "l")
    var l: String? = null,

    @field:Element(name = "m")
    var m: String? = null,

    @field:Element(name = "s")
    var s: String? = null
)

@Root(name = "mobile", strict = false)
data class MobilePhoto(
    @field:Element(name = "l")
    var l: String? = null,

    @field:Element(name = "s")
    var s: String? = null
)

@Root(name = "coupon_urls", strict = false)
data class CouponUrls(
    @field:Element(name = "pc")
    var pc: String? = null,

    @field:Element(name = "sp")
    var sp: String? = null
)