package edu.se181;

import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

//Visual creation of the chessboard
public class Chessboard {

    final int[] original = {0,0};
    final int[] clicked = {0,0};    //row,col in GridPane (chessboard) where user clicked

    public Sprite selectedPiece = null;
    public List<Move> selectedLegalMoves = new ArrayList<>();
    public GridPane chessBoard;
    public ArrayList<Sprite> blackPieces = new ArrayList<>();
    public ArrayList<Sprite> whitePieces = new ArrayList<>();
    public ArrayList<Sprite> capturedWhitePieces = new ArrayList<>();
    public ArrayList<Sprite> capturedBlackPieces = new ArrayList<>();
    public CaptureBox whiteCaptured = new CaptureBox();
    public CaptureBox blackCaptured = new CaptureBox();
    private boolean whiteTurn = true;
    private Game game = new Game();

    public Chessboard(){
        createChessBoard();
        HttpUtil.INSTANCE.setGame(game);
    }

    public void setSelectedPiece(Sprite piece){
        this.selectedPiece = piece;
    }
    public Sprite getSelectedPiece(){
        return this.selectedPiece;
    }
    public ArrayList<Sprite> getCapturedWhitePieces(){
        return this.capturedWhitePieces;
    }
    public ArrayList<Sprite> getCapturedBlackPieces(){
        return this.capturedBlackPieces;
    }
    public void setSelectedLegalMoves(List<Move> moves){
        this.selectedLegalMoves=moves;
    }
    public List<Move> getSelectedLegalMoves(){
        return this.selectedLegalMoves;
    }

    public void createChessBoard() {
        GridPane chessBoard = new GridPane();

        for (int row = 0; row < 8; row++) {
            for (int col = 0; col < 8; col++) {
                Rectangle background = new Rectangle(50, 50);
                StackPane square = new StackPane();
                Color color = Color.GRAY;
                if ((row + col) % 2 == 0) {
                    color = Color.BEIGE;
                }
                background.setFill(color);
                square.setOnMouseClicked(e->{
                    clicked[0] = GridPane.getRowIndex(square);
                    clicked[1] = GridPane.getColumnIndex(square);
                    move(clicked[1],clicked[0]);
                });
                square.getChildren().addAll(background);
                chessBoard.add(square, col, row);
            }
        }
        setPieces(chessBoard);
        this.chessBoard = chessBoard;
    }


