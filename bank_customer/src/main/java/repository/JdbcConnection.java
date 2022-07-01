public class JdbcConnection {

    private static final JdbcConnection INSTANCE = new JdbcConnection();

    public static String url;
    public static String username;
    public static String password;

    public static void init(ServletContext ctx) {
        if (null == url) {
            url = ctx.getInitParameter("jdbcURL");
            username = ctx.getInitParameter("jdbcUsername");
            password = ctx.getInitParameter("jdbcPassword");
            assert null != url && null != username && null != password;
        }
    }

    private JdbcConnection() {
    }

    /**
     * 获取一个新的链接
     *
     * @return 新的链接
     */
    public static JdbcConnection getInstance() {
        return INSTANCE;
    }

    public Connection connection() {
        Connection connection = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
        } catch (Exception e) {
            log.error("连接数据库[url={}, username={}, password={}]失败[{}]，程序退出！",
                    url, username, password, e.getMessage());
            System.exit(-1);
        }
        return connection;
    }

}

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        JdbcConnection.init(request.getServletContext());
        chain.doFilter(request, response);
    }