def call (): boolean {
    def get = new URL("http://localhost:8081/api/greetings").openConnection();
    def getRC = get.getResponseCode();
    if (getRC.equals(200)) {
        println("connection open");
        println(get.getInputStream().getText());
        return true;
    } else {
        println("connection not open");
    }

    return false;
}