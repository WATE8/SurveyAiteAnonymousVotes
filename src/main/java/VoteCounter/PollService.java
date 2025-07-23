package VoteCounter;

import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class PollService {
    private final VoteRepository repo;

    public PollService(VoteRepository repo) {
        this.repo = repo;
    }

    @PostConstruct
    public void initOptions() {
        if (repo.count() == 0) {
            repo.saveAll(List.of(
                    new VoteOption("Java", 0),
                    new VoteOption("Python", 0),
                    new VoteOption("C++", 0),
                    new VoteOption("JavaScript", 0)
            ));
        }
    }

    public void vote(String name) {
        VoteOption option = repo.findById(name).orElse(null);
        if (option != null) {
            option.vote();
            repo.save(option);
        }
    }

    public Map<String, Integer> getResults() {
        return repo.findAll().stream()
                .collect(Collectors.toMap(VoteOption::getName, VoteOption::getCount));
    }
}