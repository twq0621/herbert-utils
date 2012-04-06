package game.chat.dto {

    import lion.core.Amf3BaseDTO;

    [Bindable]
    [RemoteClass(alias="game.chat.dto.Chat_S2C")]
    public class Chat_S2C extends Amf3BaseDTO {
        public var content:String;
        public var senderName:String;
    }
}
