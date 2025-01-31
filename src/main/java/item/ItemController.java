package item;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/items")
public class ItemController {
    ItemMemoryRepository itemMemoryRepository;

    @PostMapping
    public ItemDto addItem( @RequestHeader("X-Sharer-User-Id") Integer userId, @RequestBody ItemDto item) {
        return itemMemoryRepository.addItem(item, userId);
    }
}
