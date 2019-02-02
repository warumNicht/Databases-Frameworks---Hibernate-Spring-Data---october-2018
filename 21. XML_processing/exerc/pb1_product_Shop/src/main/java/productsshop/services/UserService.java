package productsshop.services;

import productsshop.domain.dto.query2.UserSoldDto;
import productsshop.domain.dto.query4.CountAndUsersWithSoldRootDto;
import productsshop.domain.dto.seedDtos.UsersRootDto;

public interface UserService {

    void  seedUsers(UsersRootDto usersRootDto);

    UserSoldDto[]  getUsersWithSoldProduct();

    CountAndUsersWithSoldRootDto usersProducts();
}
