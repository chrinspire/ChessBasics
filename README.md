# ChessBasics

<p align="left">
  <a href="#"><img src="https://img.shields.io/badge/Java-ED8B00?style=flat&logo=java&logoColor=white"></img></a>
  <a href="https://opensource.org/license/gpl-3-0"><img src="https://img.shields.io/github/license/AP-Sensing/ostree-tui?color=black"></img></a>
  <a href="#"><img src="https://img.shields.io/github/stars/chrinspire/ChessBasics"></img></a>
  <a href="#"><img src="https://img.shields.io/github/forks/chrinspire/ChessBasics"></img></a>
  <a href="#"><img src="https://img.shields.io/github/repo-size/chrinspire/ChessBasics"></img></a>
  <a href="https://github.com/chrinspire/ChessBasics/graphs/contributors"><img src="https://img.shields.io/github/contributors/chrinspire/ChessBasics?color=blue"></img></a>
  <a href="https://github.com/chrinspire/ChessBasics/issues"><img src="https://img.shields.io/github/issues/chrinspire/ChessBasics"></img></a>
</p>

**Basic constants and functions for "hand made" Java Chess implementations**
This class **ChessBasics** might be useful for experimental chess developments in Java, esp. if you are not trying to build a "high performance" engine with bitboards, but experimentally try out your own code.
It is not an engine, just supporting the building of your own one. It provides basic chess logic and does not include features like bitboards or move generation.
It provides a foundation for building your own chess implementations in Java, allowing you to focus on specific aspects of engine implementations that you are interested in.

**Basic constants and methods for "hand made" Java Chess implementations**
This class **ChessBasics** might be useful for experimental chess developments in Java, esp. if you are not trying to build a "high performance" engine with bitboards, but experimentally try out your own code.
It is not an engine, just supporting the building of your own one. It provides basic chess logic and does not include features like bitboards or move generation.
It provides a foundation for building your own chess implementations in Java based on board representations by an arrays, allowing you to focus on specific aspects of engine implementations that you are interested in.

**Constants:**

- **Piece Types:**  Defines constants for each piece type: `KING`, `QUEEN`, `ROOK`, `BISHOP`, `KNIGHT`, `PAWN` 
  - and same for black `BLACK_KING`, `BLACK_QUEEN`, `BLACK_ROOK`, `BLACK_BISHOP`, `BLACK_KNIGHT`, `BLACK_PAWN`.
- **Colors:**  Represents the two colors (e.g., `WHITE`, `BLACK`) in boolean and integer (for array access).
- **Board Dimensions:**  Specifies the size of the chessboard (e.g., `NR_RANKS` and `NR_FILES`).
- **Starting Positions:**  Defines the initial positions of all pieces on the board as typical FEN strings, 
like `FENPOS_STARTPOS` or `FENPOS_EMPTY`.
- **Evaluation Constants:**  Defines constants for evaluation, including checkmate values, base piece values and fractions like `EVAL_TENTH` for 1/10th of a pawn value.
 - **Directions:**  Defines constants for directions on the chessboard, including `UP`, `DOWN`, `LEFT`, `RIGHT`, and diagonals like `UPLEFT` and those for knights, like `KNIGHT_DIR_UPUPLEFT` and arrays collecting all legal directions for specific pieces.  

**Methods for colors:**
- **`colorName(boolean color)`/`colorName(int colorIndex)`:** Returns the name of the color (e.g., "White" or "Black").
- **`isWhite( boolean color)`/`isWhite(int colorindex)`:** Checks if the given evaluation represents a checkmate for the given color.
- **`isBlack( boolean color)`/`isBlack(int colorindex)`:** Checks if the given evaluation represents a checkmate for the given color.
- **`colorIndex(boolean col)` and `colorFromColorIndex(int colIndex)`:** Convert between boolean and index representation.
- **`opponentColor(boolean col)` and `opponentColorIndex(int colIndex)`:** Return the opposit = opponents color.  

