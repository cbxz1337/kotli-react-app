package com.example.demo.controlers

import com.example.demo.Data
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.servlet.ModelAndView

@RestController
@RequestMapping(value = ["/"])
class MouseController {

    @GetMapping("/first-app")
    fun accessAppOne(): ModelAndView {
        return ModelAndView("forward:/first-app/index.html")
    }

    @GetMapping("/first-app/1")
    fun getUserData(): ResponseEntity<Data> {
        return ResponseEntity(Data(1, 2), HttpStatus.OK)
    }

    @GetMapping("/first-app/{number1}:{number2}")
    fun getAdminData(@PathVariable number1: Int, @PathVariable number2: Int): ResponseEntity<Data> {
        return ResponseEntity(Data(number1, number2), HttpStatus.OK)
    }


    @PostMapping("/first-app/test")
    fun postSmth(): String {
        return "POOOST"
    }

    @DeleteMapping("/first-app/test")
    fun deleteSmth(): String {
        return "DELETE"
    }

}