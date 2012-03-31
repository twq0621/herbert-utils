package game.dto {

    [Bindable]
    [RemoteClass(alias="game.dto.NameDTO")]
    public class NameDTO {
        public var age:int;
        public var money:Number;
        public var name:String;
        public var passwprd:String;
    }
}
