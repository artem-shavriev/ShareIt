package ru.practicum.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import ru.practicum.request.ItemRequest;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Item {
    private Integer id;
    private Integer owner;
    private String name;
    private String description;
    private Boolean available;
    private ItemRequest request;
}
