
package com.fundoo.notes.service.impl;

import com.fundoo.notes.dto.request.NoteRequestDto;
import com.fundoo.notes.dto.response.NoteResponseDto;
import com.fundoo.notes.entity.Note;
import com.fundoo.notes.entity.User;
import com.fundoo.notes.exception.InvalidCredentialsException;
import com.fundoo.notes.repository.NoteRepository;
import com.fundoo.notes.repository.UserRepository;
import com.fundoo.notes.service.NoteService;
import com.fundoo.notes.service.TokenService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/*
 * Class Name  : NoteServiceImpl
 * Description : Implements note creation and retrieval
 *               operations using repositories and JWT.
 * Author      : Asher Mustafa
 * @author Asher
 * Date        : 14/11/2026
 */

@Service
public class NoteServiceImpl implements NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final TokenService tokenService;

    // [Asher Mustafa]: Constructor injection for required dependencies.
    public NoteServiceImpl(
            NoteRepository noteRepository,
            UserRepository userRepository,
            TokenService tokenService) {

        this.noteRepository = noteRepository;
        this.userRepository = userRepository;
        this.tokenService = tokenService;
    }

    @Override
    @Transactional
    public NoteResponseDto createNote(
            NoteRequestDto requestDto,
            String token) {

        // [Asher Mustafa]: Resolve the owner from the verified JWT.
        User user = findUserFromToken(token);

        // [Asher Mustafa]: Create a new note and associate it with its owner.
        Note note = new Note();
        note.setTitle(requestDto.getTitle());
        note.setDescription(requestDto.getDescription());
        note.setUser(user);

        // [Asher Mustafa]: Persist the note in the database.
        Note savedNote = noteRepository.save(note);

        return mapToResponse(savedNote);
    }

    @Override
    @Transactional(readOnly = true)
    public List<NoteResponseDto> getAllNotes(String token) {

        // [Asher Mustafa]: Identify the user making the request.
        User user = findUserFromToken(token);

        // [Asher Mustafa]: Retrieve only notes owned by this user.
        List<Note> notes = noteRepository.findByUserId(user.getId());

        // [Asher Mustafa]: Convert entities into response DTOs.
        return notes.stream()
                .map(this::mapToResponse)
                .toList();
    }

    // [Asher Mustafa]: Validates the JWT and retrieves its corresponding user.
    private User findUserFromToken(String token) {

        if (token == null || token.isBlank()) {
            throw new InvalidCredentialsException("Authorization token is required");
        }

        // [Asher Mustafa]: Remove Bearer prefix when supplied by the client.
        String jwt = token.startsWith("Bearer ")
                ? token.substring(7).trim()
                : token.trim();

        if (!tokenService.isTokenValid(jwt)) {
            throw new InvalidCredentialsException("Invalid or expired token");
        }

        // [Asher Mustafa]: JWT subject contains the user's email.
        String email = tokenService.extractSubject(jwt);

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new InvalidCredentialsException("User associated with token not found"));
    }

    // [Asher Mustafa]: Maps the Note entity to an API response DTO.
    private NoteResponseDto mapToResponse(Note note) {

        return new NoteResponseDto(
                note.getId(),
                note.getTitle(),
                note.getDescription(),
                note.isPinned(),
                note.isArchived(),
                note.isTrashed()
        );
    }
}
