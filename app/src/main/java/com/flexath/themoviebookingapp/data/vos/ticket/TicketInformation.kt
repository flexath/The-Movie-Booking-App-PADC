package com.flexath.themoviebookingapp.data.vos.ticket

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.flexath.themoviebookingapp.data.vos.movie.SnackVO
import com.flexath.themoviebookingapp.data.vos.movie.confirmation.TicketCheckoutVO
import com.flexath.themoviebookingapp.persistence.typeconverters.SnackListTypeConverter
import com.flexath.themoviebookingapp.persistence.typeconverters.TicketInformationTypeConverter
import com.google.gson.annotations.SerializedName
import java.io.Serializable

@Entity("ticket_table")
@TypeConverters(
    TicketInformationTypeConverter::class,
    SnackListTypeConverter::class
)
data class TicketInformation(

    @ColumnInfo("ticket_checkout")
    val ticketCheckout:TicketCheckoutVO?,

    @ColumnInfo("snack_list ")
    var snackList:List<SnackVO>?,

    @ColumnInfo("address")
    val address:String?,

    @ColumnInfo("movie_name")
    val movieName:String?,

    @ColumnInfo("movie_poster")
    val moviePoster:String?
) : java.io.Serializable {
    @PrimaryKey(autoGenerate = true)
    var id:Int = 0
}
