package com.dataimport.service;

import com.dataimport.entity.Client;
import com.dataimport.entity.ClientData;
import com.dataimport.entity.PatientNote;
import com.dataimport.entity.PatientProfile;
import com.dataimport.repository.PatientNoteRepository;
import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
@RequiredArgsConstructor
public class MainService {

    private final GetClientsRequestService getClientsRequestService;
    private final PatientNoteRepository patientNoteRepository;
    private final ChangeNotesService changeNotesService;
    private final GetClientNotesRequestService getClientNotesRequestService;
    private final RequestGetClientsNoteBuilderService requestGetClientsNoteBuilderService;

    private static final Logger LOGGER = LoggerFactory.getLogger(MainService.class);


    @Scheduled(cron = "${cron.config.time}")
    public void main() throws IOException, ParseException {

        List<Client> clients = getClientsRequestService.send();

        //get Agency for each client to send a request for /note/getClientNotes
        Map<String, String> clientGuIdByAgency = new HashMap<>();
        for (Client client : clients) {
            clientGuIdByAgency.put(client.getClientGuid(), client.getAgency());
        }

        List<PatientNote> patientNoteList = patientNoteRepository.findAll();

        Map<String, Integer> clientGuidByPatientNote = new HashMap<>();

        for (PatientNote patientNote : patientNoteList) {
            PatientProfile patientProfile = patientNote.getPatientId();
            if (patientProfile.getStatusId() == 200 || patientProfile.getStatusId() == 210 || patientProfile.getStatusId() == 230) {

                List<String> clientGuIdList = patientProfile.getOldClientGuid();
                for (String clientGuId : clientGuIdList) {
                    clientGuidByPatientNote.put(clientGuId, patientNote.getId());
                }
            }
        }

        // for each clientGuId send request for /note/getClientNotes, get Client data and change records in accordance with the condition
        for (Map.Entry<String, Integer> clientGuId : clientGuidByPatientNote.entrySet()) {

            String oldClientGuid = clientGuId.getKey();
            String agency = clientGuIdByAgency.get(clientGuId.getKey());

            if (agency == null) {
                LOGGER.error("agency not found for client id: " +oldClientGuid);
                break;
            }

            ClientData clientData = getClientNotesRequestService.send(requestGetClientsNoteBuilderService.buildRequest(agency, oldClientGuid));
            changeNotesService.change(clientData, clientGuidByPatientNote);
        }
    }
}
