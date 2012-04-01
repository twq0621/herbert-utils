package game.dto {

    import mx.collections.ArrayCollection;
    import lion.core.Amf3BaseDTO;

    [Bindable]
    [RemoteClass(alias="game.dto.Login_S2C")]
    public class Login_S2C extends Amf3BaseDTO {
        public var roleList:ArrayCollection; //Set<RoleDTO>
        private var child0:RoleDTO;
    }
}
