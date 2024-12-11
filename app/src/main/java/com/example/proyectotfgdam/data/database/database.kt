package com.example.proyectotfgdam.data.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase

fun getDatabaseBuilder(ctx: Context): RoomDatabase.Builder<ContenidoDatabase>{
    val appContext = ctx.applicationContext
    val dbFile = appContext.getDatabasePath("ContenidoDatabase.db")
    return Room.databaseBuilder(
        context = appContext,
        name = dbFile.absolutePath
    )
}