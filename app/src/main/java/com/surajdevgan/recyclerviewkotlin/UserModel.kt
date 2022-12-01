package com.surajdevgan.recyclerviewkotlin

import java.io.Serializable

data class UserModel(
    var id: Int?,
    var email: String?,
    var first_name: String?,
    var last_name: String?,
    var avatar: String?
    ) :
    Serializable {

}



