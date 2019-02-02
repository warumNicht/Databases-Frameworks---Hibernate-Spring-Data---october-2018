package productsshop.domain.dto.query2;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "users")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class UsersSoldRootDto {
    @XmlElement(name = "user")
    private UserSoldDto[] userSoldDtos;

    public UserSoldDto[] getUserSoldDtos() {
        return userSoldDtos;
    }

    public void setUserSoldDtos(UserSoldDto[] userSoldDtos) {
        this.userSoldDtos = userSoldDtos;
    }
}
