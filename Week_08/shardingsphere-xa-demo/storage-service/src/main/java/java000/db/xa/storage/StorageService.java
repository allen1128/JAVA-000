package java000.db.xa.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StorageService {

    @Autowired
    StorageDao storageDao;

    public Storage findById(int id) {
        return storageDao.findById(id);
    }

    public void add(Storage storage) {
        storageDao.add(storage);
    }

    public void update(Integer commodityId, Integer booked) {
        storageDao.update(commodityId, booked);
    }
}
