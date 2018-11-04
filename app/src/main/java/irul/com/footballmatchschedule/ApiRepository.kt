package irul.com.footballmatchschedule

import java.net.URL

class ApiRepository {

    fun doRequest(url: String): String {
        var realUrl = url.replace(" ","%20")
        return URL(realUrl).readText()
    }
}