package game.dto {

    import lion.core.Amf3BaseDTO;

    [Bindable]
    [RemoteClass(alias="game.dto.EnterGame_C2S")]
    public class EnterGame_C2S extends Amf3BaseDTO {
        public var selectedRole:String;
    }
}
