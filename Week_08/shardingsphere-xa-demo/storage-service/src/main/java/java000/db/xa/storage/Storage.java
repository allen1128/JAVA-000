package java000.db.xa.storage;

import java.time.LocalDateTime;
import java.util.Objects;

public class Storage {
    private int commodity_id;
    private int quantity;
    private LocalDateTime created_ts;
    private LocalDateTime updated_ts;

    public Storage() {
    }

    public Storage(int commodity_id, int quantity) {
        this.commodity_id = commodity_id;
        this.quantity = quantity;
        this.created_ts = LocalDateTime.now();
        this.updated_ts = LocalDateTime.now();
    }

    public int getCommodity_id() {
        return commodity_id;
    }

    public void setCommodity_id(int commodity_id) {
        this.commodity_id = commodity_id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public LocalDateTime getCreated_ts() {
        return created_ts;
    }

    public void setCreated_ts(LocalDateTime created_ts) {
        this.created_ts = created_ts;
    }

    public LocalDateTime getUpdated_ts() {
        return updated_ts;
    }

    public void setUpdated_ts(LocalDateTime updated_ts) {
        this.updated_ts = updated_ts;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Storage storage = (Storage) o;
        return commodity_id == storage.commodity_id && quantity == storage.quantity && Objects.equals(created_ts, storage.created_ts) && Objects.equals(updated_ts, storage.updated_ts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commodity_id, quantity, created_ts, updated_ts);
    }
}
