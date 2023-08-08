package com.example.wanandroid

import com.google.gson.annotations.SerializedName

data class MusicInfo(
    var name: String,
    var url: String,
    @SerializedName("picurl")
    var picturl: String,
)
