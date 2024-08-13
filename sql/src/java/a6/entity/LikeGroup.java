package cs430.a6.entity;

/**
 * Represents like_group table
 */
public class LikeGroup {
    private final String customerId;
    private final String groupName;

    public LikeGroup(String customerId, String groupName) {
        this.customerId = customerId;
        this.groupName = groupName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public String getGroupName() {
        return groupName;
    }

    @Override
    public String toString() {
        return "LikeGroup{" +
                "customerId='" + customerId + '\'' +
                ", groupName='" + groupName + '\'' +
                '}';
    }
}
