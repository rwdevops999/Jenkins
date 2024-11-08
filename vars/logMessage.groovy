import java.text.SimpleDateFormat

/**
* print the message prefixed with date and time
*/
def call (String message) {
    def date = new Date()
    def sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss")
    println (sdf.format(date) + " : ${message}")
}