package com.mes.user_app.data.model.core

interface  User {
    var id: String
    var name: String
    var email: String
    var avatar: String
    var createdLocally: Boolean
    var syncStatus: String

}