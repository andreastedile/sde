package it.unitn.sde.controller;

import it.unitn.sde.model.Result;
import it.unitn.sde.model.Vote;
import it.unitn.sde.service.ApiService;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;

@RestController
public class ApiController {
    @Autowired
    Environment environment;

    @Autowired
    private ApiService resultService;

    @GetMapping(value = "/info", produces = "application/json")
    public ResponseEntity<?> getInfo() {
        JSONObject response = new JSONObject();

        String port = environment.getProperty("server.port");
        response.put("server port", port);

        try {
            String hostname = InetAddress.getLocalHost().getHostName();
            response.put("server hostname", hostname);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }

        String remoteAddress = InetAddress.getLoopbackAddress().getHostAddress();
        response.put("remote address", remoteAddress);

        return ResponseEntity
                .ok()
                .body(response.toString());
    }


    @PostMapping(value = "/votes", produces = "application/json")
    public ResponseEntity<?> createVote(@RequestBody @Valid Vote vote) {
        JSONObject header;
        JSONObject response;

        Boolean exists = resultService.exists(vote.getOption());
        if (exists == null) {
            header = new JSONObject()
                    .put("status", "error")
                    .put("status_code", HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .put("description", "database error");
            response = new JSONObject()
                    .put("header", header);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response.toString());
        }
        if (!exists) {
            header = new JSONObject()
                    .put("status", "error")
                    .put("status_code", HttpStatus.BAD_REQUEST.value())
                    .put("description", "invalid option");
            response = new JSONObject()
                    .put("header", header);
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response.toString());
        }

        Boolean ok = resultService.addVote(vote.getOption());
        if (ok == null) {
            header = new JSONObject()
                    .put("status", "error")
                    .put("status_code", HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .put("description", "database error");
            response = new JSONObject()
                    .put("header", header);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response.toString());
        }
        if (!ok) {
            header = new JSONObject()
                    .put("status", "error")
                    .put("status_code", HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .put("description", "could not post the vote");
            response = new JSONObject()
                    .put("header", header);
            return ResponseEntity
                    .ok()
                    .body(response.toString());
        }
        header = new JSONObject()
                .put("status", "ok")
                .put("status_code", HttpStatus.OK.value())
                .put("description", "vote posted");
        response = new JSONObject()
                .put("header", header);
        return ResponseEntity
                .ok()
                .body(response.toString());
    }


    @GetMapping(value = "/votes", produces = "application/json")
    public ResponseEntity<?> getVote() {
        JSONObject header;
        JSONObject body;
        JSONObject response;

        List<Result> listResult = resultService.getVotes();
        if (listResult == null) {
            header = new JSONObject()
                    .put("status", "error")
                    .put("status_code", HttpStatus.INTERNAL_SERVER_ERROR.value())
                    .put("description", "database error");
            response = new JSONObject()
                    .put("header", header);
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response.toString());
        }

        header = new JSONObject()
                .put("status", "ok")
                .put("status_code", HttpStatus.OK.value())
                .put("description", "votes retrieved");
        body = new JSONObject();
        for (Result result : listResult)
            body.put(result.getOption(), result.getVotes());

        response = new JSONObject()
                .put("header", header)
                .put("body", body);
        return ResponseEntity
                .ok()
                .body(response.toString());
    }
}
