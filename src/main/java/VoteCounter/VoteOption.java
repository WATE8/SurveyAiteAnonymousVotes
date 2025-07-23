package VoteCounter;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class VoteOption {
    @Id
    private String name;
    private int count;

    public VoteOption() {}

    public VoteOption(String name, int count) {
        this.name = name;
        this.count = count;
    }

    public String getName() { return name; }
    public int getCount() { return count; }

    public void vote() {
        this.count++;
    }
}