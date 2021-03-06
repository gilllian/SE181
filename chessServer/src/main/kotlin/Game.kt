import io.javalin.websocket.WsContext
import io.javalin.websocket.WsMessageContext

data class Game (
        var name: String = "",
        val private: Boolean = false,
        var gameId: String = "",
        var players: Int = 0,
        private val password: String = ""
    ) {

    private val playerMap = hashMapOf<WsContext, String>()

    fun addPlayer(ctx: WsContext, username: String) {
        playerMap[ctx] = username
        players = playerMap.size
    }

    fun getPlayer(ctx: WsContext): WsContext? {
        return playerMap.keys.firstOrNull { x -> x == ctx }
    }

    fun removePlayer(ctx: WsContext) {
        if (playerMap.contains(ctx)) {
            playerMap.remove(ctx)
            players = playerMap.size
        }
    }

    fun checkPassword(input: String?) : Boolean {
        return input != null && input == password
    }

    fun disconnectPlayers() {
        playerMap.keys.forEach {session ->
            session.session.close()
            removePlayer(session)
        }
    }

    fun sendMessage(ctx: WsMessageContext) {
        playerMap.keys.forEach { session ->
            if (session != ctx)
                session.send(ctx.message())
        }
    }
}
