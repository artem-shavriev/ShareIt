package ru.practicum.item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    private Integer itemId;
    private Integer owner;
    private String name;
    private String description;
    private Status available;
    private String request;
}
