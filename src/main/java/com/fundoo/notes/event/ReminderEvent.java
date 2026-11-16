
package com.fundoo.notes.event;

import java.time.LocalDateTime;

/*
 * Class Name  : ReminderEvent
 * Description : Stores reminder event details for RabbitMQ messaging.
 * Author      : Asher Mustafa
 * @author Asher
 * Date        : 16/11/2026
 */

public record ReminderEvent(
        Long userId,
        Long noteId,
        String email,
        String title,
        LocalDateTime reminderTime
) {
}
