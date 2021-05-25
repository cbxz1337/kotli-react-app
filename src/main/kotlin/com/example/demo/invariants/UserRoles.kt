package com.example.demo.invariants

import org.springframework.security.core.authority.SimpleGrantedAuthority
import java.util.stream.Collectors

enum class UserRoles(var mnemo: String, var permissions: Set<UserPermission>) {
    USER("USER", setOf(UserPermission.USER_GET_DATA)),
    ADMIN("ADMIN", setOf(UserPermission.ADMIN_GET_DATA, UserPermission.USER_GET_DATA));

    fun getGrantedAuthorities(): Set<SimpleGrantedAuthority> {
        val result: Set<SimpleGrantedAuthority> =
            permissions.stream().map { p -> SimpleGrantedAuthority(p.permission) }.collect(Collectors.toSet())
        return result.plusElement(SimpleGrantedAuthority("ROLE_" + this.mnemo))
    }

}
