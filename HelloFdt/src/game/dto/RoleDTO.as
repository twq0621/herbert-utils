package game.dto {

    [Bindable]
    [RemoteClass(alias="game.dto.RoleDTO")]
    public class RoleDTO {
        public var characterId:int;
        public var gender:int;
        public var name:String;
    }
}
