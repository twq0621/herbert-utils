package game.chat.dto {

    import lion.core.Amf3BaseDTO;

    [Bindable]
    [RemoteClass(alias="game.chat.dto.ConnectChat_C2S")]
    public class ConnectChat_C2S extends Amf3BaseDTO {
        public var roleName:String;
        public var sid:String;
        public var userName:String;
    }
}
