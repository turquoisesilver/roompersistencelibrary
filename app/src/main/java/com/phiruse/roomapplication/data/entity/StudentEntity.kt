package com.phiruse.roomapplication.data.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Index
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
@Entity(
    foreignKeys = [
        ForeignKey(
        entity = TeacherEntity::class,
        parentColumns = ["id"],
        childColumns = ["teacherId"]
    )
   ],
    tableName = "students",
    indices = [Index("studentId")]
)
data class StudentEntity(
    @PrimaryKey(autoGenerate = true)
    var studentId: Long,
    var teacherId: Long,
    val name: String
): Parcelable
