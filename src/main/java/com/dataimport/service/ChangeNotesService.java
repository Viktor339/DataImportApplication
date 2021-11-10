package com.dataimport.service;

import com.dataimport.entity.ClientData;
import com.dataimport.entity.ClientNote;
import com.dataimport.entity.PatientNote;
import com.dataimport.repository.PatientNoteRepository;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.text.ParseException;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
@Transactional
public class ChangeNotesService {

    private final PatientNoteRepository patientNoteRepository;
    private final DateFormattingService dateFormattingService;
    @PersistenceContext
    private final EntityManager entityManager;
    private static final Logger LOGGER = LoggerFactory.getLogger(MainService.class);


    public void change(ClientData clientData, Map<String, Integer> clientGuidByPatientNote) throws ParseException {

        List<ClientNote> clientNoteList = clientData.getClientNote();

        for (ClientNote clientNote : clientNoteList) {

            Integer id = clientGuidByPatientNote.get(clientNote.getClientGuId());
            Calendar dateFromPatientNoteFormatted = patientNoteRepository.findPatientNoteById(id).getLastModifiedDateTime();

            String dateFromClientNote = clientNote.getModifiedDatetime();
            Calendar dateFromClientNoteFormatted = dateFormattingService.format(dateFromClientNote);

            String createdDateTimeFromClientNote = clientNote.getDateTime();
            Calendar createdDateFromClientNoteFormatted = dateFormattingService.format(createdDateTimeFromClientNote);


            if (dateFromClientNoteFormatted.compareTo(dateFromPatientNoteFormatted) > 0) {

                Integer patientNoteId = clientGuidByPatientNote.get(clientNote.getClientGuId());
                PatientNote patientNote = patientNoteRepository.findPatientNoteById(patientNoteId);


                if (!clientNote.getLoggedUser().equals(patientNote.getCreatedByUserId().getLogin())) {
                    LOGGER.error("user logins don't match: "
                            + " client_note.logged_user: " + clientNote.getLoggedUser() + ","
                            + " company_user.login: " + patientNote.getCreatedByUserId().getLogin());
                }

                if (createdDateFromClientNoteFormatted != patientNote.getCreatedDateTime()) {
                    LOGGER.error("date don't match: "
                            + "client_note.datetime: " + createdDateFromClientNoteFormatted.getTime() + ","
                            + " patient_note.created_date_time: " + patientNote.getCreatedDateTime().getTime());
                }

                patientNote.setNote(clientNote.getComments());
                patientNote.setLastModifiedDateTime(createdDateFromClientNoteFormatted);

                entityManager.persist(patientNote);
            }
        }
    }


}
