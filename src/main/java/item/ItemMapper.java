package item;

public class ItemMapper {
    public ItemDto mapToItemDto(Item item) {
        ItemDto itemDto = new ItemDto();

        itemDto.setItemName(item.getItemName());
        itemDto.setItemStatus(item.getItemStatus());
        itemDto.setItemDescription(item.getItemDescription());
        itemDto.setNumberOfRentalTime(item.getNumberOfRentalTime());

        return itemDto;
    }

    public Item mapToItem(ItemDto itemDto) {
        Item item = new Item();

        item.setItemName(itemDto.getItemName());
        item.setItemStatus(itemDto.getItemStatus());
        item.setItemDescription(itemDto.getItemDescription());
        item.setNumberOfRentalTime(itemDto.getNumberOfRentalTime());

        return item;
    }
}
