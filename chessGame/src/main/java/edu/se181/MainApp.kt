package edu.se181

import javafx.application.Application
import javafx.stage.Stage

class MainApp : Application() {

    override fun start(primaryStage: Stage?) {
        GameStage.launch(primaryStage)
    }
}
