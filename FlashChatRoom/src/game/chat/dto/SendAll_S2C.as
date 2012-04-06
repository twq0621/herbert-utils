package game.chat.dto {

    import lion.core.Amf3BaseDTO;

    [Bindable]
    [RemoteClass(alias="game.chat.dto.SendAll_S2C")]
    public class SendAll_S2C extends Amf3BaseDTO {
        public var content:String;
    }
}
