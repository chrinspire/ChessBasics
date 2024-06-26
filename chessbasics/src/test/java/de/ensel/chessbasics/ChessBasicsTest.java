/*
 *     TideEval - Wired New Chess Algorithm
 *     Copyright (C) 2023 Christian Ensel
 *
 *     This program is free software: you can redistribute it and/or modify
 *     it under the terms of the GNU General Public License as published by
 *     the Free Software Foundation, either version 3 of the License, or
 *     (at your option) any later version.
 *
 *     This program is distributed in the hope that it will be useful,
 *     but WITHOUT ANY WARRANTY; without even the implied warranty of
 *     MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *     GNU General Public License for more details.
 *
 *     You should have received a copy of the GNU General Public License
 *     along with this program.  If not, see <https://www.gnu.org/licenses/>.
 */

package de.ensel.chessbasics;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.*;
        import java.util.stream.Collectors;

import static de.ensel.chessbasics.ChessBasics.*;
        import static org.junit.jupiter.api.Assertions.*;

class ChessBasicsTest {

    @Test
    void isWhite_Test() {
        assertTrue(isWhite(WHITE));
        assertFalse(isWhite(BLACK));
    }

    @Test
    void colorIndex_Test() {
        assertEquals(0, colorIndex(WHITE));
        assertEquals(1, colorIndex(BLACK));
    }

    @Test
    void colorName_Test() {
        assertNotNull(chessBasicRes.getString("colorname_white"));
        assertNotNull(chessBasicRes.getString("colorname_black"));
        assertEquals(chessBasicRes.getString("colorname_white"), colorName(WHITE));
        assertEquals(chessBasicRes.getString("colorname_black"), colorName(BLACK));
    }

    @Test
    void isQueen_Test() {
        assertTrue(isQueen(QUEEN));
        assertTrue(isQueen(QUEEN_BLACK));
        assertFalse(isQueen(ROOK));
        assertFalse(isQueen(KNIGHT_BLACK));
    }

    @Test
    void givePieceName_Test() {
        assertNotNull(chessBasicRes.getString("pieceName.knight"));
        assertNotNull(chessBasicRes.getString("pieceName.bishop"));
        assertEquals(chessBasicRes.getString("pieceName.knight"), pieceNameForType(KNIGHT));
        assertEquals(chessBasicRes.getString("pieceName.bishop"), pieceNameForType(BISHOP_BLACK));
    }

    /*@Test
    void givePieceColorAndName() {
    }*/

    @Test
    void isPieceTypeNrWhite_Test() {
        assertTrue(isPieceTypeWhite(QUEEN));
        assertTrue(isPieceTypeWhite(KING));
        assertFalse(isPieceTypeWhite(PAWN_BLACK));
        assertFalse(isPieceTypeWhite(KNIGHT_BLACK));
    }

    @Test
    void isPieceTypeNrBlack_Test() {
        assertTrue(isPieceTypeBlack(QUEEN_BLACK));
        assertTrue(isPieceTypeBlack(KING_BLACK));
        assertFalse(isPieceTypeBlack(PAWN));
        assertFalse(isPieceTypeBlack(KNIGHT));
    }

    @Test
    void colorOfPieceTypeNr_Test() {
        assertEquals(BLACK, colorOfPieceType(QUEEN_BLACK));
        assertEquals(BLACK, colorOfPieceType(KING_BLACK));
        assertEquals(WHITE, colorOfPieceType(PAWN));
        assertEquals(WHITE, colorOfPieceType(KNIGHT));
    }

    @Test
    void colorlessPieceTypeNr_Test() {
        assertEquals(QUEEN, colorlessPieceType(QUEEN_BLACK));
        assertEquals(KING, colorlessPieceType(KING_BLACK));
        assertEquals(PAWN, colorlessPieceType(PAWN));
        assertEquals(KNIGHT, colorlessPieceType(KNIGHT));
    }

    /*@Test
    void convertMainDir2DirIndex() {
    }

    @Test
    void convertDirIndex2MainDir() {
    }*/

    @Test
    @SuppressWarnings("nls")
    void squareName_Test() {
        squareName_singleTest("a1", A1SQUARE);
        squareName_singleTest("b1", A1SQUARE + RIGHT);
        squareName_singleTest("b2", A1SQUARE + UPRIGHT);
    }

