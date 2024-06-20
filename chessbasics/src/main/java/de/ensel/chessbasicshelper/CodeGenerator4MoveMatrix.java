package de.ensel.chessbasicshelper;

import static de.ensel.chessbasics.ChessBasics.*;

public class CodeGenerator4MoveMatrix {

    public static void main(String[] args) {
        System.out.println("This static class is a kind of demo, how to use the ChessBasics library.");
        System.out.println("(It is not intended to be instantiated or embedded in your program.)");
        System.out.println("You can let it run, to print Java int[][] matrices for the possible moves of each piece.");
        System.out.println("So, you can use the printed result as static code (base data) in your program.");
        System.out.println("");
        printPieceMoves(KING);
        printPieceMoves(QUEEN);
        printPieceMoves(ROOK);
        printPieceMoves(BISHOP);
        printPieceMoves(KNIGHT);
        printPieceMoves(PAWN);
        printPieceMoves(PAWN_BLACK);
    }

    public static String pieceVariableNameForType(int pceType) {
        return //pieceNames[pceType];
                switch (pceType) {
                    case KING ->         "King";
                    case QUEEN ->        "Queen";
                    case ROOK ->         "Rook";
                    case BISHOP ->       "Bishop";
                    case KNIGHT ->       "Knight";
                    case PAWN ->         "PawnWhite";
                    case KING_BLACK ->   "King";
                    case QUEEN_BLACK ->  "Queen";
                    case ROOK_BLACK ->   "Rook";
                    case BISHOP_BLACK -> "Bishop";
                    case KNIGHT_BLACK -> "Knight";
                    case PAWN_BLACK ->   "PawnBlack";
                    default ->           "unknown";
                };
    }

    public static void printPieceMoves(int pceType) {
        int[] directions = pieceDirections(pceType);
        System.out.println("moveMatix4" + pieceVariableNameForType(pceType)+" = new int[][] { ");
        for (int pos=0; pos < NR_SQUARES; pos++) {
            if (isFirstFile(pos))
                System.out.print("    /* rank " + rank2Char(pos) + ": */ ");
            else if(isSlidingPieceType(pceType))
                System.out.print("                  ");
            else
                System.out.print(" ");
            if (isPawn(pceType) && (isFirstRank(pos) || isLastRank(pos))) {
                System.out.print("{}, {}, {}, {}, {}, {}, {}, {}" + (isLastRank(pos) ? "" : ","));
                pos += 7;
            }
            else
                printMovesFromInDirs(pos, directions, !isSlidingPieceType(pceType));
            System.out.print( pos<NR_SQUARES-1 ? ", " : "");
            if (isSlidingPieceType(pceType) || isLastFile(pos))
                System.out.println("");
        }
        System.out.println("};");
        System.out.println("");
    }

    public static void printMovesFromInDirs(int startPos, int[] directions, boolean oneHopOnly) {
        int initFile = fileOf(startPos);
        int initRank = rankOf(startPos);
        boolean komma = false;

        System.out.print("{");
        for (Integer d : directions) {
            boolean dirComment = true;
            int p = startPos;
            while (plusDirIsStillLegal(p, d)) {
                p += d;
                if (komma)
                    System.out.print(",");
                else
                    komma = true;
                System.out.print(" ");
                if (dirComment) {
                    if (!oneHopOnly)
                        System.out.print(" /*" + dirIndexDescription(convertMainDir2DirIndex(d)) + "*/ ");
                    dirComment = false;
                }
                System.out.print(p);
                if (oneHopOnly)
                    break;
            }
        }
        System.out.print(" }");
    }

}
