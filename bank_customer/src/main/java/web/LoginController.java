protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException,IOException {
    String action = req.getPathInfo();
    log.info("Login action: {}", action);
    switch (action) {
        case "/in":
            in(req, resp);
            break;
        case "/out":
            out(req, resp);
            break;
        default:
            throw new UnavailableException("非法访问：" + action);
    }
}

private void in(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    String username = req.getParameter("email");
    String password = req.getParameter("password");
    log.info("email: {}, password: {}", username, password);

    if ("admin@example.com".equals(username) && "pwd123".equals(password)) {
        req.getSession().setAttribute(LOGGED, username);
        resp.sendRedirect("/user/list");
    }
    throw new SecurityException("你输入的凭证无效！");
}