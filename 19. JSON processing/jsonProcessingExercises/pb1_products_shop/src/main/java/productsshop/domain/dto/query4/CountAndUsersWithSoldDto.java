package productsshop.domain.dto.query4;

import java.util.List;

public class CountAndUsersWithSoldDto {
    private long usersCount;
    private List<UserWithSoldProducts> users;

    public long getUsersCount() {
        return usersCount;
    }

    public void setUsersCount(long usersCount) {
        this.usersCount = usersCount;
    }

    public List<UserWithSoldProducts> getUsers() {
        return users;
    }

    public void setUsers(List<UserWithSoldProducts> users) {
        this.users = users;
    }
}