**Methods concerning evaluations:**
- **`checkmateEval(boolean color)`:** Returns the evaluation for a checkmate against the given color.
- **`isCheckmateEvalFor(int eval, boolean color)`:** Checks if the given evaluation represents a checkmate for the given color.
- **`evalForColor(int eval, boolean color)`:** Returns the evaluation adjusted for the given color.
- **`pieceBaseValue(int pceTypeNr)`:** Returns the base value of a piece type in centipawns.
- **`singleLightPieceBaseValue(int pceTypeNr)`:** Returns the base value of a light piece (bishop or knight) that has no other same type piece on the board any more, in centipawns.
- **`reversePieceBaseValue(int pceTypeNr)`:** Returns a reversed piece value for specific cases.
- **`evalIsOkForColByMin(final int eval, final boolean col, final int min)`:** Checks if a square's evaluation is acceptable for a given color.
- **`evalIsOkForColByMin(final int eval, final boolean col)`:** Checks if a square's evaluation is acceptable for a given color with a default minimum value.
- **`isBetterThenFor(int eval1, int eval2, boolean color)`:** Compares two evaluations based on the color.
- **`maxFor(int eval1, int eval2, boolean color)`:** Returns the maximum of two evaluations based on the color.
- **`minFor(int eval1, int eval2, boolean color)`:** Returns the minimum of two evaluations based on the color.

**Methods concerning piece types:**
- **`fenCharFromPceType(int pceType)`:** Returns the FEN character representation of a piece type.
- **`getPceTypeFromPromoteChar(char promoteToChar)`:** Returns the piece type corresponding to a promotion character.
- **`pceTypeFromPieceSymbol(char c)`:** Returns the piece type corresponding to a piece symbol.
- **`isQueen(int pceType)`:** Checks if a piece type is a queen.
- **`isPawn(int pceType)`:** Checks if a piece type is a pawn.
- **`isKing(int pceType)`:** Checks if a piece type is a king.
- **`isRook(int pceType)`:** Checks if a piece type is a rook.
- **`isSlidingPieceType(int pceType)`:** Checks if a piece type is a sliding piece (rook, bishop, or queen).
- **`isLightPieceType(int pceType)`:** Checks if a piece type is a light piece (bishop or knight).
- **`pieceNameForType(int pceType)`:** Returns the name of a piece type.
- **`pieceColorAndName(int pceType)`:** Returns the color and name of a piece type.
- **`isPieceTypeWhite(int pceType)`:** Checks if a piece of this type is white.
- **`isPieceTypeBlack(int pceType)`:** Checks if a piece of this type is black.
- **`colorOfPieceType(int pceType)`:** Returns the color of a piece type.
- **`colorIndexOfPieceType(int pceType)`:** Returns the color index of a piece type.
- **`colorlessPieceType(int pceType)`:** Returns the piece type without its color.

**Methods dealing with directions:**
- **`convertMainDir2DirIndex(final int dir)`:** Converts a main direction to a direction index.
- **`convertDirIndex2MainDir(final int d)`:** Converts a direction index to a main direction.
- **`oppositeDirIndex(final int dirindex)`:** Returns the opposite direction index.
- **`isRookDir(final int dir)`:** Checks if a direction is a rook direction.
- **`isBishopDir(final int dir)`:** Checks if a direction is a bishop direction.
- **`isBishopDir(final int from, final int to)`:** Checks if a move is in a bishop direction.
- **`isRookDir(final int from, final int to)`:** Checks if a move is in a rook direction.
- **`isCorrectSlidingPieceDirFromTo(final int pceType, final int from, final int to)`:** Checks if a move is in the correct direction for a sliding piece.
- **`dirIndexDescription(int dirIndex)`:** Returns a textual description of a direction index.
- **`knightMoveInDirFromPosStaysOnBoard(int dir, int pos)`:** Checks if a knight move in a given direction stays on the board.
- **`calcDirFromTo(int frompos, int topos)`:** Calculates the direction of a move between two positions.
- **`calcDirIndexFromTo(final int frompos, final int topos)`:** Calculates the direction index of a move between two positions.
- **`dirsAreOnSameAxis(int dir1, int dir2)`:** Checks if two directions are on the same axis.
- **`convertDir2AxisIndex(int dir)`:** Converts a direction to an axis index.
 
