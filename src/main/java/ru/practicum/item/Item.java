package ru.practicum.item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Item {
    private Integer itemId;
    private Integer owner;
    private String name;
    private String description;
    private Status available;
    private String request;
}
