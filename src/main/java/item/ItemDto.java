package item;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemDto {
    String itemName;
    String itemDescription;
    Status itemStatus;
    Integer numberOfRentalTime;
}
