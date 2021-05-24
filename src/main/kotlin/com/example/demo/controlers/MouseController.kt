package com.example.demo.controlers

import com.example.demo.Data
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.servlet.ModelAndView

@RestController
@RequestMapping(value = ["/"])
class MouseController {

    @GetMapping("/first-app")
    fun accessAppOne(): ModelAndView {
        return ModelAndView("forward:/first-app/index.html")
    }

    @GetMapping("/first-app/1")
    fun getData(): ResponseEntity<Data> {
        return ResponseEntity(Data(1, 2), HttpStatus.OK)
    }
}