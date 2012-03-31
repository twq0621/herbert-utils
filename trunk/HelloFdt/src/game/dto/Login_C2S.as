package game.dto {

    import lion.core.Amf3BaseDTO;

    [Bindable]
    [RemoteClass(alias="game.dto.Login_C2S")]
    public class Login_C2S extends Amf3BaseDTO {
        public var name:String;
        public var pwd:String;
    }
}
