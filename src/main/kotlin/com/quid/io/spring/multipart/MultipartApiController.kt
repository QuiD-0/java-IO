package com.quid.io.spring.multipart

import com.quid.io.spring.multipart.usecase.UploadFile
import org.slf4j.LoggerFactory
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestPart
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.multipart.MultipartFile
import java.io.File


@RestController
class MultipartApiController(
    private val uploadFile: UploadFile
) {
    private val logger = LoggerFactory.getLogger(MultipartApiController::class.java)

    @PostMapping("/api/multipart")
    fun upload(@RequestPart multipartFile: MultipartFile) {
        val name: String = multipartFile.originalFilename ?: "file"
        logger.info("upload file: $name")

        File.createTempFile("temp", name).let {
            multipartFile.transferTo(it)
            uploadFile(it, name)
            it.delete()
        }
    }
}