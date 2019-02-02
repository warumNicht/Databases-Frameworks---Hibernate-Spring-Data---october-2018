package productsshop.services;

import productsshop.domain.dto.query4.CountAndUsersWithSoldDto;
import productsshop.domain.dto.seedDtos.UserSeedDto;
import productsshop.domain.dto.UserSoldDto;

public interface UserService {

    void  seedUsers(UserSeedDto[] userSeedDtos);

    UserSoldDto[]  getUsersWithSoldProduct();

    CountAndUsersWithSoldDto usersProducts();
}
