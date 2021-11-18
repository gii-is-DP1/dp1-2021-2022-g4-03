package org.springframework.samples.petclinic.board;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BoardService {
    
    @Autowired 
	BoardService boardRepository;
	
	public Optional<Board> findById(Integer id){
		return boardRepository.findById(id);
	}

	// public Board createBoard() {
    //     Board board = new Board();
    //     return board;
    // }

}
