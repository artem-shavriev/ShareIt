package ru.practicum.item;

import ru.practicum.exception.NotFoundException;
import ru.practicum.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.service.IdGenerator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Repository
public class ItemMemoryRepository {
    private final ItemMapper itemMapper;
    private final IdGenerator idGenerator;
    Map<Integer, Item> itemsMap = new HashMap<>();

    public ItemDto addItem(ItemDto itemDtoRequest, Integer userId) {
       Item item = itemMapper.mapToItem(itemDtoRequest);
       item.setOwner(userId);
       item.setItemId(idGenerator.getNextId(itemsMap));

       log.info("Предмет добавлен пользователем с id {}.", userId);
       return itemMapper.mapToItemDto(item);
    }

    public ItemDto updateItem(Integer itemId, ItemDto itemDtoRequest, Integer userId) {
        if (!itemsMap.containsKey(itemId)) {
            log.error("Предмет с id {} не найден.", itemId);
            throw new NotFoundException("Предмет с таким id не найден.");
        }

        Integer itemsOwnerId = itemsMap.get(itemId).getOwner();
        if (itemsOwnerId != userId) {
            log.error("Id польльзователя: {} не совпадает с id владельца: {} изменяемой вещи.", userId, itemsOwnerId);
            throw new ValidationException("Вносить изменения может только владелец вещи.");
        }

        Item updatedItem = new Item();

        updatedItem.setName(itemDtoRequest.getName());
        updatedItem.setDescription(itemDtoRequest.getDescription());
        updatedItem.setAvailable(itemDtoRequest.getAvailable());

        itemsMap.put(itemId, updatedItem);

        log.info("Предмет с id {} обновлен.", itemId);
        return itemMapper.mapToItemDto(updatedItem);
    }

    public ItemDto getItem(Integer itemId) {
        if (!itemsMap.containsKey(itemId)) {
            log.error("Предмет с id {} не найден.", itemId);
            throw new NotFoundException("Предмет с таким id не найден.");
        }

        log.info("Предмет найден.");
        return itemMapper.mapToItemDto(itemsMap.get(itemId));
    }

    public List<ItemDto> getOwnerItems(Integer ownerId) {
        List<Item> allItems = (List<Item>) itemsMap.values();
        List<ItemDto> ownerItemsList = new ArrayList<>();

        allItems.forEach(item -> {
            if (item.getOwner() == ownerId) {
                ownerItemsList.add(itemMapper.mapToItemDto(item));
            }
        });

        if (ownerItemsList.isEmpty()) {
            log.error("Вещи пользователя c id {} не найдены.",  ownerId);
            throw new NotFoundException("Вещи пользователя не найдены.");
        }

        log.info("Вещи пользовтеля найдены.");
        return ownerItemsList;
    }

    public List<ItemDto> itemSearch(String text) {
        List<Item> allItems = (List<Item>) itemsMap.values();
        List<ItemDto> searchItems = new ArrayList<>();

        allItems.forEach(item -> {
            if ((item.getName().contains(text) || item.getDescription().contains(text))
                    && item.getAvailable().equals(Status.AVAILABLE)) {
                searchItems.add(itemMapper.mapToItemDto(item));
            }
        });

        if (searchItems.isEmpty()) {
            log.error("Вещь не найдена или не доступна.");
            throw new NotFoundException("Вещь не найдена или не доступна.");
        }

        log.info("Вещь найдена.");
        return searchItems;
    }
}