**Methods for pawns:**
- **`getAllPawnDirs(boolean col, int fromRank)`:** Returns an array of all possible pawn directions for a given color and rank.
- **`getAllPawnPredecessorDirs(boolean col, int fromRank)`:** Returns an array of all possible pawn predecessor directions for a given color and rank.
- **`hasLongPawnPredecessor(boolean color, int pos)`:** Checks if a pawn has a long predecessor move.
- **`getLongPawnPredecessorPos(boolean color, int pos)`:** Returns the origin position of a long pawn move.
- **`getLongPawnMoveMidPos(boolean color, int pos)`:** Returns the position of the square jumped over by a long pawn move.
- **`getSimpleStraightPawnPredecessorPos(boolean color, int pos)`:** Returns the position of the predecessor square for a simple straight pawn move.
- **`getBeatingPawnPredecessorDirs(boolean col, int fromRank)`:** Returns an array of possible beating pawn predecessor directions.
- **`getAllPawnPredecessorPositions(boolean col, int fromPos)`:** Returns a list of all possible pawn predecessor positions.
- **`getAllPawnAttackPositions(boolean col, int fromPos)`:** Returns a list of all possible pawn attack positions.
- **`canPawnOfColorReachPos(boolean color, int fromPos, int toPos)`:** Checks if a pawn of a given color can reach a target position.
- **`isPromotionRankForColor(int pos, boolean color)`:** Checks if a position is a promotion rank for a given color.
- **`promotionDistanceForColor(int pos, boolean color)`:** Returns the distance to the promotion rank for a given color.

**Methods for board positions:** 
- **`squareName(final int pos)`:** Returns the name of a square (e.g., "a1").
- **`rankOf(int pos)`:** Returns the rank of a position.
- **`fileOf(int pos)`:** Returns the file of a position.
- **`onSameFile(int pos1, int pos2)`:** Checks if two positions are on the same file.
- **`boardSideOf(final int pos)`:** Returns the side of the board (white or black) for a given position.
- **`coordinateString2Pos(String coordinate)`:** Converts a coordinate string (e.g., "a1") to a position.
- **`coordinateString2Pos(String move, final int coordinateIndexInString)`:** Converts a coordinate string to a position, starting at a specific index.
- **`fileRank2Pos(final int file, final int rank)`:** Converts file and rank to a position.
- **`isFirstFile(int pos)`:** Checks if a position is on the first file.
- **`isLastFile(int pos)`:** Checks if a position is on the last file.
- **`isFirstRank(int pos)`:** Checks if a position is on the first rank.
- **`isLastRank(int pos)`:** Checks if a position is on the last rank.
- **`firstFileInRank(int pos)`:** Returns the position of the first file in the same rank.
- **`lastFileInRank(int pos)`:** Returns the position of the last file in the same rank.
- **`firstRankInFile(int pos)`:** Returns the position of the first rank in the same file.
- **`lastRankInFile(int pos)`:** Returns the position of the last rank in the same file.
- **`squareColor(int pos)`:** Returns the color of a square (true for white, false for black).
- **`isSameSquareColor(int p1, int p2)`:** Checks if two squares have the same color.
- **`calcPositionsFromTo(final int fromPosIncl, final int toPosExcl)`:** Returns an array of positions between two squares.
- **`isBetweenFromAndTo(final int checkpos, final int fromPosExcl, final int toPosExcl)`:** Checks if a position is between two other positions.
- **`distanceBetween(final int fromPos, final int toPos)`:** Calculates the distance between two positions.
- **`neighbourSquareExistsInDirFromPos(int dir, int pos)`:** Checks if a neighboring square exists in a given direction.
- **`plusDirIsStillLegal(int pos, int dir)`:** Checks if a move in a given direction from a position stays on the board.
- **`formRightTriangle(int fromPos, int toPos1, int toPos2)`:** Checks if three positions form a right triangle.

**Other:**
- **`isFileChar(char f)`:** Checks if a character represents a file.
- **`isRankChar(char r)`:** Checks if a character represents a rank.
- **`bitmap2String(int cbm)`:** Converts a bitmap to a string representation.
- **`pos2bitmap(int pos)`:** Converts a position to a bitmap.
