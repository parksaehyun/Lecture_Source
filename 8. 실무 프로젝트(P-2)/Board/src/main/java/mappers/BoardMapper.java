package mappers;

import board.Board;

import java.util.List;

public interface BoardMapper {
    int plusBoard(Board board);
    List<Board> getBoard();;
}