    public void setPieces(GridPane board){
        //Initialize all pieces and set to starting position
        Sprite blackKnight1 = new Sprite(new Image(StringSources.INSTANCE.getBLACK_KNIGHT()));
        Sprite blackKnight2 = new Sprite(new Image(StringSources.INSTANCE.getBLACK_KNIGHT()));
        Sprite blackBishop1 = new Sprite(new Image(StringSources.INSTANCE.getBLACK_BISHOP()));
        Sprite blackBishop2 = new Sprite(new Image(StringSources.INSTANCE.getBLACK_BISHOP()));
        Sprite blackRook1 = new Sprite(new Image(StringSources.INSTANCE.getBLACK_ROOK()));
        Sprite blackRook2 = new Sprite(new Image(StringSources.INSTANCE.getBLACK_ROOK()));
        Sprite blackQueen = new Sprite(new Image(StringSources.INSTANCE.getBLACK_QUEEN()));
        Sprite blackKing = new Sprite(new Image(StringSources.INSTANCE.getBLACK_KING()));
        Sprite blackPawn1 = new Sprite(new Image(StringSources.INSTANCE.getBLACK_PAWN()));
        Sprite blackPawn2 = new Sprite(new Image(StringSources.INSTANCE.getBLACK_PAWN()));
        Sprite blackPawn3 = new Sprite(new Image(StringSources.INSTANCE.getBLACK_PAWN()));
        Sprite blackPawn4 = new Sprite(new Image(StringSources.INSTANCE.getBLACK_PAWN()));
        Sprite blackPawn5 = new Sprite(new Image(StringSources.INSTANCE.getBLACK_PAWN()));
        Sprite blackPawn6 = new Sprite(new Image(StringSources.INSTANCE.getBLACK_PAWN()));
        Sprite blackPawn7 = new Sprite(new Image(StringSources.INSTANCE.getBLACK_PAWN()));
        Sprite blackPawn8 = new Sprite(new Image(StringSources.INSTANCE.getBLACK_PAWN()));

        Sprite whiteKnight1 = new Sprite(new Image(StringSources.INSTANCE.getWHITE_KNIGHT()));
        Sprite whiteKnight2 = new Sprite(new Image(StringSources.INSTANCE.getWHITE_KNIGHT()));
        Sprite whiteBishop1 = new Sprite(new Image(StringSources.INSTANCE.getWHITE_BISHOP()));
        Sprite whiteBishop2 = new Sprite(new Image(StringSources.INSTANCE.getWHITE_BISHOP()));
        Sprite whiteRook1 = new Sprite(new Image(StringSources.INSTANCE.getWHITE_ROOK()));
        Sprite whiteRook2 = new Sprite(new Image(StringSources.INSTANCE.getWHITE_ROOK()));
        Sprite whiteKing = new Sprite(new Image(StringSources.INSTANCE.getWHITE_KING()));
        Sprite whiteQueen = new Sprite(new Image(StringSources.INSTANCE.getWHITE_QUEEN()));
        Sprite whitePawn1 = new Sprite(new Image(StringSources.INSTANCE.getWHITE_PAWN()));
        Sprite whitePawn2 = new Sprite(new Image(StringSources.INSTANCE.getWHITE_PAWN()));
        Sprite whitePawn3 = new Sprite(new Image(StringSources.INSTANCE.getWHITE_PAWN()));
        Sprite whitePawn4 = new Sprite(new Image(StringSources.INSTANCE.getWHITE_PAWN()));
        Sprite whitePawn5 = new Sprite(new Image(StringSources.INSTANCE.getWHITE_PAWN()));
        Sprite whitePawn6 = new Sprite(new Image(StringSources.INSTANCE.getWHITE_PAWN()));
        Sprite whitePawn7 = new Sprite(new Image(StringSources.INSTANCE.getWHITE_PAWN()));
        Sprite whitePawn8 = new Sprite(new Image(StringSources.INSTANCE.getWHITE_PAWN()));

        blackPieces.addAll(Arrays.asList(blackRook1,blackKnight1,blackBishop1,blackQueen,blackKing,blackBishop2,
                blackKnight2,blackRook2,blackPawn1,blackPawn2,blackPawn3,blackPawn4,blackPawn5,blackPawn6,blackPawn7,
                blackPawn8));
        whitePieces.addAll(Arrays.asList(whiteRook1,whiteKnight1,whiteBishop1,whiteQueen,whiteKing,whiteBishop2,
                whiteKnight2,whiteRook2,whitePawn1,whitePawn2,whitePawn3,whitePawn4,whitePawn5,whitePawn6,whitePawn7,
                whitePawn8));

        for(int col=0;col<8;col++){
            for(int row=0;row<2;row++){
                int num;
                if(row>0){
                    num = 8+col;
                }
                else {
                    num = ((row + 1) * (col + 1)) - 1;
                }
                GridPane.setConstraints(blackPieces.get(num),col,row);
            }
        }

        GridPane.setConstraints(whiteBishop1,2,7);
        GridPane.setConstraints(whiteBishop2,5,7);
        GridPane.setConstraints(whiteRook1,0,7);
        GridPane.setConstraints(whiteRook2,7,7);
        GridPane.setConstraints(whiteKnight1,1,7);
        GridPane.setConstraints(whiteKnight2,6,7);
        GridPane.setConstraints(whiteKing,4,7);
        GridPane.setConstraints(whiteQueen,3,7);

        for(int col=0;col<8;col++){
            GridPane.setConstraints(whitePieces.get(col+8),col,6);
        }

        for(Sprite sprites: blackPieces){
            sprites.setPosition(GridPane.getColumnIndex(sprites),GridPane.getRowIndex(sprites));
            sprites.setOnMouseClicked(e->{
                if(whiteTurn && whitePieces.contains(getSelectedPiece())){
                    move(sprites.getXPos(),sprites.getYPos());
                }
                else if (!whiteTurn){
                    setSelectedPiece(sprites);
                    setSelectedLegalMoves(getLegalMoves(sprites));
                }

            });
        }
        for(Sprite sprites: whitePieces){
            sprites.setPosition(GridPane.getColumnIndex(sprites),GridPane.getRowIndex(sprites));
            sprites.setOnMouseClicked(e->{
                if(!whiteTurn && blackPieces.contains(getSelectedPiece())){
                    move(sprites.getXPos(),sprites.getYPos());
                }
                else if (whiteTurn){
                    setSelectedPiece(sprites);
                    setSelectedLegalMoves(getLegalMoves(sprites));
                }
            });
        }
        board.getChildren().addAll(blackPieces);
        board.getChildren().addAll(whitePieces);

    }

