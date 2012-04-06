package game.chat.dto {

    [Bindable]
    [RemoteClass(alias="game.chat.dto.Chat_C2S")]
    public class Chat_C2S {
        public var content:String;
        public var targetRole:String;
        public var type:int;
    }
}
