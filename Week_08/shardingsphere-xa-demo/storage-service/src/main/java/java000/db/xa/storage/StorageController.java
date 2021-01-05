package java000.db.xa.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/storage")
public class StorageController {

    @Autowired private StorageService storageService;

    @GetMapping("/{id}")
    public Storage getStorage(@PathVariable Integer id) {
        if (id == null) {
            return null;
        }
        return storageService.findById(id);
    }

    @PostMapping("/add")
    public String add(@RequestParam Integer commodityId, @RequestParam Integer quantity) {
        Storage storage = new Storage(commodityId, quantity);
        storage.setUpdated_ts(LocalDateTime.now());
        storageService.add(storage);
        return "added commodity=" + commodityId + " successfully";
    }
    
    @PostMapping("/update")
    public String update(@RequestParam Integer commodityId, @RequestParam Integer quantity) {
        storageService.update(commodityId, quantity);
        return "update commodity=" + commodityId + " successfully";
    }
}
