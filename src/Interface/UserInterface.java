package Interface;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

import Chess.ChessMatch;
import Chess.ChessPiece;
import Chess.ChessPosition;
import Chess.Color;

public class UserInterface {
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLACK = "\u001B[30m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";
    public static final String ANSI_GREEN_BACKGROUND = "\u001B[42m";

    public static void clearScreen() {
        System.out.println("\033[H\033[2J");
        System.out.flush();
    }

    public static ChessPosition readChessPosition(Scanner scanner) {
        try {
            String input = scanner.nextLine();
            char column = input.charAt(0);
            int row = Integer.parseInt(input.substring(1));
            return new ChessPosition(column, row);
        } catch (RuntimeException exception) {
            throw new InputMismatchException(ANSI_RED + "Invalid position. Enter a valid position (a1 to h8)." + ANSI_RESET);
        }
    }

    public static void printMatch(ChessMatch chessMatch, List<ChessPiece> capturedPieces) {
        printBoard(chessMatch.getPieces());
        System.out.println();
        printCapturedPieces(capturedPieces);
        System.out.println();
        System.out.println(ANSI_RED + "Turn : " + ANSI_RESET + chessMatch.getTurn());
        if (!chessMatch.getCheckMate()) {
            System.out.println(ANSI_GREEN + "Waiting player: " + ANSI_RESET + chessMatch.getCurrentPlayer());
            if (chessMatch.getCheck()) {
                System.out.println(ANSI_RED + "CHECK!" + ANSI_RESET);
            }
        } else {
            System.out.println(ANSI_RED + "CHECKMATE!" + ANSI_RESET);
            System.out.println(ANSI_GREEN + "Winner: " + ANSI_RESET + chessMatch.getCurrentPlayer());
        }
    }

    public static void printBoard(ChessPiece[][] pieces) {
        System.out.println(ANSI_CYAN + "   ______________________" + ANSI_RESET);
        for (int i = 0; i < pieces.length; i++) {
            System.out.print(ANSI_RED + (8 - i) + ANSI_CYAN + " | " + ANSI_RESET);
            for (int j = 0; j < pieces[i].length; j++) {
                printPiece(pieces[i][j], false);
            }
            System.out.println(ANSI_CYAN + "|" + ANSI_RESET);
        }
        System.out.println(ANSI_CYAN + "   _______________________" + ANSI_RESET);
        System.out.println(ANSI_RED + "   A  B  C  D  E  F  G  H " + ANSI_RESET);
    }

    public static void printBoard(ChessPiece[][] pieces, boolean[][] possibleMoves) {
        System.out.println(ANSI_CYAN + " _________________" + ANSI_RESET);
        for (int i = 0; i < pieces.length; i++) {
            System.out.print((8 - i) + ANSI_CYAN + " | " + ANSI_RESET);
            for (int j = 0; j < pieces[i].length; j++) {
                printPiece(pieces[i][j], possibleMoves[i][j]);
            }
            System.out.println(ANSI_CYAN + "|" + ANSI_RESET);
        }
        System.out.println(ANSI_CYAN + " _________________" + ANSI_RESET);
        System.out.println(ANSI_RED + " A B C D E F G H" + ANSI_RESET);
    }

    public static void printPiece(ChessPiece piece, boolean highlight) {
        if (highlight) {
            System.out.print(ANSI_GREEN_BACKGROUND);
        }
        if (piece == null) {
            System.out.print("- " + ANSI_RESET);
        } else {
            switch (piece.getColor()) {
                case WHITE:
                    System.out.print(getWhitePieceUnicode(piece) + ANSI_RESET);
                    break;
                case BLACK:
                    System.out.print(getBlackPieceUnicode(piece) + ANSI_RESET);
                    break;
            }
        }
        System.out.print(" ");
    }

    private static String getWhitePieceUnicode(ChessPiece piece) {
        switch (piece.toString()) {
            case "K":
                return "\u265A";
            case "Q":
                return "\u265B";
            case "R":
                return "\u265C";
            case "B":
                return "\u265D";
            case "N":
                return "\u265E";
            case "P":
                return "\u265F";
            default:
                throw new IllegalArgumentException(ANSI_RED + "Invalid chess piece!" + ANSI_RESET);
        }
    }

    private static String getBlackPieceUnicode(ChessPiece piece) {
        switch (piece.toString()) {
            case "K":
                return "\u2654";
            case "Q":
                return "\u2655";
            case "R":
                return "\u2656";
            case "B":
                return "\u2657";
            case "N":
                return "\u2658";
            case "P":
                return "\u2659";
            default:
                throw new IllegalArgumentException(ANSI_RED + "Invalid chess piece!" + ANSI_RESET);
        }
    }

    private static void printCapturedPieces(List<ChessPiece> capturedPieces) {
        List<ChessPiece> whiteCapturedPieces = capturedPieces.stream().filter(p -> p.getColor() == Color.WHITE).collect(Collectors.toList());
        List<ChessPiece> blackCapturedPieces = capturedPieces.stream().filter(p -> p.getColor() == Color.BLACK).collect(Collectors.toList());
        System.out.println(ANSI_YELLOW + "Captured pieces:" + ANSI_RESET);
        System.out.print(ANSI_WHITE + "White: " + ANSI_RESET);
        System.out.print(ANSI_YELLOW);
        System.out.print("[");
        System.out.print(whiteCapturedPieces.stream().map(p -> getWhitePieceUnicode(p)).collect(Collectors.joining(", ")));
        System.out.println("]");
        System.out.print(ANSI_RESET);
        System.out.print(ANSI_BLACK + "Black: " + ANSI_RESET);
        System.out.print(ANSI_YELLOW);
        System.out.print("[");
        System.out.print(blackCapturedPieces.stream().map(p -> getBlackPieceUnicode(p)).collect(Collectors.joining(", ")));
        System.out.println("]");
        System.out.print(ANSI_RESET);
    }
}

