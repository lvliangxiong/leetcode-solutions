package leetcode.editor.cn;

import leetcode.editor.cn.linkedlist.ListNode;

import java.util.*;

/**
 * <pre>
 * Design a simplified version of Twitter where users can post tweets, follow/unfollow another user and is able to
 * see the 10 most recent tweets in the user's news feed. Your design should support the following methods:
 *
 *     postTweet(userId, tweetId): Compose a new tweet.
 *
 *     getNewsFeed(userId): Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news
 *     feed must be posted by users who the user followed or by the user herself. Tweets must be ordered from most
 *     recent to least recent.
 *
 *     follow(followerId, followeeId): Follower follows a followee.
 *
 *     unfollow(followerId, followeeId): Follower unfollows a followee.
 *
 * Example:
 *
 * Twitter twitter = new Twitter();
 *
 * // User 1 posts a new tweet (id = 5).
 * twitter.postTweet(1, 5);
 *
 * // User 1's news feed should return a list with 1 tweet id -> [5].
 * twitter.getNewsFeed(1);
 *
 * // User 1 follows user 2.
 * twitter.follow(1, 2);
 *
 * // User 2 posts a new tweet (id = 6).
 * twitter.postTweet(2, 6);
 *
 * // User 1's news feed should return a list with 2 tweet ids -> [6, 5].
 * // Tweet id 6 should precede tweet id 5 because it is posted after tweet id 5.
 * twitter.getNewsFeed(1);
 *
 * // User 1 unfollows user 2.
 * twitter.unfollow(1, 2);
 *
 * // User 1's news feed should return a list with 1 tweet id -> [5],
 * // since user 1 is no longer following user 2.
 * twitter.getNewsFeed(1);
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/design-twitter
 * </pre>
 */
//leetcode submit region begin(Prohibit modification and deletion)
class Twitter {
    static final int RECENT_TWEET_NUM = 10;

    Map<Integer, TwitterUser> userMap = new HashMap<>();

    /**
     * Initialize your data structure here.
     */
    public Twitter() {
    }

    /**
     * Compose a new tweet.
     */
    public void postTweet(int userId, int tweetId) {
        createUserIfNotPresent(userId);
        TwitterUser user = userMap.get(userId);
        user.compose(tweetId);
    }

    /**
     * Retrieve the 10 most recent tweet ids in the user's news feed. Each item in the news feed must
     * be posted by users who the user followed or by the user herself. Tweets must be ordered from
     * most recent to least recent.
     *
     * @see MergeKSortedLists.Solution#mergeKLists(ListNode[])
     */
    public List<Integer> getNewsFeed(int userId) {
        List<Integer> ans = new ArrayList<>();
        if (userMap.containsKey(userId)) {
            TwitterUser user = userMap.get(userId);
            List<Tweet> news = new ArrayList<>();
            news.add(user.newest);
            for (Integer id : user.followee) {
                news.add(userMap.get(id).newest);
            }
            ans = extractNewsFeed(news);
        }
        return ans;
    }

    /**
     * 遍历自身和关注的人的 tweets，找出最新的 10 条记录，类似于合并 k 个有序链表
     * 这里采用了优先队列，速度比
     *
     * @param news
     * @return
     * @see MergeKSortedLists.Solution#mergeKLists(ListNode[])
     */
    private List<Integer> extractNewsFeed(List<Tweet> news) {
        PriorityQueue<Tweet> heap = new PriorityQueue<>(Comparator.comparingInt(tweet -> -tweet.time));
        List<Integer> ans = new ArrayList<>();
        for (Tweet newest : news) {
            if (newest != null) heap.add(newest);
        }
        while (!heap.isEmpty()) {
            Tweet newest = heap.remove();
            if (newest.next != null) heap.add(newest.next);
            ans.add(newest.tweetId);
            if (ans.size() == Twitter.RECENT_TWEET_NUM) break;
        }
        return ans;
    }

    /**
     * Follower follows a followee. If the operation is invalid, it should be a no-op.
     */
    public void follow(int followerId, int followeeId) {
        createUserIfNotPresent(followerId, followeeId);
        if (followerId == followeeId) return; // 不能自己关注自己
        TwitterUser user = userMap.get(followerId);
        user.followee.add(followeeId);
    }

    /**
     * Follower unfollows a followee. If the operation is invalid, it should be a no-op.
     */
    public void unfollow(int followerId, int followeeId) {
        createUserIfNotPresent(followerId, followeeId);
        TwitterUser user = userMap.get(followerId);
        user.followee.remove(followeeId);
    }

    private void createUserIfNotPresent(int... ids) {
        for (int id : ids) {
            userMap.computeIfAbsent(id, i -> new TwitterUser(i));
        }
    }
}

class TwitterUser {
    int userId;
    Set<Integer> followee = new HashSet<>(); // 关注的用户 id
    Tweet newest; // 发表的 tweet

    public TwitterUser(int userId) {
        this.userId = userId;
    }

    void compose(int tweetId) {
        Tweet tweet = new Tweet(tweetId, Tweet.count++);
        // 关联 tweet Deque 中的元素，使之形成一个单向链表
        tweet.next = newest;
        newest = tweet;
    }
}

class Tweet {
    static int count = 0;

    int tweetId;
    int time; // 代表 tweet 被发表的时间，数值越大，代表越新
    Tweet next;

    public Tweet(int tweetId) {
        this.tweetId = tweetId;
    }

    public Tweet(int tweetId, int time) {
        this.tweetId = tweetId;
        this.time = time;
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */
//leetcode submit region end(Prohibit modification and deletion)

