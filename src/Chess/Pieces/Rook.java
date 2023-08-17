package Chess.Pieces;

import Board.Board;
import Board.Position;
import Chess.ChessPiece;
import Chess.Color;

public class Rook extends ChessPiece {

    public Rook(Board board, Color color) {
        super(board, color);
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[board.getRows()][board.getColumns()];

        Position p = new Position(0, 0);

        // Move left
        p.setValues(position.getRow(), position.getColumn() - 1);
        while (board.positionExists(p) && !board.thereIsAPiece(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() - 1);
        }
        if (board.positionExists(p) && isThereOpponentPiece(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
        }

        // Move right
        p.setValues(position.getRow(), position.getColumn() + 1);
        while (board.positionExists(p) && !board.thereIsAPiece(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
            p.setColumn(p.getColumn() + 1);
        }
        if (board.positionExists(p) && isThereOpponentPiece(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
        }

        // Move up
        p.setValues(position.getRow() - 1, position.getColumn());
        while (board.positionExists(p) && !board.thereIsAPiece(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() - 1);
        }
        if (board.positionExists(p) && isThereOpponentPiece(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
        }

        // Move down
        p.setValues(position.getRow() + 1, position.getColumn());
        while (board.positionExists(p) && !board.thereIsAPiece(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
            p.setRow(p.getRow() + 1);
        }
        if (board.positionExists(p) && isThereOpponentPiece(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
        }

        return matrix;
    }

    @Override
    public String toString() {
        return "R";
    }
}
