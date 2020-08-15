package edu.se181;

import java.util.ArrayList;
import java.util.List;

public class Board {
    private Square[][] squares;
    private List<Piece> whitePieces;
    private List<Piece> blackPieces;
    private boolean playingWhite;

    public Board() {
        reset();
    }

    private void reset() {
        squares = new Square[8][8];
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                // a8 ... h8
                // ...    ...
                // a1 ... h1
                squares[i][j] = new Square(7 - i, j, null);
            }
        }
        whitePieces = new ArrayList<>();
        blackPieces = new ArrayList<>();
        playingWhite = true;
    }

    private void invertBoard() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                squares[i][j].setRank(i);
                squares[i][j].setFile(7 - j);
            }
        }
    }

    public void initialize(boolean white) {
        playingWhite = white;

        // invert board if playing black
        if (!white) {
            invertBoard();
        }

        // opposing nonpawns
        squares[0][0].setOccupant(new Rook(squares[0][0].getRank(), squares[0][0].getFile(), !white));
        squares[0][1].setOccupant(new Knight(squares[0][1].getRank(), squares[0][1].getFile(), !white));
        squares[0][2].setOccupant(new Bishop(squares[0][2].getRank(), squares[0][2].getFile(), !white));
        if (white) {
            squares[0][3].setOccupant(new Queen(squares[0][3].getRank(), squares[0][3].getFile(), !white));
            squares[0][4].setOccupant(new King(squares[0][4].getRank(), squares[0][4].getFile(), !white));
        } else {
            squares[0][3].setOccupant(new King(squares[0][3].getRank(), squares[0][3].getFile(), !white));
            squares[0][4].setOccupant(new Queen(squares[0][4].getRank(), squares[0][4].getFile(), !white));
        }
        squares[0][5].setOccupant(new Bishop(squares[0][5].getRank(), squares[0][5].getFile(), !white));
        squares[0][6].setOccupant(new Knight(squares[0][6].getRank(), squares[0][6].getFile(), !white));
        squares[0][7].setOccupant(new Rook(squares[0][7].getRank(), squares[0][7].getFile(), !white));

        // opposing pawns
        squares[1][0].setOccupant(new Pawn(squares[1][0].getRank(), squares[1][0].getFile(), !white));
        squares[1][1].setOccupant(new Pawn(squares[1][1].getRank(), squares[1][1].getFile(), !white));
        squares[1][2].setOccupant(new Pawn(squares[1][2].getRank(), squares[1][2].getFile(), !white));
        squares[1][3].setOccupant(new Pawn(squares[1][3].getRank(), squares[1][3].getFile(), !white));
        squares[1][4].setOccupant(new Pawn(squares[1][4].getRank(), squares[1][4].getFile(), !white));
        squares[1][5].setOccupant(new Pawn(squares[1][5].getRank(), squares[1][5].getFile(), !white));
        squares[1][6].setOccupant(new Pawn(squares[1][6].getRank(), squares[1][6].getFile(), !white));
        squares[1][7].setOccupant(new Pawn(squares[1][7].getRank(), squares[1][7].getFile(), !white));

        // player nonpawns
        squares[7][0].setOccupant(new Rook(squares[7][0].getRank(), squares[7][0].getFile(), white));
        squares[7][1].setOccupant(new Knight(squares[7][1].getRank(), squares[7][1].getFile(), white));
        squares[7][2].setOccupant(new Bishop(squares[7][2].getRank(), squares[7][2].getFile(), white));
        if (white) {
            squares[7][3].setOccupant(new Queen(squares[7][3].getRank(), squares[7][3].getFile(), white));
            squares[7][4].setOccupant(new King(squares[7][4].getRank(), squares[7][4].getFile(), white));
        } else {
            squares[7][3].setOccupant(new King(squares[7][3].getRank(), squares[7][3].getFile(), white));
            squares[7][4].setOccupant(new Queen(squares[7][4].getRank(), squares[7][4].getFile(), white));
        }
        squares[7][5].setOccupant(new Bishop(squares[7][5].getRank(), squares[7][5].getFile(), white));
        squares[7][6].setOccupant(new Knight(squares[7][6].getRank(), squares[7][6].getFile(), white));
        squares[7][7].setOccupant(new Rook(squares[7][7].getRank(), squares[7][7].getFile(), white));

        //player pawns
        squares[6][0].setOccupant(new Pawn(squares[6][0].getRank(), squares[6][0].getFile(), white));
        squares[6][1].setOccupant(new Pawn(squares[6][1].getRank(), squares[6][1].getFile(), white));
        squares[6][2].setOccupant(new Pawn(squares[6][2].getRank(), squares[6][2].getFile(), white));
        squares[6][3].setOccupant(new Pawn(squares[6][3].getRank(), squares[6][3].getFile(), white));
        squares[6][4].setOccupant(new Pawn(squares[6][4].getRank(), squares[6][4].getFile(), white));
        squares[6][5].setOccupant(new Pawn(squares[6][5].getRank(), squares[6][5].getFile(), white));
        squares[6][6].setOccupant(new Pawn(squares[6][6].getRank(), squares[6][6].getFile(), white));
        squares[6][7].setOccupant(new Pawn(squares[6][7].getRank(), squares[6][7].getFile(), white));

        if (white) {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 8; j++) {
                    blackPieces.add(squares[i][j].getOccupant());
                }
            }
            for (int i = 6; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    whitePieces.add(squares[i][j].getOccupant());
                }
            }
        } else {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 8; j++) {
                    whitePieces.add(squares[i][j].getOccupant());
                }
            }
            for (int i = 6; i < 8; i++) {
                for (int j = 0; j < 8; j++) {
                    blackPieces.add(squares[i][j].getOccupant());
                }
            }
        }
    }

    public void move(Move move) {
        // TODO: Implement move
    }

    public Square[][] getSquares() {
        return squares;
    }

    public List<Piece> getWhitePieces() {
        return whitePieces;
    }

    public List<Piece> getBlackPieces() {
        return blackPieces;
    }

    public Square getSquareByPieceOffset(Piece piece, int rankOffset, int fileOffset) {
        int newRank = piece.getRank() + rankOffset;
        int newFile = piece.getFile() + fileOffset;
        if (newRank < 0 || newRank > 7 || newFile < 0 || newFile > 7)
            return null;
        return squares[newRank][newFile];
    }

    public Square getSquareByNotation(String notation) {
        char rankChar = notation.charAt(1);
        char fileChar = notation.charAt(0);
        int rank = (int) rankChar - 96;
        int file = (int) fileChar - 49;
        Square ret;
        if (playingWhite)
            ret = squares[8 - rank][file];
        else
            ret = squares[rank][8 - file];
        return ret;
    }

    // format: abbcdefpppp...
    // a - 0 if playing black, 1 if playing white
    // bb - square of en passantable pawn or 00 if there isn't one
    // c - 1 if white can castle kingside, 0 otherwise
    // d - 1 if white can castle queenside, 0 otherwise
    // e - 1 if black can castle kingside, 0 otherwise
    // f - 1 if black can castle queenside, 0 otherwise
    // pppp... - 0 if no piece occupant, PNBRQK if white pawn, knight, etc, pnbrqk if black pawn, knight, etc
    // pppp... - indicates piece at a8, b8, ... h8, a7, b7, ..., h1 (top left to bottom right by row)
    public void loadBoard(String str) {
        reset();
        String meta = str.substring(0, 7);
        String pieces = str.substring(7);
        if (meta.substring(0, 1).equals("0")) {
            invertBoard();
            playingWhite = false;
        }

        for (int i = 0; i < 64; i++) {
            char p = pieces.charAt(i);
            if (p == '0')
                continue;
            int rank = playingWhite ? 7 - (i / 8) : i / 8;
            int file = playingWhite ? i % 8 : 7 - (i % 8);
            Piece newPiece;
            if (p == 'P') {
                newPiece = new Pawn(rank, file, true);
            } else if (p == 'N') {
                newPiece = new Knight(rank, file, true);
            } else if (p == 'B') {
                newPiece = new Bishop(rank, file, true);
            } else if (p == 'R') {
                newPiece = new Rook(rank, file, true);
            } else if (p == 'Q') {
                newPiece = new Queen(rank, file, true);
            } else if (p == 'K') {
                newPiece = new King(rank, file, true);
            } else if (p == 'p') {
                newPiece = new Pawn(rank, file, false);
            } else if (p == 'n') {
                newPiece = new Knight(rank, file, false);
            } else if (p == 'b') {
                newPiece = new Bishop(rank, file, false);
            } else if (p == 'r') {
                newPiece = new Rook(rank, file, false);
            } else if (p == 'q') {
                newPiece = new Queen(rank, file, false);
            } else if (p == 'k') {
                newPiece = new King(rank, file, false);
            } else {
                System.out.println("Invalid character, " + p + ", in board string. Skipping...");
                continue;
            }
            if (p < 97)
                whitePieces.add(newPiece);
            else
                blackPieces.add(newPiece);
            squares[rank][file].setOccupant(newPiece);
        }

        // en passant pawn
        if (!meta.substring(1, 3).equals("00")) {
            try {
                Pawn pawn = (Pawn) getSquareByNotation(meta.substring(1, 3)).getOccupant();
                pawn.setEnPassantable(true);
            } catch (ClassCastException e) {
                System.out.println("The piece on square " + meta.substring(1, 3) + " was not a pawn. Skipping en passantable...");
            }
        }

        // we don't have to check if the piece is a rook because a rook not on its starting square will disable castling automatically
        // white kingside castle
        if (meta.charAt(3) == '0') {
            Piece piece = getSquareByNotation("h1").getOccupant();
            if (piece != null)
                piece.firstMovePerformed();
        }

        // white queenside castle
        if (meta.charAt(4) == '0') {
            Piece piece = getSquareByNotation("a1").getOccupant();
            if (piece != null)
                piece.firstMovePerformed();
        }

        // black kingside castle
        if (meta.charAt(5) == '0') {
            Piece piece = getSquareByNotation("h8").getOccupant();
            if (piece != null)
                piece.firstMovePerformed();
        }

        // black queenside castle
        if (meta.charAt(6) == '0') {
            Piece piece = getSquareByNotation("a8").getOccupant();
            if (piece != null)
                piece.firstMovePerformed();
        }
    }

    //
    public String toBoardState() {
        String ret = "";
        if (playingWhite)
            ret += "1";
        else
            ret += "0";

        // en passant
        boolean foundEP = false;
        for (Piece p : whitePieces) {
            if (p instanceof Pawn && ((Pawn) p).isEnPassantable()) {
                ret += (char) (p.getFile() + 49) + (char) (p.getRank() + 96);
                foundEP = true;
                break;
            }
        }
        for (Piece p : blackPieces) {
            if (!foundEP && p instanceof Pawn && ((Pawn) p).isEnPassantable()) {
                ret += (char) (p.getFile() + 49) + (char) (p.getRank() + 96);
                foundEP = true;
                break;
            }
        }
        if (!foundEP)
            ret += "00";

        // white kingside castle
        Piece k = getSquareByNotation("e1").getOccupant();
        Piece p = getSquareByNotation("h1").getOccupant();
        if (p instanceof Rook && !p.hasMoved() && k instanceof King && !k.hasMoved())
            ret += "1";
        else
            ret += "0";

        // white queenside castle
        p = getSquareByNotation("a1").getOccupant();
        if (p instanceof Rook && !p.hasMoved() && k instanceof King && !k.hasMoved())
            ret += "1";
        else
            ret += "0";

        // black kingside castle
        k = getSquareByNotation("e8").getOccupant();
        p = getSquareByNotation("h8").getOccupant();
        if (p instanceof Rook && !p.hasMoved() && k instanceof King && !k.hasMoved())
            ret += "1";
        else
            ret += "0";

        // black queenside castle
        p = getSquareByNotation("a8").getOccupant();
        if (p instanceof Rook && !p.hasMoved() && k instanceof King && !k.hasMoved())
            ret += "1";
        else
            ret += "0";

        if (playingWhite) {
            for (int i = 7; i >= 0; i--) {
                for (int j = 0; j < 8; j++) {
                    Piece piece = squares[i][j].getOccupant();
                    if (piece instanceof Pawn && p.isWhite())
                        ret += "P";
                    else if (piece instanceof Knight && p.isWhite())
                        ret += "N";
                    else if (piece instanceof Bishop && p.isWhite())
                        ret += "B";
                    else if (piece instanceof Rook && p.isWhite())
                        ret += "R";
                    else if (piece instanceof Queen && p.isWhite())
                        ret += "Q";
                    else if (piece instanceof King && p.isWhite())
                        ret += "K";
                    else if (piece instanceof Pawn && !p.isWhite())
                        ret += "p";
                    else if (piece instanceof Knight && !p.isWhite())
                        ret += "n";
                    else if (piece instanceof Bishop && !p.isWhite())
                        ret += "b";
                    else if (piece instanceof Rook && !p.isWhite())
                        ret += "r";
                    else if (piece instanceof Queen && !p.isWhite())
                        ret += "q";
                    else if (piece instanceof King && !p.isWhite())
                        ret += "k";
                    else
                        ret += "0";
                }
            }
        } else {
            for (int i = 0; i < 8; i++) {
                for (int j = 7; j >= 0; j--) {
                    Piece piece = squares[i][j].getOccupant();
                    if (piece instanceof Pawn && p.isWhite())
                        ret += "P";
                    else if (piece instanceof Knight && p.isWhite())
                        ret += "N";
                    else if (piece instanceof Bishop && p.isWhite())
                        ret += "B";
                    else if (piece instanceof Rook && p.isWhite())
                        ret += "R";
                    else if (piece instanceof Queen && p.isWhite())
                        ret += "Q";
                    else if (piece instanceof King && p.isWhite())
                        ret += "K";
                    else if (piece instanceof Pawn && !p.isWhite())
                        ret += "p";
                    else if (piece instanceof Knight && !p.isWhite())
                        ret += "n";
                    else if (piece instanceof Bishop && !p.isWhite())
                        ret += "b";
                    else if (piece instanceof Rook && !p.isWhite())
                        ret += "r";
                    else if (piece instanceof Queen && !p.isWhite())
                        ret += "q";
                    else if (piece instanceof King && !p.isWhite())
                        ret += "k";
                    else
                        ret += "0";
                }
            }
        }
        return ret;
    }
}
