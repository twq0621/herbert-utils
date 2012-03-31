package game.dto {

    import lion.core.Amf3BaseDTO;

    [Bindable]
    [RemoteClass(alias="game.dto.CreateRole_C2S")]
    public class CreateRole_C2S extends Amf3BaseDTO {
        public var characterId:int;
        public var gender:int;
        public var roleName:String;
    }
}
