package com.jiangkang.requests.dsl

import java.net.Inet4Address

/**
 * Created by jiangkang on 2018/2/7.
 * description：
 */

data class Person(
        var name: String? = null,
        var age: Int? = null,
        var address: Address? = null
)


data class Address(
        var city: String? = null,
        var street: String? = null,
        var number: String? = null
)


fun person(block: Person.() -> Unit): Person = Person().apply { block }

fun Person.address(block: Address.() -> Unit) {
    address = Address().apply { block }
}

fun test(){

    val p = person {
        name = "jiang kang"
        age = 25
        address {
            city = "ShangHai"
            street = "unknown"
            number = "111"
        }
    }

    val (name,age,address) = p

}
