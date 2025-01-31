package item;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Item {
    Integer itemId;
    Integer itemOwnerId;
    String itemName;
    String itemDescription;
    Status itemStatus;
    Integer numberOfRentalTime;
}
