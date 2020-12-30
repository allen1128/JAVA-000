package java000.db.ha.order;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class OrderDao {

    @Autowired
    private DataSource dataSource;

    public Order findById(int id) {
        JdbcTemplate jdbcTemplate = getTemplate();
        String sql = "select * from camp.order where id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Order>() {
                @Override
                public Order mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Order order = new Order();
                    order.setId(rs.getInt("id"));
                    order.setUser_id(rs.getInt("user_id"));
                    order.setCommodity_id(rs.getInt("commodity_id"));
                    order.setQuantity(rs.getInt("quantity"));
                    order.setCreated_ts(rs.getTimestamp("created_ts").toLocalDateTime());
                    order.setUpdated_ts(rs.getTimestamp("updated_ts").toLocalDateTime());
                    return order;
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    private JdbcTemplate getTemplate() {
        return new JdbcTemplate(dataSource);
    }

    public void add(Order order) {
        String sql = "insert into camp.order (id, user_id, " +
                "commodity_id, quantity, created_ts, updated_ts) " +
                "values(?, ?, ?, ?, ?, ?) ";
        int res = getTemplate().update(sql, new Object[] {
                order.getId(), order.getUser_id(),
                order.getCommodity_id(), order.getQuantity(),
                order.getCreated_ts(), order.getUpdated_ts()});
        if (res <= 0) {
            throw new RuntimeException("insert failture");
        }
    }
}
