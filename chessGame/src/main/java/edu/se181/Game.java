package edu.se181;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Game {
    private Board board;
    private boolean whiteTurn;
    private boolean playerIsWhite;
    private Socket socket;
    protected GameLogic logicUnit;
    private List<Move> moveList;
    protected List<String> boardStates;
    protected int halfMoveClock;
    protected int repetitionIndex;
    private Chessboard chessboard;

    public Game() {
        board = new Board();
        board.initialize();
        logicUnit = new GameLogic(board);
        moveList = new ArrayList<>();
        boardStates = new ArrayList<>();
        boardStates.add(board.toBoardState());
        halfMoveClock = 0;
        repetitionIndex = 0;
        whiteTurn = true;
    }

    public Board getBoard() {
        return board;
    }

    public boolean isWhiteTurn() {
        return whiteTurn;
    }

    public void changeTurn() {
        whiteTurn = !whiteTurn;
    }

    public boolean isPlayerWhite() {
        return playerIsWhite;
    }

    public void setChessboard(Chessboard cb) {
        chessboard = cb;
    }

    protected void handleIrreversible(Move move) {
        int irreversible = DrawChecker.isIrreversibleMove(move);
        if (irreversible > 0) {
            repetitionIndex = boardStates.size();
        }
        if (irreversible == 1) {
            halfMoveClock = 0;
        }
    }

    public void handleFinish() {
        if (!logicUnit.isFinished())
            return;
        GameStage.endGame(logicUnit.finishState, logicUnit.drawCondition);
        // TODO: tell server to end game
    }

    public void makeMove(Move move) {
        handleIrreversible(move);

        board.makeMove(move);

        halfMoveClock++;
        moveList.add(move);
        boardStates.add(board.toBoardState());

        logicUnit.analyzeGameState(isWhiteTurn(), halfMoveClock, repetitionIndex, boardStates);

        handleFinish();

        if (isPlayerWhite() == isWhiteTurn()) {
            //HttpUtil.INSTANCE.sendMessage("move", logicUnit.convertToNotation(move));
        }

        changeTurn();
    }

    public List<Move> getLegalMoves(int rank, int file) {
        Piece mover = board.getSquareByRankFile(rank, file).getOccupant();
        return logicUnit.getLegalMoves(mover);
    }
}
