package productsshop.domain.dto.query4;


import javax.xml.bind.annotation.*;
import java.util.List;
@XmlRootElement(name = "users")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CountAndUsersWithSoldRootDto {
    @XmlAttribute(name = "count")
    private long usersCount;

    @XmlElement(name = "user")
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
