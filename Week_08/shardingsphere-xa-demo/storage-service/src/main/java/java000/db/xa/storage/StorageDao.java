package java000.db.xa.storage;

import org.apache.shardingsphere.transaction.annotation.ShardingTransactionType;
import org.apache.shardingsphere.transaction.core.TransactionType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Random;

@Component
public class StorageDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public Storage findById(int id) {
        String sql = "select * from camp.storage where commodity_id = ? ";
        try {
            return jdbcTemplate.queryForObject(sql, new Object[]{id}, new RowMapper<Storage>() {
                @Override
                public Storage mapRow(ResultSet rs, int rowNum) throws SQLException {
                    Storage storage = new Storage();
                    storage.setCommodity_id(rs.getInt("commodity_id"));
                    storage.setQuantity(rs.getInt("quantity"));
                    storage.setCreated_ts(rs.getTimestamp("created_ts").toLocalDateTime());
                    storage.setUpdated_ts(rs.getTimestamp("updated_ts").toLocalDateTime());
                    return storage;
                }
            });
        } catch (Exception ex) {
            ex.printStackTrace();
            return null;
        }
    }

    @Transactional(rollbackFor = SQLException.class)
    @ShardingTransactionType(TransactionType.XA)
    public void add(Storage storage) {
        String sql = "insert into camp.storage " +
                " (commodity_id, quantity, created_ts, updated_ts) " +
                "values(?, ?, ?, ?) ";
        int res = jdbcTemplate.update(sql, new Object[] {
                storage.getCommodity_id(), storage.getQuantity(),
                storage.getCreated_ts(), storage.getUpdated_ts()});
        if (res <= 0) {
            throw new RuntimeException("insert failture");
        }
    }

    @Transactional(rollbackFor = RuntimeException.class)
    @ShardingTransactionType(TransactionType.XA)
    public void update(Integer commodityId, Integer quantity) {
        String sql = "update camp.storage set quantity =  quantity - ?, updated_ts =? where commodity_id = ?";
        int res = jdbcTemplate.update(sql, new Object[] {
                quantity, LocalDateTime.now(), commodityId});
        if (res <= 0) {
            throw new RuntimeException("update failture");
        }

        int randomlized = new Random().nextInt(2);
        if (randomlized == 1) {
            throw new RuntimeException("man-made exception");
        }
    }
}
