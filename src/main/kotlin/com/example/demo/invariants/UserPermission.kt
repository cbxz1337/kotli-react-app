package com.example.demo.invariants

enum class UserPermission(var permission: String) {
    USER_GET_DATA("user:data"),
    ADMIN_GET_DATA("admin:data");
}
