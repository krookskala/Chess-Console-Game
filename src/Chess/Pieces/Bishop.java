package Chess.Pieces;

import Board.Board;
import Board.Position;
import Chess.ChessPiece;
import Chess.Color;

public class Bishop extends ChessPiece{
    public Bishop(Board board, Color color) {
        super(board, color);
    }

    @Override
    public String toString() {
        return "B";
    }

    @Override
    public boolean[][] possibleMoves() {
        boolean[][] matrix = new boolean[board.getRows()][board.getColumns()];

        Position p = new Position(0, 0);

        // Move up-left
        p.setValues(position.getRow() - 1, position.getColumn() - 1);
        while (board.positionExists(p) && !board.thereIsAPiece(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() - 1, p.getColumn() - 1);
        }
        if (board.positionExists(p) && isThereOpponentPiece(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
        }

        // Move up-right
        p.setValues(position.getRow() - 1, position.getColumn() + 1);
        while (board.positionExists(p) && !board.thereIsAPiece(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() - 1, p.getColumn() + 1);
        }
        if (board.positionExists(p) && isThereOpponentPiece(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
        }

        // Move down-right
        p.setValues(position.getRow() + 1, position.getColumn() + 1);
        while (board.positionExists(p) && !board.thereIsAPiece(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() + 1, p.getColumn() + 1);
        }
        if (board.positionExists(p) && isThereOpponentPiece(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
        }

        // Move down-left
        p.setValues(position.getRow() + 1, position.getColumn() - 1);
        while (board.positionExists(p) && !board.thereIsAPiece(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
            p.setValues(p.getRow() + 1, p.getColumn() - 1);
        }
        if (board.positionExists(p) && isThereOpponentPiece(p)) {
            matrix[p.getRow()][p.getColumn()] = true;
        }

        return matrix;
    }

}
