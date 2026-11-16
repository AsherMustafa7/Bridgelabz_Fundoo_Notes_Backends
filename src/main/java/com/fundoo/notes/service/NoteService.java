
package com.fundoo.notes.service;

import com.fundoo.notes.dto.request.NoteRequestDto;
import com.fundoo.notes.dto.response.NoteResponseDto;

import java.util.List;

/*
 * Class Name  : NoteService
 * Description : Defines business operations for creating,
 *               retrieving and updating user notes.
 * Author      : Asher Mustafa
 * @author Asher
 * Date        : 16/11/2026
 */

public interface NoteService {

    // [Asher Mustafa]: Creates a note for the authenticated user.
    NoteResponseDto createNote(NoteRequestDto requestDto, String token);

    // [Asher Mustafa]: Retrieves all notes belonging to the authenticated user.
    List<NoteResponseDto> getAllNotes(String token);

    // [Asher Mustafa]: Toggles the pin status of an authenticated user's note.
    NoteResponseDto togglePin(Long noteId, String token);

    // [Asher Mustafa]: Toggles the archive status of an authenticated user's note.
    NoteResponseDto toggleArchive(Long noteId, String token);

    // [Asher Mustafa]: Toggles the trash status of an authenticated user's note.
    NoteResponseDto toggleTrash(Long noteId, String token);
}
