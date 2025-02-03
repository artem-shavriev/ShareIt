package ru.practicum.item;

import org.springframework.stereotype.Component;

@Component
public class ItemMapper {
    public ItemDto mapToItemDto(Item item) {
        ItemDto itemDto = new ItemDto();

        itemDto.setName(item.getName());
        itemDto.setAvailable(item.getAvailable());
        itemDto.setDescription(item.getDescription());
        itemDto.setRequest(item.getRequest());

        return itemDto;
    }

    public Item mapToItem(ItemDto itemDto) {
        Item item = new Item();

        item.setName(itemDto.getName());
        item.setAvailable(itemDto.getAvailable());
        item.setDescription(itemDto.getDescription());
        item.setRequest(itemDto.getRequest());

        return item;
    }
}
