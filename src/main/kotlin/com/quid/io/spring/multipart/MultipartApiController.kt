package com.quid.io.spring.multipart

import com.quid.io.spring.multipart.usecase.UploadFile
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.File

@RestController
class MultipartApiController(
    private val uploadFile: UploadFile
){
    @PostMapping("/api/multipart")
    fun upload(@RequestPart multipartFile: MultipartFile) {
        val file = File(multipartFile.originalFilename!!)
        multipartFile.transferTo(file)

        uploadFile(file)
    }
}