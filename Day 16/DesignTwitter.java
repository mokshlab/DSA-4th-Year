import java.util.*;

public class DesignTwitter {
    private static final int MAX_USERS = 501;
    private static final int MAX_TWEETS = 30000;
    private static final int MAX_FEED = 10;

    static class Tweet {
        int userId;
        int tweetId;
        int time;
        Tweet(int userId, int tweetId, int time) {
            this.userId = userId;
            this.tweetId = tweetId;
            this.time = time;
        }
    }

    private Tweet[] tweets;
    private int tweetCount;
    private int timestamp;
    private boolean[][] follows;

    public Twitter() {
        tweets = new Tweet[MAX_TWEETS];
        tweetCount = 0;
        timestamp = 0;
        follows = new boolean[MAX_USERS][MAX_USERS];
        for (int i = 0; i < MAX_USERS; i++) {
            follows[i][i] = true; // each user follows themselves
        }
    }

    public void postTweet(int userId, int tweetId) {
        tweets[tweetCount++] = new Tweet(userId, tweetId, timestamp++);
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> ans = new ArrayList<>();
        // walk backwards from most recent tweet
        for (int i = tweetCount - 1; i >= 0 && ans.size() < MAX_FEED; i--) {
            Tweet t = tweets[i];
            if (follows[userId][t.userId]) {
                ans.add(t.tweetId);
            }
        }
        return ans;
    }

    public void follow(int followerId, int followeeId) {
        follows[followerId][followeeId] = true;
    }

    public void unfollow(int followerId, int followeeId) {
        if (followerId != followeeId) { // can't unfollow yourself
            follows[followerId][followeeId] = false;
        }
    }

    // Main method to handle input/output
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();
        Twitter twitter = new Twitter();
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int x = sc.nextInt();
            if (x == 1) { // postTweet
                int userId = sc.nextInt();
                int tweetId = sc.nextInt();
                twitter.postTweet(userId, tweetId);
            } else if (x == 2) { // getNewsFeed
                int userId = sc.nextInt();
                List<Integer> feed = twitter.getNewsFeed(userId);
                for (int id : feed) sb.append(id).append(" ");
                sb.append("\n");
            } else if (x == 3) { // follow
                int followerId = sc.nextInt();
                int followeeId = sc.nextInt();
                twitter.follow(followerId, followeeId);
            } else if (x == 4) { // unfollow
                int followerId = sc.nextInt();
                int followeeId = sc.nextInt();
                twitter.unfollow(followerId, followeeId);
            }
        }
        System.out.print(sb);
    }
}