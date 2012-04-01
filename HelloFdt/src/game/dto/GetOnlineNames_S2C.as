package game.dto {

    import mx.collections.ArrayCollection;
    import lion.core.Amf3BaseDTO;

    [Bindable]
    [RemoteClass(alias="game.dto.GetOnlineNames_S2C")]
    public class GetOnlineNames_S2C extends Amf3BaseDTO {
        public var roleNames:ArrayCollection; //List<String>
    }
}
