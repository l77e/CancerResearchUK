package com.lukeedgar.usercheckin

data class Email(val senderAddress : String,
                 val recipient: String,
                 val userId : String,
                 val eventId : String)