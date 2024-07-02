class Greeting {
    private val platform = getPlatform()

    fun greet(): String {
        return "Hello, ${platform.name}!"
    }
}

val TEST_PAGE_URL = "https://ginyolith.github.io/permission-req-kmm-sample/"