    public void move(int x, int y){
        if(getSelectedPiece()==null || (whiteTurn && blackPieces.contains(getSelectedPiece())) ||
                (!whiteTurn && whitePieces.contains(getSelectedPiece()))){
            return ;
        }
        if (getSelectedLegalMoves().stream().noneMatch((Move m) -> m.getRankDest() == 7 - y && m.getFileDest() == x)) {
            setSelectedPiece(null);
            getSelectedLegalMoves().clear();
            return ;
        }
        if(whiteTurn){
            for(Sprite piece: blackPieces){
                if(GridPane.getRowIndex(piece)==y && GridPane.getColumnIndex(piece)==x){
                    remove(piece);
                }
            }
        }
        else{
            for(Sprite piece: whitePieces){
                if(GridPane.getRowIndex(piece)==y && GridPane.getColumnIndex(piece)==x){
                    remove(piece);
                }
            }
        }
        Move move = getSelectedLegalMoves().stream().filter((Move m) -> m.getRankDest() == 7 - y && m.getFileDest() == x).collect(Collectors.toList()).get(0);
        if (move instanceof EnPassantMove) {
            if (whiteTurn) {
                Sprite pawn = blackPieces.stream().filter((Sprite s) -> s.getXPos() == x && s.getYPos() == y + 1).collect(Collectors.toList()).get(0);
                remove(pawn);
            } else {
                Sprite pawn = whitePieces.stream().filter((Sprite s) -> s.getXPos() == x && s.getYPos() == y - 1).collect(Collectors.toList()).get(0);
                remove(pawn);
            }
        }
        game.makeMove(move);

        if(move instanceof CastleMove){
            //move rook
            if(((CastleMove) move).isKingSide){
                if(whiteTurn) {
                    Sprite rook = whitePieces.stream().filter((Sprite s) -> s.getXPos() == 7 && s.getYPos() == 7).collect(Collectors.toList()).get(0);
                    GridPane.setConstraints(rook,5,7);
                    rook.setPosition(5,7);
                }
                else{
                    Sprite rook = blackPieces.stream().filter((Sprite s) -> s.getXPos() == 7 && s.getYPos() == 0).collect(Collectors.toList()).get(0);
                    GridPane.setConstraints(rook,5,0);
                    rook.setPosition(5,0);
                }
            }
            else{
                if(whiteTurn){
                    Sprite rook = whitePieces.stream().filter((Sprite s) -> s.getXPos() == 0 && s.getYPos() == 7).collect(Collectors.toList()).get(0);
                    GridPane.setConstraints(rook,3,7);
                    rook.setPosition(3,7);
                }
                else{
                    Sprite rook = blackPieces.stream().filter((Sprite s) -> s.getXPos() == 0 && s.getYPos() == 0).collect(Collectors.toList()).get(0);
                    GridPane.setConstraints(rook,3,0);
                    rook.setPosition(3,0);
                }
            }
            //move king
            GridPane.setConstraints(getSelectedPiece(),x,y);
            System.out.println("here");
        }

        GridPane.setConstraints(getSelectedPiece(), x, y);
        getSelectedPiece().setPosition(x, y);
        setSelectedPiece(null);
        getSelectedLegalMoves().clear();
        whiteTurn = !whiteTurn;
    }

    public void remove(Sprite piece){
        chessBoard.getChildren().remove(piece);
        if(whiteTurn){
            capturedBlackPieces.add(piece);
            blackCaptured.showPiece(piece);
        }
        else{
            capturedWhitePieces.add(piece);
            whiteCaptured.showPiece(piece);
        }
    }

    public void highlight(ImageView piece){
        int y = GridPane.getRowIndex(piece);
        int x = GridPane.getColumnIndex(piece);
        Rectangle nSquare = new Rectangle(50,50);
        nSquare.setFill(Color.TRANSPARENT);

        StackPane newSquare = new StackPane();
        newSquare.getChildren().addAll(nSquare);
        newSquare.setBorder(new Border(new BorderStroke(Color.BLUE,
                BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        chessBoard.add(newSquare,x,y);
    }

    public List<Move> getLegalMoves(Sprite sprite){
        List<Move> moves = game.getLegalMoves(7-sprite.getYPos(),sprite.getXPos());
        if (moves.isEmpty())
            System.out.println("No moves"); // debug
        for(Move m : moves) {
            System.out.print((char) (m.getFileDest() + 97));
            System.out.println((char) (m.getRankDest() + 49));
        }
        System.out.println("----");
        return moves;
    }

}
