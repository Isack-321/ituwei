package com.ituwei.polls.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import javax.validation.Valid;

import com.ituwei.polls.Model.Poll;
import com.ituwei.polls.Repository.PollRepo;
import com.ituwei.polls.Repository.UserRepo;
import com.ituwei.polls.Repository.VoteRepo;
import com.ituwei.polls.payload.ApiResponse;
import com.ituwei.polls.payload.PagedResponse;
import com.ituwei.polls.payload.PollRequest;
import com.ituwei.polls.payload.PollResponse;
import com.ituwei.polls.payload.VoteRequest;
import com.ituwei.polls.security.CurrentUser;
import com.ituwei.polls.security.UserPrincipal;
import com.ituwei.polls.service.PollService;
import com.ituwei.polls.util.AppConstants;

import java.net.URI;

@RestController
@RequestMapping("/api/polls")
public class PollController {

    @Autowired
    private PollRepo pollRepository;

    @Autowired
    private VoteRepo voteRepository;

    @Autowired
    private UserRepo userRepository;

    @Autowired
    private PollService pollService;

    private static final Logger logger = LoggerFactory.getLogger(PollController.class);

    @GetMapping
    public PagedResponse<PollResponse> getPolls(@CurrentUser UserPrincipal currentUser,
            @RequestParam(value = "page", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = AppConstants.DEFAULT_PAGE_SIZE) int size) {
        return pollService.getAllPolls(currentUser, page, size);
    }

    @PostMapping
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createPoll(@Valid @RequestBody PollRequest pollRequest) {
        Poll poll = pollService.createPoll(pollRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{pollId}").buildAndExpand(poll.getId())
                .toUri();

        return ResponseEntity.created(location).body(new ApiResponse(true, "Poll Created Successfully"));
    }

    @GetMapping("/{pollId}")
    public PollResponse getPollById(@CurrentUser UserPrincipal currentUser, @PathVariable Long pollId) {
        return pollService.getPollById(pollId, currentUser);
    }

    @PostMapping("/{pollId}/votes")
    @PreAuthorize("hasRole('USER')")
    public PollResponse castVote(@CurrentUser UserPrincipal currentUser, @PathVariable Long pollId,
            @Valid @RequestBody VoteRequest voteRequest) {
        return pollService.castVoteAndGetUpdatedPoll(pollId, voteRequest, currentUser);
    }
}