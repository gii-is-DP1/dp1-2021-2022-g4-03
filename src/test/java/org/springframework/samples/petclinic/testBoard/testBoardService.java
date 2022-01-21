package org.springframework.samples.petclinic.testBoard;

import static org.junit.Assert.assertThat;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.security.Provider.Service;
import java.util.Optional;

import org.junit.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.samples.petclinic.board.Board;
import org.springframework.samples.petclinic.board.BoardRepository;
import org.springframework.samples.petclinic.board.BoardService;
import org.springframework.samples.petclinic.game.GameRepository;
import org.springframework.samples.petclinic.game.GameService;

@DataJpaTest(includeFilters = @ComponentScan.Filter(Service.class))
public class testBoardService {

    @Autowired
    protected BoardService boardService;

    @Autowired
    protected BoardRepository boardRepository;

    @Autowired
    protected GameService gameService;

    @Autowired
    protected GameRepository gameRepository;



    @ParameterizedTest
    @ValueSource(ints = {1})
    public void testFindById(Integer id) {
        Optional<Board> optboard = boardRepository.findById(id);
        assertTrue(optboard.get().getId() == 1);

    }



}
