def boolean call () {
    def boolean isActive = false;

    while (! isActive) {
        try {
            def get = new URL("http://localhost:8081/api/greetings").openConnection();
            def getRC = get.getResponseCode();
            if (getRC.equals(200)) {
                println("connection open");
                println(get.getInputStream().getText());
                isActive = true;
            } else {
                println("connection not open");
            }
        } catch (Exception e) {
            println("connection exception catched");
        }
    }
}