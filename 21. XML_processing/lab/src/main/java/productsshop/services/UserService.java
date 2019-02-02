package productsshop.services;

import productsshop.domain.dto.UserSoldDto;
import productsshop.domain.dto.query4.CountAndUsersWithSoldDto;
import productsshop.domain.dto.seedDtos.UserSeedDto;

public interface UserService {

    void  seedUsers(UserSeedDto[] userSeedDtos);

    UserSoldDto[]  getUsersWithSoldProduct();

    CountAndUsersWithSoldDto usersProducts();
}
