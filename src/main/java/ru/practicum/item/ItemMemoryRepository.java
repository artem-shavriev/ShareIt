package ru.practicum.item;

import ru.practicum.exception.NotFoundException;
import ru.practicum.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import ru.practicum.service.IdGenerator;
import ru.practicum.user.UserMemoryRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
@Slf4j
@Repository
public class ItemMemoryRepository {
    private final ItemMapper itemMapper;
    private final UserMemoryRepository userMemoryRepository;
    private final IdGenerator idGenerator;
    Map<Integer, Item> itemsMap = new HashMap<>();

    public ItemDto addItem(ItemDto itemDtoRequest, Integer userId) {

        if (itemDtoRequest.getAvailable() == null) {
            throw new ValidationException("Поле доступности не должно быть null");
        }

        if (itemDtoRequest.getName() == null || itemDtoRequest.getName().isBlank()) {
            throw new ValidationException("Item name не должно быть null");
        }

        if (itemDtoRequest.getDescription() == null || itemDtoRequest.getDescription().isBlank()) {
            throw new ValidationException("Item name не должно быть null");
        }

        if (userMemoryRepository.getUserById(userId) == null) {
            throw new NotFoundException("Пользовтель с данным id не найден.");
        }

        Item item = itemMapper.mapToItem(itemDtoRequest);
        item.setOwner(userId);
        item.setId(idGenerator.getNextId(itemsMap));
        itemsMap.put(item.getId(), item);

        log.info("Предмет c id {} добавлен пользователем с id {}.", item.getId(), userId);
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
            throw new NotFoundException("Вносить изменения может только владелец вещи.");
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
        Collection<Item> allItems = itemsMap.values();
        List<ItemDto> ownerItemsList = new ArrayList<>();

        allItems.forEach(item -> {
            if (item.getOwner() == ownerId) {
                ownerItemsList.add(itemMapper.mapToItemDto(item));
            }
        });

        if (ownerItemsList.isEmpty()) {
            log.error("Вещи пользователя c id {} не найдены.", ownerId);
            throw new NotFoundException("Вещи пользователя не найдены.");
        }

        log.info("Вещи пользовтеля найдены.");
        return ownerItemsList;
    }

    public List<ItemDto> itemSearch(String text) {
        List<ItemDto> searchItems = new ArrayList<>();
        if (text != null && !text.isBlank()) {

            Collection<Item> allItems = itemsMap.values();

            allItems.forEach(item -> {
                if (item.getName() != null && item.getAvailable() != null && item.getAvailable().equals(true)) {
                    if ((item.getName().toLowerCase().contains(text.toLowerCase()))) {
                        searchItems.add(itemMapper.mapToItemDto(item));
                        log.info("Вещь найдена по названию. Название вещи: {}, текст поиска: {}, id: {}",
                                item.getName(), text, item.getId());
                    }
                }

                if (item.getDescription() != null && item.getAvailable() != null && item.getAvailable().equals(true)) {
                    if (item.getDescription().toLowerCase().contains(text.toLowerCase())) {
                        searchItems.add(itemMapper.mapToItemDto(item));
                        log.info("Вещь найдена по описанию. Описанию: {}, текст поиска {}, id {}",
                                item.getDescription(), text, item.getId());
                    }
                }
            });

            System.out.println("текст: " + text);
        }
        return searchItems;
    }
}
