package game.dto {

    import lion.core.Amf3BaseDTO;

    [Bindable]
    [RemoteClass(alias="game.dto.TestPushMsg_S2C")]
    public class TestPushMsg_S2C extends Amf3BaseDTO {
        public var nameDTO:NameDTO;
    }
}