    private void squareName_singleTest(String expected, int testPos) {
        assertTrue( expected.equalsIgnoreCase( squareName( testPos ) ) );
    }

    @Test
    @SuppressWarnings("nls")
    void coordinateString2Pos_Test() {
        assertEquals(A1SQUARE, coordinateString2Pos("a1b1", 0 ));
        assertEquals(A1SQUARE, coordinateString2Pos("b2a1", 2 ));
    }


    @Test
    void isFirstFile_Test() {
        for(int pos=0; pos<A1SQUARE; pos+=NR_FILES)
            assertTrue(ChessBasics.isFirstFile(pos));
        for(int pos=1; pos<A1SQUARE; pos+=NR_FILES)
            assertFalse(ChessBasics.isFirstFile(pos));
        for(int pos=NR_FILES-1; pos<(NR_SQUARES-NR_FILES); pos+=NR_FILES)
            assertFalse(isFirstFile(pos));
    }

    @Test
    void isLastFile_Test() {
        for(int pos=NR_FILES-1; pos<A1SQUARE; pos+=NR_FILES)
            assertTrue(isLastFile(pos));
        for(int pos=0; pos<A1SQUARE; pos+=NR_FILES)
            assertFalse(isLastFile(pos));
        for(int pos=NR_FILES-2; pos<A1SQUARE; pos+=NR_FILES)
            assertFalse(isLastFile(pos));
    }

    @ParameterizedTest
    @ValueSource(ints = {A1SQUARE, A1SQUARE+1, NR_SQUARES-2, NR_SQUARES-1})
        // Tests 4 true samples - assumes board size of NR_FILES>=2
    void isFirstRank_Test_True(int pos) {
        assertTrue(isFirstRank(pos));
    }

    @Test
    void isFirstRank_Test_False() {
        // run from "a8" diagonally over board, up to almost the first rank "1" to make false-samples
        for(int pos=0; pos<(A1SQUARE-NR_FILES-1); pos+=NR_FILES+1)
            assertFalse(isFirstRank(pos));
    }

    @ParameterizedTest
    @ValueSource(ints = {0, 1, NR_FILES-2, NR_FILES-1})
        // Tests 4 true samples - assumes board size of NR_FILES>=2
    void isLastRank_Test_True(int pos) {
        assertTrue(isLastRank(pos));
    }

    @Test
    void isLastRank_Test_False() {
        // run from "a1" diagonally over board, up to almost the last rank "1" to make false-samples
        for(int pos=A1SQUARE; pos>(2*NR_FILES-1); pos-=NR_FILES-1)
            assertFalse(isLastRank(pos));
    }

    @Test
    void firstFileInRank_Test() {
        for (int rank=0; rank<NR_RANKS; rank++) {
            int startpos = rank*NR_FILES;
            assertEquals(startpos, firstFileInRank(startpos));
            assertEquals(startpos, firstFileInRank(startpos+RIGHT));
            assertEquals(startpos, firstFileInRank(startpos + NR_FILES-1));
            assertEquals(startpos, firstFileInRank(startpos + NR_FILES-1 + 2*LEFT));
        }
    }

    @Test
    void lastFileInRank_Test() {
        for (int rank=0; rank<NR_RANKS; rank++) {
            int startpos = rank*NR_FILES;
            assertEquals(startpos+NR_FILES-1, lastFileInRank(startpos));
            assertEquals(startpos+NR_FILES-1, lastFileInRank(startpos+RIGHT));
            assertEquals(startpos+NR_FILES-1, lastFileInRank(startpos + NR_FILES-1));
            assertEquals(startpos+NR_FILES-1, lastFileInRank(startpos + NR_FILES-1 + 2*LEFT));
        }
    }

    @Test
    void firstRankInFile_Test() {
        for (int file=0; file<NR_FILES; file++) {
            assertEquals(A1SQUARE+file, firstRankInFile(A1SQUARE+file));
            assertEquals(A1SQUARE+file, firstRankInFile(file));
            assertEquals(A1SQUARE+file, firstRankInFile(file + 2 * DOWN));
            assertEquals(A1SQUARE+file, firstRankInFile(A1SQUARE + file + UP));
        }
    }

