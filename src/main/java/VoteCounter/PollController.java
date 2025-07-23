package VoteCounter;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class PollController {

    private final PollService pollService;

    public PollController(PollService pollService) {
        this.pollService = pollService;
    }

    @PostMapping("/vote")
    public void vote(@RequestBody Map<String, String> payload) {
        String vote = payload.get("vote");
        pollService.vote(vote);
    }

    @GetMapping("/results")
    public Map<String, Integer> results() {
        return pollService.getResults();
    }
}