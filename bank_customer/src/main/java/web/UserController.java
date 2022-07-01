@Override
protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String action = req.getPathInfo();
    log.info("User action: {}", action);
    switch (action) {
        case "/list":
            list(req, resp);
            break;
        case "/add":
            add(req, resp);
            break;
        case "/update":
            update(req, resp);
            break;
        case "/delete":
            delete(req, resp);
            break;
        default:
            throw new UnavailableException("非法访问：" + action);
    }
}