def call() {
    if(params.COMPOSE) {
        return params.COMPOSE;
    } else {
        return "UP";
    }
}