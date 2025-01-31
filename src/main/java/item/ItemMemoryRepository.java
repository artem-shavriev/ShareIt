package item;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Repository
public class ItemMemoryRepository {
    ItemMapper itemMapper;
    Map<Integer, Item> itemsMap = new HashMap<>();

    public ItemDto addItem(ItemDto itemDtoRequest, Integer userId) {
       Item item = itemMapper.mapToItem(itemDtoRequest);
       item.setItemOwnerId(userId);

       return itemDtoRequest;
    }


}
