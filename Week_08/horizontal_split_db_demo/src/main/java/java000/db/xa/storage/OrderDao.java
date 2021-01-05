package java000.db.xa.storage;

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
        String sql = "select * from " + whichTable(0, id) + " where id = ? union all select * from " + whichTable(1, id) + " where id = ?";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id, id}, new RowMapper<Order>() {
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

    private String whichTable(int dbNum, int orderId) {
        StringBuilder sb = new StringBuilder();
        sb.append("camp_" + dbNum);
        sb.append(".order_" + (orderId % 16));
        return sb.append(" ").toString();
    }

    private String whichSchemaAndTable(int userId, int orderId) {
        StringBuilder sb = new StringBuilder();
        sb.append("camp_" + (userId % 2));
        sb.append(".order_" + (orderId % 16));
        return sb.append(" ").toString();
    }

    private JdbcTemplate getTemplate() {
        return new JdbcTemplate(dataSource);
    }

    public void add(Order order) {
        String sql = "insert into " + whichSchemaAndTable(order.getUser_id(), order.getId()) +
                " (id, user_id, commodity_id, quantity, created_ts, updated_ts) " +
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
