package Client;

import Interfaces.Message;
import Interfaces.MessageType;

public class ListRequest implements Message {
    @Override
    public MessageType getType() {
        return MessageType.LIST_REQUEST;
    }
}
