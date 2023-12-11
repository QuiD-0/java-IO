package com.quid.io.file

import java.nio.file.FileSystems
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.StandardWatchEventKinds.*
import java.nio.file.WatchService

class WatchDir {

    fun watch(watchPath: String) {
        val watchService: WatchService = FileSystems.getDefault().newWatchService()
        val path: Path = Paths.get(watchPath)

        val watchKey = path.register(watchService, ENTRY_CREATE, ENTRY_DELETE, ENTRY_MODIFY)

        while (true) {
            val key = watchKey.pollEvents()
            key.forEach {
                println("Event kind: ${it.kind()} | File affected: ${it.context()}")
            }
        }
    }
}