public List<UserEntity> findAll() {
    List<UserEntity> rows = new ArrayList<>();
    String sql = "SELECT * FROM user";
    try (Connection conn = JdbcConnection.getInstance().connection();
        PreparedStatement ps = conn.prepareStatement(sql);) {
        ResultSet rs = ps.executeQuery(sql);
        while (rs.next()) {
            rows.add(UserEntity.builder()
                .id(rs.getLong("id"))
            .type(rs.getByte("type"))
            .name(rs.getString("name"))
            .account(rs.getString("account"))
            .password(rs.getString("password"))
            .basicInfo(rs.getString("basic_info"))
            .identityInfo(rs.getString("identity_info"))
            .beenOpened(rs.getByte("been_opened"))
            .createdAt(LocalDateTime.parse(
                rs.getString("created_at"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
            .updatedAt(LocalDateTime.parse(
                rs.getString("updated_at"),
                DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")))
            .version(rs.getInt("version"))
            .build());
        }
    } catch (SQLException e) {
        throw new DemoSysException(e);
    }
    return rows;
}