    @Test
    void lastRankInFile_Test() {
        for (int file=0; file<NR_FILES; file++) {
            assertEquals(file, lastRankInFile(A1SQUARE+file));
            assertEquals(file, lastRankInFile(file));
            assertEquals(file, lastRankInFile(file + 2 * DOWN));
            assertEquals(file, lastRankInFile(A1SQUARE + file + UP));
        }
    }

    @Test
    void rankOf_Test() {
        assertEquals(0, rankOf(A1SQUARE));
        assertEquals(0, rankOf(NR_SQUARES-1));
        assertEquals(NR_RANKS-1, rankOf(NR_FILES-1));
        assertEquals(NR_RANKS-2, rankOf(NR_FILES));
    }

    @Test
    void fileOf_Test() {
        assertEquals(0, fileOf(A1SQUARE));
        assertEquals(NR_FILES-1, fileOf(NR_SQUARES-1));
        assertEquals(NR_FILES-1, fileOf(NR_FILES-1));
        assertEquals(0, fileOf(NR_FILES));
    }

    @Test
    void knightMoveInDirFromPosStaysOnBoard_Test() {
        // some legal moves
        assertTrue( knightMoveInDirFromPosStaysOnBoard(KNIGHT_DIR_REREUP,   A1SQUARE));
        assertTrue( knightMoveInDirFromPosStaysOnBoard(KNIGHT_DIR_UPUPRIGHT,A1SQUARE));
        assertTrue( knightMoveInDirFromPosStaysOnBoard(KNIGHT_DIR_UPUPLEFT, A1SQUARE+RIGHT));
        assertTrue( knightMoveInDirFromPosStaysOnBoard(KNIGHT_DIR_REREDOWN, A1SQUARE+UP));
        // some moves of the board
        assertFalse( knightMoveInDirFromPosStaysOnBoard(KNIGHT_DIR_REREDOWN, A1SQUARE));
        assertFalse( knightMoveInDirFromPosStaysOnBoard(KNIGHT_DIR_UPUPLEFT, A1SQUARE));
        assertFalse( knightMoveInDirFromPosStaysOnBoard(KNIGHT_DIR_LELEUP,   A1SQUARE+RIGHT));
        assertFalse( knightMoveInDirFromPosStaysOnBoard(KNIGHT_DIR_LELEDOWN, A1SQUARE+RIGHT));
        // some non-knight-moves
        assertFalse( knightMoveInDirFromPosStaysOnBoard(RIGHT, A1SQUARE));
        assertFalse( knightMoveInDirFromPosStaysOnBoard(2*RIGHT, A1SQUARE));
        assertFalse( knightMoveInDirFromPosStaysOnBoard(DOWN,   A1SQUARE));
        assertFalse( knightMoveInDirFromPosStaysOnBoard(UP, A1SQUARE+RIGHT));
    }

    @Test
    void fileRank2Pos_Test() {
        assertEquals(A1SQUARE, fileRank2Pos(0, 0) );
        assertEquals(NR_FILES-1, fileRank2Pos(NR_FILES-1, NR_RANKS-1) );
    }

    @ParameterizedTest
    @CsvSource({"w, a4, a2+a3+b3",
            "w, a3, a2+b2",
            "w, a5, a4+b4",
            "w, h4, h2+h3+g3",
            "w, h3, h2+g2",
            "w, h5, h4+g4",
            "w, b4, b2+b3+a3+c3",
            "w, d8, c7+d7+e7",
            "b, d1, c2+d2+e2",
            "b, b5, b6+b7+a6+c6",
            "b, h6, g7+h7",
            "b, a5, a7+a6+b6"})
    void getAllPawnPredecessorPositionsTest(String col, String posS, String expectedSL) {
        List<Integer> expected = Arrays.stream(expectedSL.split("\\+"))
                .map(s->coordinateString2Pos(s))
                .collect(Collectors.toList());
        expected.sort(Comparator.naturalOrder() );
        List<Integer> calculated = getAllPawnPredecessorPositions(col.toLowerCase().startsWith("w"), coordinateString2Pos(posS) );
        calculated.sort(Comparator.naturalOrder());
        assertTrue(expected.equals(calculated));
    }

}